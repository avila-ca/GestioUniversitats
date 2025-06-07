package interfaces;

import exceptions.GestorUniversitatsException;
import model.Universitat;

/**
 *
 */
public interface ProveedorPersistencia {
    public void desarUniversitat(String nomFitxer, Universitat universitat)throws GestorUniversitatsException;
    public void carregarUniversitat(String nomFitxer)throws GestorUniversitatsException; 

    public Universitat getUniversitat();
    public void setUniversitat(Universitat Universitat);
}
