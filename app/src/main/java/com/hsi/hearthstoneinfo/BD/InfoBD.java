package com.hsi.hearthstoneinfo.BD;

/**
 * Created by xibhu on 20/02/2018.
 */

public class InfoBD {

    //Nombre de la base de datos
    final static String BD_NOMBRE = "bd_hearthstone";
    final static Integer BD_VERSION = 1;

    //TABLA MAZO
    //Información acerca de la tabla de mazos, para ser utilizada por otras clases
    final static String MAZO_TABLA = "mazo";
    final static String MAZO_ID = "id";
    final static String MAZO_NOMBRE = "nombre";

    final static String CREAR_TABLA_MAZO = "CREATE TABLE "+ MAZO_TABLA + " (" +
            MAZO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            MAZO_NOMBRE + " TEXT" +
            ")";
    final static String BORRAR_TABLA_MAZO = "DROP TABLE IF EXIST " + MAZO_TABLA;


    //TABLA CARTA
    //Información acerca de la tabla de cartas, para ser utilizada por otras clases
    final static String CARTA_TABLA = "carta";
    final static String CARTA_ID = "id";
    final static String CARTA_ID_MAZO = "id_mazo";
    final static String CARTA_NOMBRE = "nombre";
    final static String CARTA_VIDA = "vida";
    final static String CARTA_ATAQUE = "ataque";

    final static String CREAR_TABLA_CARTA = "CREATE TABLE " + CARTA_TABLA + " (" +
            CARTA_ID + " INTEGER AUTOINCREMENT NOT NULL, "+
            CARTA_ID_MAZO + " INTEGER NOT NULL, " +
            CARTA_NOMBRE + " TEXT, " +
            CARTA_VIDA + " INTEGER, " +
            CARTA_ATAQUE + " INTEGER, " +
            "FOREIGN KEY (" + CARTA_ID_MAZO + ") REFERENCES " + MAZO_TABLA + " (" + MAZO_ID + "), " +
            "PRIMARY KEY (" + CARTA_ID + ", " + CARTA_ID_MAZO + ")" +
            ")";

    final static String BORRAR_TABLA_CARTA = "DROP TABLE IF EXIST " + CARTA_TABLA;

}
