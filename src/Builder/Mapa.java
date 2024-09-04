package Builder;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class Mapa extends JPanel{
	private int[][] mapa;
	private int filas;
	private int columnas;

	// ###########Agregadas
	private final int ANCHO_BLOQUE = 30,ALTO_BLOQUE = 30;

	public Mapa() {

	}

	public Mapa(int[][] mapa, int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.mapa = mapa;
		this.setPreferredSize(new Dimension((columnas)* ANCHO_BLOQUE,(filas) * ALTO_BLOQUE)); //tamaño del mapa
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

	public void paintComponent(Graphics grafico){
		super.paintComponent(grafico);
		for (int fila = 0; fila < filas; fila++) {
			for (int columna = 0; columna < columnas; columna++) {
				switch (mapa[fila][columna]) {
					case 1: // 1 = Villano
						grafico.setColor(Color.red);
						grafico.fillRect(columna * 30 +1, fila * 30 +1, ANCHO_BLOQUE,ALTO_BLOQUE);
						break;
					case 2: // 2 = Zona contaminada
						grafico.setColor(Color.green);
						grafico.fillRect(columna * 30, fila * 30, ANCHO_BLOQUE,ALTO_BLOQUE);
						break;
					case 3:// 3 = Pozo
						grafico.setColor(Color.gray);
						grafico.fillRect(columna * 30, fila * 30, ANCHO_BLOQUE, ALTO_BLOQUE);
						break;
					case 4:// 4 = Tesoro
						grafico.setColor(Color.yellow);
						grafico.fillRect(columna * 30, fila * 30, ANCHO_BLOQUE, ALTO_BLOQUE);
						break;
					case 5: // 5 = Obstaculo, no deja pasar al personaje por esa celda, no resta puntos de
							// vida
						grafico.setColor(Color.black);
						grafico.fillRect(columna * 30, fila * 30, ANCHO_BLOQUE, ALTO_BLOQUE);
						break;
					default:
						grafico.setColor(Color.white);
						grafico.fillRect(columna * 30, fila * 30, ANCHO_BLOQUE, ANCHO_BLOQUE);
						break;
				}
			}
		}
	}
}
