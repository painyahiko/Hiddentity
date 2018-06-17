package com.example.david.hiddentity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ParametrosActivity extends AppCompatActivity {

    Button empezar,cancelar,miembroRojo,miembroAzul;
    Spinner mazos;
    int numeroMazo;
    EditText tiempo,cartas;
    ListView equipoRojo,equipoAzul;

    TimesUp partida;

    ArrayList<String> miembrosRojos = new ArrayList<>();
    ArrayAdapter<String> datosRojos;

    ArrayList<String> miembrosAzules = new ArrayList<>();
    ArrayAdapter<String> datosAzules;

    EditText nombreMiembro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametros);

        nombreMiembro = findViewById(R.id.dialogName);

        empezar = findViewById(R.id.empezarParametros);
        cancelar = findViewById(R.id.cancelarParametros);
        miembroRojo = findViewById(R.id.miembroRojo);
        miembroAzul = findViewById(R.id.miembroAzul);
        mazos = findViewById(R.id.snipper);
        tiempo = findViewById(R.id.editTiempo);
        cartas = findViewById(R.id.editCartas);
        equipoRojo = findViewById(R.id.listviewRedMember);
        equipoAzul = findViewById(R.id.listviewBlueMember);


        final Database database = new Database(PrincipalActivity.db);
        ArrayList<String> mazosDatabase;
        mazosDatabase = database.nombresMazos();

        final ArrayList<String> listamazos = new ArrayList<>();

        for(int i=0;i<mazosDatabase.size();i++){
            if(!mazosDatabase.get(i).equalsIgnoreCase("crear mazo")){
                listamazos.add(mazosDatabase.get(i));
            }
        }

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, listamazos);

        mazos.setAdapter(adaptador);


        mazos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numeroMazo = database.numeroGrupo(listamazos.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        miembroRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(ParametrosActivity.this,R.style.RedDialog);
                } else {
                    builder = new AlertDialog.Builder(ParametrosActivity.this);
                }
                // Get the layout inflater
                final LayoutInflater inflater = ParametrosActivity.this.getLayoutInflater();

                final View mView = inflater.inflate(R.layout.dialog_player, null);

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(mView)
                        // Add action buttons
                        .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                EditText nombreMiembro = mView.findViewById(R.id.dialogName);
                                miembrosRojos.add(nombreMiembro.getText().toString());
                                datosRojos = new ArrayAdapter<>(ParametrosActivity.this, android.R.layout.simple_list_item_1, miembrosRojos);
                                equipoRojo.setAdapter(datosRojos);

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        }).setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        miembroAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(ParametrosActivity.this,R.style.BlueDialog);
                } else {
                    builder = new AlertDialog.Builder(ParametrosActivity.this);
                }
                // Get the layout inflater
                final LayoutInflater inflater = ParametrosActivity.this.getLayoutInflater();

                final View mView = inflater.inflate(R.layout.dialog_player, null);

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(mView)
                        // Add action buttons
                        .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                EditText nombreMiembro = mView.findViewById(R.id.dialogName);
                                miembrosAzules.add(nombreMiembro.getText().toString());
                                datosAzules = new ArrayAdapter<>(ParametrosActivity.this, android.R.layout.simple_list_item_1, miembrosAzules);
                                equipoAzul.setAdapter(datosAzules);

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        }).setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        equipoRojo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                String pj = miembrosRojos.get(i);
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(ParametrosActivity.this,R.style.RedDialog);
                } else {
                    builder = new AlertDialog.Builder(ParametrosActivity.this);
                }
                builder.setTitle("Borrar personaje")
                        .setMessage("¿Estas seguro que desea borrar a " + pj + "?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                miembrosRojos.remove(i);
                                datosRojos = new ArrayAdapter<>(ParametrosActivity.this, android.R.layout.simple_list_item_1, miembrosRojos);
                                equipoRojo.setAdapter(datosRojos);
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

        equipoAzul.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                String pj = miembrosAzules.get(i);
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(ParametrosActivity.this, R.style.BlueDialog);
                } else {
                    builder = new AlertDialog.Builder(ParametrosActivity.this);
                }
                builder.setTitle("Borrar personaje")
                        .setMessage("¿Estas seguro que desea borrar a " + pj + "?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                miembrosAzules.remove(i);
                                datosAzules = new ArrayAdapter<>(ParametrosActivity.this, android.R.layout.simple_list_item_1, miembrosAzules);
                                equipoAzul.setAdapter(datosAzules);
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

        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cartas.getText().toString().equals("") && !tiempo.getText().toString().equals("") && !tiempo.getText().toString().equals("0") && !cartas.getText().toString().equals("0") ){
                    final int numeroCartas = Integer.parseInt(cartas.getText().toString());
                    final int tiempoPartida = Integer.parseInt(tiempo.getText().toString());

                    final int pjDisponibles = database.numeroPersonajes(numeroMazo);

                    if (database.numeroPersonajes(numeroMazo) < numeroCartas) {
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

                                        ArrayList<String> personajes;
                                        personajes = database.elegirPersonajes(numeroCartas, numeroMazo);
                                        partida = new TimesUp(personajes, tiempoPartida);
                                        partida.jugadoresRojos = miembrosRojos;
                                        partida.jugadoresAzules = miembrosAzules;
                                        Intent intent = new Intent(ParametrosActivity.this, PartidaActivity.class);
                                        intent.putExtra("partida", partida);
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
                    } else {
                        ArrayList<String> personajes;
                        personajes = database.elegirPersonajes(numeroCartas, numeroMazo);
                        partida = new TimesUp(personajes, tiempoPartida);
                        partida.jugadoresRojos = miembrosRojos;
                        partida.jugadoresAzules = miembrosAzules;
                        Intent intent = new Intent(ParametrosActivity.this, PartidaActivity.class);
                        intent.putExtra("partida", partida);
                        startActivity(intent);
                    }

                } else {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(ParametrosActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(ParametrosActivity.this);
                    }
                    builder.setTitle("Datos insuficientes")
                            .setMessage("Los campos tiempo y número de cartas no pueden estar vacios")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }


            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParametrosActivity.this, PrincipalActivity.class);
                startActivity(intent);
            }
        });
    }


}
