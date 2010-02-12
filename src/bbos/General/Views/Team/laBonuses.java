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
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author frederic
 */
public class laBonuses implements ActionListener, FocusListener {

    JTextField _text;
    JCheckBox _check;
    mTeam _team;
    String _objet;
    jpnTeam _panel;

    public laBonuses(JTextField text, mTeam team, String objet, jpnTeam panel) {
        _team = team;
        _text = text;
        _objet = objet;
        _panel = panel;
    }

    public laBonuses(JCheckBox check, mTeam team, String objet, jpnTeam panel) {
        _team = team;
        _check = check;
        _objet = objet;
        _panel = panel;
    }

    public void actionPerformed(ActionEvent arg0) {
        if (_objet.equals("Apo")) {

            _team.setApothecary(_check.isSelected());

        } else {
            String text = _text.getText();
            try {

                int value = Integer.parseInt(text);
                if (_objet.equals("Reroll")) {
                    _team.setReroll(value);
                }
                if (_objet.equals("Assist")) {
                    _team.setAssist(value);
                }
                if (_objet.equals("Pompom")) {
                    _team.setPomPom(value);
                }
                if (_objet.equals("Pop")) {
                    _team.setPopFactor(value);
                }
            } catch (NumberFormatException e) {

            }

        }
        _panel.repaint();
    }

    public void focusGained(FocusEvent e) {

    }

    public void focusLost(FocusEvent e) {
        if (_objet.equals("Apo")) {

            _team.setApothecary(_check.isSelected());

        } else {
            String text = _text.getText();
            try {

                int value = Integer.parseInt(text);
                if (_objet.equals("Reroll")) {
                    _team.setReroll(value);
                }
                if (_objet.equals("Assist")) {
                    _team.setAssist(value);
                }
                if (_objet.equals("Pompom")) {
                    _team.setPomPom(value);
                }
                if (_objet.equals("Pop")) {
                    _team.setPopFactor(value);
                }
            } catch (NumberFormatException ex) {

            }

        }
        _panel.repaint();
    }
}
