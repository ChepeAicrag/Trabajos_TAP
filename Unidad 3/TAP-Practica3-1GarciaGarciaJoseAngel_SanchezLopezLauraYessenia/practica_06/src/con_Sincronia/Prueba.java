
package con_Sincronia;

/**
 * @author Sanchez Lopez Laura Yessenia
 * @author García García José Ángel
 */
public class Prueba {

    public static void main(String[] args) {
        Pila pila = new Pila(10);
        Productor p1 = new Productor(pila);
        Thread prodT1 = new Thread(p1, "Hilo_1-productor");
        prodT1.start();
        Thread c1 = new Thread(new Consumidor(pila), "Hilo_1-consumidor");
        Thread c2 = new Thread(new Consumidor(pila), "Hilo_2-consumidor");
        Thread c3 = new Thread(new Consumidor(pila), "Hilo_3-consumidor");
        Productor p2 = new Productor(pila);
        Thread prodT2 = new Thread(p2, "Hilo_2-productor");
        prodT2.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
