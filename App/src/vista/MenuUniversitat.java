package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author fgarin
 */
public class MenuUniversitat extends JFrame {

    private JButton[] menuButtons = new JButton[6];

    private final int AMPLADA = 800;
    private final int ALCADA = 600;

    public MenuUniversitat() {

        /*
         * TODO
         * 
         * No afegiu cap listener a cap control.
         * 
         * Amb els atributs d'aquesta classe, heu de fer el següent:
         * 
         * - Heu de posar el títol "Menú Universitats" a l'objecte JFrame
         * 
         * - Heu d'establir un layout Grid d'una columna per al JFrame
         * 
         * - Heu de crear els botons del formulari. Cada botó serà un element de l'array
         * de botons amb les etiquetes indicades: 
         * 
         * "0. Sortir" 
         * "1. Alta Universitat"
         * "2. Seleccionar Universitat" 
         * "3. Llistar Universitats"
         * "4. Carregar Universitat" 
         * "5. Desar Universitat"
         * 
         * - Heu d'afegir-ho tots els botons al panell per defecte del JFrame
         * (this.getContentPane()).
         * 
         * - Heu de fer visible el frame amb l'amplada i alçada de les constants AMPLADA
         * i ALCADA, i fer que la finestra es tanqui quan l'usuari ho fa amb el control
         * "X" de la finestra. Per fer tot això, heu de cridar al mètode showFinestra()
         * d'aquesta classe.
         * 
         */

        // Definició del títol de la finestra del menú.	
        this.setTitle("Menú Universitats");

        // Definició del layout
        this.setLayout(new GridLayout(0, 1));

        // Creació dels botons a la llista
        menuButtons[0] = new JButton("0. Sortir");
        menuButtons[1] = new JButton("1. Alta Universitat");
        menuButtons[2] = new JButton("2. Seleccionar Universitat");
        menuButtons[3] = new JButton("3. Llistar Universitats");
        menuButtons[4] = new JButton("4. Carregar Universitat");
        menuButtons[5] = new JButton("5. Desar Universitat");

        // Addició dels botons a la finestra
        for (JButton boto : menuButtons) {
            this.getContentPane().add(boto);
        }

        showFinestra();
    }

    private void showFinestra() {
        // Es mostra la finestra amb propietats per defecte
        this.setSize(AMPLADA, ALCADA);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JButton[] getMenuButtons() {
        return menuButtons;
    }

    public void setMenuButtons(JButton[] menuButtons) {
        this.menuButtons = menuButtons;
    }
}
