package com.example.david.hiddentity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class RecuentoActivity extends AppCompatActivity {

    TextView vencedor,puntosRojos,puntosAzules;
    ListView recuentoRojo,recuentoAzul;
    Button terminar;

    ArrayAdapter<String> datosRojos;
    ArrayAdapter<String> datosAzules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuento);

        vencedor = findViewById(R.id.vencedor);
        puntosRojos = findViewById(R.id.puntosFinalesRojos);
        puntosAzules = findViewById(R.id.puntosFinalesAzules);
        recuentoRojo = findViewById(R.id.recuentoRojo);
        recuentoAzul = findViewById(R.id.recuentoAzul);
        terminar = findViewById(R.id.terminarRecuento);

        Intent intent = this.getIntent();
        final TimesUp partida = intent.getParcelableExtra("partida");

        puntosRojos.setText(partida.redPoints + "");
        puntosAzules.setText(partida.bluePoints + "");

        if(partida.redPoints > partida.bluePoints){
            vencedor.setText("Gana el equipo Rojo");
            vencedor.setBackgroundResource(R.color.teamRed);
        }else if(partida.redPoints < partida.bluePoints){
            vencedor.setText("Gana el equipo Azul");
            vencedor.setBackgroundResource(R.color.teamBlue);

        } else{
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
                Intent intent = new Intent(RecuentoActivity.this,PrincipalActivity.class);
                startActivity(intent);
            }
        });

    }
}
