package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author fgarin
 */
public final class UniversitatLlista extends JFrame {

	private final int AMPLADA = 600;
	private final int ALCADA = 200;

	private JTable tUniversitats;

	private JButton sortir;

	public UniversitatLlista() {

		// Definició del títol de la finestra del menú
		this.setTitle("Llista d'Universitats");

		// Definició del layout
		this.setLayout(new GridLayout(0, 1));

		tUniversitats = new JTable(new UniversitatTableModel());

		sortir = new JButton("Sortir");
		sortir.setActionCommand("SortirLlista");

		this.add(new JScrollPane(tUniversitats));
		this.add(sortir);

		showFinestra();
	}

	private void showFinestra() {
		// Es mostra la finestra amb propietats per defecte
		this.setSize(AMPLADA, ALCADA);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public JTable gettUniversitats() {
		return tUniversitats;
	}

	public void settUniversitats(JTable tUniversitats) {
		this.tUniversitats = tUniversitats;
	}

	public JButton getSortir() {
		return sortir;
	}

	public void setSortir(JButton sortir) {
		this.sortir = sortir;
	}
}
