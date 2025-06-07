package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author fgarin
 */
public class MenuCampus extends JFrame{

    private JButton[] menuButtons = new JButton[3];

    private final int AMPLADA = 800;
    private final int ALCADA = 600;

    public MenuCampus() {
        
		// Definició del títol de la finestra del menú.
    	this.setTitle("Menú Campus");	
    	
		// Definició del layout
    	this.setLayout(new GridLayout(0, 1));

        //Creació dels botons a la llista
        menuButtons[0] = new JButton("0. Sortir");
        menuButtons[1] = new JButton("1. Alta Campus");
        menuButtons[2] = new JButton("2. Llistar Campus");
        
        //Addició dels botons a la finestra
        for (JButton boto : menuButtons) {
        	this.getContentPane().add(boto);
        }
        
        showFinestra();
    }
    
    private void showFinestra(){
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
