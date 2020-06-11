package minijuego;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import jugador.Jugador;
/**
 * Esta es la clase que contiene el codigo del minijuego uno
 * este trata de que cada jugador en su turno disponga de 6 segundos para presionar la mayor cantidad de veces
 * que pueda la letra j del teclado, el que logra hacerlo mas veces gana el minijuego
 * @author Juan Navarro
 *
 */
public class MinijuegoUno extends Minijuego {
	
	public Jugador jugadorActual;
	private float cantidadDeJ=0;
	private JLabel etiquetaMinijuegoUno;
	private JLabel temporizador;
	private JLabel contador;
	private JLabel iniciar;
	private boolean onGame = false;
	private Timer t;
	private int s=6,cs=99;
	
	/**
	 * El metodo de la libreria Timer necesita una accion implementada con un ActionListener
	 * aunque este no es un metodo lo que hace es ejecutar una nueva accion cada vez que se utiliza
	 * a grandes rasgos lo que hace es actualizar la etiqueta de temporizador cada centesima de segundo
	 */
	private ActionListener acciones= new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			cs--;
			if (cs==0) {
				if (s==0) {
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
	 * En este contructor se contruye un nuevo minijuego que hereda la interfaz y demas metodo de la
	 * clase Minijuego
	 * @param listaJugadores
	 */
	public MinijuegoUno(Jugador listaJugadores[]) {
		super(listaJugadores);
		this.setTitle("Teclado rapido!");
		tituloMinijuego.setText("Teclado rapido!");
		descripcionMinijuego.setText("Presiona la tecla j la mayor cantidad de veces que puedas en 10s");
		panelMinijuegos.repaint();
	}
	/**
	 * Corresponde a un metodo que actualiza el temporizador, cambia el estring que aparece en este 
	 * cada que vez que se llama
	 */
	private void actualizarTempo() {
		String tiempo= s+ " : "+(cs<=9?"0":"")+cs;
		temporizador.setText(tiempo);
		panelMinijuegos.repaint();
	}
	/**
	 * Como este minijuego cuenta la cantidad de teclas j que presiona el jugador entonces se actualiza un label
	 * que sobreescribe un nuevo dato en la etiqeuta de contador
	 */
	private void actualizarContador() {
		contador.setText("Contador: "+Math.round(cantidadDeJ));
		panelMinijuegos.repaint();
	}
	
	@Override
	public void runMinijuego(Jugador jugador) {
		running=true;
		jugadorActual=jugador;
		s=5;
		cs=99;
		cantidadDeJ=0;
		onGame=false;
		definirComponentes();
	}
	/**
	 * Crea o reescirbe las etiquetas que muestran toda las infromacion del minijuego, esto dependiendo
	 * en que si el jugador es el primero que aparece en la lista de los que estan jugando o es otro
	 */
	private void definirComponentes() {
		if (jugadorActual.numeroJugadorMinijuego==0) {
			etiquetaMinijuegoUno= new JLabel ();
			etiquetaMinijuegoUno.setBounds(263, 150, 540, 200);
			etiquetaMinijuegoUno.setOpaque(true);
			etiquetaMinijuegoUno.setBackground(new Color(66, 66, 66));
			panelMinijuegos.add(etiquetaMinijuegoUno);
			
			temporizador= new JLabel();
			temporizador.setSize(etiquetaMinijuegoUno.getWidth(), etiquetaMinijuegoUno.getHeight()/4);
			temporizador.setLocation(0, 0);
			temporizador.setHorizontalAlignment(SwingConstants.CENTER);
			temporizador.setForeground(Color.white);
			temporizador.setFont(fuenteTitulo);
			temporizador.setText("6:00");
			etiquetaMinijuegoUno.add(temporizador);
			
			contador= new JLabel();
			contador.setSize(etiquetaMinijuegoUno.getWidth()/2, etiquetaMinijuegoUno.getHeight()/4);
			contador.setLocation(etiquetaMinijuegoUno.getWidth()/4, etiquetaMinijuegoUno.getHeight()/4);
			contador.setHorizontalAlignment(SwingConstants.CENTER);
			contador.setBackground(Color.white);
			contador.setFont(fuenteTitulo);
			contador.setForeground(Color.BLACK);
			contador.setText("Contador: "+cantidadDeJ);
			etiquetaMinijuegoUno.add(contador);
			
			iniciar= new JLabel ();
			iniciar.setSize(etiquetaMinijuegoUno.getWidth()/3, etiquetaMinijuegoUno.getHeight()/4);
			iniciar.setLocation(etiquetaMinijuegoUno.getWidth()*1/3, etiquetaMinijuegoUno.getHeight()*2/3);
			iniciar.setOpaque(true);
			iniciar.setHorizontalAlignment(SwingConstants.CENTER);
			iniciar.setBackground(Color.green);
			iniciar.setForeground(Color.white);
			iniciar.addMouseListener(null);
			iniciar.setText("Iniciar");
			iniciar.setFont(fuenteTitulo);
			etiquetaMinijuegoUno.add(iniciar);
		}
		else {
			temporizador.setVisible(true);
			temporizador.setText("6:00");
			contador.setVisible(true);
			contador.setText("Contador: "+cantidadDeJ);
			iniciar.setBackground(Color.green);
			iniciar.setText("Iniciar");
			panelMinijuegos.repaint();
		}
	
	
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (onGame==true) {
				String j=String.valueOf(e.getKeyChar());
				if ("j".equals(j)) {
					if (jugadorActual.numeroJugadorMinijuego==0) {
						cantidadDeJ+=1;
					}
					else if (jugadorActual.numeroJugadorMinijuego==1) {
						cantidadDeJ+=0.5;
					}
					else if (jugadorActual.numeroJugadorMinijuego==2) {
						cantidadDeJ+=0.33;
					}
					else if (jugadorActual.numeroJugadorMinijuego==3) {
						cantidadDeJ+=0.25;
					}
					
				}
				actualizarContador();
			}
			}
			
		});
		
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				iniciar.setBackground(Color.red);
				if (onGame==false) {
					onGame=true;
					iniciar.setVisible(false);
					Thread t1= new Thread (()->{
						t= new Timer(10, acciones);	
						t.start();
						
					});
						t1.start();
						
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
	/**
	 * En este metodo se cambia el atributo de cada jugador por un nuevo puntaje obtenido
	 * Luego con este puntaje se decide el orden de los resultados del minujuego
	 * Tmabien esconde las etiquetas para evitar problemas con el codigo mientras cambia de jugador, para que el proximo juegue el minijuego
	 * En caso de ser el ultimo cierra todo
	 */
	private void resultados () {
		running=false;
		iniciar.setVisible(true);
		jugadorActual.puntajeMinijuego+= cantidadDeJ;
		iniciar.setText("Puntaje: "+jugadorActual.puntajeMinijuego);
		contador.setVisible(false);
		temporizador.setVisible(false);
		iniciar.removeMouseListener(null);
		narrador.setVisible(true);
		
		if (jugadorActual.numeroJugadorMinijuego==listaJugadores.length-1) {
			this.actualizarDatosMarcador();
			etiquetaMinijuegoUno.setVisible(false);
		}
	}
	

}