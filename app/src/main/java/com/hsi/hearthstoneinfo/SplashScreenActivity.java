package com.hsi.hearthstoneinfo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long TIEMPO_VENTANA = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Configurar para que esté en horizontal
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Ocultar barra de título
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash_screen);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //Inicio de la siguiente actividad
                Intent intentPrincipal = new Intent()
                        .setClass(SplashScreenActivity.this, MainActivity.class);
                startActivity(intentPrincipal);
            }

            //Cerramos la actividad del splash para que el usuario no vuelva a esta actividad dandole atrás

            //finish();
        };

        Timer timer = new Timer();
        timer.schedule(task, TIEMPO_VENTANA);

    }
}
