package minijuego;

import jugador.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Integer.parseInt;

public class MinijuegoCuatro extends Minijuego implements MouseListener {
    public Jugador jugadorActual;
    private JLabel etiquetaMinijuegoCuatro;
    private JLabel etiquetaDados;
    private JLabel etiquetaResultado;
    private int dado1, dado2, dado3, puntaje;
    private ImageIcon dados = new ImageIcon("Imagenes/Dados.png");

    public MinijuegoCuatro(Jugador[] listaJugadores) {

        super(listaJugadores);
        this.setTitle("Tira 3 dados");
        tituloMinijuego.setText("Tira 3 dados");
        descripcionMinijuego.setText("El puntaje total es la suma de los dados");
        descripcionMinijuego.setFont(fuenteTexto);
        panelMinijuegos.repaint();
        System.out.println("prueba");
    }

    public void runMinijuego(Jugador jugador) {
        jugadorActual = jugador;
        dado1 = 0; dado2 = 0; dado3 = 0;
        definirComponentes();
    }

    private void definirComponentes() {
        if (jugadorActual.numeroJugador == 0) {
            etiquetaMinijuegoCuatro = new JLabel();
            etiquetaMinijuegoCuatro.setBounds(263, 150, 540, 400);
            etiquetaMinijuegoCuatro.setOpaque(true);
            etiquetaMinijuegoCuatro.setBackground(new Color(66, 66, 66));
            panelMinijuegos.add(etiquetaMinijuegoCuatro);

            etiquetaDados = new JLabel();
            etiquetaDados.setSize(etiquetaMinijuegoCuatro.getWidth(), etiquetaMinijuegoCuatro.getHeight()/2);
            etiquetaDados.setLocation(0, 0);
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
            etiquetaResultado.setText("Resultados:");
            etiquetaResultado.setFont(fuenteTitulo);
            etiquetaMinijuegoCuatro.add(etiquetaResultado);

        }else {
            etiquetaResultado.setText("Resultados:");
            panelMinijuegos.repaint();
        }

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                    etiquetaDados.setText("Tirando dados!");
                    etiquetaDados.setFont(fuenteTitulo);
                    mezclarDados();
                    String dadoUno = Integer.toString(dado1);
                    String dadoDos = Integer.toString(dado2);
                    String dadoTres = Integer.toString(dado3);
                    String puntos = Integer.toString(puntaje);
                    etiquetaResultado.setText("Dado 1: " + dadoUno + " Dado 2: " + dadoDos + " Dado 3: " + dadoTres + "Total: " + puntos);
                    etiquetaResultado.setFont(fuenteTexto);
                    jugadorActual.puntajeMinijuego = puntaje;
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
    private void mezclarDados() {
        dado1 = (int) Math.floor(Math.random()*6+1);
        dado2 = (int) Math.floor(Math.random()*6+1);
        dado3 = (int) Math.floor(Math.random()*6+1);
        puntaje = dado1 + dado2 + dado3;


    }
    private void resultados () {
         if (jugadorActual.numeroJugador==listaJugadores.length-1) {
            etiquetaMinijuegoCuatro.setVisible(false);
        }
    }
    }
