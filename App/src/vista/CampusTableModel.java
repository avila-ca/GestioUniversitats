package vista;

import controlador.ControladorPrincipal;
import javax.swing.table.AbstractTableModel;
import model.Campus;

/**
 *
 */
public class CampusTableModel extends AbstractTableModel {   /// Per modificar !!!!!!!!!!!!!!!!!!!!!!!!!

    private final String[] columnNames = {"nomCampus", "ubicacio"};

    private String[][] data;

    public CampusTableModel() {
        
        int i = 0;
        
        int totalCampus = 0;

        for (int j = 0; j < ControladorPrincipal.getUniversitatActual().getCampusList().size(); j++) {

            if (ControladorPrincipal.getUniversitatActual().getCampusList().get(j) instanceof Campus) {
                totalCampus++;
            }           
        }

        System.out.println(totalCampus);
        
        data = new String[totalCampus][2];
        
        for (Campus campus : ControladorPrincipal.getUniversitatActual().getCampusList()) {
            if (campus instanceof Campus) {
                data[i][0] = ((Campus)campus).getNomCampus();
                data[i][1] = ((Campus)campus).getUbicacio();
                i++;
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return data[0].length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

}
