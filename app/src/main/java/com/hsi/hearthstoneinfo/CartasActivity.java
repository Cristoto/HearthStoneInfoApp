package com.hsi.hearthstoneinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hsi.hearthstoneinfo.BD.ConnSQLiteHelper;
import com.hsi.hearthstoneinfo.Entidades.Carta;
import com.hsi.hearthstoneinfo.Entidades.Mazo;

import java.util.ArrayList;

public class CartasActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Variables necesarias para rellenar el spinner con los mazos donde se pueden insertar cartas.
    Spinner mazoSpinner;
    ArrayAdapter<Mazo> mazoSpinnerAdapter;
    ArrayList<Mazo> mazoList;

    //Variables para enlazar con la interfaz.
    EditText nombreEditText;
    EditText vidaEditText;
    EditText ataqueEditText;

    TextView cantidadCartas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartasActivity.this, ContactameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Enlazar elementos de la interfaz con las variables.
        mazoSpinner = findViewById(R.id.mazoSpinner);

        nombreEditText = findViewById(R.id.nombreEditText);
        vidaEditText = findViewById(R.id.vidaEditText);
        ataqueEditText = findViewById(R.id.ataqueEditText);

        cantidadCartas = findViewById(R.id.cantidadCartas);

        mazoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (mazoSpinner.getSelectedItem() != null)
                actualizarCantidadCartas((Mazo)adapterView.getItemAtPosition(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        actualizarSpinner();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.inicio) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.mazos) {
            Intent intent = new Intent(this, MazosActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.cartas) {

        } else if (id == R.id.contacto) {
            Intent intent = new Intent(this, ContactameActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.acercaDe) {
            Intent intent = new Intent(this, AcercaDeActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * Rellena el spinner con los mazos disponibles en ese momento.
     */
    public void actualizarSpinner(){

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        mazoList = c.consultarTodosMazos();
        mazoSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, mazoList);
        mazoSpinner.setAdapter(mazoSpinnerAdapter);

    }

    /**
     * Botón para insertar una nueva carta en un mazo concreto.
     * Se realizan todas las comprobaciones que son necesarias.
     * @param view
     */
    public void onInsertarButtonAction(View view){
        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        Mazo m = null;
        Carta carta = new Carta();

        boolean error = false;
        String errores = "";

        if(mazoSpinner.getSelectedItem() == null){
            error = true;
            errores = "Falta seleccionar un mazo.\n";
        }
        if(nombreEditText.getText().toString().equals("")){
            error = true;
            errores += "Falta insertar el nombre.\n";
        }
        if(vidaEditText.getText().toString().equals("")){
            error = true;
            errores += "Falta insertar la vida.\n";
        }
        if(ataqueEditText.getText().toString().equals("")){
            error = true;
            errores += "Falta insertar el ataque.";
        }

        if (!error){
            m =(Mazo) mazoSpinner.getSelectedItem();
            if (!c.consultarCartaExisteEnMazo(m.getId(),nombreEditText.getText().toString())){

                if (c.consultarCartasCantidad(m.getId()) < 15){
                    carta.setNombre(nombreEditText.getText().toString());
                    carta.setVida(Integer.valueOf(vidaEditText.getText().toString()));
                    carta.setAtaque(Integer.valueOf(ataqueEditText.getText().toString()));

                    c.insertarCarta(m, carta);
                    Toast.makeText(this, "Carta añadida", Toast.LENGTH_SHORT).show();

                    nombreEditText.setText("");
                    vidaEditText.setText("");
                    ataqueEditText.setText("");
                    actualizarCantidadCartas(m);
                }else{
                    Toast.makeText(this, "Has llegado al límite de 15 cartas", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Ya existe una carta con el mismo nombre", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, errores, Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Actualizar el conteo de las cartas
     * @param m Mazo del que coger la cantidad de cartas.
     */
    public void actualizarCantidadCartas(Mazo m){
        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        cantidadCartas.setText(c.consultarCartasCantidad(m.getId()) + "/15");
    }

}
