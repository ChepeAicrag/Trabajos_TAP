package com.example.asteroidesapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import java.util.Vector;

public class VistaJuego extends View {

    private Vector<Grafico> asteroides; // Vector con los asteroides;
    private int numAsteroides = 5,
                numFragmentos = 3,
                giroNave; // Incremento de direccion de la nave
    private double aceleracionNave; // Incremento de velocidad de la nave
    private Grafico nave;
    private static final int MAX_VELOCIDAD_NAVE = 20, // Velocidad maxima de la nave
                             PASO_GIRO_NAVE = 5;
    private static final float PASO_ACELERACION_NAVE = 0.5f;

    public VistaJuego(Context context, AttributeSet attrs){
        super(context,attrs);
        Drawable drawableNave, drawableAsteroide, drawableMisil;
        drawableAsteroide = context.getResources().getDrawable(R.drawable.asteroide1);
        drawableNave = context.getResources().getDrawable(R.drawable.nave);
        nave = new Grafico(this,drawableNave);

        asteroides = new Vector<Grafico>();
        for ( int i = 0; i < numAsteroides; i++ ){
            Grafico asteroide = new Grafico(this,drawableAsteroide);
            asteroide.setIncY(Math.random() * 4 - 2);
            asteroide.setIncX(Math.random() * 4 - 2);
            asteroide.setAngulo((int) Math.random() * 360);
            asteroide.setRotacion((int) Math.random() * 8 -4);
            asteroides.add(asteroide);
        }
    }

    @Override
    protected void onSizeChanged(int ancho, int alto, int ancho_anter, int alto_anter) {
        super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);
        nave.setCenX(ancho);
        nave.setCenY(alto);
        for ( Grafico asteroide : asteroides ){
            do {
                asteroide.setCenX((int) Math.random() * ancho);
                asteroide.setCenY((int) Math.random() * alto);
            }while (asteroide.distancia(nave) < (ancho + alto) / 5);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for ( Grafico asteroide : asteroides){
            asteroide.dibujarGrafico(canvas);
        }
        nave.dibujarGrafico(canvas);
    }
}
