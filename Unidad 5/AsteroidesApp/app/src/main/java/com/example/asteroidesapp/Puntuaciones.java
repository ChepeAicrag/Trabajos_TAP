package com.example.asteroidesapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Puntuaciones extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntuaciones);
        /*
        setListAdapter(new ArrayAdapter<String>(this,R.layout.elemento_lista,R.id.titulo,
                MainActivity.almacen.listaPuntuaciones(10)));
        */
        setListAdapter(new MiAdaptador(this,MainActivity.almacen.listaPuntuaciones(10)));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Object o = getListAdapter().getItem(position);
        Toast.makeText(this,"Seleccion: " + Integer.toString(position) + " - " + o.toString(),
                Toast.LENGTH_LONG).show();
    }
}
