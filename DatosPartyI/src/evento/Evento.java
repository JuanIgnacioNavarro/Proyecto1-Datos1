package evento;

import java.util.Random;
import tablero.Tablero;

import javax.swing.JOptionPane;

import juego.Inicio;
import juego.Partida;
import jugador.Jugador;


/**
 * Esta clase se usa como nodo de una pila, por eso uno de sus atributos 
 * es un Evento llamado next.
 * Adem�s es capaz de ejecutar todos los eventos descritos en la descripci�n del proyecto
 * @author Juan Navarro
 */
public class Evento {
	public Evento next;
	public int tipoEvento;
	/**
	 * Constructor de la clase eventos, solo se usa para generar la pila
	 * @param tipoEvento : Integer
	 */
	public Evento(int tipoEvento) {
		this.tipoEvento= tipoEvento;
	}
	/**
	 * Corresponde al �nico m�todo de los eventos, dado que en las instrucciones se
	 * detallan varios eventos este m�todo es capaz de ejecutar cualquiera 
	 * dependiendo del tipo de evento que se defini� al crear la pila
	 * Este m�todo llama a la respectiva acci�n de la pila 
	 *No retorna ningpun objeto en espec�fico, pero al usarse este m�todo se
	 *va a ejecutar cualquiera de las siguientes acciones, cuando se crean los nodos se 
	 *a�ade el tipo de evento
	 *1) Duelo: Se activa un minijuego, donde los jugadores son escogidos de manera aleatoria
	 *se enfrentan en un minijuego 1 vs 1
	 *2)Robar monedas: El jugador que obtiene el evento tiene derecho de robarle una cantidad 
	 *de monadas aleatorias a otro jugador que escoja
	 *3) Regalar monedas: El jugador que activa el evento pierde una cantidad aleatoria de monedas, las cuales 
	 *4) Perder una estrella: el jugador actual pierde una estrella
	 *5)Ganar 2 estrellas: El jugador actual gana una estrella
	 *6) Ganar 5 estrellas: El jugador actual gana 5 estrellas
	 *7)Robar estrella: el jugador actual roba una estrella
	 *8) Teletransporte: el jugador se va a teletransportar a culauquier otro lugar del tablero
	 *9)Cambio de lugares:el jugador va a cambiar de lugar con otro jugador aleatorio
	 */
	public void ejecutarEvento(Jugador jugadorActual, Jugador[] listaJugadores, Tablero tablero) {
		if (tipoEvento==1) {
			JOptionPane.showMessageDialog(null, "Se ha activado el evento de minijuego, hay que a�adir el contenido despu�s", "Evento Activado", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (tipoEvento==2){
			int randomInt;
			while (true) {
				Random random= new Random();
				randomInt= random.nextInt(Inicio.cantidadJugadores);
				if (jugadorActual.numeroJugador!=randomInt) {
					break;
				}
			}
			robarMonedas(jugadorActual, listaJugadores[randomInt]);
		}
		else if (tipoEvento==3){
			regalarMonedas(jugadorActual, listaJugadores);
		}
		else if (tipoEvento==4){
			int randomInt;
			while (true) {
				Random random= new Random();
				randomInt= random.nextInt(Inicio.cantidadJugadores);
				if (jugadorActual.numeroJugador!=randomInt) {
					break;
				}
			}
			pierdeEstrella(jugadorActual, listaJugadores[randomInt]);
		}
		else if (tipoEvento==5){
			jugadorActual.estrellasJugador+=2;
			JOptionPane.showMessageDialog(null, "�Wow! El "+jugadorActual.nombreJugador+ " se ha ganado 2 estrellas en el evento", "Evento Activado", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (tipoEvento==6){
			jugadorActual.estrellasJugador+=5;
			JOptionPane.showMessageDialog(null, "��Mega Wow!! El "+jugadorActual.nombreJugador+ " se ha ganado 5 estrellas en el evento", "Evento Activado", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (tipoEvento==7){
			int randomInt;
			while (true) {
				Random random= new Random();
				randomInt= random.nextInt(Inicio.cantidadJugadores);
				if (jugadorActual.numeroJugador!=randomInt) {
					break;
				}
			}
			robarEstrella(jugadorActual, listaJugadores[randomInt]);
		}
		else if (tipoEvento==8){
			teletransporte(jugadorActual, tablero);
		}
		else if (tipoEvento==9){
			int randomInt;
			while (true) {
				Random random= new Random();
				randomInt= random.nextInt(Inicio.cantidadJugadores);
				if (jugadorActual.numeroJugador!=randomInt) {
					break;
				}
			}
			cambiarPosiciones(jugadorActual, listaJugadores[randomInt], tablero);
		}
	}
	
	
	
	private void robarMonedas(Jugador jugadorActual, Jugador jugadorRandom) {
		int cantidadMonedas;
		int cantidadMaxMonedas;
		Random random= new Random();
		cantidadMaxMonedas= jugadorRandom.monedasJugador;
		cantidadMonedas= random.nextInt(cantidadMaxMonedas)+1;
		jugadorRandom.monedasJugador-= cantidadMonedas;
		jugadorActual.monedasJugador+=cantidadMonedas;
		JOptionPane.showMessageDialog(null, jugadorActual.nombreJugador+ " le ha robado "+cantidadMonedas+ " de monedas a "+ jugadorRandom.nombreJugador, "Evento Activado", JOptionPane.INFORMATION_MESSAGE);
	}
	private void regalarMonedas(Jugador jugadorActual, Jugador[] listaJugadores) {
		int cantidadJugadores= Inicio.cantidadJugadores-1;
		int cantidadMaxMonedas= (int)jugadorActual.monedasJugador/cantidadJugadores;
		Random random= new Random();
		int cantidadARegalar=random.nextInt(cantidadMaxMonedas)+1;
		jugadorActual.monedasJugador-=cantidadJugadores*cantidadARegalar;
		int i= Inicio.cantidadJugadores-1;
		while (i!=-1) {
			if (jugadorActual.numeroJugador==listaJugadores[i].numeroJugador) {
			}
			else {
			listaJugadores[i].monedasJugador+= cantidadARegalar;}
			i-=1;
			}
		JOptionPane.showMessageDialog(null, jugadorActual.nombreJugador+" le ha regalado "+cantidadARegalar+" monedas a todos los jugadores", "Evento Activado", JOptionPane.INFORMATION_MESSAGE);
	}
	private void pierdeEstrella(Jugador jugadorActual, Jugador jugadorRandom) {
		if (jugadorActual.estrellasJugador>0) {
		jugadorActual.estrellasJugador-=1;
		jugadorRandom.estrellasJugador+=1;
		JOptionPane.showMessageDialog(null, jugadorActual.nombreJugador+ " le ha regalado 1 estrella a "+ jugadorRandom.nombreJugador, "Evento Activado", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "El "+jugadorActual.nombreJugador+ " activ� el evento de regalar estrellas, pero no tiene estrellas", "Evento Activado", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private void robarEstrella(Jugador jugadorActual, Jugador jugadorRandom) {
		if (jugadorRandom.estrellasJugador>0) {
		jugadorActual.estrellasJugador+=1;
		jugadorRandom.estrellasJugador-=1;
		JOptionPane.showMessageDialog(null, jugadorActual.nombreJugador+ " le ha robado una estrella al "+ jugadorRandom.nombreJugador, "Evento Activado", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "El "+jugadorActual.nombreJugador+ " activ� el evento de robar una estrella, pero el "+jugadorRandom.nombreJugador+" no tiene estrellas", "Evento Activado", JOptionPane.INFORMATION_MESSAGE);
		}	
	}
	private void teletransporte(Jugador jugadorActual, Tablero tablero) {
		int numeroCasillaRandom;
		Random random= new Random();
		numeroCasillaRandom= random.nextInt(67+1)+2;
		jugadorActual.casillaActual=tablero.encontrarCasilla(numeroCasillaRandom);
		jugadorActual.casillaActual.etiquetaCasilla.add(jugadorActual.etiquetaImagen);
		jugadorActual.casillaActual.etiquetaCasilla.repaint();
		Partida.panelPartida.repaint();
		JOptionPane.showMessageDialog(null, "El "+jugadorActual.nombreJugador+ " ha sido teletransportado a otra casilla aleatoria", "Evento Activado", JOptionPane.INFORMATION_MESSAGE);
	
	}
	private void cambiarPosiciones(Jugador jugadorActual, Jugador jugadorRandom, Tablero tablero) {
		int numeroCasillaJugadorActual= jugadorActual.casillaActual.numeroCasilla;
		int numeroCasillaJugadorRandom= jugadorRandom.casillaActual.numeroCasilla;
		
		jugadorActual.casillaActual= tablero.encontrarCasilla(numeroCasillaJugadorRandom);
		jugadorActual.casillaActual.etiquetaCasilla.add(jugadorActual.etiquetaImagen);
		jugadorActual.casillaActual.etiquetaCasilla.repaint();
		
		jugadorRandom.casillaActual= tablero.encontrarCasilla(numeroCasillaJugadorActual);
		jugadorRandom.casillaActual.etiquetaCasilla.add(jugadorRandom.etiquetaImagen);
		jugadorRandom.casillaActual.etiquetaCasilla.repaint();
		
		Partida.panelPartida.repaint();
		JOptionPane.showMessageDialog(null, "El "+jugadorActual.nombreJugador+ " ha sido cambiado de lugar con el "+jugadorRandom.nombreJugador, "Evento Activado", JOptionPane.INFORMATION_MESSAGE);
	}
}
