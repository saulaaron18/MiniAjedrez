//sa.santillan@alumnos.upm.es
package progii.juegotablero.model.ajedrez.piezas;

import list.ArrayList;
import list.IList;
import progii.juegotablero.model.Casilla;
import progii.juegotablero.model.Jugador;
import progii.juegotablero.model.ajedrez.ControlJugadoresAjedrez;
import progii.juegotablero.model.ajedrez.PiezaAjedrez;
import progii.juegotablero.model.ajedrez.TipoPiezaAjedrez;

/**
 * Clase que representa el Peon
 * 
 * @author agonzalez
 *
 */

public class Peon extends PiezaAjedrez {

	/**
	 * Crea un Peón pertenenciente a jugador en la posición (x,y) del tablero
	 * 
	 * @param jugador El jugador al que pertenece la pieza
	 * @param fila    Fila que ocupa
	 * @param columna Columna que ocupa
	 */
	public Peon(Jugador jugador, int fila, char columna) {
		super(jugador, TipoPiezaAjedrez.PEON, fila, columna);

	}

	@Override
	public IList<Casilla> movimientosValidos() {
		IList<Casilla> resultado = new ArrayList<>();

		int fila = getFila();
		int columna = getColumna();
		int direccion = (getJugador().getId() == ControlJugadoresAjedrez.BLANCO) ? -1 : 1; // Comprobamos el movimiento
		int paso = fila + direccion;

		// Se pude mover adelante (según su color)
		if (queHay(paso, columna) == null) {
			casillaVisitable(resultado, paso, columna);

			// Se puede mover en la segunda casilla
			int filaInicial = (direccion == -1) ? 6 : 1;
			int doblePaso = fila + direccion * 2;
			if (queHay(doblePaso, columna) == null && fila == filaInicial) {
				casillaVisitable(resultado, doblePaso, columna);
			}
		}

		PiezaAjedrez piezaEsquinaIzquierda = queHay(paso, columna - 1);
		PiezaAjedrez piezaEsquinaDerecha = queHay(paso, columna + 1);

		// Se pude comer la pieza de la izquierda (según la matriz)
		if (piezaEsquinaIzquierda != null && piezaEsquinaIzquierda.getJugador().getId() != getJugador().getId()) {
			casillaVisitable(resultado, paso, columna - 1);
		}

		// Se puede comer la pieza de la derceha (según la matriz)
		if (piezaEsquinaDerecha != null && piezaEsquinaDerecha.getJugador().getId() != getJugador().getId()) {
			casillaVisitable(resultado, paso, columna + 1);
		}

		return resultado;
	}

}
