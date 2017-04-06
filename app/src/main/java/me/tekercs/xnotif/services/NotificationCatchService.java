package me.tekercs.xnotif.services;

import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import java.io.IOException;

import me.tekercs.xnotif.entities.NotificationMeta;
import me.tekercs.xnotif.networking.DesktopConnection;

/**
 * Created by sebestyen on 18/12/16.
 */

public class NotificationCatchService extends NotificationListenerService
{
    @Override
    public void onNotificationPosted(StatusBarNotification sbn)
    {
        super.onNotificationPosted(sbn);

        if (!DesktopConnection.INSTANCE.isAlive())
        {
            try
            {
                DesktopConnection.INSTANCE.connect();
            }
            catch (IOException e)
            {
                System.out.println("ezt dobja");
                //e.printStackTrace();
            }
        }

        NotificationMeta notificationMeta = this.getNotificationMetaFrom(sbn);
        try
        {
            System.out.println(DesktopConnection.INSTANCE.isAlive());
            DesktopConnection.INSTANCE.send(notificationMeta.toString());
        }
        catch (IOException e)
        {
            System.out.println(DesktopConnection.INSTANCE.isAlive());
            System.out.println("Bejon mert nincs connection");
            //e.printStackTrace();
        }
    }

    private NotificationMeta getNotificationMetaFrom(StatusBarNotification sbn)
    {
        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.title");
        String text = extras.getCharSequence("android.text").toString();

        return new NotificationMeta(title, text);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn)
    {
        super.onNotificationRemoved(sbn);
    }
}
