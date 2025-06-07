package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Campus;
import vista.MenuCampus;
import vista.CampusForm;
import vista.CampusLlista;

/**
 *
 */
public class ControladorCampus implements ActionListener {

	private MenuCampus menuCampus;
	private CampusForm campusForm = null;
	private CampusLlista campusLlista = null;

	public ControladorCampus() {

		menuCampus = new MenuCampus();
		afegirListenersMenu();

	}

	private void afegirListenersMenu() {

		for (JButton boto : menuCampus.getMenuButtons()) {
			boto.addActionListener(this);
		}

	}

	private void afegirListenersForm() {

		campusForm.getDesar().addActionListener(this);
		campusForm.getSortir().addActionListener(this);

	}

	private void afegirListenersLlista() {

		campusLlista.getSortir().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		seleccionarOpcio(e.getActionCommand());

	}

	private void seleccionarOpcio(String opcio) {

		switch (opcio) {
		case "0. Sortir":
			menuCampus.dispose();
			ControladorPrincipal.getMenuPrincipal().setVisible(true);
			break;
		case "1. Alta Campus":
			if (ControladorPrincipal.getUniversitats()[0] != null) {
				campusForm = new CampusForm();
				afegirListenersForm();
			} else {
				menuCampus.setVisible(true);
				JOptionPane.showMessageDialog(menuCampus,
						"Abans s'ha de crear almenys una universitat en el menú d'universitats.");
			}
			break;
		case "2. Llistar Campus":
			if (ControladorPrincipal.getUniversitats()[0] != null) {
				campusLlista = new CampusLlista();
				afegirListenersLlista();
			} else {
				menuCampus.setVisible(true);
				JOptionPane.showMessageDialog(menuCampus,
						"Abans s'ha de crear almenys una universitat en el menú d'universitats.");
			}

			break;

		case "SortirForm":
			menuCampus.setVisible(true);
			campusForm.dispose();
			break;

		case "SortirLlista":
			menuCampus.setVisible(true);
			campusLlista.dispose();
			break;

		case "Desar":
			Campus campus = new Campus(campusForm.getNomCampus(),
					campusForm.getUbicacio());
			ControladorPrincipal.getUniversitatActual().addCampus(campus);

			break;

		}

	}

}
