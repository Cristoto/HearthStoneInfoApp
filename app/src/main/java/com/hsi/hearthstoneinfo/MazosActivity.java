package com.hsi.hearthstoneinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.TextView;

import com.hsi.hearthstoneinfo.BD.ConnSQLiteHelper;
import com.hsi.hearthstoneinfo.Entidades.Carta;
import com.hsi.hearthstoneinfo.Entidades.Mazo;

import java.util.ArrayList;

public class MazosActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /*Variables que se necesitan para rellenar el spinner de mazos*/
    Spinner mazosSpinner;
    ArrayList<Mazo> mazoSpinnerArray;
    ArrayAdapter<Mazo> mazoSpinnerAdapter;


    /*Variables que se necesitan para rellenar la ListView de cartas*/
    ListView mazoListView;
    ArrayList<Carta> mazoListArray;
    ArrayAdapter<Carta> mazoAdapterListView;

    TextView cartasTotales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mazos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MazosActivity.this, ContactoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Obntener la referencia de la interfaz.
        mazosSpinner = findViewById(R.id.mazosSpinner);
        mazoListView = findViewById(R.id.mazosListView);

        //Inicializar los componentes necesarios.
        mazoSpinnerArray = new ArrayList<>();
        mazoListArray = new ArrayList<>();

        cartasTotales = findViewById(R.id.cartasTotales);

        actualizarSpinner();

        /**
         * Listener para el evento de item seleccionado en el spinner.
         */
        mazosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //Cuando se selecciona un elemento, se crea un objeto mazo y se envia a la función, para que rellene el ListView.
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Mazo m = (Mazo)adapterView.getItemAtPosition(i);

                rellenarList(m);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /**
         * Listener para el ListView, para cuando se selecciona un item.
         */
        mazoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Se crea una carta y se envía a la función verCarta.
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Carta c = (Carta)adapterView.getItemAtPosition(i);

                verCarta(c);

            }
        });

    }

    /**
     *
     Cuando se vuelve a esta actividad, se actualiza el spinner, para que se ponga en funcionamiento el listener, y muestre las cartas. (
     Y en caso de que el primer mazo de cartas haya recibido cambios, se actualiza la lista de cartas)
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        actualizarSpinner();
    }

    /**
     *
     Recoge una carta e inicia la actividad donde se ve toda la información sobre la carta.
     */
    public void verCarta(Carta c){
        Intent i = new Intent(this, VerCarta.class);
        i.putExtra("carta", c);
        startActivity(i);
    }

    /**
     *Actualiza la liste del spinner con los mazos actuales.
     */
    public void actualizarSpinner(){

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        mazoSpinnerArray = c.consultarTodosMazos();
        mazoSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, mazoSpinnerArray);
        mazosSpinner.setAdapter(mazoSpinnerAdapter);

    }

    /**
     * Rellena la ListView con el mazo seleccionado
     * @param m Mazo del que se quiere ver sus cartas en el ListView
     */
    public void rellenarList(Mazo m){

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        mazoListArray = c.consultarCartasDeMazo(m.getId());
        mazoAdapterListView = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mazoListArray);
        mazoListView.setAdapter(mazoAdapterListView);

        cartasTotales.setText(c.consultarCartasCantidad(m.getId())+"/15");
    }

    /**
     * Botón que lleva a la actividad para las acciones sobre los mazos.
     * @param view
     */
    public void AjusteMazoButtonPressed(View view){
        Intent intent = new Intent(this, InsertarMazo.class);
        startActivity(intent);
    }




    //SECUNDARIO

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
