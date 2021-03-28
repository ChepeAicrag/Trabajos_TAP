
package sin_Sincronia;

/**
 * @author Sanchez Lopez Laura Yessenia
 * @author García García José Ángel
 */
public class Prueba {

    public static void main(String[] args) {
        PilaS pila = new PilaS(10);
        Productor p1 = new Productor(pila);
        Productor p2 = new Productor(pila);
        Thread prodT1 = new Thread(p1);
        Thread prodT2 = new Thread(p2);
        prodT1.start();
        prodT2.start();
        Consumidor con1 = new Consumidor(pila);
        Consumidor con2 = new Consumidor(pila);
        Consumidor con3 = new Consumidor(pila);
        Thread c1 = new Thread(con1);
        Thread c2 = new Thread(con2);
        Thread c3 = new Thread(con3);
        c1.start();
        c2.start();
        c3.start();
    }
}
