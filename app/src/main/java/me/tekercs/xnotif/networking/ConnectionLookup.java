package me.tekercs.xnotif.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;

import me.tekercs.xnotif.activities.ConnectionActivity;
import me.tekercs.xnotif.helpers.Observer;

/**
 * Created by sebestyen on 27/01/17.
 */

public class ConnectionLookup implements Runnable
{
    private List<Observer> observers;
    private Queue<DesktopConnection> connections;

    public ConnectionLookup()
    {
        this.observers = new ArrayList<>();
        this.connections = new LinkedList<>();
    }

    public void addObserver(Observer observer)
    {
        this.observers.add(observer);
    }

    public DesktopConnection getNextConnection()
    {
        return connections.remove();
    }

    public boolean isAnyConnection()
    {
        return connections.size() != 0;
    }

    private void registerConnection(String hostName, String address, int port)
    {
        try
        {
            DesktopConnection tempConnection = new DesktopConnection(hostName, address, port);
            this.connections.add(tempConnection);

            this.signifyObservers();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void signifyObservers()
    {
        for (Observer observer : this.observers)
            observer.signify();
    }

    @Override
    public void run()
    {
        // ide kell a networking reszt implementalni
        // kikuldi a szorast majd halgat a valasozkra
        // ha jon valasz akkor meghivjra ra a registerConnectiont

        try
        {
            DatagramSocket socket = new DatagramSocket();

            InetAddress address = InetAddress.getByName("255.255.255.255");

            byte[] message = "help".getBytes();
            DatagramPacket packet = new DatagramPacket(message, message.length, address, 14567);

            socket.send(packet);
            System.out.println("sent");

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
