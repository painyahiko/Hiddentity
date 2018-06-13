package com.example.david.hiddentity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class ParametrosActivity extends AppCompatActivity {

    Button empezar,cancelar;
    Spinner mazos;
    Integer numeroMazo;
    EditText tiempo,cartas;

    TimesUp partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametros);

        empezar = findViewById(R.id.empezarParametros);
        cancelar = findViewById(R.id.cancelarParametros);
        mazos = findViewById(R.id.snipper);
        tiempo = findViewById(R.id.editTiempo);
        cartas = findViewById(R.id.editCartas);


        final Database database = new Database(PrincipalActivity.db);
        ArrayList<String> mazosDatabase = new ArrayList<String>();
        mazosDatabase = database.nombresMazos();

        ArrayList<String> listamazos = new ArrayList<String>();

        for(int i=0;i<mazosDatabase.size();i++){
            if(!mazosDatabase.get(i).equalsIgnoreCase("crear mazo")){
                listamazos.add(mazosDatabase.get(i));
            }
        }

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, listamazos);

        mazos.setAdapter(adaptador);

        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> personajes = new ArrayList<String>();
                int numeroCartas = Integer.parseInt(cartas.getText().toString());
                int tiempoPartida = Integer.parseInt(tiempo.getText().toString());
                personajes = database.elegirPersonajes(numeroCartas,numeroMazo);
                partida = new TimesUp(personajes,tiempoPartida);
                Log.e("cartas y tiempo",personajes.size() + " " + partida.tiempo);

                Intent intent = new Intent(ParametrosActivity.this,PartidaActivity.class);
                intent.putExtra("partida",partida);
                startActivity(intent);

            }
        });

        mazos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numeroMazo = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}
