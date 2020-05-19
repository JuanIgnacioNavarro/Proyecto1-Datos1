package paqueteJugador;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JugadorUno extends Jugador{
	private ImageIcon imagenJugador= new ImageIcon("Images/Token1.png");
	private int correccionCoordenadaX= 0;
	private int correccionCoordenadaY=0;
	private JLabel etiquetaImagen;
	
	public JugadorUno() {
		nombreJugador= "Jugador 1";
//		 ______
//______/ Añadimos la imagen del jugador 1
		etiquetaImagen= new JLabel();
		etiquetaImagen.setIcon(imagenJugador);
		System.out.println("Añadiendo imagen");
		etiquetaImagen.setBounds(-200, -20, 500+correccionCoordenadaX, 500+correccionCoordenadaY);
		Partida.panel.add(etiquetaImagen);
	}
}
