package itoaxaca.edu.hilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText entrada;
    private TextView salida;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada = findViewById(R.id.entrada);
        salida = findViewById(R.id.salida);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularOperacion(v);
            }
        });
    }

    /* Sin hilos
    public void calcularOperacion(View v){
        int n = Integer.parseInt(entrada.getText().toString()),
            res = factorial(n);
        salida.append(n + "! = " + res + "\n");
    }*/

    /** Con hilos */
    public void calcularOperacion(View v){
        int n = Integer.parseInt(entrada.getText().toString());
        salida.append("\n Calculando >>>>> ");
        new MiThread(n).start();
    }

    public int factorial(int n){
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
            SystemClock.sleep(1000);
        }
        return res;
    }

    class MiThread extends Thread{
        private int n, res;

        public MiThread(int n){
            this.n = n;
        }

        @Override
        public void run() {
            res = factorial(n);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    salida.append(n + "! = " + res + "\n");
                }
            });
        }
    }
}
