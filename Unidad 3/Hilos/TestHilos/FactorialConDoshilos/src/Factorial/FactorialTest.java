package Factorial;

public class FactorialTest {    
    public static void main (String [] args) throws InterruptedException{
        final int n = 350000;                            //Valor del factorial a calcular
        long timeEnd = 0 ;                              //Variable para obtener el tiempo final de la ejecucion
        Factorial f1 = new Factorial(n);                //Crea una nueva instania de Factorial
        long timeStart = System.currentTimeMillis();    //Toma el tiempo inicial de la ejecucion
        Thread t1 = new Thread(f1);                     //Crea un nuevo hilo
        t1.start();                                     //Método para que los hilos se ejecuten concurrentemente
        while(t1.isAlive())                             //Verifica si el hilo todavía se encuentra vivo, se puede usar tambien el método join.                                     
        timeEnd = System.currentTimeMillis();           //Toma el tiempo final de la ejecución
        //Imprime los datos en la pantalla
        
        System.out.println("Calculo de Factorial con UN HILO");
                                                        //Se usa bitCount() para representar en forma de bit el numero que obtengamos.
        System.out.printf("Factorial de: %d\nResultado = %d \nTiempo: %.4f\n",n,f1.getResultado().bitCount(), 
                (timeEnd-timeStart)/1000.0);
    }
}
