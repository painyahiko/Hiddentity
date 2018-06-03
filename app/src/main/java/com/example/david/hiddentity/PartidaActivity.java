package com.example.david.hiddentity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PartidaActivity extends AppCompatActivity {

    TextView tiempo,personaje;
    Button siguiente;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        tiempo = findViewById(R.id.tiempo);
        personaje = findViewById(R.id.personaje);
        siguiente = findViewById(R.id.btnsiguiente);

        Database database = new Database(PrincipalActivity.db);

        ArrayList<String> personajes = new ArrayList<String>();
        personajes = database.elegirPersonajes(30);

        final TimesUp partida = new TimesUp(personajes);

        personaje.setText(partida.personajes.get(position));

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                tiempo.setText("" + (millisUntilFinished / 1000));
            }

            public void onFinish() {
                Log.e("redpoint", String.valueOf(partida.redPoints));
                Intent intent = new Intent(PartidaActivity.this,PointmarkActivity.class);
                intent.putExtra("partida",partida);
                startActivity(intent);
            }
        }.start();

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                partida.redPoints++;
                partida.personajesRojos.add(partida.personajes.get(position));
                //partida.personajes.remove(partida.personajes.get(position));
                position++;
                personaje.setText(partida.personajes.get(position));


            }
        });

    }

    public void onClick() {
        position++;
       // personaje.setText(partida.personajes.get(position));
       // partida.redPoints++;

    }
}
