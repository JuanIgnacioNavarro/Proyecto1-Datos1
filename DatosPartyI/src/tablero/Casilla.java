package tablero;

public class Casilla {
	public String tipoCasilla;
	public Casilla casillaAnterior;
	public int coordenadaCasillaX;
	public int coordenadaCasillaY;
	public Casilla casillaSiguienteAux;
	public Casilla casillaSiguiente;
	public static Casilla casillaTeletransporte;
	
	public Casilla getCasilllaSiguienteAux() {
		return this.casillaSiguienteAux;
	}
	
	public Casilla getCasillaSiguiente() {
		return this.getCasillaSiguiente();
	}
		
	
}
