/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.General.Views.Rosters;

import javax.swing.table.AbstractTableModel;
import bbos.Match.Model.Competences.dCompetence;
import bbos.Match.Model.Competences.dCompetenceType;
import bbos.General.Model.*;

/**
 *
 * @author frederic
 */
public class jtmPlayerTypes extends AbstractTableModel
{
    mTeamRoster _teamRoster;
    
    public jtmPlayerTypes(mTeamRoster roster)
    {
        _teamRoster=roster;
    }

    public int getColumnCount() {
        return 10;
    }

    public int getRowCount() {
        return _teamRoster.getRegularPlayersNumber();
    }

    public String getColumnName(int col) {
        switch (col)
        {
            case 0:
                return "Qty";
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
            case 8:
                return "Normal";
            case 9:
                return "Double";
        }
        return "";
    }

    public Object getValueAt(int row, int col) 
    {
        mPlayerType player=(mPlayerType)_teamRoster.getPlayerType(row);
         switch (col)
        {
            case 0:
                return "0-"+Integer.toString(player.getLimit());
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
                String comps="";
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
            case 8:
                String normal="";
                for (int i=0; i<player.getSimpleRoll().size(); i++)
                {
                    if (normal.equals(""))
                    {
                        normal=((dCompetenceType)(player.getSimpleRoll().get(i))).getAccronym();
                    }
                    else
                    {
                        normal=normal+", "+((dCompetenceType)(player.getSimpleRoll().get(i))).getAccronym();
                    }
                }
                return normal;
            case 9:
                String ldouble="";
               for (int i=0; i<player.getDoubleRoll().size(); i++)
                {
                    if (ldouble.equals(""))
                    {
                        ldouble=((dCompetenceType)(player.getDoubleRoll().get(i))).getAccronym();
                    }
                    else
                    {
                        ldouble=ldouble+", "+((dCompetenceType)(player.getDoubleRoll().get(i))).getAccronym();
                    }
                }
                return ldouble;
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
