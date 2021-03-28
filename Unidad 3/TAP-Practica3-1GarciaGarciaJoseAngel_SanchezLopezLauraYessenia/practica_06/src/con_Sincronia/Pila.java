

package con_Sincronia;

/**
 * @author Sanchez Lopez Laura Yessenia
 * @author García García José Ángel
 */
public class Pila {
    
    private int tope;
    private char[] datos;
    
    public Pila(int nd){
        datos = new char[nd];
        tope = -1;
    }
    
    public boolean llena(){
        return tope == datos.length-1;
    }
    
    public boolean vacia(){
        return tope < 0;
    }
    
    public synchronized void poner(char c){
        if (llena())
            System.out.println("Pila llena, intentó colocar " + Thread.currentThread().getName());
        while(llena())
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("Error al poner");
            }
        tope++;
        datos[tope] = c;
        this.notify();
    }
    
    public synchronized char quitar(){
        char d = ' ';
        if (vacia()) 
            System.out.println("Pila vacia, en espera el hilo " + Thread.currentThread().getName());
        while (vacia())                 
              try {
                   this.wait();
                } catch (InterruptedException e) {
                    System.out.println("Error al quitar xd");
                }
        d = datos[tope];
        tope--;
        this.notify();
        return d;
    }
    
    public char ver(){
        return !vacia() ? datos[tope] : ' ';
    }
}
