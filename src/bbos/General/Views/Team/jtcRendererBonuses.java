/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Views.Team;

import bbos.General.Model.mTeam;
import bbos.General.Model.mTeamRoster;
import bbos.mBBoS;
import java.awt.Color;
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
import javax.swing.JCheckBox;


/**
 *
 * @author frederic
 */
public class jtcRendererBonuses extends AbstractCellEditor implements TableCellEditor, TableCellRenderer
{
    private JLabel label;
    private JTextField text;
    private JCheckBox check;

    private mTeam _team;
    private jpnTeam _panel;
    
    public jtcRendererBonuses(mTeam team,jpnTeam panel)
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
        if ((col == 0) || (col == 2) || (col == 3) || (col == 4)|| (col == 5))
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
            if (col == 0)
            {
                label.setHorizontalAlignment(JLabel.TRAILING);
            }
            else if (col == 5)
            {
                label.setHorizontalAlignment(JLabel.TRAILING);
            }
            else
            {
                label.setHorizontalAlignment(JLabel.CENTER);
            }

            return label;
        }

        if (col == 1)
        {
            if (row==3)
            {
                check=new JCheckBox();
                if (!_team.getTeamType().isApothecary()) {
                    check.setEnabled(false);
                }
             check.setSelected(_team.hasApothecary());
             return check;
            }
            
            else
            {
                text = new JTextField();
                if (String.class.equals(objet.getClass()))
                {
                    text.setText((String) objet);
                }
                text.setHorizontalAlignment(JLabel.CENTER);
            }
            return text;
        }

        return new JLabel();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col)
    {
        if (col == 1) 
        {
            if (row == 0)
            {
                text=new JTextField();
                text.setText(Integer.toString(_team.getReroll()));
                text.addActionListener(new laBonuses(text, _team,"Reroll",_panel));
                text.addFocusListener(new laBonuses(text, _team,"Reroll",_panel));
            }
            if (row == 1)
            {
                text=new JTextField();
                text.setText(Integer.toString(_team.getAssists()));
                text.addActionListener(new laBonuses(text, _team,"Assist",_panel));
                text.addFocusListener(new laBonuses(text, _team,"Assist",_panel));
            }
            if (row == 2)
            {
                text=new JTextField();
                text.setText(Integer.toString(_team.getPomspoms()));
                text.addActionListener(new laBonuses(text, _team,"Pompom",_panel));
                text.addFocusListener(new laBonuses(text, _team,"Pompom",_panel));
            }
            if (row == 4)
            {
                text=new JTextField();
                text.setText(Integer.toString(_team.getPopFactor()));
                text.addActionListener(new laBonuses(text, _team,"Pop",_panel));
                text.addFocusListener(new laBonuses(text, _team,"Pop",_panel));
            }
            if (row == 3)
            {
                check=new JCheckBox();
                check.setSelected(_team.hasApothecary());
                check.addActionListener(new laBonuses(check, _team,"Apo",_panel));
                check.addFocusListener(new laBonuses(check, _team,"Apo",_panel));
                return check;
            }
            _panel.repaint();
            return text;
        }
        
        return null;
    }

    //inherited
    public Object getCellEditorValue()
    {
        return null;
    }
}

