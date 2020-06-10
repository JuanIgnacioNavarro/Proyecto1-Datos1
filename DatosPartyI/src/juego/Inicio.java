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
public class Inicio extends JFrame implements ActionListener {
	public static int cantidadJugadores = 4;
	public static int cantidadRondas=2;
	private JPanel panelInicio;
	private JButton botonJugar;
	private JLabel etiquetaCantidadJugadores, etiquetaCantidadRondas, etiquetaDatos;
	private Integer numJugadores[] = {2, 3, 4};
	private Integer numRondas[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	private JComboBox<Integer> cajaCantidadJugadores;
	private JComboBox<Integer> cajaCantidadRondas;
	private Font fuenteTitulo = new Font("Comic Sans MS", 1, 25);
	private Font fuenteTexto = new Font("Comic Sans MS", 0, 16);



	/**
	 * Constructor de la clase que ubica las cajas para elegir
	 * la cantidad de jugadores y rondas
	 */
	public Inicio() {	
		setTitle("Configuracion");
        setVisible(true);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        agregarPanel();
        agregarEtiqueta();
        agregarCajaCombo();
        agregarBoton();
        
        panelInicio.repaint();

	}

	private void agregarPanel() {
		panelInicio = new JPanel();
        this.getContentPane().add(panelInicio);
        panelInicio.setLayout(null);
        panelInicio.setBackground(Bienvenida.colorVentana);
		
	}
	
	private void agregarEtiqueta() {
		etiquetaCantidadJugadores = new JLabel("Cantidad de jugadores:");
		etiquetaCantidadJugadores.setSize(200, 50);
		etiquetaCantidadJugadores.setLocation((this.getWidth() / 2) - (etiquetaCantidadJugadores.getWidth() / 2), 10);
		etiquetaCantidadJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaCantidadJugadores.setFont(fuenteTexto);
		etiquetaCantidadJugadores.setForeground(Color.WHITE);
		panelInicio.add(etiquetaCantidadJugadores);
		
		etiquetaCantidadRondas = new JLabel("Cantidad de rondas:");
		etiquetaCantidadRondas.setSize(200, 50);
		etiquetaCantidadRondas.setLocation((this.getWidth() / 2) - (etiquetaCantidadRondas.getWidth() / 2), etiquetaCantidadJugadores.getY() + 125);
		etiquetaCantidadRondas.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaCantidadRondas.setFont(fuenteTexto);
		etiquetaCantidadRondas.setForeground(Color.WHITE);
		panelInicio.add(etiquetaCantidadRondas);
		
	}
	
	private void agregarCajaCombo() {
		cajaCantidadJugadores = new JComboBox<Integer>(numJugadores);
		cajaCantidadJugadores.setSize(100, 30);
		cajaCantidadJugadores.setLocation((this.getWidth() / 2) - (cajaCantidadJugadores.getWidth() / 2), etiquetaCantidadJugadores.getY() + 50);
		panelInicio.add(cajaCantidadJugadores);
		
		cajaCantidadRondas = new JComboBox<Integer>(numRondas);
		cajaCantidadRondas.setSize(100, 30);
		cajaCantidadRondas.setLocation((this.getWidth() / 2) - (cajaCantidadRondas.getWidth() / 2), etiquetaCantidadRondas.getY() + 50);
		panelInicio.add(cajaCantidadRondas);
	
	}

	private void agregarBoton() {
		botonJugar = new JButton("JUGAR");
		botonJugar.setSize(100, 50);
		botonJugar.setLocation((this.getWidth() / 2) - (botonJugar.getWidth() / 2), (this.getHeight() - botonJugar.getHeight() - 50));
		botonJugar.setFont(fuenteTitulo);
		botonJugar.setLayout(null);
		botonJugar.addActionListener(this);
		panelInicio.add(botonJugar);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonJugar) {
			cantidadJugadores = cajaCantidadJugadores.getItemAt(cajaCantidadJugadores.getSelectedIndex());
			cantidadRondas = cajaCantidadRondas.getItemAt(cajaCantidadRondas.getSelectedIndex());
			this.setVisible(false);
			Partida partida = new Partida();

		}
	}
}
