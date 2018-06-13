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
import android.widget.ListView;

import java.util.ArrayList;

public class CreateDeckActivity extends AppCompatActivity {

    Button addCharacter,terminarMazo;
    ListView personajesAdd;
    EditText mazoText,personajeText;

    ArrayList<String> personajes = new ArrayList<>();
    ArrayAdapter<String> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deck);

        addCharacter = findViewById(R.id.addCharacter);
        terminarMazo = findViewById(R.id.terminarMazo);
        personajesAdd = findViewById(R.id.listviewPersonajes);
        mazoText = findViewById(R.id.editTextNombrarMazo);
        personajeText = findViewById(R.id.editTextPersonaje);

        Intent intent = getIntent();
        final int grupo = intent.getIntExtra("GRUPO",0);
        String nombreMazo = intent.getStringExtra("MAZO");
        ArrayList<String> pjs = intent.getStringArrayListExtra("PERSONAJES");
        if(!nombreMazo.isEmpty()){
            mazoText.setText(nombreMazo);
        }
        if(!pjs.isEmpty()){
            personajes = pjs;
            datos = new ArrayAdapter<>(CreateDeckActivity.this, android.R.layout.simple_list_item_1, personajes);
            personajesAdd.setAdapter(datos);
        }

        final Database database = new Database(PrincipalActivity.db);

        addCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!personajeText.getText().toString().isEmpty()) {
                    personajes.add(personajeText.getText().toString());
                    datos = new ArrayAdapter<>(CreateDeckActivity.this, android.R.layout.simple_list_item_1, personajes);
                    personajesAdd.setAdapter(datos);
                    personajeText.setText("");
                }
            }
        });


        terminarMazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("asd", String.valueOf(personajes.size()));
                if(personajes.size()<30 && !mazoText.getText().toString().equals("")){

                    database.borrarPersonajesGrupo(grupo);
                    for(int i=0;i<personajes.size();i++){
                        database.meterPersonaje(personajes.get(i),grupo);
                    }

                    database.cambiarNombreMazo(mazoText.getText().toString(),grupo);

                    Intent menu = new Intent(CreateDeckActivity.this,PrincipalActivity.class);
                    startActivity(menu);

                }else{
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(CreateDeckActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(CreateDeckActivity.this);
                    }
                    builder.setTitle("Faltan datos:")
                            .setMessage("El mínimo de personajes a de ser 30 y el mazo debe tener nombre")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //cerramos dialogo
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            }
        });

        personajesAdd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                String pj = datos.getItem(i);
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(CreateDeckActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(CreateDeckActivity.this);
                }
                builder.setTitle("Borrar personaje")
                        .setMessage("¿Estas seguro que desea borrar a " + pj + "?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                personajes.remove(i);
                                datos = new ArrayAdapter<>(CreateDeckActivity.this, android.R.layout.simple_list_item_1, personajes);
                                personajesAdd.setAdapter(datos);
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



}
