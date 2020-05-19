package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import evento.Evento;
import jugador.ConstructorJugador;
import jugador.Jugador;
import tablero.Tablero;

public class Partida extends JFrame implements MouseListener {
	public static JPanel panelPartida;
	private ImageIcon imagenDados = new ImageIcon("Imagenes/Dados.png");
	private ImageIcon imagenTienda = new ImageIcon("Imagenes/TiendaEstrella.png");
	private ImageIcon imagenMoneda = new ImageIcon("Imagenes/Moneda.png");
	private ImageIcon imagenEstrella = new ImageIcon("Imagenes/Estrella.png");
	private JLabel etiquetaDados;
	private JLabel etiquetaTienda;
	private JLabel etiquetaMoneda;
	private JLabel etiquetaEstrella;
	private JLabel etiquetaFondoInfoJugadorActual;
	private JLabel etiquetaNombreJugadorActual;
	private JLabel etiquetaMonedasJugadorActual;
	private JLabel etiquetaEstrellasJugadorActual;
	
	private Font fuenteTitulo = new Font("Comic Sans MS", 1, 24);
	private Font fuenteTexto = new Font("Comic Sans MS", 1, 18);
	private Color colorResalte = new Color(145, 145, 145);
	private Color colorNegativo = new Color(230, 100, 100);
	private Color colorPositivo = new Color(180, 225, 120);
	public static boolean eventoActivado = false;
	private boolean partidaIniciada;
	private int cantidadRondas = Inicio.cantidadRondas;
	private Jugador listaJugadores[] = new ConstructorJugador[Inicio.cantidadJugadores];
	public Jugador jugadorActual;
	private Tablero tablero;
	private Evento evento = new Evento();

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
		actualizarInfoJugadorActual();
		
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
			listaJugadores[i] = new ConstructorJugador(i+1, tablero.primeraCasilla);
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

	private void actualizarInfoJugadorActual() {
		if (etiquetaFondoInfoJugadorActual == null) {
			
			etiquetaNombreJugadorActual = new JLabel();
			etiquetaNombreJugadorActual.setBounds(40, 25, 120, 40);
			etiquetaNombreJugadorActual.setFont(fuenteTitulo);
			etiquetaNombreJugadorActual.setForeground(Color.white);
			panelPartida.add(etiquetaNombreJugadorActual);
			
			etiquetaMoneda = new JLabel();
			etiquetaMoneda.setBounds(30, 75, 40, 40);
			etiquetaMoneda.setIcon(new ImageIcon(imagenMoneda.getImage().getScaledInstance(etiquetaMoneda.getWidth(), etiquetaMoneda.getHeight(), Image.SCALE_SMOOTH)));
			etiquetaMoneda.addMouseListener(this);
			panelPartida.add(etiquetaMoneda);
			
			etiquetaMonedasJugadorActual = new JLabel();
			etiquetaMonedasJugadorActual.setBounds(68, 80, 75, 30);
			etiquetaMonedasJugadorActual.setFont(fuenteTexto);
			etiquetaMonedasJugadorActual.setForeground(Color.white);
			panelPartida.add(etiquetaMonedasJugadorActual);
			
			etiquetaEstrella = new JLabel();
			etiquetaEstrella.setBounds(30, 125, 40, 40);
			etiquetaEstrella.setIcon(new ImageIcon(imagenEstrella.getImage().getScaledInstance(etiquetaEstrella.getWidth(), etiquetaEstrella.getHeight(), Image.SCALE_SMOOTH)));
			etiquetaEstrella.addMouseListener(this);
			panelPartida.add(etiquetaEstrella);
			
			etiquetaEstrellasJugadorActual = new JLabel();
			etiquetaEstrellasJugadorActual.setBounds(68, 130, 75, 30);
			etiquetaEstrellasJugadorActual.setFont(fuenteTexto);
			etiquetaEstrellasJugadorActual.setForeground(Color.white);
			panelPartida.add(etiquetaEstrellasJugadorActual);
			
			etiquetaFondoInfoJugadorActual = new JLabel();
			etiquetaFondoInfoJugadorActual.setBounds(25, 25, 150, 150);
			etiquetaFondoInfoJugadorActual.setOpaque(true);
			etiquetaFondoInfoJugadorActual.setBackground(colorResalte);
			panelPartida.add(etiquetaFondoInfoJugadorActual);
		}
		
		etiquetaNombreJugadorActual.setText(jugadorActual.nombreJugador);
		etiquetaMonedasJugadorActual.setText(": " + String.valueOf(jugadorActual.monedasJugador));
		etiquetaEstrellasJugadorActual.setText(": " + String.valueOf(jugadorActual.estrellasJugador));

		
		

	}
	
	private void agregarEstrella() {

	}
	
	private void activarMinijuego() {

	}
	
	private void comprobarEvento() {
		if (eventoActivado == true) {
			eventoActivado = false;
//			evento.activarEvento(jugadorActual, listaJugadores);
		}

	}
	
	private void comprobarEventoDuelo() {
		for (int i = 0; i < listaJugadores.length - 1; i++) {
			for (int j = i + 1; j < listaJugadores.length; j++) {
//				if (listaJugadores[i].casillaJugador.numeroCasilla == listaJugadores[j].casillaJugador.numeroCasilla) {
//					evento.eventoDuelo(listaJugadores[i], listaJugadores[j]);
					break;
//				}
			}
		}
	}
	private void tirarDados() {	
		if (partidaIniciada == false) {
			partidaIniciada = true;
		}

		else {
			siguienteTurno();
			actualizarInfoJugadorActual();
		}
//		Dados.mezclarDados();
//		jugadorActual.moverjugador(Dados.numeroDados);
//		comprobarEvento();
//		comprobarEventoDuelo();
		actualizarInfoJugadorActual();
		
	}
	
	private void siguienteTurno() {
		if (cantidadRondas == 0) {
			finPartida();
		}
		
		else {
			if (jugadorActual.numeroJugador == listaJugadores.length - 1) {
				jugadorActual = listaJugadores[0];
				cantidadRondas -= 1;
				activarMinijuego();
				agregarEstrella();
			} 
			
			else {
				jugadorActual = listaJugadores[jugadorActual.numeroJugador + 1];
			}
		}
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
				if (jugadorActual.monedasJugador < 500) {
					JOptionPane.showMessageDialog(null, "No tienes suficiente dinero", "ERROR", JOptionPane.WARNING_MESSAGE);
				}
				
				else {
					jugadorActual.estrellasJugador += 1;
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
			if (jugadorActual.comprarEstrella == false || jugadorActual.monedasJugador < 500) {
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
