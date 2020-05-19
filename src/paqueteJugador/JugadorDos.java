package paqueteJugador;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JugadorDos extends Jugador{
	private ImageIcon imagenJugador= new ImageIcon("Images/Token2.png");
	private int correccionCoordenadaX= 100;
	private int correccionCoordenadaY=100;
	private JLabel etiquetaImagen;
	
	public JugadorDos() {
		nombreJugador= "Jugador 2";
//		 ______
//______/ Añadimos la imagen del jugador 2
		etiquetaImagen= new JLabel();
		etiquetaImagen.setIcon(imagenJugador);
		System.out.println("Añadiendo imagen");
		etiquetaImagen.setBounds(0, 0, 500+correccionCoordenadaX, 500+correccionCoordenadaY);
		Partida.panel.add(etiquetaImagen);
	}
}
