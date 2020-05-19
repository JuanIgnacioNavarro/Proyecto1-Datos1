package jugador;

import java.awt.Image;
import tablero.Casilla;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import juego.*;

public class ConstructorJugador extends Jugador{
	private ImageIcon imagenJugador;

	public ConstructorJugador(int numeroJugador, Casilla casillaInicial) {
		casillaActual=casillaInicial;
		this.numeroJugador=numeroJugador;
		if (numeroJugador == 0) {
			imagenJugador= new ImageIcon("Images/Token1.png");
			correccionCoordenadaX=0;
			correccionCoordenadaY=0;
			
		}
		else if (numeroJugador == 1) {
			imagenJugador= new ImageIcon("Images/Token2.png");
			correccionCoordenadaX=30;
			correccionCoordenadaY=30;
		}
		else if (numeroJugador == 2) {
			imagenJugador=new ImageIcon("Images/Token3.png");
			correccionCoordenadaX=60;
			correccionCoordenadaY=60;
		}
		else if (numeroJugador == 3) {
			imagenJugador= new ImageIcon("Images/Token4.png");
			correccionCoordenadaX=90;
			correccionCoordenadaY=90;
		}

		etiquetaImagen= new JLabel();
		etiquetaImagen.setBounds(20+correccionCoordenadaX, 20+correccionCoordenadaY, 50, 50);
		etiquetaImagen.setIcon(new ImageIcon (imagenJugador.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH)));
		Partida.panelPartida.add(etiquetaImagen);
	}
}