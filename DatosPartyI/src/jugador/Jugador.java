package jugador;

import javax.swing.JLabel;

import tablero.Casilla;

public class Jugador {
	public String nombreJugador= null;
	public int monedasJugador =100;
	public int estrellasJugador = 0;
	public int numeroJugador;
	private boolean direccionInversa= false;
	private boolean direccionAuxiliar= false;
	private boolean direccionTeletransporte= false;
	public boolean comprarEstrella= false;
	public JLabel etiquetaImagen;
	protected int correccionCoordenadaX, correccionCoordenadaY;
	protected Casilla casillaActual; 
	

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
	}
	
	public void moverJugador( int numeroDados) {
		verificarDireccion();
		
		while(numeroDados!=0) {
			if (direccionInversa==true) {
				casillaActual= casillaActual.casillaAnterior;			
				etiquetaImagen.setLocation(casillaActual.coordenadaCasillaX+ this.correccionCoordenadaX, casillaActual.coordenadaCasillaY+this.correccionCoordenadaY);
				numeroDados-=1;
				verificarDireccion();
				if (direccionAuxiliar==true) {
					direccionAuxiliar= false;
				}
			}else {
			if (direccionAuxiliar==true) {
				casillaActual.getCasilllaSiguienteAux();
				direccionAuxiliar= false;
			}else {
				casillaActual= casillaActual.getCasillaSiguiente();
			}
			}
			//etiquetaImagen.setLocation(casillaActual.coordenadaCasillaX+ this.correccionCoordenadaX, casillaActual.coordenadaCasillaY+this.correccionCoordenadaY);
			numeroDados-=1;
		}
		
		verificarDireccion();
		if (direccionTeletransporte==true) {
			casillaActual= Casilla.casillaTeletransporte;
			etiquetaImagen.setLocation(casillaActual.coordenadaCasillaX+ this.correccionCoordenadaX, casillaActual.coordenadaCasillaY+this.correccionCoordenadaY);
		}
		verificarTipoCasilla();
	}
	
	public void verificarEstrella (int casilla) {
		
	}
	
	public void verificarTipoCasilla() {
		
	}

}
