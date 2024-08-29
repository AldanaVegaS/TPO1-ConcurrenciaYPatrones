package backend.entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import backend.Builder.Mapa;

public class Personaje {
    private int[][] plano;
    private Random random = new Random();
    private int x = 0;
    private int y = 0;
    private final int ancho = 40;
    private final int alto = 40;
    private final int movimiento = 40;
    private int vida = 10; 
    private boolean zonaContaminada=false;
    private boolean fin=false;
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public Personaje(Mapa mapa) {
        plano = mapa.getMapa();
        x = 0;
        y = 0;
        /*
         * do { // Quise agregar esto para que no quedara el persoaje arriba de una
         * celda pero
         * // no funciona por que los random no siguen el x e y de las celdas 40x40 creo
         * :)
         * x = random.nextInt(mapa.getFilas());
         * y = random.nextInt(mapa.getColumnas());
         * } while (plano[x][y] != 0);
         */
        System.out.println("Posicion personaje: "+x+","+y);
    }

    public void paint(Graphics grafico) {
        grafico.setColor(Color.pink);
        grafico.fillOval(x, y, ancho, alto);
    }

    public boolean fin(){
        return fin;
    }

    public void teclaPrecionada(KeyEvent evento) {
        switch (evento.getKeyCode()) {
            case 37: // izquierda
                if(verificarCelda(y/40,(x-movimiento)/40)){
                    x = x - movimiento;
                    if (plano[y/40][(x-movimiento)/40]!=0) {
                        accionSobrePersonaje(y/40,(x-movimiento)/40);
                    }
                }
                System.out.println("Posicion personaje mueve a Izq: "+x+","+y);
                break;
            case 39: // derecha
                if(verificarCelda(y/40, (x+movimiento)/40)){
                    x = x + movimiento;
                    if (plano[y/40][(x+movimiento)/40]!=0) {
                        accionSobrePersonaje(y/40,(x-movimiento)/40);
                    }
                }
                System.out.println("Posicion personaje mueve a derecha: "+x+","+y);
                verificarCelda(x/40+1, y/40);
                break;
            case 40: // abajo
                if(verificarCelda((y+movimiento)/40, x/40)){
                y = y + movimiento;
                if (plano[(y+movimiento)/40][x/40]!=0) {
                    accionSobrePersonaje((y+movimiento)/40,x/40);
                }
                }
                System.out.println("Posicion personaje mueve a abajo: "+x+","+y);
                verificarCelda(x/40, y/40-1);
                break;
            case 38: // arriba
                if(verificarCelda((y-movimiento)/40, x/40)){
                    y = y - movimiento;
                    if (plano[(y-movimiento)/40][x/40]!=0) {
                        accionSobrePersonaje((y+movimiento)/40,x/40);
                    }
                }
                System.out.println("Posicion personaje mueve arriba: "+x+","+y);
                verificarCelda(x/40, y/40+1);
                break;

            default:
                break;
        }
    }

    private boolean verificarCelda(int x, int y){
        if((x<plano.length && x>=0) && (y<plano[0].length && y>=0)){
            System.out.println("Celda a moverse: "+x+","+y+" contiene:"+plano[x][y]);
            return plano[x][y]!=5;
        }
        return false;
    }

    private void accionSobrePersonaje(int x, int y){
        switch (plano[x][y]) {
            case 1: //Hace lo mismo que zona contaminada
            case 2: entrarZonaContaminada();
            case 5: fin=true; //Encuentra el tesoro
            case 3: //Cae al pozo se reinicia el juego
            default:

            break;
        }
    }

        private void entrarZonaContaminada(){    
            executor.scheduleAtFixedRate(()->{
                zonaContaminada=true;
                vida--;
                System.out.println("Vida restante:"+vida);
            },1,1,TimeUnit.SECONDS);
        }

        private void salirZonaContaminada(){
            System.out.println("Salir de la zona contaminada");
                zonaContaminada=false;
                executor.shutdown();
                executor=null;
            
        }

}
