package com.luismichu.grey_controller;

import com.badlogic.gdx.Gdx;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FBConexion extends Thread{
    public void run(){
        FirebaseDatabase db = null;
        try {
            db = conectar();
        } catch (IOException e) { e.printStackTrace(); }

        assert db != null : "Null Database";
        DatabaseReference ref = db.getReference("/w");
        DatabaseReference refToc = db.getReference("/toc");
        Gdx.app.log("ENVIADO", "Listo para enviar");
        while (true){
            refToc.setValueAsync(GreyController.toc);
            if(GreyController.toc){
                ref.setValueAsync((int)((float)GreyController.x / Gdx.graphics.getWidth()* 100));

            }

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public FirebaseDatabase conectar() throws IOException {
        Gdx.app.log("ENVIADO", "Listo para enviar0");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(Gdx.files.internal("key.json").read()))
                .setDatabaseUrl("https://test-46259.firebaseio.com/")
                .build();

        FirebaseApp defaultApp = FirebaseApp.initializeApp(options);

        return FirebaseDatabase.getInstance(defaultApp);
    }
}
