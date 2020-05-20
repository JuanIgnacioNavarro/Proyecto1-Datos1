package jugador;

import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

import tablero.Casilla;

public class Jugador {

//    __Atributos de las características más importantes de cada jugador
//___/
	public String nombreJugador= "Jugador 1";
	public int monedasJugador =100;
	public int estrellasJugador = 0;
	public int numeroJugador;
	
//	  __Atributos que controlan los movimientos que debe realizar el jugador depesndiendo de su ubicación actual
//___/
	private boolean direccionInversa;
	private boolean direccionAuxiliar;
	private boolean direccionTeletransporte;
	public boolean comprarEstrella;
	
//     __ Atributos que contralan la posicion del jugador en la interfaz (Se colocan en el panel estático de Partida)
//____/

	public JLabel etiquetaImagen;
	protected int correccionCoordenadaX, correccionCoordenadaY;
	protected Casilla casillaActual; 
	
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
		else if (casillaActual.tipoCasilla.equals("Azul") || casillaActual.tipoCasilla.equals("Amarilla")) {
			direccionTeletransporte= false;
			direccionAuxiliar=false;
			direccionInversa=false;
		}
	}
	/**
	 * Este método permite que el jugador se mueva a lo largo del tablero dependiendo del 
	 * número obtenido en los dados.
	 * Para poder ubicar a el jugados en las casillas correspondientes hay un atributo del objeto
	 * llamado casillaActual, el cual representa a la casilla en la cual se encuentra cada jugador
	 * todas las casilla poseen el atributo de su posición entonces se copia y se le suma un poco
	 * a la posición para eitar que los jugadores se superpongan.
	 * 
	 *  Este método considera los distintos caminos que podría tomar el jugador dependiendo de la 
	 *  posición por la cuál se empiece a mover.
	 *  
	 * @param numeroDados indica cuántas posiciones se va a mover
	 */
	public void moverJugador(int numeroDados) {
		
		while(numeroDados!=0) {
			verificarDireccion();
			if (direccionInversa==true) {
				casillaActual= casillaActual.casillaAnterior;
			}
			else if (direccionAuxiliar==true) {
				casillaActual.getCasilllaSiguienteAux();
			}
			else if (direccionTeletransporte==true) {
				casillaActual= Casilla.casillaTeletransporte;
			}
			else {
				casillaActual= casillaActual.getCasillaSiguiente();
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			}
			etiquetaImagen.setLocation(casillaActual.coordenadaCasillaX+ this.correccionCoordenadaX, casillaActual.coordenadaCasillaY+this.correccionCoordenadaY);
			numeroDados-=1;	
			verificarTipoCasilla();
		}
	}
	
	public boolean verificarEstrella () {
		if (casillaActual.estrellaEncima==true) {
			return true;
		}else {
			return false;
		}
	}
	
	public void verificarTipoCasilla() {
		if (casillaActual.tipoCasilla.equals("Verde")) {
			this.monedasJugador+= 300;
		}
		else if (casillaActual.tipoCasilla.equals("Roja")) {
			this.monedasJugador-= 300;
		}
		else if (casillaActual.tipoCasilla.equals("Amarilla")) {
			
		}
	}

}
