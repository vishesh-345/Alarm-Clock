package com.example.vishesh.awakken;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Vishesh on 09-Oct-17.
 */



public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent in = new Intent(Intent.ACTION_MAIN);
        in.setClass(context, alarmnotification.class);

        in.putExtra("REQUEST CODE", intent.getIntExtra("REQUEST CODE",0));
        in.putExtra("MOTIVE",intent.getStringExtra("MOTIVE"));
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(in);
    }
}
