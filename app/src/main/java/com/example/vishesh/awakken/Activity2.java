package com.example.vishesh.awakken;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.Calendar;

public class Activity2 extends AppCompatActivity {
    Calendar calendar;
    CheckBox checkBox;
    EditText MotiveText;
    EditText snoozeText;
    Button setButton;
    Button cancelButton;
    Intent AlarmIntent;
    public AlarmManager alarmManager;
    PendingIntent sender;
    Calendar curCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        //now get your bundle value(your key,deafult value)
        //default means if error ki wajah s value pass n ho to 0 print ho
        int hour=this.getIntent().getIntExtra("hour",0);
        int minutes=this.getIntent().getIntExtra("minutes",0);
        calendar= Calendar.getInstance();
        //.hour of the day means 24 hour and
        //.hour for 12 hour format
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minutes);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        MotiveText=(EditText)findViewById(R.id.MotiveText);
        snoozeText=(EditText)findViewById(R.id.snoozeText);
        setButton=(Button)findViewById(R.id.set);
        cancelButton=(Button)findViewById(R.id.cancel);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmIntent=new Intent(getApplicationContext(),AlarmReceiver.class);
                alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                int _id = (int) System.currentTimeMillis();
                //for sending data putextra
                AlarmIntent.putExtra("REQUEST CODE", _id);
                AlarmIntent.putExtra("IS VIBRATE",checkBox.isChecked());
                AlarmIntent.putExtra("MOTIVE", MotiveText.getText().toString());
                AlarmIntent.putExtra("SNOOZE",Integer.parseInt(snoozeText.getText().toString()));
                //(contex,int,intent,int)
                sender = PendingIntent.getBroadcast(getApplicationContext(), _id, AlarmIntent, 0);
                curCalendar = Calendar.getInstance();
                curCalendar.set(Calendar.SECOND,0);
                curCalendar.set(Calendar.MILLISECOND, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                if (calendar.getTimeInMillis() <= curCalendar.getTimeInMillis()) {
                    calendar.add(Calendar.HOUR, 24);
                }

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
                String CalendarHourStr;
                String date = (String) calendar.getTime().toString();
                Toast.makeText(getApplicationContext(), "Alarm is set for" + date, Toast.LENGTH_LONG).show();


            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(k);
            }
        });
    }
}
