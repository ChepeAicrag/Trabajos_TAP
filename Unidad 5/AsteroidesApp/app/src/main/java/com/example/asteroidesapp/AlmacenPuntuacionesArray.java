package com.example.asteroidesapp;

import java.util.Vector;

public class AlmacenPuntuacionesArray implements AlmacenPuntuaciones {

    private Vector<String> puntuaciones;

    public AlmacenPuntuacionesArray(){
        puntuaciones = new Vector<String>();
        puntuaciones.add("123080 Pepito Dominguez");
        puntuaciones.add("111000 Pedro Martinez");
        puntuaciones.add("011000 Paco Pérez");
    }

    @Override
    public void guardarPuntuacion(int puntos, String nombre, long fech) {
        puntuaciones.add(0,puntos+ " " + nombre);
    }

    @Override
    public Vector<String> listaPuntuaciones(int cantidad) {
        return puntuaciones;
    }
}
