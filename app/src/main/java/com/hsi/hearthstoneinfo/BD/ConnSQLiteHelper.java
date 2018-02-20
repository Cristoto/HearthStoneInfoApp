package com.hsi.hearthstoneinfo.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xibhu on 20/02/2018.
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






}
