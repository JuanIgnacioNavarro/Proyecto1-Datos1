package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import jugador.Jugador;

public class Final extends JFrame implements MouseListener {
	private Jugador[] listaJugadores;
//Atributos del Frame
	private ImageIcon imagenMoneda = new ImageIcon("Imagenes/Moneda.png");
	private ImageIcon imagenEstrella = new ImageIcon("Imagenes/Estrella.png");
	private Font fuenteTitulo = new Font("Comic Sans MS", 1, 25);
	private Font fuenteTexto = new Font("Comic Sans MS", 0, 16);
	private JPanel panelResultados;
	private JLabel botonFin;
	private JLabel labelTitulo;
	private JLabel[] listaEtiquetasInfo;
	private JLabel[] labelPosicion;
	private JLabel[] labelImagenMonedas;
	private JLabel[] labelMonedas;
	private JLabel[] labelImagenEstrellas;
	private JLabel[] labelEstrellas;
	private JLabel[] labelPuntajes;

	
	public Final(Jugador listaJugadores[]) {
		this.listaJugadores= listaJugadores;
		for (int i=0; i< listaJugadores.length; i++) {
			int puntaje= listaJugadores[i].monedasJugador+ listaJugadores[i].estrellasJugador*700;
			listaJugadores[i].puntajeTotal= puntaje;
		}
		ordenarResultados();
		int mitad= listaJugadores.length/2-1;
		for (int i=0; i<=mitad; i++) {
			Jugador jugadorTemp= listaJugadores[listaJugadores.length-1-i];
			listaJugadores[listaJugadores.length-1-i]=listaJugadores[i];
			listaJugadores[i]= jugadorTemp;
		}
		setTitle("Resultados Finales");
		setVisible(true);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		componentesVentana();
	}
	
	private void componentesVentana() {
		panelResultados= new JPanel();
		this.getContentPane().add(panelResultados);
		panelResultados.setLayout(null);
		panelResultados.setBackground(Color.white);
		agregarEtiquetas();
	}
	
	private void agregarEtiquetas() {
		agregarEtiquetasUnicas();
		listaEtiquetasInfo= new JLabel[listaJugadores.length];
		labelPosicion= new JLabel[listaJugadores.length];
		labelImagenMonedas= new JLabel[listaJugadores.length];
		labelMonedas= new JLabel[listaJugadores.length];
		labelImagenEstrellas= new JLabel[listaJugadores.length];
		labelEstrellas= new JLabel[listaJugadores.length];
		labelPuntajes= new JLabel[listaJugadores.length];
		int altoLabel= 500/listaJugadores.length;
				
		/*
		 * va desde el 90 al 590 es decir hay500
		 * cada uno medira 500/cantidad de jugadores-10
		 * posicion en 500/ cantidad de jugadores*(i+1) 
		 * ancho será el doble 
		 * 
		 */
		
		for (int i=0; i< listaJugadores.length; i++) {
			listaEtiquetasInfo[i]= new JLabel();
			listaEtiquetasInfo[i].setBounds(300-altoLabel ,80+altoLabel*i, altoLabel*2 , altoLabel-20);
			listaEtiquetasInfo[i].setOpaque(true);
			listaEtiquetasInfo[i].setBackground(Color.white);
			panelResultados.add(listaEtiquetasInfo[i]);
			
			labelPosicion[i]= new JLabel ();
			labelPosicion[i].setSize(listaEtiquetasInfo[i].getWidth(), listaEtiquetasInfo[i].getHeight()/3);
			labelPosicion[i].setLocation(0, 0);
			labelPosicion[i].setHorizontalAlignment(SwingConstants.CENTER);
			labelPosicion[i].setFont( new Font("Comic Sans MS", 1, 20));
			labelPosicion[i].setForeground(Color.red);
			labelPosicion[i].setText("Posicion: "+(i+1)+" ,"+ listaJugadores[i].nombreJugador);
			listaEtiquetasInfo[i].add(labelPosicion[i]);
			
			labelImagenMonedas[i]= new JLabel();
			labelImagenMonedas[i].setSize(listaEtiquetasInfo[i].getHeight()/3,listaEtiquetasInfo[i].getHeight()/3);
			labelImagenMonedas[i].setLocation(0, listaEtiquetasInfo[i].getHeight()/3);
			labelImagenMonedas[i].setIcon(new ImageIcon(imagenMoneda.getImage().getScaledInstance(labelImagenMonedas[i].getWidth(), labelImagenMonedas[i].getHeight(), Image.SCALE_SMOOTH)));
			listaEtiquetasInfo[i].add(labelImagenMonedas[i]);
			
			labelMonedas[i]= new JLabel ();
			labelMonedas[i].setSize(listaEtiquetasInfo[i].getHeight()/4+30, listaEtiquetasInfo[i].getHeight()/3);
			labelMonedas[i].setLocation(listaEtiquetasInfo[i].getHeight()/4+10, listaEtiquetasInfo[i].getHeight()/3);
			labelMonedas[i].setHorizontalAlignment(SwingConstants.CENTER);
			labelMonedas[i].setFont(new Font("Comic Sans MS", 0, 17));
			labelMonedas[i].setForeground(Color.black);
			labelMonedas[i].setText(""+listaJugadores[i].monedasJugador);
			listaEtiquetasInfo[i].add(labelMonedas[i]);
			
			labelImagenEstrellas[i]= new JLabel();
			labelImagenEstrellas[i].setSize(listaEtiquetasInfo[i].getHeight()/3,listaEtiquetasInfo[i].getHeight()/3);
			labelImagenEstrellas[i].setLocation(listaEtiquetasInfo[i].getWidth()/2+30, listaEtiquetasInfo[i].getHeight()/3);
			labelImagenEstrellas[i].setIcon(new ImageIcon(imagenEstrella.getImage().getScaledInstance(labelImagenEstrellas[i].getWidth(), labelImagenEstrellas[i].getHeight(), Image.SCALE_SMOOTH)));
			listaEtiquetasInfo[i].add(labelImagenEstrellas[i]);
			
			labelEstrellas[i]= new JLabel ();
			labelEstrellas[i].setSize(listaEtiquetasInfo[i].getHeight()/4+10, listaEtiquetasInfo[i].getHeight()/3);
			labelEstrellas[i].setLocation(listaEtiquetasInfo[i].getWidth()*3/4+10, listaEtiquetasInfo[i].getHeight()/3);
			labelEstrellas[i].setHorizontalAlignment(SwingConstants.CENTER);
			labelEstrellas[i].setFont(new Font("Comic Sans MS", 0, 17));
			labelEstrellas[i].setForeground(Color.black);
			labelEstrellas[i].setText(""+listaJugadores[i].estrellasJugador);
			listaEtiquetasInfo[i].add(labelEstrellas[i]);
			
			labelPuntajes[i]= new JLabel ();
			labelPuntajes[i].setSize(listaEtiquetasInfo[i].getWidth(), listaEtiquetasInfo[i].getHeight()/3);
			labelPuntajes[i].setLocation(0, listaEtiquetasInfo[i].getHeight()*2/3);
			labelPuntajes[i].setHorizontalAlignment(SwingConstants.CENTER);
			labelPuntajes[i].setFont( new Font("Comic Sans MS", 1, 17));
			labelPuntajes[i].setForeground(Color.black);
			labelPuntajes[i].setText(""+listaJugadores[i].puntajeTotal);
			listaEtiquetasInfo[i].add(labelPuntajes[i]);
		}
		
		
	}
	
	private void agregarEtiquetasUnicas() {
		labelTitulo= new JLabel();
		labelTitulo.setBounds(150, 10, 300, 70);
		labelTitulo.setOpaque(false);
		labelTitulo.setForeground(Color.red);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setText("Resultados DatosParty");
		labelTitulo.setFont(fuenteTitulo);
		panelResultados.add(labelTitulo);
		
		botonFin= new JLabel ();
		botonFin.setBounds(500,15,80,50);
		botonFin.setOpaque(true);
		botonFin.setBackground(Color.red);
		botonFin.setFont(fuenteTexto);
		botonFin.setForeground(Color.white);
		botonFin.setHorizontalAlignment(SwingConstants.CENTER);
		botonFin.addMouseListener(this);
		botonFin.setText("Terminar");
		panelResultados.add(botonFin);	
	}
	
	private void ordenarResultados() {
		int n= listaJugadores.length;
		for (int i=0; i<n-1; i++) {
			int minIndex=i;
			for (int j=i+1; j<n;j++) {
				if (listaJugadores[j].puntajeTotal<listaJugadores[minIndex].puntajeTotal) {
					minIndex=j;
				}
			}
		Jugador jugadorTemporal= listaJugadores[minIndex];
		listaJugadores[minIndex]=listaJugadores[i];
		listaJugadores[i]= jugadorTemporal;
		}
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		this.setVisible(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
