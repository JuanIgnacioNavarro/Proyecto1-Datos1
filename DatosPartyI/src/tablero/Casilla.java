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
	private int correcionCoordenadaEX = 38, correcionCoordenadaEY = 7;
	public boolean estrellaCasilla;
	
	public Casilla(String tipoCasilla, int coordenadaCasillaX, int coordenadaCasillaY) {
		this.tipoCasilla = tipoCasilla;
		this.coordenadaCasillaX = coordenadaCasillaX;
		this.coordenadaCasillaY = coordenadaCasillaY;
		
		String direccion = "Imagenes/Casilla" + tipoCasilla + ".png";
		imagenCasilla = new ImageIcon(direccion);
		
		crearEstrella();
		crearCasilla(tipoCasilla);

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
		etiquetaEstrella.setBounds(coordenadaCasillaX + correcionCoordenadaEX, coordenadaCasillaY + correcionCoordenadaEY, 25, 25);
		etiquetaEstrella.setIcon(new ImageIcon(imagenEstrella.getImage().getScaledInstance(etiquetaEstrella.getWidth(), etiquetaEstrella.getHeight(), Image.SCALE_SMOOTH)));
		etiquetaEstrella.setVisible(false);
		Partida.panelPartida.add(etiquetaEstrella);
	}
	
	public void ponerEstrella() {
		etiquetaEstrella.setVisible(true);
		estrellaCasilla = true;
		
	}
	
	public void eliminarEstrella() {
		etiquetaEstrella.setVisible(false);
		estrellaCasilla = false;
		
	}
}
