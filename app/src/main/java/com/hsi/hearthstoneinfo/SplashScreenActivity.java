package com.hsi.hearthstoneinfo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.hsi.hearthstoneinfo.BD.ConnSQLiteHelper;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long TIEMPO_VENTANA = 3000;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Ejecucion del sonido de inicio cuando se abre la aplicacion
        mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.intro);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                mediaPlayer.start();
            }
        });


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
                finish();
            }

            //Cerramos la actividad del splash para que el usuario no vuelva a esta actividad dandole atrás

            //finish();
        };

        Timer timer = new Timer();
        timer.schedule(task, TIEMPO_VENTANA);

    }
}
