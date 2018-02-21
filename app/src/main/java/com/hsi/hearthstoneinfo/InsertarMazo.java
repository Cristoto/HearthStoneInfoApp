package com.hsi.hearthstoneinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.hsi.hearthstoneinfo.BD.ConnSQLiteHelper;
import com.hsi.hearthstoneinfo.Entidades.Mazo;

import java.util.ArrayList;

public class InsertarMazo extends AppCompatActivity {

    EditText insertarEditText;
    EditText modificarEditText;

    Spinner eliminarMazoSpinner;
    Spinner modificarMazoSpinner;


    ArrayAdapter<Mazo> mazoSpinnerAdapter;
    ArrayList<Mazo> mazoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_mazo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        insertarEditText = findViewById(R.id.insertarEditText);
        modificarEditText = findViewById(R.id.modificarEditText);

        eliminarMazoSpinner = findViewById(R.id.eliminarMazoSpinner);
        modificarMazoSpinner = findViewById(R.id.modificarMazoSpinner);

        mazoList = new ArrayList<>();

        actualizarSpinner();


    }

    public void actualizarSpinner(){

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        mazoList = c.consultarTodosMazos();
        mazoSpinnerAdapter = new ArrayAdapter<Mazo>(this, R.layout.support_simple_spinner_dropdown_item, mazoList);
        modificarMazoSpinner.setAdapter(mazoSpinnerAdapter);
        eliminarMazoSpinner.setAdapter(mazoSpinnerAdapter);

    }


    public void onInsertarButtonAction(View view){

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        c.insertarMazo(insertarEditText.getText().toString());
        actualizarSpinner();

    }

    public void onEliminarButtonAction(View view){

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        Mazo m = (Mazo)eliminarMazoSpinner.getSelectedItem();
        c.eliminarMazo(m.getId());
        actualizarSpinner();

    }

    public void onModificarButtonAction(View view){

        Mazo m = (Mazo)modificarMazoSpinner.getSelectedItem();

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        c.modificarMazo(m.getId(), modificarEditText.getText().toString());
        actualizarSpinner();
    }
}
