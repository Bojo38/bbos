/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.General.Views.Team;

import bbos.General.Model.mTeam;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;


/**
 *
 * @author frederic
 */
public 
class laTeamName implements ActionListener, FocusListener
{
    JTextField _text;
    mTeam _team;
    jpnTeam _panel;
    
    public laTeamName( JTextField text, mTeam team,jpnTeam panel)
    {
        _team=team;
        _text=text;
        _panel=panel;
    }
    
    public void actionPerformed(ActionEvent arg0)
    {
            _team.setTeamName(_text.getText());
            _panel.repaint();
    }
    
    public void focusGained(FocusEvent e) {

    }

    public void focusLost(FocusEvent e) {
         _team.setTeamName(_text.getText());
            _panel.repaint();
    }
}
