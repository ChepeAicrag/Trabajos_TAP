package com.example.asteroidesapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

public class Puntuaciones extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntuaciones);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.elemento_lista,R.id.titulo,
                MainActivity.almacen.listaPuntuaciones(10)));
    }

}
