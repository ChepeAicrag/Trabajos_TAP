package com.example.asteroidesapp;

import android.app.Activity;
import android.app.TaskInfo;
import android.content.Context;
import android.content.Intent;
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
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;
import java.util.prefs.Preferences;

public class VistaJuego extends View{///implements SensorEventListener {

    private Vector<Grafico> asteroides, // Vector con los asteroides;
            misiles; // Vector con los misiles
    private int numAsteroides = 2,
            numFragmentos = 3,
            numMisiles = 10,
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
    private Vector<Integer> tiemposMisiles;
    private  Drawable drawableNave, drawableAsteroide[], drawableMisil;
    private SoundPool soundPool;
    int idDisparo, idExplosion;
    MediaPlayer mp, mp_Final, mp_Terminar;
    private int puntuacion = 0;
    private Juego padre;
    private boolean musica;

    public VistaJuego(Context context, AttributeSet attrs) {
        super(context, attrs);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        numFragmentos = Integer.valueOf(pref.getString("fragmentos", "3"));
        musica = pref.getBoolean("musica", true);
        drawableAsteroide = new Drawable[3];
        // Registro del sensor
        /*SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> listSensors = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if (!listSensors.isEmpty()) {
            Sensor orientationSensor = listSensors.get(0);
            sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_GAME);
        }*/
        /** Asteroide, misil y nave con grafico vectorial */
        if ((pref.getString("graficos", "1")).equals("0")) {
            /** Inicio para el asteroide */
            Path pathAsteroide = new Path();
            pathAsteroide.moveTo((float) 0.3, (float) 0.0);
            pathAsteroide.lineTo((float) 0.6, (float) 0.0);
            pathAsteroide.lineTo((float) 0.6, (float) 0.3);
            pathAsteroide.lineTo((float) 0.8, (float) 0.2);
            pathAsteroide.lineTo((float) 1.0, (float) 0.4);
            pathAsteroide.lineTo((float) 0.8, (float) 0.6);
            pathAsteroide.lineTo((float) 0.9, (float) 0.9);
            pathAsteroide.lineTo((float) 0.8, (float) 1.0);
            pathAsteroide.lineTo((float) 0.4, (float) 1.0);
            pathAsteroide.lineTo((float) 0.0, (float) 0.6);
            pathAsteroide.lineTo((float) 0.0, (float) 0.2);
            pathAsteroide.lineTo((float) 0.8, (float) 1.0);
            pathAsteroide.lineTo((float) 0.3, (float) 0.0);
            for (int i = 0; i < 3; i++) {
                ShapeDrawable dAsteroide = new ShapeDrawable(new PathShape(pathAsteroide, 1, 1));
                dAsteroide.getPaint().setColor(Color.WHITE);
                dAsteroide.getPaint().setStyle(Paint.Style.STROKE);
                dAsteroide.setIntrinsicWidth(50 - i * 14);
                dAsteroide.setIntrinsicHeight(50 - i * 14);
                drawableAsteroide[i] = dAsteroide;
            }
            /**
             * Fin para el asteroide
             * Inicio para la nave
             * */
            Path pathNave = new Path();
            pathNave.moveTo((float) 0.0, (float) 0.0);
            pathNave.lineTo((float) 1.0, (float) 0.5);
            pathNave.lineTo((float) 0.0, (float) 1.0);
            pathNave.lineTo((float) 0.0, (float) 0.0);
            ShapeDrawable dNave = new ShapeDrawable(new PathShape(pathNave, 1, 1));
            dNave.getPaint().setColor(Color.WHITE);
            dNave.getPaint().setStyle(Paint.Style.STROKE);
            dNave.setIntrinsicHeight(15);
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
            //setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        } else {
            drawableAsteroide[0] = context.getResources().getDrawable(R.drawable.asteroide1);
            drawableAsteroide[1] = context.getResources().getDrawable(R.drawable.asteroide2);
            drawableAsteroide[2] = context.getResources().getDrawable(R.drawable.asteroide3);
            drawableNave = context.getResources().getDrawable(R.drawable.nave);
            drawableMisil = context.getResources().getDrawable(R.drawable.misil1);
            //setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        /** ----- */
        nave = new Grafico(this, drawableNave);
        asteroides = new Vector<Grafico>();
        misiles = new Vector<Grafico>();
        tiemposMisiles = new Vector<Integer>();
        for (int i = 0; i < numAsteroides; i++) {
            Grafico asteroide = new Grafico(this, drawableAsteroide[0]);
            asteroide.setIncY(Math.random() * 4 - 2);
            asteroide.setIncX(Math.random() * 4 - 2);
            asteroide.setAngulo((int) (Math.random() * 360));
            asteroide.setRotacion((int) (Math.random() * 8 - 4));
            asteroides.add(asteroide);
        }
        if(musica && soundPool == null){
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
            idDisparo = soundPool.load(context, R.raw.disparo, 0);
            idExplosion = soundPool.load(context, R.raw.explosion, 0);
            mp = MediaPlayer.create(context,R.raw.audio);
            mp_Final = MediaPlayer.create(context,R.raw.muerte);
            mp_Terminar = MediaPlayer.create(context,R.raw.ganador);
            mp.start();
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
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            for ( Grafico asteroide : asteroides){
                asteroide.dibujarGrafico(canvas);
            }
        nave.dibujarGrafico(canvas);
            for (Grafico misil : misiles ){
                misil.dibujarGrafico(canvas);
            }
         this.invalidate();
    }

    @Override
    public boolean onKeyDown(int codigoTecla, KeyEvent evento) {
        super.onKeyDown(codigoTecla,evento);
        boolean procesada = true;
        switch (codigoTecla){
            case KeyEvent.KEYCODE_DPAD_UP:
                aceleracionNave += PASO_ACELERACION_NAVE;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                giroNave -= PASO_GIRO_NAVE;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                giroNave += PASO_GIRO_NAVE;
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                activaMisil();
                break;
            default:
                procesada = false;
                break;
        }
        return procesada;
    }

    @Override
    public boolean onKeyUp(int codigoTecla, KeyEvent evento) {
        super.onKeyDown(codigoTecla,evento);
        boolean procesada = true;
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
            default:
                break;
        }
        mX = x;
        mY = y;
        return true;
    }

    public void activaMisil() {
        if(numMisiles < misiles.size())
            return;
        misiles.add(new Grafico(this, drawableMisil));
        Grafico misil = misiles.get(misiles.size()-1);
        misil.setCenX(nave.getCenX());
        misil.setCenY(nave.getCenY());
        misil.setAngulo(nave.getAngulo());
        misil.setIncX(Math.cos(Math.toRadians(misil.getAngulo())) * PASO_VELOCIDAD_MISIL);
        misil.setIncY(Math.sin(Math.toRadians(misil.getAngulo())) * PASO_VELOCIDAD_MISIL);
        Double d = Math.min(this.getWidth() / Math.abs(misil.getIncX()),
                this.getHeight() / Math.abs(misil.getIncY()))- 2;
        Integer t = d.intValue();
        tiemposMisiles.add(t);
        if(soundPool != null)
            soundPool.play(idDisparo,1,1,1,0,1);
    }

    synchronized public void actualizaFisica(){
        long ahora = System.currentTimeMillis();
        if (ultimoProceso + PERIODO_PROCESO > ahora)
            return;// Salir si el periodo no se ha compulido
        // Para ejecucion en tiempo real enecesitamos el retardo
        double retardo = (ahora - ultimoProceso) / PERIODO_PROCESO;
        ultimoProceso = ahora;
        // Actualizar velocidaad y direccion de la nave
        nave.setAngulo((int) (nave.getAngulo() + giroNave * retardo));
        double nIncX = nave.getIncX() + aceleracionNave * Math.cos(Math.toRadians(nave.getAngulo()) * retardo),
                nIncY = nave.getIncY() + aceleracionNave * Math.sin(Math.toRadians(nave.getAngulo()) * retardo);
        if(Math.hypot(nIncX,nIncY) <= MAX_VELOCIDAD_NAVE){
            nave.setIncX(nIncX);
            nave.setIncY(nIncY);
        }
        nave.incrementarPos(retardo); // Actualizar la posición
        for (Grafico asteroide : asteroides){
            asteroide.incrementarPos(retardo);
        //    if(nave.verficarColision(asteroide)){
          //      ((Juego)(padre)).terminar();
            //}
        }
        if(!misiles.isEmpty())
            for(int m = 0; m < misiles.size(); m++){
                misiles.elementAt(m).incrementarPos(retardo);
                tiemposMisiles.set(m, tiemposMisiles.get(m) - (int) retardo);
                if (tiemposMisiles.get(m) >= 0){
                    for (int i = 0; i < asteroides.size(); i++)
                        if (misiles.elementAt(m).verficarColision(asteroides.elementAt(i))){
                            destruyeAsteroide(i);
                            destruyeMisil(m);
                            break;
                        }
                }else
                   destruyeMisil(m);
            }
        for (Grafico asteroide : asteroides){
            if (asteroide.verficarColision(nave)){
                try {
                    if(musica){
                        mp.pause();
                        mp_Final.start();
                        while (!mp_Final.isPlaying()){}
                    }
                    salir("Haz sido destruido!!!");
                }catch (Exception e){e.printStackTrace();}
            }
        }
    }

    private void destruyeAsteroide(int i){
        int tam;
        //if (asteroides.get(i).getDrawable() == drawableAsteroide[2])
        //   asteroides.remove(i);
        if (asteroides.get(i).getDrawable() == drawableAsteroide[0]){
            if (asteroides.get(i).getDrawable() == drawableAsteroide[1])
                tam = 2;
            else
                tam = 1;
            for (int n = 0; n < numFragmentos; n++) {
                Grafico asteroide = new Grafico(this, drawableAsteroide[tam]);
                asteroide.setCenX(asteroides.get(i).getCenX());
                asteroide.setCenY(asteroides.get(i).getCenY());
                asteroide.setIncX(Math.random() * 7 - 2 - tam);
                asteroide.setIncY(Math.random() * 7 - 2 - tam);
                asteroide.setAngulo((int) (Math.random() * 360));
                asteroide.setRotacion((Math.random() * 8 - 4));
                asteroides.add(asteroide);
            }
        }
            asteroides.removeElementAt(i);
        /** En teoria si ya no hay graficos de asteroides en el array, se termina el juego*/
        if (asteroides.isEmpty()){
            if(musica){
                mp.pause();
                mp_Terminar.start();
                while(!mp_Terminar.isPlaying()){} // Hasta que se termine reproducir
            }
            salir("¡¡¡FELICIDADES!!!");
        }
        if (soundPool != null)
            soundPool.play(idExplosion, 1, 1, 0, 0, 1);
        puntuacion += 100;
    }

    /** Identificar en que actividad se mostrará la vista y así poder terminar esta junto con la vista*/
    public void setPadre(Juego padre) {
        this.padre = padre;
    }

    private void salir(String msj){
        try {
            finalize();
            padre.mostrarMensaje(msj);
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void destruyeMisil(int i){
            misiles.removeElementAt(i);
            tiemposMisiles.removeElementAt(i);
    }
/*
    @Override
    public void onSensorChanged(SensorEvent event) {
        float valor = event.values[1];
        if (!hayValorInicial){
            valorInicial = valor;
            hayValorInicial = true;
        }
        giroNave = (int) (valor - valorInicial) / 3;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        hayValorInicial = false;
    }
*/
    public ThreadJuego getThread(){
        return thread;
    }

    class ThreadJuego extends Thread {

        private boolean pausa, corriendo;

        @Override
        public void run() {
            corriendo = true;
            while (corriendo){
                actualizaFisica();
                synchronized (this){
                    while (pausa){
                        try {
                            wait();
                        }catch (Exception e){e.printStackTrace();}
                    }
                }
            }
        }

        public synchronized void pausar(){
            pausa = true;
        }

        public synchronized void reanudar(){
            pausa = false;
            notify();
        }

        public void detener(){
            corriendo = false;
            if (pausa)
                reanudar();
        }
    }

}
