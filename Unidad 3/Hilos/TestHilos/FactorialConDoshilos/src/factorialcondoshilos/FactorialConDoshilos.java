/** Practica Factorial con DOS Hilos
 *  DATE: 21-APRIL-2019
 *  @author: ALEJANDRO DIAZ RUIZ 
 */
package factorialcondoshilos;
import java.math.BigInteger;

public class FactorialConDoshilos implements Runnable{    //Clase Facorial con dos hilos
    private int start;                                    //Atributo start numero que inicia el factorial
    private int end;                                      //Atributo end numero donde termina el facorial
    
    private BigInteger resultado = BigInteger.ONE;        //Objeto BigInteger que almacenará el resultado
    
    public FactorialConDoshilos (int start, int end){     //Constructor de la clase
        this.start = start;
        this.end = end;
    }
    
    public BigInteger getResultado(){                     //Método para retornar el resultado
        return resultado;
    }
    
    @Override
    public void run(){                                    //Operaciones del hilo
        for (int i = start ; i <= end ; i++){
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
    }
}
