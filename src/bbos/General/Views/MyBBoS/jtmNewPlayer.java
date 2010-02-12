/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.General.Views.MyBBoS;

import bbos.Match.Model.Competences.dCompetence;
import bbos.General.Model.mPlayer;
import bbos.General.Model.mTeam;
import bbos.Tools.sdLang;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author root
 */
public class jtmNewPlayer extends AbstractTableModel{

    mPlayer _player;

    public jtmNewPlayer(mPlayer player) {
        _player = player;
    }

    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        return 1;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Position";
            case 1:
                return "M";
            case 2:
                return "St";
            case 3:
                return "Ag";
            case 4:
                return "Ar";
            case 5:
                return "Competences";
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        if (_player != null) {
            switch (col) {
                case 0:
                      return sdLang.getSingleton().getResource(_player.getPlayerType().getPosition());
                case 1:
                      return _player.getPlayerType().getMovement();
                case 2:
                        return _player.getPlayerType().getStrength();
                case 3:
                        return _player.getPlayerType().getAgility();
                case 4 :
                        return _player.getPlayerType().getArmor();
                case 5:
                    String sCompetences="";
                    if (_player.getPlayerTypeCompetences().size()>0)
                    {
                        sCompetences=sdLang.getSingleton().getResource(((dCompetence)_player.getPlayerTypeCompetences().get(0)).getName());
                    }
                    
                    for (int i=1; i<_player.getPlayerTypeCompetences().size(); i++)
                    {
                        dCompetence comp=(dCompetence)_player.getPlayerTypeCompetences().get(i);
                        sCompetences=sCompetences+", "+sdLang.getSingleton().getResource(comp.getName());
                    }
                    return sCompetences;
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
        return false;
    }
}
