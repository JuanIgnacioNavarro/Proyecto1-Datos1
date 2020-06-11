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

import evento.PilaEventos;
import jugador.Jugador;
import tablero.Tablero;
import tablero.Casilla;

/**
 * Clase donde se reunen todos los componentes del juego
 * @author Andres Martinez Vargas
 * 
 */
public class Partida extends JFrame implements MouseListener, Runnable {
	public static JPanel panelPartida;
	private ImageIcon imagenDados = new ImageIcon("Imagenes/Dados.png");
	private ImageIcon imagenTienda = new ImageIcon("Imagenes/EstrellaExtra.png");
	private ImageIcon imagenMoneda = new ImageIcon("Imagenes/Moneda.png");
	private ImageIcon imagenEstrella = new ImageIcon("Imagenes/Estrella.png");
	private ImageIcon imagenSilbato= new ImageIcon ("Imagenes/silbato.png");

	private Font fuenteTitulo = new Font("Comic Sans MS", 1, 25);
	private Font fuenteTexto = new Font("Comic Sans MS", 0, 16);
	private Font fuenteTexto2 = new Font("Comic Sans MS", 0, 14);

	private Color colorResalte = new Color(66, 66, 66);
	private Color colorNegativo = new Color(222, 66, 80);
	private Color colorPositivo = new Color(180, 225, 120);

	private JLabel etiquetaDados, etiquetaNumeroDados, etiquetaTienda, etiquetaMoneda, etiquetaEstrella, 
	etiquetaFondoInfoJugadorActual, etiquetaNombreJugadorActual, etiquetaMonedasJugadorActual, 
	etiquetaEstrellasJugadorActual, etiquetaRondas, etiquetaNarrador, etiquetaSilbato, etiquetaFondoNarrador;

	private Jugador listaJugadores[] = new Jugador[Inicio.cantidadJugadores], jugadorActual;
	private Tablero tablero;
	private Casilla casillaAlazar;
	public PilaEventos pilaEventos = new PilaEventos();

	public static boolean eventoActivado, minijuegoActivado, movimientoJugador, partidaIniciada;
	private int numeroCasillaAlazar, cantidadRondas = Inicio.cantidadRondas, rondasTerminadas = 1, numeroDadoUno, numeroDadoDos, numeroDados;
	
	/**
	 * Constructor de la clase, el cual genera tanto los componentes de la ventana como los de la partida
	 */
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

	/**
	 * Metodo pora agragar los componentes de la ventana
	 */
	private void componentesVentana() {
		agregarPanel();
	}

	/**
	 * Metodo pora agragar los componentes de la partida
	 */
	private void componentesPartida() {
		agregarTablero();
		agregarJugadores();
		agregarDados();
		agregarTienda();
		actualizarInfoJugadorActual();

		panelPartida.repaint();
	}

	/**
	 * Metodo para instanciar un panel dentro de la ventana
	 */
	private void agregarPanel() {
		panelPartida = new JPanel();
		this.getContentPane().add(panelPartida);
		panelPartida.setLayout(null);
		panelPartida.setBackground(Bienvenida.colorVentana);
	}

	/**
	 * Metodo para instanciar el tablero de la partida
	 */
	private void agregarTablero() {
		tablero = new Tablero();
	}

	/**
	 * Metodo para instanciar los jugadores de la partida
	 */
	private void agregarJugadores() {
		for (int i = 0; i < Inicio.cantidadJugadores; i++) {
			listaJugadores[i] = new Jugador(i, tablero.caminoPrincipal.primeraCasilla);
		}

		jugadorActual = listaJugadores[0];
	}

	/**
	 * Metodo para agregar los dados del juego, asi como la etiqueta del resultado al tirarlos
	 */
	private void agregarDados() {
		etiquetaDados = new JLabel();
		etiquetaDados.setSize(120, 120);
		etiquetaDados.setLocation(20, this.getHeight() - etiquetaDados.getHeight() - 40);
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

	/**
	 * Metodo para agregar la tienda que permite comprar estrellas en la partida
	 */
	private void agregarTienda() {
		etiquetaTienda = new JLabel();
		etiquetaTienda.setSize(100, 100);
		etiquetaTienda.setLocation(this.getWidth() - etiquetaTienda.getWidth() - 20, 20);
		etiquetaTienda.setIcon(new ImageIcon(imagenTienda.getImage().getScaledInstance(etiquetaTienda.getWidth(), etiquetaTienda.getHeight(), Image.SCALE_SMOOTH)));
		etiquetaTienda.addMouseListener(this);
		panelPartida.add(etiquetaTienda);

	}

	/**
	 * Metodo para escoger aleatriamente un numero del 3 al 69 y ponerle a la casilla en dicha posicion una estrella
	 */
	private void agregarEstrella() {
		numeroCasillaAlazar = (int) Math.floor(Math.random()*66 + 3);
		casillaAlazar = tablero.encontrarCasilla(numeroCasillaAlazar);
		casillaAlazar.ponerEstrella();
	}

	/**
	 * Metodo para colocar los datos que tiene el jugador que va tirando
	 */
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
			etiquetaFondoNarrador.setLocation(this.getWidth() - etiquetaFondoNarrador.getWidth() - 20, this.getHeight() - etiquetaFondoNarrador.getHeight() - 40);
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

	/**
	 * Metodo para simular el resultado que daria sumar las caras superiores de dos dados al tirarlos
	 */
	private void mezclarDados() {
		numeroDadoUno = (int) Math.floor(Math.random()*6+1);
		numeroDadoDos = (int) Math.floor(Math.random()*6+1);     
		numeroDados = 5; //numeroDadoUno + numeroDadoDos;
	}

	/**
	 * Metodo para escoger aleatriamente un numero del 1 al 6 y activar dicho numero del minujuego
	 */
	private void activarMinijuego(Jugador [] listaJugadores) {
		
		minijuegoActivado = true;
		int numeroDeMinijuego;
		Random random = new Random();
		numeroDeMinijuego= random.nextInt(6)+1;
		if (numeroDeMinijuego==1) {
			new MinijuegoUno(listaJugadores);
		}
		else if (numeroDeMinijuego==2) {
			new MinijuegoDos(listaJugadores);
		}
		else if (numeroDeMinijuego==3) {
			new MinijuegoTres(listaJugadores);
		}
		else if (numeroDeMinijuego==4) {
			new MinijuegoCuatro(listaJugadores);
		}
		else if (numeroDeMinijuego==5) {
			new MinijuegoCinco(listaJugadores);
		}
		else {
			new MinijuegoSeis(listaJugadores);
		}
	}

	/**
	 * Metodo para comprobar si se debe activar un evento
	 */
	private void comprobarEvento() {
		if (eventoActivado == true) {
			eventoActivado = false;

			pilaEventos.seek(jugadorActual, listaJugadores, tablero);
			pilaEventos.pop();
		}
	}

	/**
	 * Metodo para comprobar si se debe activar un minijuego de duelo
	 * Se usa el metodo de activar minijuego con la lista de los jugadores que se encuentran en la misma posicion
	 * Luego en los metodos de resultados de cada minijuego se comprueba si el minijuego se dio por un evento duelo
	 * y se mueve el jugador a la siguiente casilla
	 */
	private void comprobarEventoDuelo(){
		for (int i = 0; i <= listaJugadores.length - 2; i++) {
			for (int j = i + 1; j <= listaJugadores.length - 1; j++) {
				//Debe comprobar si las posiciones de los jugadores son las mismas
				if (listaJugadores[i].casillaActual.numeroCasilla == listaJugadores[j].casillaActual.numeroCasilla && 
				listaJugadores[i].casillaActual.numeroCasilla != 1 && listaJugadores[j].casillaActual.numeroCasilla != 1) {
					minijuegoActivado = true; // el minijuego activado es para bloquear el boton de los dados
					Jugador[] listaMismaPosicion = {listaJugadores[i], listaJugadores[j]}; //Esta es la lista de los jugadores que se encunetran en la misma posicion 
					activarMinijuego(listaMismaPosicion); //Se activa uno de los minijuegos
					break;
				}
			}
		}
	}

	/**
	 * Metodo que da inicio al turno de un jugador, este actuailza la informacion del nuevo jugador, 
	 * desactiva poder tirar nuevamente los dados, mezcla los dados y dice el resultado, actualiza al narrador y
	 * activa un Thread para observar el movimiento del jugador
	 * @throws El metodo puede manejar excepciones de tipo InterruptedException
	 */
	private void tirarDados() throws InterruptedException {
		if (partidaIniciada == false) {
			partidaIniciada = true;
		}
		else {
			siguienteTurno();
			actualizarInfoJugadorActual();
		}
		if (cantidadRondas != 0) {
			movimientoJugador = true;
			etiquetaDados.setBackground(colorNegativo);
			
			mezclarDados();
			etiquetaNumeroDados.setText(String.valueOf(numeroDados));
			etiquetaNumeroDados.setVisible(true);
	
			etiquetaNarrador.setVisible(false);
			etiquetaSilbato.setVisible(false);
	
			new Thread(this).start();
		}
	}

	/**
	 * Metodo para cambiar al siguiente jugador, agregar una estrella al comenzar una ronda y verificar si el juego termina
	 */
	private void siguienteTurno() {
		if (jugadorActual.numeroJugador == listaJugadores.length - 1) {
			jugadorActual = listaJugadores[0];
			cantidadRondas -= 1;
			rondasTerminadas += 1;
			if (cantidadRondas == 0) {
				JOptionPane.showMessageDialog(null,"El juego ha terminado, ver resultados?", "Jeugo terminado", JOptionPane.INFORMATION_MESSAGE);
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

	/**
	 * Metodo quitar la ventana de partida y poner la ventana final
	 */
	private void finPartida() {
		this.setVisible(false);
		Final f = new Final(listaJugadores);

	}

	/**
	 * Metodo para mover al jugador actual por el tablero por medio de un Thread, 
	 * comprobar al finalizar del recorrido si debe aparecer un evento y actualizar la informacion del narrador
	 */
	@Override
	public void run() {
		jugadorActual.moverJugador(numeroDados);
		
		if (jugadorActual.numeroJugador != listaJugadores.length - 1) {
			comprobarEventoDuelo();
		}
		else {
			activarMinijuego(listaJugadores);
		}
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
		
		etiquetaNarrador.setVisible(true);
		etiquetaSilbato.setVisible(true);

	}
	
	/**
	 * Metodo para saber cuando se le hace click a los dados o en la tienda, 
	 * El de los dados solo servira si no hay algun jugador miviendose por el tablero y si no hay un minijuego en curso
	 * El de la tienda solo servir si no hay un minijuego en curso
	 * @param e - tipo: MouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == etiquetaDados) {
			if (movimientoJugador == false && minijuegoActivado == false) {
				try {
					tirarDados();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

		else if (e.getSource() == etiquetaTienda) {
			if (minijuegoActivado == false) {
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
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Metodo para saber cuando el mouse entra al espacio de los dados o en la tienda, 
	 * De ser los dados: se pondra color rojo si hay algun jugador miviendose por el tablero o 
	 * si hay un minijuego en curso y gris oscuro en caso de no cumplirse alguna de estas condiciones mencionadas
	 * De ser la tienda: se pondra rojo si un jugador no ha pasado por una casilla con estrella o 
	 * no tiene suficiente dinero para comprarla o hay algun minijuego en curso y gris oscuro en caso de no cumplirse todas estas condiciones mencionadas
	 * @param e - tipo: MouseEvent
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == etiquetaDados) {
			etiquetaDados.setOpaque(true);
			if (movimientoJugador == false && minijuegoActivado == false) {
				etiquetaDados.setBackground(colorResalte);
			}
			else {
				etiquetaDados.setBackground(colorNegativo);
			}
		}
		else if (e.getSource() == etiquetaTienda) {
			etiquetaTienda.setOpaque(true);
			if (jugadorActual.comprarEstrella == false || jugadorActual.monedasJugador < 500 || minijuegoActivado == true) {
				etiquetaTienda.setBackground(colorNegativo);
			}
			else {
				etiquetaTienda.setBackground(colorPositivo);
			}
		}
	}

	/**
	 * Metodo para saber cuando el mouse sale al espacio de los dados o en la tienda, 
	 * en cualquier caso se pondra del color de la ventana
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == etiquetaDados) {
			etiquetaDados.setBackground(Bienvenida.colorVentana);
		}

		else if (e.getSource() == etiquetaTienda) {
			etiquetaTienda.setBackground(Bienvenida.colorVentana);
		}
	}
}
