package principal;

import controlador.ControladorPrincipal;
import javax.swing.SwingUtilities;

/**
 *
 * @author fgarin
 */
public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ControladorPrincipal();
			}
		});
	}

}
