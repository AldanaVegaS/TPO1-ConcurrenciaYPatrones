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
                if(game.termino()){
                    if(game.gano()){

                        JOptionPane.showMessageDialog(null, "Felicidades, ganaste la partida!", "GameOver", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Perdiste :(", "GameOver", JOptionPane.INFORMATION_MESSAGE);
    
                    }
                    executor.shutdown();
                    
                }
            }
        }, 1, 10, TimeUnit.MILLISECONDS);
    }

}
