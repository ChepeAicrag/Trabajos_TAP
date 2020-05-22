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
                numFragmentos = 3;

    public VistaJuego(Context context, AttributeSet attrs){
        super(context,attrs);
        Drawable drawableNave, drawableAsteroide, drawableMisil;
        drawableAsteroide = context.getResources().getDrawable(R.drawable.asteroide1);
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
        for ( Grafico asteroide : asteroides ){
            asteroide.setCenX((int) Math.random() * ancho);
            asteroide.setCenY((int) Math.random() * alto);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for ( Grafico asteroide : asteroides){
            asteroide.dibujarGrafico(canvas);
        }
    }
}
