/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Views.Team;

import bbos.General.Model.mPlayer;
import bbos.General.Model.mTeam;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author frederic
 */
public class laPlayers implements ActionListener, FocusListener {

    JTextField _text;
    JComboBox _combo;
    mTeam _team;
    String _objet;
    JPanel _panel;
    int _row;

    public laPlayers(JTextField text, mTeam team, String objet, JPanel panel, int row) {
        _team = team;
        _text = text;
        _objet = objet;
        _panel = panel;
        _row = row;
    }

    public laPlayers(JComboBox combo, mTeam team, String objet, JPanel panel, int row) {
        _team = team;
        _combo = combo;
        _objet = objet;
        _panel = panel;
        _row = row;
    }

    public void actionPerformed(ActionEvent arg0) {
        if (_objet.equals("Player")) {
            _team.setPlayerType(_row, (String) _combo.getSelectedItem());
        } else {
            String text = _text.getText();
            mPlayer player = _team.getPlayer(_row + 1);
            if (player != null) {
                player.setName(_text.getText());
            }
        }
        _panel.repaint();
    }

    public void focusGained(FocusEvent e) {

    }

    public void focusLost(FocusEvent e) {
        if (_objet.equals("Player")) {
            _team.setPlayerType(_row, (String) _combo.getSelectedItem());
        } else {
            String text = _text.getText();
            mPlayer player = _team.getPlayer(_row + 1);
            if (player != null) {
                player.setName(_text.getText());
            }
        }

        _panel.repaint();
    }
}
