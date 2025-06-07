package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author fgarin
 */
public class CampusForm extends JFrame{   
    
    private final int AMPLADA = 300;
    private final int ALCADA = 200;

    private JLabel lNomCampus;
    private JTextField tNomCampus;
    private JLabel lUbicacio;
    private JTextField tUbicacio;

    private JButton desar;   
    private JButton sortir;   

    public CampusForm() {

        this.setTitle("Formulari Campus");
        this.setLayout(new GridLayout(0, 1));

        //Creació dels controls del formulari
        lNomCampus = new JLabel("NomCampus");
        tNomCampus = new JTextField(20);
        lUbicacio = new JLabel("Ubicacio");
        tUbicacio = new JTextField(20);

        //Creació dels botons del formulari
        desar = new JButton("Desar");
        sortir = new JButton("Sortir");
        sortir.setActionCommand("SortirForm");

        //Addició del tot el formulari a la finestra
        this.getContentPane().add(lNomCampus);
        this.getContentPane().add(tNomCampus);
        this.getContentPane().add(lUbicacio);
        this.getContentPane().add(tUbicacio);
        this.getContentPane().add(desar);       
        this.getContentPane().add(sortir);

        showFinestra();
    }
    
    public CampusForm(String nom, String area){
        this();
        tNomCampus.setText(nom);
        tUbicacio.setText(area);
    }
    
    private void showFinestra(){
        //Es mostra la finestra amb propietats per defecte
    	this.setSize(AMPLADA, ALCADA);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
    }
   
    public String getNomCampus() {
    	return tNomCampus.getText();
    }
   
    public String getUbicacio() {
    	return tUbicacio.getText();
    }
    
    public JTextField gettNomCampus() {
        return tNomCampus;
    }
    
    public void settNomCampus(JTextField tNomCampus) {
        this.tNomCampus = tNomCampus;
    }

    public JTextField gettUbicacio() {
        return tUbicacio;
    }
    
    public void settUbicacio(JTextField tUbicacio) {
        this.tUbicacio = tUbicacio;
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