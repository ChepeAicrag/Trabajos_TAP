
package con_Sincronia;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Sanchez Lopez Laura Yessenia
 * @author García García José Ángel
 */
public class PruebaExecutorService {
    public static void main(String[] args) {
        Pila pila = new Pila(10);
        Productor p1 = new Productor(pila);
        Productor p2 = new Productor(pila);
        Consumidor c1 = new Consumidor(pila);
        Consumidor c2 = new Consumidor(pila);
        Consumidor c3 = new Consumidor(pila);
        ExecutorService ejecutor = Executors.newCachedThreadPool();
        ejecutor.execute(p1);
        ejecutor.execute(p2);
        ejecutor.execute(c2);
        ejecutor.execute(c3);
        ejecutor.execute(c1);
        ejecutor.shutdown();
        try {
            boolean tareasTerminaron = ejecutor.awaitTermination(1, TimeUnit.MINUTES);
            if (tareasTerminaron) {
                System.out.println("Todas las tareas terminaron");
            }else{
                System.out.println("Se agotoó el tiempo esperando a que las tareas terminaran.");
                System.exit(1);
            }
        } catch (InterruptedException e) {
            System.out.println("Hubo una interrupcion mientra se esperaba a que terminaran las tareas");
        }
        
        } 
}
