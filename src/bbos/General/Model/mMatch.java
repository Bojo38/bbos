/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Model;

import bbos.mBBoS;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import bbos.Tools.sdLang;

/**
 *
 * @author frederic
 */
public class mMatch {

    public static int STATE_CHALLENGE = 1;
    public static int STATE_IN_COURSE = 2;
    public static int STATE_DONE = 3;
    GregorianCalendar _date;
    bbos.General.Model.mLeague _league;
    bbos.General.Model.mTeam _opponent;
    int _state;
    int _my_score;
    int _opponent_score;
    int _winner;
    int _round;
    boolean _extratime;
    int _public;
    int _my_FAME;
    int _opponent_FAME;
    int _my_winnings;
    int _opponent_winnings;
    int _opponentId;
    Vector _actions;
    int _id;
    int _challengerId;
    Object _data;

    public mMatch(webbos.webbos.Match match) {
        String tmpDate = match.getDate();

        StringTokenizer st = new StringTokenizer(tmpDate);
        if (st.countTokens() == 6) {
            String year = st.nextToken();
            String month = st.nextToken();
            String day = st.nextToken();
            String hour = st.nextToken();
            String minute = st.nextToken();
            String second = st.nextToken();

            _date = new GregorianCalendar(Integer.valueOf(year),
                    Integer.valueOf(month),
                    Integer.valueOf(day),
                    Integer.valueOf(hour),
                    Integer.valueOf(minute),
                    Integer.valueOf(second));
        } else {
            _date = new GregorianCalendar();
        }
        //_date = match.getDate();
        _state = match.getState();
        _my_score = match.getMyScore();
        _opponent_score = match.getOpponentScore();
        _winner = match.getWinnerId();
        _round = match.getRound();
        _extratime = match.isExtraTime();
        _public = match.getPublic();
        _my_FAME = match.getMyFAME();
        _opponent_FAME = match.getOpponentFAME();
        _my_winnings = match.getMyWinnings();
        _opponent_winnings = match.getOpponentWinnings();
        _opponentId = match.getOpponentId();
        _actions = new Vector();
        for (int i = 0; i < match.getActions().getAction().size(); i++) {
            _actions.add(new bbos.General.Model.mAction((webbos.webbos.Action) match.getActions().getAction().get(i)));
        }
        _id = match.getId();

        _opponent = null;
        _challengerId = match.getChallengerId();

        _data = null;
        
        if (match.getData() != null) {
            if (match.getData().length != 0) {
                try {
                    ByteArrayInputStream is = new ByteArrayInputStream(match.getData());
                    ObjectInputStream reader = new ObjectInputStream(is);
                    _data = reader.readObject();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }


    }

    public mMatch(int team1_id, int team2_id, int matchId) {
        _date = new GregorianCalendar();
        _state = 1;
        _opponentId = team2_id;
        _challengerId = team1_id;
        _id = matchId;
    }

    public int getId() {
        return _id;
    }

    public int getState() {
        return _state;
    }

    public int getWinnerId() {
        return _winner;
    }

    public int getOpponentId() {
        return _opponentId;
    }

    public String getScore() {
        return Integer.toString(_my_score) + " - " + Integer.toString(_opponent_score);
    }

    public bbos.General.Model.mTeam getOppponent() {
        if (_opponent == null) {
            _opponent = mBBoS.getSingleton().getTeam(_opponentId);
        }
        return _opponent;
    }

    public Vector getActions() {
        return _actions;
    }

    public int getMyScore() {
        return _my_score;
    }

    public int getOppScore() {
        return _opponent_score;
    }

    public int getMyFAME() {
        return _my_FAME;
    }

    public int getOppFAME() {
        return _opponent_FAME;
    }

    public int getMyWinnings() {
        return _my_winnings;
    }

    public int getOppWinnings() {
        return _opponent_winnings;
    }

    public int getChallengerId() {
        return _challengerId;
    }

    public Object getData() {
        return _data;
    }
}
