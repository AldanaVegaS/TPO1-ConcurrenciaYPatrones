package entidades;

import Builder.Builder;
import Builder.Mapa;

public class Configuracion {

    public Configuracion() {
    }

    public void nivelFacil(Builder builder) {
        builder.setTamaño(5, 5);
        builder.setTesoro(1);
        builder.setObstaculos(5);
    }

    public void nivelMedio(Builder builder) {
        builder.setTamaño(10, 10);
        builder.setTesoro(1);
        builder.setObstaculos(5);
        builder.setVillano();
        builder.setPozos(2);
    }

    public void nivelDificil(Builder builder) {
        builder.setTamaño(20, 20);
        builder.setTesoro(1);
        builder.setObstaculos(5);
        builder.setVillano();
        builder.setPozos(2);
        builder.setZonaContaminada(5);
    }

    public static Mapa configurarjuego(Builder builder, Nivel dificultad) {
        switch (dificultad) {
            case FACIL:
                
                break;
            case MEDIO:

                break;
            case DIFICIL:

                break;

            default:
                break;
        }
        return mapa;
    }
}
