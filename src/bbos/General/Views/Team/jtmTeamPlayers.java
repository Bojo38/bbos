/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Views.Team;

import bbos.Match.Model.Competences.dCompetence;
import bbos.General.Model.mPlayer;
import javax.swing.table.AbstractTableModel;
import bbos.General.Model.mTeam;

/**
 *
 * @author frederic
 */
public class jtmTeamPlayers extends AbstractTableModel
{

    public static int FULL_VIEW = 1;
    public static int NEW_VIEW = 2;
    public static int EX_VIEW = 3;
    
    mTeam _team;
    int _view;

    public jtmTeamPlayers(mTeam team, int view)
    {
        _team = team;
        _view = view;
    }

    public int getColumnCount()
    {
        if (_view == NEW_VIEW)
        {
            return 9;
        }
        if (_view == FULL_VIEW)
        {
            return 18;
        }
        if (_view == EX_VIEW)
        {
            return 19;
        }
        return 0;
    }

    public int getRowCount()
    {
        if (_view == EX_VIEW)
        {
            return _team.getExPlayers().size();
        }
        else
        {
            return 16;
        }
    }

    public String getColumnName(int col)
    {
        switch (col)
        {
            case 0:
                return "N";
            case 1:
                return "Name";
            case 2:
                return "Position";
            case 3:
                return "M";
            case 4:
                return "St";
            case 5:
                return "Ag";
            case 6:
                return "Ar";
            case 8:
                if ((_view == FULL_VIEW) || (_view == EX_VIEW))
                {
                    return "Abilities";
                }
                if (_view == NEW_VIEW)
                {
                    return "Cost";
                }
            case 7:
                if ((_view == FULL_VIEW) || (_view == EX_VIEW))
                {
                    return "Base abilities";
                }
                if (_view == NEW_VIEW)
                {
                    return "Abilities";
                }
            case 9:
                return "Cost";
            case 10:
                return "Miss next match";
            case 11:
                return "Persistant injuries";
            case 12:
                return "Completions";
            case 13:
                return "Casualties";
            case 14:
                return "Interceptions";
            case 15:
                return "Touchdowns";
            case 16:
                return "MVP";
            case 17:
                return "XP";
            case 18:
                return "Status";
            }

        return "";
    }

    public Object getValueAt(int row, int col)
    {
        mPlayer player;
        if (_view == EX_VIEW)
        {
            if (row <_team.getExPlayers().size())
            {
                player = (mPlayer) _team.getExPlayers().get(row);
            }
            else
            {
                player=null;
            }
        }
        else
        {
            player = (mPlayer) _team.getPlayer(row + 1);
        }

        switch (col)
        {
            case 0:
                if (_view == EX_VIEW)
                {
                    if (player != null)
                    {
                        return player.getNumber();
                    }
                }
                else
                {
                    return row + 1;
                }
            case 1:
                if (player != null)
                {
                    return player.getName();
                }
            case 2:
                if (player != null)
                {
                    return player.getPosition();
                }
            case 3:
                if (player != null)
                {
                    return player.getMovement();
                }
            case 4:
                if (player != null)
                {
                    return player.getStrength();

                }
            case 5:
                if (player != null)
                {
                    return player.getAgility();
                }
            case 6:
                if (player != null)
                {
                    return player.getArmor();
                }
            case 7:
                if (player != null)
                {
                    String comps = "";
                    for (int i = 0; i < player.getPlayerTypeCompetences().size(); i++)
                    {
                        if (comps.equals(""))
                        {
                            comps = ((dCompetence) (player.getPlayerTypeCompetences().get(i))).getName();
                        }
                        else
                        {
                            comps = comps + ", " + ((dCompetence) (player.getPlayerTypeCompetences().get(i))).getName();
                        }
                    }
                    return comps;
                }

            case 8:
                if ((_view == FULL_VIEW) || (_view == EX_VIEW))
                {
                    if (player != null)
                    {
                        String comps = "";
                        for (int i = 0; i < player.getCompetences().size(); i++)
                        {
                            if (comps.equals(""))
                            {
                                comps = ((dCompetence) (player.getCompetences().get(i))).getName();
                            }
                            else
                            {
                                comps = comps + ", " + ((dCompetence) (player.getCompetences().get(i))).getName();
                            }
                        }
                        return comps;
                    }
                }
                if (_view == NEW_VIEW)
                {
                    if (player != null)
                    {
                        return player.getCost();
                    }
                }
            case 9:
                if (player != null)
                {
                    return player.getCost();
                }
            case 10:
                if (player != null)
                {
                    return player.MissNewMatch();
                }
            case 11:
                if (player != null)
                {
                    return player.getPersistant();
                }
            case 12:
                if (player != null)
                {
                    return player.getCompletions();
                }
            case 13:
                if (player != null)
                {
                    return player.getCasualties();
                }
            case 14:
                if (player != null)
                {
                    return player.getInterceptions();
                }
            case 15:
                if (player != null)
                {
                    return player.getTouchdowns();
                }
            case 16:
                if (player != null)
                {
                    return player.getMVP();
                }
            case 17:
                if (player != null)
                {
                    return player.getExperiencePoints();
                }
            case 18:
                if (player != null)
                {
                    if (player.getStatus() == mPlayer.STATUS_ACTIVE)
                    {
                        return "Active";
                    }
                    if (player.getStatus() == mPlayer.STATUS_RETIRED)
                    {
                        return "Retired";
                    }
                    if (player.getStatus() == mPlayer.STATUS_DEAD)
                    {
                        return "Dead";
                    }
                }
            }

        return "";
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
        if (_view == NEW_VIEW)
        {
            if ((col == 1) || (col == 2))
            {
                return true;
            }
        }
        return false;
    }
}
