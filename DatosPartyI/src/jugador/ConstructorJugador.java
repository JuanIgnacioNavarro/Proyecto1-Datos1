package jugador;

import java.awt.Image;
import tablero.Casilla;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import juego.*;

public class ConstructorJugador extends Jugador{
	private ImageIcon imagenJugador;
	
	public ConstructorJugador(int numeroJugador, Casilla casillaInicial) {
		casillaActual = casillaInicial;
		this.numeroJugador=numeroJugador;
		
		if (numeroJugador == 0) {
			imagenJugador= new ImageIcon("Imagenes/Token1.png");
			correccionCoordenadaX=0;
			correccionCoordenadaY=0;
			nombreJugador = "Jugador 1";
		}
		else if (numeroJugador == 1) {
			imagenJugador= new ImageIcon("Imagenes/Token2.png");
			correccionCoordenadaX=30;
			correccionCoordenadaY=30;
			nombreJugador = "Jugador 2";
		}
		else if (numeroJugador == 2) {
			imagenJugador=new ImageIcon("Imagenes/Token3.png");
			correccionCoordenadaX=60;
			correccionCoordenadaY=60;
			nombreJugador = "Jugador 3";
		}
		else if (numeroJugador == 3) {
			imagenJugador = new ImageIcon("Imagenes/Token4.png");
			correccionCoordenadaX=90;
			correccionCoordenadaY=90;
			nombreJugador = "Jugador 4";
		}

		etiquetaImagen= new JLabel();
	//El comentario de abajo es lo que debe ir cuando el tablero esté terminado
		//etiquetaImagen.setBounds(casillaActual.coordenadaCasillaX+correccionCoordenadaX, casillaActual.coordenadaCasillaY+correccionCoordenadaY, 50, 50 );
		etiquetaImagen.setBounds(30+correccionCoordenadaX, 30+correccionCoordenadaY, 50, 50 );
		etiquetaImagen.setIcon(new ImageIcon (imagenJugador.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH)));
		Partida.panelPartida.add(etiquetaImagen);
	}
}
