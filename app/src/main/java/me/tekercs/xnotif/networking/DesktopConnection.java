package me.tekercs.xnotif.networking;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;


/**
 * Created by sebestyen on 10/01/17.
 */

public class DesktopConnection
{
    private Socket socket;
    private InetAddress address;
    public static final int DEFAULT_PORT = 14567;

    public DesktopConnection(InetAddress address) throws IOException
    {
        this.address = address;
    }

    public String getHostName()
    {
        return this.address.getHostName();
    }

    public String getIpAddress()
    {
        return this.address.getHostAddress();
    }

    public InetAddress getAddress()
    {
        return address;
    }

    public boolean isAlive()
    {
        return this.socket != null && this.socket.isConnected();
    }

    public DesktopConnection connect() throws IOException
    {
        this.socket = new Socket(this.address, DesktopConnection.DEFAULT_PORT);

        return this;
    }

    public DesktopConnection send(String message) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        writer.write(message);
        writer.close();

        return this;
    }

    public void disconnect() throws IOException
    {
        this.socket.close();
    }
}
