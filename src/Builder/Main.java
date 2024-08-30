package Builder;


//CLASE HECHA SOLO PARA PROBAR QUE FUNCIONA EL PATRON
//BORRAR
public  class Main {
    public static void main(String[] args){
        Director director = new Director();

        MapaBuilder builder = new MapaBuilder();
        director.nivelDificil(builder);

        Mapa mapa = builder.build();
        mapa.mostrarMapa();
    }
}
