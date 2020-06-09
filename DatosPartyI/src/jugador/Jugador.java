package jugador;

import java.awt.Image;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import juego.Bienvenida;
import juego.Partida;
import tablero.Casilla;
/**
 * Esta clase hace objetos llamados jugadores que representan a cada uno de los 
 * usuarios que deseen jugar el juego.
 * Estos pueden almacenar la información relacionada a puntajes del juego y 
 * controlar los movimeintos de cada jugador en el tablero.
 * @author Juan Navarro
 *
 */
public class Jugador {
//    __Atributos de las características más importantes de cada jugador
//___/
	public String nombreJugador;
	public int monedasJugador = 1000;
	public int estrellasJugador = 2;
	public int numeroJugador;
	int guardadoCorreccionX, guardadoCorreccionY;
	public int puntajeMinijuego;
	public int numeroJugadorMinijuego;
	public int puntajeTotal;
	public String textoResultados;
//	  __Atributos que controlan los movimientos que debe realizar el jugador depesndiendo de su ubicación actual
//___/
	private boolean direccionInversa;
	private boolean direccionAuxiliar;
	private boolean direccionTeletransporte;
	public boolean comprarEstrella;
	private boolean seguirMovimiento;
	
//     __ Atributos que contralan la posicion del jugador en la interfaz (Se colocan en el panel estático de Partida)
//____/

	public JLabel etiquetaImagen;
	private ImageIcon imagenJugador;
	protected int correccionCoordenadaX, correccionCoordenadaY;
	public Casilla casillaActual; 
	
	/**
	 * Contructor de la clase, define sus atributos iniciales y coloca su imagen en la casilla inicial
	 * @param numeroJugador : int corresponde a un identificador de cada jugador, así como el nombre de cada uno
	 * @param casillaInicial: Casilla es la posición en la que inicia el juego así que entre como parámetro para que el jugador se pueda ubicar ahí
	 */
	public Jugador(int numeroJugador, Casilla casillaInicial) {
		casillaActual = casillaInicial;
		this.numeroJugador = numeroJugador;
		
		etiquetaImagen = new JLabel();
		etiquetaImagen.setSize(20, 20);
		
		if (numeroJugador == 0) {
			imagenJugador= new ImageIcon("Imagenes/Token1.png");
			correccionCoordenadaX = (casillaActual.etiquetaCasilla.getWidth() / 2) - etiquetaImagen.getWidth();
			correccionCoordenadaY = (casillaActual.etiquetaCasilla.getHeight() / 2) - etiquetaImagen.getHeight();
			nombreJugador = "Jugador 1";
		}
		else if (numeroJugador == 1) {
			imagenJugador= new ImageIcon("Imagenes/Token2.png");
			correccionCoordenadaX = (casillaActual.etiquetaCasilla.getWidth() / 2);
			correccionCoordenadaY = (casillaActual.etiquetaCasilla.getHeight() / 2) - etiquetaImagen.getHeight();;
			nombreJugador = "Jugador 2";
		}
		else if (numeroJugador == 2) {
			imagenJugador=new ImageIcon("Imagenes/Token3.png");
			correccionCoordenadaX = (casillaActual.etiquetaCasilla.getWidth() / 2) - etiquetaImagen.getWidth();
			correccionCoordenadaY = (casillaActual.etiquetaCasilla.getHeight() / 2);
			nombreJugador = "Jugador 3";
		}
		else if (numeroJugador == 3) {
			imagenJugador = new ImageIcon("Imagenes/Token4.png");
			correccionCoordenadaX = (casillaActual.etiquetaCasilla.getWidth() / 2);
			correccionCoordenadaY = (casillaActual.etiquetaCasilla.getHeight() / 2);
			nombreJugador = "Jugador 4";
		}
		guardadoCorreccionX = correccionCoordenadaX;
		guardadoCorreccionY = correccionCoordenadaY;

		etiquetaImagen.setLocation(correccionCoordenadaX, correccionCoordenadaY);
		etiquetaImagen.setIcon(new ImageIcon (imagenJugador.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH)));
		casillaActual.etiquetaCasilla.add(etiquetaImagen);
	}
	
	/**
	 * Este método analiza la posición de cada jugador para determinar si ocupa 
	 * moverse hacia alguna de las posiciones especiales del tablero y también 
	 * para determinar la direción en la que se debe mover.
	 */
	public void verificarDireccion() {
		if (casillaActual.tipoCasilla.equals("Morada")) {
			direccionTeletransporte= true;
			direccionAuxiliar = false;
			direccionInversa=false;
		}
		else if (casillaActual.tipoCasilla.equals("Verde")) {
			direccionTeletransporte = false;
			direccionAuxiliar= true;
			direccionInversa= false;
		}
		else if (casillaActual.tipoCasilla.equals("Roja")) {
			direccionTeletransporte = false;
			direccionAuxiliar= false;
			direccionInversa= true;
		}
		else if (casillaActual.tipoCasilla.equals("Azul")) {
			direccionTeletransporte= false;
			direccionAuxiliar=false;
			direccionInversa=false;
		}
		else if (casillaActual.tipoCasilla.equals("Amarilla")) {
			direccionTeletransporte= false;
		}
	}
	
	/**
	 * Este método permite que el jugador se mueva a lo largo del tablero dependiendo del 
	 * número obtenido en los dados.
	 * Para poder ubicar a el jugador en las casillas correspondientes hay un atributo del objeto
	 * llamado casillaActual, el cual representa a la casilla en la cual se encuentra cada jugador
	 * en cada instante, todas las casilla poseen el atributo de su posición entonces se copia y se le suma un poco
	 * a la posición para evitar que los jugadores se superpongan.
	 * 
	 *  Este método considera los distintos caminos que podría tomar el jugador dependiendo de la 
	 *  posición por la cuál se empiece a mover.
	 *  
	 * @param numeroDados indica cuántas posiciones se va a mover
	 * @throws InterruptedException 
	 */
	public void moverJugador(int numeroDados) {
		verificarDireccion();
		while (numeroDados != 0) {
			verificarEstrella();
			if (direccionInversa == true) {
				casillaActual = casillaActual.anteriorCasilla;
				verificarDireccion();
				if (direccionAuxiliar == true) {
					direccionAuxiliar = false;
				}
			}
			else {
				if (direccionAuxiliar == true) {
					casillaActual = casillaActual.siguienteCasillaAux;
					direccionAuxiliar = false;
				}

				else {
					if (casillaActual.siguienteCasilla == null) {
						casillaActual = casillaActual.anteriorCasilla;
						direccionInversa = true;
					}
					else {
						casillaActual = casillaActual.siguienteCasilla;
					}
				}
			}
			
			if (casillaActual.tipoCasilla.equals("Morada")) {
				correccionCoordenadaX = (casillaActual.etiquetaCasilla.getWidth() / 2) - (etiquetaImagen.getWidth() / 2);
				correccionCoordenadaY = (casillaActual.etiquetaCasilla.getHeight() / 2) - (etiquetaImagen.getHeight() / 2);

			}
			cambiarPosicionJugador();
			numeroDados -= 1;

		}
		
		verificarEstrella();
		verificarDireccion();
		if (direccionTeletransporte == true) {
			casillaActual = casillaActual.teletransporteCasilla;
			
			correccionCoordenadaX = (casillaActual.etiquetaCasilla.getWidth() / 2) - (etiquetaImagen.getWidth() / 2);
			correccionCoordenadaY = (casillaActual.etiquetaCasilla.getHeight() / 2) - (etiquetaImagen.getHeight() / 2);
			
			cambiarPosicionJugador();
		}
		
		verificarTipoCasilla();
	}
		
	/**
	 * Este método verifica si hay una estrella en cada casilla que recorre
	 * para poder habilitar la tienda de estrellas por medio de una variable
	 * estática llamada comprarEstrella
	 */
	public void verificarEstrella() {
		if (casillaActual.estrellaEncima == true) {
			comprarEstrella = true;
			casillaActual.eliminarEstrella();	
		}
	}
	/**
	 * Al finalizar el movimiento el jugador debe analizar la casilla en la 
	 * que se encuentra y aplicar la respectiva instrucción de la casilla
	 * Verdes: te dan monedas
	 * Rojas: te quitan monedas
	 * Azules y moradas: no se ejecuta nada
	 * morada: no se ejecuta nada, solo cambia de camino
	 */
	public void verificarTipoCasilla() {
		if (casillaActual.tipoCasilla.equals("Verde")) {
			this.monedasJugador += 300;
			JOptionPane.showMessageDialog(null,"El "+this.nombreJugador+ " ha ganado 300 monedas", "Accion de casilla", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (casillaActual.tipoCasilla.equals("Roja")) {
			if (this.monedasJugador>300) {
				this.monedasJugador -= 300;
				JOptionPane.showMessageDialog(null,"El "+this.nombreJugador+  " ha perdido 300 monedas", "Accion de casilla", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if (casillaActual.tipoCasilla.equals("Amarilla")) {
			Partida.eventoActivado=true;
		}
		
	}
	/**
	 * Este método ayuda a refacatorizar el método de moverJugador()
	 * en este se ejecuta un movmiento y se detiene el hilo actual
	 * pare apreciar cómo se mueve el jugador.
	 */
	private void cambiarPosicionJugador()  {
		etiquetaImagen.setLocation(correccionCoordenadaX, correccionCoordenadaY);
		if (casillaActual.tipoCasilla.equals("Morada")) {
			correccionCoordenadaX = guardadoCorreccionX;
			correccionCoordenadaY = guardadoCorreccionY;
		}
		casillaActual.etiquetaCasilla.add(etiquetaImagen);
		casillaActual.etiquetaCasilla.repaint();
		Partida.panelPartida.repaint();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}