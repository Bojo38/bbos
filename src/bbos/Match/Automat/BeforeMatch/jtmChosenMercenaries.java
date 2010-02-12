/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.Match.Automat.BeforeMatch;


import bbos.General.Views.Rosters.*;
import javax.swing.table.AbstractTableModel;
import bbos.Match.Model.Competences.dCompetence;
import bbos.General.Model.*;
import bbos.Match.Model.dPlayer;
import java.util.Vector;
import javax.swing.JButton;

/**
 *
 * @author frederic
 */
public class jtmChosenMercenaries  extends AbstractTableModel
{
    Vector _stars;
    
    public jtmChosenMercenaries(Vector stars)
    {
        _stars=stars;
    }

    public int getColumnCount() {
        return 8;
    }

    public int getRowCount() {
        return _stars.size();
    }

    public String getColumnName(int col) {
        switch (col)
        {
            case 0:
                return "Name";
            case 1:
                return "Position";
            case 2:
                return "M";
            case 3:
                return "St";
            case 4:
                return "Ag";
            case 5:
                return "Ar";
            case 6:
                return "Abilities";
            case 7:
                return "Cost";
        }
        return "";
    }

    public Object getValueAt(int row, int col) 
    {
        dPlayer player=(dPlayer)_stars.get(row);
         switch (col)
        {
            case 0:
                return player.getName();
            case 1:
                return player.getPosition();
            case 2:
                return player.getMovement();
            case 3:
                return player.getStrength();
            case 4:
                return player.getAgility();
            case 5:
                return player.getArmor();
            case 6:
                String comps="Loner";
                for (int i=0; i<player.getCompetences().size(); i++)
                {
                    if (comps.equals(""))
                    {
                        comps=((dCompetence)(player.getCompetences().get(i))).getName();
                    }
                    else
                    {
                        comps=comps+", "+((dCompetence)(player.getCompetences().get(i))).getName();
                    }
                }
                return comps;
            case 7:
                return player.getCost();
        }
        return "";
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
            return false;
    }
}
