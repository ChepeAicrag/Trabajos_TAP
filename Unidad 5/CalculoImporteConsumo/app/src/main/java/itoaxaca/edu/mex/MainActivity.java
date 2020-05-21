package itoaxaca.edu.mex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText lecAnt; // Lectura Anterior
    private EditText lecAct; // LecturaActual
    private TextView consumo;
    private TextView importe;
    private final double V_IMPUESTO = 10; //10%
    private final double V_MANTENIMIENTO = 15.0; // 15 PESOS
    private final int PRECIO_MT3 = 7; // PRECIO EN $ POR METRO CUBICO
    private double vImporte;
    private double vConsumo;
    private double vImpuesto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lecAnt = findViewById(R.id.lecturaAnt);
        lecAct = findViewById(R.id.lecturaAct);
        consumo = findViewById(R.id.consumo);
        importe = findViewById(R.id.importe);
    }

    public void calculo(View v){
        // Operaciones para calcular el importe a pagar
        if(validarCampos()){
            int consumoMts3 = Integer.parseInt(lecAct.getText().toString())-Integer.parseInt(lecAnt.getText().toString());
            vConsumo = PRECIO_MT3 * consumoMts3;
            vImpuesto = (V_IMPUESTO * vConsumo) / 100; // 10% sobre el consumo de agua
            vImporte = vConsumo + vImpuesto + V_MANTENIMIENTO;
            consumo.setText("" + consumoMts3);
            importe.setText("$" + vImporte);
        }
    }

    public void verDetalle(View v){
        String detalle = String.format("Consumo  %13s %.2f%n" +
                                       "Impuesto %14s %.2f%n" +
                                       "Mantenimiento %5s %.2f%n" +
                                       "Importe total %8s %s"
                                       ,"$",vConsumo,"$",vImpuesto,"$",V_MANTENIMIENTO,"$",importe.getText().toString());
        // Se especifica donde se visiauliza (getAplicationContext())
        // texto a mostar y la diuración que tendrá
        mostrarToast(detalle);
    }

    public void limpiarDatos(View v){
        lecAnt.setText("0"); // Lectura Anterior
        lecAct.setText("0"); // LecturaActual
        consumo.setText("");
        importe.setText("");
    }

    private void mostrarToast(String detalle){
        Toast mensajeDetalle = Toast.makeText(getApplicationContext(),detalle,Toast.LENGTH_LONG);
        mensajeDetalle.show(); // Hace que se muestre

    }
    private boolean validarCampos(){
        if(lecAnt.getText().toString().isEmpty() || lecAct.getText().toString().isEmpty()){
            mostrarToast("Valor de las Lectuas NO deben estar vacios");
            return false;
        }
        if(Integer.parseInt(lecAct.getText().toString()) < Integer.parseInt(lecAnt.getText().toString())){
            mostrarToast("Valor de la lectura actual debe ser >= Valor de la lectura anterior");
            return false;
        }
        return true;
    }
}
