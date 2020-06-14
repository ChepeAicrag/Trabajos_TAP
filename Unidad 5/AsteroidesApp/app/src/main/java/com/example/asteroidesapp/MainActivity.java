package com.example.asteroidesapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private Button btnAcercaDe, btnPuntuacion, btnPreferencias, btnJugar, btnSalir;
    private TextView tituloApp;
    private Animation giro, aparecer, desplazamiento;
    public static AlmacenPuntuaciones almacen = new AlmacenPuntuacionesArray();
    private MediaPlayer mp;
    private  String rotacion;
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
                lanzarPuntuaciones(v);
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
        giro = AnimationUtils.loadAnimation(this, R.anim.giro_con_zoom);
        aparecer = AnimationUtils.loadAnimation(this, R.anim.aparecer);
        desplazamiento = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_derecha);
        tituloApp.startAnimation(giro);
        btnJugar.startAnimation(aparecer);
        btnPreferencias.startAnimation(desplazamiento);
        mp = MediaPlayer.create(this,R.raw.intro);
        rotacion = getRotation(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(rotacion.equals("vertical"))
            mp.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.pause();
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
        //startActivity(i);
        startActivityForResult(i,1);
    }

    public void mostrarMensaje(String txt){
        Toast.makeText(this,txt,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String mensaje = data.getStringExtra("mensaje");
                mostrarMensaje(mensaje);
            }
        }
    }

    /** Para comprobar en que sentido se encuentra la pantalla*/
    public String getRotation(Context context){
        final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
        switch (rotation) {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                return "vertical";
            case Surface.ROTATION_90:
            default:
                return "horizontal";
        }
    }
}
