package com.hsi.hearthstoneinfo.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.hsi.hearthstoneinfo.Entidades.Mazo;

import java.util.ArrayList;

/**
 * Created by Carlos - xibhu on 20/02/2018.
 */

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
    public void insertarCarta(Integer id_mazo, String nombre, Integer vida, Integer ataque){

        ContentValues v = new ContentValues();
        v.put(InfoBD.CARTA_ID_MAZO, id_mazo);
        v.put(InfoBD.CARTA_NOMBRE, nombre);
        v.put(InfoBD.CARTA_VIDA, vida);
        v.put(InfoBD.CARTA_ATAQUE, ataque);

        getWritableDatabase().insert(InfoBD.CARTA_TABLA, null, v);

        close();

    }

    public void eliminarCarta(Integer id_carta){

    }

    public void consultarCarta(Integer id_carta){

    }

    public void consultarCartasDeMazo(Integer id_mazo){

    }

    public void consultarCartasCantidad(){

    }

    public void modificarCarta(){

    }

}
