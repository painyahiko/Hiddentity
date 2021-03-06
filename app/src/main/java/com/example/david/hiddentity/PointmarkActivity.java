package com.example.david.hiddentity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class PointmarkActivity extends AppCompatActivity {

    TextView rojoInfo,azulInfo,resumen;
    ListView listView;
    Button startGame,salir;
    LinearLayout pointMarkLayout;

    ArrayAdapter<String> datos;

    View decorView;

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
        setContentView(R.layout.activity_pointmark);

        rojoInfo = findViewById(R.id.rojoInfo);
        azulInfo = findViewById(R.id.azulInfo);
        resumen = findViewById(R.id.resumen);
        listView = findViewById(R.id.listview);
        startGame = findViewById(R.id.nextTeam);
        salir = findViewById(R.id.salir);
        pointMarkLayout = findViewById(R.id.pointMarkLayout);

        Intent intent = this.getIntent();
        final TimesUp partida = intent.getParcelableExtra("partida");
        if(partida.redTurn) {
            if(!partida.jugadoresAzules.isEmpty()) {
                String auxPersonaje = partida.jugadoresAzules.get(0);
                partida.jugadoresAzules.remove(0);
                partida.jugadoresAzules.add(auxPersonaje);
                startGame.setText("Siguiente turno: " + partida.jugadoresAzules.get(0));
            }else{
                startGame.setText("Siguiente turno: Equipo Azul");
            }
            resumen.setText("Resumen Equipo Rojo");
            pointMarkLayout.setBackgroundColor(getResources().getColor(R.color.teamRed));
            datos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, partida.personajesRojos);
        } else{
            if(!partida.jugadoresRojos.isEmpty()) {
                String auxPersonaje = partida.jugadoresRojos.get(0);
                partida.jugadoresRojos.remove(0);
                partida.jugadoresRojos.add(auxPersonaje);
                startGame.setText("Siguiente turno: " + partida.jugadoresRojos.get(0));
            }else{
                startGame.setText("Siguiente turno: Equipo Rojo");
            }
            resumen.setText("Resumen Equipo Azul");
            pointMarkLayout.setBackgroundColor(getResources().getColor(R.color.teamBlue));
            datos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, partida.personajesAzules);
        }

        if(partida.personajes.isEmpty()){
            startGame.setText("Partida terminada");
        }

        listView.setAdapter(datos);
        rojoInfo.setText(partida.redPoints + "");
        azulInfo.setText(partida.bluePoints + "");


        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(partida.redTurn){
                    partida.redTurn = false;
                } else {
                    partida.redTurn = true;
                }
                if(partida.personajes.isEmpty()){
                    Intent intent = new Intent(PointmarkActivity.this,RecuentoActivity.class);
                    intent.putExtra("partida",partida);
                    startActivity(intent);
                } else{
                    Intent intent = new Intent(PointmarkActivity.this,PartidaActivity.class);
                    intent.putExtra("partida",partida);
                    startActivity(intent);
                }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                String pj;

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(PointmarkActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(PointmarkActivity.this);
                }
                if(partida.redTurn){
                     pj = partida.personajesRojos.get(i);
                    builder.setTitle("Borrar personaje")
                            .setMessage("¿Estas seguro que desea borrar a " + pj + " del equipo rojo y devolverlo a partida?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    partida.personajes.add(partida.personajesRojos.get(i));
                                    partida.personajesRojos.remove(i);
                                    partida.redPoints--;
                                    startGame.setText("Siguiente turno: " + partida.jugadoresAzules.get(0));
                                    rojoInfo.setText(partida.redPoints + "");
                                    datos = new ArrayAdapter<>(PointmarkActivity.this, android.R.layout.simple_list_item_1, partida.personajesRojos);
                                    listView.setAdapter(datos);
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
                } else {
                     pj = partida.personajesAzules.get(i);
                    builder.setTitle("Borrar personaje")
                            .setMessage("¿Estas seguro que desea eliminar a " + pj + " del equipo azul y devolverlo a partida?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    partida.personajes.add(partida.personajesAzules.get(i));
                                    partida.personajesAzules.remove(i);
                                    partida.bluePoints--;
                                    startGame.setText("Siguiente turno: " + partida.jugadoresRojos.get(0));
                                    azulInfo.setText(partida.bluePoints + "");
                                    datos = new ArrayAdapter<>(PointmarkActivity.this, android.R.layout.simple_list_item_1, partida.personajesAzules);
                                    listView.setAdapter(datos);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // cerramos alert
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });

        pointMarkLayout.setOnClickListener(new View.OnClickListener() {
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

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(PointmarkActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(PointmarkActivity.this);
                }
                builder.setTitle("Terminar la partida")
                        .setMessage("¿Estas seguro que desea terminar la partida?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {Intent intent = new Intent(PointmarkActivity.this,PrincipalActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // cerramos alert
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(PointmarkActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(PointmarkActivity.this);
        }
        builder.setTitle("Volver al menu")
                .setMessage("¿Estas seguro que desea volver al menú?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(PointmarkActivity.this,PrincipalActivity.class);
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
