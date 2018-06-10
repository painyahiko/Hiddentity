package com.example.david.hiddentity;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PartidaActivity extends Activity {

    TextView tiempo,personaje,puntos;
    Button siguiente;
    RelativeLayout partidaLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        tiempo = findViewById(R.id.tiempo);
        personaje = findViewById(R.id.personaje);
        puntos = findViewById(R.id.puntos);
        siguiente = findViewById(R.id.btnsiguiente);
        partidaLayout = findViewById(R.id.partidaLayout);

        final TimesUp partida;

        if(this.getIntent().getParcelableExtra("partida") != null) {
            Intent intent = this.getIntent();
            partida = intent.getParcelableExtra("partida");

            if(partida.redTurn){
                puntos.setText("Puntos: " + partida.redPoints);
                partidaLayout.setBackgroundColor(getResources().getColor(R.color.teamRed));
            } else {
                puntos.setText("Puntos: " + partida.bluePoints);
                partidaLayout.setBackgroundColor(getResources().getColor(R.color.teamBlue));
            }
        }else{
            Database database = new Database(PrincipalActivity.db);

            ArrayList<String> personajes = new ArrayList<String>();
            personajes = database.elegirPersonajes(30);

             partida = new TimesUp(personajes);
        }

        personaje.setText(partida.personajes.get(0));

        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                tiempo.setText("" + (millisUntilFinished / 1000));
            }

            public void onFinish() {
                Intent intent = new Intent(PartidaActivity.this,PointmarkActivity.class);
                intent.putExtra("partida",partida);
                startActivity(intent);
            }
        }.start();

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(partida.redTurn) {
                    partida.redPoints++;
                    partida.personajesRojos.add(partida.personajes.get(0));
                    puntos.setText("Puntos: " + partida.redPoints);
                } else {
                    partida.bluePoints++;
                    partida.personajesAzules.add(partida.personajes.get(0));
                    puntos.setText("Puntos: " + partida.bluePoints);
                }
                partida.personajes.remove(partida.personajes.get(0));
                if(partida.personajes.isEmpty()){
                    Intent intent = new Intent(PartidaActivity.this,PointmarkActivity.class);
                    intent.putExtra("partida",partida);
                    startActivity(intent);
                }else {
                    personaje.setText(partida.personajes.get(0));
                }

            }
        });

    }
}
