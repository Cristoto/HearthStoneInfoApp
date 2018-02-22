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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hsi.hearthstoneinfo.BD.ConnSQLiteHelper;
import com.hsi.hearthstoneinfo.Entidades.Carta;
import com.hsi.hearthstoneinfo.Entidades.Mazo;

import java.util.ArrayList;

public class MazosActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner mazosSpinner;

    ArrayList<Mazo> mazoSpinnerArray;
    ArrayAdapter<Mazo> mazoSpinnerAdapter;


    ListView mazoListView;

    ArrayList<Carta> mazoListArray;
    ArrayAdapter<Carta> mazoAdapterListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mazos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MazosActivity.this, ContactoActivity.class);
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

        mazosSpinner = findViewById(R.id.mazosSpinner);
        mazoListView = findViewById(R.id.mazosListView);

        mazoSpinnerArray = new ArrayList<>();
        mazoListArray = new ArrayList<>();

        actualizarSpinner();

        mazosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Mazo m = (Mazo)adapterView.getItemAtPosition(i);
                rellenarList(m);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mazoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Carta c = (Carta)adapterView.getItemAtPosition(i);

                verCarta(c);

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        actualizarSpinner();
    }

    public void verCarta(Carta c){
        Intent i = new Intent(this, VerCarta.class);
        i.putExtra("carta", c);
        startActivity(i);
    }


    public void actualizarSpinner(){

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        mazoSpinnerArray = c.consultarTodosMazos();
        mazoSpinnerAdapter = new ArrayAdapter<Mazo>(this, R.layout.support_simple_spinner_dropdown_item, mazoSpinnerArray);
        mazosSpinner.setAdapter(mazoSpinnerAdapter);

    }

    public void rellenarList(Mazo m){

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        mazoListArray = c.consultarCartasDeMazo(m.getId());
        mazoAdapterListView = new ArrayAdapter<Carta>(this, android.R.layout.simple_list_item_1, mazoListArray);
        mazoListView.setAdapter(mazoAdapterListView);

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

    public void AjusteMazoButtonPressed(View view){
        Intent intent = new Intent(this, InsertarMazo.class);
        startActivity(intent);
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

        } else if (id == R.id.cartas) {
            Intent intent = new Intent(this, CartasActivity.class);
            startActivity(intent);
            finish();
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
}
