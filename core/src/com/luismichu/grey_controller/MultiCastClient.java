package com.luismichu.grey_controller;

import java.net.*;

public class MultiCastClient {
    public static String recibirIP(){
        String IP = "";
        try{
            MulticastSocket socket = new MulticastSocket(5556);

            socket.joinGroup(InetAddress.getByName("225.0.0.1"));

            byte [] ip = new byte [25];

            DatagramPacket dp = new DatagramPacket(ip, ip.length);
            socket.receive(dp);

            ip = dp.getData();
            IP = new String(ip).trim();
        }catch(Exception e){e.printStackTrace();}

        return IP;
    }
}
