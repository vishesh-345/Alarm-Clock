package com.example.vishesh.awakken;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class Activity1 extends AppCompatActivity {
    Button b2;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        timePicker =(TimePicker)findViewById(R.id.timePicker);
        b2=(Button)findViewById(R.id.proceed);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),Activity2.class);
                //get the hour and minute by bundle timepicker
               int hour=timePicker.getCurrentHour();
                int minutes=timePicker.getCurrentMinute();
                //passing data from one activity to another activity
                //("key value",value)
                j.putExtra("hour",hour);
                j.putExtra("minutes",minutes);

                startActivity(j);
            }
        });
    }
}
