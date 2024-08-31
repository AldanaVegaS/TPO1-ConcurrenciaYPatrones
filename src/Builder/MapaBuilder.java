package Builder;

import java.util.Random;
// 1 = Villano
// 2 = Zona contaminada
// 3 = Pozo
// 4 = Tesoro
// 5 = Obstaculo, no deja pasar al personaje por esa celda, no resta puntos de vida
// 6 = Personaje para indicar donde comienza

public class MapaBuilder implements Builder{
    private int[][] mapa;
    private int filas;
    private int columnas;
    private final Random random = new Random();

    @Override
    public void setTamaño(int filas, int columnas){
        this.filas=filas;
        this.columnas=columnas;
        this.mapa=new int[filas][columnas];
        mapa[0][0]=6;
    }

    @Override
    public void setVillano(){//Podria agregarse un atributo de poder para restarle al pers
        ubicarElemento(1);
    }

    @Override
    public void setZonaContaminada(int cant){
        for(int i = 0; i<cant ; i++){
            ubicarElemento(2);
        }
    }

    @Override
    public void setPozos(int cant){
        for(int i = 0; i<cant ; i++){
            ubicarElemento(3);
        }
    }

    @Override
    public void setTesoro(int cant){
        for(int i = 0; i<cant ; i++){
            ubicarElemento(4);
        }
    }

    @Override
    public void setObstaculos(int cant){
        for(int i = 0; i<cant ; i++){
            ubicarElemento(5);
        }
    }

    public Mapa build(){
        return (new Mapa(mapa,filas,columnas));
    }

    private void ubicarElemento(int elto){
        int f, c;
        //Busca una posicion que no este ocupada por otro elemento y que no sea la inicial del personaje
        do{
            f = random.nextInt(filas);
            c = random.nextInt(columnas);
        }while(mapa[f][c]!=0 && f!=0 && c!=0);
        mapa[f][c]=elto;
    }
    
}
