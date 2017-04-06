package me.tekercs.xnotif.entities;

/**
 * Created by sebestyen on 19/12/16.
 */

public class NotificationMeta
{
    private String title;
    private String text;

    public NotificationMeta(String title, String text)
    {
        this.title = title;
        this.text = text;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getText()
    {
        return this.text;
    }

    @Override
    public String toString()
    {
        return "NotiMeta##" +
                "title=" + title + "##" +
                "text=" + text + "##" +
                "##";
    }
}
