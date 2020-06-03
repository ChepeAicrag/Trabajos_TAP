package com.example.asteroidesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;
import java.util.Vector;

public class VistaJuego extends View implements SensorEventListener {

    private Vector<Grafico> asteroides; // Vector con los asteroides;
    private int numAsteroides = 5,
            numFragmentos = 3,
            giroNave; // Incremento de direccion de la nave
    private double aceleracionNave; // Incremento de velocidad de la nave
    private Grafico nave;
    private static final int MAX_VELOCIDAD_NAVE = 20, // Velocidad maxima de la nave
                             PASO_GIRO_NAVE = 5,
                             PERIODO_PROCESO = 50, // Para mover asteroide
                             PASO_VELOCIDAD_MISIL = 12;
    private static final float PASO_ACELERACION_NAVE = 0.5f;
    // HILOS PARA MOVER LOS ASTEROIDES
    private ThreadJuego thread = new ThreadJuego();
    private long ultimoProceso = 0;
    // PARA MOVER LA NAVE CON TOUCH
    private float mX = 0, mY = 0;
    private boolean disparo = false;
    // Manejor Sensores
    private boolean hayValorInicial;
    private float valorInicial;
    // MISIL
    //private Grafico misil;
    private Vector<Grafico> misiles;

    //private boolean misilActivo = false;
    //private int tiempoMisil;
    private Vector<Integer> tiemposMisiles;
    private  Drawable drawableNave, drawableAsteroide, drawableMisil;
    public VistaJuego(Context context, AttributeSet attrs){
        super(context,attrs);

        /** Asteroide con grafico */
        //drawableAsteroide = context.getResources().getDrawable(R.drawable.asteroide1);

        // Registro del sensor
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> listSensors = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if(!listSensors.isEmpty()){
            Sensor orientationSensor = listSensors.get(0);
            // Falta completar esta parte xd
            sensorManager.registerListener(this,orientationSensor,SensorManager.SENSOR_DELAY_GAME);
        }

        /** Asteroide, misil y nave con grafico vectorial */
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
            /**
            * Fin para el asteroide
            * Inicio para la nave
            * */
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
            /**
             * Fin para la nave
             * Inicio para el misil
             * */
            ShapeDrawable dMisil = new ShapeDrawable(new RectShape());
            dMisil.getPaint().setColor(Color.WHITE);
            dMisil.getPaint().setStyle(Paint.Style.STROKE);
            dMisil.setIntrinsicWidth(15);
            dMisil.setIntrinsicHeight(3);
            drawableMisil = dMisil;
            /** Fin para el dibujo de misil en forma vectorial*/
            setBackgroundColor(Color.BLACK);
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }else{
            drawableAsteroide = context.getResources().getDrawable(R.drawable.asteroide1);
            drawableNave = context.getResources().getDrawable(R.drawable.nave);
            drawableMisil = context.getResources().getDrawable(R.drawable.misil1);
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
        ultimoProceso = System.currentTimeMillis();
        thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        synchronized (asteroides){
        for ( Grafico asteroide : asteroides){
                asteroide.dibujarGrafico(canvas);
            }
        }
        nave.dibujarGrafico(canvas);
        /*
        if (misilActivo)
            misil.dibujarGrafico(canvas);
        */
        for (Grafico misil : misiles ){
            misil.dibujarGrafico(canvas);
        }
    }

    @Override
    public boolean onKeyDown(int codigoTecla, KeyEvent evento) {
        super.onKeyDown(codigoTecla,evento);
        boolean procesada = false;
        switch (codigoTecla){
            case KeyEvent.KEYCODE_DPAD_UP:
                aceleracionNave = +PASO_ACELERACION_NAVE;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                giroNave = -PASO_GIRO_NAVE;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                giroNave = +PASO_GIRO_NAVE;
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                activaMisil();
            default:
                procesada = false;
                break;
        }
        return procesada;
    }

    @Override
    public boolean onKeyUp(int codigoTecla, KeyEvent evento) {
        super.onKeyDown(codigoTecla,evento);
        boolean procesada = false;
        switch (codigoTecla){
            case KeyEvent.KEYCODE_DPAD_UP:
                aceleracionNave = 0;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                giroNave = 0;
                break;
            default:
                procesada = false;
                break;
        }
        return procesada;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        float x = event.getX(), y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                disparo = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - mX),
                      dy = Math.abs(y - mY);
                if (dy < 6 && dx > 6) {
                    giroNave = Math.round(x - mX) / 2;
                    disparo = false;
                }else if (dx < 6 && dy > 6){
                    aceleracionNave = Math.round(mY - y) / 25;
                    disparo = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                giroNave = 0;
                aceleracionNave = 0;
                if (disparo)
                    activaMisil();
                break;
        }
        mX = x;
        mY = y;
        return true;
    }

    public void activaMisil() {
        Grafico misil = new Grafico(this, drawableMisil);
        misil.setCenX(nave.getCenX());
        misil.setCenY(nave.getCenY());
        misil.setAngulo(nave.getAngulo());
        misil.setIncX(Math.cos(Math.toRadians(misil.getAngulo())) * PASO_VELOCIDAD_MISIL);
        misil.setIncY(Math.sin(Math.toRadians(misil.getAngulo())));
        misiles.add(misil);
        tiemposMisiles.add((int) Math.min(this.getWidth() / Math.abs(misil.getIncX()),
                                    this.getHeight() / Math.abs(misil.getIncY())) - 2);
        //misilActivo = true;
    }

    public void actualizaFisica(){
        long ahora = System.currentTimeMillis();
        if (ultimoProceso + PERIODO_PROCESO > ahora){
            return;// Salir si el periodo no se ha compulido
        }

        // Para ejecucion en tiempo real enecesitamos el retardo
        double retardo = (ahora - ultimoProceso) / PERIODO_PROCESO;
        ultimoProceso = ahora;

        // Actualizar velocidaad y direccion de la nave
        nave.setAngulo((int) (nave.getAngulo() + giroNave + retardo));

        double nIncX = nave.getIncX() + aceleracionNave * Math.cos(Math.toRadians(nave.getAngulo())),
               nIncY = nave.getIncY() + aceleracionNave * Math.sin(Math.toRadians(nave.getAngulo()));
        if(Math.hypot(nIncX,nIncY) <= MAX_VELOCIDAD_NAVE){
            nave.setIncX(nIncX);
            nave.setIncY(nIncY);
        }
        nave.incrementarPos(retardo); // Actualizar la posición
        for (Grafico asteroide : asteroides)
            asteroide.incrementarPos(retardo);

        // Actualizar la posicion del misil
        /*
        if (misilActivo){
            misil.incrementarPos(retardo);
            tiempoMisil -= retardo;
            if (tiempoMisil < 0)
                misilActivo = false;
            else
                for (int i = 0; i < asteroides.size(); i++)
                    if (misil.verficarColision(asteroides.get(i))){
                        destruyeAsteroide(i);
                        break;
                    }
        }*/
        for(int m = 0; m < misiles.size(); m++){
            misiles.elementAt(m).incrementarPos(retardo);
            tiemposMisiles.set(m, tiemposMisiles.get(m) - 1);
            if (tiemposMisiles.get(m) >= 0)
                for (int i = 0; i < asteroides.size(); i++)
                    if (misiles.elementAt(i).verficarColision(asteroides.get(i))){
                        destruyeAsteroide(i);
                        break;
                    }
        }
    }

    private void destruyeAsteroide(int i){
        synchronized (asteroides){
            asteroides.remove(i);
            //misilActivo = false;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float valor = event.values[1];
        if (!hayValorInicial){
            valorInicial = valor;
            hayValorInicial = true;
        }
        giroNave = (int) (valor - valorInicial / 3);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        hayValorInicial = false;
        valorInicial = 0;
    }

    class ThreadJuego extends Thread {

        @Override
        public void run() {
            while (true){
                actualizaFisica();
            }
        }
    }

}
