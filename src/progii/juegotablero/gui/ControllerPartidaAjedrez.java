package progii.juegotablero.gui;

import javax.swing.JOptionPane;
import list.IList;
import progii.juegotablero.exceptions.FinPartidaException;
import progii.juegotablero.exceptions.PartidaException;
import progii.juegotablero.model.Casilla;
import progii.juegotablero.model.Partida;
import progii.juegotablero.model.ajedrez.PartidaAjedrez;

public class ControllerPartidaAjedrez {
	private boolean seleccionado = false;

	private int selFila;

	private int selColumna;

	private FrameAjedrez frame;

	private Partida partida;

	public ControllerPartidaAjedrez(FrameAjedrez frame) {
		this.frame = frame;
	}

	public void nuevaPartida() {
		this.partida = (Partida)new PartidaAjedrez();
		this.frame.getMovimientos().removeAllElements();
		desmarcarTodo();
		this.seleccionado = false;
	}

	public void guardarPartida(String file) {
		try {
			this.partida.guardar(file);
		} catch (PartidaException e) {
			JOptionPane.showMessageDialog(this.frame, e.getMessage());
		} 
	}

	public void cargarPartida(String file) {
		try {
			this.partida.cargar(file);
			desmarcarTodo();
			this.seleccionado = false;
		} catch (PartidaException e) {
			JOptionPane.showMessageDialog(this.frame, e.getMessage());
		} 
	}

	private void hacerMovimiento(int x, int y, int toX, int toY) {
		try {
			String movimiento = this.partida.hacerMovimiento(x, y, toX, toY);
			System.out.println(this.partida);
			this.frame.getMovimientos().addElement(movimiento);
		} catch (FinPartidaException e) {
			JOptionPane.showMessageDialog(this.frame, e.getMessage());
		} catch (PartidaException e) {
			JOptionPane.showMessageDialog(this.frame, "Ha ocurrido el error: " + e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.frame, "Ha ocurrido un error desconocido: " + 
					e.getMessage() + 
					"\nEn la consola aparecerla traza con la excepci\nLa aplicacise cerrar");
			e.printStackTrace();
			System.exit(-1);
		} 
		this.frame.repaint();
	}

	public void deshacer() {
		if (this.partida.estaFinalzada()) {
			JOptionPane.showMessageDialog(this.frame, "La partida ha terminado. ");
			return;
		} 
		try {
			String movimiento = this.partida.deshacerMovimiento();
			this.frame.getMovimientos().addElement(String.valueOf(movimiento) + " (deshacer)");
		} catch (PartidaException e) {
			JOptionPane.showMessageDialog(this.frame, "Ha ocurrido el error: " + e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.frame, "Ha ocurrido un error desconocido: " + 
					e.getMessage() + 
					"\nEn la consola aparecerla traza con la excepci\nLa aplicacise cerrar");
			e.printStackTrace();
			System.exit(-1);
		} 
		this.frame.repaint();
	}

	public void rehacer() {
		if (this.partida.estaFinalzada()) {
			JOptionPane.showMessageDialog(this.frame, "La partida ha terminado. ");
			return;
		} 
		try {
			String movimiento = this.partida.rehacerMovimiento();
			this.frame.getMovimientos().addElement(String.valueOf(movimiento) + " (rehacer)");
		} catch (PartidaException e) {
			JOptionPane.showMessageDialog(this.frame, "Ha ocurrido el error: " + e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.frame, "Ha ocurrido un error desdonocido: " + 
					e.getMessage() + 
					"\nEn la consola aparecerla traza con la excepci\nLa aplicacise cerrar");
			e.printStackTrace();
			System.exit(-1);
		} 
		this.frame.repaint();
	}

	public IList<Casilla> getMovimientosValidos(int x, int y) {
		return this.partida.getMovimientosPosibles(x, y);
	}

	public String getTipoPieza(int x, int y) {
		return this.partida.getTipoPieza(x, y);
	}

	public String getTurnoActual() {
		return this.partida.getTurnoActual();
	}

	public void doClick(int x, int y, boolean validDestinatiom) {
		if (this.partida.estaFinalzada()) {
			JOptionPane.showMessageDialog(this.frame, "La partida ha terminado. ");
			return;
		} 
		if (!this.seleccionado && !this.partida.hayPieza(x, y))
			return; 
		if (this.seleccionado && x == this.selFila && y == this.selColumna) {
			this.seleccionado = false;
			desmarcarTodo();
			return;
		} 
		if (!this.seleccionado && !this.partida.tieneTurno(x, y)) {
			JOptionPane.showMessageDialog(this.frame, "La pieza seleccionada no tiene el turno");
			return;
		} 
		if (this.seleccionado) {
			if (validDestinatiom) {
				hacerMovimiento(this.selFila, this.selColumna, x, y);
				this.seleccionado = false;
				desmarcarTodo();
			} else {
				JOptionPane.showMessageDialog(this.frame, "La casilla seleccionada no es un destino v");
				return;
			} 
		} else {
			this.selFila = x;
			this.selColumna = y;
			this.seleccionado = true;
			this.frame.getPanelTablero().getPanelPieza(this.selFila, this.selColumna).marcar();
			IList<Casilla> validos = getMovimientosValidos(x, y);
			for (int i = 0; validos != null && i < validos.size(); i++)
				this.frame.getPanelTablero().getPanelPieza(((Casilla)validos.get(i)).getX(), ((Casilla)validos.get(i)).getY()).setValido(); 
		} 
		this.frame.repaint();
	}

	private void desmarcarTodo() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				this.frame.getPanelTablero().getPanelPieza(i, j).desmarcar(); 
		} 
	}

	public boolean haySeleccionado() {
		return this.seleccionado;
	}
}