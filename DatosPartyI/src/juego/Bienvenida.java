package juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bienvenida extends JFrame {
	public static Color colorVentana = new Color(94, 94, 94);
	private JPanel panelBienvenida;
	private JButton b1;
	private JLabel l1;
	public Bienvenida() {
		setTitle("Bienvenido a Datos Party I");
		setSize(646,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("Imagenes/Fondo.jpg")));
		setLayout(new FlowLayout());
		
		agregarPanel();
		agregarEtiqueta();
		agregarBoton();

	}
	
	private void agregarPanel() {
		panelBienvenida = new JPanel();
		this.getContentPane().add(panelBienvenida);
//		panelBienvenida.setLayout(null);
		panelBienvenida.setBackground(colorVentana);
		
	}
	private void agregarEtiqueta() {
		l1 = new JLabel("Here is a button");
		panelBienvenida.add(l1);
		
	}
	private void agregarBoton() {
		b1 = new JButton("I am a button");
		panelBienvenida.add(b1);

	}
	
	public static void main(String[] args){
		Bienvenida b = new Bienvenida();
	}
	
}
