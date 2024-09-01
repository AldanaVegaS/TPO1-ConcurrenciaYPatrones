package entidades;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            if (game.comenzo()) {
                ventana.pack();
                game.repaint();
                if(game.termino()){
                    if(game.gano()){

                        JOptionPane.showMessageDialog(null, "Felicidades, ganaste la partida!", "GameOver", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Perdiste :(", "GameOver", JOptionPane.INFORMATION_MESSAGE);
    
                    }
                }
            }
        }, 1, 10, TimeUnit.MILLISECONDS);
    }

}
