package entidades;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//import java.awt.BorderLayout;

public class Test {

    public static void main(String[] args) {
        Juego game = new Juego();
        JFrame ventana = new JFrame();
        ventana.add(game);

        ventana.pack();
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            if (game.comenzo()) {
                ventana.pack();
                game.repaint();
                if (game.termino()) {
                    int response;
                    if (game.gano()) {

                        response = JOptionPane.showOptionDialog(null, "Felicidades, ganaste la partida!", "GameOver",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new Object[] { "Salir" },
                                "Salir");
                    } else {
                        response = JOptionPane.showOptionDialog(null, "Perdiste :(", "GameOver",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new Object[] { "Salir" },
                                "Salir");
                    }
                    if (response == JOptionPane.OK_OPTION) {
                        // Realiza la operación deseada
                        ventana.setVisible(false);
                        executor.shutdown();
                    } 
                }
            }
        }, 1, 10, TimeUnit.MILLISECONDS);
    }

}
