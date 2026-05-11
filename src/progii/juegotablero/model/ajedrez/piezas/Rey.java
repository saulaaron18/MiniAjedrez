//sa.santillan@alumnos.upm.es
package progii.juegotablero.model.ajedrez.piezas;

import list.ArrayList;
import list.IList;
import progii.juegotablero.model.Casilla;
import progii.juegotablero.model.Jugador;
import progii.juegotablero.model.ajedrez.PiezaAjedrez;
import progii.juegotablero.model.ajedrez.TipoPiezaAjedrez;

/**
 * Clase que representa el Rey
 * 
 * @author Saúl
 * 
 *
 */

public class Rey extends PiezaAjedrez {

	/**
	 * Crea un Rey pertenenciente a jugador en la posición (x,y) del tablero
	 * 
	 * @param jugador El jugador al que pertenece la pieza
	 * @param fila    Fila que ocupa
	 * @param columna Columna que ocupa
	 */
	public Rey(Jugador jugador, int fila, char columna) {
		super(jugador, TipoPiezaAjedrez.REY, fila, columna);

	}

	@Override
	public IList<Casilla> movimientosValidos() {
		IList<Casilla> resultado = new ArrayList<>();
		/*
		 * EL rey se mueve una posición en una de las 8 direcciones
		 */
		int fila = getFila();
		int columna = getColumna();

		// Arriba
		casillaVisitable(resultado, fila - 1, columna);

		// Abajo
		casillaVisitable(resultado, fila + 1, columna);

		// Izquierda
		casillaVisitable(resultado, fila, columna - 1);

		// Derecha
		casillaVisitable(resultado, fila, columna + 1);

		// Diagonal superior izquierda
		casillaVisitable(resultado, fila - 1, columna - 1);

		// Diagonal superior derecha
		casillaVisitable(resultado, fila - 1, columna + 1);

		// Diagonal inferior izquierda
		casillaVisitable(resultado, fila + 1, columna - 1);

		// Diagonal inferior derecha
		casillaVisitable(resultado, fila + 1, columna + 1);

		return resultado;
	}

}
