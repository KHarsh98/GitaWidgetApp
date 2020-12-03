package com.example.gitawidget;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "Gita_Widget_Shared_Preferences";
    public static final String EnableNotifsKey = "Enable_Notifications";
    private Switch enableNotificationSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        enableNotificationSwitch = (Switch) (findViewById(R.id.enable_notifications_switch));



        enableNotificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startAlarm();
                }

                else {
                    cancelAlarm();
                }


            }
        });
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE); // PRIVATE MEANS ON OTHER APP CAN ACCESS IT
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(EnableNotifsKey, enableNotificationSwitch.isChecked());

        editor.apply();
    }

    public void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        enableNotificationSwitch.setChecked(sharedPreferences.getBoolean(EnableNotifsKey, false));
    }

    private void startAlarm()
    {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);

        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, min+1);
        c.set(Calendar.SECOND, 0);

        //TODO: set alarm to be repeating with one day interval as an argument
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        System.out.println("Alarm set");

        //sendBroadcast1();
    }

    private void cancelAlarm() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.cancel(pendingIntent);
        System.out.println("Alarm cancelled");

    }


    public void sendBroadcast1()
    {
        Intent intent = new Intent(this, NotificationBroadcastReceiver.class);
        sendBroadcast(intent);
    }

}