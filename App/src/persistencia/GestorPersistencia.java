package persistencia;

import exceptions.GestorUniversitatsException;
import model.Universitat;
import interfaces.ProveedorPersistencia;

/**
 *
 */
public class GestorPersistencia {

	private ProveedorPersistencia gestor;

	public ProveedorPersistencia getGestor() {
		return gestor;
	}

	public void desarUniversitat(String tipusPersistencia, String nomFitxer, Universitat universitat)
			throws GestorUniversitatsException {

		switch (tipusPersistencia) {

		case "XML":
			System.out.println("Seleccionat XML.");
			gestor = new GestorXML();
                        gestor.desarUniversitat(nomFitxer, universitat);
			break;
		case "JDBC":
			System.out.println("Seleccionat JDBC.");
			gestor = new GestorJDBC();
                        gestor.desarUniversitat(null, universitat);
			break;
		default:
			System.out.println("Seleccionat DB4O.");
			gestor = new GestorDB4O();
                        gestor.desarUniversitat(null, universitat);
			break;
		}

		

	}

	public void carregarUniversitat(String tipusPersistencia, String nomFitxer) throws GestorUniversitatsException {

		switch (tipusPersistencia) {

		case "XML":
			gestor = new GestorXML();
			break;
		case "JDBC":
			gestor = new GestorJDBC();
			break;
		default:
			gestor = new GestorDB4O();
			break;

		}

		gestor.carregarUniversitat(nomFitxer);
	}
}
