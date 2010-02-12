/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Model;

/**
 *
 * @author frederic
 */
public class mAction
{

    String _data;
    String _actionName;
    int _actionTypeId;
    int _id;
    int _opponentId;
    String _opponentName;
    int _opponentNumber;
    String _playerName;
    int _playerId;
    int _playerNumber;
    int _teamId;
    int _turn;
    
    /*
    1  	Pass
    2 	Catch
    3 	Touchdown
    4 	Casualty
    5 	Aggression
    6 	Interception
    7 	MVP
    8 	Public
     */
    public static final int ACTION_PASS = 1;
    public static final int ACTION_CATCH = 2;
    public static final int ACTION_TOUCHDOWN = 3;
    public static final int ACTION_CASUALTY = 4;
    public static final int ACTION_AGGRESSION = 5;
    public static final int ACTION_INTERCEPTION = 6;
    public static final int ACTION_MVP = 7;
    public static final int ACTION_PUBLIC = 8;
    public static final int ACTION_SENT_OFF = 9;

    public mAction(webbos.webbos.Action action)
    {
        _data = action.getData();
        _actionName = action.getActionName();
        _actionTypeId = action.getActionType();
        _id = action.getId();
        _opponentId = action.getOpponentPlayerId();
        _opponentName = action.getOpponentPlayerName();
        _opponentNumber = action.getOpponentPlayerNumber();
        _playerName = action.getPlayerName();
        _playerId = action.getPlayerId();
        _playerNumber = action.getPlayerNumber();
        _teamId = action.getTeamId();
        _turn = action.getTurn();
    }

    public int getTurn()
    {
        return _turn;
    }

    public String getActionDescription()
    {
        String tmp = "";
        switch (_actionTypeId)
        {
            case ACTION_PASS:
                tmp = _playerName + "#(" + Integer.toString(_playerNumber) + ") ";
                tmp = tmp + " pass the ball.";
                break;
            case ACTION_CATCH:
                tmp = _playerName + "#(" + Integer.toString(_playerNumber) + ") ";
                tmp = tmp + " catch the ball";
                break;
            case ACTION_TOUCHDOWN:
                tmp = _playerName + "#(" + Integer.toString(_playerNumber) + ") ";
                tmp = tmp + " scores touchdown";
                break;
            case ACTION_CASUALTY:
                tmp = _playerName + "#(" + Integer.toString(_playerNumber) + ") ";
                tmp = tmp + " hurt opponent player " + _opponentName + " (" + Integer.toString(_opponentNumber) + "),";
                tmp = tmp + " injury is " + _data + ".";
                break;
            case ACTION_AGGRESSION:
                tmp = _playerName + "#(" + Integer.toString(_playerNumber) + ") ";
                tmp = tmp + " fouled opponent player " + _opponentName + " (" + Integer.toString(_opponentNumber) + "),";
                tmp = tmp + " injury is " + _data + ".";
                break;
            case ACTION_INTERCEPTION:
                tmp = _playerName + "#(" + Integer.toString(_playerNumber) + ") ";
                tmp = tmp + " intercepts pass from " + _opponentName + " (" + Integer.toString(_opponentNumber) + ").";
                break;
            case ACTION_MVP:
                tmp = _playerName + "#(" + Integer.toString(_playerNumber) + ") ";
                tmp = tmp + " has received MVP title.";
                break;
            case ACTION_PUBLIC:
                tmp = "Audience hurts opponent player " + _opponentName + " (" + Integer.toString(_opponentNumber) + "),";
                tmp = tmp + " injury is " + _data + ".";
                break;
            case ACTION_SENT_OFF:
                tmp = _playerName + "#(" + Integer.toString(_playerNumber) + ") ";
                tmp = tmp + " has been sent off.";
                break;
        }
        return tmp;
    }
    
    public int getTeamId()
    {
        return _teamId;
    }
}

