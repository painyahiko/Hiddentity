package com.example.david.hiddentity;

import android.database.Cursor;

/**
 * Created by david on 07/05/2018.
 */

public class TimesUp {

    Boolean redTurn = true;
    int redPoints = 0;
    int bluePoints = 0;
    String[] personajes;
    Database db;

    public TimesUp(Database db){
        this.db = db;
    }

    public String[] meterPersonajes(){
        int posicion = 0;
        final Cursor c = db.db.rawQuery("SELECT nombre FROM personajes ORDER BY NEWID();", null);
        for (int i=0;i<5;i++){
            if(c.moveToPosition(i)){
                personajes[i] = c.getString(1);
            }
        }

        return personajes;
    }


}
