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
import java.util.Collections;

public class PartidaActivity extends Activity {

    TextView tiempo,personaje,puntos;
    Button siguiente;
    RelativeLayout partidaLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_partida);


        tiempo = findViewById(R.id.tiempo);
        personaje = findViewById(R.id.personaje);
        puntos = findViewById(R.id.puntos);
        siguiente = findViewById(R.id.btnsiguiente);
        partidaLayout = findViewById(R.id.partidaLayout);

        final TimesUp partida;

            Intent intent = this.getIntent();
            partida = intent.getParcelableExtra("partida");

            if(partida.redTurn){
                puntos.setText("Puntos: " + partida.redPoints);
                partidaLayout.setBackgroundColor(getResources().getColor(R.color.teamRed));
            } else {
                puntos.setText("Puntos: " + partida.bluePoints);
                partidaLayout.setBackgroundColor(getResources().getColor(R.color.teamBlue));
            }

        Collections.shuffle(partida.personajes);

        personaje.setText(partida.personajes.get(0));
        final CountDownTimer countDownTimer = new CountDownTimer(partida.tiempo, 1000) {

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
                    countDownTimer.cancel();
                    startActivity(intent);
                }else {
                    personaje.setText(partida.personajes.get(0));
                }

            }
        });

    }

    @Override
    public void onBackPressed() {

    }

}
