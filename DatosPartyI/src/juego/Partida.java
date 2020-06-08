package juego;

import java.awt.Color;
import minijuego.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import evento.Evento;
import evento.PilaEventos;
import jugador.Jugador;
import tablero.Tablero;
import tablero.Casilla;

public class Partida extends JFrame implements MouseListener, Runnable {
	public static JPanel panelPartida;
	private ImageIcon imagenDados = new ImageIcon("Imagenes/Dados.png");
	private ImageIcon imagenTienda = new ImageIcon("Imagenes/EstrellaExtra.png");
	private ImageIcon imagenMoneda = new ImageIcon("Imagenes/Moneda.png");
	private ImageIcon imagenEstrella = new ImageIcon("Imagenes/Estrella.png");
	private ImageIcon imagenSilbato= new ImageIcon ("Imagenes/silbato.png");
	private JLabel etiquetaDados;
	private JLabel etiquetaNumeroDados;
	private JLabel etiquetaTienda;
	private JLabel etiquetaMoneda;
	private JLabel etiquetaEstrella;
	private JLabel etiquetaFondoInfoJugadorActual;
	private JLabel etiquetaNombreJugadorActual;
	private JLabel etiquetaMonedasJugadorActual;
	private JLabel etiquetaEstrellasJugadorActual;
	private JLabel etiquetaRondas;
	private JLabel etiquetaNarrador;
	private JLabel etiquetaSilbato;
	
	private JLabel etiquetaFondoNarrador;

	private Font fuenteTitulo = new Font("Comic Sans MS", 1, 25);
	private Font fuenteTexto = new Font("Comic Sans MS", 0, 16);
	private Font fuenteTexto2 = new Font("Comic Sans MS", 0, 14);
	private Color colorResalte = new Color(66, 66, 66);
	private Color colorNegativo = new Color(222, 66, 80);
	private Color colorPositivo = new Color(180, 225, 120);
	public static boolean eventoActivado = false;
	public static boolean movimientoJugador = false;
	private boolean partidaIniciada;
	private int cantidadRondas = Inicio.cantidadRondas;
	private Jugador listaJugadores[] = new Jugador[Inicio.cantidadJugadores];
	public Jugador jugadorActual;
	private Tablero tablero;
	private int numeroDadoUno, numeroDadoDos, numeroDados;
	private int numeroCasillaAlazar;
	private int rondasTerminadas=1;
	private Casilla casillaAlazar;
	private Evento evento;
	public PilaEventos pilaEventos= new PilaEventos();
	
	public Partida() {
		setTitle("Datos Party I");
		setVisible(true);
		setSize(1000, 750);
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
			listaJugadores[i] = new Jugador(i, tablero.caminoPrincipal.primeraCasilla);
		}

		jugadorActual = listaJugadores[0];
	}

	private void agregarDados() {
		etiquetaDados = new JLabel();
		etiquetaDados.setSize(120, 120);
		etiquetaDados.setLocation(20, panelPartida.getHeight() - etiquetaDados.getHeight() - 20);
		etiquetaDados.setIcon(new ImageIcon(imagenDados.getImage().getScaledInstance(etiquetaDados.getWidth(), etiquetaDados.getHeight(), Image.SCALE_SMOOTH)));
		etiquetaDados.addMouseListener(this);
		panelPartida.add(etiquetaDados);
		
		etiquetaNumeroDados = new JLabel();
		etiquetaNumeroDados.setSize(etiquetaDados.getWidth(), 50);
		etiquetaNumeroDados.setLocation(etiquetaDados.getX(), etiquetaDados.getY() - 50);
		etiquetaNumeroDados.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaNumeroDados.setFont(fuenteTitulo);
		etiquetaNumeroDados.setForeground(Color.WHITE);
		etiquetaNumeroDados.setVisible(false);
		panelPartida.add(etiquetaNumeroDados);

	}

	private void agregarTienda() {
		etiquetaTienda = new JLabel();
		etiquetaTienda.setSize(100, 100);
		etiquetaTienda.setLocation(panelPartida.getWidth() - etiquetaTienda.getWidth() - 20, 20);
		etiquetaTienda.setIcon(new ImageIcon(imagenTienda.getImage().getScaledInstance(etiquetaTienda.getWidth(), etiquetaTienda.getHeight(), Image.SCALE_SMOOTH)));
		etiquetaTienda.addMouseListener(this);
		panelPartida.add(etiquetaTienda);

	}

	private void actualizarInfoJugadorActual() {
		if (etiquetaFondoInfoJugadorActual == null) {

			etiquetaFondoInfoJugadorActual = new JLabel();
			etiquetaFondoInfoJugadorActual.setBounds(20, 20, 150, 150);
			etiquetaFondoInfoJugadorActual.setOpaque(true);
			etiquetaFondoInfoJugadorActual.setBackground(colorResalte);
			panelPartida.add(etiquetaFondoInfoJugadorActual);

			etiquetaNombreJugadorActual = new JLabel();
			etiquetaNombreJugadorActual.setBounds(0, 0, etiquetaFondoInfoJugadorActual.getWidth(), 40);
			etiquetaNombreJugadorActual.setHorizontalAlignment(SwingConstants.CENTER);
			etiquetaNombreJugadorActual.setFont(fuenteTitulo);
			etiquetaNombreJugadorActual.setForeground(Color.white);
			etiquetaFondoInfoJugadorActual.add(etiquetaNombreJugadorActual);

			etiquetaMonedasJugadorActual = new JLabel();
			etiquetaMonedasJugadorActual.setSize(75, 30);
			etiquetaMonedasJugadorActual.setLocation((etiquetaFondoInfoJugadorActual.getWidth() / 2), (etiquetaFondoInfoJugadorActual.getHeight() / 2) - (etiquetaMonedasJugadorActual.getHeight() / 2));
			etiquetaMonedasJugadorActual.setFont(fuenteTexto);
			etiquetaMonedasJugadorActual.setForeground(Color.white);
			etiquetaFondoInfoJugadorActual.add(etiquetaMonedasJugadorActual);

			etiquetaMoneda = new JLabel();
			etiquetaMoneda.setSize(30, 30);
			etiquetaMoneda.setLocation(etiquetaMonedasJugadorActual.getX() - etiquetaMoneda.getWidth(), etiquetaMonedasJugadorActual.getY());
			etiquetaMoneda.setIcon(new ImageIcon(imagenMoneda.getImage().getScaledInstance(etiquetaMoneda.getWidth(), etiquetaMoneda.getHeight(), Image.SCALE_SMOOTH)));
			etiquetaMoneda.addMouseListener(this);
			etiquetaFondoInfoJugadorActual.add(etiquetaMoneda);

			etiquetaEstrellasJugadorActual = new JLabel();
			etiquetaEstrellasJugadorActual.setBounds(etiquetaMonedasJugadorActual.getX(), etiquetaMonedasJugadorActual.getY() + etiquetaMonedasJugadorActual.getHeight(), 75, 30);
			etiquetaEstrellasJugadorActual.setFont(fuenteTexto);
			etiquetaEstrellasJugadorActual.setForeground(Color.white);
			etiquetaFondoInfoJugadorActual.add(etiquetaEstrellasJugadorActual);

			etiquetaEstrella = new JLabel();
			etiquetaEstrella.setBounds(etiquetaMoneda.getX(), etiquetaMoneda.getY() + etiquetaMoneda.getHeight(), 30, 30);
			etiquetaEstrella.setIcon(new ImageIcon(imagenEstrella.getImage().getScaledInstance(etiquetaEstrella.getWidth(), etiquetaEstrella.getHeight(), Image.SCALE_SMOOTH)));
			etiquetaEstrella.addMouseListener(this);
			etiquetaFondoInfoJugadorActual.add(etiquetaEstrella);
			
			etiquetaFondoNarrador=new JLabel ();
			etiquetaFondoNarrador.setSize(205, 100);
			etiquetaFondoNarrador.setLocation(panelPartida.getWidth() - etiquetaFondoNarrador.getWidth() - 20, panelPartida.getHeight() - etiquetaFondoNarrador.getHeight() - 20);
			etiquetaFondoNarrador.setOpaque(true);
			etiquetaFondoNarrador.setBackground(colorResalte);
			panelPartida.add(etiquetaFondoNarrador);
			
			etiquetaRondas= new JLabel();
			etiquetaRondas.setBounds(0, 0, etiquetaFondoNarrador.getWidth(), 35);
			etiquetaRondas.setHorizontalAlignment(SwingConstants.CENTER);
			etiquetaRondas.setFont(fuenteTitulo);
			etiquetaRondas.setForeground(Color.white);
			etiquetaFondoNarrador.add(etiquetaRondas);
			
			etiquetaNarrador= new JLabel ();
			etiquetaNarrador.setSize(etiquetaFondoNarrador.getWidth(), 35);
			etiquetaNarrador.setLocation(0, etiquetaFondoNarrador.getHeight() - etiquetaNarrador.getHeight());
			etiquetaNarrador.setHorizontalAlignment(SwingConstants.CENTER);
			etiquetaNarrador.setFont(fuenteTexto2);
			etiquetaNarrador.setForeground(Color.yellow);
			etiquetaFondoNarrador.add(etiquetaNarrador);
			etiquetaNarrador.setText(jugadorActual.nombreJugador+" tira los dados!");
			
			etiquetaSilbato= new JLabel();
			etiquetaSilbato.setSize(30, 30);
			etiquetaSilbato.setLocation(etiquetaFondoNarrador.getWidth() / 2 - etiquetaSilbato.getWidth() / 2 , etiquetaFondoNarrador.getHeight() / 2 - etiquetaSilbato.getHeight() / 2);
			etiquetaSilbato.setIcon(new ImageIcon(imagenSilbato.getImage().getScaledInstance(etiquetaSilbato.getWidth(), etiquetaSilbato.getHeight(), Image.SCALE_SMOOTH)));
			etiquetaFondoNarrador.add(etiquetaSilbato);
			
		}	
		etiquetaRondas.setText("Ronda " + rondasTerminadas + " de " + Inicio.cantidadRondas);
		etiquetaNombreJugadorActual.setText(jugadorActual.nombreJugador);
		etiquetaMonedasJugadorActual.setText(": " + String.valueOf(jugadorActual.monedasJugador));
		etiquetaEstrellasJugadorActual.setText(": " + String.valueOf(jugadorActual.estrellasJugador));
	}

	private void mezclarDados() {
		numeroDadoUno = (int) Math.floor(Math.random()*6+1);
		numeroDadoDos = (int) Math.floor(Math.random()*6+1);     
		numeroDados = numeroDadoUno + numeroDadoDos;
		

	}
	
	private void agregarEstrella() {
		numeroCasillaAlazar = (int) Math.floor(Math.random()*66 + 3);
		casillaAlazar = tablero.encontrarCasilla(numeroCasillaAlazar);
		casillaAlazar.ponerEstrella();
	}

	private void activarMinijuego() {
		new MinijuegoCinco(listaJugadores);
		System.out.println("El orden de la listade jugadores es el siguiente");
		int i=0;
		while (i<4) {
			System.out.println(listaJugadores[i].nombreJugador);
			i+=1;
		}
	}

	private void comprobarEvento() {
		if (eventoActivado == true) {
			eventoActivado = false;

			pilaEventos.seek(jugadorActual, listaJugadores, tablero);
			pilaEventos.pop();
		}
	}

	private void comprobarEventoDuelo() {
		for (int i = 0; i < listaJugadores.length - 1; i++) {
			for (int j = i + 1; j < listaJugadores.length; j++) {
				if (listaJugadores[i].casillaActual.numeroCasilla == listaJugadores[j].casillaActual.numeroCasilla) {
//					evento.eventoDuelo(listaJugadores[i], listaJugadores[j]);
					break;
				}
			}
		}
	}
	
	private void tirarDados() throws InterruptedException {
		if (partidaIniciada == false) {
			partidaIniciada = true;
		}
		else {
			siguienteTurno();
			actualizarInfoJugadorActual();
		}
		movimientoJugador = true;
		etiquetaDados.setBackground(colorNegativo);
		
		mezclarDados();
		etiquetaNumeroDados.setText(String.valueOf(numeroDados));
		etiquetaNumeroDados.setVisible(true);

		etiquetaNarrador.setVisible(false);
		etiquetaSilbato.setVisible(false);

	    new Thread(this).start();
	    
	}

	private void siguienteTurno() {
		if (jugadorActual.numeroJugador == listaJugadores.length - 1) {
			jugadorActual = listaJugadores[0];
			cantidadRondas -= 1;
			rondasTerminadas += 1;
			if (cantidadRondas == 0) {
				finPartida();
			}
			else {

				agregarEstrella();
			}
		}
		else {
			jugadorActual = listaJugadores[jugadorActual.numeroJugador + 1];
		}
	}

	private void finPartida() {
		this.setVisible(false);
		Final f = new Final(listaJugadores);

	}

	@Override
	public void run() {
		jugadorActual.moverJugador(numeroDados);
		
		comprobarEvento();
		actualizarInfoJugadorActual();
		
		movimientoJugador = false;
		etiquetaDados.setBackground(Bienvenida.colorVentana);
		etiquetaNumeroDados.setVisible(false);

		if (jugadorActual.numeroJugador + 2 > listaJugadores.length) {
			etiquetaNarrador.setText("Jugador 1 tira los dados!");
	    }
	    else {
			int numeroJugadorSiguiente = jugadorActual.numeroJugador + 2;
			etiquetaNarrador.setText("Jugador " + numeroJugadorSiguiente + " tira los dados!");
		}
		
		comprobarEventoDuelo();		
	    if (jugadorActual.numeroJugador==Inicio.cantidadJugadores-1) {
	    	activarMinijuego();
	    }
		
		
		etiquetaNarrador.setVisible(true);
		etiquetaSilbato.setVisible(true);

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//Pruebas con el minijuego esta linea de abajo se debe borrar
		activarMinijuego();
		if (e.getSource() == etiquetaDados) {
			if (movimientoJugador == false) {
				try {
					tirarDados();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
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
					jugadorActual.monedasJugador -= 500;
					jugadorActual.estrellasJugador += 1;
					jugadorActual.comprarEstrella = false;
					actualizarInfoJugadorActual();
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
			if (movimientoJugador == false) {
				etiquetaDados.setBackground(colorResalte);
			}
			else {
				etiquetaDados.setBackground(colorNegativo);
			}
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

	public static void main(String[] args){
		Partida p = new Partida();
	}
}
