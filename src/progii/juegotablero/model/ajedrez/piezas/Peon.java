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
 * Clase que representa a la Torre
 * 
 * @author agonzalez
 *
 */

public class Peon extends PiezaAjedrez {

	/**
	 * Crea una torre pertenenciente a jugador en la posición (x,y) del tablero
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
		int desplazamiento = fila + direccion;

		// Se pude mover adelante (según su color)
		if (queHay(desplazamiento, columna) == null) {
			casillaVisitable(resultado, desplazamiento, columna);

			// Se puede mover en la segunda casilla
			if (queHay(fila + direccion * 2, columna) == null
					&& ((direccion == -1 && fila == 6) || (direccion == 1 && fila == 1))) {
				casillaVisitable(resultado, fila + direccion * 2, columna);
			}
		}

		PiezaAjedrez piezaEsquinaIzquierda = queHay(desplazamiento, columna - 1);
		PiezaAjedrez piezaEsquinaDerecha = queHay(desplazamiento, columna + 1);

		// Se pude comer la pieza de la izquierda (según la matriz)
		if (piezaEsquinaIzquierda != null && piezaEsquinaIzquierda.getJugador().getId() != getJugador().getId()) {
			casillaVisitable(resultado, desplazamiento, columna - 1);
		}

		// Se puede comer la pieza de la derceha (según la matriz)
		if (piezaEsquinaDerecha != null && piezaEsquinaDerecha.getJugador().getId() != getJugador().getId()) {
			casillaVisitable(resultado, desplazamiento, columna + 1);
		}

		return resultado;
	}

}
