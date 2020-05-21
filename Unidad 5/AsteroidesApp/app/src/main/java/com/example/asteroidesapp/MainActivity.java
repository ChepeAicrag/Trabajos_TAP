package com.example.asteroidesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private Button bAcercaDe;
    private Button bSalir;
    private Button btnPreferencias;
    public static AlmacenPuntuaciones almacen = new AlmacenPuntuacionesArray();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bAcercaDe = findViewById(R.id.btn3);
        bSalir = findViewById(R.id.btn4);
        btnPreferencias = findViewById(R.id.btn2);
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

}
