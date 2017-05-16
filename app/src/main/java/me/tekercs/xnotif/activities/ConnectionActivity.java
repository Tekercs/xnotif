package me.tekercs.xnotif.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.crypto.spec.DESKeySpec;

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

    private void displayConnection(InetAddress connection)
    {
        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        Button tempButton = new Button(this);
        tempButton.setText(connection.getHostName());

        tempButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Button button = (Button) v;
                String tempAddress = (String) button.getText();

                try
                {
                    InetAddress address = InetAddress.getByName(tempAddress);
                    DesktopConnection.INSTANCE.setAddress(address);
                }
                catch (UnknownHostException e)
                {
                    e.printStackTrace();
                }
            }
        });

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
        this.displayConnection(this.connectionLookup.getNextConnection());
    }
}
