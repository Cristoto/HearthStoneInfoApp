package com.hsi.hearthstoneinfo.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.hsi.hearthstoneinfo.Entidades.Carta;
import com.hsi.hearthstoneinfo.Entidades.Mazo;

import java.util.ArrayList;

/**
 * Created by Carlos - xibhu on 20/02/2018.
 */


//TODO HACER QUE LAS CLASES RECIBAN clases MAZO Y CARTA para hacer los getters
public class ConnSQLiteHelper extends SQLiteOpenHelper {

    /**
     * Constructor por defecto.
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public ConnSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Constructor generado custom, que automáticamente recoge los valores necesarios de la clase InfoBD.
     * @param context
     */
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

    /**
     * Insertar un mazo nuevo dándole un nombre.
     * @param nombre Nombre que se le quiere dar al nuevo mazo que se insertará.
     */
    public void insertarMazo(String nombre){

        ContentValues v = new ContentValues();
        v.put(InfoBD.MAZO_NOMBRE, nombre);
        getWritableDatabase().insert(InfoBD.MAZO_TABLA, null, v);

        close();
    }

    /**
     * Eliminar mazo a través de su ID.
     * @param id_mazo ID del mazo que se quiere eliminar.
     */
    public void eliminarMazo(Integer id_mazo){

        String[] parametros = {id_mazo + ""};
        getWritableDatabase().delete(InfoBD.MAZO_TABLA, InfoBD.MAZO_ID + " = ?", parametros);
        close();

    }

    /**
     * Consultar un mazo concreto a través de su ID.
     * @param id_mazo ID del mazo que se quiere consultar.
     * @return Mazo, con los atributos recogidos de la base de datos.
     */
    public Mazo consultarMazo(Integer id_mazo) {

        String[] parametros = {id_mazo.toString()};
        String[] campos = {InfoBD.MAZO_ID, InfoBD.MAZO_NOMBRE};

        Cursor c = getReadableDatabase().query(InfoBD.MAZO_TABLA, campos, InfoBD.MAZO_ID + "= ?", parametros, null, null, null);
        c.moveToFirst();

        Mazo m = new Mazo(c.getInt(0), c.getString(1));
        close();

        return m;
    }

    /**
     * Consultar todos los mazos de la base de datos.
     * @return ArrayList<Mazo> con todos los mazos que se han recogido.
     */
    public ArrayList<Mazo> consultarTodosMazos(){
        ArrayList<Mazo> mazos = new ArrayList<>();

        String[] campos = {InfoBD.MAZO_ID, InfoBD.MAZO_NOMBRE};
        Cursor c = getReadableDatabase().query(InfoBD.MAZO_TABLA, campos, null, null, null, null, null);

        while (c.moveToNext()){
            mazos.add(new Mazo(c.getInt(0), c.getString(1)));
        }
        close();
        return mazos;
    }

    /**
     * Modificar el nombre de un mazo existente.
     * @param id_mazo ID del mazo que se quiere modificar.
     * @param nombre Nuevo nombre que se le quiere dar al mazo.
     */
    public void modificarMazo(Integer id_mazo, String nombre){

        ContentValues values = new ContentValues();
        values.put(InfoBD.MAZO_NOMBRE, nombre);

        String[] parametros = {id_mazo + ""};

        getWritableDatabase().update(InfoBD.MAZO_TABLA, values, InfoBD.MAZO_ID + "=?", parametros);
        close();
    }




    //FUNCIONES DE CARTA

    /**
     * Insertar una carta en un mazo concreto.
     * @param m Mazo en el que se quiere insertar la carta.
     * @param c Carta (sin id de mazo) que se quiere insertar.
     */
    public void insertarCarta(Mazo m, Carta c){

        ContentValues v = new ContentValues();
        v.put(InfoBD.CARTA_ID_MAZO, m.getId());
        v.put(InfoBD.CARTA_NOMBRE, c.getNombre());
        v.put(InfoBD.CARTA_VIDA, c.getVida());
        v.put(InfoBD.CARTA_ATAQUE, c.getAtaque());

        getWritableDatabase().insert(InfoBD.CARTA_TABLA, null, v);

        close();

    }

    /**
     * Eliminar una carta de un mazo en concreto.
     * @param id_mazo Mazo del que se quiere eliminar una carta.
     * @param nombre Nombre de la carta que se quiere eliminar.
     */
    public void eliminarCarta(Integer id_mazo ,String nombre){

        String[] parametros = {nombre, String.valueOf(id_mazo)};
        getWritableDatabase().delete(InfoBD.CARTA_TABLA, InfoBD.CARTA_NOMBRE + " = ? and " + InfoBD.CARTA_ID_MAZO + " = ?", parametros);
        close();

    }

    /**
     * Devuelve todas las cartas de un mazo concreto.
     * @param id_mazo Mazo del que se quiere saber todas las cartas.
     * @return ArrayList<Carta> con todas las cartas de ese mazo.
     */
    public ArrayList<Carta> consultarCartasDeMazo(Integer id_mazo){
        ArrayList<Carta> cartas = new ArrayList<>();

        String[] parametros = {String.valueOf(id_mazo)};

        String[] campos = { InfoBD.CARTA_ID_MAZO, InfoBD.CARTA_NOMBRE, InfoBD.CARTA_VIDA, InfoBD.CARTA_ATAQUE};
        Cursor c = getReadableDatabase().query(InfoBD.CARTA_TABLA, campos, InfoBD.CARTA_ID_MAZO + "=?", parametros, null, null, null);

        while (c.moveToNext()){
            cartas.add(new Carta(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3)));
        }
        close();

        return cartas;
    }

    /**
     * Devuelve la cantidad total de cartas de un mazo concreto
     * @param id_mazo Mazo del que se quiere saber la cantidad de cartas.
     * @return Integer con el número de cartas.
     */
    public int  consultarCartasCantidad(Integer id_mazo){
        String consulta = "select count(*) from " + InfoBD.CARTA_TABLA + " where " + InfoBD.CARTA_ID_MAZO + " = " + String.valueOf(id_mazo);

        Cursor mCount= getReadableDatabase().rawQuery(consulta, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        close();
        return count;
    }

}
