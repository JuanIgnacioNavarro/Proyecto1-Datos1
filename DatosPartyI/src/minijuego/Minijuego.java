package minijuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import jugador.Jugador;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import juego.Bienvenida;
import juego.Partida;

/**
 * Corresponde a la clase Padre de los minijuegos, como estos compart�an tantas caracteristicas similares se decidio llevar 
 * a cabo los minijuegos con herencia
 * En general esta clase controla la interfaz grafica que utiliza Swing, metodos que controlan los turnos de cada jugador para jugar
 * y metodos de ordenamiento y muestra de los resultados obtenidos en estos
 * @author Juan Navarro
 *
 */
public abstract class Minijuego extends JFrame implements MouseListener {
//    ____
//___/  Atributos de control de la clase minijuego e hijas
	protected Jugador listaJugadores[]; 
	protected Jugador jugadorActual;
	protected int contadorRondas;
	protected boolean enJuego;
	protected boolean running;
//    ____
//___/  Atributos de la interfaz grafica
	protected JPanel panelMinijuegos;
	protected JLabel listaEtiquetasInfo[];
	protected JLabel listaEtiquetasPuntaje[];
	protected JLabel listaEtiquetasResultados[];
	protected JLabel informacionResultados[];
	protected JLabel tituloResultados;
	protected JLabel etiquetaTituloMinijuego;
	protected  JLabel tituloMinijuego;
	protected JLabel descripcionMinijuego;
	protected JLabel botonPlay;
	protected JLabel narrador;
	private ImageIcon imagenPlay= new ImageIcon("Imagenes/botonPlay.png");
//    ____	
//___/  Atributos de variables usadas varias veces en la interfaz grafica
	protected Font fuenteTexto = new Font("Comic Sans MS", 0, 18);
	protected Font fuenteTitulo = new Font("Comic Sans MS", 1, 30);

	/**
	 * Correponde a un constructor que actua como padre para todos los minijuegos del proyecto
	 * Se adapta a la cantidad de jugadores (de 2 a 4) y genera toda la interfaz grafica, tambien controla
	 * el orden de las instrucciones ya que cada jugador juega por separado.
	 * 
	 * @param listaJugadores: una lista de los jugadores que participan en este minijuego
	 */
	public Minijuego(Jugador listaJugadores[]) {
		this.listaJugadores= listaJugadores;
		for (int i=0;i<listaJugadores.length; i++) {
			this.listaJugadores[i].numeroJugadorMinijuego=i;
		}
		setTitle("Minijuego");
		setVisible(true);
		setSize(900,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //Esta instruccion es para que el proyecto no se caiga
		agregarComponentesVentana();
	}
	
	/**
	 * Corresponde a un metodo que llama a otros metodos que a�aden los componentes de la interfaz grafica
	 */
	private void agregarComponentesVentana() { //Este metodo llama a mas metodos, esto con el objetivo de ordenar el codigo
		agregarPanel ();//Panel de la interfaz
		agregarMarcador();//Un marcador que lleva los puntajes del minijuego al tanto
		this.jugadorActual=this.listaJugadores[0];
		agregarBoton();//Este boton le permite empezar el juego a cada jugador cuando quiera
		componentesVentenaHeredada();//Estos componentes se editan en cada clase hija
	}
	
	/**
	 * Como su nombre lo dice a�ade un panel
	 * el nombre de este es panelMinijuegos
	 */
	private void agregarPanel () {
		panelMinijuegos=new JPanel();
		this.getContentPane().add(panelMinijuegos);
		panelMinijuegos.setLayout(null);
		panelMinijuegos.setBackground(Bienvenida.colorVentana);
	}
	
	/**
	 * Este metodo agrega los marcadores de cada jugador, donde indica el nombre y el puntaje
	 */
	private void agregarMarcador() {
		listaEtiquetasInfo= new JLabel[listaJugadores.length];
		listaEtiquetasPuntaje= new JLabel[listaJugadores.length];
		int i=0;
		while (i<listaJugadores.length) { //Como el minijuego se debe adaptar a la cantidad de jugadores se debe agregar cada marcador con un while
			listaEtiquetasInfo[i]= new JLabel();
			listaEtiquetasInfo[i].setBounds(15, 130*i+20, 150, 120);
			listaEtiquetasInfo[i].setOpaque(true);
			listaEtiquetasInfo[i].setBackground(new Color(66, 66, 66));
			panelMinijuegos.add(listaEtiquetasInfo[i]);
	
			listaEtiquetasPuntaje[i]= new JLabel();
			listaEtiquetasPuntaje[i].setSize(listaEtiquetasInfo[i].getWidth(), listaEtiquetasInfo[i].getHeight()/3);
			listaEtiquetasPuntaje[i].setHorizontalAlignment(SwingConstants.CENTER);
			listaEtiquetasPuntaje[i].setLocation(0, 60);
			listaEtiquetasPuntaje[i].setFont(fuenteTexto);
			listaEtiquetasPuntaje[i].setForeground(Color.yellow);
			listaEtiquetasPuntaje[i].setText("Puntaje: "+ listaJugadores[i].puntajeMinijuego);
			listaEtiquetasInfo[i].add(listaEtiquetasPuntaje[i]);
			
			JLabel etiquetaNombreJugador= new JLabel();
			etiquetaNombreJugador.setSize(listaEtiquetasInfo[i].getWidth(), listaEtiquetasInfo[i].getHeight()/3);
			etiquetaNombreJugador.setHorizontalAlignment(SwingConstants.CENTER);
			etiquetaNombreJugador.setLocation(0, 10);
			etiquetaNombreJugador.setFont(fuenteTexto);
			etiquetaNombreJugador.setForeground(Color.white);
			etiquetaNombreJugador.setText(listaJugadores[i].nombreJugador);
			listaEtiquetasInfo[i].add(etiquetaNombreJugador);
	
			panelMinijuegos.repaint();
			i+=1;
		}
	}
	/**
	 * Este metodo agrega un boton que permite controlar los tiempos del minijuego, tambien incluye un narrador
	 * El narrador le indica a los usuarios loque deben de hacer
	 */
	private void agregarBoton() {
		botonPlay= new JLabel();
		botonPlay.setSize(80, 80);
		botonPlay.setLocation(45, 580);
		botonPlay.setIcon(new ImageIcon(imagenPlay.getImage().getScaledInstance(botonPlay.getWidth(), botonPlay.getHeight(), Image.SCALE_SMOOTH)));
		botonPlay.addMouseListener(this);
		panelMinijuegos.add(botonPlay);
		
		narrador= new JLabel();
		narrador.setBounds(15,540,160,30);
		narrador.setOpaque(true);
		narrador.setBackground(new Color(66, 66, 66));
		narrador.setFont(new Font("Comic Sans MS", 0, 15));
		narrador.setText("Turno de "+ jugadorActual.nombreJugador);
		narrador.setForeground(Color.red);
		narrador.setHorizontalAlignment(SwingConstants.CENTER);
		panelMinijuegos.add(narrador);
		
	}
	
	/**
	 * La informacion en los marcadores y en el narador esta cambiando constantemente (despues de cada turno hay un puntaje
	 * diferente para  el jugador del turno y el narrador muestra otra indicacion)
	 */
	public void actualizarDatosMarcador() {
		int i=0;
		while (i<listaEtiquetasInfo.length) {
			listaEtiquetasPuntaje[i].setText("Puntaje: "+ listaJugadores[i].puntajeMinijuego);			
			i+=1;
		}
		if (contadorRondas == listaJugadores.length-1) {
			narrador.setText("Ver resultados");
		}
		else if (contadorRondas == listaJugadores.length) {
			narrador.setText("Continuar");
		}
		else {
			narrador.setText("Turno de "+ jugadorActual.nombreJugador);
		}
		panelMinijuegos.repaint();
	}
	/**
	 * Este metodo controla el turno de cada jugador, ademas se adapta con cada clase hija
	 * actualiza los datos despues de que cada jugador haya terminado su ronda y llama a 
	 * mostrar los resultados si el juego termina.
	 */
	public void iniciarMinijuego() {
		etiquetaTituloMinijuego.setVisible(false);
		narrador.setVisible(false);
		System.out.println("Boton en false");
		runMinijuego(jugadorActual);
		
		try {
		jugadorActual= listaJugadores[contadorRondas+1];
		}catch(Exception e) {}
		actualizarDatosMarcador();
		if (contadorRondas==listaJugadores.length) {
			running=false;
			mostrarResultados();
		}
		else if (contadorRondas==listaJugadores.length+1) {
			int i=0;
			while (i<listaJugadores.length) {
				listaJugadores[i].puntajeMinijuego=0;
				i+=1;
			}
			Partida.minijuegoActivado = false;
			this.setVisible(false);
		}
		contadorRondas+=1;
	}
	
	/**
	 * Corresponde al metodo que corre la parte del minijuego en la cual interactua con el usuario
	 * Cada clase hija usa este metodo pues son diferentes
	 * @param jugador
	 */
	public abstract void runMinijuego(Jugador jugador);
	
	/**
	 * Este metodo muestra con distintos JLabel los reusltados del minijuego, primero ordena la lista segun 
	 * el puntaje de cada jugador y luego pone los resultados a la interfaz
	 */
	public void mostrarResultados() {
		tituloResultados= new JLabel();
		tituloResultados.setBounds(283, 50, 500, 70);
		tituloResultados.setHorizontalAlignment(SwingConstants.CENTER);
		tituloResultados.setFont(fuenteTitulo);
		tituloResultados.setForeground(Color.red);
		tituloResultados.setText("Resultados");
		panelMinijuegos.add(tituloResultados);
		ordenarResultados();
		etiquetaTituloMinijuego.setVisible(false); //Escondiendo los JLabel de previa del juego
		int cantidadEmpates=0;
		int i=0;
		listaEtiquetasResultados= new JLabel[listaJugadores.length];
		informacionResultados= new JLabel[listaJugadores.length];
		
		//En este while se agregan los JLabel de los resultados, se define el texto de cada uno considerando empates 
		//Tambien se agrega la cantidad de monedas que se ganan a cada juagdor
		while (i<listaJugadores.length) {
			listaEtiquetasResultados[i]= new JLabel();
			listaEtiquetasResultados[i].setBounds(283, 130*i+130, 500, 100);
			listaEtiquetasResultados[i].setOpaque(true);
			listaEtiquetasResultados[i].setBackground(new Color(66, 66, 66));
			panelMinijuegos.add(listaEtiquetasResultados[i]);
			
			informacionResultados[i]= new JLabel();
			informacionResultados[i].setSize(listaEtiquetasResultados[i].getWidth(), listaEtiquetasResultados[i].getHeight());
			informacionResultados[i].setLocation(0, 0);
			informacionResultados[i].setFont(fuenteTexto);
			informacionResultados[i].setForeground(Color.white);
			informacionResultados[i].setHorizontalAlignment(SwingConstants.CENTER);
			//En esta serie de condicionales considera los posibles empates de los jugadores y agrega las monedas a cada uno
			if (i==0) {
				listaJugadores[listaJugadores.length-1-i].textoResultados= " gana "+(listaJugadores.length+1-i)*100+" monedas!";
				informacionResultados[i].setText((i+1)+") "+listaJugadores[listaJugadores.length-1-i].nombreJugador.concat(listaJugadores[listaJugadores.length-1-i].textoResultados));
				listaJugadores[listaJugadores.length-1-i].monedasJugador+=(listaJugadores.length+1-i)*100; //Agrega monedas aljugador
			}
			else if (listaJugadores[listaJugadores.length-1-i].puntajeMinijuego==listaJugadores[listaJugadores.length-i].puntajeMinijuego){
				listaJugadores[listaJugadores.length-1-i].textoResultados=listaJugadores[listaJugadores.length-i].textoResultados;
				informacionResultados[i].setText((i+1)+") "+listaJugadores[listaJugadores.length-1-i].nombreJugador.concat(listaJugadores[listaJugadores.length-1-i].textoResultados));
				cantidadEmpates+=1;
				listaJugadores[listaJugadores.length-1-i].monedasJugador+=(listaJugadores.length+1-i+cantidadEmpates)*100;
			}
			else {
				cantidadEmpates=0;
				listaJugadores[listaJugadores.length-1-i].textoResultados= " gana "+(listaJugadores.length+1-i)*100+" monedas!";
				informacionResultados[i].setText((i+1)+") "+listaJugadores[listaJugadores.length-1-i].nombreJugador.concat(listaJugadores[listaJugadores.length-1-i].textoResultados));
				listaJugadores[listaJugadores.length-1-i].monedasJugador+=(listaJugadores.length+1-i)*100; //A�ade monedas aljugador
			}
			listaEtiquetasResultados[i].add(informacionResultados[i]);
			
			panelMinijuegos.repaint();
			i+=1;
		}
		ordenarResultadosNum();
	}
	
	/**
	 * Es un metodo de ayuda que permite ordenar la lista de los jugadores segun su numero de jugador
	 * corresponde al alforitmo de selection llamado selectionSort
	 */
	public void ordenarResultadosNum() {
		int n= listaJugadores.length;
		for (int i=0; i<n-1; i++) {
			int minIndex=i;
			for (int j=i+1; j<n;j++) {
				if (listaJugadores[j].numeroJugador<listaJugadores[minIndex].numeroJugador) {
					minIndex=j;
				}
			}
		Jugador jugadorTemporal= listaJugadores[minIndex];
		listaJugadores[minIndex]=listaJugadores[i];
		listaJugadores[i]= jugadorTemporal;
		}
	}
	/**
	 * Corresponde un Selection Sort que ordena la lista de jugadores segun su puntaje
	 * Esto facilita mostrar los resultados
	 */
	public void ordenarResultados() { 
		int n= listaJugadores.length;
		for (int i=0; i<n-1; i++) {
			int minIndex=i;
			for (int j=i+1; j<n;j++) {
				if (listaJugadores[j].puntajeMinijuego<listaJugadores[minIndex].puntajeMinijuego) {
					minIndex=j;
				}
			}
		Jugador jugadorTemporal= listaJugadores[minIndex];
		listaJugadores[minIndex]=listaJugadores[i];
		listaJugadores[i]= jugadorTemporal;
		}
	}
	/**
	 * La ventana de cada minijuego tiene un nombre y description difernete,
	 * en estos labels se debe especificar, aunque se crean aqui la informacion
	 * que traen se a�ade en cada una de las clases hijas
	 */
	protected void componentesVentenaHeredada() {
		etiquetaTituloMinijuego= new JLabel();
		etiquetaTituloMinijuego.setBounds(263, 150, 540, 200);
		etiquetaTituloMinijuego.setOpaque(true);
		etiquetaTituloMinijuego.setBackground(new Color(66, 66, 66));
		panelMinijuegos.add(etiquetaTituloMinijuego);
		
		tituloMinijuego= new JLabel();
		tituloMinijuego.setSize(etiquetaTituloMinijuego.getWidth(), etiquetaTituloMinijuego.getHeight()/2);;
		tituloMinijuego.setLocation(0, 0);
		tituloMinijuego.setHorizontalAlignment(SwingConstants.CENTER);
		tituloMinijuego.setForeground(Color.blue);
		tituloMinijuego.setFont(fuenteTitulo);
		etiquetaTituloMinijuego.add(tituloMinijuego);
		
		descripcionMinijuego= new JLabel();
		descripcionMinijuego.setSize(etiquetaTituloMinijuego.getWidth(), etiquetaTituloMinijuego.getHeight()/3);
		descripcionMinijuego.setLocation(0, etiquetaTituloMinijuego.getHeight()/2);
		descripcionMinijuego.setHorizontalAlignment(SwingConstants.CENTER);
		descripcionMinijuego.setForeground(Color.white);
		descripcionMinijuego.setFont(fuenteTexto);
		etiquetaTituloMinijuego.add(descripcionMinijuego);
		
	}
	/**
	 * El label denominado botonPlay funciona para poder controlar las rondas de los jugadores,
	 * mientras estos juegan (this.running==true) no se puede usar el boton, pero una vez que terminan
	 * es lo que permite cambiar de jugadores
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource()==botonPlay) {
			if (running==false) {
			botonPlay.setOpaque(true);
			botonPlay.setBackground(new Color(222, 66, 80));
			panelMinijuegos.repaint();
			iniciarMinijuego();
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource()==botonPlay) {
			botonPlay.setOpaque(true);
			if (running==false) {
				botonPlay.setBackground(new Color(66, 66, 66));
			}
			else {
				botonPlay.setBackground(new Color(222, 66, 80));
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource()==botonPlay) {
			botonPlay.setBackground(Bienvenida.colorVentana);
		}
	}

}
