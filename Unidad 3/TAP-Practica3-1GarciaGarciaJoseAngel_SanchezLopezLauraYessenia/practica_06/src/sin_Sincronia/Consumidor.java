
package sin_Sincronia;

/** 
 * @author Sanchez Lopez Laura Yessenia
 * @author García García José Ángel
 */
public class Consumidor implements Runnable{
    private PilaS pila;
    private static int numCons = 0;
    private int numC;
    
    public Consumidor(PilaS p){
        pila = p;
        numC = ++numCons;
    }
    
    @Override
    public void run() {
        char c;
        for (int i = 0; i < 20; i++) {
            c = pila.quitar();
            if (Character.isAlphabetic(c)) {
                System.out.println("Hilo: " + Thread.currentThread().getName() + " Consumidor " + numC + " : " + c);
            }
            try {
                Thread.sleep((int)(Math.random() * 777));
            } catch (Exception e) {}
        }
    }
}
