package evento;

import java.util.Random;
import jugador.*;

public class PilaEventos {
	public Evento eventoHead= null;
//    __ Se hizo un array con todos los números de los eventos dela pila
//___/
	public int[] arrayTiposEventos= {1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,4,4,4,5,5,5,6,7,7,7,8,8,8,8,8,8,8,8,8,8,9,9,9,9,9};
	
	
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
	 * Este método elimina el método de la cabeza
	 */
	public void pop() {
		eventoHead= eventoHead.next;
	}
	/**
	 * Este método permite usar uno de los métodos de la clase evento
	 */
	public void seek(Jugador jugadorActual, Jugador[] listaJugadores) {
		eventoHead.ejecutarEvento();
	}
	
	public void imprimirPila () {
		Evento eventoActual= eventoHead;
		System.out.println ("Los tipos de eventos en el stack son");
		while (eventoActual!=null) {
			System.out.print(eventoActual.tipoEvento+ " ,");
			eventoActual= eventoActual.next;
		}
	}
}
