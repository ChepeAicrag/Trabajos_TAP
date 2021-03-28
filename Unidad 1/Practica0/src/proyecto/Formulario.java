/*
 * Replicar la GUI 
 */
package proyecto;

/**
 *
 * @author Garcia Garcia Jose Angel
 */

import com.toedter.calendar.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Formulario extends JFrame {

    public Formulario() {
        super("Registro de personal");
        setSize(640, 490);
        setLocationRelativeTo(null);
        add(full());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public JPanel full() {
        SpringLayout s = new SpringLayout();
        JPanel pa = new JPanel(s);
        JPanel p1 = contenedor1();
        JPanel p2 = contenedor2();
        JPanel p3 = contenedor3();
        pa.add(p1);
        pa.add(p2);
        pa.add(p3);
        // Colocacion del Panel 1 "Datos Personales"
        s.putConstraint(SpringLayout.NORTH, pa, 12, SpringLayout.NORTH, p1);
        s.putConstraint(SpringLayout.WEST, p1, 8, SpringLayout.WEST, pa);
        s.putConstraint(SpringLayout.EAST, pa, 12, SpringLayout.EAST, p1);
        s.putConstraint(SpringLayout.NORTH, p1, 12, SpringLayout.NORTH, pa);
        s.putConstraint(SpringLayout.SOUTH, p1, 140, SpringLayout.NORTH, pa);
        // Colocacion del Panel 2 "Domicilio"
        s.putConstraint(SpringLayout.NORTH, p2, 20, SpringLayout.SOUTH, p1);
        s.putConstraint(SpringLayout.WEST, p2, 18, SpringLayout.WEST, pa);
        s.putConstraint(SpringLayout.EAST, p2, -12, SpringLayout.EAST, pa);
        s.putConstraint(SpringLayout.SOUTH, p2, -25, SpringLayout.NORTH, p3);
        // Colocacion del Panel 2 "Adscripcion"
        s.putConstraint(SpringLayout.NORTH, p3, 280, SpringLayout.NORTH, pa);
        s.putConstraint(SpringLayout.SOUTH, pa, 70, SpringLayout.SOUTH, p3);
        s.putConstraint(SpringLayout.EAST, p3, -12, SpringLayout.EAST, pa);
        s.putConstraint(SpringLayout.WEST, p3, 18, SpringLayout.WEST, pa);
        Border b1 = BorderFactory.createLineBorder(Color.BLACK, 2);
        ImageIcon img1 = new ImageIcon("agregar.png");
        ImageIcon icono1 = new ImageIcon(img1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JButton agregar = new JButton(icono1);
        agregar.setBorder(b1);
        ImageIcon img2 = new ImageIcon("editar.png");
        ImageIcon icono2 = new ImageIcon(img2.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JButton editar = new JButton(icono2);
        editar.setBorder(b1);
        ImageIcon img3 = new ImageIcon("buscar.png");
        ImageIcon icono3 = new ImageIcon(img3.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JButton buscar = new JButton(icono3);
        buscar.setBorder(b1);
        ImageIcon img4 = new ImageIcon("eliminar.png");
        ImageIcon icono4 = new ImageIcon(img4.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JButton eliminar = new JButton(icono4);
        eliminar.setBorder(b1);
        pa.add(agregar);
        pa.add(editar);
        pa.add(buscar);
        pa.add(eliminar);
        s.putConstraint(SpringLayout.WEST, agregar, 470, SpringLayout.WEST, pa);
        s.putConstraint(SpringLayout.NORTH, agregar, 25, SpringLayout.SOUTH, p3);
        s.putConstraint(SpringLayout.NORTH, editar, 25, SpringLayout.SOUTH, p3);
        s.putConstraint(SpringLayout.NORTH, buscar, 25, SpringLayout.SOUTH, p3);
        s.putConstraint(SpringLayout.NORTH, eliminar, 25, SpringLayout.SOUTH, p3);
        s.putConstraint(SpringLayout.WEST, editar, 3, SpringLayout.EAST, agregar);
        s.putConstraint(SpringLayout.WEST, buscar, 3, SpringLayout.EAST, editar);
        s.putConstraint(SpringLayout.WEST, eliminar, 3, SpringLayout.EAST, buscar);
        return pa;
    }

    public JPanel contenedor3() {
        SpringLayout s = new SpringLayout();
        JPanel pa = new JPanel(s);
        Border bordejpanel = new TitledBorder(new EtchedBorder(Color.BLACK, Color.black), "Adscripción");
        pa.setBorder(bordejpanel);
        JLabel etqDep = new JLabel("Departamento");
        JLabel etqPue = new JLabel("Puesto");
        JLabel etqFec = new JLabel("Fecha de ingreso");
        String[] deps = {"Sistemas", "Electrica", "Civil", "Mecnaica", "Gestion", "Administracion"};
        String[] pues = {"Jefe", "Maestro", "Admon", "Mantenimiento"};
        JComboBox comDeps = new JComboBox(deps);
        JComboBox comPues = new JComboBox(pues);
        JDateChooser cal = new JDateChooser();
        pa.add(etqDep);
        pa.add(etqFec);
        pa.add(etqPue);
        pa.add(comPues);
        pa.add(comDeps);
        pa.add(cal);
        s.putConstraint(SpringLayout.WEST, etqDep, 20, SpringLayout.WEST, pa);
        s.putConstraint(SpringLayout.NORTH, etqDep, 12, SpringLayout.NORTH, pa);
        s.putConstraint(SpringLayout.WEST, comDeps, 40, SpringLayout.EAST, etqDep);
        s.putConstraint(SpringLayout.NORTH, comDeps, 12, SpringLayout.NORTH, pa);
        s.putConstraint(SpringLayout.WEST, etqFec, 145, SpringLayout.EAST, comDeps);
        s.putConstraint(SpringLayout.NORTH, etqFec, 10, SpringLayout.NORTH, pa);
        s.putConstraint(SpringLayout.WEST, etqPue, 20, SpringLayout.WEST, pa);
        s.putConstraint(SpringLayout.NORTH, etqPue, 16, SpringLayout.SOUTH, etqDep);
        s.putConstraint(SpringLayout.WEST, comPues, 80, SpringLayout.EAST, etqPue);
        s.putConstraint(SpringLayout.NORTH, comPues, 10, SpringLayout.SOUTH, comDeps);
        s.putConstraint(SpringLayout.NORTH, cal, 12, SpringLayout.NORTH, pa);
        s.putConstraint(SpringLayout.WEST, cal, 12, SpringLayout.EAST, etqFec);
        return pa;
    }

    public JPanel contenedor2() {
        SpringLayout s = new SpringLayout();
        JPanel pa = new JPanel(s);
        Border bordejpanel = new TitledBorder(new EtchedBorder(Color.BLACK, Color.black), "Domicilio");
        pa.setBorder(bordejpanel);
        JLabel etqCalle = new JLabel("Calle");
        JLabel etqCol = new JLabel("Colonia");
        JLabel etqNum = new JLabel("Número");
        JLabel etqCiu = new JLabel("Ciudad");
        JTextField txtCalle = new JTextField(23);
        JTextField txtCol = new JTextField(23);
        JTextField txtNum = new JTextField(5);
        JTextField txtCiu = new JTextField(15);
        pa.add(etqCalle);
        pa.add(etqCiu);
        pa.add(etqCol);
        pa.add(etqNum);
        pa.add(txtCalle);
        pa.add(txtCiu);
        pa.add(txtCol);
        pa.add(txtNum);
        s.putConstraint(SpringLayout.WEST, etqCalle, 20, SpringLayout.WEST, pa);
        s.putConstraint(SpringLayout.WEST, etqCol, 20, SpringLayout.WEST, pa);
        s.putConstraint(SpringLayout.NORTH, etqCalle, 12, SpringLayout.NORTH, pa);
        s.putConstraint(SpringLayout.NORTH, etqCol, 20, SpringLayout.SOUTH, etqCalle);
        s.putConstraint(SpringLayout.NORTH, txtCalle, 12, SpringLayout.NORTH, pa);
        s.putConstraint(SpringLayout.NORTH, etqNum, 12, SpringLayout.NORTH, pa);
        s.putConstraint(SpringLayout.NORTH, txtNum, 12, SpringLayout.NORTH, pa);
        s.putConstraint(SpringLayout.WEST, txtCalle, 26, SpringLayout.EAST, etqCalle);
        s.putConstraint(SpringLayout.WEST, etqNum, 12, SpringLayout.EAST, txtCalle);
        s.putConstraint(SpringLayout.WEST, txtNum, 12, SpringLayout.EAST, etqNum);
        s.putConstraint(SpringLayout.NORTH, txtCol, 16, SpringLayout.SOUTH, txtCalle);
        s.putConstraint(SpringLayout.WEST, txtCol, 12, SpringLayout.EAST, etqCol);
        s.putConstraint(SpringLayout.NORTH, etqCiu, 20, SpringLayout.SOUTH, etqNum);
        s.putConstraint(SpringLayout.NORTH, txtCiu, 16, SpringLayout.SOUTH, txtNum);
        s.putConstraint(SpringLayout.WEST, etqCiu, 12, SpringLayout.EAST, txtCol);
        s.putConstraint(SpringLayout.WEST, txtCiu, 18, SpringLayout.EAST, etqCiu);
        return pa;
    }

    public JPanel contenedor1() {
        JPanel c = new JPanel();
        ImageIcon img = new ImageIcon("logo.png");
        ImageIcon icono = new ImageIcon(img.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        JLabel etqImg = new JLabel(icono);
        JLabel etqApe = new JLabel("Apellidos :");
        JLabel etqNom = new JLabel("Nombre :");
        JLabel etqSex = new JLabel("Sexo");
        ImageIcon img2 = new ImageIcon("sesion.png");
        ImageIcon icono2 = new ImageIcon(img2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JLabel etqImg2 = new JLabel(icono2);
        JLabel etqSesion = new JLabel("Cerrar sesión");
        JCheckBox m = new JCheckBox("Mujer");
        JCheckBox h = new JCheckBox("Hombre");
        JTextField txtApe = new JTextField(20);
        JTextField txtNom = new JTextField(20);
        SpringLayout s = new SpringLayout();
        c.setLayout(s);
        c.add(etqImg);
        c.add(etqApe);
        c.add(txtApe);
        c.add(etqNom);
        c.add(txtNom);
        c.add(etqSex);
        c.add(m);
        c.add(h);
        c.add(etqSesion);
        c.add(etqImg2);
        s.putConstraint(SpringLayout.NORTH, etqImg, 25, SpringLayout.NORTH, c);
        s.putConstraint(SpringLayout.NORTH, etqApe, 25, SpringLayout.NORTH, c);
        s.putConstraint(SpringLayout.NORTH, txtApe, 25, SpringLayout.NORTH, c);
        s.putConstraint(SpringLayout.WEST, etqImg, 12, SpringLayout.WEST, c);
        s.putConstraint(SpringLayout.WEST, etqApe, 12, SpringLayout.EAST, etqImg);
        s.putConstraint(SpringLayout.WEST, txtApe, 10, SpringLayout.EAST, etqApe);
        s.putConstraint(SpringLayout.NORTH, etqNom, 10, SpringLayout.SOUTH, etqApe);
        s.putConstraint(SpringLayout.NORTH, txtNom, 7, SpringLayout.SOUTH, txtApe);
        s.putConstraint(SpringLayout.WEST, etqNom, 12, SpringLayout.EAST, etqImg);
        s.putConstraint(SpringLayout.WEST, txtNom, 17, SpringLayout.EAST, etqNom);
        s.putConstraint(SpringLayout.WEST, etqSex, 12, SpringLayout.EAST, etqImg);
        s.putConstraint(SpringLayout.NORTH, etqSex, 15, SpringLayout.SOUTH, etqNom);
        s.putConstraint(SpringLayout.WEST, m, 28, SpringLayout.EAST, etqSex);
        s.putConstraint(SpringLayout.NORTH, m, 7, SpringLayout.SOUTH, txtNom);
        s.putConstraint(SpringLayout.NORTH, h, 7, SpringLayout.SOUTH, txtNom);
        s.putConstraint(SpringLayout.WEST, h, 8, SpringLayout.EAST, m);
        s.putConstraint(SpringLayout.NORTH, etqSesion, 20, SpringLayout.NORTH, c);
        s.putConstraint(SpringLayout.WEST, etqSesion, -80, SpringLayout.EAST, c);
        s.putConstraint(SpringLayout.NORTH, etqSesion, 18, SpringLayout.NORTH, c);
        s.putConstraint(SpringLayout.WEST, etqImg2, 560, SpringLayout.WEST, c);
        return c;
    }
}
