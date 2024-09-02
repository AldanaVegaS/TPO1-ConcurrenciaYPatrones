package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Personaje extends JPanel {
    private int x = 0;
    private int y = 0;
    private final int ANCHO = 30;
    private final int ALTO = 30;
    private int vida = 10;
    private boolean zonaContaminada = false;
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public Personaje() {
        x = 0;
        y = 0;
    }

    public void cambiarPosición(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics grafico) {
        grafico.setColor(Color.pink);
        grafico.fillOval(x * 30, y * 30, ANCHO, ALTO);
    }

    public boolean enZonaContaminada() {
        return zonaContaminada;
    }

    public void entrarZonaContaminada(JProgressBar barraVida) {
        executor.scheduleAtFixedRate(() -> {
            zonaContaminada = true;
            restarVida();
            barraVida.setValue(vida);
            System.out.println("Vida restante:" + vida);
        }, 1, 1, TimeUnit.SECONDS);
    }

    public void salirZonaContaminada() {
        System.out.println("Salir de la zona contaminada");
        zonaContaminada = false;
        executor.shutdown();
        executor = Executors.newSingleThreadScheduledExecutor();

    }

    public void restarVida() {
        vida -= 1;
    }

    public void caerEnPozo() {
        if (vida > 3) {
            vida -= 3;
            x = 0;
            y = 0;
        } else {
            vida = 0;
        }
    }

    public int getVida() {
        return vida;
    }

    public boolean tieneVida() {
        return vida > 0;
    }
}
