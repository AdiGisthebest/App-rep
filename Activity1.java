package com.example.taskmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Activity1 extends Activity {
    String datestr = "";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        final Button button = findViewById(R.id.button);
        final EditText text2 = findViewById(R.id.editText);
        final CalendarView date = findViewById(R.id.editText3);
        SimpleDateFormat hi = new SimpleDateFormat("MM/dd/yyyy");
        datestr = hi.format(new Date(date.getDate()));
        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                datestr = (month + 1) + "/" + dayOfMonth + "/"+year;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent ret = new Intent();
                ret.putExtra(Intent.EXTRA_TEXT,text2.getText().toString());
                ret.putExtra(Intent.EXTRA_CC,datestr);
                setResult(RESULT_OK,ret);
                finish();
            }
        });
    }
}
