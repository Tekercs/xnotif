package me.tekercs.xnotif.services;

import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import me.tekercs.xnotif.entities.NotificationMeta;

/**
 * Created by sebestyen on 18/12/16.
 */

public class NotificationCatchService extends NotificationListenerService
{
    @Override
    public void onNotificationPosted(StatusBarNotification sbn)
    {
        super.onNotificationPosted(sbn);

        NotificationMeta notificationMeta = this.getNotificationMetaFrom(sbn);
        System.out.println(notificationMeta.toString());
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
