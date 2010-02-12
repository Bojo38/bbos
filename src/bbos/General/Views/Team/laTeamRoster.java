/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.General.Views.Team;

import bbos.General.Model.mTeam;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author frederic
 */
public 
class laTeamRoster implements ActionListener
{
    JComboBox _combo;
    mTeam _team;
    JPanel _panel;
    
    public laTeamRoster( JComboBox combo, mTeam team,JPanel panel)
    {
        _team=team;
        _combo=combo;
        _panel=panel;
    }
    
    public void actionPerformed(ActionEvent arg0)
    {
            int index = _combo.getSelectedIndex();
            _team.setTeamRoster(index);
            _panel.repaint();
            
    }
}
