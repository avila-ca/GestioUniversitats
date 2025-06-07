package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Universitat;
import persistencia.GestorPersistencia;
import persistencia.GestorXML;
import exceptions.GestorUniversitatsException;
import persistencia.GestorDB4O;
import persistencia.GestorJDBC;
import vista.MenuUniversitat;
import vista.UniversitatForm;
import vista.UniversitatLlista;

/**
 *
 */
public class ControladorUniversitat implements ActionListener {

    private MenuUniversitat menuUniversitat;
    private UniversitatForm universitatForm = null;
    private UniversitatLlista universitatLlista = null;

	public ControladorUniversitat() {

		 // S'inicialitza l'atribut menuUniversitat (això mostrarà el menú d'universitats).
                 menuUniversitat = new MenuUniversitat();

		// Es crida a afegirListenersMenu per afegir tots els escoltadors als botons del
		// menú.
		afegirListenersMenu();

	}

	// El controlador com a listener dels controls de les finestres que gestionen les universitats

	private void afegirListenersMenu() {

		// S'afegeix aquest mateix objecte (ControladorUniversitat) com a listener, a
                // cada botó del menú d'universitats.
		 for (JButton boto : menuUniversitat.getMenuButtons()) {
                    boto.addActionListener(this);
		}

	}

	private void afegirListenersForm() {

		// S'afegeix aquest mateix objecte (ControladorUniversitat) com a listener, a
                // cada botó del formulari de la unversitat.
                universitatForm.getDesar().addActionListener(this);
                universitatForm.getSortir().addActionListener(this);

	}

	private void afegirListenersLlista() {

		// S'afegeix aquest mateix objecte (ControladorUniversitat) com a listener, al
                // botó sortir de la llista d'universitats.
                universitatLlista.getSortir().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Selecciona l'acció a realitzar cridant el mètode seleccionarOpcio i
		// utilitzant la propietat actionCommand dels botons.
		seleccionarOpcio(e.getActionCommand());

	}

	private void seleccionarOpcio(String opcio) {

		GestorPersistencia gestor;
		Universitat universitat;

		switch (opcio) {

		case "0. Sortir":
			menuUniversitat.dispose();
			ControladorPrincipal.getMenuPrincipal().setVisible(true);
			break;

		case "1. Alta Universitat":
			if (ControladorPrincipal.getpUniversitats() < ControladorPrincipal.getMAXUNIVERSITATS()) {
				menuUniversitat.setVisible(false);
				universitatForm = new UniversitatForm();
				afegirListenersForm();
			} else {
				menuUniversitat.setVisible(true);
				JOptionPane.showMessageDialog(menuUniversitat, "Màxim nombre d'universitats.");
			}
			break;

		case "2. Seleccionar Universitat":
			menuUniversitat.setVisible(true);
			if (ControladorPrincipal.getUniversitats()[0] != null) {
				seleccionarUniversitat();
			} else {
				JOptionPane.showMessageDialog(menuUniversitat, "Abans s'ha de crear al menys una universitat");
			}
			break;

		case "3. Llistar Universitats":
			if (ControladorPrincipal.getUniversitats()[0] != null) {
				universitatLlista = new UniversitatLlista();
				afegirListenersLlista();
			} else {
				menuUniversitat.setVisible(true);
				JOptionPane.showMessageDialog(menuUniversitat, "Abans s'ha de crear al menys una universitat");
			}
			break;

		// EAC11: Modificat respecte l'EAC10 per acceptar diferentes formes de persistència.	
		case "4. Carregar Universitat":

			menuUniversitat.setVisible(true);

			int code = JOptionPane.showOptionDialog(null, "Selecciona un mètode", "Carregar universitat", 0,
					JOptionPane.QUESTION_MESSAGE, null, ControladorPrincipal.getMETODESPERSISTENCIA(), "XML");

			if (code != JOptionPane.CLOSED_OPTION) {

				gestor = new GestorPersistencia();

				try {

					String nom = JOptionPane.showInputDialog("Quin és el nom de la universitat que vols carregar?");

					gestor.carregarUniversitat(ControladorPrincipal.getMETODESPERSISTENCIA()[code], nom);

					String[] values = ControladorPrincipal.getMETODESPERSISTENCIA();

					switch (values[code]) {
					case "XML":
						universitat = ((GestorXML) gestor.getGestor()).getUniversitat();
						break;

					case "JDBC":
						universitat = ((GestorJDBC) gestor.getGestor()).getUniversitat();
						break;

					case "DB4O":
						universitat = ((GestorDB4O) gestor.getGestor()).getUniversitat();
						break;

					default:
						throw new IllegalArgumentException("Unexpected value: " + code);
					}

					int pos = comprovarUniversitat(universitat.getNomUniversitat());

					if (pos >= 0) {

						Object[] options = { "OK", "Cancel·lar" };
						int i = JOptionPane.showOptionDialog(null, "Premeu OK per substituir-la.",
								"La universitat ja existeix", JOptionPane.DEFAULT_OPTION,
								JOptionPane.WARNING_MESSAGE, null, options, options[0]);

						if (i == 0) {
							ControladorPrincipal.getUniversitats()[pos] = universitat;
						}

					} else {

						ControladorPrincipal.getUniversitats()[ControladorPrincipal
								.getpUniversitats()] = universitat;
						ControladorPrincipal.setpUniversitats();
						JOptionPane.showMessageDialog(menuUniversitat, "Universitat afegida correctament");

					}

				} catch (GestorUniversitatsException e) {

					JOptionPane.showMessageDialog(menuUniversitat, e.getMessage());

				}
			}
			break;

		// EAC11: Modificat respecte l'EAC10 per acceptar diferentes formes de persistència.		
		case "5. Desar Universitat":
			menuUniversitat.setVisible(true);

			if (ControladorPrincipal.getUniversitatActual() != null) {

				int tipusMissatge = JOptionPane.QUESTION_MESSAGE;
				int codi = JOptionPane.showOptionDialog(null, "Selecciona un mètode", "Desar universitat", 0,
						tipusMissatge, null, ControladorPrincipal.getMETODESPERSISTENCIA(), "XML");

				if (codi != JOptionPane.CLOSED_OPTION) {

					System.out.println("Pica botó després de seleccionar entre XML, JDBC o DBO4");

					gestor = new GestorPersistencia();

					try {

						System.out.println("Centre de Recerca:" + ControladorPrincipal.getUniversitatActual().getNomUniversitat());
                                                Universitat universitatActual = ControladorPrincipal.getUniversitatActual();
						gestor.desarUniversitat(ControladorPrincipal.getMETODESPERSISTENCIA()[codi], universitatActual.getNomUniversitat(),
								ControladorPrincipal.getUniversitatActual());

						JOptionPane.showMessageDialog(menuUniversitat, "Universitat desada correctament");

					} catch (GestorUniversitatsException e) {
						JOptionPane.showMessageDialog(menuUniversitat, e.getMessage());
					}
				}
			} else {
				JOptionPane.showMessageDialog(menuUniversitat, "Abans s'ha de seleccionar una universitat");
			}
			System.out.println("5. Desar Universitat");
			break;

		case "SortirForm":
			menuUniversitat.setVisible(true);
			universitatForm.dispose();
			break;

		case "SortirLlista":
			menuUniversitat.setVisible(true);
			universitatLlista.dispose();
			break;

		case "Desar":
			universitat = new Universitat(universitatForm.gettNomUniversitat().getText(),
					universitatForm.gettUbicacioSeu().getText());
			ControladorPrincipal.getUniversitats()[ControladorPrincipal.getpUniversitats()] = universitat;
			ControladorPrincipal.setpUniversitats();
			ControladorPrincipal.setUniversitatActual(universitat);
			System.out.println("Desar");
			break;
		}

	}

	private void seleccionarUniversitat() {

		String[] noms = new String[ControladorPrincipal.getpUniversitats()];

		int i = 0;

		for (Universitat universitat : ControladorPrincipal.getUniversitats()) {

			if (universitat != null) {
				noms[i] = universitat.getNomUniversitat();
			}

			i++;

		}

		int messageType = JOptionPane.QUESTION_MESSAGE;
		int codi = JOptionPane.showOptionDialog(null, "Selecciona una universitat",
				"Selecció de la universitat", 0, messageType, null, noms, "A");

		if (codi != JOptionPane.CLOSED_OPTION) {
			ControladorPrincipal.setUniversitatActual(ControladorPrincipal.getUniversitats()[codi]);
		}

	}

	private int comprovarUniversitat(String nom) {

		boolean trobat = false;

		int pos = -1;

		for (int i = 0; i < ControladorPrincipal.getUniversitats().length && !trobat; i++) {

			if (ControladorPrincipal.getUniversitats()[i] != null) {
				if (ControladorPrincipal.getUniversitats()[i].getNomUniversitat().equals(nom)) {
					pos = i;
					trobat = true;
				}
			}
		}

		return pos;
	}

}
