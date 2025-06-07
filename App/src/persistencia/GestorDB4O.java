package persistencia;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;
import exceptions.GestorUniversitatsException;
import model.Universitat;
import interfaces.ProveedorPersistencia;

/**
 *
 */
public class GestorDB4O implements ProveedorPersistencia {

	private ObjectContainer db;
	private Universitat universitat;

	public Universitat getUniversitat() {
		return universitat;
	}

	public void setUniversitat(Universitat Universitat) {
		this.universitat = Universitat;
	}

	/*
	 * TODO
	 * 
	 * Paràmetres: cap.
	 *
	 * Acció:
	 * 
	 * - Heu de crear / obrir la base de dades "EAC112425S2.db4o".
	 * 
	 * - Aquesta base de dades ha de permetre que Universitat s'actualitzi en
	 * cascada.
	 *
	 * Retorn: cap.
	 *
	 */
	public void estableixConnexio() {
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().objectClass(Universitat.class).cascadeOnUpdate(true);
		db = Db4oEmbedded.openFile(config, "EAC112425S2.db4o");
	}

	public void tancaConnexio() {
		db.close();
	}

	/*
	 * TODO
	 * 
	 * Paràmetres: el nom del fitxer i la universitat a desar.
         *        
         * El primer paràmetre NO s'utilitza (és null).
	 *
	 * Acció:
	 * 
	 * - Heu d'establir la connexio i al final tancar-la.
	 * 
	 * - Heu de desar l'objecte Universitat passat per paràmetre sobre la base de
	 * dades. Heu d'inserir si no existia ja a la base de dades, o actualitzar en
	 * l'altre cas.
	 * 
	 * - S'ha de fer la consulta de l'existència amb Predicate.
	 *
	 * Retorn: cap.
	 *
	 */
	@Override
	public void desarUniversitat(String nomFitxer, Universitat pUniversitat)
			throws GestorUniversitatsException {

		estableixConnexio();

		List<Universitat> universitats = db.query(new Predicate<Universitat>() {
			public boolean match(Universitat universitats) {
                                // Criteri de cerca
				return universitats.getNomUniversitat().equals(pUniversitat.getNomUniversitat());
			}
		});

		// No existeix
		if (universitats.size() != 1) {
			db.store(pUniversitat);
			db.commit();
		// Existeix
		} else {
			universitat = universitats.iterator().next();
			universitat.setNomUniversitat(pUniversitat.getNomUniversitat());
			universitat.setUbicacioSeu(pUniversitat.getUbicacioSeu());
			db.store(universitat);
			db.commit();
		}

		tancaConnexio();
	}

	/*
	 * TODO
	 * 
	 * Paràmetres: el nom de la universitat.
	 *
	 * Acció:
	 * 
	 * - Heu d'establir la connexio i al final tancar-la.
	 * 
	 * - Heu de carregar la universitat des de la base de dades assignant-la a
	 * l'atribut "universitat". Si no existeix, llanceu l'excepció
	 * GestorUniversitatException amb codi "GestorDB4O.noExisteix".
	 * 
	 * - S'ha de fer la consulta amb Predicate.
	 *
	 * Retorn: cap
	 *
	 */
	@Override
	public void carregarUniversitat(String nom) throws GestorUniversitatsException {

		estableixConnexio();

		List<Universitat> universitats = db.query(new Predicate<Universitat>() {
			public boolean match(Universitat universitat) {
				return universitat.getNomUniversitat().equals(nom);
			}
		});

		// No existeix
		if (universitats.size() != 1) {
			throw new GestorUniversitatsException("GestorDB4O.noExisteix");
		} else {
			universitat = universitats.iterator().next();
		}

		tancaConnexio();
	}
}
