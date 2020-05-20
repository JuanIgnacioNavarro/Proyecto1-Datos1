package jugador;

import javax.swing.JLabel;

import tablero.Casilla;

public class Jugador {
	public String nombreJugador = "Jugador 1";
	public String nombreJugador;
	public int monedasJugador = 100;
	public int estrellasJugador = 0;
	public int numeroJugador;
	private boolean direccionInversa;
	private boolean direccionAuxiliar;
	private boolean direccionTeletransporte;
	public boolean comprarEstrella;
	public JLabel etiquetaImagen;
	protected int correccionCoordenadaX, correccionCoordenadaY;
	protected Casilla casillaActual; 
	public Casilla casillaActual; 
	

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
			direccionAuxiliar = false;
			direccionInversa = true;
		}
		else if (casillaActual.tipoCasilla.equals("Azul")) {
			direccionTeletransporte= false;
			direccionAuxiliar=false;
			direccionInversa=false;
		}
	}
	
	public void moverJugador( int numeroDados) {
	public void moverJugador(int numeroDados, int cantidadCasillas) {
		verificarDireccion();
		
		while(numeroDados!=0) {
			if (direccionInversa==true) {
				casillaActual= casillaActual.casillaAnterior;			
				casillaActual= casillaActual.anteriorCasilla;			
				etiquetaImagen.setLocation(casillaActual.coordenadaCasillaX+ this.correccionCoordenadaX, casillaActual.coordenadaCasillaY+this.correccionCoordenadaY);
				numeroDados-=1;
				verificarDireccion();
				if (direccionAuxiliar==true) {
					direccionAuxiliar= false;
					direccionAuxiliar = false;
				}
			}
			
			else {
				if (direccionAuxiliar == true) {
					casillaActual = casillaActual.siguienteCasillaAux;
					direccionAuxiliar = false;
				}
				
				else {
					casillaActual = casillaActual.siguienteCasilla;
				}
			}else {
			if (direccionAuxiliar==true) {
				casillaActual.getCasilllaSiguienteAux();
				direccionAuxiliar= false;
			}else {
				casillaActual= casillaActual.getCasillaSiguiente();
			}
//			etiquetaImagen.setLocation(casillaActual.coordenadaCasillaX+ this.correccionCoordenadaX, casillaActual.coordenadaCasillaY+this.correccionCoordenadaY);
			numeroDados -= 1;
			if (casillaActual.numeroCasilla == cantidadCasillas) {
				verificarDireccion();
			}
			//etiquetaImagen.setLocation(casillaActual.coordenadaCasillaX+ this.correccionCoordenadaX, casillaActual.coordenadaCasillaY+this.correccionCoordenadaY);
			numeroDados-=1;
		}
		
		verificarDireccion();
		if (direccionTeletransporte==true) {
			casillaActual= Casilla.casillaTeletransporte;
			etiquetaImagen.setLocation(casillaActual.coordenadaCasillaX+ this.correccionCoordenadaX, casillaActual.coordenadaCasillaY+this.correccionCoordenadaY);
		if (direccionTeletransporte == true) {
			casillaActual = casillaActual.teletransporteCasilla;
			etiquetaImagen.setLocation(casillaActual.coordenadaCasillaX + this.correccionCoordenadaX, casillaActual.coordenadaCasillaY + this.correccionCoordenadaY);
		}
		
		verificarTipoCasilla();
	}
	
	public void verificarEstrella (int casilla) {
		
	}
	
	public void verificarTipoCasilla() {
		
	}

}
