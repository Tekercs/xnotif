package me.tekercs.xnotif.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.tekercs.xnotif.R;
import me.tekercs.xnotif.services.NotificationCatchService;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view)
    {
        startService(new Intent(this, NotificationCatchService.class));
    }

    public void requestPermission(View view)
    {
        Intent requestIntent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        requestIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(requestIntent);
    }

    public void fireDummyNotification(View view)
    {
        Notification.Builder notiBuilder = new Notification.Builder(this);
        notiBuilder.setContentTitle("Example notification!")
                   .setContentText("THis is a bit longer string, idk why but it has to be!")
                   .setSmallIcon(R.drawable.noti_icon);

        Notification noti = notiBuilder.build();

        NotificationManager notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notiManager.notify(00125, noti);
    }

    public void searchComputerConnection(View view)
    {
        /**
         * TODO
         * search computer connection
         * via UDP broadcasting!
         */
        System.out.println("searching...");

        startActivity(new Intent(this, ConnectionActivity.class));
    }
}
