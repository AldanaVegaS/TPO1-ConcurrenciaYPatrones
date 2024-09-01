package entidades;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.OverlayLayout;

import Builder.Mapa;
import Builder.MapaBuilder;
import Builder.Nivel;
import Builder.Configuracion;

public class Juego extends JPanel {
	private int[][] matrizMapa;
	private int numeroFilas; // asignar con la fila del mapa
	private int numeroColumnas; // asignar con la columna de la columna

	private int x, y;
	private Nivel nivelDificultad;
	private boolean tesoroEncontrado;
	private boolean gameOver;
	private boolean comenzo;

	// atributos menu
	private Personaje personaje;
	private CardLayout cardLayout;
	private JPanel panelMenu;
	private JProgressBar barraVida;

	public Juego() {

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case 37: // izquierda
						if ((x - 1 >= 0) && verificarPosicion(x - 1, y)) {
							x = x - 1;
						}
						break;
					case 39: // derecha
						if ((x + 1 <= numeroColumnas) && verificarPosicion(x + 1, y)) {
							
							x = x + 1;
						}
						break;
					case 40: // abajo
						if ((y + 1 <= numeroFilas) && verificarPosicion(x, y + 1)) {
							System.out.println(verificarPosicion(x, y + 1));
							y = y + 1;
						}
						break;
					case 38: // arriba
						if ((y - 1 >= 0) && verificarPosicion(x, y - 1)) {
							y = y - 1;
						}
						break;
					default:
						break;
				}
				for (int i = 0; i < numeroFilas; i++) {
					for (int j = 0; j < numeroColumnas; j++) {
						System.out.print(matrizMapa[i][j] + " ");
					}
					System.out.println();
				}
				personaje.cambiarPosición(x, y);
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		// Hacer que el panel pueda recibir el foco
		setFocusable(true);

		this.comenzo = false;
		panelMenu = new JPanel();
		cardLayout = new CardLayout();
		barraVida = new JProgressBar(0, 10);
		this.setLayout(cardLayout);
		this.inicializarMenu();
	}

	private void inicializarMenu() {

		JButton bFacil, bMedio, bDificil;

		panelMenu.setLayout(new GridLayout(3, 1));
		panelMenu.setPreferredSize(new Dimension(300, 150));
		bFacil = new JButton("Facil");
		//bFacil.addActionListener(this);
		bMedio = new JButton("Medio");
		//bMedio.addActionListener(this);
		bDificil = new JButton("Dificil");
		bFacil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nivelDificultad = Nivel.FACIL;
				iniciarJuego();
			}
		});
		bMedio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nivelDificultad = Nivel.MEDIO;
				iniciarJuego();
			}
		});
		bDificil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nivelDificultad = Nivel.DIFICIL;
				iniciarJuego();
			}
		});
		panelMenu.add(bFacil, BorderLayout.NORTH);
		panelMenu.add(bMedio, BorderLayout.CENTER);
		panelMenu.add(bDificil, BorderLayout.SOUTH);
		this.add(panelMenu, "Menu");
	}

	public void iniciarJuego() {

		Configuracion config = new Configuracion();
		MapaBuilder mapaBuilder = new MapaBuilder();
		Mapa mapa = config.configurarJuego(mapaBuilder, nivelDificultad);

		personaje = new Personaje();
		matrizMapa = mapa.getMapa();
		mapa.mostrarMapa();
		numeroFilas = mapa.getFilas();
		numeroColumnas = mapa.getColumnas();
		x = 0;
		y = 0;
		gameOver = false;
		tesoroEncontrado = false;
		barraVida = new JProgressBar(0, 10);
		barraVida.setValue(10); // Vida inicial
		barraVida.setStringPainted(true); // Mostrar el valor numérico

		JPanel panelJuego = new JPanel();
		JPanel cont = new JPanel();

		cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS)); // para que los componentes esten alineados verticalmente
		cont.add(mapa);
		cont.add(barraVida);

		panelJuego.setLayout(new OverlayLayout(panelJuego));
		panelJuego.add(personaje);
		panelJuego.add(cont);

		this.add(panelJuego, "Juego");

		cardLayout.show(this, "Juego");
		comenzo = true;

	}

	public boolean comenzo(){
		return comenzo;
	}

	public boolean termino(){
		return gameOver;
	}
	public boolean gano(){
		return tesoroEncontrado;
	}

	private boolean verificarPosicion(int x, int y) {
		// 1 = Villano -> no pueden ocupar la misma posicion. Le resta -1 de vida
		// 2 = Zona contaminada
		// 3 = Pozo
		// 4 = Tesoro
		// 5 = Obstaculo, no deja pasar al personaje por esa celda, no resta puntos de
		// vida
		boolean permitido = true;
		int aux = matrizMapa[x][y];
		switch (aux) {
			case 0:
				if (personaje.enZonaContaminada()) {
					personaje.salirZonaContaminada();
				}
				break;
			case 1:
				personaje.restarVida();
				barraVida.setValue(personaje.getVida());
				if (!personaje.tieneVida()) {
					gameOver = true;
				}
				permitido = false;

				break;
			case 2:
				if (!personaje.enZonaContaminada()) {
					personaje.entrarZonaContaminada();
				}
				break;
			case 3:
				personaje.caerEnPozo();
				barraVida.setValue(personaje.getVida());
				if (!personaje.tieneVida()) {
					gameOver = true;
				}
				break;
			case 4:
				tesoroEncontrado = true;
				gameOver = true;
				break;
			case 5:
			
				permitido = false;
				break;

			default:
				break;
		}
		return permitido;
	}
}
