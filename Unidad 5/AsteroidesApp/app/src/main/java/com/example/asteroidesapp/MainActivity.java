package com.example.asteroidesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private Button bAcercaDe,
                   bSalir,
                   btnPreferencias,
                   btnJugar;
    public static AlmacenPuntuaciones almacen = new AlmacenPuntuacionesArray();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bAcercaDe = findViewById(R.id.btn3);
        bSalir = findViewById(R.id.btn4);
        btnPreferencias = findViewById(R.id.btn2);
        btnJugar = findViewById(R.id.btn1);
        bAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarAcercaDe(v);
            }
        });
        bSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarPuntuaciones(null);
            }
        });
        btnPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarPreferences(v);
            }
        });
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarJuego(v);
            }
        });
        TextView tituloApp = findViewById(R.id.TituloApp);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.giro_con_zoom),
                  animation2 = AnimationUtils.loadAnimation(this,R.anim.aparecer);
        tituloApp.startAnimation(animation);
        btnJugar.startAnimation(animation2);
    }

    public void lanzarAcercaDe(View view){
        Intent i = new Intent(this,AcercaDe.class);
        startActivity(i);
    }

    public void lanzarPreferences(View view){
        Intent i = new Intent(this,Preferences.class);
        startActivity(i);
    }
    public void salirApp(View v) {
        finish();
    }

    public void lanzarPuntuaciones(View v){
        Intent i = new Intent(this,Puntuaciones.class);
        startActivity(i);
    }

    public void lanzarJuego(View v){
        Intent i = new Intent(this,Juego.class);
        startActivity(i);
    }
}
