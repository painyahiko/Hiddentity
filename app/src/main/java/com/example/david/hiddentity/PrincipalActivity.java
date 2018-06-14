package com.example.david.hiddentity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends Activity {

    Button jugar, reglas, personalizar;
    Database database;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //revisar
        db = openOrCreateDatabase("Hiddentity", Context.MODE_PRIVATE, null);

        database = new Database(db);

        runOnUiThread(new Runnable() {
            public void run() {
                database.openOrCreate();
            }
        });
        jugar = findViewById(R.id.btnJugar);
        reglas = findViewById(R.id.btnReglas);
        personalizar = findViewById(R.id.btnMazos);


        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent partida = new Intent(PrincipalActivity.this,ParametrosActivity.class);
                startActivity(partida);
            }
        });

        reglas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reglas = new Intent(PrincipalActivity.this,ReglasActivity.class);
                startActivity(reglas);
            }
        });

        personalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent personalizar = new Intent(PrincipalActivity.this,PersonalizarActivity.class);
                startActivity(personalizar);
            }
        });


    }
}
