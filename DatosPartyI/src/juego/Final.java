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

/**
 * Esta corresponde a la clase que ordena los resultados obtenidos en el juego DatosParty
 * Primero ordena la lista jugadores segun su parametro de puntajeTotal y despues agrega
 * un resumen de los datos de cada jugador con respecto a estrellas y monedas 
 * segun el orden obtenido antes 
 * @author Juan Navarro
 */
public class Final extends JFrame implements MouseListener {
	private ImageIcon imagenMoneda = new ImageIcon("Imagenes/Moneda.png");
	private ImageIcon imagenEstrella = new ImageIcon("Imagenes/Estrella.png");
	private Font fuenteTitulo = new Font("Comic Sans MS", 1, 25);
	private Font fuenteTexto = new Font("Comic Sans MS", 0, 16);
	private JPanel panelResultados;
	private JLabel botonFin, labelTitulo;
	private JLabel[] listaEtiquetasInfo, labelPosicion, labelImagenMonedas, labelMonedas, labelImagenEstrellas, labelEstrellas, labelPuntajes;
	private Jugador[] listaJugadores;

	/**
	 * Corresponde al contructor de la clase Final, primero ordena la lista segun los resultados y luego llama a un metodo para imprimir los resultados
	 * @param listaJugadores, pues con cada jugador obtiene toda la informacion que ocupa
	 */
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

	/**
	 * Es un metodo para anadir el panel y cambiar su configuracion
	 */
	private void componentesVentana() {
		panelResultados= new JPanel();
		this.getContentPane().add(panelResultados);
		panelResultados.setLayout(null);
		panelResultados.setBackground(Color.white);
		agregarEtiquetas();
	}

	/**
	 * En este metodo se anaden todas las etiquetas como se deben poner las mismas etiqeuatas para cada juagdor
	 * (consus datos respectivos) se hizo una lista de etiquetas ue contienen informacion similar y se evaluo para cada jugador 
	 */
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
		
		for (int i=0; i< listaJugadores.length; i++) { //Lo recorre para cada jugador de la lista y estos ya se encuentran en orden segun su puntaje
			//Etiqueta que contiene al resto de las etiqeutas para un respectivo jugador
			listaEtiquetasInfo[i]= new JLabel();
			listaEtiquetasInfo[i].setBounds(300-altoLabel ,80+altoLabel*i, altoLabel*2 , altoLabel-20);
			listaEtiquetasInfo[i].setOpaque(true);
			listaEtiquetasInfo[i].setBackground(Color.white);
			panelResultados.add(listaEtiquetasInfo[i]);
			
			//Etiqueta que contiene la posicion en la que quedo cada jugador y su nombre
			labelPosicion[i]= new JLabel ();
			labelPosicion[i].setSize(listaEtiquetasInfo[i].getWidth(), listaEtiquetasInfo[i].getHeight()/3);
			labelPosicion[i].setLocation(0, 0);
			labelPosicion[i].setHorizontalAlignment(SwingConstants.CENTER);
			labelPosicion[i].setFont( new Font("Comic Sans MS", 1, 20));
			labelPosicion[i].setForeground(Color.red);
			labelPosicion[i].setText("Posicion: "+(i+1)+", "+ listaJugadores[i].nombreJugador);
			listaEtiquetasInfo[i].add(labelPosicion[i]);
			
			//Etiqueta que contiene la imagen de las monedas
			labelImagenMonedas[i]= new JLabel();
			labelImagenMonedas[i].setSize(listaEtiquetasInfo[i].getHeight()/3,listaEtiquetasInfo[i].getHeight()/3);
			labelImagenMonedas[i].setLocation(0, listaEtiquetasInfo[i].getHeight()/3);
			labelImagenMonedas[i].setIcon(new ImageIcon(imagenMoneda.getImage().getScaledInstance(labelImagenMonedas[i].getWidth(), labelImagenMonedas[i].getHeight(), Image.SCALE_SMOOTH)));
			listaEtiquetasInfo[i].add(labelImagenMonedas[i]);
			
			//Etiqueta que contiene la informacion de las monedas
			labelMonedas[i]= new JLabel ();
			labelMonedas[i].setSize(listaEtiquetasInfo[i].getHeight()/4+30, listaEtiquetasInfo[i].getHeight()/3);
			labelMonedas[i].setLocation(listaEtiquetasInfo[i].getHeight()/4+10, listaEtiquetasInfo[i].getHeight()/3);
			labelMonedas[i].setHorizontalAlignment(SwingConstants.CENTER);
			labelMonedas[i].setFont(new Font("Comic Sans MS", 0, 17));
			labelMonedas[i].setForeground(Color.black);
			labelMonedas[i].setText(""+listaJugadores[i].monedasJugador);
			listaEtiquetasInfo[i].add(labelMonedas[i]);
			
			//Etiqueta que contiene la imagen de las estrellas
			labelImagenEstrellas[i]= new JLabel();
			labelImagenEstrellas[i].setSize(listaEtiquetasInfo[i].getHeight()/3,listaEtiquetasInfo[i].getHeight()/3);
			labelImagenEstrellas[i].setLocation(listaEtiquetasInfo[i].getWidth()/2+30, listaEtiquetasInfo[i].getHeight()/3);
			labelImagenEstrellas[i].setIcon(new ImageIcon(imagenEstrella.getImage().getScaledInstance(labelImagenEstrellas[i].getWidth(), labelImagenEstrellas[i].getHeight(), Image.SCALE_SMOOTH)));
			listaEtiquetasInfo[i].add(labelImagenEstrellas[i]);
			
			//etiqueta que contiene la informacion de las monedas de cada jugador
			labelEstrellas[i]= new JLabel ();
			labelEstrellas[i].setSize(listaEtiquetasInfo[i].getHeight()/4+10, listaEtiquetasInfo[i].getHeight()/3);
			labelEstrellas[i].setLocation(listaEtiquetasInfo[i].getWidth()*3/4+10, listaEtiquetasInfo[i].getHeight()/3);
			labelEstrellas[i].setHorizontalAlignment(SwingConstants.CENTER);
			labelEstrellas[i].setFont(new Font("Comic Sans MS", 0, 17));
			labelEstrellas[i].setForeground(Color.black);
			labelEstrellas[i].setText(""+listaJugadores[i].estrellasJugador);
			listaEtiquetasInfo[i].add(labelEstrellas[i]);
			
			//etiqueta que contiene los puntajes de cada jugador
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
	/**
	 * Este metodo anade las etiquetas que no necesitan repeticiones pues son unicas en el la clase
	 * tambien contiene al boton de finalizar que cierra la ventana para terminar el juego por completo
	 * el obejtivo de este metodo fu eno sobrecargar tanto el metodo de anadirEtiquetas
	 */
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
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
