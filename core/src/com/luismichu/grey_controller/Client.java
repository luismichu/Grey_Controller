package com.luismichu.grey_controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

import java.io.PrintWriter;

public class Client extends Thread{
    private boolean alive;

    private Socket conectar(){
        SocketHints socketHints = new SocketHints();
        socketHints.connectTimeout = 4000;
        return Gdx.net.newClientSocket(Net.Protocol.TCP, "192.168.1.106", 5555, socketHints);
    }

    public void run(){
        Gdx.app.log("log", "Hilo iniciado");
        Socket socket = conectar();
        Gdx.app.log("log", "Conectado a 106");

        PrintWriter fsalida = new PrintWriter(socket.getOutputStream(), true);

        alive = true;
        while(alive){
            if(GreyController.toc)
                fsalida.println(GreyController.x);

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void dispose(){
        alive = false;
    }
}
