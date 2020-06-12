package minijuego;

import jugador.Jugador;

import javax.swing.*;

import juego.Partida;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Clase MinijuegoCuatro: consiste en
 * "lanzar" tres dados y obtener el mejor puntaje
 * @author Juan Pena
 */

public class MinijuegoCuatro extends Minijuego {
    public Jugador jugadorActual;
    private JLabel etiquetaMinijuegoCuatro, etiquetaDados, etiquetaResultado;
    private int dado1, dado2, dado3, puntaje;
    private boolean onGame;
    private ImageIcon dados = new ImageIcon("Imagenes/Dados.png");

    public MinijuegoCuatro(Jugador[] listaJugadores) {
        super(listaJugadores);
        this.setTitle("Tira 3 dados");
        tituloMinijuego.setText("Tira 3 dados");
        descripcionMinijuego.setText("El puntaje total es la suma de los dados");
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
        jugadorActual = jugador;
        dado1 = 0; dado2 = 0; dado3 = 0;
        onGame = false;
        definirComponentes();
    }
    /**
     * Metodo que definine los componentes de este minijuego:
     * Etiquetas de: instruccion, imagen de los dados
     * y la de resultados
     */
    private void definirComponentes() {
        if (jugadorActual.numeroJugadorMinijuego == 0) {
            etiquetaMinijuegoCuatro = new JLabel();
            etiquetaMinijuegoCuatro.setBounds(263, 150, 540, 400);
            etiquetaMinijuegoCuatro.setOpaque(true);
            etiquetaMinijuegoCuatro.setBackground(new Color(66, 66, 66));
            panelMinijuegos.add(etiquetaMinijuegoCuatro);

            etiquetaDados = new JLabel();
            etiquetaDados.setSize(etiquetaMinijuegoCuatro.getWidth()/3, etiquetaMinijuegoCuatro.getHeight()/2-10);
            etiquetaDados.setLocation(etiquetaMinijuegoCuatro.getWidth()/3, 0);
            etiquetaDados.setHorizontalAlignment(SwingConstants.CENTER);
            etiquetaDados.setBackground(Color.lightGray);
            etiquetaDados.setIcon((new ImageIcon(dados.getImage().getScaledInstance(etiquetaDados.getWidth(), etiquetaDados.getHeight(), Image.SCALE_SMOOTH))));
            etiquetaMinijuegoCuatro.add(etiquetaDados);

            etiquetaResultado = new JLabel ();
            etiquetaResultado.setSize(etiquetaMinijuegoCuatro.getWidth()*2/3, etiquetaMinijuegoCuatro.getHeight()/4);
            etiquetaResultado.setLocation(etiquetaMinijuegoCuatro.getWidth()*1/6, etiquetaMinijuegoCuatro.getHeight()*2/3);
            etiquetaResultado.setOpaque(true);
            etiquetaResultado.setHorizontalAlignment(SwingConstants.CENTER);
            etiquetaResultado.setBackground(Color.green);
            etiquetaResultado.setForeground(Color.white);
            etiquetaResultado.addMouseListener(this);
            etiquetaResultado.setText("Resultados:");
            etiquetaResultado.setFont(fuenteTitulo);
            etiquetaMinijuegoCuatro.add(etiquetaResultado);

        }
        else {
            etiquetaDados.setIcon((new ImageIcon(dados.getImage().getScaledInstance(etiquetaDados.getWidth(), etiquetaDados.getHeight(), Image.SCALE_SMOOTH))));
            etiquetaResultado.setText("Resultados:");
            etiquetaDados.setVisible(true);
            panelMinijuegos.repaint();
        }

            this.addMouseListener(new MouseListener() {

                @Override
                //Al hacer clic recibe los datos generados por los metodos mezclarDados() y resultadoDados()

                public void mouseClicked(MouseEvent e) {
                    if(!onGame) {
                        etiquetaResultado.removeMouseListener(null);
                        etiquetaDados.setText("Tirando dados!");
                        etiquetaDados.setFont(fuenteTitulo);
                        mezclarDados();
                        resultadoDados();
                        onGame = true;
                        jugadorActual.puntajeMinijuego = puntaje;
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    resultados();
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
     * Metodo para hacer actualizar el resultado
     * de cada dado y asignar el puntaje total al jugador
     */
    private void resultadoDados() {
        String dadoUno = Integer.toString(dado1);
        String dadoDos = Integer.toString(dado2);
        String dadoTres = Integer.toString(dado3);
        String puntos = Integer.toString(puntaje);
        etiquetaResultado.setText("Dado 1: " + dadoUno + " Dado 2: " + dadoDos + " Dado 3: " + dadoTres + "Total: " + puntos);
        etiquetaResultado.setFont(fuenteTexto);
    }

    /**
     * Metodo que obtiene los valores
     * numericos para cada dado y los suma
     */
    private void mezclarDados() {
        dado1 = (int) Math.floor(Math.random()*6+1);
        dado2 = (int) Math.floor(Math.random()*6+1);
        dado3 = (int) Math.floor(Math.random()*6+1);
        puntaje = dado1 + dado2 + dado3;


    }
    /**
     * Metodo para cerrar la ventana de minijuego
     * cuando ya han los jugadores han terminado
     */
    private void resultados () {
    	running=false;
        etiquetaDados.removeMouseListener(null);
        etiquetaDados.setVisible(false);
    	narrador.setVisible(true);
         if (jugadorActual.numeroJugadorMinijuego==listaJugadores.length-1) {
        	 if (Partida.eventoDueloActivado==true) {
 				eventoDuelo();
 				Partida.eventoDueloActivado = false;
 			}
        	 Partida.minijuegoActivado = false;
        	 Thread t2= new Thread (()-> {
					try {
						actualizarDatosMarcador();
						Thread.sleep(2000);
						etiquetaMinijuegoCuatro.setVisible(false);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				});
				t2.start();
        }
    }
    }
