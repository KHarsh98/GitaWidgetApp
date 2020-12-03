package com.example.gitawidget;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.content.Context.MODE_PRIVATE;
import static com.example.gitawidget.AppWrapper.CHANNEL_1_ID;
import static com.example.gitawidget.MainActivity.EnableNotifsKey;
import static com.example.gitawidget.MainActivity.SHARED_PREFS;

public class NotificationBroadcastReceiver extends BroadcastReceiver {


    private NotificationManagerCompat notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Triggered", Toast.LENGTH_LONG).show();
        notificationManager = NotificationManagerCompat.from(context);
        //TODO: Pull in a random quote from the database

        String quote = "Ram Naam Satya Hai";

        //TODO: Fix bug - when notification is tapped, app doesn't open
        Intent open_intent = new Intent (context, MainActivity.class);
        open_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 24, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_1_ID )
                .setSmallIcon(R.drawable.ic_baseline_home_repair_service_24)
                .setContentTitle("A Gita quote for you")
                .setContentText(quote)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(quote));

        notificationManager.notify(1, builder.build());


    }



}
