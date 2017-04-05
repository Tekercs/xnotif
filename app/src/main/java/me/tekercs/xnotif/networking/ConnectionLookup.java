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
    private Queue<DesktopConnection> connections;
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

    public DesktopConnection getNextConnection()
    {
        return connections.remove();
    }

    public boolean isAnyConnection()
    {
        return connections.size() != 0;
    }

    private void registerConnection(InetAddress address)
    {
        try
        {
            DesktopConnection tempConnection = new DesktopConnection(address);
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
            observer.update();
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
            socket.setSoTimeout(ConnectionLookup.SOCKET_TIMEOUT);

            InetAddress address = InetAddress.getByName("255.255.255.255");

            byte[] message = "h".getBytes();
            DatagramPacket packet = new DatagramPacket(message, message.length, address, 14568);

            socket.send(packet);
            /**
             * TODO
             * put it to the queue
             * and wait for the next answer
             */

            try
            {
                message = new byte[1];
                packet = new DatagramPacket(message, message.length);
                InetAddress serverAddress;

                while (true)
                {
                    socket.receive(packet);

                    this.registerConnection(packet.getAddress());
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
