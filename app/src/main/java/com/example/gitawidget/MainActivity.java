package com.example.gitawidget;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.view.View;

import static com.example.gitawidget.AppWrapper.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    public static final String SHARED_PREFS = "Gita_Widget_Shared_Preferences";
    public static final String EnableNotifsKey = "Enable_Notifications";
   


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // NOTIFICATION MANAGER
        notificationManager = NotificationManagerCompat.from(this);

    }

    public void openSettings(View v)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void sendNotificationBroadcast(View v)
    {
        Intent intent = new Intent(this, NotificationBroadcastReceiver.class);
        sendBroadcast(intent);
    }



}