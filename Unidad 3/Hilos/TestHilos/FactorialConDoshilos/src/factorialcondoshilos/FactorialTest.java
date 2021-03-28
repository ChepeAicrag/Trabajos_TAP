package factorialcondoshilos;

import java.math.BigInteger;

public class FactorialTest {
    public static void main (String [] args) throws InterruptedException{
        long timeEnd = 0;                                                   //Almacenará el tiempo de ejecución final
        final int n = 350000;                                                //Valor del factorial a calcular
        FactorialConDoshilos f1 = new FactorialConDoshilos(2, n/2);         //Objeto que calculará la mitad de los factoriales
        FactorialConDoshilos f2 = new FactorialConDoshilos(n/2 +1 , n);     //Objeto que calculará la otra mitad de los factoriales
        long timeStart = System.currentTimeMillis();                        //Toma el tiempo en el que inicia el proceso
        Thread t1 = new Thread(f1);                                         //Instancia dos hilos
        Thread t2 = new Thread(f2);
        t1.start();                                                         //Método para que los hilos se ejecuten concurrentemente
        t2.start();
        while (t1.isAlive() && t2.isAlive())                                //Ciclo que se encargará de preguntar si los hilos todavia están vivos
        //t1.join();   //se pueden usar estos métodos en lugar del ciclo
        //t2.join();
        timeEnd = System.currentTimeMillis();                               //Tiempo final de la ejecución
        BigInteger total = f2.getResultado().multiply(f1.getResultado());   //Multiplica el resultado del hilo 1 con el resultado del hilo 2
        //Imprime resultados
        System.out.println("Calculo de Factorial con DOS HILOS");
        System.out.printf("Factorial de : %d \nResultado = %d \nTiempo: %.4f %s\n",n,total.bitCount(), //Bitcount() para representar en forma de bit el resuldado obtenido
                (timeEnd-timeStart)/1000.0 ," Segundos");
    }
}
