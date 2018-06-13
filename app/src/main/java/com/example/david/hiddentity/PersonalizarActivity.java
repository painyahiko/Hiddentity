package com.example.david.hiddentity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class PersonalizarActivity extends AppCompatActivity {

    Button grupo1,grupo2,grupo3,grupo4;
    String nombreMazo = "";
    ArrayList<String> personajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar);

        grupo1 = findViewById(R.id.grupo1);
        grupo2 = findViewById(R.id.grupo2);
        grupo3 = findViewById(R.id.grupo3);
        grupo4 = findViewById(R.id.grupo4);

        final Database database = new Database(PrincipalActivity.db);
        ArrayList<String> mazos = new ArrayList<String>();
        mazos = database.nombresMazos();

        grupo1.setText(mazos.get(1));
        grupo2.setText(mazos.get(2));
        grupo3.setText(mazos.get(3));
        grupo4.setText(mazos.get(4));

        grupo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMazo = (String) grupo1.getText();
                if(nombreMazo.equalsIgnoreCase("crear mazo")){
                    personajes = new ArrayList<>();
                    Intent deck1 = new Intent(PersonalizarActivity.this,CreateDeckActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("GRUPO",2);
                    bundle.putString("MAZO", "");
                    bundle.putStringArrayList("PERSONAJES",personajes);
                    deck1.putExtras(bundle);
                    startActivity(deck1);
                } else {
                    personajes = database.getGrupo(2);
                    Intent deck1 = new Intent(PersonalizarActivity.this,CreateDeckActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("GRUPO",2);
                    bundle.putString("MAZO", nombreMazo);
                    bundle.putStringArrayList("PERSONAJES",personajes);
                    deck1.putExtras(bundle);
                    startActivity(deck1);
                }
            }
        });

        grupo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMazo = (String) grupo2.getText();
                if(nombreMazo.equalsIgnoreCase("crear mazo")){
                    personajes = new ArrayList<>();
                    Intent deck1 = new Intent(PersonalizarActivity.this,CreateDeckActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("GRUPO",3);
                    bundle.putString("MAZO", "");
                    bundle.putStringArrayList("PERSONAJES",personajes);
                    deck1.putExtras(bundle);
                    startActivity(deck1);
                } else {
                    personajes = database.getGrupo(3);
                    Intent deck1 = new Intent(PersonalizarActivity.this,CreateDeckActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("GRUPO",3);
                    bundle.putString("MAZO", nombreMazo);
                    bundle.putStringArrayList("PERSONAJES",personajes);
                    deck1.putExtras(bundle);
                    startActivity(deck1);
                }

            }
        });

        grupo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMazo = (String) grupo3.getText();
                if(nombreMazo.equalsIgnoreCase("crear mazo")){
                    personajes = new ArrayList<>();
                    Intent deck1 = new Intent(PersonalizarActivity.this,CreateDeckActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("GRUPO",4);
                    bundle.putString("MAZO", "");
                    bundle.putStringArrayList("PERSONAJES",personajes);
                    deck1.putExtras(bundle);
                    startActivity(deck1);
                } else {
                    personajes = database.getGrupo(4);
                    Intent deck1 = new Intent(PersonalizarActivity.this,CreateDeckActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("GRUPO",4);
                    bundle.putString("MAZO", nombreMazo);
                    bundle.putStringArrayList("PERSONAJES",personajes);
                    deck1.putExtras(bundle);
                    startActivity(deck1);
                }

            }
        });

        grupo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMazo = (String) grupo1.getText();
                if(nombreMazo.equalsIgnoreCase("crear mazo")){
                    personajes = new ArrayList<>();
                    Intent deck1 = new Intent(PersonalizarActivity.this,CreateDeckActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("GRUPO",5);
                    bundle.putString("MAZO", "");
                    bundle.putStringArrayList("PERSONAJES",personajes);
                    deck1.putExtras(bundle);
                    startActivity(deck1);
                } else {
                    personajes = database.getGrupo(5);
                    Intent deck1 = new Intent(PersonalizarActivity.this,CreateDeckActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("GRUPO",5);
                    bundle.putString("MAZO", nombreMazo);
                    bundle.putStringArrayList("PERSONAJES",personajes);
                    deck1.putExtras(bundle);
                    startActivity(deck1);
                }
            }
        });

        grupo1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                nombreMazo = (String) grupo1.getText();
                if(nombreMazo.equalsIgnoreCase("crear mazo")){
                    return true;
                }else {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(PersonalizarActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(PersonalizarActivity.this);
                    }
                    builder.setTitle("Borrar Mazo:")
                            .setMessage("Esta seguro que desea borrar el mazo " + grupo1.getText() + "?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    database.borrarPersonajesGrupo(2);
                                    database.cambiarNombreMazo("crear mazo",2);
                                    grupo1.setText("crear mazo");
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // cerramos alert
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return true;
                }
            }
        });

        grupo2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                nombreMazo = (String) grupo2.getText();
                if(nombreMazo.equalsIgnoreCase("crear mazo")){
                    return true;
                }else {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(PersonalizarActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(PersonalizarActivity.this);
                    }
                    builder.setTitle("Borrar Mazo:")
                            .setMessage("Esta seguro que desea borrar el mazo " + grupo2.getText() + "?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    database.borrarPersonajesGrupo(3);
                                    database.cambiarNombreMazo("crear mazo",3);
                                    grupo2.setText("crear mazo");
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // cerramos alert
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return true;
                }
            }
        });

        grupo3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                nombreMazo = (String) grupo3.getText();
                if(nombreMazo.equalsIgnoreCase("crear mazo")){
                    return true;
                }else {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(PersonalizarActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(PersonalizarActivity.this);
                    }
                    builder.setTitle("Borrar Mazo:")
                            .setMessage("Esta seguro que desea borrar el mazo " + grupo3.getText() + "?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    database.borrarPersonajesGrupo(4);
                                    database.cambiarNombreMazo("crear mazo",4);
                                    grupo3.setText("crear mazo");
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // cerramos alert
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return true;
                }
            }
        });

        grupo4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                nombreMazo = (String) grupo4.getText();
                if(nombreMazo.equalsIgnoreCase("crear mazo")){
                    return true;
                }else {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(PersonalizarActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(PersonalizarActivity.this);
                    }
                    builder.setTitle("Borrar Mazo:")
                            .setMessage("Esta seguro que desea borrar el mazo " + grupo4.getText() + "?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    database.borrarPersonajesGrupo(5);
                                    database.cambiarNombreMazo("crear mazo",5);
                                    grupo4.setText("crear mazo");
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // cerramos alert
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return true;
                }
            }
        });

    }
}
