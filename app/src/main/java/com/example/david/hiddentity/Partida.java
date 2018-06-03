package com.example.david.hiddentity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by david on 03/06/2018.
 */

public class Partida {
    private static final Partida ourInstance = new Partida();
    int NUMERO_PERSONAJES = 30;
    Boolean redTurn;
    int redPoints;
    int bluePoints;
    Context context;

    ArrayList<String> personajes = new ArrayList<String>();
    private static SQLiteDatabase db;
    Database database;

    public Partida() {


    }

    public static Partida getInstance() {
        return ourInstance;
    }


}
