/*
 * tMatch.java
 *
 * Created on 10 janvier 2008, 20:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match;

import bbos.Match.Automat.*;
import bbos.Match.Automat.DuringMatch.jdgMatch;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiTeam;
import java.rmi.RemoteException;

/**
 *
 * @author moi
 */
public class tDisplayMatch extends Thread {

    rmiMatch _model;
    rmiTeam _leftTeam;
    rmiTeam _rightTeam;
    boolean _isChallenger;
    boolean _displayed = false;
    boolean _standalone=false;

    public static boolean isDisplayed() {
        return _singleton._displayed;
    }

    public void resetSelections() {
        if (_window != null) {
            _window.resetSelections();
            _window.repaint();
        }
    }

    public void refresh() {
        if (_window != null) {
            try {
                if (_isChallenger) {
                    if (_model.needLeftRefresh()) {
                        _window.repaint();
                        _model.setLeftRefresh(false);
                    }
                } else {
                    if (_model.needRightRefresh()) {
                        _window.repaint();
                        _model.setRightRefresh(false);
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /** Creates a new instance of tMatch */
    protected tDisplayMatch(rmiMatch model, rmiTeam leftTeam, rmiTeam rightTeam, boolean isChallenger,boolean standalone) {
        _model = model;
        _rightTeam = rightTeam;
        _leftTeam = leftTeam;
        _isChallenger = isChallenger;
        _standalone=standalone;
    }
    protected static tDisplayMatch _singleton = null;

    public static tDisplayMatch createMatchDisplay(rmiMatch model, rmiTeam leftTeam, rmiTeam rightTeam, boolean isChallenger,boolean standalone) {
        _singleton = new tDisplayMatch(model, leftTeam, rightTeam, isChallenger,standalone);
        return _singleton;
    }

    public static tDisplayMatch getMatchDisplay() {
        return _singleton;
    }
    jdgMatch _window;

    public void run() {
        _window = new jdgMatch(_model, _leftTeam, _rightTeam, _isChallenger,_standalone);
        _displayed = true;
        _window.setVisible(true);
    }
}
