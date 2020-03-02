package com.tylerlyczak.notificationdemo;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Gets the bundle from the intent passed to it
        Bundle bundle = intent.getExtras();
        String desc = bundle.getString("Description");

        // Builds the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "tester")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Reminding lol")
                .setContentText(desc)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        // Sets the notification to be made
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(202, builder.build());
    }
}
