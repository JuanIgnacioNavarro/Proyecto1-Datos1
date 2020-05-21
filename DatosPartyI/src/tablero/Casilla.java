package tablero;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import juego.Partida;

public class Casilla {
	private ImageIcon imagenCasilla;
	private ImageIcon imagenEstrella = new ImageIcon("Imagenes/Estrella.png");

	public JLabel etiquetaCasilla;
	public JLabel etiquetaEstrella;

	public String tipoCasilla;
	public int numeroCasilla;
	public Casilla siguienteCasilla;
	public Casilla siguienteCasillaAux;
	public Casilla anteriorCasilla;
	public Casilla teletransporteCasilla;
	public int coordenadaCasillaX;
	public int coordenadaCasillaY;
	public boolean estrellaEncima;
	
	public Casilla(String tipoCasilla, int coordenadaCasillaX, int coordenadaCasillaY) {
		this.tipoCasilla = tipoCasilla;
		this.coordenadaCasillaX = coordenadaCasillaX;
		this.coordenadaCasillaY = coordenadaCasillaY;
		
		String direccion = "Imagenes/Casilla" + tipoCasilla + ".png";
		imagenCasilla = new ImageIcon(direccion);
		
		crearCasilla(tipoCasilla);
		crearEstrella();

	}	
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
	
	public void ponerEstrella() {
		etiquetaEstrella.setVisible(true);
		estrellaEncima = true;
		
	}
	
	public void eliminarEstrella() {
		etiquetaEstrella.setVisible(false);
		estrellaEncima = false;
		
	}
}
