package com.example.david.hiddentity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by david on 07/05/2018.
 */

public class TimesUp implements Parcelable {

    Boolean redTurn;
    int redPoints;
    int bluePoints;
    ArrayList<String> personajes = new ArrayList<String>();
    ArrayList<String> personajesRojos;
    ArrayList<String> personajesAzules;



    public TimesUp(ArrayList<String> personajes){
        this.personajes = personajes;
        redTurn = true;
        bluePoints = 0;
        redPoints = 0;
        personajesAzules = new ArrayList<String>();
        personajesRojos = new ArrayList<String>();
    }



    //Implementacion de Parcelable para pasar el objeto entre actividades

    protected TimesUp(Parcel in) {
        byte redTurnVal = in.readByte();
        redTurn = redTurnVal == 0x02 ? null : redTurnVal != 0x00;
        redPoints = in.readInt();
        bluePoints = in.readInt();
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