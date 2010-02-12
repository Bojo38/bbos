/*
 * dTeam.java
 *
 * Created on 22 novembre 2007, 20:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model;

import bbos.*;
import bbos.General.Model.mPlayer;
import bbos.General.Model.mTeam;
import bbos.Match.Model.Inducements.dInducement;
import bbos.Match.Model.Inducements.diExtraReroll;
import bbos.Tools.bbTool;
import java.util.*;
import java.awt.*;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * This class contains all the data and methods relative to a team during a match.
 * @author Frederic
 */
public class dTeam implements rmiTeam, Serializable {

    /**
     * Name of the team
     */
    String _name;
    /**
     * Popularity factor
     */
    int _popfactor;
    /**
     * Total number of reroll
     */
    int _reroll;
    /**
     * Pompom number
     */
    int _pomPoms;
    /**
     * Number of assists
     */
    int _assists;
    /**
     * Identifier
     * 
     */
    int _id;
    /**
     * Race Identifier
     * 
     */
    int _raceId;
    /**
     * Players of teh team
     */
    Vector _players;
    /**
     * has apothecary;
     */
    boolean _apothecary;
    long _treasury;
    long _pettyCash = 0;
    boolean _pettyCashChosen = false;
    boolean _inducementsChosen = false;
    Vector _inducements;
    String _race;
    transient dMatch _match;
    boolean _isChallenger;

    public dTeam(mTeam team, boolean isChallenger, dMatch match) {
        _name = team.getName();
        _popfactor = team.getPopFactor();
        _reroll = team.getReroll();
        _pomPoms = team.getPomspoms();
        _assists = team.getAssists();
        _apothecary = team.hasApothecary();
        _id = team.getId();
        _raceId = team.getTeamType().getId();
        _isChallenger = isChallenger;
        _match = match;
        _race = team.getTeamType().getName();

        _players = new Vector();
        for (int i = 0; i < team.getPlayers().size(); i++) {
            mPlayer player = (mPlayer) team.getPlayers().values().toArray()[i];
            if (!player.MissNewMatch()) {
                _players.add(new dPlayer(player, isChallenger, _race, _match));
            }
        }
        _treasury = team.getTreasury();
        _pettyCash = 0;
        _inducements = new Vector();
    }

    public void addPlayer(dPlayer player) {
        player.setModel(_match);
        _players.add(player);
    }

    public boolean isPettyCashChosen() {
        return _pettyCashChosen;
    }

    public void setPettyCashChosen(boolean petty) {
        _pettyCashChosen = petty;
    }

    public String getRace() {
        return _race;
    }

    /**
     * Return the Cheerleaders number
     * @return cheerleaders
     */
    public int getPomspoms() throws RemoteException {
        return _pomPoms;
    }

    /**
     * Return the number of assists
     * @return assists
     */
    public int getAssists() throws RemoteException {
        return _assists;
    }

    public int getId() throws RemoteException {
        return _id;
    }

    public int getRaceId() throws RemoteException {
        return _raceId;
    }
    /**
     * FAME
     */
    int _FAME;
    /**
     * Audience
     */
    int _public;

    /**
     * Return the popuylarity factor
     * @return Popularity factor
     */
    public int getPopFactor() throws RemoteException {
        return _popfactor;
    }

    /**
     * Return the public
     * @return public
     */
    public int getPublic() throws RemoteException {
        return _public;
    }

    /**
     * Return the FAME
     * @return FAME
     */
    public int getFAME() throws RemoteException {
        return _FAME;
    }

    /**
     * Set the public value
     * @param v_public new public value
     */
    public void setPublic(int v_public) throws RemoteException {
        _public = v_public;
    }

    /**
     * Set the FAME
     * @param FAME new FAME
     */
    public void setFAME(int FAME) throws RemoteException {
        _FAME = FAME;
    }

    public long getRanking() throws RemoteException {
        int ranking = 0;

        for (int i = 0; i < _players.size(); i++) {
            ranking += ((dPlayer) _players.get(i)).getCost();
        }

        ranking += this._assists * 10000;
        ranking += this._pomPoms * 10000;
        ranking += this._popfactor * 10000;
        if (_apothecary) {
            ranking += 50000;
        }

        return ranking;
    }

    public long getMatchRanking() throws RemoteException {
        return getRanking() + _pettyCash;
    }

    public long getTreasury() throws RemoteException {
        return _treasury;
    }

    public long getPettyCash() throws RemoteException {
        return _pettyCash;
    }

    public void setTreasury(long treasury) throws RemoteException {
        _treasury = treasury;
    }

    public void setPettyCash(long petty) throws RemoteException {
        _pettyCash = petty;
    }

    public void setContent(dTeam team) {
        _FAME = team._FAME;
        _apothecary = team._apothecary;
        _assists = team._assists;
        _id = team._id;
        _name = team._name;
        _pettyCash = team._pettyCash;
        _pettyCashChosen = team._pettyCashChosen;
        _pomPoms = team._pomPoms;
        _popfactor = team._popfactor;
        _public = team._public;
        _raceId = team._raceId;
        _reroll = team._reroll;
        _treasury = team._treasury;

        for (int i = 0; i < _players.size(); i++) {
            dPlayer player = (dPlayer) _players.get(i);
            dPlayer player2 = (dPlayer) team._players.get(i);

            player.setContent(player2);
        }
    }

    public boolean areInducementsChosen() throws RemoteException {
        return _inducementsChosen;
    }

    public void setInducementsChosen(boolean chosen) throws RemoteException {
        _inducementsChosen = chosen;
    }

    /**
     * Returns a reference to the players list
     * @return Players lis
     */
    public Vector getPlayers() {
        return _players;
    }

    public int getPlayersNumber() {
        return _players.size();
    }

    public dPlayer getPlayerCopy(int i) {
        dPlayer player = (dPlayer) _players.get(i);
        dPlayer clone = new dPlayer(player);
        clone.setModel(null);
        return clone;
    }

    public dPlayer getPlayer(int i) {
        return (dPlayer) _players.get(i);
    }

    public void addInducement(dInducement inducement) {
        _inducements.add(inducement);
    }

    public Vector getInducements() {
        return _inducements;
    }

    /**
     * This function uses inducement to build team
     */
    public void buildTeam() {
        for (int i = 0; i < _inducements.size(); i++) {
            dInducement induc = (dInducement) _inducements.get(i);
            /*if (induc instanceof diMercenary) {
            _players.add(((diMercenary) induc).getPlayer());
            }
            if (induc instanceof diStarPlayer) {
            _players.add(((diStarPlayer) induc).getPlayer());
            }*/

            if (induc instanceof diExtraReroll) {
                _reroll++;
            }

            _rerollLeft = _reroll;
        }
    }

    /*public void setPlayers(Vector players) {
    _players = (Vector) players.clone();
    }*/
    public boolean hasApothecary() {
        return _apothecary;
    }
    /**
     * Current score
     */
    int _score;
    /**
     * Reroll left
     */
    int _rerollLeft;
    /**
     * Current turn value
     */
    int _turn;

    public int getRerollLeft() {
        return _rerollLeft;
    }

    public int getScore() {
        return _score;
    }

    public int getTurn() {
        return _turn;
    }

    public int getPlayerNumber(int X, int Y) {
        int nb = -1;

        for (int i = 0; i < _players.size(); i++) {
            dPlayer player = (dPlayer) _players.get(i);
            if ((player.getX() == X) && (player.getY() == Y)) {
                nb = i;
                break;
            }
        }

        return nb;
    }
    boolean _playersOnThePitch = false;

    public boolean arePlayersOnThePitch() {
        return _playersOnThePitch;
    }

    public void setPlayersOnThePitch(boolean value) {
        _playersOnThePitch = value;
    }

    public String getName() {
        return _name;
    }

    /**
     * Returns if the team has been set on the pitch according to the rules
     * if the return value is 0 : it is correct
     * if 1 : Player number on the pitch is not the right one
     * if 2 : Too many players on the sides
     * if 3 : Not enough players on the scrimmage line
     * @arg side 1: left, 2 right
     * @return The status of the team when setting up players
     */
    public int isSetOnThePitch(int side) {
        int i;

        int players_on_the_pitch = 0;
        int players_available = 0;
        int players_scrimmage = 0;
        int players_sideTop = 0;
        int players_sidebottom = 0;

        for (i = 0; i < _players.size(); i++) {
            dPlayer player = (dPlayer) _players.get(i);
            if ((player.getState() == dPlayer.C_STATE_NO_ZONE) ||
                    (player.getState() == dPlayer.C_STATE_OK) ||
                    (player.getState() == dPlayer.C_STATE_PRONE) ||
                    (player.getState() == dPlayer.C_STATE_ROOTED)) {
                players_available++;
            }
        }

        /*
         * Compute Maximum players on the pitch
         */
        for (i = 0; i < _players.size(); i++) {
            dPlayer player = (dPlayer) _players.get(i);

            if (player.isOnTheHalfPitch(side)) {
                players_on_the_pitch++;
            }
            if (player.isOnScrimmage(side)) {
                players_scrimmage++;
            }
            if (player.isOnSideTop(side)) {
                players_sideTop++;
            }
            if (player.isOnSideBottom(side)) {
                players_sidebottom++;
            }
        }
        if ((players_sideTop > 2) || (players_sidebottom > 2)) {
            return 2;
        }
        if (players_scrimmage < Math.min(3, players_available)) {
            return 3;
        }
        if ((players_on_the_pitch < Math.min(11, players_available)) || (players_on_the_pitch > 11)) {
            return 1;
        }
        return 0;
    }

    /**
     * Returns if there is a player on this square.
     * @param square
     * @return is there a player or not
     */
    public boolean isAPlayer(dSquare square) {
        boolean found = false;


        /*
         * Boucle sur les joueurs
         */
        for (short i = 0; i < _players.size(); i++) {

            /*
             * Recherche en X
             */
            if (((dPlayer) _players.get(i)).getX() == square.getX()) {
                /*
                 * Recherche en Y
                 */
                if (((dPlayer) _players.get(i)).getY() == square.getY()) {
                    found = true;
                }
            }
        }


        return found;
    }

    public boolean isAPlayerWithTackleZone(dSquare s) {
        boolean found = false;
        /*
         * Boucle sur les joueurs
         */
        for (short i = 0; i < _players.size(); i++) {

            dPlayer player = (dPlayer) _players.get(i);
            /*
             * Recherche en X
             */
            if (player.getX() == s.getX()) {
                /*
                 * Recherche en Y
                 */
                if (player.getY() == s.getY()) {
                    if ((player.getState() == dPlayer.C_STATE_OK) || (player.getState() == dPlayer.C_STATE_ROOTED)) {
                        found = true;
                    }
                }
            }
        }


        return found;
    }

    /**
     * Set the left rerolls
     * @param rerollLeft New number of reroll left
     */
    public void setRerollLeft(int rerollLeft) {
        _rerollLeft = rerollLeft;
    }
    /**
     * Can be sent off or not
     */
    boolean _canBeSentOff;

    /**
     * Returns if a member of the team can be sent off when fouling
     * @return Can be sent off
     */
    public boolean canBeSentOff() {
        return _canBeSentOff;
    }

    /**
     * Set if a member of the team can be sent off
     * @param sentoff Set the status of the sent off
     */
    public void canBeSentOff(boolean sentoff) {
        _canBeSentOff = sentoff;
    }

    public void setPlayerState(int i, int state) {
        ((dPlayer) _players.get(i)).setState(state);
    }

    /**
     * Set turn counter.
     * 
     * @param turn Turn counter new value.
     */
    public void setTurn(int turn) {
        if (_turn < turn) {
            _turn = turn;
            if (_turn > 8) {
                _halfTime++;
            }
        } else {
            _turn = turn;
            if (_turn < 0) {
                _turn = 0;
            }
        }
    }

    public int getPlayersOK() {
        int nb = 0;
        for (int i = 0; i < _players.size(); i++) {
            dPlayer p = (dPlayer) _players.get(i);
            if ((p.getState() == dPlayer.C_STATE_OK) ||
                    (p.getState() == dPlayer.C_STATE_NO_ZONE) ||
                    (p.getState() == dPlayer.C_STATE_PRONE) ||
                    (p.getState() == dPlayer.C_STATE_ROOTED) ||
                    (p.getState() == dPlayer.C_STATE_STUNNED)) {
                nb++;
            }
        }
        return nb;
    }

    public void rollsOver() {
        for (int i = 0; i < _players.size(); i++) {
            dPlayer player = (dPlayer) _players.get(i);
            player.hasPlayed(false);
            if (player.getState() == dPlayer.C_STATE_STUNNED) {
                player.setState(dPlayer.C_STATE_PRONE);
                player.hasPlayed(true);
            }
        }
        
        setBlitzDone(false);
        setFoulDone(false);
        setPassDone(false);
        setHandOffDone(false);
    }

    public void sendPlayersToDugout() {
        for (int i = 0; i < _players.size(); i++) {
            dPlayer player = (dPlayer) _players.get(i);
            if (player.isOnThePitch()) {
                player.setState(dPlayer.C_STATE_OK);
                player.setOnPitch(false);
            }
        }
    }

    public boolean isTouchdown() {
        boolean td = false;
        for (int i = 0; i < _players.size(); i++) {
            dPlayer player = (dPlayer) _players.get(i);
            if (player.isOnPitch()) {
                if (((player.getState() == dPlayer.C_STATE_OK) ||
                        (player.getState() == dPlayer.C_STATE_NO_ZONE) ||
                        (player.getState() == dPlayer.C_STATE_ROOTED)) && (player.hasBall())) {
                    if ((_isChallenger) && (player.getX() == 25)) {
                        td = true;
                    }
                    if ((!_isChallenger) && (player.getX() == 0)) {
                        td = true;
                    }
                }
            }
        }
        return td;
    }

    public void rollsKOs() {
        for (int i = 0; i < _players.size(); i++) {
            dPlayer player = (dPlayer) _players.get(i);

            if (player.getState() == dPlayer.C_STATE_KO) {
                int roll = bbTool.getd6();
                int owner;
                if (_isChallenger) {
                    owner = 1;
                } else {
                    owner = 2;
                }
                _match.AddBlockRollHistory(roll, owner, "KO roll for " + player.getName() + " n°" + player.getNumber());
                if (roll >= 4) {
                    player.setState(dPlayer.C_STATE_OK);
                }
            }
        }
    }

    public void setTeamHasPlayed(boolean value) {
        for (int i = 0; i < _players.size(); i++) {
            dPlayer player = (dPlayer) _players.get(i);
            player.hasPlayed(value);
            player.isActive(false);
        }
    }
    
    
    boolean _handOffDone=false;
    boolean _passDone=false;
    boolean _blitzDone=false;
    boolean _foulDone=false;
    
    public void setHandOffDone(boolean value)
    {
        _handOffDone=value;
    }
    
    public void setPassDone(boolean value)
    {
        _passDone=value;
    }
    public void setBlitzDone(boolean value)
    {
        _blitzDone=value;
    }
    public void setFoulDone(boolean value)
    {
        _foulDone=value;
    }
    
    public boolean isHandOffDone()
    {
        return _handOffDone;
    }
    public boolean isPassDone()
    {
        return _passDone;
    }
    public boolean isBlitzDone()
    {
        return _blitzDone;
    }
    public boolean isFoolDone()
    {
        return _foulDone;
    }
    
    /**
     * OLD
     */
    /**
     * Half-time number
     */
    int _halfTime;
    /**
     * Current dice rolls results
     */
    int _currentDiceRoll1 = 0;
    int _currentDiceRoll2 = 1;
    int _currentDiceRoll3 = 2;
    /**
     * Logo of teh team
     */
    Image _logo;

    /**
     * Creates a new instance of dTeam
     * @param race Race of the new team
     */
    public dTeam(String race) {
        _players = new Vector();
        _canBeSentOff = true;
    }

    /**
     * Returns the team logo
     * @return Team logo
     */
    public Image getTeamLogo() {
        return _logo;
    }

    /**
     * Return the number of players on the pitch by side
     * @param side side of the ground to analyze. 1=left; 2=right.
     * @return Number of player on the pitch.
     */
    public int getOnThePitchNumber(int side) {
        int i;

        int players_on_the_pitch = 0;

        /*
         * Compute Maximum players on the pitch
         */
        for (i = 0; i < _players.size(); i++) {
            dPlayer player = (dPlayer) _players.get(i);

            if (player.isOnTheHalfPitch(side)) {
                players_on_the_pitch++;
            }
        }
        return players_on_the_pitch;
    }

    /**
     * Returns the player number X (X is among the players on the pitch, not the player's number).
     * For example if 1, it is the first player on the pitch, not the number 1.
     * 
     * @param number Number of the player
     * @param side Side of the pitch to watch  1=left; 2=right.
     * @return The serached player or null, if there is no player.
     */
    public dPlayer getOnThePitchPlayer(int number, int side) {
        int i;

        int players_on_the_pitch = 0;

        /*
         * Compute Maximum players on the pitch
         */
        for (i = 0; i < _players.size(); i++) {
            dPlayer player = (dPlayer) _players.get(i);

            if (player.isOnTheHalfPitch(side)) {
                players_on_the_pitch++;
                if (players_on_the_pitch == number) {
                    return player;
                }
            }
        }
        return null;
    }

    /**
     * Enable all the player of one side of the pitch.
     * @param side Side of the pitch  1=left; 2=right.
     */
    public void enablePlayersOnThePitch(int side) {
        int i;
        for (i = 0; i < _players.size(); i++) {
            dPlayer player = (dPlayer) _players.get(i);

            if (player.isOnTheHalfPitch(side)) {
                player._hasPlayed = false;
            }
        }
    }

    /**
     * Disable all the player of one side of the pitch.
     * @param side Side of the pitch  1=left; 2=right.
     */
    public void disablePlayersOnThePitch(int side) {
        int i;
        for (i = 0; i < _players.size(); i++) {
            dPlayer player = (dPlayer) _players.get(i);

            if (player.isOnTheHalfPitch(side)) {
                player._hasPlayed = true;
            }
        }
    }

    /**
     * Return the player on the x-y coordinates. Returns null if no player.
     * @param x x coordinate of the player
     * @param y y coordinate of the player
     * @return The player found or null
     */
    public dPlayer getPlayer(int x, int y) {
        boolean found = false;
        dPlayer player = null;

        /*
         * Boucle sur les joueurs
         */
        for (short i = 0; i < _players.size(); i++) {

            /*
             * Recherche en X
             */
            if (((dPlayer) _players.get(i)).getX() == x) {
                /*
                 * Recherche en Y
                 */
                if (((dPlayer) _players.get(i)).getY() == y) {
                    player = ((dPlayer) _players.get(i));
                }
            }
        }

        return player;
    }

    /**
     * Return the player serached on the square
     * @param s Square where the player is searched
     * @return The searched player.
     */
    public dPlayer getPlayer(dSquare s) {
        return this.getPlayer(s._X, s._Y);
    }

    /**
     * Set the score
     * @param score new score
     */
    public void setScore(int score) {
        _score = score;
    }
}
