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
    private String hostName;
    private InetAddress address;
    private int port;

    public DesktopConnection(String hostName, InetAddress address, int port) throws IOException
    {
        this.address = address;
        this.port = port;
        this.hostName = hostName;
    }

    public String getHostName()
    {
        return hostName;
    }

    public InetAddress getAddress()
    {
        return address;
    }

    public int getPort()
    {
        return port;
    }

    public boolean isAlive()
    {
        return this.socket != null && this.socket.isConnected();
    }

    public DesktopConnection connect() throws IOException
    {
        this.socket = new Socket(this.address, this.port);

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
