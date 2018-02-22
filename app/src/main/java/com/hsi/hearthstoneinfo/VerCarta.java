package com.hsi.hearthstoneinfo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hsi.hearthstoneinfo.BD.ConnSQLiteHelper;
import com.hsi.hearthstoneinfo.Entidades.Carta;

public class VerCarta extends AppCompatActivity {

    Carta carta;

    TextView nombreTextView;
    TextView vidaTextView;
    TextView ataqueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombreTextView = findViewById(R.id.nombreCartaTextView);
        vidaTextView = findViewById(R.id.vidaCartaTextView);
        ataqueTextView = findViewById(R.id.ataqueCartaTextView);

        carta = (Carta) getIntent().getExtras().getSerializable("carta");

        rellenarCarta(carta);
    }


    public void rellenarCarta(Carta c){

        nombreTextView.setText(c.getNombre());
        vidaTextView.setText(String.valueOf(c.getVida()));
        ataqueTextView.setText(String.valueOf(c.getAtaque()));

    }

    public void onEliminarButtonAction(View view){

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        c.eliminarCarta(carta.getId_mazo(), carta.getNombre());
        finish();
    }

}
