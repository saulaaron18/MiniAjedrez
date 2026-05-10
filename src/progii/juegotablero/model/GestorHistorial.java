package progii.juegotablero.model;


import progii.juegotablero.exceptions.MovimientoException;
import stacks.exceptions.EmptyStackException;
import stacks.Stack;



/**
 * Clase que gestiona el historial de movimientos de la partida
 * 
 *
 */
public class GestorHistorial {

	/**
	 * Pila con los movimientos a deshacer
	 */
	private Stack<Movimiento> pilaDeshacer;

	/**
	 * Pila con los movimientos a rehacer
	 */
	private Stack<Movimiento> pilaRehacer;

	/**
	 * Crea e inicializa las pilas del gestor del historial
	 */
	public GestorHistorial() {
		this.pilaDeshacer = new Stack<>();
		this.pilaRehacer = new Stack<>();
	}

	/**
	 * Guarda un nuevo movimientos en el historial
	 * @param movimiento Movimiento a guardar
	 */
	public void guardarMovimiento (Movimiento movimiento) {
		if(!pilaRehacer.isEmpty()) {
			pilaRehacer.makeEmpty();
		}
		pilaDeshacer.push(movimiento);
	}

	/**
	 * Devuelve el último movimiento realizado y lo elimina de la pila de deshacer
	 * @return El movimiento a deshacer
	 * @throws MovimientoException En caso de que no haya movimientos que deshacer
	 * @throws EmptyStackException 
	 */
	public Movimiento deshacer () throws MovimientoException, EmptyStackException {
		if(pilaDeshacer.isEmpty()) {
			throw new MovimientoException("No se puede deshacer porque no hay movimientos para deshacer");
		}
		Movimiento movimientoDeshecho = pilaDeshacer.pop();
		pilaRehacer.push(movimientoDeshecho);

		return movimientoDeshecho; 
	}

	/**
	 * Devuelve el último movimiento deshecho y lo elimina de la pila de rehacer
	 * @return El movimiento a rehacer
	 * @throws MovimientoException En caso de que no haya movimientos que rehacer
	 * @throws EmptyStackException 
	 */
	public Movimiento rehacer () throws MovimientoException, EmptyStackException {
		if(pilaRehacer.isEmpty()) {
			throw new MovimientoException("No se puede rehacer porque no hay movimientos para rehacer");
		}
		Movimiento movimientoRehecho = pilaRehacer.pop();
		pilaDeshacer.push(movimientoRehecho);

		return movimientoRehecho; 
	}

}
