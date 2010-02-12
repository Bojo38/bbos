/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Views.Team;

import bbos.General.Model.mTeam;
import bbos.General.Model.mTeamRoster;
import bbos.mBBoS;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.AbstractCellEditor;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.Vector;


/**
 *
 * @author frederic
 */
public class jtcRendererSettings extends AbstractCellEditor implements TableCellEditor, TableCellRenderer
{

    private JLabel label;
    private JTextField text;
    private JComboBox combo;
    private mTeam _team;
    jpnTeam _panel;

    public jtcRendererSettings(mTeam team, jpnTeam panel)
    {
        super();
        _team=team;
        _panel=panel;
    }

    public Component getTableCellRendererComponent(
            JTable table, Object objet,
            boolean isSelected, boolean hasFocus,
            int row, int col)
    {
        if ((row == 0) || (row == 3) || (row == 4) || (col == 0))
        {
            label = new JLabel();
            label.setOpaque(true);
            if (Integer.class.equals(objet.getClass()))
            {
                label.setText(((Integer) objet).toString());
            }
            if (String.class.equals(objet.getClass()))
            {
                label.setText((String) objet);
            }
            if (col == 1)
            {
                label.setHorizontalAlignment(JLabel.LEADING);
            }
            else
            {
                label.setHorizontalAlignment(JLabel.TRAILING);
            }

            return label;
        }

        if ((row == 1) && (col == 1))
        {
            text = new JTextField();
            /*if (Integer.class.equals(objet.getClass()))
            {
            text.setText(((Integer) objet).toString());
            }*/
            if (String.class.equals(objet.getClass()))
            {
                text.setText((String) objet);
            }
            text.setHorizontalAlignment(JLabel.LEADING);

            return text;
        }

        if ((row == 2) && (col == 1))
        {
            String[] teams;
            Vector roster_list = mBBoS.getSingleton().getTeamTypes();
            teams = new String[roster_list.size()];
            for (int i = 0; i < roster_list.size(); i++)
            {
                String tmp = ((mTeamRoster) roster_list.get(i)).getName();
                teams[i] = tmp;
            }
            combo = new JComboBox(teams);
            combo.setEnabled(true);
            if (String.class.equals(objet.getClass()))
            {
                combo.setSelectedItem(objet);
            }
            return combo;
        }

        return new JLabel();
    }

    /**
     * Unused in FULL_VIEW mode
     * @param table
     * @param value
     * @param isSelected
     * @param row
     * @param col
     * @return
     */
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col)
    {
        if ((row == 2) && (col == 1))
        {
            String[] teams;
            Vector roster_list = mBBoS.getSingleton().getTeamTypes();
            teams = new String[roster_list.size()];
            for (int i = 0; i < roster_list.size(); i++)
            {
                String tmp = ((mTeamRoster) roster_list.get(i)).getName();
                teams[i] = tmp;
            }
            combo = new JComboBox(teams);

            combo.addActionListener(new laTeamRoster(combo, _team,_panel));
            _panel.repaint();
            return combo;
        }
        
        if ((row == 1) && (col == 1))
        {
            
            text = new JTextField(_team.getName());
            text.addActionListener(new laTeamName(text,_team,_panel));
            text.addFocusListener(new laTeamName(text,_team,_panel));
            
            _panel.repaint();
            return text;
        }
        _panel.repaint();
        
        return null;
    }

    //inherited
    public Object getCellEditorValue()
    {
        return null;
    }
}

