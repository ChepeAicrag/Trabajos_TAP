package com.example.asteroidesapp;

import java.util.Vector;

public interface AlmacenPuntuaciones {
    public void guardarPuntuacion(int puntos, String nombre, long fech);
    public Vector<String> listaPuntuaciones(int cantidad);

}
