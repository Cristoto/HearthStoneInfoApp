package com.hsi.hearthstoneinfo.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Carlos - xibhu on 20/02/2018.
 */

public class ConnSQLiteHelper extends SQLiteOpenHelper {

    public ConnSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ConnSQLiteHelper(Context context){
        this(context, InfoBD.BD_NOMBRE, null, InfoBD.BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(InfoBD.CREAR_TABLA_MAZO);
        sqLiteDatabase.execSQL(InfoBD.CREAR_TABLA_CARTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(InfoBD.BORRAR_TABLA_CARTA);
        sqLiteDatabase.execSQL(InfoBD.BORRAR_TABLA_MAZO);
        onCreate(sqLiteDatabase);
    }

    //FUNCIONES DE MAZO
    public void insertarMazo(String nombre){

        ContentValues v = new ContentValues();
        v.put(InfoBD.MAZO_NOMBRE, nombre);
        getWritableDatabase().insert(InfoBD.MAZO_TABLA, null, v);

    }

    public void eliminarMazo(Integer id_mazo){

    }

    public String consultarMazo(Integer id_mazo) {

        String[] parametros = {id_mazo.toString()};
        String[] campos = {InfoBD.MAZO_NOMBRE};

        Cursor c = getReadableDatabase().query(InfoBD.MAZO_TABLA, campos, InfoBD.MAZO_ID + "=?", parametros, null, null, null);
        c.moveToFirst();

        return c.getString(0);
    }

    public void modificarMazo(Integer id_mazo){

    }

    //FUNCIONES DE CARTA
    public void insertarCarta(Integer id_mazo, String nombre, String vida, String ataque){

    }

    public void eliminarCarta(Integer id_carta){

    }

    public void consultarCarta(Integer id_carta){

    }

    public void consultarCartasDeMazo(Integer id_mazo){

    }

    public void modificarCarta(){

    }

}
