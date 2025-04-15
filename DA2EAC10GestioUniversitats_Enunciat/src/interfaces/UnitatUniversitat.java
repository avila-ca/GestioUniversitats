/**
 * Interfície que implementarà qualsevol element d'una universitat 
 */
package interfaces;

import java.util.Scanner;

/**
 *
 */
public interface UnitatUniversitat {
    
    public final static Scanner DADES = new Scanner(System.in);
    
    public void updateUnitatUniversitat(); 
    
    public void showUnitatUniversitat();
    
}

