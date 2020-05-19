package paqueteJugador;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class Partida extends JFrame{
	public static JPanel panel;
	public Partida() {
		configuracionJFrame(); 
//		 ______
//______/ Instanciar los respectivos jugadores
		
		Jugador jugador1= new ConstructorJugador(1);
		//jugador1= new Jugador(1);
		Jugador jugador2= new ConstructorJugador(2);
		Jugador jugador3= new ConstructorJugador(3);
		Jugador jugador4= new ConstructorJugador(4);

		panel.repaint();
		
	}
	
	
	public void configuracionJFrame() {
//		 ______
//______/ Configuración del JFrame
		setTitle("Ventana Partida");
		setVisible(true);
		setSize(500, 500);
		setLocationRelativeTo(this);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		 ______
//______/ Configuración del Panel
		panel= new JPanel();
		this.getContentPane().add(panel);
		Partida.panel.setLayout(null); 
		Partida.panel.setBackground(new Color(235, 233, 126));
	}
	
	public static void main (String[] args) {
		Partida partida= new Partida();
	}
}
