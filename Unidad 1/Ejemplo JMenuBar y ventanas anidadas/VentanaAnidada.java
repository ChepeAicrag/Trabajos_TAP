import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.*;

 
public class VentanaAnidada extends JFrame {

    private JMenuBar barraMenu;   
    private JMenu menuVer,menuEdicion,menuAyuda,menuHistorial;
    private JMenuItem itmEstandar, itmCientifica, itmCopiar, itmPegar, itmHistorialCopiar, itmHistorialEditar, itmHistorialCancelar, itmHistorialBorrar, itmAyudaVer, itmAyudaAcerca;
	private JLabel etiqueta1 ; // etiqueta con texto
	private JTextField cuadroDeTexto ; // 
	private JButton botonAceptar, botonCancelar ; // botón aceptar
	private Container panel ;
	private JPanel secFormulario, secBotones;
     
    /* Constructor Por Defecto */
    public VentanaAnidada() {
        /* con el super llamo al constructor del Padre y le paso el titulo de la ventana */
        super("Ejemplo JMenuBar");
		setLayout(new GridLayout(2,1));

		secFormulario = new JPanel();
		secBotones = new JPanel();
        /* Inicializo todos los componentes de la ventana */
        barraMenu          = new JMenuBar();
         
        menuVer            = new JMenu("Ver");
        menuEdicion        = new JMenu("Editar");
        menuAyuda          = new JMenu("Ayuda");
        menuHistorial      = new JMenu("Historial");
         
        itmEstandar        = new JMenuItem("Estandar");
        itmCientifica      = new JMenuItem("Cientifica");
         
        itmCopiar          = new JMenuItem("Copiar");
        itmPegar           = new JMenuItem("Pegar");
         
        itmHistorialCopiar = new JMenuItem("Copiar Historial");
        itmHistorialEditar = new JMenuItem("Editar");
        itmHistorialCancelar=new JMenuItem("Cancelar Edición");
        itmHistorialBorrar = new JMenuItem("Borrar");
        itmAyudaVer        = new JMenuItem("Ver La Ayuda");
        itmAyudaAcerca     = new JMenuItem("Acerca de Calculadora");

        /* dimensiones a la ventana */
       
         
        /*  Agregar los JMenu al JMenuBar y los JMenuItem a los JMenu */
         
        /* Agregar el JMenu "Ver" a la Barra de Menu */
        barraMenu.add(menuVer);
            /* 
            Ahora al JMenu "Ver" Agrego dos JMenuItem 
            Deberia quedar Mas o menos asi:
                    [ Ver ► ]
                        |__ Estandar
                        |__ Científica
            */
            menuVer.add(itmEstandar);
            menuVer.add(itmCientifica);
			menuVer.addMenuListener(new MiMenuListener());
        /* Ahora es el turno del JMenu "Edicion" agregarse a la barra de menu */
        barraMenu.add(menuEdicion);
            /* 
                Al JMenu "Edición" le agrego 2 JMenuItem y 1 JMenu
                Deberia quedar Mas o menos asi:
                    [ Edicion ► ]
                        |__ Copiar
                        |__ Pegar
                        |__ [ Historial ► ]
             */
            menuEdicion.add(itmCopiar);
            menuEdicion.add(itmPegar);
            menuEdicion.add( menuHistorial);
			
		    itmCopiar.addActionListener(new ActionListener(){
  			  	public void actionPerformed(ActionEvent e) {
      			  System.out.println("Opcion elegida: "+e.getActionCommand());
  				}
 		   });
		  
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
                 menuHistorial.add( itmHistorialCopiar);
                 menuHistorial.add( itmHistorialEditar);
                 menuHistorial.add( itmHistorialCancelar);
                 menuHistorial.add( itmHistorialBorrar);
        /* Por ultimo Agrego el JMenu Ayuda a la barra */
			 
         barraMenu.add( menuAyuda);
            /* 
                 A ese JMenu le agrego los items que tendrá
                    [ Ayuda ► ]
                        |__ Ver
                        |__ Acerca de Calculadora
             */
             menuAyuda.add( itmAyudaVer);
             menuAyuda.add( itmAyudaAcerca);
             
             
      
		
		// agregar componentes a JFrame
		etiqueta1 = new JLabel ("Mensaje en etiqueta");
		cuadroDeTexto = new JTextField(20);
		botonAceptar = new JButton ("Aceptar");	
		botonCancelar = new JButton("Cancelar");
		cuadroDeTexto.setToolTipText("Ingresa tu nombre");
		
		// agregamos paneles
		secFormulario.setLayout(new GridLayout(1,2,5,5));
		secBotones.setLayout(new FlowLayout());
		secFormulario.add(etiqueta1);  // agregamos la etiqueta1 al JFrame
		secFormulario.add(cuadroDeTexto); //agregamos el cuadro de texto al JFrame
		secBotones.add(botonAceptar); //agregamos el botón al JFrame
		secBotones.add(botonCancelar);
        /* Por último le seteo el JMenuBar a esta ventana */
		
        setJMenuBar( barraMenu);
		add(secBotones);
		add(secFormulario);
		
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
 
    }
	public class MiMenuListener implements MenuListener {

    @Override
    public void menuSelected(MenuEvent e) {
        System.out.println("menuSelected");
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        System.out.println("menuDeselected");
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        System.out.println("menuCanceled");
    }
}
	public static void main (String[] args){
		VentanaAnidada v = new VentanaAnidada();
	}
}