package com.example.david.hiddentity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Principal extends Activity {

    Button btn, pasar, terminar, jugar, reglas;
    Database database;
    TextView texto, personaje;
    TimesUp partida;
    int posicion = 0;
    int puntuacion = 0;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //revisar
        db = openOrCreateDatabase("Hiddentity", Context.MODE_PRIVATE, null);

        database = new Database(db);

        runOnUiThread(new Runnable() {
            public void run() {
                database.openOrCreate();
            }
        });
        final Button jugar = findViewById(R.id.btnJugar);
        final Button reglas = findViewById(R.id.btnReglas);

        partida = new TimesUp(database);

        //personaje.setText(personajes[posicion]);

        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        texto.setText("" + (millisUntilFinished / 1000));
                    }

                    public void onFinish() {
                        texto.setText("done!");
                    }
                }.start();
            }
        });

        pasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //personaje.setText(personajes.get(posicion));
                posicion++;
                puntuacion++;


            }
        });

        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, PointmarkActivity.class);
                startActivity(intent);
            }
        });*/

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        reglas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reglas = new Intent(getApplicationContext(),Reglas.class);
                startActivity(reglas);
            }
        });


    }
}
