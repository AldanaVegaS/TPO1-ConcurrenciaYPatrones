package Builder;

public class Configuracion {

    public Configuracion() {
    }

    private void nivelFacil(Builder builder) {
        builder.setTamaño(10, 10);
        builder.setTesoro(1);
        builder.setObstaculos(5);
    }

    private void nivelMedio(Builder builder) {
        builder.setTamaño(15, 15);
        builder.setTesoro(1);
        builder.setObstaculos(5);
        builder.setVillano();
        builder.setPozos(2);
    }

    private void nivelDificil(Builder builder) {
        builder.setTamaño(20, 20);
        builder.setTesoro(1);
        builder.setObstaculos(5);
        builder.setVillano();
        builder.setPozos(2);
        builder.setZonaContaminada(5);
    }

    public Mapa configurarjuego(MapaBuilder builder, Nivel dificultad) {

        switch (dificultad) {
            case FACIL:
                this.nivelFacil(builder);
                break;
            case MEDIO:
                this.nivelMedio(builder);
                break;
            case DIFICIL:
                this.nivelDificil(builder);
                break;
            default:
                break;
        }
        return builder.build();
    }
}