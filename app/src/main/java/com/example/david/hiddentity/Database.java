package com.example.david.hiddentity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by david on 07/05/2018.
 */

public class Database {

    public  SQLiteDatabase db;


    Database(SQLiteDatabase db){
        this.db = db;
    }

    public SQLiteDatabase openOrCreate(){
        db.execSQL("CREATE TABLE IF NOT EXISTS personajes(id Varchar, nombre Varchar);");
        db.execSQL("INSERT INTO personajes VALUES ('1','pikachu');");
        db.execSQL("INSERT INTO personajes VALUES ('2','bulbasaur');");
        db.execSQL("INSERT INTO personajes VALUES ('3','charmander');");
        db.execSQL("INSERT INTO personajes VALUES ('4','squirtle');");
        db.execSQL("INSERT INTO personajes VALUES ('5','vegeta');");
        db.execSQL("INSERT INTO personajes VALUES ('6','goku');");
        db.execSQL("INSERT INTO personajes VALUES ('7','gohan');");
        return db;
    }




}
