package com.example.asteroidesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.widget.Toast.LENGTH_LONG;

public class Juego extends Activity {

    private VistaJuego vistaJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        vistaJuego = (VistaJuego) findViewById(R.id.VistaJuego);
        vistaJuego.setPadre(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        vistaJuego.getThread().pausar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vistaJuego.getThread().reanudar();
    }

    @Override
    protected void onDestroy() {
        vistaJuego.getThread().detener();
        super.onDestroy();
    }

    public void mostrarMensaje(String txt){
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("mensaje",txt);
        setResult(Activity.RESULT_OK, i);
        finish();
    }

}
