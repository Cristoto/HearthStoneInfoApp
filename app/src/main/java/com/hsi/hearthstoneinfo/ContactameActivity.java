package com.hsi.hearthstoneinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class ContactameActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText telefonoText, mensajeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactame);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        telefonoText = findViewById(R.id.telefono);
        mensajeText = findViewById(R.id.mensaje);

    }

    /**
     * Evento para el botón de enviar, recoge los datos y los manda a la aplicación por defecto de SMS.
     * @param view
     */
    public void enviarBtnOnClick(View view){

        String telefono = telefonoText.getText().toString();
        String mensaje = mensajeText.getText().toString();

        boolean error = false;
        String errores = "";

        if (telefono.equals("")){
            error = true;
            errores = "Falta insertar el teléfono.\n";
        }
        if (mensaje.equals("")){
            error = true;
            errores += "Falta insertar el mensaje.";
        }

        if (!error){

            enviarMensaje(telefono, mensaje);
        }else{
            Toast.makeText(this, errores, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Se le da los datos necesarios para enviar a la aplicación de SMS
     * @param numero Número de telefono de destino.
     * @param mensaje Mensaje que se quiere enviar.
     */
    private void enviarMensaje(String numero, String mensaje){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + numero));
        intent.putExtra("sms_body", mensaje);
        startActivity(intent);
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
            Intent intent = new Intent(this, CartasActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.contacto) {

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
