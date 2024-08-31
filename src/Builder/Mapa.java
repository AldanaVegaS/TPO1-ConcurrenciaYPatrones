package Builder;

import java.util.Random;

public class Mapa {
	private int[][] mapa;
	private int filas;
	private int columnas;

	public Mapa() {

	}

	public Mapa(int[][] mapa, int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.mapa = mapa;
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

	public void mostrarMapa() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print(mapa[i][j] + " ");
			}
			System.out.println();
		}
	}
}
