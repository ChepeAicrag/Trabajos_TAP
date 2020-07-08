
package MVC_Principal;

import Controlador.ControladorBiblioteca;
import Modelo.ModeloBiblioteca;
import Vista.VistaBiblioteca;

/**
 * 
 * @author Garcia Garcia Jose Angel
 */
public class PrincipalBiblioteca {
     public static void main(String[] args) {
        ModeloBiblioteca modelo = new ModeloBiblioteca("dbbiblioteca"); 
        VistaBiblioteca vista  = new VistaBiblioteca();
        ControladorBiblioteca controlador  = new ControladorBiblioteca(vista, modelo);
        vista.conectaControlador(controlador);
        } // cerrar main
}
