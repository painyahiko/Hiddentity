package com.example.david.hiddentity;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by david on 07/05/2018.
 */

public class TimesUp {

    Boolean redTurn = true;
    int redPoints = 0;
    int bluePoints = 0;

    ArrayList<String> personajes = new ArrayList<String>();
    Database db;

    public TimesUp(Database db){
        this.db = db;
    }

    public ArrayList<String> meterPersonajes(){
        int posicion = 0;
        final Cursor c = db.db.rawQuery("SELECT nombre FROM personajes;", null);
        //SELECT * FROM table ORDER BY RANDOM() LIMIT 1;
        for (int i=0;i<5;i++){
            if(c.moveToPosition(i)){
                personajes.add(c.getString(1));
            }
        }

        return personajes;
    }


}
