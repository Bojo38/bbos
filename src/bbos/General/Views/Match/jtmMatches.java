/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Views.Match;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author frederic
 */
public class jtmMatches extends AbstractTableModel{

    Vector _matches;

    public jtmMatches(Vector matches) {
        _matches = matches;
    }

    public int getColumnCount() {
        return 1;
    }

    public int getRowCount() {
        return _matches.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Matches";
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        return _matches.get(row);
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return true;
    }
}
