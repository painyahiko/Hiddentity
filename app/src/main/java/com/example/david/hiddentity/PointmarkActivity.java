package com.example.david.hiddentity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PointmarkActivity extends AppCompatActivity {

    ListView listView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointmark);

        listView = findViewById(R.id.listview);
        ArrayList<String> lista = new ArrayList<String>();

        for(int i = 0;i<30;i++){
            lista.add("personaje " + i);
        }

        ArrayAdapter<String> datos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);

        listView.setAdapter(datos);

    }


}
