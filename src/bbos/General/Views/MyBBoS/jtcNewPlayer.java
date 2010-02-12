/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Views.MyBBoS;

import bbos.General.Model.mPlayer;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author root
 */
public class jtcNewPlayer implements TableCellRenderer {

    mPlayer _player;

    public jtcNewPlayer(mPlayer player) {
        super();
        _player = player;
    }

    public Component getTableCellRendererComponent(
            JTable table, Object objet,
            boolean isSelected, boolean hasFocus,
            int row, int col) {
        
            JLabel label = new JLabel();
            label.setOpaque(true);

            if (Integer.class.equals(objet.getClass())) {
                label.setText(((Integer) objet).toString());
            }
            if (String.class.equals(objet.getClass())) {
                label.setText((String) objet);
            }
            label.setHorizontalAlignment(JLabel.CENTER);
            return label;
    }
}
