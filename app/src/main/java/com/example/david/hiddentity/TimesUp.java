package com.example.david.hiddentity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by david on 07/05/2018.
 */

public class TimesUp implements Parcelable {

    Boolean redTurn;
    int redPoints;
    int bluePoints;
    int tiempo;
    ArrayList<String> personajes = new ArrayList<>();
    ArrayList<String> personajesRojos;
    ArrayList<String> personajesAzules;
    ArrayList<String> jugadoresRojos;
    ArrayList<String> jugadoresAzules;



    public TimesUp(ArrayList<String> personajes,int tiempo){
        this.personajes = personajes;
        this.tiempo = tiempo*1000;
        redTurn = true;
        bluePoints = 0;
        redPoints = 0;
        personajesAzules = new ArrayList<>();
        personajesRojos = new ArrayList<>();
        jugadoresRojos = new ArrayList<>();
        jugadoresAzules = new ArrayList<>();
    }

    //Implementacion de parcelable para pasar el objeto entre actividades

    protected TimesUp(Parcel in) {
        byte redTurnVal = in.readByte();
        redTurn = redTurnVal == 0x02 ? null : redTurnVal != 0x00;
        redPoints = in.readInt();
        bluePoints = in.readInt();
        tiempo = in.readInt();
        if (in.readByte() == 0x01) {
            personajes = new ArrayList<String>();
            in.readList(personajes, String.class.getClassLoader());
        } else {
            personajes = null;
        }
        if (in.readByte() == 0x01) {
            personajesRojos = new ArrayList<String>();
            in.readList(personajesRojos, String.class.getClassLoader());
        } else {
            personajesRojos = null;
        }
        if (in.readByte() == 0x01) {
            personajesAzules = new ArrayList<String>();
            in.readList(personajesAzules, String.class.getClassLoader());
        } else {
            personajesAzules = null;
        }
        if (in.readByte() == 0x01) {
            jugadoresRojos = new ArrayList<String>();
            in.readList(jugadoresRojos, String.class.getClassLoader());
        } else {
            jugadoresRojos = null;
        }
        if (in.readByte() == 0x01) {
            jugadoresAzules = new ArrayList<String>();
            in.readList(jugadoresAzules, String.class.getClassLoader());
        } else {
            jugadoresAzules = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (redTurn == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (redTurn ? 0x01 : 0x00));
        }
        dest.writeInt(redPoints);
        dest.writeInt(bluePoints);
        dest.writeInt(tiempo);
        if (personajes == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(personajes);
        }
        if (personajesRojos == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(personajesRojos);
        }
        if (personajesAzules == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(personajesAzules);
        }
        if (jugadoresRojos == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(jugadoresRojos);
        }
        if (jugadoresAzules == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(jugadoresAzules);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TimesUp> CREATOR = new Parcelable.Creator<TimesUp>() {
        @Override
        public TimesUp createFromParcel(Parcel in) {
            return new TimesUp(in);
        }

        @Override
        public TimesUp[] newArray(int size) {
            return new TimesUp[size];
        }
    };
}