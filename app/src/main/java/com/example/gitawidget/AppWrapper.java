package com.example.gitawidget;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class AppWrapper extends Application {
    public static final String CHANNEL_1_ID = "GitaWidgetNotificationChannel";


    /*

    This one wraps around the entire app. useful for notifications and other services
     */
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();

    }

    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // O stands for Oreo
            NotificationChannel channel1 = new NotificationChannel( // Create a channel- channels can be used to customize these notifs into different categories
                    CHANNEL_1_ID, // pass the ID
                    "channel1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is channel 1");


            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

        }
    }

}
