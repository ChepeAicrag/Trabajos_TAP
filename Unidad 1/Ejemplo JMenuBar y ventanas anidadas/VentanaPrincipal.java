import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class VentanaPrincipal extends JFrame {
    /* Atributos de la Clase */
     
    /* Declaro mi JMenuBar, éste me dibujara una barra horizontal */
    private JMenuBar barraMenu;
     
    /* Declaro los JMenu */
    private JMenu menuVer,menuEdicion,menuAyuda,menuHistorial;
     
    /* Declaro todos los JMenuItem */
    private JMenuItem itmEstandar,itmCientifica,itmCopiar,itmPegar,itmHistorialCopiar,itmHistorialEditar,itmHistorialCancelar,itmHistorialBorrar,itmAyudaVer,itmAyudaAcerca;
     
     
    /* Constructor Por Defecto */
    public VentanaPrincipal() {
        /* con el super llamo al constructor del Padre y le paso el titulo de la ventana */
        super("Ejemplo JMenuBar");
        /* Inicializo todos los componentes de la ventana */
        this.barraMenu          = new JMenuBar();
         
        this.menuVer            = new JMenu("Ver");
        this.menuEdicion        = new JMenu("Editar");
        this.menuAyuda          = new JMenu("Ayuda");
        this.menuHistorial      = new JMenu("Historial");
         
        this.itmEstandar        = new JMenuItem("Estandar");
        this.itmCientifica      = new JMenuItem("Cientifica");
         
        this.itmCopiar          = new JMenuItem("Copiar");
        this.itmPegar           = new JMenuItem("Pegar");
         
        this.itmHistorialCopiar = new JMenuItem("Copiar Historial");
        this.itmHistorialEditar = new JMenuItem("Editar");
        this.itmHistorialCancelar=new JMenuItem("Cancelar Edición");
        this.itmHistorialBorrar = new JMenuItem("Borrar");
        this.itmAyudaVer        = new JMenuItem("Ver La Ayuda");
        this.itmAyudaAcerca     = new JMenuItem("Acerca de Calculadora");
        /* llamo al metodo init para ir agregando los componentes a la ventana */
  //      this.init();
         
 //   }
 //   public void init() {
        /* Le doy las dimensiones a mi ventana */
        this.setSize(250,300);
        /* le digo que al presionar el boton "X" el programa se detenga */
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         
        /*  Ahora empezaré a agregar los JMenu al JMenuBar y los JMenuItem a los JMenu */
         
        /* Primero agrego el JMenu "Ver" a la Barra de Menu */
        this.barraMenu.add(this.menuVer);
            /* 
            Ahora al JMenu "Ver" Agrego dos JMenuItem 
            Deberia quedar Mas o menos asi:
                    [ Ver ► ]
                        |__ Estandar
                        |__ Científica
            */
            this.menuVer.add(this.itmEstandar);
            this.menuVer.add(this.itmCientifica);
        /* Ahora es el turno del JMenu "Edicion" agregarse a la barra de menu */
        this.barraMenu.add(this.menuEdicion);
            /* 
                Al JMenu "Edición" le agrego 2 JMenuItem y 1 JMenu
                Deberia quedar Mas o menos asi:
                    [ Edicion ► ]
                        |__ Copiar
                        |__ Pegar
                        |__ [ Historial ► ]
             */
            this.menuEdicion.add(this.itmCopiar);
            this.menuEdicion.add(this.itmPegar);
            this.menuEdicion.add(this.menuHistorial);
                /* 
                    Al JMenu Historial (que esta dentro del JMenu Editar) 
                    le agrego los Items 
                    [ Edicion ► ]
                        |__ Copiar
                        |__ Pegar
                        |__ [ Historial ► ]
                                    |__ Copiar
                                    |__ Editar
                                    |__ Cancelar
                                    |__ Borrar
                 */
                this.menuHistorial.add(this.itmHistorialCopiar);
                this.menuHistorial.add(this.itmHistorialEditar);
                this.menuHistorial.add(this.itmHistorialCancelar);
                this.menuHistorial.add(this.itmHistorialBorrar);
        /* Por ultimo Agrego el JMenu Ayuda a la barra */
        this.barraMenu.add(this.menuAyuda);
            /* 
                 A ese JMenu le agrego los items que tendrá
                    [ Ayuda ► ]
                        |__ Ver
                        |__ Acerca de Calculadora
             */
            this.menuAyuda.add(this.itmAyudaVer);
            this.menuAyuda.add(this.itmAyudaAcerca);
             
             
        /* Por último le seteo el JMenuBar a esta ventana */
        this.setJMenuBar(this.barraMenu);
         
        /* Centro la ventana */
        this.setLocationRelativeTo(null);
         
        /* Llamo al metodo "programaEventos()" para asignarle eventos a cada uno de los JMenuItem */
        this.programaEventos();
         
        /* y la muestro */
        this.setVisible(true);
 
    }
     
    public void programaEventos() {
        /* 
            La forma de implementar la funcionalidad de los JMenuItem es identica a como lo hacian con JButton
            crean un listener y se lo asignan al JMenuItem
             
            Solo le asignaremos un listener a "itmAyudaAcerca" y cuando lo presionemos desplegara un mensaje de alerta
         */
        ActionListener ejemploA = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Presionaste el JMenuItem Acerca de");
            }   
        };
        this.itmAyudaAcerca.addActionListener(ejemploA);
    }
	
	public static void main (String[] args){
		VentanaPrincipal vp = new VentanaPrincipal();
	}
}