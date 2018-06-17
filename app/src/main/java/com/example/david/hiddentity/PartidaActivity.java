package com.example.david.hiddentity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;

public class PartidaActivity extends Activity {

    TextView tiempo,personaje,puntosRojos,puntosAzules;
    Button siguiente;
    RelativeLayout partidaLayout;
    View decorView;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        decorView = getWindow().getDecorView();
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
        puntosRojos = findViewById(R.id.rojoInfoPartida);
        puntosAzules = findViewById(R.id.azulInfoPartida);
        siguiente = findViewById(R.id.btnsiguiente);
        partidaLayout = findViewById(R.id.partidaLayout);

        final TimesUp partida;

            Intent intent = this.getIntent();
            partida = intent.getParcelableExtra("partida");

            if(partida.redTurn){
                partidaLayout.setBackgroundColor(getResources().getColor(R.color.teamRed));
            } else {
                partidaLayout.setBackgroundColor(getResources().getColor(R.color.teamBlue));
            }

        puntosRojos.setText(partida.redPoints + "");
        puntosAzules.setText(partida.bluePoints + "");

        Collections.shuffle(partida.personajes);

        personaje.setText(partida.personajes.get(0));
        countDownTimer = new CountDownTimer(partida.tiempo, 1000) {

            public void onTick(long millisUntilFinished) {
                tiempo.setText("" + (millisUntilFinished / 1000));
            }

            public void onFinish() {

                MediaPlayer mp = MediaPlayer.create(PartidaActivity.this,R.raw.final_turno);
                mp.start();
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
                    puntosRojos.setText(partida.redPoints + "");
                    partida.personajes.remove(partida.personajes.get(0));
                } else {
                    partida.bluePoints++;
                    partida.personajesAzules.add(partida.personajes.get(0));
                    puntosAzules.setText(partida.bluePoints + "");
                    partida.personajes.remove(partida.personajes.get(0));
                }
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

        partidaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(PartidaActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(PartidaActivity.this);
        }
        builder.setTitle("Volver al menu")
                .setMessage("¿Estas seguro que desea volver al menú?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        countDownTimer.cancel();
                        Intent intent = new Intent(PartidaActivity.this,PrincipalActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // cerramos alert
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
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

}
