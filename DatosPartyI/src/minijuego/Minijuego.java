package minijuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import juego.Partida;
import java.awt.event.MouseListener;
import jugador.Jugador;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import juego.Bienvenida;
import jugador.Jugador;


public class Minijuego extends JFrame implements MouseListener{
//    ____
//___/  Atributos de control de la clase minijuego e hijas
	protected Jugador listaJugadores[]; 
	protected Jugador jugadorActual;
	protected int contadorRondas=0;
	protected boolean enJuego=false;
	protected boolean running=false;
//    ____
//___/  Atributos de la interfaz gráfica
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
//___/  Atributos de variables usadas varias veces en la interfaz gráficas
	protected Font fuenteTexto = new Font("Comic Sans MS", 0, 18);
	protected Font fuenteTitulo = new Font("Comic Sans MS", 1, 30);

	/**
	 * Correponde a un constructor que actúa como padre para todos los minijuegos del proyecto
	 * Se adapta a la cantidad de jugadores (de 2 a 4) y genera toda la interfaz gráfica, también controla
	 * el orden de las instrucciones ya que cada jugador juega por separado.
	 * 
	 * @param listaJugadores: una lista de los jugadores que participan en este minijuego
	 */
	public Minijuego(Jugador listaJugadores[]) {
		this.listaJugadores= listaJugadores;
		setTitle("Minijuego");
		setVisible(true);
		setSize(900,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //Esta instrucción es para que el proyecto no se caiga
		agregarComponentesVentana();
	}
	
	private void agregarComponentesVentana() { //Este método llama a más métodos, esto con el objetivo de ordenar el código
		agregarPanel ();//Panel de la interfaz
		agregarMarcador();//Un marcador que lleva los puntajes del minijuego al tanto
		this.jugadorActual=this.listaJugadores[0];
		agregarBoton();//Este botón le permite empezar el juego a cada jugador cuando quiera
		componentesVentenaHeredada();//Estos componentes se editan en cada clase hija
	}
	
	private void agregarPanel () {
		panelMinijuegos=new JPanel();
		this.getContentPane().add(panelMinijuegos);
		panelMinijuegos.setLayout(null);
		panelMinijuegos.setBackground(Bienvenida.colorVentana);
	}
	
	/**
	 * Este método añade los marcadores de cada jugador, donde indica el nombre y el puntaje
	 */
	private void agregarMarcador() {
		listaEtiquetasInfo= new JLabel[listaJugadores.length];
		listaEtiquetasPuntaje= new JLabel[listaJugadores.length];
		int i=0;
		while (i<listaJugadores.length) { //Como el minijuego se debe adaptar a la cantidad de jugadores se debe añadir cada marcador con un while
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
	 * Este método añade un botón que permite controlar los timepos del minijuego, también incluye un narrador
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
	 * La información en los marcadores y en el narador está cambiando constantemente (depués de cada turno hay un puntaje
	 * diferente para  el jugador del turno y el narrador muestra otra indicación)
	 */
	public void actualizarDatosMarcador() {
		int i=0;
		while (i<listaEtiquetasInfo.length) {
			listaEtiquetasPuntaje[i].setText("Puntaje: "+ listaJugadores[i].puntajeMinijuego);			
			i+=1;
		}
		if (contadorRondas==listaJugadores.length-1) {
			narrador.setText("Ver resultados");
		}
		else if (contadorRondas==listaJugadores.length) {
			narrador.setText("Continuar");
		}
		else {
			narrador.setText("Turno de "+ jugadorActual.nombreJugador);
		}
		panelMinijuegos.repaint();
	}
	/**
	 * Este método controla el turno de cada jugador, además se adapta con cada clase hija
	 * actualiza los datos después de que cada jugador haya terminado su ronda y llama a 
	 * mostrar los resultados si el juego terminó.
	 */
	public void iniciarMinijuego() {
		etiquetaTituloMinijuego.setVisible(false);
		narrador.setVisible(false);
		System.out.println("Acabo de iniciar un minijuego run minijuego");
		runMinijuego(jugadorActual);
		
		
		try {
		jugadorActual= listaJugadores[contadorRondas+1];
		}catch(Exception e) {}
		actualizarDatosMarcador();
		if (contadorRondas==listaJugadores.length) {
			mostrarResultados();
		}
		else if (contadorRondas==listaJugadores.length+1) {
			int i=0;
			while (i<listaJugadores.length) {
				listaJugadores[i].puntajeMinijuego=0;
				i+=1;
			}
			this.setVisible(false);
		}
		contadorRondas+=1;
		botonPlay.setVisible(true);
	}
	
	public void runMinijuego(Jugador jugador) {
		

		//Este es el método que perimte hacer el override de la herencia
	}
	
	/**
	 * Este método muestra con distintos JLabel los reusltados del minijuego, primero ordena la lista según 
	 * el puntaje de cada jugador y luego añade los resultados a la interfaz
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
		
		//En este while se añaden los JLabel de los resultados, se define el texto de cada uno considerando empates 
		//También se añade la cantidad de monedas que se ganan a cada juagdor
		//Está un poco desordenado, funciona para todos los casos así que no le pongan tanta atención
		while (i<listaJugadores.length) {
			System.out.println("Añadiendo las etiquetas de resultados ");
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
			//En esta serie de condicionales considera los posibles empates de los jugadores y añade las monedas a cada uno
			if (i==0) {
				listaJugadores[listaJugadores.length-1-i].textoResultados= " gana "+(listaJugadores.length+1-i)*100+" monedas!";
				informacionResultados[i].setText((i+1)+") "+listaJugadores[listaJugadores.length-1-i].nombreJugador.concat(listaJugadores[listaJugadores.length-1-i].textoResultados));
				listaJugadores[listaJugadores.length-1-i].monedasJugador+=(listaJugadores.length+1-i)*100; //Añade monedas aljugador
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
				listaJugadores[listaJugadores.length-1-i].monedasJugador+=(listaJugadores.length+1-i)*100; //Añade monedas aljugador
			}
			listaEtiquetasResultados[i].add(informacionResultados[i]);
			
			panelMinijuegos.repaint();
			i+=1;
		}
		ordenarResultadosNum();
	}
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
	 * Corresponde un Selction Sort que ordena la lista de jugadores según su puntaje
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
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==botonPlay) {
			if (running==false) {
			System.out.println("Iniciar Minijuego para "+jugadorActual.nombreJugador);
			iniciarMinijuego();
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
