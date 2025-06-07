package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Universitat;
import persistencia.GestorPersistencia;
import vista.MenuPrincipal;

/**
 *
 */
public class ControladorPrincipal implements ActionListener {

	static private MenuPrincipal menuPrincipal;
	static private final int MAXUNIVERSITATS = 4;
	static private Universitat[] universitats = new Universitat[MAXUNIVERSITATS];
	static private int pUniversitats = 0; // Primera posició buida del vector universitats
	static private Universitat universitatActual = null;
	static private GestorPersistencia gp = new GestorPersistencia();
	static private final String[] METODESPERSISTENCIA = { "XML", "JDBC", "DB4O" };

	public ControladorPrincipal() {

		// S'inicialitza l'atribut menuPrincipal (això mostrarà el menú principal).
		menuPrincipal = new MenuPrincipal();

		// A cada botó del menú, s'afegeix aquest mateix objecte (ControladorPrincipal)
		// com a listener.
		for (JButton boto : menuPrincipal.getMenuButtons()) {
			boto.addActionListener(this);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// e.getActionCommand() Identifica l'acció prevista. Per defecte, si no
		// s'estableix la propietat actionCommand a un botó, aquesta pren el valor del
		// text del botó.
		seleccionarOpcio(e.getActionCommand());

	}

	private void seleccionarOpcio(String opcio) {
		switch (opcio) {
		case "0. Sortir":
			// Tanca la finestra.
			menuPrincipal.dispose();
			break;
		case "1. Menú Universitats":
			// Fa no visible la finestra.
			menuPrincipal.setVisible(false);
			new ControladorUniversitat();
			break;
		// EAC11
		case "2. Menú Campus":

			if (universitatActual != null) {
				menuPrincipal.setVisible(false);
				new ControladorCampus();
			} else {
				JOptionPane.showMessageDialog(menuPrincipal,
						"<html><p>Primer s'ha de seleccionar la universitat <br> al menú d'universitats.</p><html>");
			}

			break;
		}

	}

	public static MenuPrincipal getMenuPrincipal() {
		return menuPrincipal;
	}

	public static void setMenuPrincipal(MenuPrincipal menuPrincipal) {
		ControladorPrincipal.menuPrincipal = menuPrincipal;
	}

	public static GestorPersistencia getGp() {
		return gp;
	}

	public static void setGp(GestorPersistencia gp) {
		ControladorPrincipal.gp = gp;
	}

	public static Universitat[] getUniversitats() {
		return universitats;
	}

	public static void setUniversitats(Universitat[] universitats) {
		ControladorPrincipal.universitats = universitats;
	}

	public static int getpUniversitats() {
		return pUniversitats;
	}

	public static void setpUniversitats() {
		ControladorPrincipal.pUniversitats++;
	}

	public static Universitat getUniversitatActual() {
		return universitatActual;
	}

	public static int getMAXUNIVERSITATS() {
		return MAXUNIVERSITATS;
	}

	public static void setUniversitatActual(Universitat universitatActual) {
		ControladorPrincipal.universitatActual = universitatActual;
	}

	public static String[] getMETODESPERSISTENCIA() {
		return METODESPERSISTENCIA;
	}

}
