import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton; 
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
final class InternalFrameDemo extends JFrame{
 JDesktopPane desktop; //Contendrá los JInternalFrames.
  
 /**
  * Crea la ventana de demostración de JInternalFrame.

  * @param tit - El título de la ventana de demostración.
  */
public InternalFrameDemo(String tit) {
  super(tit);
  desktop = new JDesktopPane(); //Un panel especializado.

  setContentPane(desktop);
  desktop.setBackground(Color.LIGHT_GRAY);
 }
 /**
  * Crea una ventana interna (JInternalFrame) añadiendo
  * el componente y título pasados como argumento.
  * @param comp - El componente a añadir a la ventana.
  * @param tit - El título de la ventana.

  */
 protected void crearVentana(JComponent comp, String tit) {
  //Clase que es creada más abajo:
  MiInternalFrame interna = new MiInternalFrame(comp, tit);
  desktop.add(interna); //Añadimos la ventana interna al JFrame
  try {
   interna.setSelected(true);
  }
  catch (java.beans.PropertyVetoException e){
   e.printStackTrace(); //En caso de error...
  }
 }
/**
   * Crea y devuelve un panel con algunos componentes con
  * el propósito de mostrarlo una ventana interna.
  * @return - El panel con los componentes a mostrar.
  */
 protected static JPanel crearContenido(){
  JPanel panel = new JPanel(new FlowLayout());
  panel.add(new JLabel("Etiqueta de muestra"));
  panel.add(new JButton("Botón De Muestra"));
  panel.add(new JTextField("Campo de muestra"));
  panel.add(new JCheckBox("Casilla de muestra"));
  panel.add(new JRadioButton("Botón de radio de muestra"));
  return panel;
 }

 public static void main(String[] args) {
  //Instanciamos el demo y damos un título a la ventana...
  InternalFrameDemo ifd = new InternalFrameDemo("Cómo usar JInternalFrame - El Blog de Baro");
  ifd.setDefaultCloseOperation(EXIT_ON_CLOSE);
  //Creamos las ventanas:
  ifd.crearVentana(crearContenido(), "Ventana Interna #1");
  ifd.crearVentana(crearContenido(), "Ventana Interna #2");
   ifd.crearVentana(crearContenido(), "Ventana Interna #3");
   ifd.crearVentana(crearContenido(), "Ventana Interna #4");
  //Tamaño y locación (null = centro):
  ifd.setSize(800, 500);
  ifd.setLocationRelativeTo(null); 
  //Mostramos la ventana:
  ifd.setVisible(true);
 }
}

/**
 * Clase sencilla que se encarga de externder una ventana interna.
 */
class MiInternalFrame extends JInternalFrame{
 //private static final long serialVersionUID = 1L;
 //Contador estático que aumenta cada que instanciamos una ventana.
 static int openFrameCount = 1;
 //Para posicionar la ventana interna:
 static final int xOffset = 50, yOffset = 50;
 static int posX = 0, posY = 0;
 /**
  * Construye una ventana interna, añadiendo el componente
  * y título pasados como argumento.
  * @param comp - El componente a añadir.
  * @param titulo - El título para la vetana.
  */
 public MiInternalFrame(JComponent comp, String titulo) {
  super(titulo,
    true, //Resizable
    true, //Closable
    true, //Maximizable
    true);//Iconifiable
  //Aumentamos el conteo de ventanas.
  openFrameCount ++;
  //Añadimos el componente a la ventana interna.
  add(comp);
  pack();
  setVisible(true);
  //Ponemos la locación de la ventana.
  setLocation(posX, posY);
  //Operación sencilla para posicionar un poco más abajo la siguiente ventana:
  posX = xOffset*openFrameCount;
  posY = yOffset*openFrameCount;
 }

}
