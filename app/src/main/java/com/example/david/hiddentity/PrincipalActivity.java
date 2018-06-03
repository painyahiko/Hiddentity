package com.example.david.hiddentity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrincipalActivity extends Activity {

    Button jugar, reglas;
    Database database;
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
        jugar = findViewById(R.id.btnJugar);
        reglas = findViewById(R.id.btnReglas);

       // partida = new TimesUp(database);

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
                Intent intent = new Intent(PrincipalActivity.this, PointmarkActivity.class);
                startActivity(intent);
            }
        });*/

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent partida = new Intent(PrincipalActivity.this,PartidaActivity.class);
                startActivity(partida);

            }
        });

        reglas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reglas = new Intent(PrincipalActivity.this,ReglasActivity.class);
                startActivity(reglas);
            }
        });


    }
}
