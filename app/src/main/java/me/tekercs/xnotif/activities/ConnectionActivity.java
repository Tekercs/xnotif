package me.tekercs.xnotif.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

import me.tekercs.xnotif.R;
import me.tekercs.xnotif.networking.DesktopConnection;

public class ConnectionActivity extends AppCompatActivity
{

    private LinearLayout listHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        this.listHolder = (LinearLayout) findViewById(R.id.list_holder);

        System.out.println("Lookup activity started...");

        try {
            this.displayConnection(new DesktopConnection("barack", "127.0.0.1", 6000));
            this.displayConnection(new DesktopConnection("barack", "127.0.0.1", 6000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayConnection(DesktopConnection connection)
    {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView tempTextView = new TextView(this);
        tempTextView.setText(connection.getHostName());

        linearLayout.addView(tempTextView);
        this.listHolder.addView(linearLayout);
    }

}
