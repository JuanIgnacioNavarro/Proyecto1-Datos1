package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jugador.Jugador;
import tablero.Tablero;

public class Partida extends JFrame implements MouseListener {
	public JPanel panelPartida;
	private ImageIcon imagenDados = new ImageIcon("Imagenes/Dados.png");
	private ImageIcon imagenTienda = new ImageIcon("Imagenes/tiendaEstrella.png");
	private JLabel etiquetaDados;
	private JLabel etiquetaTienda;
	
	public Color colorResalte = new Color(145, 145, 145);
	private Color colorNegativo = new Color(230, 100, 100);
	private Color colorPositivo = new Color(180, 225, 120);
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
		etiquetaTienda = new JLabel();
		etiquetaTienda.setBounds(1060, 25, 100, 100);
		etiquetaTienda.setIcon(new ImageIcon(imagenTienda.getImage().getScaledInstance(etiquetaTienda.getWidth(), etiquetaTienda.getHeight(), Image.SCALE_SMOOTH)));
		etiquetaTienda.addMouseListener(this);
		panelPartida.add(etiquetaTienda);

	}

	private void agregarMinijuego() {

	}

	private void agregarEstrella() {

	}
	
	private void tirarDados() {		
		siguienteTurno();
	}
	
	private void siguienteTurno() {
		if (cantidadRondas == 0) {
			finPartida();
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == etiquetaDados) {
			tirarDados();
		}
		
		else if (e.getSource() == etiquetaTienda) {
			if (jugadorActual.comprarEstrella == false) {
				JOptionPane.showMessageDialog(null, "No has pasado por una casilla con estrella", "ERROR", JOptionPane.WARNING_MESSAGE);
			}
			
			else {
				if (jugadorActual.numeroMonedas < 500) {
					JOptionPane.showMessageDialog(null, "No tienes suficiente dinero", "ERROR", JOptionPane.WARNING_MESSAGE);
				}
				
				else {
					jugadorActual.numeroEstrellas += 1;
					JOptionPane.showMessageDialog(null, "Has comprado una estrella!", "NUEVA ESTRELLA", JOptionPane.INFORMATION_MESSAGE);

				}
			}
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
			etiquetaDados.setBackground(colorResalte);
		}
		else if (e.getSource() == etiquetaTienda) {
			etiquetaTienda.setOpaque(true);
			if (jugadorActual.comprarEstrella == false || jugadorActual.numeroMonedas < 500) {
				etiquetaTienda.setBackground(colorNegativo);
			}
			else {
				etiquetaTienda.setBackground(colorPositivo);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == etiquetaDados) {
			etiquetaDados.setBackground(Bienvenida.colorVentana);
		}	
		
		else if (e.getSource() == etiquetaTienda) {
			etiquetaTienda.setBackground(Bienvenida.colorVentana);
		}
	}
	
	public static void main(String[] args) {
		Partida p = new Partida();
	}
}
