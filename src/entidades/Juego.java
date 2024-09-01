package entidades;

import Builder.Configuracion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Builder.Mapa;
import Builder.MapaBuilder;
import Builder.Nivel;

import java.awt.Graphics;

public class Juego extends JPanel implements ActionListener {
	private int[][] mapa;
	private int fila = 0;
	private int columna = 0;
	private int numeroFilas; // asignar con la fila del mapa
	private int numeroColumnas; // asignar con la columna de la columna
	private final int anchoBloque = 40;
	private final int altoBloque = 40;
	private Personaje personaje;
	private int x, y;
	private Nivel nivelDificultad;
	private boolean tesoroEncontrado;
	private boolean gameOver;
	// atributos menu
	private JPanel panelMenu;
	private JButton bFacil, bMedio, bDificil;
	private JFrame ventana;

	public Juego(Mapa mapa) {
		this.mapa = mapa.getMapa();
		this.numeroFilas = mapa.getFilas();
		this.numeroColumnas = mapa.getColumnas();
		this.personaje = new Personaje(mapa);
		System.out.println(getName());
	}

	public Juego() {
	}

	public void inicializarMenu(JFrame vent) {
		this.ventana = vent;
		panelMenu = new JPanel();
		panelMenu.setLayout(new GridLayout(3, 1));
		panelMenu.setPreferredSize(new Dimension(100, 150));

		bFacil = new JButton("Facil");
		bFacil.addActionListener(this);
		bMedio = new JButton("Medio");
		bMedio.addActionListener(this);
		bDificil = new JButton("Dificil");
		bDificil.addActionListener(this);
		// bFacil.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// nivelDificultad = Nivel.FACIL;
		// iniciarJuego();
		// }
		// });
		// bMedio.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// nivelDificultad = Nivel.MEDIO;
		// iniciarJuego();
		// }
		// });
		// bDificil.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// nivelDificultad = Nivel.DIFICIL;
		// iniciarJuego();
		// }
		// });
		panelMenu.add(bFacil);
		panelMenu.add(bMedio);
		panelMenu.add(bDificil);
		ventana.add(panelMenu);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bFacil) {
			System.out.println("Has seleccionado el nivel Fácil");
			nivelDificultad = Nivel.FACIL;
			ventana.remove(panelMenu);
			ventana.revalidate();
			iniciarJuego();
			// Aquí puedes agregar el código para iniciar el juego en modo fácil
		} else if (e.getSource() == bMedio) {
			System.out.println("Has seleccionado el nivel Medio");
			nivelDificultad = Nivel.MEDIO;
			ventana.remove(panelMenu);
			ventana.revalidate();
			iniciarJuego();
			// Aquí puedes agregar el código para iniciar el juego en modo medio
		} else if (e.getSource() == bDificil) {
			System.out.println("Has seleccionado el nivel Difícil");
			nivelDificultad = Nivel.DIFICIL;
			ventana.remove(panelMenu);
			ventana.revalidate();
			iniciarJuego();
			// Aquí puedes agregar el código para iniciar el juego en modo difícil
		}
	}

	public void iniciarJuego() {

		// ##########falta esta parte y lo del Schedule########
		System.out.println("Inicia juego, muestra el mapa");
		Configuracion config = new Configuracion();
		MapaBuilder mapaBuilder = new MapaBuilder();
		Mapa mapa = config.configurarJuego(mapaBuilder, nivelDificultad);

		ventana.setSize(mapa.getFilas() * 45, mapa.getColumnas() * 45);
		// setSize(600,600);
		this.mapa = mapa.getMapa();
		Juego game = new Juego(mapa);
		ventana.add(game);
		x = 0;
		y = 0;
		gameOver = false;
		tesoroEncontrado = false;

		Runnable task = () -> {
			game.repaint();
		};

		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(task, 0, 10, TimeUnit.MILLISECONDS);

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
					default:
						grafico.setColor(Color.white);
						grafico.fillRect(columna * 40, fila * 40, anchoBloque, altoBloque);
						break;
				}
			}
		}
		grafico.setColor(Color.pink);
		grafico.fillOval(x, y, anchoBloque, altoBloque);
	}

	public void teclaPrecionada(KeyEvent evento) {

		switch (evento.getKeyCode()) {
			case 37: // izquierda
				if (x - 1 >= 0 && verificarPosicion(x - 1, y)) {
					x = x - 1;
				}
				break;
			case 39: // derecha
				if (x + 1 <= numeroColumnas && verificarPosicion(x + 1, y)) {
					x = x + 1;
				}
				break;
			case 40: // abajo
				if (y - 1 <= numeroFilas && verificarPosicion(x, y - 1)) {
					y = y - 1;
				}
				break;
			case 38: // arriba
				if (y + 1 >= 0 && verificarPosicion(x, y + 1)) {
					y = y + 1;
				}
				break;
			default:
				break;
		}

	}

	private boolean verificarPosicion(int x, int y) {
		// 1 = Villano -> no pueden ocupar la misma posicion. Le resta -1 de vida
		// 2 = Zona contaminada
		// 3 = Pozo
		// 4 = Tesoro
		// 5 = Obstaculo, no deja pasar al personaje por esa celda, no resta puntos de
		// vida
		boolean permitido = true;
		int aux = mapa[x][y];
		switch (aux) {
			case 0:
				if (personaje.enZonaContaminada()) {
					personaje.salirZonaContaminada();
				}
				break;
			case 1:
				personaje.restarVida();
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
