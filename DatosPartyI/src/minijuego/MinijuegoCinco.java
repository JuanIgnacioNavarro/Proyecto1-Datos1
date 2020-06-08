package minijuego;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;


import jugador.Jugador;

public class MinijuegoCinco extends Minijuego implements MouseListener {

// Atributos de las etiquetas del juego
	public Jugador jugadorActual;
	private JLabel etiquetaMinijuegoCinco;
	private JLabel temporizador;
	private JLabel resultado;
	private JLabel iniciar; 
	
//Atributos que controlan el reloj
	private boolean onGame= false;
	private boolean allowResults= false;
	private Timer t;
	private int s=0, cs=99;
	
    public MinijuegoCinco (Jugador listaJugadores[]) {
    	super(listaJugadores);
    	this.setTitle("Reloj Mental!");
    	tituloMinijuego.setText("Reloj Mental");
    	descripcionMinijuego.setText("Intenta parar el reloj a los 3s, usa tu mente");
    	panelMinijuegos.repaint();
    }
    
    @Override
    public void runMinijuego (Jugador jugador) {
    	jugadorActual=jugador;
    	s=9;
    	cs=99;
    	onGame= false;
    	definirComponentes();
    }
    
    private void definirComponentes () {
    	if (jugadorActual.numeroJugador==0) {
    		etiquetaMinijuegoCinco= new JLabel ();
    		etiquetaMinijuegoCinco.setBounds(263, 150, 540, 200);
    		etiquetaMinijuegoCinco.setOpaque(true);
    		etiquetaMinijuegoCinco.setBackground(new Color(66,66,66));
    		panelMinijuegos.add(etiquetaMinijuegoCinco);
    		
    		temporizador= new JLabel();
    		temporizador.setSize(etiquetaMinijuegoCinco.getWidth(), etiquetaMinijuegoCinco.getHeight()/4);
    		temporizador.setLocation(0, 0);
    		temporizador.setHorizontalAlignment(SwingConstants.CENTER);
    		temporizador.setForeground(Color.white);
    		temporizador.setFont(fuenteTitulo);
    		temporizador.setText("10:00");
    		etiquetaMinijuegoCinco.add(temporizador);
    		
    		resultado= new JLabel(); //En este Label se mostrará el resulatdo para mantener el orden
    		resultado.setSize(etiquetaMinijuegoCinco.getWidth(), etiquetaMinijuegoCinco.getHeight()/4);
    		resultado.setLocation(0, etiquetaMinijuegoCinco.getHeight()/4);
    		resultado.setHorizontalAlignment(SwingConstants.CENTER);
    		resultado.setVerticalAlignment(SwingConstants.CENTER);
    		resultado.setBackground(Color.white);
    		resultado.setFont(fuenteTexto);
    		resultado.setForeground(Color.black);
    		resultado.setText("");
    		etiquetaMinijuegoCinco.add(resultado);
    		
    		iniciar = new JLabel();
    		iniciar.setSize(etiquetaMinijuegoCinco.getWidth()/3, etiquetaMinijuegoCinco.getHeight()/4);
    		iniciar.setLocation(etiquetaMinijuegoCinco.getWidth()*1/3, etiquetaMinijuegoCinco.getHeight()*2/3);
    		iniciar.setOpaque(true);
    		iniciar.setForeground(Color.white);
    		iniciar.setHorizontalAlignment(SwingConstants.CENTER);
    		iniciar.setBackground(Color.green);
    		iniciar.addMouseListener(this);
    		iniciar.setFont(fuenteTitulo);
    		iniciar.setText("Empezar!");
    		etiquetaMinijuegoCinco.add(iniciar);
    	}
    	else {
    		temporizador.setVisible(true);
    		temporizador.setText("10:00");
    		resultado.setVisible(true);
    		resultado.setText("Intenta parar el reloj en 3s");
    		iniciar.setText("Empezar!");
    		panelMinijuegos.repaint();
    	}
    	
    	if (jugadorActual.numeroJugador==0) {
    	iniciar.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//if (e.getSource()==iniciar) {
					iniciar.setBackground(Color.red);
					if (onGame==false) {
						iniciar.setText("Pausar!");
						onGame=true;
						Thread t1= new Thread (()-> {
							t= new Timer (10, acciones);
							t.start();
						});
						t1.start();
						allowResults=true;
					}
					else if (allowResults==true) {
						t.stop();
						temporizador.setVisible(true);
						iniciar.setEnabled(false);
						resultados ();
					}
				//}
			}
			
			private ActionListener acciones=new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent e) {
					cs--;
					if (cs==0) {
						if (s==7) {
							temporizador.setVisible(false);
						}
						if (s==0) {
							
							temporizador.setVisible(true);
							onGame=false;
							iniciar.setEnabled(false);
							resultados();
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
			
			private void actualizarTempo() {
				String tiempo=s+ " : "+(cs<=9?"0":"")+cs;
				temporizador.setText(tiempo);
				panelMinijuegos.repaint();
			}
			
			private void resultados() {
	    		System.out.println("Estoy en resultados");
				int difSegundos;
				int centesimasRestantes;
				if (s>=3) {
					difSegundos=s-4;
					centesimasRestantes=cs;
				}
				else {
					difSegundos=2-s;
					centesimasRestantes=100-cs;
				}
				resultado.setText("La diferencia es de "+difSegundos+" : "+(centesimasRestantes<=9?"0":"")+centesimasRestantes);
				int difMaxima= 699;
				int difEnCentesimas= difSegundos*100+centesimasRestantes;
				int puntaje= (difMaxima-difEnCentesimas)/10;
				jugadorActual.puntajeMinijuego=puntaje;
				iniciar.setText("Puntaje: "+puntaje);
				iniciar.removeMouseListener(null);
				panelMinijuegos.repaint();
				//onGame=false;
				
				//Ocultar para el mostrar resultados
				narrador.setVisible(true);
				if (jugadorActual.numeroJugador==listaJugadores.length-1) {
					etiquetaMinijuegoCinco.setVisible(false);
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
}