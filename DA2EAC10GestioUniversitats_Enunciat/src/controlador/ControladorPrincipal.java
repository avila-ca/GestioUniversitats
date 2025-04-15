package controlador;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import model.Universitat;
import persistencia.GestorPersistencia;
import vista.MenuPrincipal;

/**
 *
 * @author fgarin
 */
public class ControladorPrincipal implements ActionListener {

    static private MenuPrincipal menuPrincipal;
    static private final int MAXUNIVERSITATS = 4;
    static private Universitat[] universitats = new Universitat[MAXUNIVERSITATS];
    static private int pUniversitats = 0; // Primera posició buida del vector universitats
    static private Universitat universitatActual = null;
    static private GestorPersistencia gp = new GestorPersistencia();
    static private final String[] METODESPERSISTENCIA = { "XML" };

    public ControladorPrincipal() {

        /*
         * TODO
         * 
         * Inicialitzeu l'atribut menuPrincipal (això mostrarà el menú principal). A
         * cada botó del menú, afegiu aquest mateix (ControladorPrincipal) objecte com a
         * listener.
         * 
         */
       menuPrincipal = new MenuPrincipal();

       menuPrincipal.getBtnSortir().addActionListener(this);
       menuPrincipal.getBtnUniversitats().addActionListener(this);
       menuPrincipal.getBtnAules().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /*
         * TODO
         * 
         * Seleccioneu l'acció a realitzar cridant el mètode seleccionarOpcio i
         * utilitzant la propietat actionCommand dels botons, que per defecte 
         * és igual al text dels botons. El mètode getActionCommand de l'esdeveniment 
         * retorna la propietat actionCommand de l'objecte que ha produït l'acció.
         * 
         */
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
            case "2. Menú Aules":
                JOptionPane.showMessageDialog(menuPrincipal, "No heu d'implementar aquest menú");
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
