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

public class Reina extends PiezaAjedrez  {

	/**
	 * Crea una torre pertenenciente a jugador en la posición (x,y) del tablero
	 * @param jugador El jugador al que pertenece la pieza
	 * @param fila Fila que ocupa 
	 * @param columna Columna que ocupa
	 */
	public Reina(Jugador jugador, int fila, char columna) {
		super(jugador, TipoPiezaAjedrez.REINA, fila, columna);


	}


	@Override
	public IList<Casilla> movimientosValidos() {
		IList<Casilla> resultado = new ArrayList<>();
		/* La reina se mueve en cruz y en diagonal 
		 */
		//EN CRUZ
		
		//Vertical descendente según el modelo interno (matriz)
		casillasVisitables (resultado, 1, 0);
		//Vertical ascendente según el modelo interno (matriz)
		casillasVisitables (resultado, -1, 0);

		//Horiznotal izquierda según el modelo interno (matriz)
		casillasVisitables (resultado, 0, -1);
		//Horizontal derecha según el modelo interno (matriz)
		casillasVisitables (resultado, 0, 1);
		
		//DIAGONAL
		
		//Diagonal superior derecho (matriz)
		casillasVisitables (resultado, -1, 1);
		//Diagonal superior izquierdo (matriz)
		casillasVisitables (resultado, -1, -1);

		//Diagonal inferior derecho (matriz)
		casillasVisitables (resultado, 1, 1);
		//Diagonal inferior izquierdo (matriz)
		casillasVisitables (resultado, 1, -1);

		return resultado;
	}

}

