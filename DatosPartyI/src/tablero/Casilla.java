package tablero;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import juego.Partida;

/**
 * Clase donde se se crean los nodos en forma de casilla
 * @author Andres Martinez Vargas
 */
public class Casilla {
	private ImageIcon imagenCasilla;
	private ImageIcon imagenEstrella = new ImageIcon("Imagenes/Estrella.png");

	public JLabel etiquetaCasilla, etiquetaEstrella;

	public String tipoCasilla;
	public int numeroCasilla, coordenadaCasillaX, coordenadaCasillaY;
	public Casilla siguienteCasilla, siguienteCasillaAux, anteriorCasilla, teletransporteCasilla;
	public boolean estrellaEncima;
	
	/**
	 * Contructor de la clase, define sus atributos iniciales y la imagen corerspondiente al tipo de casilla
	 * @param tipoCasilla : String que define el tipo de casila (Azul, roja, verde, Amarilla o Morada)
	 * @param coordenadaCasillaX: int coordenada en x en la que se ubica la casilla
	 * @param coordenadaCasillaY: int coordenada en y en la que se ubica la casilla
	 */
	public Casilla(String tipoCasilla, int coordenadaCasillaX, int coordenadaCasillaY) {
		this.tipoCasilla = tipoCasilla;
		this.coordenadaCasillaX = coordenadaCasillaX;
		this.coordenadaCasillaY = coordenadaCasillaY;
		
		String direccion = "Imagenes/Casilla" + tipoCasilla + ".png";
		imagenCasilla = new ImageIcon(direccion);
		
		crearCasilla(tipoCasilla);
		crearEstrella();

	}	

	/**
	 * Metodo para crear la casilla
	 * @param tipoCasilla : String que define el tipo de casila (Azul, roja, verde, Amarilla o Morada)
	 */
	public void crearCasilla(String tipoCasilla) {
		etiquetaCasilla = new JLabel();
		etiquetaCasilla.setLocation(coordenadaCasillaX, coordenadaCasillaY);
		if (tipoCasilla.equals("Morada")) {
			etiquetaCasilla.setSize(48, 48);
		}
		else {
			etiquetaCasilla.setSize(70, 70);
		}
		etiquetaCasilla.setIcon(new ImageIcon(imagenCasilla.getImage().getScaledInstance(etiquetaCasilla.getWidth(), etiquetaCasilla.getHeight(), Image.SCALE_SMOOTH)));
		Partida.panelPartida.add(etiquetaCasilla);	
	}
	
	/**
	 * Metodo para crear la estrella de la casilla
	 */
	public void crearEstrella() {
		etiquetaEstrella = new JLabel();
		etiquetaEstrella.setIcon(imagenEstrella);
		etiquetaEstrella.setSize(18,18);
		etiquetaEstrella.setLocation((etiquetaCasilla.getWidth() / 2) - (etiquetaEstrella.getWidth() / 2), (etiquetaCasilla.getHeight() / 2) - (etiquetaEstrella.getHeight() / 2));
		etiquetaEstrella.setIcon(new ImageIcon(imagenEstrella.getImage().getScaledInstance(etiquetaEstrella.getWidth(), etiquetaEstrella.getHeight(), Image.SCALE_SMOOTH)));
		etiquetaEstrella.setVisible(false);
		etiquetaCasilla.add(etiquetaEstrella);
		
		Partida.panelPartida.repaint();
	}
	
	/**
	 * Metodo para poner una estrelle en una casilla
	 */
	public void ponerEstrella() {
		etiquetaEstrella.setVisible(true);
		estrellaEncima = true;
		
	}
	
	/**
	 * Metodo para eliminar una estrelle en una casilla
	 */
	public void eliminarEstrella() {
		etiquetaEstrella.setVisible(false);
		estrellaEncima = false;
		
	}
}
