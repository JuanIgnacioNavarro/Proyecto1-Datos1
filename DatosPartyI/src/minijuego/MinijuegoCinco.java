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
/**
 * Esta es la clase del minijuego 5, corresponde a un minijuego que reta al jugador a parar un reloj que 
 * se muestra en los primeros 3 segundos desde el decimo en los tres segundos, de lograr pararlo justo cuando el reloj
 * marca los 3 segundos se obtendran 70 puntos, si no entre mas se aleje obtendra menos puntos
 * @author Juan Navarro
 *
 */
public class MinijuegoCinco extends Minijuego {

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
	
	/**
	 * Metodo contructor del minijuego, aqu� usa el contructor de la clase padre
	 * que crea la interfaz grafica basica, el resto se desarrolla en esta clase
	 * @param listaJugadores
	 */
    public MinijuegoCinco (Jugador listaJugadores[]) {
    	super(listaJugadores);
    	this.setTitle("Reloj Mental!");
    	tituloMinijuego.setText("Reloj Mental");
    	descripcionMinijuego.setText("Intenta parar el reloj cuando marque 3s, usa tu mente");
    	panelMinijuegos.repaint();
    }
    
    @Override
    public void runMinijuego (Jugador jugador) {
    	running=true;
    	jugadorActual=jugador;
    	s=9;
    	cs=99;
    	onGame= false;
    	definirComponentes();
    }
    /**
	 *Crea o reescirbe las etiquetas que muestran toda las infromacion del minijuego, esto dependiendo
	 * en que si el jugador es el primero que aparece en la lista de los que estan jugando o es otro
     */
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
    		
    		resultado= new JLabel(); //En este Label se mostrara el resulatdo para mantener el orden
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
    		iniciar.setBackground(Color.green);
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
					if (e.getSource()==iniciar) {
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
							resultados ();
						}
					}
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
				
				/**
				 * Es un metodo que permite actualizar el temporizador
				 */
				private void actualizarTempo() {
					String tiempo=s+ " : "+(cs<=9?"0":"")+cs;
					temporizador.setText(tiempo);
					panelMinijuegos.repaint();
				}
				/**
				 * Define el puntaje obtenido por cada jugador, basado en que tan cercano estuvo
				 * su resultado de los tres segundos del reloj
				 * Tambien esconde los label que pueden traer problemas al cambiar de jugador o mostrar los resultados
				 */
				private void resultados() {
					running=false;
					System.out.println("Estoy en resultados");
					int difSegundos;
					int centesimasRestantes;
					if (s>3) {
						difSegundos=s-4;
						centesimasRestantes=cs;
					}
					else if(s==3)  {
						difSegundos=0;
						centesimasRestantes=cs;
					}
					else {
						difSegundos=2-s;
						centesimasRestantes=100-cs;
					}
					resultado.setText("La diferencia es de "+difSegundos+" : "+(centesimasRestantes<=9?"0":"")+centesimasRestantes);
					int difMaxima= 700;
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
						Thread t2= new Thread (()-> {
							try {
								actualizarDatosMarcador();
								Thread.sleep(2000);
								etiquetaMinijuegoCinco.setVisible(false);
							} catch (InterruptedException e) {
								System.out.println("Estoy en el catch del ultimo jugador");
								e.printStackTrace();
							}
							
						});
						t2.start();
						
					}
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					
				}
				
			});
    	}
    }
}