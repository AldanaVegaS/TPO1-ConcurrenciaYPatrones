package Builder;

import entidades.Nivel;
//CLASE HECHA SOLO PARA PROBAR QUE FUNCIONA EL PATRON
//BORRAR
public  class Main {
    public static void main(String[] args){
        Configuracion config = new Configuracion();

        MapaBuilder builder = new MapaBuilder();
        
        Mapa mapa =config.configurarJuego(builder,Nivel.FACIL);
        mapa.mostrarMapa();
    }
}
