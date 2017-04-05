package me.tekercs.xnotif.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.tekercs.xnotif.R;
import me.tekercs.xnotif.helpers.Observer;
import me.tekercs.xnotif.networking.ConnectionLookup;
import me.tekercs.xnotif.networking.DesktopConnection;

public class ConnectionActivity extends AppCompatActivity implements Observer
{

    private LinearLayout listHolder;
    private ConnectionLookup connectionLookup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        this.listHolder = (LinearLayout) findViewById(R.id.list_holder);
        this.connectionLookup = new ConnectionLookup();
        this.connectionLookup.addObserver(this);
        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(connectionLookup);
    }

    private void displayConnection(DesktopConnection connection)
    {
        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView tempTextView = new TextView(this);
        tempTextView.setText(connection.getHostName());

        Button tempButton = new Button(this);
        tempButton.setText("Connect");

        linearLayout.addView(tempTextView);
        linearLayout.addView(tempButton);

        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                listHolder.addView(linearLayout);
            }
        });

    }

    @Override
    public void update()
    {
        /**
         * TODO
         * Get the desktop Connection form the
         * ConnectionLookip queue
         * then displayConnection it
         */
        System.out.println("igen ittvagyunk");
        this.displayConnection(this.connectionLookup.getNextConnection());
    }
}
