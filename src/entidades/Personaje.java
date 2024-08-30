package entidades;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Builder.Mapa;

public class Personaje {
    private int[][] plano;
    private Random random = new Random();
    private int x = 0;
    private int y = 0;
    private final int ancho = 40;
    private final int alto = 40;
    private final int movimiento = 40;
    private int vida = 10;
    private boolean zonaContaminada = false;
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public Personaje(Mapa mapa) {
        plano = mapa.getMapa();
        x = 0;
        y = 0;
        System.out.println("Posicion personaje: " + x + "," + y);
    }

    public void paint(Graphics grafico) {
        grafico.setColor(Color.pink);
        grafico.fillOval(x, y, ancho, alto);
    }

    public void teclaPrecionada(KeyEvent evento) {
        switch (evento.getKeyCode()) {
            case 37: // izquierda
                if (x - movimiento >= 0) {
                    x = x - movimiento;
                }
                break;
            case 39: // derecha
                if (x + movimiento <= 40) {
                    x = x + movimiento;
                }
                break;
            case 40: // abajo
                if (y + movimiento <= 40) {
                    y = y + movimiento;
                }
                break;
            case 38: // arriba
                if (y - movimiento >= 0) {
                    y = y - movimiento;
                }
                break;
            default:
                break;
        }
    }

    public boolean enZonaContaminada() {
        return zonaContaminada;
    }

    public void entrarZonaContaminada() {
        executor.scheduleAtFixedRate(() -> {
            zonaContaminada = true;
            vida--;
            System.out.println("Vida restante:" + vida);
        }, 1, 1, TimeUnit.SECONDS);
    }

    public void salirZonaContaminada() {
        System.out.println("Salir de la zona contaminada");
        zonaContaminada = false;
        executor.shutdown();
        executor = null;

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

    public boolean tieneVida() {
        return vida > 0;
    }

    public Dimension getPosicion() {
        return new Dimension(x / 40, y / 40);
    }
}
