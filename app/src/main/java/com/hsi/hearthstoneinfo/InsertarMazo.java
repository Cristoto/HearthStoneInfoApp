package com.hsi.hearthstoneinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

    /**
     * Actualizar los spinner cada vez que se hace una modificación en la base de datos.
     */
    public void actualizarSpinner(){

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        mazoList = c.consultarTodosMazos();
        mazoSpinnerAdapter = new ArrayAdapter<Mazo>(this, R.layout.support_simple_spinner_dropdown_item, mazoList);
        modificarMazoSpinner.setAdapter(mazoSpinnerAdapter);
        eliminarMazoSpinner.setAdapter(mazoSpinnerAdapter);

    }

    /**
     * Botón insertar, se recoge el string del EditText de la interfaz, y se hace la inserción en la base de datos.
     * @param view
     */
    //TODO comprobar que no hayan más de 10 cartas por mazo
    public void onInsertarButtonAction(View view){

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);

        if(!insertarEditText.getText().toString().equals("")){

            try{
                c.insertarMazo(insertarEditText.getText().toString());
                Toast.makeText(this, "Mazo insertado", Toast.LENGTH_SHORT).show();
                actualizarSpinner();
                insertarEditText.setText("");
            }catch (Exception e){
                Toast.makeText(this, "Error al insertar mazo", Toast.LENGTH_SHORT).show();
            }



        }else{

            Toast.makeText(this, "Introduce nombre", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * Botón eliminar, se recoge del spinner el mazo que se quiere eliminar, y se hace la eliminación.
     * @param view
     */

    //TODO hacer comprobación de cartas, y si hay borrar las cartas antes del mazo.
    public void onEliminarButtonAction(View view){

        if(eliminarMazoSpinner.getSelectedItem() != null){
            Mazo m = (Mazo)eliminarMazoSpinner.getSelectedItem();

            try{

                ConnSQLiteHelper c = new ConnSQLiteHelper(this);
                c.eliminarMazo(m.getId());
                Toast.makeText(this, "Mazo eliminado", Toast.LENGTH_SHORT).show();
                actualizarSpinner();

            }catch (Exception e){
                Toast.makeText(this, "Error al eliminar mazo", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "No hay mazo seleccionado", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Botón modificar, se recoge del spinner el mazo que se quiere modificar, y a través del EditText, se genera un update en la base de datos.
     * @param view
     */
    public void onModificarButtonAction(View view){

        if(modificarMazoSpinner.getSelectedItem() != null){

            Mazo m = (Mazo)modificarMazoSpinner.getSelectedItem();

            if (!modificarEditText.getText().toString().equals("")){
                try{
                    ConnSQLiteHelper c = new ConnSQLiteHelper(this);
                    c.modificarMazo(m.getId(), modificarEditText.getText().toString());
                    Toast.makeText(this, "Mazo modificado", Toast.LENGTH_SHORT).show();
                    actualizarSpinner();
                }catch (Exception e){
                    Toast.makeText(this, "Error al modificar", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Introduce nombre", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Selecciona un mazo", Toast.LENGTH_SHORT).show();
        }

    }
}
