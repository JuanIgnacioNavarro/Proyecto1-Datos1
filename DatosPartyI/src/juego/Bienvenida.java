package juego;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;



/**
 * Esta clase instancia la ventana de bienvenida al juego.
 * 
 * @author Juan Pena
 */
public class Bienvenida extends JFrame implements ActionListener {
    public static Color colorVentana = new Color(94, 94, 94);
    private JPanel panelBienvenida;
    private JButton botonInicio;
    private JLabel etiquetaBienvenida;
    private ImageIcon imagenEstrella = new ImageIcon("Imagenes/Moneda.png");
    private Font fuenteTitulo = new Font("Comic Sans MS", 1, 25);

    /**
     * Constructor de Bienvenida() Agrega el botón de inicio para la siguiente
     * ventana
     */
    public Bienvenida() {
        setTitle("Bienvenido a Datos Party I");
        setVisible(true);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        agregarPanel();
        agregarEtiqueta();
        agregarBoton();
        
        panelBienvenida.repaint();
    }

    private void agregarPanel() {
        panelBienvenida = new JPanel();
        this.getContentPane().add(panelBienvenida);
        panelBienvenida.setLayout(null);
        panelBienvenida.setBackground(colorVentana);

    }

    private void agregarEtiqueta() {

        etiquetaBienvenida = new JLabel();
        etiquetaBienvenida.setSize(300, 300);
        etiquetaBienvenida.setLocation((this.getWidth() / 2) - (etiquetaBienvenida.getWidth() / 2), 5);
        etiquetaBienvenida.setIcon(new ImageIcon(imagenEstrella.getImage().getScaledInstance(etiquetaBienvenida.getWidth(), etiquetaBienvenida.getHeight(), Image.SCALE_SMOOTH)));
        panelBienvenida.add(etiquetaBienvenida);

    }

    private void agregarBoton() {
        botonInicio = new JButton("INICIAR");
        botonInicio.setSize(150, 75);
        botonInicio.setLocation((this.getWidth() / 2) - (botonInicio.getWidth() / 2), (this.getHeight() / 2) + botonInicio.getHeight());
        botonInicio.setFont(fuenteTitulo);
        botonInicio.setLayout(null);
        botonInicio.addActionListener(this);
        panelBienvenida.add(botonInicio);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonInicio) {
            this.setVisible(false);
            Inicio inicio = new Inicio();
        }

    }

}
