package com.example.gitawidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

public class GitaWidgetProvider extends AppWidgetProvider {

    public static final String RandomQuote = "RandomQuote"; // RANDOM QOUTE ACTION LISTENER
    public static final String ChapterSelect = "ChapterSelect"; // Select chapter ACTION LISTENER
    protected PendingIntent getPendingSelfIntent(Context context, String action)
    {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class); // To create the Main Activity which the app itself
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0); // To make it a pending Intent
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.gita_widget); // To handle the current widget view
            views.setOnClickPendingIntent(R.id.gita_widget_open_app_button, pendingIntent);
            views.setOnClickPendingIntent(R.id.git_widget_random_button, getPendingSelfIntent(context, RandomQuote));
            views.setOnClickPendingIntent(R.id.gita_widget_chapter_button, getPendingSelfIntent(context, ChapterSelect));
            appWidgetManager.updateAppWidget(appWidgetId, views);




        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
       super.onReceive(context, intent);
       RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.gita_widget);

        if (RandomQuote.equals(intent.getAction()))
        {
           view.setTextViewText(R.id.gita_widget_quote, "hey soul sister");
           view.setViewVisibility(R.id.gita_widget_quote, View.VISIBLE);


       }
       if (ChapterSelect.equals(intent.getAction()))
       {
           Toast.makeText(context, "Chapter Select clicked. Select a chapter", Toast.LENGTH_SHORT).show();
           view.setViewVisibility(R.id.gita_widget_quote, View.INVISIBLE);
           //view.setViewVisibility(R.id.sv1, View.VISIBLE);

       }

        ComponentName componentName = new ComponentName(context, GitaWidgetProvider.class);
        AppWidgetManager.getInstance(context).updateAppWidget(componentName, view);
    }
}