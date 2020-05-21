package juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bienvenida {
	JButton b1;
	JLabel l1;
	public Bienvenida()
	{
		setTitle("Datos Party");
		setSize(646,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("DatosPartyI/Imagenes/Fondo.jpg")));
		setLayout(new FlowLayout());
		l1=new JLabel("Here is a button");
		b1=new JButton("I am a button");
		add(l1);
		add(b1);
}
