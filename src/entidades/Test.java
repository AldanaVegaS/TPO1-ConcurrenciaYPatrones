package entidades;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Builder.MapaBuilder;
import Builder.Configuracion;
import Builder.Mapa;
import Builder.Nivel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

//import java.awt.BorderLayout;

public class Test {

    public static void main(String[] args) {
        Juego game = new Juego();
        JFrame ventana = new JFrame();
        ventana.setSize(400, 400);
        ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ventana.setTitle("Juego");
        // ventana.add(game);
        game.inicializarMenu(ventana);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        // while (true) {
        // try {
        // Thread.sleep(1000);
        // } catch (Exception e) {
        // }
        // game.repaint();
        // }

        // JFrame ventana = new JFrame();
        // ventana.add(game);
        // game.setLocationRelativeTo(null);
        // game.pack();
        // game.setVisible(true);

        // JFrame c = new JFrame();
        // c.setSize(500, 600);
        // Configuracion config = new Configuracion();
        // MapaBuilder mapaBuilder = new MapaBuilder();
        // Mapa mapa = config.configurarjuego(mapaBuilder, Nivel.DIFICIL);
        // c.add(mapa);
        // c.pack();
        // c.setVisible(true);
    }

}
