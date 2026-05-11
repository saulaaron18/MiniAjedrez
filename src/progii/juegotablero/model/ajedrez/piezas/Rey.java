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

public class Rey extends PiezaAjedrez {

	/**
	 * Crea una torre pertenenciente a jugador en la posición (x,y) del tablero
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
		 * EL rey se mueve una posición en todas las direcciones
		 */
		int fila = getFila();
		int columna = getColumna();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {

				if (i != 0 || j != 0) {
					casillaVisitable(resultado, fila + i, columna + j);
				}
			}
		}

		return resultado;
	}

}
