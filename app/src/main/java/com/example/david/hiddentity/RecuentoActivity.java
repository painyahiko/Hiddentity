package com.example.david.hiddentity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class RecuentoActivity extends AppCompatActivity {

    TextView vencedor,puntosRojos,puntosAzules;
    ListView recuentoRojo,recuentoAzul;
    Button terminar;
    LinearLayout linearRecuento;
    ArrayAdapter<String> datosRojos;
    ArrayAdapter<String> datosAzules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View decorView = getWindow().getDecorView();
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
        setContentView(R.layout.activity_recuento);

        vencedor = findViewById(R.id.vencedor);
        puntosRojos = findViewById(R.id.puntosFinalesRojos);
        puntosAzules = findViewById(R.id.puntosFinalesAzules);
        recuentoRojo = findViewById(R.id.recuentoRojo);
        recuentoAzul = findViewById(R.id.recuentoAzul);
        terminar = findViewById(R.id.terminarRecuento);
        linearRecuento = findViewById(R.id.linearRecuento);

        Intent intent = this.getIntent();
        final TimesUp partida = intent.getParcelableExtra("partida");

        puntosRojos.setText(partida.redPoints + "");
        puntosAzules.setText(partida.bluePoints + "");

        if (partida.redPoints > partida.bluePoints) {
            vencedor.setText("Gana el equipo Rojo");
            vencedor.setBackgroundResource(R.color.teamRed);
        } else if (partida.redPoints < partida.bluePoints) {
            vencedor.setText("Gana el equipo Azul");
            vencedor.setBackgroundResource(R.color.teamBlue);

        } else {
            vencedor.setText("Empate");
            vencedor.setBackgroundResource(R.color.greenButton);
        }

        datosRojos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, partida.personajesRojos);
        datosAzules = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, partida.personajesAzules);
        recuentoRojo.setAdapter(datosRojos);
        recuentoAzul.setAdapter(datosAzules);

        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecuentoActivity.this, PrincipalActivity.class);
                startActivity(intent);
            }
        });

        linearRecuento.setOnClickListener(new View.OnClickListener() {
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

        recuentoRojo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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

        recuentoAzul.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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
        Intent intent = new Intent(RecuentoActivity.this,PrincipalActivity.class);
        startActivity(intent);
    }
}
