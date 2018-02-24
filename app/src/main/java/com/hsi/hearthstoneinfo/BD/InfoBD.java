package com.hsi.hearthstoneinfo.BD;

/**
 * Created by Carlos - xibhu on 20/02/2018.
 * Clase para reutilizar los nombres de las tablas, columnas, y tener los strings necesarios para la creación y eliminación de la base de datos.
 */

public class InfoBD {

    //Nombre de la base de datos
    public final static String BD_NOMBRE = "bd_hearthstone";
    //Versión de la base de datos, utilizada para que en las actualizaciones de la APP, si es necesario, modificar las tablas mediante el onUpgrade de la clase ConnSQLiteHelper
    public final static Integer BD_VERSION = 1;

    //TABLA MAZO
    //Información acerca de la tabla de mazos, para ser utilizada por otras clases
    public final static String MAZO_TABLA = "mazo";
    public final static String MAZO_ID = "id";
    public final static String MAZO_NOMBRE = "nombre";

    public final static String CREAR_TABLA_MAZO = "CREATE TABLE "+ MAZO_TABLA + " (" +
            MAZO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MAZO_NOMBRE + " TEXT" +
            ")";

    public final static String BORRAR_TABLA_MAZO = "DROP TABLE IF EXIST " + MAZO_TABLA;


    //TABLA CARTA
    //Información acerca de la tabla de cartas, para ser utilizada por otras clases
    public final static String CARTA_TABLA = "carta";
    public final static String CARTA_NOMBRE = "nombre";
    public final static String CARTA_ID_MAZO = "id_mazo";
    public final static String CARTA_VIDA = "vida";
    public final static String CARTA_ATAQUE = "ataque";

    public final static String CREAR_TABLA_CARTA = "CREATE TABLE " + CARTA_TABLA + " (" +
            CARTA_NOMBRE + " TEXT, " +
            CARTA_ID_MAZO + " INTEGER, " +
            CARTA_VIDA + " INTEGER, " +
            CARTA_ATAQUE + " INTEGER, " +
            "FOREIGN KEY (" + CARTA_ID_MAZO + ") REFERENCES " + MAZO_TABLA + " (" + MAZO_ID + "), " +
            "PRIMARY KEY (" + CARTA_NOMBRE + ", " + CARTA_ID_MAZO + ")" +
            ")";

    public final static String BORRAR_TABLA_CARTA = "DROP TABLE IF EXIST " + CARTA_TABLA;


}
