package evento;

public class Evento {
	public Evento next;
	public int tipoEvento;
	
	public Evento(int tipoEvento) {
		this.tipoEvento= tipoEvento;
	}
	/**
	 * Este método llama a la respectiva acción de la pila 
	 *Las diferentes acicones según su número son: 
	 *1) Duelo: Se activa un minijuego, donde los jugadores son escogidos de manera aleatoria
	 *se enfrentan en un minijuego 1 vs 1
	 *2)Robar monedas: El jugador que obtiene el evento tiene derecho de robarle una cantidad 
	 *de monadas aleatorias a otro jugador que escoja
	 *3) Regalar monedas: El jugador que activa el evento pierde una cantidad aleatoria de monedas, las cuales 
	 *4) Perder una estrella
	 *5)Ganar 2 estrellas: 
	 *6) Ganar 5 estrellas:
	 *7)Robar estrella:
	 *8) Teletransporte
	 *9)Cambio de lugares:
	 */
	public void ejecutarEvento() {
		if (tipoEvento==1) {
			System.out.println("Se ejecuta evento: "+ tipoEvento);
		}
		else if (tipoEvento==2){
			System.out.println("Se ejecuta evento: "+ tipoEvento);
		}
		else if (tipoEvento==3){
			System.out.println("Se ejecuta evento: "+ tipoEvento);
		}
		else if (tipoEvento==4){
			System.out.println("Se ejecuta evento: "+ tipoEvento);
		}
		else if (tipoEvento==5){
			System.out.println("Se ejecuta evento: "+ tipoEvento);
		}
		else if (tipoEvento==6){
			System.out.println("Se ejecuta evento: "+ tipoEvento);
		}
		else if (tipoEvento==7){
			System.out.println("Se ejecuta evento: "+ tipoEvento);
		}
		else if (tipoEvento==8){
			System.out.println("Se ejecuta evento: "+ tipoEvento);
		}
		else if (tipoEvento==9){
			System.out.println("Se ejecuta evento: "+ tipoEvento);
		}
	}
}
