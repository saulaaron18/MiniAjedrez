//sa.santillan@alumnos.upm.es
package progii.juegotablero.model.ajedrez.piezas;

import list.ArrayList;
import list.IList;
import progii.juegotablero.model.Casilla;
import progii.juegotablero.model.Jugador;
import progii.juegotablero.model.ajedrez.PiezaAjedrez;
import progii.juegotablero.model.ajedrez.TipoPiezaAjedrez;

/**
 * Clase que representa a la Torre
 * 
 * @author agonzalez
 *
 */

public class Caballo extends PiezaAjedrez {

	/**
	 * Crea una torre pertenenciente a jugador en la posición (x,y) del tablero
	 * 
	 * @param jugador El jugador al que pertenece la pieza
	 * @param fila    Fila que ocupa
	 * @param columna Columna que ocupa
	 */
	public Caballo(Jugador jugador, int fila, char columna) {
		super(jugador, TipoPiezaAjedrez.CABALLO, fila, columna);

	}

	@Override
	public IList<Casilla> movimientosValidos() {
		IList<Casilla> resultado = new ArrayList<>();
		/*
		 * El caballo se mueve en L
		 */
		int fila = getFila();
		int columna = getColumna();

		// Casillas arriba izquierda
		casillaVisitable(resultado, fila - 1, columna - 2);
		casillaVisitable(resultado, getFila() - 2, getColumna() - 1);

		// Casillas arriba derecha
		casillaVisitable(resultado, getFila() - 1, getColumna() + 2);
		casillaVisitable(resultado, getFila() - 2, getColumna() + 1);

		// Casillas abajo izquierda
		casillaVisitable(resultado, getFila() + 1, getColumna() - 2);
		casillaVisitable(resultado, getFila() + 2, getColumna() - 1);

		// Casillas abajo derecha
		casillaVisitable(resultado, getFila() + 1, getColumna() + 2);
		casillaVisitable(resultado, getFila() + 2, getColumna() + 1);

		return resultado;
	}

}
