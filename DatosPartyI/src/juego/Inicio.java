package juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase Inicio que guarda los datos de la partida
 * 1er dato: cantidad de jugadores
 * 2do dato: cantidad de rondas
 * @author Juan Pena
 */
public class Inicio extends JFrame {
	public static int cantidadJugadores = 4;
	public static int cantidadRondas=2;
	private JButton botonJugar;
	private JLabel etiquetaCantidadJugadores;
	private JLabel etiquetaCantidadRondas;
	private JLabel etiquetaDatos;
	private Integer numJugadores[] = {2, 3, 4};
	private Integer numRondas[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	private JComboBox<Integer> cajaCantidadJugadores = new JComboBox<>(numJugadores);
	private JComboBox<Integer> cajaCantidadRondas = new JComboBox<>(numRondas);

	/**
	 * Constructor de la clase que ubica las cajas para elegir
	 * la cantidad de jugadores y rondas
	 */
	public Inicio() {
		setVisible(true);
		setTitle("INICIAR PARTIDA");
		setBounds(0, 0, 400, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Bienvenida.colorVentana);
		setLayout(null);

		etiquetaCantidadJugadores = new JLabel("Seleccione la cantidad de jugadores: ");
		getContentPane().add(etiquetaCantidadJugadores);
		etiquetaCantidadJugadores.setBounds(75, 50, 250, 30);
		etiquetaCantidadJugadores.setFont(new Font("Comic Sans MS", 1, 12));

		etiquetaCantidadRondas = new JLabel("Seleccione la cantidad de rondas:");
		getContentPane().add(etiquetaCantidadRondas);
		etiquetaCantidadRondas.setBounds(75, 100, 250, 30);
		etiquetaCantidadRondas.setFont(new Font("Comic Sans MS", 1, 12));

		cajaCantidadJugadores.setBounds(135, 75, 80, 20);
		getContentPane().add(cajaCantidadJugadores);

		cajaCantidadRondas.setBounds(135, 130, 80, 20);
		getContentPane().add(cajaCantidadRondas);

		agregarBoton();
	}

	private void agregarBoton() {
		botonJugar = new JButton("PLAY");
		getContentPane().add(botonJugar).setBounds(130, 190, 90, 40);
		botonJugar.setFont(new Font("Comic Sans MS", 1, 12));
		botonJugar.setLayout(null);
		botonJugar.addActionListener(e -> {
			cantidadJugadores = cajaCantidadJugadores.getItemAt(cajaCantidadJugadores.getSelectedIndex());
			cantidadRondas = cajaCantidadRondas.getItemAt(cajaCantidadRondas.getSelectedIndex());
			Partida partida = new Partida();
			this.setVisible(false);
			partida.setVisible(true);
		});
	}
}
