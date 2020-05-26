package juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Bienvenida extends JFrame {
	public static Color colorVentana = new Color(94, 94, 94);
	private JPanel panelBienvenida;
	private JButton botonInicio;
	private JLabel etiquetaBienvenida;
	public Bienvenida() {
		setVisible(true);
		setTitle("Bienvenido a Datos Party I");
		setBounds(0, 0, 800, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(colorVentana);

		agregarBoton();
		agregarEtiqueta();


	}
	private void agregarEtiqueta() {

		etiquetaBienvenida = new JLabel("DATOS PARTY I");
		getContentPane().add(etiquetaBienvenida);
		etiquetaBienvenida.setBounds(0, 0, 500, 400);
		etiquetaBienvenida.setFont(new Font("Comic Sans MS", 1, 25));
		etiquetaBienvenida.setIcon(new ImageIcon("DatosPartyI/Imagenes/Estrella.png"));
	}
	private void agregarBoton() {
			botonInicio = new JButton("PLAY");
			getContentPane().add(botonInicio).setBounds(300, 500, 100, 30);
			botonInicio.setFont(new Font("Comic Sans MS", 1, 25));
			botonInicio.setLayout(null);
			botonInicio.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}


		public static void main(String[] args){
			Bienvenida b = new Bienvenida();
		}



	}