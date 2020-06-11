package minijuego;

import jugador.Jugador;

import javax.swing.*;

import juego.Partida;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Clase MinijuegoTres que consiste en
 * Responder una pregunta de cultura general
 * Es una diferente para cada jugador
 * Gana 10 puntos si acierta, 0 si falla
 * @author Juan Pena
 */
public class MinijuegoTres extends Minijuego {
    public Jugador jugadorActual;
    private JLabel etiquetaMinijuegoTres, etiquetaPregunta, iniciar;
    private String respuestaUno, respuestaDos, respuestaTres, respuestaCuatro;
    private String respuestaUno2, respuestaDos2, respuestaTres2, respuestaCuatro2;
    private String respuestaUno3, respuestaDos3, respuestaTres3, respuestaCuatro3;
    private String respuestaUno4, respuestaDos4, respuestaTres4, respuestaCuatro4;
    private boolean onGame;
    private JComboBox<String> cajaRespuestas = new JComboBox<>();
    private int puntaje;

    public MinijuegoTres(Jugador[] listaJugadores) {

        super(listaJugadores);
        this.setTitle("Cultura General");
        tituloMinijuego.setText("Cultura General");
        descripcionMinijuego.setText("Responde a la siguiente pregunta de cultura general");
        descripcionMinijuego.setFont(fuenteTexto);
        panelMinijuegos.repaint();
    }
    /**
     * Metodo heredado donde se corren los
     * componentes actualizados a cada jugador para
     * la ventana del minijuego
     * @param jugador
     */
    public void runMinijuego(Jugador jugador) {
    	running=true;
        jugadorActual=jugador;
        onGame = false;
        definirComponentes();
    }
    /**
     * Metodo que definine los componentes de este minijuego:
     * Etiquetas de: pregunta, caja de respuestas y el resgistro
     * de dicha respuesta, en el cual también se muestra si
     * respondió bien o mal
     */
    private void definirComponentes() {
        if (jugadorActual.numeroJugadorMinijuego == 0) {
            etiquetaMinijuegoTres= new JLabel ();
            etiquetaMinijuegoTres.setBounds(263, 150, 540, 200);
            etiquetaMinijuegoTres.setOpaque(true);
            etiquetaMinijuegoTres.setBackground(new Color(66, 66, 66));
            panelMinijuegos.add(etiquetaMinijuegoTres);

            etiquetaPregunta = new JLabel("En que pais se encuentra el famoso monumento Taj Mahal?");
            etiquetaPregunta.setSize(etiquetaMinijuegoTres.getWidth(), etiquetaMinijuegoTres.getHeight()/4);
            etiquetaPregunta.setLocation(0, 0);
            etiquetaPregunta.setHorizontalAlignment(SwingConstants.CENTER);
            etiquetaPregunta.setForeground(Color.white);
            etiquetaPregunta.setFont(fuenteTexto);
            etiquetaMinijuegoTres.add(etiquetaPregunta);

            respuestaUno = "Arabia Saudita"; respuestaDos = "Qatar"; respuestaTres = "India"; respuestaCuatro = "Pakistan";
            cajaRespuestas = new JComboBox();
            cajaRespuestas.addItem(respuestaUno); cajaRespuestas.addItem(respuestaDos); cajaRespuestas.addItem(respuestaTres);
            cajaRespuestas.addItem(respuestaCuatro);
            cajaRespuestas.setSize(etiquetaMinijuegoTres.getWidth()*3/4, etiquetaMinijuegoTres.getHeight()/4);
            cajaRespuestas.setLocation(etiquetaMinijuegoTres.getWidth()*1/6, etiquetaMinijuegoTres.getHeight()*1/3);
            cajaRespuestas.setBackground(Color.ORANGE);
            cajaRespuestas.setForeground(Color.white);
            cajaRespuestas.setFont(fuenteTexto);
            etiquetaMinijuegoTres.add(cajaRespuestas);

            iniciar= new JLabel ();
            iniciar.setSize(etiquetaMinijuegoTres.getWidth()/3, etiquetaMinijuegoTres.getHeight()/4);
            iniciar.setLocation(etiquetaMinijuegoTres.getWidth()*1/3, etiquetaMinijuegoTres.getHeight()*2/3);
            iniciar.setOpaque(true);
            iniciar.setHorizontalAlignment(SwingConstants.CENTER);
            iniciar.setBackground(Color.green);
            iniciar.setForeground(Color.white);
            iniciar.addMouseListener(null);
            iniciar.setText("Listo");
            iniciar.setFont(fuenteTitulo);
            etiquetaMinijuegoTres.add(iniciar);

        }
        else {
            iniciar.setText("Listo");
            etiquetaPregunta.setVisible(true);
            cajaRespuestas.setVisible(true);
            iniciar.setBackground(Color.green);
            panelMinijuegos.repaint();
        }
        if (jugadorActual.numeroJugadorMinijuego == 0) {
            this.addMouseListener(new MouseListener() {


                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!onGame) {
                        iniciar.setBackground(Color.red);
                        if (jugadorActual.numeroJugadorMinijuego == 0) {
                            comparaRespuesta(respuestaTres);
                            jugadorActual.puntajeMinijuego = puntaje;
                            etiquetaPregunta.setText("Cual es el oceano mas grande?");
                            cajaRespuestas.removeAllItems();
                            respuestaUno2 = "Atlantico";
                            respuestaDos2 = "Pacifico";
                            respuestaTres2 = "Indico";
                            respuestaCuatro2 = "Artico";
                            cajaRespuestas.addItem(respuestaUno2);
                            cajaRespuestas.addItem(respuestaDos2);
                            cajaRespuestas.addItem(respuestaTres2);
                            cajaRespuestas.addItem(respuestaCuatro2);
                            panelMinijuegos.repaint();
                        } else if (jugadorActual.numeroJugadorMinijuego == 1) {
                            comparaRespuesta(respuestaDos2);
                            jugadorActual.puntajeMinijuego = puntaje;
                            etiquetaPregunta.setText("Quien escribio La Odisea?");
                            cajaRespuestas.removeAllItems();
                            respuestaUno3 = "Esquilo";
                            respuestaDos3 = "Sofocles";
                            respuestaTres3 = "Esopo";
                            respuestaCuatro3 = "Homero";
                            cajaRespuestas.addItem(respuestaUno3);
                            cajaRespuestas.addItem(respuestaDos3);
                            cajaRespuestas.addItem(respuestaTres3);
                            cajaRespuestas.addItem(respuestaCuatro3);
                            panelMinijuegos.repaint();
                        } else if (jugadorActual.numeroJugadorMinijuego == 2) {

                            comparaRespuesta(respuestaCuatro3);
                            jugadorActual.puntajeMinijuego = puntaje;

                            etiquetaPregunta.setText("Si 50 es el 100 porciento, cuanto es el 90?");
                            cajaRespuestas.removeAllItems();
                            respuestaUno4 = "45";
                            respuestaDos4 = "47";
                            respuestaTres4 = "43";
                            respuestaCuatro4 = "40";
                            cajaRespuestas.addItem(respuestaUno4);
                            cajaRespuestas.addItem(respuestaDos4);
                            cajaRespuestas.addItem(respuestaTres4);
                            cajaRespuestas.addItem(respuestaCuatro4);
                            panelMinijuegos.repaint();
                        } else if (jugadorActual.numeroJugadorMinijuego == 3) {

                            comparaRespuesta(respuestaUno4);
                            jugadorActual.puntajeMinijuego = puntaje;
                        }
                        onGame = true;
                        resultados();
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

    private void comparaRespuesta(String respuesta) {
        if (cajaRespuestas.getItemAt(cajaRespuestas.getSelectedIndex()) == respuesta){
            iniciar.setText("Acerto!");
            iniciar.setBackground(Color.green);
            puntaje = 10;
        }else{iniciar.setText("Fallo!"); iniciar.setBackground(Color.red); puntaje = 0;}
    }


    private void resultados() {
    	running=false;
        iniciar.setText("Puntaje: "+jugadorActual.puntajeMinijuego);
        iniciar.removeMouseListener(null);
        etiquetaPregunta.setVisible(false);
        cajaRespuestas.setVisible(false);
        iniciar.removeMouseListener(null);
        narrador.setVisible(true);
        if (jugadorActual.numeroJugadorMinijuego == listaJugadores.length - 1) {
        	if (Partida.minijuegoActivado==true) {
				eventoDuelo();
				Partida.minijuegoActivado= false;
			}
        	Thread t2= new Thread (()-> {
				try {
					actualizarDatosMarcador();
					Thread.sleep(2000);
					etiquetaMinijuegoTres.setVisible(false);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			});
			t2.start();
        }
    }
}

