package com.example.asteroidesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.preference.PreferenceManager;
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
        /** Asteroide con grafico */
        //drawableAsteroide = context.getResources().getDrawable(R.drawable.asteroide1);
        /** Asteroide con grafico vectorial */
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        if ((pref.getString("graficos","1")).equals("0")){
            /** Inicio para el asteroide */
            Path pathAsteroide = new Path();
            pathAsteroide.moveTo((float) 0.3,(float) 0.0);
            pathAsteroide.lineTo((float) 0.6,(float) 0.0);
            pathAsteroide.lineTo((float) 0.6,(float) 0.3);
            pathAsteroide.lineTo((float) 0.8,(float) 0.2);
            pathAsteroide.lineTo((float) 1.0,(float) 0.4);
            pathAsteroide.lineTo((float) 0.8,(float) 0.6);
            pathAsteroide.lineTo((float) 0.9,(float) 0.9);
            pathAsteroide.lineTo((float) 0.8,(float) 1.0);
            pathAsteroide.lineTo((float) 0.4,(float) 1.0);
            pathAsteroide.lineTo((float) 0.0,(float) 0.6);
            pathAsteroide.lineTo((float) 0.0,(float) 0.2);
            pathAsteroide.lineTo((float) 0.8,(float) 1.0);
            pathAsteroide.lineTo((float) 0.3,(float) 0.0);
            ShapeDrawable dAsteroide = new ShapeDrawable(new PathShape(pathAsteroide,1,1));
            dAsteroide.getPaint().setColor(Color.WHITE);
            dAsteroide.getPaint().setStyle(Paint.Style.STROKE);
            dAsteroide.setIntrinsicWidth(50);
            dAsteroide.setIntrinsicHeight(50);
            drawableAsteroide = dAsteroide;
            /** Fin para el asteroide */
            Path pathNave = new Path();
            pathNave.moveTo((float) 0.0, (float) 0.0);
            pathNave.lineTo((float) 1.0, (float) 0.5);
            pathNave.lineTo((float) 0.0, (float) 1.0);
            pathNave.lineTo((float) 0.0, (float) 0.0);
            ShapeDrawable dNave = new ShapeDrawable(new PathShape(pathNave,1,1));
            dNave.getPaint().setColor(Color.WHITE);
            dNave.getPaint().setStyle(Paint.Style.STROKE);
            dNave.setIntrinsicHeight(12);
            dNave.setIntrinsicWidth(20);
            drawableNave = dNave;
            setBackgroundColor(Color.BLACK);
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }else{
            drawableAsteroide = context.getResources().getDrawable(R.drawable.asteroide1);
            drawableNave = context.getResources().getDrawable(R.drawable.nave);
            setLayerType(View.LAYER_TYPE_HARDWARE,null);
        }
        /** ----- */
        //drawableNave = context.getResources().getDrawable(R.drawable.nave);
        drawableMisil = context.getResources().getDrawable(R.drawable.misil1);
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
        nave.setCenX(ancho / 2);
        nave.setCenY(alto / 2);
        for ( Grafico asteroide : asteroides ){
            do {
                asteroide.setCenX((int) (Math.random() * ancho));
                asteroide.setCenY((int) (Math.random() * alto));
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
