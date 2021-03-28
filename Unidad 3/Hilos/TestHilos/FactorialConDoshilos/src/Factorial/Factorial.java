/** Practica Factorial con Hilos
 *  DATE: 18-APRIL-2019
 *  @author: ALEJANDRO DIAZ RUIZ 
 */
package Factorial;
import java.math.BigInteger;

public class Factorial implements Runnable{
    private int numero;
    private BigInteger resultado = BigInteger.ONE;
    
    public Factorial (int numero){
        this.numero = numero;
    }
    
    public BigInteger getResultado(){
        return resultado;
    }
    @Override
    public void run(){
        for (int i = 2 ; i <= numero ; i++){
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
    }
}
