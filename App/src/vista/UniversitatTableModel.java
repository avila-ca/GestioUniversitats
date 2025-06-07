package vista;

import controlador.ControladorPrincipal;
import javax.swing.table.AbstractTableModel;
import model.Universitat;

/**
 *
 * @author fgarin
 */
public class UniversitatTableModel extends AbstractTableModel{
    
    private final String[] columnNames = {"NomUniversitat", "UbicacioSeu"};

    String[][] data = new String[ControladorPrincipal.getMAXUNIVERSITATS()][2];

    public UniversitatTableModel() {
        int i = 0;
        for (Universitat universitat : ControladorPrincipal.getUniversitats()) {
            if (universitat != null) {
                data[i][0] = universitat.getNomUniversitat();
                data[i][1] = universitat.getUbicacioSeu();
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
