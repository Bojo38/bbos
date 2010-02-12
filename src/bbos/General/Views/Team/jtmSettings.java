/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.General.Views.Team;


import javax.swing.table.AbstractTableModel;
import bbos.Match.Model.Competences.dCompetence;
import bbos.Match.Model.Competences.dCompetenceType;
import bbos.General.Model.*;
import bbos.mBBoS;
import java.util.Vector;

/**
 *
 * @author frederic
 */
public class jtmSettings  extends AbstractTableModel
{
   mTeam _team;
   int _view;
    
    public jtmSettings(mTeam team,int view)
    {
       _team=team;
       _view=view;
    }

    public int getColumnCount() {
        return 2;
    }

    public int getRowCount() {
        return 5;
    }

    public String getColumnName(int col) {
        switch (col)
        {
            case 0:
                return "Desc";
            case 1:
                return "Value";
        }
        return "";
    }

    public Object getValueAt(int row, int col) 
    {
         switch (row)
        {
            case 0:
                if (col==0)
                {
                    return "Coach Name :";
                }
                else
                {
                    String result="";
                    if (_team.getCoach()==null)
                    {
                        result=mBBoS.getSingleton().getCoachName();
                    }
                    else
                    {
                        result=_team.getCoach();
                    }
                    return result;
                }
            case 1:
                if (col==0)
                {
                    return "Team name :";
                }
                else
                {
                    return _team.getName();         
                }
            case 2:
                 if (col==0)
                {
                    return "Race :";
                }
                else
                {
                     if (_team!=null)
                     {
                         if (_team.getTeamType()!=null)
                         {
                             return _team.getTeamType().getName();
                         }
                     }
                     return "";      
                }
            case 3:
                if (col==0)
                {
                    return "Treasury :";
                }
                else
                {
                    return Integer.toString(_team.getTreasury());
                }
            case 4:
                 if (col==0)
                {
                    return "Ranking :";
                }
                else
                {
                    return Integer.toString(_team.getRanking());         
                }
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
        if (jtmTeamPlayers.NEW_VIEW==_view)
        {
            if (col==0)
            {
                return false;
            }
            else
            {
                if ((row==0)||(row==3)||(row==4))
                {
                    return false;
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    

}
