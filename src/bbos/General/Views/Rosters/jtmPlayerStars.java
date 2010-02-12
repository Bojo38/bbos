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
public class jtmPlayerStars  extends AbstractTableModel
{
    mTeamRoster _teamRoster;
    
    public jtmPlayerStars(mTeamRoster roster)
    {
        _teamRoster=roster;
    }

    public int getColumnCount() {
        return 7;
    }

    public int getRowCount() {
        return _teamRoster.getStarPlayersNumber();
    }

    public String getColumnName(int col) {
        switch (col)
        {
            case 0:
                return "Name";
            case 1:
                return "M";
            case 2:
                return "St";
            case 3:
                return "Ag";
            case 4:
                return "Ar";
            case 5:
                return "Abilities";
            case 6:
                return "Cost";
        }
        return "";
    }

    public Object getValueAt(int row, int col) 
    {
        mPlayerType player=(mPlayerType)_teamRoster.getStarPlayer(row);
         switch (col)
        {
            case 0:
                return player.getName();
            case 1:
                return player.getMovement();
            case 2:
                return player.getStrength();
            case 3:
                return player.getAgility();
            case 4:
                return player.getArmor();
            case 5:
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
            case 6:
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
