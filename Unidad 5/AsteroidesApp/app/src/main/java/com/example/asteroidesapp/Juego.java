package com.example.asteroidesapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class Juego extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
    }
}
