package com.example.david.hiddentity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReglasActivity extends Activity {

    Button crear,parametros,jugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglas);

        crear = findViewById(R.id.video1);
        parametros = findViewById(R.id.video2);
        jugar = findViewById(R.id.video3);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReglasActivity.this,VideoReglasActivity.class);
                intent.putExtra("video","crear");
                startActivity(intent);
            }
        });

        parametros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReglasActivity.this,VideoReglasActivity.class);
                intent.putExtra("video","parametros");
                startActivity(intent);
            }
        });

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReglasActivity.this,VideoReglasActivity.class);
                intent.putExtra("video","jugar");
                startActivity(intent);
            }
        });
    }
}
