package Builder;

import java.util.Random;

public class Mapa {
    private final int[][] mapa;
    private final int filas;
    private final int columnas;
    
    public Mapa(int[][] mapa, int filas, int columnas){
        this.filas=filas;
        this.columnas=columnas;
        this.mapa=mapa;
    }

    public int[][] getMapa(){
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

    public class MapaBuilder implements Builder{

		// 1 = Villano
		// 2 = Zona contaminada
		// 3 = Pozo
		// 4 = Tesoro
		// 5 = Obstaculo, no deja pasar al personaje por esa celda, no resta puntos de
		// vida

		private int[][] mapa;
		private int filas;
		private int columnas;
		private Random random = new Random();
        
		public void setTamaño(int filas, int columnas) {
			this.filas = filas;
			this.columnas = columnas;
			this.mapa = new int[filas][columnas];
		}

		@Override
		public void setVillano() {// Podria agregarse un atributo de poder para restarle al personaje
			ubicarElemento(1);
		}

		@Override
		public void setZonaContaminada(int cant) {
			for (int i = 0; i < cant; i++) {
				ubicarElemento(2);
			}
		}

		@Override
		public void setPozos(int cant) {
			for (int i = 0; i < cant; i++) {
				ubicarElemento(3);
			}
		}

		@Override
		public void setTesoro(int cant) {
			for (int i = 0; i < cant; i++) {
				ubicarElemento(4);
			}
		}

		public void setObstaculos(int cant) {
			for (int i = 0; i < cant; i++) {
				ubicarElemento(5);
			}
		}

		public Mapa getResultado() {
	        return new Mapa(mapa, filas, columnas);
	    }

		private void ubicarElemento(int elto) {
			int f, c;
			// Busca una posicion que no este ocupada por otro elemento y que no sea la
			// inicial del personaje
			int i = 0;

			do {
				f = random.nextInt(filas);
				c = random.nextInt(columnas);
			} while (mapa[f][c] != 0 && f != 0 && c != 0);
			mapa[f][c] = elto;
		}

	}
}
