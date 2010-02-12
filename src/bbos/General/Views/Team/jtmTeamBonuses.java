/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Views.Team;

import javax.swing.table.AbstractTableModel;
import bbos.General.Model.mTeam;

/**
 *
 * @author frederic
 */
public class jtmTeamBonuses extends AbstractTableModel {

    mTeam _team;
    int _view;

    public jtmTeamBonuses(mTeam team, int view) {
        _team = team;
        _view = view;
    }

    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        return 5;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Name";
            case 1:
                return "Number";
            case 2:
                return "X";
            case 3:
                return "Prices";
            case 4:
                return "=";
            case 5:
                return "Cost";
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        if (_team.getTeamType() != null) {
            switch (col) {
                case 0:
                    if (row == 0) {
                        return "Reroll : ";
                    } else if (row == 1) {
                        return "Assists : ";
                    } else if (row == 2) {
                        return "Cheerleaders : ";
                    } else if (row == 3) {
                        return "Apothecary : ";
                    } else if (row == 4) {
                        return "Popularity : ";
                    }
                case 1:
                    if (row == 0) {
                        return Integer.toString(_team.getReroll());
                    } else if (row == 1) {
                        return Integer.toString(_team.getAssists());
                    } else if (row == 2) {
                        return Integer.toString(_team.getPomspoms());
                    } else if (row == 3) {
                        if (_team.hasApothecary()) {
                            return "1";
                        } else {
                            return "0";
                        }
                    } else if (row == 4) {
                        return Integer.toString(_team.getPopFactor());
                    }
                case 2:
                    return "X";
                case 3:
                    if (row == 0) {
                        return Integer.toString(_team.getTeamType().getRerollCost());
                    } else if (row == 1) {
                        return "10000";
                    } else if (row == 2) {
                        return "10000";
                    } else if (row == 3) {
                        if (_team.getTeamType().isApothecary()) {
                            return Integer.toString(_team.getTeamType().getApothecaryCost());
                        } else {
                            return "Forbidden";
                        }
                    } else if (row == 4) {
                        return "10000";
                    }
                case 4:
                    return "=";
                case 5:
                    if (row == 0) {
                        return _team.getReroll() * _team.getTeamType().getRerollCost();
                    } else if (row == 1) {
                        return _team.getAssists() * 10000;
                    } else if (row == 2) {
                        return _team.getPomspoms() * 10000;
                    } else if (row == 3) {
                        if (_team.hasApothecary()) {
                            return _team.getTeamType().getApothecaryCost();
                        } else {
                            return 0;
                        }
                    } else if (row == 4) {
                        return _team.getPopFactor() * 10000;
                    }
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
        if (_view == jtmTeamPlayers.NEW_VIEW) {
            if (col == 1) {
                if (row == 3) {
                    if (_team.getTeamType().isApothecary()) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
