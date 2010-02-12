/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.General.Views.Rosters;

import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Color;

/**
 *
 * @author frederic
 */
public class jtcRenderer extends JLabel implements TableCellRenderer {

     public jtcRenderer() {
        super();
        setOpaque(true); 
    }

    public Component getTableCellRendererComponent(
                            JTable table, Object objet,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {

        if ((row %2)==0)
        {
            this.setBackground(Color.white);
        }
        else
        {
            this.setBackground(Color.LIGHT_GRAY);
        }

        if (Integer.class.equals(objet.getClass()))
        {
            this.setText(((Integer)objet).toString());
            this.setToolTipText(((Integer)objet).toString());
        }
        else
        {
            this.setText((String)objet);
            this.setToolTipText((String)objet);
        }
        
        this.setHorizontalAlignment(JLabel.CENTER);
        
        return this;
    }
}
