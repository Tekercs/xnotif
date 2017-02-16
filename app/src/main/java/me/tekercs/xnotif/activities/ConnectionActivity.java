package me.tekercs.xnotif.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.tekercs.xnotif.R;
import me.tekercs.xnotif.helpers.Observer;
import me.tekercs.xnotif.networking.ConnectionLookup;
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

        ConnectionLookup connectionLookup = new ConnectionLookup();
        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(connectionLookup);

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
