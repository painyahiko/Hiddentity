package com.example.david.hiddentity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PointmarkActivity extends AppCompatActivity {

    TextView rojoInfo,azulInfo;
    ListView listView;
    Button startGame;
    LinearLayout pointMarkLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointmark);

        rojoInfo = findViewById(R.id.rojoInfo);
        azulInfo = findViewById(R.id.azulInfo);
        listView = findViewById(R.id.listview);
        startGame = findViewById(R.id.nextTeam);
        pointMarkLayout = findViewById(R.id.pointMarkLayout);

        Intent intent = this.getIntent();
        final TimesUp partida = intent.getParcelableExtra("partida");
        ArrayAdapter<String> datos;
        if(partida.redTurn) {
            pointMarkLayout.setBackgroundColor(getResources().getColor(R.color.teamRed));
            datos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, partida.personajesRojos);
        } else{
            pointMarkLayout.setBackgroundColor(getResources().getColor(R.color.teamBlue));
            datos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, partida.personajesAzules);
        }

        listView.setAdapter(datos);
        rojoInfo.setText("El equipo rojo tiene " + partida.redPoints + " puntos");
        azulInfo.setText("El equipo azul tiene " + partida.bluePoints + " puntos");


        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(partida.redTurn){
                    partida.redTurn = false;
                } else {
                    partida.redTurn = true;
                }
                Intent intent = new Intent(PointmarkActivity.this,PartidaActivity.class);
                intent.putExtra("partida",partida);
                startActivity(intent);
            }
        });

    }


}
