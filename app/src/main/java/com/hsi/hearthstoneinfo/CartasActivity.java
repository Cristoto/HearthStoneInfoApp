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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.hsi.hearthstoneinfo.BD.ConnSQLiteHelper;
import com.hsi.hearthstoneinfo.Entidades.Mazo;

import java.util.ArrayList;

public class CartasActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Spinner mazoSpinner;
    ArrayAdapter<Mazo> mazoSpinnerAdapter;
    ArrayList<Mazo> mazoList;

    EditText nombreEditText;
    EditText vidaEditText;
    EditText ataqueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartasActivity.this, ContactoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mazoSpinner = findViewById(R.id.mazoSpinner);

        nombreEditText = findViewById(R.id.nombreEditText);
        vidaEditText = findViewById(R.id.vidaEditText);
        ataqueEditText = findViewById(R.id.ataqueEditText);

        actualizarSpinner();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
            Intent intent = new Intent(this, ContactoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.acercaDe) {
            Intent intent = new Intent(this, AcercaDeActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void actualizarSpinner(){

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        mazoList = c.consultarTodosMazos();
        mazoSpinnerAdapter = new ArrayAdapter<Mazo>(this, R.layout.support_simple_spinner_dropdown_item, mazoList);
        mazoSpinner.setAdapter(mazoSpinnerAdapter);

    }

    //TODO hacer comprobaciones pertinentes
    public void onInsertarButtonAction(View view){

        Mazo m = (Mazo)mazoSpinner.getSelectedItem();

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);

        Integer v = Integer.valueOf(vidaEditText.getText().toString());
        Integer a = Integer.valueOf(ataqueEditText.getText().toString());
        c.insertarCarta(m.getId(), nombreEditText.getText().toString(), v, a);

    }

}
