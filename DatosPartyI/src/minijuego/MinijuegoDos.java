package minijuego;

import jugador.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Integer.parseInt;

/**
 * Clase MinijuegoDos:
 * Consiste en que cada jugador debe ingresar un numero entre
 * 1 y 100, con el objetivo de acertar o estar lo mas cerca posible
 * del numero real.
 * Su puntaje será determinado por la condicion de
 * que tan cerca esta del numero
 * donde 100 es el puntaje maximo
 * @author Juan Pena
 */
public class MinijuegoDos extends Minijuego implements MouseListener {
    public Jugador jugadorActual;
    private JLabel etiquetaMinijuegoDos;
    private JLabel listo;
    private JTextField adivina;
    private JLabel etiquetaIngresa;
    private int numero;
    private int puntos;
    private int diferencia;
    private boolean onGame = false;

    public MinijuegoDos(Jugador[] listaJugadores) {

        super(listaJugadores);
        this.setTitle("Piensa del 1 al 100!");
        tituloMinijuego.setText("Piensa en un número del 1 al 100");
        descripcionMinijuego.setText("Adivina el numero, entre mas cerca estes, mejor");
        descripcionMinijuego.setFont(fuenteTexto);
        panelMinijuegos.repaint();
        System.out.println("prueba");
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
        onGame=false;
        definirComponentes();

    }

    /**
     * Metodo que definine los componentes de este minijuego:
     * Etiquetas de: instruccion, cuadro para ingresar el numero
     * y la etiqueta para registrarlo
     */
    private void definirComponentes() {
        if (jugadorActual.numeroJugador == 0) {
            etiquetaMinijuegoDos = new JLabel();
            etiquetaMinijuegoDos.setBounds(263, 150, 540, 200);
            etiquetaMinijuegoDos.setOpaque(true);
            etiquetaMinijuegoDos.setBackground(new Color(66, 66, 66));
            panelMinijuegos.add(etiquetaMinijuegoDos);

            etiquetaIngresa= new JLabel();
            etiquetaIngresa.setSize(etiquetaMinijuegoDos.getWidth()*2/3, etiquetaMinijuegoDos.getHeight()/4);
            etiquetaIngresa.setLocation(etiquetaMinijuegoDos.getWidth()*1/6, etiquetaMinijuegoDos.getHeight()*1/8);
            etiquetaIngresa.setOpaque(true);
            etiquetaIngresa.setHorizontalAlignment(SwingConstants.CENTER);
            etiquetaIngresa.setBackground(Color.darkGray);
            etiquetaIngresa.setForeground(Color.white);
            etiquetaIngresa.setText("Ingresa el numero que estoy pensando");
            etiquetaIngresa.setFont(fuenteTexto);
            etiquetaMinijuegoDos.add(etiquetaIngresa);

            adivina = new JTextField();
            adivina.setSize(etiquetaMinijuegoDos.getWidth()*1/9, etiquetaMinijuegoDos.getHeight()*1/9);
            adivina.setLocation(etiquetaMinijuegoDos.getWidth()*4/9, etiquetaMinijuegoDos.getHeight()*1/2);
            adivina.setOpaque(true);
            adivina.setHorizontalAlignment(SwingConstants.CENTER);
            adivina.setBackground(Color.darkGray);
            adivina.setForeground(Color.white);
            etiquetaMinijuegoDos.add(adivina);

            listo = new JLabel ();
            listo.setSize(etiquetaMinijuegoDos.getWidth()*2/3, etiquetaMinijuegoDos.getHeight()/4);
            listo.setLocation(etiquetaMinijuegoDos.getWidth()*1/6, etiquetaMinijuegoDos.getHeight()*2/3);
            listo.setOpaque(true);
            listo.setHorizontalAlignment(SwingConstants.CENTER);
            listo.setBackground(Color.green);
            listo.setForeground(Color.white);
            listo.addMouseListener(null);
            listo.setText("Listo");
            listo.setFont(fuenteTitulo);
            etiquetaMinijuegoDos.add(listo);
        }
        else {
            listo.setText("listo");
            listo.setBackground(Color.green);
            panelMinijuegos.repaint();
        }

        this.addMouseListener(new MouseListener() {
            /**
             * En este MouseEvent se da la lógica que
             * determina el puntaje del jugador luego
             * de ingresar el número
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!onGame){
                listo.setBackground(Color.red);
                listo.setFont(fuenteTexto);
                String guess = adivina.getText();
                int numero = parseInt(guess);
                int correcto = (int) (1+Math.random()*99);
                diferencia = correcto-numero;
                puntos = 100 - Math.abs(diferencia);
                if (numero <= 100 && numero > 0){
                    if(numero != correcto){
                        listo.setText("Fallo, la diferencia fue por"+" "+ Math.abs(diferencia));
                    } else{
                        listo.setBackground(Color.green);
                        listo.setText("Felicidades! Acerto");
                    }
                    System.out.println(puntos);
                    jugadorActual.puntajeMinijuego = puntos;
                }else{
                    JOptionPane.showMessageDialog(null, "Ingresa un numero permitido", "ERROR", JOptionPane.WARNING_MESSAGE);

                }
                onGame = true;
            }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
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
     * Metodo para cerrar la ventana de minijuego
     * cuando ya han los jugadores han terminado
     */
    private void resultados () {
    		running=false;
            listo.removeMouseListener(null);
            numero = 0;
            narrador.setVisible(true);
            if (jugadorActual.numeroJugador == listaJugadores.length - 1) {
                etiquetaMinijuegoDos.setVisible(false);
        }
    }

}