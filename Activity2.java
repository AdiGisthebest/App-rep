package com.example.taskmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity2 extends Activity {
    String datetxt;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick);
        final Intent intent = getIntent();
        String [] hi4 = intent.getStringExtra(Intent.EXTRA_TEXT).split(" ");
        final Button del = findViewById(R.id.del);
        final Button save = findViewById(R.id.save);
        final EditText hi2 = findViewById(R.id.editText4);
        int len = hi4.length - 3;
        System.out.println(len + " len");
        String strttext = hi4[0];
        for (int i = 0; i < len; i++) {
            strttext = strttext +" "+ hi4[i + 1];
        }
        hi2.setText(strttext);
        final CalendarView hi3 = findViewById(R.id.editText5);
        Date date = new Date();
        SimpleDateFormat hi = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date = hi.parse(hi4[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        datetxt = hi4[hi4.length - 1];
        hi3.setDate(date.getTime());
        final Button discard = findViewById(R.id.discard);
        hi3.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                datetxt = (month + 1) + "/" + dayOfMonth + "/" + year;
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent hi = new Intent();
                setResult(RESULT_OK,hi);
                hi.putExtra(Intent.EXTRA_CC,"Del");
                hi.putExtra(Intent.EXTRA_INDEX,intent.getStringExtra(Intent.EXTRA_TEXT));
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hi = new Intent();
                setResult(RESULT_OK,hi);
                hi.putExtra(Intent.EXTRA_CC,"Save");
                hi.putExtra(Intent.EXTRA_INDEX,intent.getStringExtra(Intent.EXTRA_TEXT));
                hi.putExtra(Intent.EXTRA_TEXT,hi2.getText().toString() + " Due: " + datetxt);
                finish();
            }
        });
        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hi = new Intent();
                setResult(RESULT_OK,hi);
                hi.putExtra(Intent.EXTRA_CC,"Discard");
                finish();
            }
        });
    }
}
