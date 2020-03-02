package com.tylerlyczak.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private void createNotificationChannel()    {
        // Checks if the user is using Android Oreo or above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Makes the name and description of the Notification Channel
            CharSequence name = "TesterChannel";
            String description = "Channel for the tester";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("tester", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void onClickButton (View view)   {
        // Makes a bundle to send information to the notification
        Bundle bundle = new Bundle();
        bundle.putString("Description", "Added a description");

        Intent intent = new Intent(MainActivity.this, ReminderBroadcast.class);
        intent.putExtras(bundle);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();
        long timeForNotification = 1000*10;

        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick+timeForNotification, pendingIntent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();
    }
}
