package Builder;

import java.awt.Color;
import java.awt.Graphics;

public class Mapa {
    private final int[][] mapa;
    private final int filas;
    private final int columnas;
    private final int anchoBloque = 40;
    private final int altoBloque = 40;
    
    Mapa(int[][] mapa, int filas, int columnas){
        this.filas=filas;
        this.columnas=columnas;
        this.mapa=mapa;
    }

	public int[][] getMapa() {
		return mapa;
	}

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

    public void mostrarMapa(){
        for(int i=0;i<filas;i++){
            for(int j=0;j<columnas;j++){
                System.out.print(mapa[i][j]+" ");
            }
            System.out.println();
        }
	}
}
