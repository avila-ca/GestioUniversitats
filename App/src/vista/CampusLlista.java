package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @author fgarin
 */
public final class CampusLlista extends JFrame{ 
    
    private final int AMPLADA = 600;
    private final int ALCADA = 200;
    
    private JTable tCampus;

    private JButton sortir;   
    

    public CampusLlista() {

        this.setTitle("Llista de Campus");
        this.setLayout(new GridLayout(0, 1));

        tCampus = new JTable(new CampusTableModel());
        
        sortir = new JButton("Sortir");
        sortir.setActionCommand("SortirLlista");

        this.add(new JScrollPane(tCampus));  
        this.add(sortir);

        showFinestra();
    }
    
    private void showFinestra(){
        //Es mostra la finestra amb propietats per defecte
        this.setSize(AMPLADA, ALCADA);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JTable getTableCampus() {
        return tCampus;
    }

    public void setTableCampus(JTable tCampus) {
        this.tCampus = tCampus;
    }    

    public JButton getSortir() {
        return sortir;
    }

    public void setSortir(JButton sortir) {
        this.sortir = sortir;
    }
}
