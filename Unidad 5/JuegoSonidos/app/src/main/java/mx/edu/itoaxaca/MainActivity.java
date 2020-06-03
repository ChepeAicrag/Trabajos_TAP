package mx.edu.itoaxaca;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaExtractor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.System.exit;
import static java.lang.System.lineSeparator;

public class MainActivity extends AppCompatActivity {

    // Variables
    private MediaPlayer reprod; // Sonido continuo
    private TextView frase; // Frases a formar

        /** Sujeto */
        private ImageButton ibt; // boton c/imagen tigre
        private ImageButton ibl; // boton c/imagen leon
        private ImageButton ibn; // boton c/imagen niño
        /** Verbo */
        private ImageButton ibd; // boton c/imagen dormir
        private ImageButton ibr; // boton c/imagen correr (Run)
        private ImageButton ibc; // boton c/imagen comer
        /** Lugar */
        private ImageButton ibs; // boton c/imagen selva
        private ImageButton ibca; // boton c/imagen casa
        private ImageButton ibcam; // boton c/imagen cama

        private MediaPlayer sujeto, verbo, lugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Asignación de las references
        frase = findViewById(R.id.tFrase);
        /** Sujeto */
        ibt = findViewById(R.id.bTigre);
        ibl = findViewById(R.id.bLeon);
        ibn = findViewById(R.id.bNino);
        /** Verbo*/
        ibd = findViewById(R.id.bDormir);
        ibr = findViewById(R.id.bCorrer);
        ibc = findViewById(R.id.bComer);
        /** Lugar*/
        ibs = findViewById(R.id.bSelva);
        ibca = findViewById(R.id.bCasa);
        ibcam = findViewById(R.id.bCama);
    }

    public void fraseAudible(View v){
        switch (v.getId()){
            case R.id.bNino:
                frase.setText("");
                accionReproducir("El niño",1, R.raw.elninio);
                break;
            case R.id.bLeon:
                frase.setText("");
                accionReproducir("El león", 1, R.raw.elleon);
                break;
            case R.id.bTigre:
                frase.setText("");
                accionReproducir("El tigre", 1, R.raw.eltigre);
                break;
            case R.id.bDormir:
                accionVerbo(" está durmiendo", R.raw.durmiendo);
                break;
            case R.id.bComer:
                accionVerbo(" está comiendo", R.raw.comiendo);
                break;
            case R.id.bCorrer:
                accionVerbo(" está corriendo", R.raw.corriendo);
                break;
            case R.id.bCasa:
                accionLugar(" en la casa", R.raw.casa);
                break;
            case R.id.bSelva:
                accionLugar(" en la selva", R.raw.selva);
                break;
            case R.id.bCama:
                accionLugar(" en la cama", R.raw.cama);
                break;
        }
    }

    private void accionVerbo(String texto, int recurso){
        if(seleccionSujeto())
            mostrarToast("Seleccione un elemento del reglón sujeto");
        else
            accionReproducir(texto, 2, recurso);
    }

    private void accionLugar(String texto, int recurso){
        if (seleccionVerbo())
            mostrarToast("Seleccione un elemento del renglón verbo");
        else
            accionReproducir(texto,3, recurso);
    }

    private void accionReproducir(String texto,int renglon, int recurso){
        if (validarProductor()){
            frase.append(texto);
            opcionRenglon(renglon,false);
            reprod = MediaPlayer.create(this,recurso);
            reprod.start();
            asignarReproductor(renglon);
        }
    }

    private void asignarReproductor(int renglon) {
        if(renglon == 1)
            sujeto = reprod;
        else if(renglon == 2)
            verbo = reprod;
        else
            lugar = reprod;
    }

    private void opcionRenglon(int i, boolean estado){
        if(i == 1) {
            ibt.setEnabled(estado);
            ibl.setEnabled(estado);
            ibn.setEnabled(estado);
        }else if (i == 2){
            ibd.setEnabled(estado);
            ibr.setEnabled(estado);
            ibc.setEnabled(estado);
        }else{
            ibs.setEnabled(estado);
            ibca.setEnabled(estado);
            ibcam.setEnabled(estado);
        }
    }

    private boolean validarProductor(){
        return reprod == null || !reprod.isPlaying();
    }

    private boolean seleccionSujeto(){
        return ibt.isEnabled() || ibl.isEnabled() || ibn.isEnabled();
    }

    private boolean seleccionVerbo(){
        return ibd.isEnabled() || ibr.isEnabled() || ibc.isEnabled();
    }

    private void mostrarToast(String msj){
        Toast.makeText(getApplicationContext(), msj, Toast.LENGTH_LONG).show();
    }

    public void restaurar(View v){
        frase.setText("____ FRASE ____");
        opcionRenglon(1,true);
        opcionRenglon(2,true);
        opcionRenglon(3,true);
    }

    public void salir(View v){
        exit(0);
    }

    public void repetir(View v){
           if (sujeto == null){
               mostrarToast("No ha seleccionado correctamente");
               return;
           }

           sujeto.start();
           while (sujeto.isPlaying()){}
                   verbo.start();
           while (verbo.isPlaying()){}
                   lugar.start();
    }
}
