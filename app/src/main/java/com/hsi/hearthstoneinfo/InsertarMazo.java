package com.hsi.hearthstoneinfo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hsi.hearthstoneinfo.BD.ConnSQLiteHelper;

public class InsertarMazo extends AppCompatActivity {

    EditText insertarEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_mazo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        insertarEditText = findViewById(R.id.insertarEditText);

    }

    public void onInsertarButtonAction(View view){

        ConnSQLiteHelper c = new ConnSQLiteHelper(this);
        Toast.makeText(this, insertarEditText.getText().toString(), Toast.LENGTH_SHORT).show();
        //c.insertarMazo(insertarEditText.getText().toString());

    }


}
