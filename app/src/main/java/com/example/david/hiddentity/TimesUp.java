package com.example.david.hiddentity;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by david on 07/05/2018.
 */

public class TimesUp {

    int NUMERO_PERSONAJES = 30;
    Boolean redTurn;
    int redPoints;
    int bluePoints;

    ArrayList<String> personajes = new ArrayList<String>();
    Database db;

    public TimesUp(Database db){
        this.db = db;
        this.personajes = db.elegirPersonajes(NUMERO_PERSONAJES);
        redTurn = true;
        bluePoints = 0;
        redPoints = 0;
    }

}
