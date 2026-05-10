package progii.juegotablero.model.ajedrez.piezas;

import list.ArrayList;
import list.IList;
import progii.juegotablero.model.Casilla;
import progii.juegotablero.model.Jugador;
import progii.juegotablero.model.ajedrez.PiezaAjedrez;
import progii.juegotablero.model.ajedrez.TipoPiezaAjedrez;

/**
 * Clase que representa a la Torre
 * @author agonzalez
 *
 */

public class Rey extends PiezaAjedrez  {

	/**
	 * Crea una torre pertenenciente a jugador en la posición (x,y) del tablero
	 * @param jugador El jugador al que pertenece la pieza
	 * @param fila Fila que ocupa 
	 * @param columna Columna que ocupa
	 */
	public Rey(Jugador jugador, int fila, char columna) {
		super(jugador, TipoPiezaAjedrez.REY, fila, columna);


	}


	@Override
	public IList<Casilla> movimientosValidos() {
		IList<Casilla> resultado = new ArrayList<>();
		/* EL rey se mueve una posición en todas las direcciones 
		 */

		//Izquierda
		casillaVisitable(resultado, getFila(), getColumna()-1);
		//Izquierda arriba
		casillaVisitable(resultado, getFila()-1, getColumna()-1);
		//Arriba
		casillaVisitable(resultado, getFila()-1, getColumna());
		//Derecha arriba
		casillaVisitable(resultado, getFila()-1, getColumna()+1);
		//Derecha
		casillaVisitable(resultado, getFila(), getColumna()+1);
		//Derecha abajo
		casillaVisitable(resultado, getFila()+1, getColumna()+1);
		//Abajo
		casillaVisitable(resultado, getFila()+1, getColumna());
		//Izquierda abajo
		casillaVisitable(resultado, getFila()+1, getColumna()-1);

		return resultado;
	}

}

