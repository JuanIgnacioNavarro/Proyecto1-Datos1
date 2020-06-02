package minijuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import juego.Partida;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import juego.Bienvenida;
import jugador.Jugador;


public class Minijuego extends JFrame implements MouseListener{
	protected Jugador listaJugadores[]; 
	protected Jugador jugadorActual;
	protected int contadorRondas=0;
//    _	
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
	private Font fuenteTexto = new Font("Comic Sans MS", 0, 18);
	private Font fuenteTitulo = new Font("Comic Sans MS", 1, 30);
	protected JLabel botonPlay;
	protected JLabel narrador;
	private ImageIcon imagenPlay= new ImageIcon("Imagenes/botonPlay.png");
	protected boolean enJuego=false;
	private Color colorNegativo=new Color(222,66,80);
	private Color colorPositivo= new Color(180, 225, 120);
	
	public Minijuego(Jugador listaJugadores[]) {
		this.listaJugadores= listaJugadores;
		setTitle("Minijuego");
		setVisible(true);
		setSize(900,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		agregarComponentesVentana();
	}
	
	private void agregarComponentesVentana() {
		agregarPanel ();
		System.out.println(this.listaJugadores[0].nombreJugador);
		agregarMarcador();
		this.jugadorActual=this.listaJugadores[0];
		agregarBoton();
		componentesVentenaHeredada();//componentes de inicio del juego
	}
	
	private void agregarPanel () {
		panelMinijuegos=new JPanel();
		this.getContentPane().add(panelMinijuegos);
		panelMinijuegos.setLayout(null);
		panelMinijuegos.setBackground(Bienvenida.colorVentana);
	}
	private void agregarMarcador() {
		listaEtiquetasInfo= new JLabel[listaJugadores.length];
		listaEtiquetasPuntaje= new JLabel[listaJugadores.length];
		int i=0;
		while (i<listaJugadores.length) {
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
			
			//Prueba del repaint 
//			listaJugadores[i].puntajeMinijuego+=2;
//			listaEtiquetasPuntaje[i].setText("Puntaje: "+ listaJugadores[i].puntajeMinijuego);
//			panelMinijuegos.repaint();
			//Esto Funciona
			
			i+=1;
		}
	}
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
	
	public void actualizarDatosMarcador() {
		int i=0;
		while (i<listaEtiquetasInfo.length) {
			listaEtiquetasPuntaje[i].setText("Puntaje: "+ listaJugadores[i].puntajeMinijuego);			
			i+=1;
		}
		if (contadorRondas==listaJugadores.length-1) {
			narrador.setText("Ver resultados");
		}
		else {
			narrador.setText("Turno de "+ jugadorActual.nombreJugador);
		}
		panelMinijuegos.repaint();
	}
	
	public void iniciarMinijuego() {
		etiquetaTituloMinijuego.setVisible(false);
		//Aquí se realizan todas las acciones de solo una ronda del minijuego!
		
		try {
		jugadorActual= listaJugadores[contadorRondas+1];
		}catch(Exception e) {}
		actualizarDatosMarcador();
		if (contadorRondas==listaJugadores.length) {
			mostrarResultados();
		}
		contadorRondas+=1;
		botonPlay.setVisible(true);
	}
	
	public void mostrarResultados() {
		tituloResultados= new JLabel();
		tituloResultados.setBounds(283, 50, 500, 70);
		tituloResultados.setHorizontalAlignment(SwingConstants.CENTER);
		tituloResultados.setFont(fuenteTitulo);
		tituloResultados.setForeground(Color.red);
		tituloResultados.setText("Resultados");
		panelMinijuegos.add(tituloResultados);
		ordenarResultados();
		//Escondiendo los JLabel de previa del juego
		etiquetaTituloMinijuego.setVisible(false);
		int i=0;
		listaEtiquetasResultados= new JLabel[listaJugadores.length];
		informacionResultados= new JLabel[listaJugadores.length];
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
			informacionResultados[i].setText((i+1)+") "+listaJugadores[listaJugadores.length-1-i].nombreJugador+" gana "+(listaJugadores.length+1-i)*100+" monedas!");
			listaEtiquetasResultados[i].add(informacionResultados[i]);
			
			panelMinijuegos.repaint();
			i+=1;
		}
	}
	
	public void ordenarResultados() { //Se ordenan los resultados según el puntaje 
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
		//en 283 y lohago de 500, alto 150 y que mida 400
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
//		tituloMinijuego.setText("Titulo del minijuego");
		etiquetaTituloMinijuego.add(tituloMinijuego);
		
		descripcionMinijuego= new JLabel();
		descripcionMinijuego.setSize(etiquetaTituloMinijuego.getWidth(), etiquetaTituloMinijuego.getHeight()/3);
		descripcionMinijuego.setLocation(0, etiquetaTituloMinijuego.getHeight()/2);
		descripcionMinijuego.setHorizontalAlignment(SwingConstants.CENTER);
		descripcionMinijuego.setForeground(Color.white);
		descripcionMinijuego.setFont(fuenteTexto);
//		descripcionMinijuego.setText("Esta es una muy buena descripcion del minijuego");
		etiquetaTituloMinijuego.add(descripcionMinijuego);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==botonPlay) {
			botonPlay.setVisible(false);
			System.out.println("Iniciar Minijuego para "+jugadorActual.nombreJugador);
		}
		iniciarMinijuego();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
