package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author fgarin
 */
public class UniversitatForm extends JFrame {

    private final int AMPLADA = 300;
    private final int ALCADA = 200;

    private JLabel lNomUniversitat;
    private JTextField tNomUniversitat;
    private JLabel lUbicacioSeu;
    private JTextField tUbicacioSeu;

    private JButton desar;
    private JButton sortir;

    public UniversitatForm() {
        /*
         * TODO
         * 
         * No afegiu cap listener a cap control.
         * 
         * Amb els atributs d'aquesta classe, heu de fer el següent:
         * 
         * - Heu de posar el títol "Formulari Universitat" a l'objecte JFrame
         * 
         * - Heu d'establir un layout Grid d'una columna per al JFrame
         * 
         * - Heu de crear els controls del formulari (JLabels i JTextFields).
         * 
         * - Heu de crear els botons del formulari.
         * 
         * - Heu d'afegir-ho tot al panell per defecte del JFrame
         * (this.getContentPane()).
         * 
         * - Heu de fer visible el frame amb l'amplada i alçada de les constants AMPLADA
         * i ALCADA, i fer que la finestra es tanqui quan l'usuari ho fa amb el control
         * "X" de la finestra. Per fer tot això, heu de cridar al mètode showFinestra()
         * d'aquesta classe.
         * 
         */

    }

    private void showFinestra() {
        // Es mostra la finestra amb propietats per defecte
        this.setSize(AMPLADA, ALCADA);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JLabel getlNomUniversitat() {
        return lNomUniversitat;
    }

    public void setlNomUniversitat(JLabel lNomUniversitat) {
        this.lNomUniversitat = lNomUniversitat;
    }

    public JTextField gettNomUniversitat() {
        return tNomUniversitat;
    }

    public void settNomUniversitat(JTextField tNomUniversitat) {
        this.tNomUniversitat = tNomUniversitat;
    }

    public JLabel getlUbicacioSeu() {
        return lUbicacioSeu;
    }

    public void setlUbicacioSeu(JLabel lUbicacioSeu) {
        this.lUbicacioSeu = lUbicacioSeu;
    }

    public JTextField gettUbicacioSeu() {
        return tUbicacioSeu;
    }

    public void settUbicacioSeu(JTextField tUbicacioSeu) {
        this.tUbicacioSeu = tUbicacioSeu;
    }

    public JButton getDesar() {
        return desar;
    }

    public void setDesar(JButton desar) {
        this.desar = desar;
    }

    public JButton getSortir() {
        return sortir;
    }

    public void setSortir(JButton sortir) {
        this.sortir = sortir;
    }

}