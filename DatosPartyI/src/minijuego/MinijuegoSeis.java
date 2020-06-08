package minijuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import jugador.Jugador;

public class MinijuegoSeis extends Minijuego implements MouseListener{
	
//Atributos de las etiquetas del juego
	public Jugador jugadorActual;
	private JLabel etiquetaMinijuegoSeis;
	private JLabel temporizador;
	private JLabel lista;
	private JTextField respuesta;
	private JLabel responder;
	private int[] listaJuego= new int[5];
	private int elementoPregunta;
	
//Atributos que controlan el reloj
	private boolean onGame=false;
	private boolean allowResults= false;
	private Timer t;
	private int s=0, cs=99;

//Action listener del boton
	private ActionListener acciones = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			cs--;
			if (cs==0) {
				if (s==0) {
					responder.setVisible(true);
					//temporizador.setVisible(false);
					actualizarBoton();
					t.stop();
				}
				else {
					cs=99;
					s--;
				}
			}
			actualizarTempo();
		}
		 
	 };
    
	
	public MinijuegoSeis(Jugador listaJugadores[]) {
		super(listaJugadores);
		this.setTitle("Memoriza la lista!");
		tituloMinijuego.setText("Memoriza la lista");
		descripcionMinijuego.setText("La lista saldrá por 5s, memorizala!");
		panelMinijuegos.repaint();
	}
	@Override
	public void runMinijuego(Jugador jugador) {
		jugadorActual=jugador;
		s=4;
		cs=99;
		onGame= false; 
		definirComponentes();
	}
	
	private void definirComponentes () {
		if (jugadorActual.numeroJugador==0) {
			etiquetaMinijuegoSeis= new JLabel ();
			etiquetaMinijuegoSeis.setBounds(263, 150, 540, 200);
			etiquetaMinijuegoSeis.setOpaque(true);
			etiquetaMinijuegoSeis.setBackground(new Color(66,66,66));
			panelMinijuegos.add(etiquetaMinijuegoSeis);
			
			temporizador= new JLabel ();
			temporizador.setSize(etiquetaMinijuegoSeis.getWidth(), etiquetaMinijuegoSeis.getHeight()/4);
			temporizador.setLocation(0,  0);
			temporizador.setHorizontalAlignment(SwingConstants.CENTER);
			temporizador.setVerticalAlignment(SwingConstants.CENTER);
			temporizador.setForeground(Color.white);
			temporizador.setFont(fuenteTitulo);
			temporizador.setText("5:00");
			etiquetaMinijuegoSeis.add(temporizador);
			
			lista= new JLabel ();
			lista.setSize(etiquetaMinijuegoSeis.getWidth(), etiquetaMinijuegoSeis.getHeight()/4);
			lista.setLocation(0, etiquetaMinijuegoSeis.getHeight()/4);
			lista.setHorizontalAlignment(SwingConstants.CENTER);
			lista.setVerticalAlignment(SwingConstants.CENTER);
			lista.setBackground(Color.white);
			lista.setFont(fuenteTexto);
			lista.setForeground(Color.black);
			anadirTextoLista(); //Este método añade el texto a la lista y crea la lista que se va a usar en el juego
			etiquetaMinijuegoSeis.add(lista);
			
			responder= new JLabel();
			responder.setSize(etiquetaMinijuegoSeis.getWidth()*1/3, etiquetaMinijuegoSeis.getHeight()/4);
			responder.setLocation(etiquetaMinijuegoSeis.getWidth()*1/3, etiquetaMinijuegoSeis.getHeight()*2/3);
			responder.setOpaque(true);
			responder.setForeground(Color.white);
			responder.setHorizontalAlignment(SwingConstants.CENTER);
			responder.setBackground(Color.green);
			responder.addMouseListener(this);
			responder.setFont(fuenteTitulo);
			responder.setText("Empezar!");
			etiquetaMinijuegoSeis.add(responder);
		}
		else {
			temporizador.setVisible(true);
			temporizador.setText("5:00");
			responder.setFont(fuenteTitulo);
			responder.setBackground(Color.green);
			responder.setText("Empezar!");
			anadirTextoLista(); //Este método añade el texto a la lista y crea la lista que se va a usar en el juego
			panelMinijuegos.repaint();
		}
		if (jugadorActual.numeroJugador==0) {
			responder.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					
					if (onGame==false) {
						onGame=true;
						lista.setVisible(true);
						responder.setBackground(Color.RED);
						responder.setVisible(false);
						responder.setText("Responder");
						t= new Timer (10, acciones);
						t.start();
					}
					else {
						System.out.println("Lo escrito dentro del jtextfield es"+respuesta.getText());
						try {
							int numeroRespuesta= Integer.parseInt(respuesta.getText());
							resultados(numeroRespuesta);
						}catch(Exception l) {
							JOptionPane.showMessageDialog(null, "La lista solo tiene números enteros, debe ingresar solo uno", "Error al ingresar numero", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		 
	}
	
	private void anadirTextoLista() {
		for (int i=0; i< this.listaJuego.length ; i++) {
			int randomInt;
			Random random= new Random();
			randomInt= random.nextInt(100);
			listaJuego[i]=randomInt;
		}
		String textoLista= "[";
		for (int i=0; i< this.listaJuego.length-1 ; i++) {
			textoLista=textoLista.concat(listaJuego[i]+" ,");
		}
		textoLista=textoLista.concat(listaJuego[this.listaJuego.length-1]+"]");
		lista.setText(textoLista);
		lista.setVisible(false);//después de probarlo cmabiarlo por false
		System.out.println(lista.getText());
	}
	private void actualizarTempo() {
		String tiempo=s+ " : "+(cs<=9?"0":"")+cs;
		temporizador.setText(tiempo);
		if (s==0 & cs==0) {
			Random random= new Random();
			elementoPregunta= random.nextInt(listaJuego.length);
			temporizador.setText("Cual es el elemento numero "+(elementoPregunta+1)+"?");
		}
		panelMinijuegos.repaint();
	}
	private void actualizarBoton() {
		responder.setVisible(true);
		lista.setVisible(false);
		if (jugadorActual.numeroJugador==0) {
			respuesta= new JTextField();
			respuesta.setSize(etiquetaMinijuegoSeis.getWidth()/3, etiquetaMinijuegoSeis.getHeight()/4);
			respuesta.setLocation(etiquetaMinijuegoSeis.getWidth()/3, etiquetaMinijuegoSeis.getHeight()/4);
			respuesta.setHorizontalAlignment(SwingConstants.CENTER);
			respuesta.setBackground(Color.yellow);
			respuesta.setForeground(Color.black);
			respuesta.setFont(fuenteTexto);
			etiquetaMinijuegoSeis.add(respuesta);
			panelMinijuegos.repaint();
		}
		else {
			respuesta.setText("");
			respuesta.setVisible(true);
			panelMinijuegos.repaint();
		}
	}
	
	private void resultados(int numeroRespuesta) {
		responder.setFont(new Font("Comic Sans MS", 0, 14));
		if (numeroRespuesta==listaJuego[elementoPregunta]) {
			jugadorActual.puntajeMinijuego=10;
			responder.setText("Correcto! puntaje=10");
		}
		else {
			jugadorActual.puntajeMinijuego=0;
			responder.setText("Incorrecto! puntaje=0");
		}
		narrador.setVisible(true);
		respuesta.setVisible(false);
		temporizador.setVisible(false);
		if (jugadorActual.numeroJugador==listaJugadores.length-1) {
			etiquetaMinijuegoSeis.setVisible(false);
		}
	}
	
	
	
}