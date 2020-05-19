package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jugador.Jugador;
import tablero.Tablero;

public class Partida extends JFrame implements MouseListener {
	public JPanel panelPartida;
	private ImageIcon imagenDados = new ImageIcon("Imagenes/Dados.png");
	private JLabel etiquetaDados;
	
	public static boolean eventoActivado = false;
	private boolean minijuegoActivado = false;
	private int cantidadRondas = Inicio.cantidadRondas;
	private Jugador listaJugadores[] = new Jugador[Inicio.cantidadJugadores];
	public Jugador jugadorActual;
	private Tablero tablero;

	public Partida() {
		setTitle("Datos Party I");
		setVisible(true);
		setSize(1200, 900);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		componentesVentana();
		componentesPartida();

	}

	private void componentesVentana() {
		agregarPanel();
	}

	private void componentesPartida() {
		agregarTablero();
		agregarJugadores();
		agregarDados();
		agregarTienda();
		
		panelPartida.repaint();
	}

	private void agregarPanel() {
		panelPartida = new JPanel();
		this.getContentPane().add(panelPartida);
		panelPartida.setLayout(null);
		panelPartida.setBackground(Bienvenida.colorVentana);
	}

	private void agregarTablero() {
		tablero = new Tablero();
	}

	private void agregarJugadores() {
		for (int i = 0; i < Inicio.cantidadJugadores; i++) {
			listaJugadores[i] = new Jugador(i);
		}

		jugadorActual = listaJugadores[0];
	}

	private void agregarDados() {
		etiquetaDados = new JLabel();
		etiquetaDados.setBounds(1010, 685, 150, 150);
		etiquetaDados.setIcon(new ImageIcon(imagenDados.getImage().getScaledInstance(etiquetaDados.getWidth(), etiquetaDados.getHeight(), Image.SCALE_SMOOTH)));
		etiquetaDados.addMouseListener(this);
		panelPartida.add(etiquetaDados);

	}

	private void agregarTienda() {

	}

	private void agregarMinijuego() {

	}

	private void agregarEstrella() {

	}
	
	private void tirarDados() {
		System.out.print(jugadorActual.numeroJugador);	
		
		siguienteTurno();
	}
	
	private void siguienteTurno() {
		if (cantidadRondas == 0) {
			finPartida();
			System.out.print("Termino el juego");	
		}
		
		else {
			if (jugadorActual.numeroJugador == listaJugadores.length - 1) {
				jugadorActual = listaJugadores[0];
				cantidadRondas -= 1;
			} 
			
			else {
				jugadorActual = listaJugadores[jugadorActual.numeroJugador + 1];
			}
		}
	}


	private void moverJugador() {

	}

	private void comprobarEventoDuelo() {

	}

	private void comprarEstrella() {

	}

	private void finPartida() {
		this.setVisible(false);
		Final f = new Final(listaJugadores);

	}

	public static void main(String[] args) {
		Partida p = new Partida();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == etiquetaDados) {
			tirarDados();
		}		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == etiquetaDados) {
			etiquetaDados.setOpaque(true);
			etiquetaDados.setBackground(Bienvenida.colorResalte);
		}			
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == etiquetaDados) {
			etiquetaDados.setBackground(Bienvenida.colorVentana);
		}		
	}
}
