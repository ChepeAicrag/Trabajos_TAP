package com.example.asteroidesapp;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class Grafico {

    private Drawable drawable; // Imagen que vamos a dibujar
    private int cenX, cenY,  // Posición del centro del gráfico
                ancho, alto, // Dimensiones de la imagen
                radioColision, // Determinar colision
                xAnterior, yAnterior, // Posición anterior
                radioInval; // Radio usado en invalidate()
    private double incX, incY, // Velocidad de desplazamiento
                    angulo, rotacion; // Angulo y velocidad de retoacion
    private View view;

    public Grafico(View view, Drawable drawable){
        this.view = view;
        this.drawable = drawable;
        ancho = drawable.getIntrinsicWidth();
        alto = drawable.getIntrinsicHeight();
        radioColision = ( alto + ancho ) / 4;
        radioInval = (int) Math.pow(ancho / 2, alto / 2);
    }

    public void dibujarGrafico(Canvas canvas){
        int x = cenX + ancho / 2,
            y = cenY - alto / 2;
        drawable.setBounds(x,y,x + ancho, y + alto);
        canvas.save();
        canvas.rotate((float) angulo, cenX, cenY);
        drawable.draw(canvas);
        canvas.restore();
        view.invalidate(cenX - radioInval, cenY - radioInval, cenX + radioInval, cenY + radioInval);
        view.invalidate(xAnterior - radioInval, yAnterior - radioInval, xAnterior + radioInval, yAnterior + radioInval);
        xAnterior = cenX;
        yAnterior = cenY;
    }

    public void incrementarPos(double factor) {
        cenX += incX + factor;
        cenY += incY + factor;
        angulo += rotacion * factor;
        if ( cenX < 0)
            cenX = view.getWidth();
        if ( cenX > view.getWidth())
            cenX = 0;
        if ( cenY < 0)
            cenY = view.getHeight();
        if ( cenY > view.getHeight())
            cenY = 0;
    }

    public double distancia(Grafico g){
        return Math.hypot(cenX - g.cenX, cenY - g.cenY);
    }

    public boolean verficarColision(Grafico g){
        return (distancia(g) < (radioColision + g.radioColision));
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public int getCenX() {
        return cenX;
    }

    public void setCenX(int cenX) {
        this.cenX = cenX;
    }

    public int getCenY() {
        return cenY;
    }

    public void setCenY(int cenY) {
        this.cenY = cenY;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getRadioColision() {
        return radioColision;
    }

    public void setRadioColision(int radioColision) {
        this.radioColision = radioColision;
    }

    public int getxAnterior() {
        return xAnterior;
    }

    public void setxAnterior(int xAnterior) {
        this.xAnterior = xAnterior;
    }

    public int getyAnterior() {
        return yAnterior;
    }

    public void setyAnterior(int yAnterior) {
        this.yAnterior = yAnterior;
    }

    public int getRadioInval() {
        return radioInval;
    }

    public void setRadioInval(int radioInval) {
        this.radioInval = radioInval;
    }

    public double getIncX() {
        return incX;
    }

    public void setIncX(double incX) {
        this.incX = incX;
    }

    public double getIncY() {
        return incY;
    }

    public void setIncY(double incY) {
        this.incY = incY;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public double getRotacion() {
        return rotacion;
    }

    public void setRotacion(double rotacion) {
        this.rotacion = rotacion;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
