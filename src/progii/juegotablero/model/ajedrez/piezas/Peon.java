package progii.juegotablero.model.ajedrez.piezas;

import list.ArrayList;
import list.IList;
import progii.juegotablero.model.Casilla;
import progii.juegotablero.model.ControlJugadores;
import progii.juegotablero.model.Jugador;
import progii.juegotablero.model.ajedrez.ControlJugadoresAjedrez;
import progii.juegotablero.model.ajedrez.PiezaAjedrez;
import progii.juegotablero.model.ajedrez.TipoPiezaAjedrez;

/**
 * Clase que representa a la Torre
 * @author agonzalez
 *
 */

public class Peon extends PiezaAjedrez  {

	/**
	 * Crea una torre pertenenciente a jugador en la posición (x,y) del tablero
	 * @param jugador El jugador al que pertenece la pieza
	 * @param fila Fila que ocupa 
	 * @param columna Columna que ocupa
	 */
	public Peon(Jugador jugador, int fila, char columna) {
		super(jugador, TipoPiezaAjedrez.PEON, fila, columna);


	}


	@Override
	public IList<Casilla> movimientosValidos() {
		IList<Casilla> resultado = new ArrayList<>();
		//Comprobar que es null el de delante y añadirlo
		int fila = getFila();
		int columna = getColumna();

		if(getJugador().getId() == ControlJugadoresAjedrez.BLANCO ) {
			int arriba = fila-1;
			
			PiezaAjedrez[] piezasDiagonales = new PiezaAjedrez[2];
			piezasDiagonales[0] = queHay(arriba, columna-1);
			piezasDiagonales[1] = queHay(arriba, columna+1);
			
			casillaVisitable(resultado, arriba, columna);
			
			if(fila==6){
				casillaVisitable(resultado, arriba-1, columna);
			}
			
			for(int i=0;i<piezasDiagonales.length;i++) {
				if(piezasDiagonales[i]!=null) {
					casillaVisitable(resultado, arriba, columna-1+i*2);
				}
			}
		}
		else{
			int abajo = fila+1;
			
			PiezaAjedrez[] piezasDiagonales = new PiezaAjedrez[2];
			piezasDiagonales[0] = queHay(abajo, columna-1);
			piezasDiagonales[1] = queHay(abajo, columna+1);
			
			casillaVisitable(resultado, abajo, columna);
			
			if(fila==1) {
				casillaVisitable(resultado, abajo+1, columna);
			}
			
			for(int i=0;i<piezasDiagonales.length;i++) {
				if(piezasDiagonales[i]!=null) {
					casillaVisitable(resultado, abajo, columna-1+i*2);
				}
			}
		}

		return resultado;
	}

}

