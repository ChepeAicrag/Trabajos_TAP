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
    private Button btnAcercaDe, btnPuntuacion, btnPreferencias, btnJugar, btnSalir;
    private TextView tituloApp;
    private Animation giro, aparecer, desplazamiento;
    public static AlmacenPuntuaciones almacen = new AlmacenPuntuacionesArray();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnJugar = findViewById(R.id.btn1);
        btnPreferencias = findViewById(R.id.btn2);
        btnAcercaDe = findViewById(R.id.btn3);
        btnPuntuacion = findViewById(R.id.btn4);
        btnSalir = findViewById(R.id.btn5);
        btnAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarAcercaDe(v);
            }
        });
        btnPuntuacion.setOnClickListener(new View.OnClickListener() {
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
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirApp(v);
            }
        });
        tituloApp = findViewById(R.id.TituloApp);
        giro = AnimationUtils.loadAnimation(this,R.anim.giro_con_zoom);
        aparecer = AnimationUtils.loadAnimation(this,R.anim.aparecer);
        desplazamiento = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_derecha);
        tituloApp.startAnimation(giro);
        btnJugar.startAnimation(aparecer);
        btnPreferencias.startAnimation(desplazamiento);

    }

    public void lanzarAcercaDe(View view){
        btnAcercaDe.startAnimation(giro);
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
