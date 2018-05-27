package com.example.david.hiddentity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

 Button btn,btnPasar,btnTerminar;
 TextView texto,personaje;
 int posicion = 0;
 int puntuacion = 0;
    Database database;
 public static SQLiteDatabase db;
TimesUp timesUp;
 public static String[] nombres = {"pikachu","charmander","venusaur","charizard"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //revisar
        db = openOrCreateDatabase("Hiddentity", Context.MODE_PRIVATE,null);

        database = new Database(db);

        runOnUiThread(new Runnable(){
            public void run() {
                database.openOrCreate();
            }
        });


        timesUp = new TimesUp(database);

        String[] personajes = timesUp.personajes;

        Button btn = findViewById(R.id.btn1);
        Button pasar = findViewById(R.id.btnPasar);
        final TextView texto = findViewById(R.id.texto);
        final TextView personaje = findViewById(R.id.personaje);
        //personaje.setText(personajes[posicion]);

        btn.setOnClickListener(new View.OnClickListener() {
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
                posicion++;
                puntuacion++;
                personaje.setText(nombres[posicion]);


            }
        });

    }
}
