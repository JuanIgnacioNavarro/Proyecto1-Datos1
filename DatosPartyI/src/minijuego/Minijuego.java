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
import javax.swing.SwingConstants;

import juego.Bienvenida;
import jugador.Jugador;


public class Minijuego extends JFrame implements MouseListener{
	protected Jugador listaJugadores[]; 
	protected Jugador jugadorActual;
//    _	
//___/  Atributos de la interfaz gráfica
	protected JPanel panelMinijuegos;
	protected JLabel listaEtiquetasInfo[];
	protected JLabel listaEtiquetasPuntaje[];
	private Font fuenteTexto = new Font("Comic Sans MS", 0, 18);
	protected JLabel botonPlay;
	protected JLabel narrador;
	private ImageIcon imagenPlay= new ImageIcon("Imagenes/botonPlay.png");
	
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
			narrador.setText("Turno de "+ jugadorActual.nombreJugador);
			panelMinijuegos.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
