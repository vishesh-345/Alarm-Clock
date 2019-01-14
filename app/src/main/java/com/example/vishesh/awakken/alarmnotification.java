package com.example.vishesh.awakken;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class alarmnotification extends AppCompatActivity {
    TextView Motive;
    Button snoozeButton;
    Button dismissButton;
    AlarmManager alarmManager;
    PendingIntent sender;
    Intent AlarmIntent;
    MediaPlayer mMediaPlayer;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmnotification);

        Motive = (TextView) findViewById(R.id.Motive);
        Motive.setText(getIntent().getStringExtra("MOTIVE"));
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this,uri);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
        dismissButton = (Button) findViewById(R.id.dismissButton);
        snoozeButton = (Button) findViewById(R.id.snoozeButton);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlarmManager AlmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                AlarmIntent = new Intent(getApplicationContext(),
                        AlarmReceiver.class);
                sender= PendingIntent.getBroadcast(
                        getApplicationContext(),getIntent().getIntExtra("REQUEST CODE",0), AlarmIntent,0);
                AlmMgr.cancel(sender);
                mMediaPlayer.stop();
                finish();;

            }
        });
        snoozeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                //int snoozeTime = mMinute + SNOOZE_MIN;
                calendar.add(Calendar.MINUTE,getIntent().getIntExtra("SNOOZE",1) );
                long snoozeTime = calendar.getTimeInMillis();
                //Build Intent and Pending Intent to Set Snooze Alarm
                AlarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                alarmManager= (AlarmManager)getSystemService(ALARM_SERVICE);
                int id=getIntent().getIntExtra("REQUEST CODE",0);
                AlarmIntent.putExtra("REQUEST CODE",getIntent().getIntExtra("REQUEST CODE",0));
                AlarmIntent.putExtra("MOTIVE",getIntent().getStringExtra("MOTIVE"));
                sender = PendingIntent.getBroadcast(getApplicationContext(),id, AlarmIntent,0);
                alarmManager.set(AlarmManager.RTC_WAKEUP, snoozeTime, sender);
                Toast.makeText(getApplicationContext(),"snoozed for "+getIntent().getIntExtra("SNOOZE",1)+" minutes. Next buzz on "+ calendar.getTime().toString(), Toast.LENGTH_LONG).show();
                mMediaPlayer.stop();
                finish();

            }
        });
    }
}
