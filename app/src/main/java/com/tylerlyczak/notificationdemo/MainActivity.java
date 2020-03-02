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

            // Sets the importance of the Notification channel
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            // Makes the notification channel
            NotificationChannel channel = new NotificationChannel("tester", name, importance);
            channel.setDescription(description);

            // Creates it with the setting from above
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void onClickButton (View view)   {
        // Makes a bundle to send information to the notification
        Bundle bundle = new Bundle();
        bundle.putString("Description", "Added a description");

        // Makes an intent for the custom broadcaster class
        Intent intent = new Intent(MainActivity.this, ReminderBroadcast.class);
        intent.putExtras(bundle);

        // Makes a pending intent with the intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        // Gets the alarm manager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // These two longs dictate the time for the notification
        // only need to change timeForNotification, it is set to 10 seconds
        long timeAtButtonClick = System.currentTimeMillis();
        long timeForNotification = 1000*10;

        // Sets the alarm manager to call the intent after a certain time
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick+timeForNotification, pendingIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();
    }
}
