package com.example.david.hiddentity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by david on 07/05/2018.
 */

public class Database {

    public  SQLiteDatabase db;


    Database(SQLiteDatabase db){
        this.db = db;
    }

    public SQLiteDatabase openOrCreate() {
        //db.execSQL("DROP TABLE IF EXISTS personajes");
        db.execSQL("CREATE TABLE IF NOT EXISTS personajes(id INT, nombre Varchar, grupo INT);");
        //db.execSQL("DROP TABLE IF EXISTS mazos");
        db.execSQL("CREATE TABLE IF NOT EXISTS mazos(id INT, nombre Varchar, grupo INT);");

        final Cursor c = db.rawQuery("SELECT nombre FROM personajes;", null);


        if (!c.moveToFirst()) {

            db.execSQL("INSERT INTO personajes VALUES (1,'Pikachu',1);");
            db.execSQL("INSERT INTO personajes VALUES (2,'Joker',1);");
            db.execSQL("INSERT INTO personajes VALUES (3,'Mr Bean',1);");
            db.execSQL("INSERT INTO personajes VALUES (4,'La pantera rosa',1);");
            db.execSQL("INSERT INTO personajes VALUES (5,'Cristina Pedroche',1);");
            db.execSQL("INSERT INTO personajes VALUES (6,'Goku',1);");
            db.execSQL("INSERT INTO personajes VALUES (7,'Vegeta',1);");
            db.execSQL("INSERT INTO personajes VALUES (8,'Cristina Cifuentes',1);");
            db.execSQL("INSERT INTO personajes VALUES (9,'Brad Pitt',1);");
            db.execSQL("INSERT INTO personajes VALUES (10,'Deadpool',1);");
            db.execSQL("INSERT INTO personajes VALUES (11,'Carlomagno',1);");
            db.execSQL("INSERT INTO personajes VALUES (12,'Harry Potter',1);");
            db.execSQL("INSERT INTO personajes VALUES (13,'Stephen Hawking',1);");
            db.execSQL("INSERT INTO personajes VALUES (14,'Juan Pablo II',1);");
            db.execSQL("INSERT INTO personajes VALUES (15,'Atila (el Uno)',1);");
            db.execSQL("INSERT INTO personajes VALUES (16,'Leonidas',1);");
            db.execSQL("INSERT INTO personajes VALUES (17,'Donald Trump',1);");
            db.execSQL("INSERT INTO personajes VALUES (18,'Matusalen',1);");
            db.execSQL("INSERT INTO personajes VALUES (19,'Naruto',1);");
            db.execSQL("INSERT INTO personajes VALUES (20,'Zelda',1);");
            db.execSQL("INSERT INTO personajes VALUES (21,'Scooby Doo',1);");
            db.execSQL("INSERT INTO personajes VALUES (22,'Antonio Resines',1);");
            db.execSQL("INSERT INTO personajes VALUES (23,'Messi',1);");
            db.execSQL("INSERT INTO personajes VALUES (24,'Cristiano Ronaldo',1);");
            db.execSQL("INSERT INTO personajes VALUES (25,'Iker Casillas',1);");
            db.execSQL("INSERT INTO personajes VALUES (26,'Roger Federer',1);");
            db.execSQL("INSERT INTO personajes VALUES (27,'Rafael Nadal',1);");
            db.execSQL("INSERT INTO personajes VALUES (28,'Mickey Mouse',1);");
            db.execSQL("INSERT INTO personajes VALUES (29,'Charles Chapplin',1);");
            db.execSQL("INSERT INTO personajes VALUES (30,'Kenny',1);");
            db.execSQL("INSERT INTO personajes VALUES (31,'Barney Stinsson',1);");
            db.execSQL("INSERT INTO personajes VALUES (32,'Ted Mosby',1);");
            db.execSQL("INSERT INTO personajes VALUES (33,'Optimus Prime',1);");
            db.execSQL("INSERT INTO personajes VALUES (34,'Buzz Lightyear',1);");
            db.execSQL("INSERT INTO personajes VALUES (35,'Santa Claus',1);");
            db.execSQL("INSERT INTO personajes VALUES (36,'Antonio Recio',1);");
            db.execSQL("INSERT INTO personajes VALUES (37,'Walter White',1);");
            db.execSQL("INSERT INTO personajes VALUES (38,'Dora, la Exploradora',1);");
            db.execSQL("INSERT INTO personajes VALUES (39,'Beethoven',1);");
            db.execSQL("INSERT INTO personajes VALUES (40,'Amadeus Mozart',1);");
            db.execSQL("INSERT INTO personajes VALUES (41,'Barack Obama',1);");
            db.execSQL("INSERT INTO personajes VALUES (42,'Billy el niño',1);");
            db.execSQL("INSERT INTO personajes VALUES (43,'Michael Jackson',1);");
            db.execSQL("INSERT INTO personajes VALUES (44,'Eminem',1);");
            db.execSQL("INSERT INTO personajes VALUES (45,'Justin Bieber',1);");
            db.execSQL("INSERT INTO personajes VALUES (46,'René Descartes',1);");
            db.execSQL("INSERT INTO personajes VALUES (47,'Platón',1);");
            db.execSQL("INSERT INTO personajes VALUES (48,'Leonardo Da Vinci',1);");
            db.execSQL("INSERT INTO personajes VALUES (49,'Martin Luther King',1);");
            db.execSQL("INSERT INTO personajes VALUES (50,'Marie Curie',1);");
            db.execSQL("INSERT INTO personajes VALUES (51,'Bill Gates',1);");
            db.execSQL("INSERT INTO personajes VALUES (52,'Walt Disney',1);");
            db.execSQL("INSERT INTO personajes VALUES (53,'Steve Jobs',1);");
            db.execSQL("INSERT INTO personajes VALUES (54,'Alexander Fleming',1);");
            db.execSQL("INSERT INTO personajes VALUES (55,'Harry Houdini',1);");
            db.execSQL("INSERT INTO personajes VALUES (56,'Adolf Hitler',1);");
            db.execSQL("INSERT INTO personajes VALUES (57,'Eva Braun',1);");
            db.execSQL("INSERT INTO personajes VALUES (58,'Francisco Franco',1);");
            db.execSQL("INSERT INTO personajes VALUES (59,'Juan Carlos I',1);");
            db.execSQL("INSERT INTO personajes VALUES (60,'Taylor Swift',1);");
            db.execSQL("INSERT INTO personajes VALUES (61,'Leonardo DiCaprio',1);");
            db.execSQL("INSERT INTO personajes VALUES (62,'Jennifer Aniston',1);");
            db.execSQL("INSERT INTO personajes VALUES (63,'Usain Bolt',1);");
            db.execSQL("INSERT INTO personajes VALUES (64,'J. K. Rowling',1);");
            db.execSQL("INSERT INTO personajes VALUES (65,'Neil Patrick Harris',1);");
            db.execSQL("INSERT INTO personajes VALUES (66,'LeBron James',1);");
            db.execSQL("INSERT INTO personajes VALUES (67,'Michel Jordan',1);");
            db.execSQL("INSERT INTO personajes VALUES (68,'Kobe Bryant',1);");
            db.execSQL("INSERT INTO personajes VALUES (69,'Larry Bird',1);");
            db.execSQL("INSERT INTO personajes VALUES (70,'Jon Bon Jobi',1);");
            db.execSQL("INSERT INTO personajes VALUES (71,'Maria Sharapova',1);");
            db.execSQL("INSERT INTO personajes VALUES (72,'John Titor',1);");
            db.execSQL("INSERT INTO personajes VALUES (73,'Charles Darwin',1);");
            db.execSQL("INSERT INTO personajes VALUES (74,'Cristobal Colón',1);");
            db.execSQL("INSERT INTO personajes VALUES (75,'Galileo Galilei',1);");
            db.execSQL("INSERT INTO personajes VALUES (76,'Jesus de Nazaret',1);");
            db.execSQL("INSERT INTO personajes VALUES (77,'Karl Marx',1);");
            db.execSQL("INSERT INTO personajes VALUES (78,'Popeye el Marino',1);");
            db.execSQL("INSERT INTO personajes VALUES (79,'Lenin',1);");
            db.execSQL("INSERT INTO personajes VALUES (80,'Cleopatra',1);");
            db.execSQL("INSERT INTO personajes VALUES (81,'Michael Phelps',1);");
            db.execSQL("INSERT INTO personajes VALUES (82,'Tiger Woods',1);");
            db.execSQL("INSERT INTO personajes VALUES (83,'Pablo Escobar',1);");
            db.execSQL("INSERT INTO personajes VALUES (84,'Daniel el Rojo',1);");
            db.execSQL("INSERT INTO personajes VALUES (85,'Osama Bin Laden',1);");
            db.execSQL("INSERT INTO personajes VALUES (86,'Pedro Picapiedra',1);");
            db.execSQL("INSERT INTO personajes VALUES (87,'El oso Yogui',1);");
            db.execSQL("INSERT INTO personajes VALUES (88,'Agallas (el perro cobarde)',1);");
            db.execSQL("INSERT INTO personajes VALUES (89,'Johnny Bravo',1);");
            db.execSQL("INSERT INTO personajes VALUES (90,'Marvin el marciano',1);");
            db.execSQL("INSERT INTO personajes VALUES (91,'Luffy',1);");
            db.execSQL("INSERT INTO personajes VALUES (92,'Rick Grimmes',1);");
            db.execSQL("INSERT INTO personajes VALUES (93,'John Snow',1);");
            db.execSQL("INSERT INTO personajes VALUES (94,'Jason',1);");
            db.execSQL("INSERT INTO personajes VALUES (95,'Lupin III',1);");
            db.execSQL("INSERT INTO personajes VALUES (96,'Kenedy',1);");
            db.execSQL("INSERT INTO personajes VALUES (97,'Megaman',1);");
            db.execSQL("INSERT INTO personajes VALUES (98,'Lara Croft',1);");
            db.execSQL("INSERT INTO personajes VALUES (99,'Crash Bandicoot',1);");
            db.execSQL("INSERT INTO personajes VALUES (100,'Miguel de Cervantes',1);");

        }

        final Cursor c1 = db.rawQuery("SELECT nombre FROM mazos;", null);

        if (!c1.moveToFirst()) {

            db.execSQL("INSERT INTO mazos VALUES (1,'Predeterminado',1);");
            db.execSQL("INSERT INTO mazos VALUES (2,'crear mazo',2);");
            db.execSQL("INSERT INTO mazos VALUES (3,'crear mazo',3);");
            db.execSQL("INSERT INTO mazos VALUES (4,'crear mazo',4);");
            db.execSQL("INSERT INTO mazos VALUES (5,'crear mazo',5);");
        }

        return db;
    }

    public ArrayList<String> elegirPersonajes(int num,int grupo){
        ArrayList<String> elegidos = new ArrayList<String>();
        final Cursor c = db.rawQuery("SELECT nombre FROM personajes WHERE grupo = '" + grupo + "'ORDER BY RANDOM() LIMIT "+num+";", null);
        for (int i=0;i<num;i++){
            if(c.moveToPosition(i)){
                elegidos.add(c.getString(0));
            }
        }
        return elegidos;
    }

    public ArrayList<String> nombresMazos(){
        ArrayList<String> elegidos = new ArrayList<String>();
        final Cursor c = db.rawQuery("SELECT nombre FROM mazos;", null);
        for (int i=0;i<5;i++){
            if(c.moveToPosition(i)){
                elegidos.add(c.getString(0));
            }
        }
        return elegidos;
    }

    public void cambiarNombreMazo(String nombre,Integer grupo){
        db.execSQL("UPDATE mazos SET nombre='" + nombre + "' WHERE grupo='" + grupo + "';");
    }

    public void meterPersonaje(String personaje,Integer grupo){
        db.execSQL("INSERT INTO personajes VALUES ('','" + personaje + "','" + grupo + "');");
    }
    public void borrarPersonajesGrupo(Integer grupo){
        db.execSQL("DELETE FROM personajes WHERE grupo='"+grupo+"';");
    }

    public ArrayList<String> getGrupo(Integer grupo){
        ArrayList<String> personajes = new ArrayList<String>();
        final Cursor c = db.rawQuery("SELECT nombre FROM personajes WHERE grupo = '" + grupo + "';", null);
        for (int i=0;i<c.getCount();i++){
            if(c.moveToPosition(i)){
                personajes.add(c.getString(0));
            }
        }
        return personajes;
    }

    public int numeroPersonajes(Integer grupo){
        final Cursor c = db.rawQuery("SELECT nombre FROM personajes WHERE grupo = '" + grupo + "';", null);
        return c.getCount();
    }

    public int numeroGrupo(String nombre){
        final Cursor c = db.rawQuery("SELECT grupo FROM mazos WHERE nombre = '" + nombre + "';", null);
        c.moveToFirst();
        return c.getInt(0);
    }



    //db.execSQL("UPDATE usuarios SET usuario='" + usu + "',contraseña='" + paswd + "';");
}
