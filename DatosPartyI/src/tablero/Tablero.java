package tablero;

public class Tablero {
	public Casilla primeraCasilla;
	public Casilla ultimaCasilla;
	public static int cantidadCasillas = 0;
	
	public Tablero caminoPrincipal;
	public Tablero subCaminoUno;
	public Tablero subCaminoDos;
	public Tablero subCaminoTres;
	public Tablero subCaminoCuatro;
	
	private int posicionCasillaX, posicionCasillaY;
	private int N = 1, NE = 2, E = 3, SE = 4, S = 5, SO = 6, O = 7, NO = 8;
	private static Casilla casilla12, casilla17, casilla34, casilla35;
		
	public Tablero() {
		caminoPrincipal = new Tablero(0);
		
		casilla35 = encontrarCasilla(35);
		subCaminoUno = new Tablero(1);
		
		casilla12 = encontrarCasilla(12);
		subCaminoDos = new Tablero(2);
		
		casilla17 = encontrarCasilla(17);
		subCaminoTres = new Tablero(3);
		
		casilla34 = encontrarCasilla(34);
		subCaminoCuatro = new Tablero(4);
		
		ensamblarCaminos();
		
	}
	public Tablero(int i) {
		if (i == 0) {
			agregarCaminoPrincipal();			
		}
		else if (i == 1) {
			agregarSubCaminoUno();
		}
		else if (i == 2) {
			agregarSubCaminoDos();
		}
		else if (i == 3) {
			agregarSubCaminoTres();
		}
		else if (i == 4) {
			agregarSubCaminoCuatro();
		}
	}
	
	private void direccionCasilla(int direccion) {
		if (direccion == 1) {
			posicionCasillaY -= 57;
		}
		else if (direccion == 2) {
			posicionCasillaX += 40;
			posicionCasillaY -= 40;
		}
		else if (direccion == 3) {
			posicionCasillaX += 57;
		}
		else if (direccion == 4) {
			posicionCasillaX += 40;
			posicionCasillaY += 40;
		}
		else if (direccion == 5) {
			posicionCasillaY += 57;
		}
		else if (direccion == 6) {
			posicionCasillaX -= 40;
			posicionCasillaY += 40;
		}
		else if (direccion == 7) {
			posicionCasillaX -= 57;
		}
		else if (direccion == 8) {
			posicionCasillaX -= 40;
			posicionCasillaY -= 40;
		}
	}
	
	private void agregarCaminoPrincipal() {
		posicionCasillaX = 240;
		posicionCasillaY = 635;
		
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NE);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);	
		direccionCasilla(NE);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(SE);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(SE);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(E);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(E);
		nuevaCasilla("Roja", posicionCasillaX,posicionCasillaY);
		direccionCasilla(E);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(E);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NE);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NE);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NE);
		nuevaCasilla("Verde", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NE);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NE);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NE);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(N);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(N);
		nuevaCasilla("Verde", posicionCasillaX,posicionCasillaY);
		direccionCasilla(N);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(N);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(O);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(O);
		nuevaCasilla("Roja", posicionCasillaX,posicionCasillaY);
		direccionCasilla(O);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(O);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(SO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(SO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(SO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(SO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(SO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(SO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(S);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(S);
		nuevaCasilla("Verde", posicionCasillaX,posicionCasillaY);
		direccionCasilla(S);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(S);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(SE);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);

	}
	
	private void agregarSubCaminoUno() {
		posicionCasillaX = Tablero.casilla35.coordenadaCasillaX;
		posicionCasillaY = Tablero.casilla35.coordenadaCasillaY;
		
		direccionCasilla(E);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(E);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(SE);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(SE);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(SE);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(SE);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(S);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
	
	}
	private void agregarSubCaminoDos() {
		posicionCasillaX = Tablero.casilla12.coordenadaCasillaX;
		posicionCasillaY = Tablero.casilla12.coordenadaCasillaY;
		
		direccionCasilla(NO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		nuevaCasilla("Morada", posicionCasillaX -= 23,posicionCasillaY -= 23);
		nuevaCasilla("Azul", posicionCasillaX -= 45,posicionCasillaY -= 45);
		direccionCasilla(NO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NO);
		nuevaCasilla("Azul", posicionCasillaX,posicionCasillaY);
	
	}
	private void agregarSubCaminoTres() {
		posicionCasillaX = Tablero.casilla17.coordenadaCasillaX;
		posicionCasillaY = Tablero.casilla17.coordenadaCasillaY;
		
		direccionCasilla(O);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(O);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NO);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NO);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NO);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NO);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(N);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
	}
	
	private void agregarSubCaminoCuatro() {
		posicionCasillaX = Tablero.casilla34.coordenadaCasillaX - 200;
		posicionCasillaY = Tablero.casilla34.coordenadaCasillaY + 50;
		
		nuevaCasilla("Morada", posicionCasillaX,posicionCasillaY);
		nuevaCasilla("Amarilla", posicionCasillaX += 23,posicionCasillaY -= 45);
		direccionCasilla(NE);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NE);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NE);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NE);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NE);
		nuevaCasilla("Amarilla", posicionCasillaX,posicionCasillaY);
		direccionCasilla(NE);
		nuevaCasilla("Roja", posicionCasillaX,posicionCasillaY);
	}
	
	private void ensamblarCaminos() {
		Casilla casilla3 = encontrarCasilla(3);
		caminoPrincipal.ultimaCasilla.siguienteCasilla = casilla3;	
		
		casilla35.siguienteCasillaAux = subCaminoUno.primeraCasilla;
		subCaminoUno.primeraCasilla.anteriorCasilla = casilla35;
		
		Casilla casilla7 = encontrarCasilla(7);
		subCaminoUno.ultimaCasilla.siguienteCasilla = casilla7;
		casilla7.anteriorCasilla = subCaminoUno.ultimaCasilla;
		
		casilla12.siguienteCasillaAux = subCaminoDos.primeraCasilla;
		
		Casilla casilla30 = encontrarCasilla(30);
		subCaminoDos.ultimaCasilla.siguienteCasilla = casilla30;
		
		casilla17.siguienteCasillaAux = subCaminoTres.primeraCasilla;
		subCaminoTres.primeraCasilla.anteriorCasilla = casilla17;
		
		Casilla casilla25 = encontrarCasilla(25);
		subCaminoTres.ultimaCasilla.siguienteCasilla = casilla25;
		casilla25.anteriorCasilla = subCaminoTres.ultimaCasilla;

		Casilla casilla50 = encontrarCasilla(50);
		casilla50.teletransporteCasilla = subCaminoCuatro.primeraCasilla;
		subCaminoCuatro.primeraCasilla.teletransporteCasilla = casilla50;
		
	}
	
	public Casilla encontrarCasilla(int numeroCasilla){
		if (numeroCasilla <= caminoPrincipal.ultimaCasilla.numeroCasilla) {
			return encontrarCasillaAux(caminoPrincipal, numeroCasilla);
		}
		
		else if (caminoPrincipal.ultimaCasilla.numeroCasilla < numeroCasilla && numeroCasilla <= subCaminoUno.ultimaCasilla.numeroCasilla) {
			return encontrarCasillaAux(subCaminoUno, numeroCasilla);

		}

		else if (subCaminoUno.ultimaCasilla.numeroCasilla < numeroCasilla && numeroCasilla <= subCaminoDos.ultimaCasilla.numeroCasilla) {
			return encontrarCasillaAux(subCaminoDos, numeroCasilla);

		}

		else if (subCaminoDos.ultimaCasilla.numeroCasilla < numeroCasilla && numeroCasilla <= subCaminoTres.ultimaCasilla.numeroCasilla) {
			return encontrarCasillaAux(subCaminoTres, numeroCasilla);

		}

		else {
			return encontrarCasillaAux(subCaminoCuatro, numeroCasilla);
		}
	}

	public Casilla encontrarCasillaAux(Tablero camino, int numeroCasilla) {
		Casilla casilla = camino.primeraCasilla;
		while (casilla.numeroCasilla != numeroCasilla) {
			casilla = casilla.siguienteCasilla;
		}
		return casilla;
	}
	
	public void nuevaCasilla(String tipoCasilla, int coordenadaCasillaX, int coordenadaCasillaY) {
		cantidadCasillas += 1;
		if (primeraCasilla == null) {
			primeraCasilla = new Casilla(tipoCasilla, coordenadaCasillaX, coordenadaCasillaY);
			ultimaCasilla = primeraCasilla;
			primeraCasilla.numeroCasilla = cantidadCasillas;
		}
		else {
			ultimaCasilla.siguienteCasilla = new Casilla(tipoCasilla, coordenadaCasillaX, coordenadaCasillaY);
			ultimaCasilla.siguienteCasilla.numeroCasilla = cantidadCasillas;
			if (tipoCasilla.equals("Amarilla")) {
				ultimaCasilla.siguienteCasilla.anteriorCasilla = ultimaCasilla;
			}
			ultimaCasilla = ultimaCasilla.siguienteCasilla;
		}
	}
}
