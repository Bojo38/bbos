/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Views.Match;

import bbos.General.Model.mAction;
import bbos.General.Model.mMatch;
import bbos.General.Model.mTeam;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

class turnAction
{

    public String myText;
    public String oppText;
    
    public turnAction(String my, String opp)
    {
        myText=my;
        oppText=opp;
    }
}

/**
 *
 * @author frederic
 */
public class jtmActions extends AbstractTableModel
{

    mMatch _match;
    mTeam _team;
    HashMap _actions;

    public jtmActions(mMatch match, int team_id)
    {
        _match = match;
        _actions = new HashMap();


        for (int i = 0; i < match.getActions().size(); i++)
        {
            mAction action = (mAction) match.getActions().get(i);
            int turn = action.getTurn();
            
            String myActionDone = "";
            String oppActionDone = "";

            if (_actions.get(turn) != null)
            {
                myActionDone = ((turnAction) _actions.get(turn)).myText+"\n";
                oppActionDone = ((turnAction) _actions.get(turn)).oppText+"\n";
            }
            else
            {
                myActionDone ="";
                oppActionDone = "";
            }
            
            if (action.getTeamId() == team_id)
            {
                myActionDone = myActionDone + action.getActionDescription();
            }
            else
            {
                oppActionDone = oppActionDone + action.getActionDescription();
            }
            _actions.put(turn, new turnAction(myActionDone,oppActionDone));

        }
    }

    public int getColumnCount()
    {
        return 3;
    }

    public int getRowCount()
    {
        return _actions.size();
    }

    public String getColumnName(int col)
    {
        switch (col)
        {
            case 0:
                return "My Actions";
            case 1:
                return "Turn";
            case 2:
                return "Opponent";
        }
        return "";
    }

    public Object getValueAt(int row, int col)
    {
        if (col == 0)
        {
            return ((turnAction) _actions.get(_actions.keySet().toArray()[row])).myText;
        }else if (col == 2)
        {
            return ((turnAction) _actions.get(_actions.keySet().toArray()[row])).oppText;
        }
        else
        {
            return _actions.keySet().toArray()[row];
        }
    }

    public Class getColumnClass(int c)
    {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col)
    {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return false;
    }
}
