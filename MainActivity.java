package com.example.taskmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    EditText hi;
    Button button;
    ListView list;
    LinkedList<String> ll;
    ArrayAdapter<String> hello;
    TextView text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(this.getFilesDir().toString());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this,R.style.DeepShadow);
        ll =  new LinkedList<String>();
        ReadFromFile h = new ReadFromFile();
        ll = h.read(this);
        String[] x = new String[ll.toArray().length];
        x = ll.toArray(x);
        hello = new AlterAdapter<String>(this,android.R.layout.simple_list_item_1,ll);
        text2 = findViewById(R.id.textView3);
        if (ll.toArray().length != 0) {
            text2.setVisibility(View.INVISIBLE);
        }
        list = findViewById(R.id.Listview);
        list.setAdapter(hello);
        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //NotificationChannel hi = new NotificationChannel("hello","Adichannel", NotificationManager.IMPORTANCE_MAX);
                //NotificationManagerCompat hi8 = NotificationManagerCompat.from(MainActivity.this);
                //hi8.createNotificationChannel(hi);
                //NotificationCompat.Builder hi9 = new NotificationCompat.Builder(MainActivity.this,"hello").setContentText("You are adding a task").setSmallIcon(R.drawable.ic_launcher_background);
                //hi8.notify(0, hi9.build());
                String helloo = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(view.getContext(),Activity2.class);
                intent.putExtra(Intent.EXTRA_TEXT,helloo);
                startActivityForResult(intent,1);
                System.out.println(1);

            }
        });
        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               Intent text = new Intent(view.getContext(), Activity1.class);
               startActivityForResult(text,0);
               text2.setVisibility(View.INVISIBLE);
            }
        });
        DataToFile hi = new DataToFile();
        hi.put(this,ll);
    }
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        System.out.println(3);
        if(resultCode == RESULT_OK) {
            System.out.println(4);
            if(requestCode == 0) {
                DataToFile hi = new DataToFile();
                if (data.getLongExtra(Intent.EXTRA_INDEX,0) >= 0) {
                    ll.add(hi.index(ll, data.getStringExtra(Intent.EXTRA_CC)), data.getStringExtra(Intent.EXTRA_TEXT) + "\nDays left: " + data.getLongExtra(Intent.EXTRA_INDEX, 0) + "\nDue: " + data.getStringExtra(Intent.EXTRA_CC));
                    hello.notifyDataSetChanged();
                }
            }
            if(requestCode == 1) {
                if (data.getStringExtra(Intent.EXTRA_CC).equals("Del")) {
                   System.out.println(data.getStringExtra(Intent.EXTRA_INDEX));

                   hello.remove(data.getStringExtra(Intent.EXTRA_INDEX));
                   hello.notifyDataSetChanged();
                    if (ll.toArray().length == 0) {
                        text2.setVisibility(View.VISIBLE);
                    }
                } else if (data.getStringExtra(Intent.EXTRA_CC).equals("Save")) {
                    System.out.println(5);
                    DataToFile hi = new DataToFile();
                    hello.remove(data.getStringExtra(Intent.EXTRA_INDEX));
                    hello.notifyDataSetChanged();
                    String [] hi9 = data.getStringExtra(Intent.EXTRA_TEXT).toString().split("\n");
                    String [] hi10 = hi9[2].split(" ");
                    ll.add(hi.index(ll,hi10[1]),data.getStringExtra(Intent.EXTRA_TEXT));
                    hello.notifyDataSetChanged();
                }
            }
        }
    }
    protected void onStop() {
        super.onStop();
        System.out.println(this.getFilesDir().toString());
        System.out.println("OnDestroy1");
        System.out.println("hi");
        DataToFile hi = new DataToFile();
        hi.put(this,ll);
    }
    //@Override
    /*public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
