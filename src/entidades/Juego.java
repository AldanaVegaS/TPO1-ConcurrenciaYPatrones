package entidades;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Builder.Mapa;

import java.awt.Graphics;

public class Juego {
	private int[][] mapa;
	private int fila = 0;
	private int columna = 0;
	private int numeroFilas; // asignar con la fila del mapa
	private int numeroColumnas; // asignar con la columna de la columna
	private final int anchoBloque = 40;
	private final int altoBloque = 40;

	public Juego(Mapa mapa) {
		this.mapa = mapa.getMapa();
		this.numeroFilas = mapa.getFilas();
		this.numeroColumnas = mapa.getColumnas();
	}

	public void paint(Graphics grafico) {
		for (fila = 0; fila < numeroFilas; fila++) {
			for (columna = 0; columna < numeroColumnas; columna++) {
				switch (mapa[fila][columna]) {
					case 1: // 1 = Villano
						grafico.setColor(Color.red);
						grafico.fillRect(columna * 40, fila * 40, anchoBloque, altoBloque);
						break;
					case 2: // 2 = Zona contaminada
						grafico.setColor(Color.green);
						grafico.fillRect(columna * 40, fila * 40, anchoBloque, altoBloque);
						break;
					case 3:// 3 = Pozo
						grafico.setColor(Color.gray);
						grafico.fillRect(columna * 40, fila * 40, anchoBloque, altoBloque);
						break;
					case 4:// 4 = Tesoro
						grafico.setColor(Color.yellow);
						grafico.fillRect(columna * 40, fila * 40, anchoBloque, altoBloque);
						break;
					case 5: // 5 = Obstaculo, no deja pasar al personaje por esa celda, no resta puntos de
							// vida
						grafico.setColor(Color.black);
						grafico.fillRect(columna * 40, fila * 40, anchoBloque, altoBloque);
						break;
					default: grafico.setColor(Color.white);
					grafico.fillRect(columna*40, fila*40, anchoBloque, altoBloque);
						break;
				}
			}
		}
	}

	/*
	 * private ScheduledExecutorService executor;
	 * private Mapa mapa;
	 * private PacMan pacman;
	 * private Fantasma[] fantasma;
	 * private JPanel panelMenu;
	 * private JButton bFacil, bMedio, bDificil;
	 * private NivelDificultad nivel;
	 * 
	 * public Juego() {
	 * this.setTitle("PacMan");
	 * this.setSize(400, 400);
	 * this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * this.setLocationRelativeTo(null);
	 * this.setResizable(false);
	 * executor = Executors.newSingleThreadScheduledExecutor();
	 * this.inicializarMenu();
	 * this.add(panelMenu);
	 * }
	 * 
	 * private void inicializarMenu() {
	 * panelMenu = new JPanel();
	 * panelMenu.setLayout(new GridLayout(3, 1));
	 * panelMenu.setPreferredSize(new Dimension (100,200));
	 * bFacil = new JButton("Fácil");
	 * bMedio = new JButton("Medio");
	 * bDificil = new JButton("Dificil");
	 * 
	 * bFacil.addActionListener(new ActionListener() {
	 * 
	 * @Override
	 * public void actionPerformed(ActionEvent e) {
	 * nivel = NivelDificultad.FACIL;
	 * iniciarJuego();
	 * }
	 * });
	 * 
	 * bMedio.addActionListener(new ActionListener() {
	 * 
	 * @Override
	 * public void actionPerformed(ActionEvent e) {
	 * nivel = NivelDificultad.MEDIO;
	 * iniciarJuego();
	 * }
	 * });
	 * 
	 * bDificil.addActionListener(new ActionListener() {
	 * 
	 * @Override
	 * public void actionPerformed(ActionEvent e) {
	 * nivel = NivelDificultad.DIFICIL;
	 * iniciarJuego();
	 * }
	 * });
	 * 
	 * panelMenu.add(bFacil);
	 * panelMenu.add(bMedio);
	 * panelMenu.add(bDificil);
	 * }
	 * 
	 * private void iniciarJuego() {
	 * executor.scheduleAtFixedRate(this, (long) 0, (long) 16,
	 * TimeUnit.MILLISECONDS);
	 * }
	 * 
	 * private void actualizarJuego() {
	 * 
	 * }
	 * 
	 * @Override
	 * public void run() {
	 * 
	 * }
	 */
}
