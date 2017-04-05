package me.tekercs.xnotif.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.tekercs.xnotif.helpers.Observer;

/**
 * Created by sebestyen on 27/01/17.
 */

public class ConnectionLookup implements Runnable
{
    private List<Observer> observers;
    private Queue<InetAddress> connections;
    private static final int SOCKET_TIMEOUT = 180000;

    public ConnectionLookup()
    {
        this.observers = new ArrayList<>();
        this.connections = new LinkedList<>();
    }

    public void addObserver(Observer observer)
    {
        this.observers.add(observer);
    }

    public InetAddress getNextConnection()
    {
        return connections.remove();
    }

    public boolean isAnyConnection()
    {
        return connections.size() != 0;
    }

    private void signifyObservers()
    {
        for (Observer observer : this.observers)
            observer.update();
    }

    @Override
    public void run()
    {

        try
        {
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(ConnectionLookup.SOCKET_TIMEOUT);

            InetAddress address = InetAddress.getByName("255.255.255.255");

            byte[] message = "h".getBytes();
            DatagramPacket packet = new DatagramPacket(message, message.length, address, 14568);

            socket.send(packet);

            message = new byte[1];
            packet = new DatagramPacket(message, message.length);
            try
            {

                while (true)
                {
                    socket.receive(packet);

                    this.connections.add(packet.getAddress());
                    this.signifyObservers();
                }
            }
            catch (SocketTimeoutException e)
            {
                System.out.println("Timeout expired: stop waiting");
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
