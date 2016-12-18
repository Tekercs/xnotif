package me.tekercs.xnotif.services;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

/**
 * Created by sebestyen on 18/12/16.
 */

public class NotificationCatchService extends NotificationListenerService
{
    public NotificationCatchService()
    {
        System.out.println("Service started");
    }

    @Override
    public void onListenerConnected()
    {
        super.onListenerConnected();
        System.out.println("Listener connected to NotificationManager!");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn)
    {
        super.onNotificationPosted(sbn);
        System.out.println("Notification posted lel !!!");
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn)
    {
        super.onNotificationRemoved(sbn);
        System.out.println("Notification removed lel !!!");
    }
}
