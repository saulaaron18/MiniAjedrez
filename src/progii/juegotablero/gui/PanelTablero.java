package progii.juegotablero.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class PanelTablero extends JPanel {
	private static final long serialVersionUID = -1958220213524832382L;

	private PanelPieza[][] pPiezas;

	public PanelTablero(int nFilas, int nColumnas, int tamanio, ControllerPartidaAjedrez controller) {
		setLayout(new GridBagLayout());
		this.pPiezas = new PanelPieza[nFilas][nColumnas];
		GridBagConstraints gbc = new GridBagConstraints();
		Color c = Color.GRAY;
		add(new PanelLeyenda(tamanio, " "), gbc);
		int i;
		for (i = 0; i < nColumnas; i++)
			add(new PanelLeyenda(tamanio, (char)(97 + i)+""), gbc); 
		for (i = 0; i < nFilas; i++) {
			c = (c == Color.WHITE) ? Color.GRAY : Color.WHITE;
			gbc.gridy = i + 1;
			gbc.gridx = 0;
			add(new PanelLeyenda(tamanio, nFilas - i + ""), gbc);
			for (int j = 0; j < nColumnas; j++) {
				gbc.gridx = j + 1;
				this.pPiezas[i][j] = new PanelPieza(i, j, tamanio, c, controller);
				add(this.pPiezas[i][j], gbc);
				c = (c == Color.WHITE) ? Color.GRAY : Color.WHITE;
			} 
		} 
	}

	protected PanelPieza getPanelPieza(int x, int y) {
		return this.pPiezas[x][y];
	}
}