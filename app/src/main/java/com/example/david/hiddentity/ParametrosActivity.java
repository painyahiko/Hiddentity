package com.example.david.hiddentity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
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
    int numeroMazo;
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

        final ArrayList<String> listamazos = new ArrayList<String>();

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

                final int pjDisponibles = database.numeroPersonajes(numeroMazo);
                final int numeroCartas = Integer.parseInt(cartas.getText().toString());

                if(database.numeroPersonajes(numeroMazo)<numeroCartas){
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(ParametrosActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(ParametrosActivity.this);
                    }
                    builder.setTitle("No dispones de tantos personajes")
                            .setMessage("Si acepta jugara con " + pjDisponibles + " personajes (máximo disponible)")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    ArrayList<String> personajes = new ArrayList<String>();
                                    int tiempoPartida = Integer.parseInt(tiempo.getText().toString());
                                    personajes = database.elegirPersonajes(numeroCartas,numeroMazo);
                                    partida = new TimesUp(personajes,tiempoPartida);
                                    Intent intent = new Intent(ParametrosActivity.this,PartidaActivity.class);
                                    intent.putExtra("partida",partida);
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
                }else{
                    ArrayList<String> personajes = new ArrayList<String>();
                    int tiempoPartida = Integer.parseInt(tiempo.getText().toString());
                    personajes = database.elegirPersonajes(numeroCartas,numeroMazo);
                    partida = new TimesUp(personajes,tiempoPartida);
                    Intent intent = new Intent(ParametrosActivity.this,PartidaActivity.class);
                    intent.putExtra("partida",partida);
                    startActivity(intent);
                }


            }
        });

        mazos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numeroMazo = database.numeroGrupo(listamazos.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}
