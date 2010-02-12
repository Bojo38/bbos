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
import bbos.Match.Model.dMatch;
import bbos.*;
import bbos.General.Model.mMatch;
import bbos.General.Model.mTeam;
import bbos.General.Views.jdgProgressBar;
import bbos.Match.Automat.dsWholeMatch;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiTeam;

/**
 *
 * @author moi
 */
public class tMatch extends Thread {

    rmiMatch _model;
    rmiTeam _leftTeam;
    rmiTeam _rightTeam;
    dsWholeMatch _matchSequence;
    public boolean _stopMatch = false;
    boolean _server = false;
    String _IP = "";
    int _port = 8080;
    int _timeout;
    int _matchId;
    int _myTeamId;
    boolean _isChallenger;
    boolean _connected = false;
    boolean _standalone=false;
    tNetworkConnexion _connexion;

    /** Creates a new instance of tMatch */
    protected tMatch(boolean standalone,boolean server, String IP, int port, mTeam challenger, mTeam opponent, mMatch match, int myTeamId, int timeout) {
        _port = port;
        _IP = IP;
        _server = server;
        _standalone=standalone;
        _matchId = match.getId();
        _myTeamId = myTeamId;
        _timeout = timeout * 1000;
        _connexion = tNetworkConnexion.createConnextion(standalone,_server, _IP, _timeout, new dMatch(challenger, opponent), _myTeamId);
        _model = _connexion.getMatchModel();
        _rightTeam = _connexion.getRightTeam();
        _leftTeam = _connexion.getLeftTeam();
        _isChallenger = (myTeamId == challenger.getId());
        _matchSequence = new dsWholeMatch(_model, _leftTeam, _rightTeam, _isChallenger,_standalone);
    }
    protected static tMatch _singleton;

    public static tMatch createMatch(boolean standalone,boolean server, String IP, int port, mTeam challenger, mTeam opponent, mMatch match, int myTeamId, int timeout) {
        _singleton = new tMatch(standalone,
                server,
                IP,
                port,
                challenger,
                opponent,
                match,
                myTeamId,
                timeout);
        return _singleton;
    }

    public static tMatch getMatch() {
        return _singleton;
    }

    public void run() {

        _connexion.start();

        jdgProgressBar.setProgressValue(1, "OK");
        /**
         * Restore match data
         */
        
        while (!_stopMatch) {
            _matchSequence.nextStep();

            try {
                sleep(100);
            } catch (InterruptedException e) {

            }

        /**
         * Save data to Database
         */
        // mBBoS.getSingleton().saveMatchData(_matchId, _model);
                /*
         * Synchronize Display with data
         */
        //_model.refresh();
        }
    }
}
