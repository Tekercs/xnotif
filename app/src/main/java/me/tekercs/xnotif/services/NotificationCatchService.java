package me.tekercs.xnotif.services;

import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

/**
 * Created by sebestyen on 18/12/16.
 */

public class NotificationCatchService extends NotificationListenerService
{
    @Override
    public void onNotificationPosted(StatusBarNotification sbn)
    {
        super.onNotificationPosted(sbn);

        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.title");
        String text = extras.getCharSequence("android.text").toString();

        System.out.println(title + "/" + text);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn)
    {
        super.onNotificationRemoved(sbn);
    }
}
