package com.luismichu.grey_controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

import java.io.PrintWriter;

public class Client extends Thread{
    private boolean alive;

    private Socket conectar(){
        String IP = MultiCastClient.recibirIP();

        SocketHints socketHints = new SocketHints();
        socketHints.connectTimeout = 4000;
        return Gdx.net.newClientSocket(Net.Protocol.TCP, IP, 5555, socketHints);
    }

    public void run(){
        Socket socket = conectar();
        Gdx.app.log("w", "Conectado");
        PrintWriter fsalida = new PrintWriter(socket.getOutputStream(), true);

        alive = true;
        while(alive){
            if(GreyController.toc) {
                fsalida.println((int)((float)GreyController.x / Gdx.graphics.getWidth()* 100));
                Gdx.app.log("w", String.valueOf(Gdx.graphics.getWidth()));
                Gdx.app.log("w", String.valueOf((int) ((float)GreyController.x / Gdx.graphics.getWidth()* 100)));
            }

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
