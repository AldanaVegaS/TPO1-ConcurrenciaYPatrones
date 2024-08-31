package entidades;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Builder.MapaBuilder;
import Builder.Configuracion;
import Builder.Mapa;
import Builder.Nivel;

//import java.awt.BorderLayout;

public class Test {

    public static void main(String[] args) {
        Juego game = new Juego();
        JFrame ventana = new JFrame();
        ventana.add(game);
        ventana.setLocationRelativeTo(null);
        ventana.pack();
        ventana.setVisible(true);

        //  JFrame c = new JFrame();
        // c.setSize(500, 600);
        // Configuracion config = new Configuracion();
        // MapaBuilder mapaBuilder = new MapaBuilder();
        // Mapa mapa = config.configurarjuego(mapaBuilder, Nivel.DIFICIL);
        // c.add(mapa);
        // c.pack();
        // c.setVisible(true);
    }

}
