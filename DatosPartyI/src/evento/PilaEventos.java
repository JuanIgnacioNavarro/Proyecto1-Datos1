package evento;

import java.util.Random;
import tablero.Tablero;
import jugador.*;
/**
 * Corresponde a la pila de los eventos
 * En todo el juego solo se utiliza una pila de eventos entonces solo se instancia una vez
 * @author Juan Navarro
 *
 */
public class PilaEventos {
	public Evento eventoHead= null;
//    __ Se hizo un array con todos los números de los eventos dela pila
//___/
	public int[] arrayTiposEventos= {1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,4,4,4,5,5,5,6,7,7,7,8,8,8,8,8,8,8,8,8,8,9,9,9,9,9};

	/**
	 * Constructor de la PilaEventos
	 * toma el array de los atributos y lo desordena para luego añadir todos sus elementos en una pila
	 */
	public PilaEventos() {
		barajarArray(arrayTiposEventos);
		int i=arrayTiposEventos.length-1;
		while (i!=-1) {
			push(arrayTiposEventos[i]);
			i-=1;
		}
	}
	private void barajarArray (int[] array) {
		int index;
		Random random= new Random();
		for (int i= array.length-1; i>0; i--) {
			index= random.nextInt(i+1);
			if (index!=i) {
				array[index]^= array[i];
				array[i]^= array[index];
				array[index]^= array[i];	
			}
		}
	}
	
	
	/**
	 * Este método añade un nodo de primero (cada nodo es un evento)
	 * @param int tipo, corresponde al tipo de evento que se quiere crear
	 * La lista de tipo de evento se encuentra en el método ejecutarTipo()
	 */
	public void push(int tipo) {
		Evento newEvent = new Evento(tipo);
		newEvent.next=this.eventoHead;
		eventoHead= newEvent;
	}
	/**
	 * Este método elimina el método de la cabeza de la pila
	 */
	public void pop() {
		eventoHead= eventoHead.next;
	}
	/**
	 * Permite usar el ejecutar el Evento de la cabeza de la pila
	 */
	public void seek(Jugador jugadorActual, Jugador[] listaJugadores, Tablero tablero) {
		eventoHead.ejecutarEvento(jugadorActual, listaJugadores, tablero);
	}
	/**
	 * Permite imprimir la pila que se creó, este método es útil para corroporar que los
	 * otros métodos de esta clase funcionan bien.
	 */
	public void imprimirPila () {
		Evento eventoActual= eventoHead;
		while (eventoActual!=null) {
			eventoActual= eventoActual.next;
		}
	}
}
