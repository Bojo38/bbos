/*
 * dMatch.java
 *
 * Created on 22 novembre 2007, 20:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model;

import bbos.Tools.bbTool;
import bbos.*;

import bbos.General.Model.mTeam;
import java.util.*;
import javax.swing.*;
import java.awt.*;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *
 * @author Administrateur
 */
public class dMatch implements rmiMatch, Serializable {

    /**
     * Main Step of the match :
     * 0 : Pre match sequence
     * 1 : Match
     * 2 : Post match sequence
     */
    int _mainStep = 0;
    /**
     * Current step depending of MainStep
     * if PreMatch Sequence:
     *      0 : Add JourneyMen, compute ranking
     *      1 : Meteo
     *      2 : Select tresury
     *      3 : Select inducement
     *      4 : FAME
     *      5 : Toas
     * if Match sequence
     *      1 : Turn 1
     *      2 : Turn 2, etc ...
     * if Post Match sequence
     *      1 : Improvements rolls (pop, MVP, competences)
     *      2 : remove dead players
     *      3 : Generates Winnings
     *      4 : Spiralling expenses
     *      5 : Hire Journeymen
     */
    int _currentStep = 0;
    /**
     * Sub step of current step
     */
    int _subSubStep = 0;
    /**
     * Active team
     *  0 : challenger
     *  1 : other
     */
    int _activeCoach = 0;
    /**
     * Right team : Opponent
     */
    dTeam _teamRight;
    /**
     * Left team : Challenger
     */
    dTeam _teamLeft;

    /**
     * Return active team unique ID
     * @return integer
     */
    public int getActiveTeamId() throws RemoteException {
        if (_activeCoach == 0) {
            return _teamLeft.getId();
        } else {
            return _teamRight.getId();
        }
    }

    public dTeam getLeftTeam() {
        return _teamLeft;
    }

    public dTeam getRightTeam() {
        return _teamRight;
    }

    public int getMainStep() {
        return _mainStep;
    }

    public void setMainStep(int step) {
        _mainStep = step;
    }

    public void setActiveCoach(int coach) {
        _activeCoach = coach;
    }

    public int getActiveCoach() {
        return _activeCoach;
    }

    public void setSubStep(int step) {
        _currentStep = step;
    }

    public int getSubStep() {
        return _currentStep;
    }

    public void setSubSubStep(int step) {
        _subSubStep = step;
    }

    public int getSubSubStep() {
        return _subSubStep;
    }
    dMeteo _meteo;

    public int getMeteo() {
        return _meteo.getMeteo();
    }

    public void setMeteo(int meteo) {
        _meteo.setMeteo(meteo);
    }

    /**
     * Build new Match data
     * @param challenger
     * @param opponent
     */
    public dMatch(mTeam challenger, mTeam opponent) {
        _teamLeft = new dTeam(challenger, true, this);
        _teamRight = new dTeam(opponent, false, this);
        _meteo = new dMeteo();
    }
    protected int _kickingTeam = 0;

    public void setKickinkgTeam(int team) {
        _kickingTeam = team;
    }

    public int getKickinkgTeam() {
        return _kickingTeam;
    }
    int _half;

    public int getHalf() {
        return _half;
    }
    boolean _leftPlayersDispatched = false;
    boolean _rightPlayersDispatched = false;

    public boolean NetworkPlayersDispatched() {
        return _leftPlayersDispatched && _rightPlayersDispatched;
    }

    public void setLeftPlayersDispatched(boolean value) {
        _leftPlayersDispatched = value;
    }

    public void setRightPlayersDispatched(boolean value) {
        _rightPlayersDispatched = value;
    }
    boolean _leftRefresh = false;
    boolean _rightRefresh = false;

    public boolean needLeftRefresh() {
        return _leftRefresh;
    }

    public void setLeftRefresh(boolean value) {
        _leftRefresh = value;
    }

    public boolean needRightRefresh() {
        return _rightRefresh;
    }

    public void setRightRefresh(boolean value) {
        _rightRefresh = value;
    }
    String _diary = "";

    public String getDiary() {
        return _diary;
    }

    public void AddDiary(String value) {
        if (_diary.equals("")) {
            _diary = value;
        } else {
            _diary = _diary + "\n" + value;
        }
    }
    protected Vector _rollHistory = new Vector();

    public void AddRollHistory(int dice, int owner, String description) {
        _rollHistory.add(new dRollHistory(1, owner, description, dice));
    }

    public void AddRollHistory(int dice1, int dice2, int owner, String description) {
        _rollHistory.add(new dRollHistory(1, owner, description, dice1, dice2));
    }

    public void AddBlockRollHistory(int dice, int owner, String description) {
        _rollHistory.add(new dRollHistory(2, owner, description, dice));
    }

    public void AddBlockRollHistory(int dice1, int dice2, int owner, String description) {
        _rollHistory.add(new dRollHistory(2, owner, description, dice1, dice2));
    }

    public void AddBlockRollHistory(int dice1, int dice2, int dice3, int owner, String description) {
        _rollHistory.add(new dRollHistory(2, owner, description, dice1, dice2, dice3));
    }

    public Vector getDiceHistory() {
        return _rollHistory;
    }
    protected boolean _ballSet = false;

    public boolean isBallSet() {
        return _ballSet;
    }
    protected dSquareCollection _squares = new dSquareCollection(26, 15);

    public void setWaitingBall(dSquare s) {
        _squares.setWaitBall(s);
    }

    public void setBall(dSquare s) {
        _squares.setBall(s);
    }

    public void setBall(boolean value) {
        _ballSet = value;
    }

    public dSquareCollection getSquares() {
        return _squares;
    }

    public boolean isAPlayer(dSquare square) {
        return (_teamRight.isAPlayer(square)) || (_teamLeft.isAPlayer(square));
    }

    public void removeBall() {
        _squares.removeAllBalls();
    }

    public boolean isBallPlacedOnThePitch(int side) {
        return _squares.isBallPlacedOnThePitch(side);
    }

    public dSquare getBallSquare() {
        Vector v = _squares.getBallSquares();
        if (v != null) {
            if (v.size() > 0) {
                return (dSquare) (v.get(0));
            }
        }
        return null;
    }
    protected int _currentStepData = 0;

    public int getCurrentStepData() {
        return _currentStepData;
    }

    public void setCurrentStepData(int value) {
        _currentStepData = value;
    }

    public void setSquareState(dSquare square, int state) {
        _squares.setState(square, state);
    }

    public void setSquareSpecialState(dSquare square, boolean state) {
        _squares.setSpecialState(square, state);
    }

    public void resetSquareMove() {
        _squares.removeMove();
    }

    public void resetSquareBlock() {
        _squares.removeBlock();
    }

    public void resetSquarePass() {
        _squares.removePass();
        _drawPass = false;
    }

    public void resetSquareFoul() {
        _squares.removeFoul();
    }

    public void setBlockDiceNumber(dSquare s, int number) {
        _squares.setBlockDiceNumber(s.getX(), s.getY(), number);
    }
    boolean _turnover = false;

    public void turnover(boolean value) {
        _turnover = value;
    }

    public boolean isTurnover() {
        return _turnover;
    }

    public void setHalf(int half) {
        _half = half;
    }

    public void sendPlayersToDugout() {
        _teamLeft.sendPlayersToDugout();
        _teamRight.sendPlayersToDugout();
    }

    public int getDiceHistorySize() {
        return _rollHistory.size();
    }

    public int getSquareState(dSquare s) {
        dSquare sq = _squares.getSquare(s.getX(), s.getY());
        return sq.getState();

    }
    boolean _waitForDiceChoice = false;
    int _blockDicesNumber = 0;
    int _blockDice1;
    int _blockDice2;
    int _blockDice3;
    int _blockChooser = 0;
    int _blockDiceChosen = 0;

    public int getBlockDiceNumber() {
        return _blockDicesNumber;
    }

    public int getBlockDiceValue(int i) {
        switch (i) {
            case 1:
                return _blockDice1;
            case 2:
                return _blockDice2;
            case 3:
                return _blockDice3;
        }
        return 0;
    }

    public void waitForDiceChoice(int chooser, int _dices, int _dice1, int _dice2, int _dice3) {
        _waitForDiceChoice = true;
        _blockChooser = chooser;
        _blockDicesNumber = _dices;
        _blockDice1 = _dice1;
        _blockDice2 = _dice2;
        _blockDice3 = _dice3;
    }

    public boolean isWaitingForDiceChoice() {
        return _waitForDiceChoice;
    }

    public void stopWaitingForDiceChoice(int choice) {
        _waitForDiceChoice = false;
        _blockDiceChosen = choice;
    }

    public int getBlockDiceChosen() {
        return _blockDiceChosen;
    }

    public int getDiceBlockChooser() {
        return _blockChooser;
    }
    protected boolean _drawPass;

    public void displayPassRule(boolean value) {
        _drawPass = value;
    }

    public boolean displayPassRule() {
        return _drawPass;
    }

    public int getAllowedPassRange() {
        return 4;
    }
    boolean _waitingInterceptorChoice = false;
    int _interceptionChooser;

    public void WaitForInterceptionChoice(int chooser) {
        _interceptionChooser = chooser;
        _waitingInterceptorChoice = true;
    }

    public boolean hasThrowableTeammate(int x, int y) {
        boolean yes = false;
        dTeam team = null;

        if (_teamLeft.getPlayerNumber(x, y) > -1) {
            team = _teamLeft;
        }
        if (_teamRight.getPlayerNumber(x, y) > -1) {
            team = _teamRight;
        }

        if (team != null) {
            for (int i = 0; i < team.getPlayersNumber(); i++) {
                dPlayer p = team.getPlayer(i);
                if ((p.isOnPitch()) && (p.getState() == dPlayer.C_STATE_OK)) {
                    yes = true;
                    break;
                }
            }
        }

        return yes;
    }
    /**
     * OLD MODEL
     */
    /**
     * Creates a new instance of dMatch
     */
    /*dsWholeMatch _matchSequence;*/
    public boolean _validated = false;
    /* public Vector _blocSquares;*/
    /*
     * Special turn :
     *  - 0 : No
     *  - 1 : Pass block
     *  - 2 : Dump off
     *  - 3 : Position
     */
    public int _specialTurn;
    public int _currentTurn;

    /*
     * 1 (left) ou 2 (right)
     */
    /* public dBall _ball;*/
    public boolean _touchdownScored;

    public void initTeamTurn() {
        /*
         * Init Teams
         */
        /* for (int i = 0; i < _activeTeam._players.size(); i++) {
        dPlayer player = (dPlayer) _activeTeam._players.get(i);
        if (player.isOnThePitch()) {
        player._hasPlayed = false;
        /*                if (player._State == dPlayer.C_PLAYER_STATE_STUNNED) {
        player._havePlayed = true;
        player._State = dPlayer.C_PLAYER_STATE_PRONE;
        }*/
        /* }
        }
        /* for (int i = 0; i < _opposingTeam._players.size(); i++) {
        dPlayer player = (dPlayer) _opposingTeam._players.get(i);
        if (player.isOnThePitch()) {
        player._hasPlayed = false;
        }
        }-+*/
    }

    public void swapTeam() {
        /*  dTeam tmp;
        tmp = _activeTeam;
        _activeTeam = _opposingTeam;
        _opposingTeam = tmp;*/
    }

    /*    public void newMatch() {
    /*_ball=new dBall();
    _ball.setX(-1);
    _ball.setY(-1);*/

    /*
     * Chargement des equipes
     */
    /*      _teamRight = new dTeam("WoodElf");
    _teamRight._side = 2;
    dPlayer player = new dPlayer(this,"treeman", "WoodElf", 2);
    _teamRight._players.add(player);
    player = new dPlayer(this,"wardancer", "WoodElf", 2);
    _teamRight._players.add(player);
    player = new dPlayer(this,"wardancer", "WoodElf", 2);
    _teamRight._players.add(player);
    player = new dPlayer(this,"catcher", "WoodElf", 2);
    _teamRight._players.add(player);
    player = new dPlayer(this,"catcher", "WoodElf", 2);
    _teamRight._players.add(player);
    player = new dPlayer(this,"catcher", "WoodElf", 2);
    _teamRight._players.add(player);
    player = new dPlayer(this,"catcher", "WoodElf", 2);
    _teamRight._players.add(player);
    player = new dPlayer(this,"thrower", "WoodElf", 2);
    _teamRight._players.add(player);
    player = new dPlayer(this,"thrower", "WoodElf", 2);
    _teamRight._players.add(player);
    player = new dPlayer(this,"lineman", "WoodElf", 2);
    _teamRight._players.add(player);
    player = new dPlayer(this,"lineman", "WoodElf", 2);
    _teamRight._players.add(player);
    player = new dPlayer(this,"jordell", "WoodElf", 2);
    _teamRight._players.add(player);
    ((dPlayer) _teamRight._players.get(8))._Injury = 1;
    ((dPlayer) _teamRight._players.get(11))._Injury = 1;
    ((dPlayer) _teamRight._players.get(2))._Injury = 4;
    ((dPlayer) _teamRight._players.get(1))._Injury = 3;
    ((dPlayer) _teamRight._players.get(0))._Injury = 2;
    ((dPlayer) _teamRight._players.get(11))._Injury = 4;
    for (short i = 0; i < _teamRight._players.size(); i++) {
    ((dPlayer) _teamRight._players.get(i))._number = i;
    ((dPlayer) _teamRight._players.get(i))._name = "Joueur " + Short.toString(i);
    ((dPlayer) _teamRight._players.get(i))._X = 25 - (i / 15);
    ((dPlayer) _teamRight._players.get(i))._Y = i - (i / 15) * 15;
    }
    _teamLeft = new dTeam("ChaosDwarf");
    _teamLeft._side = 1;
    player = new dPlayer(this,"centaur", "ChaosDwarf", 1);
    _teamLeft._players.add(player);
    player = new dPlayer(this,"centaur", "ChaosDwarf", 1);
    _teamLeft._players.add(player);
    player = new dPlayer(this,"blocker", "ChaosDwarf", 1);
    _teamLeft._players.add(player);
    player = new dPlayer(this,"blocker", "ChaosDwarf", 1);
    _teamLeft._players.add(player);
    player = new dPlayer(this,"blocker", "ChaosDwarf", 1);
    _teamLeft._players.add(player);
    player = new dPlayer(this,"blocker", "ChaosDwarf", 1);
    _teamLeft._players.add(player);
    player = new dPlayer(this,"blocker", "ChaosDwarf", 1);
    _teamLeft._players.add(player);
    player = new dPlayer(this,"blocker", "ChaosDwarf", 1);
    _teamLeft._players.add(player);
    player = new dPlayer(this,"hobgoblin", "ChaosDwarf", 1);
    _teamLeft._players.add(player);
    player = new dPlayer(this,"minotaur", "ChaosDwarf", 1);
    _teamLeft._players.add(player);
    player = new dPlayer(this,"hobgoblin", "ChaosDwarf", 1);
    _teamLeft._players.add(player);
    player = new dPlayer(this,"hobgoblin", "ChaosDwarf", 1);
    _teamLeft._players.add(player);
    for (short i = 0; i < _teamLeft._players.size(); i++) {
    ((dPlayer) _teamLeft._players.get(i))._number = i;
    ((dPlayer) _teamLeft._players.get(i))._name = "Joueur " + Short.toString(i);
    ((dPlayer) _teamLeft._players.get(i))._X = (i / 15);
    ((dPlayer) _teamLeft._players.get(i))._Y = i - (i / 15) * 15;
    }
    ((dPlayer) _teamLeft._players.get(8))._Injury = 1;
    ((dPlayer) _teamLeft._players.get(11))._Injury = 1;
    ((dPlayer) _teamLeft._players.get(2))._Injury = 4;
    ((dPlayer) _teamLeft._players.get(10))._Injury = 4;
    ((dPlayer) _teamLeft._players.get(1))._Injury = 3;
    ((dPlayer) _teamLeft._players.get(0))._Injury = 2;
    _teamLeft._popfactor = 2;
    _teamRight._popfactor = 4;
    /*
     * Set To reserve
     */
    /*    MainForm.getSingleton().redraw();
    setToReserve();
    MainForm.getSingleton().repaint();
    }*/
    public void setToReserve() {
        /*
         * Set players to reserve
         */
        for (short i = 0; i < _teamLeft._players.size(); i++) {
            dPlayer player = (dPlayer) _teamLeft._players.get(i);
            /*player._reserveX = i - (i / 8) * 8;
            player._reserveY = i / 8;
            player._reserve = true;*/
        }


        for (short i = 0; i < _teamRight._players.size(); i++) {
            dPlayer player = (dPlayer) _teamRight._players.get(i);
            /*            player._reserveX = i - (i / 8) * 8;
            player._reserveY = i / 8;
            player._reserve = true;*/
        }
    }

    public void refresh() {
        MainForm.getSingleton().repaint();
    }

    public void selectPlayer(int x_coord, int y_coord) {
        /*
         * Boucle sur les joueurs
         */

        /*dTeam team = _activeTeam;
        for (short i = 0; i < team._players.size(); i++) {
        /*
         * Recherche en X
         */
        /*   if ((((dPlayer) team._players.get(i)).getX() * 30 <= x_coord) && ((((dPlayer) team._players.get(i)).getX() * 30 + 30 > x_coord))) {
        /*
         * Recherche en Y
         */
        /*       if ((((dPlayer) team._players.get(i)).getY() * 30 <= y_coord) && ((((dPlayer) team._players.get(i)).getY() * 30 + 30 > y_coord))) {
        /*                    if ((((dPlayer) team._players.get(i)).isPlayable()) && (!((dPlayer) team._players.get(i))._havePlayed)) {
        if (((dPlayer) team._players.get(i))._isActive) {
        ((dPlayer) team._players.get(i))._isActive = false;
        _selectedPlayer = null;
        } else {
        if (((dPlayer) team._players.get(i)).isSelectable()) {
        _selectedPlayer = ((dPlayer) team._players.get(i));
        _selectedPlayer._isActive = true;
        }
        }
        }*/

        /*           for (short j = 0; j < team._players.size(); j++) {
        if (j != i) {
        ((dPlayer) team._players.get(j))._isActive = false;
        }
        }
        }
        }
        }*/
    }
    public dSquare _selectedSquare;

    public void selectSquare(int x_coord, int y_coord) {

        Vector v = _squares.getPassAndMoveSquares();
        _selectedSquare = null;

        for (int i = 0; i < v.size(); i++) {
            if (((((dSquare) (v.get(i)))._X == x_coord / 30)
                    && (((dSquare) (v.get(i)))._Y == y_coord / 30))) {
                _selectedSquare = new dSquare(x_coord / 30, y_coord / 30);
            }
        }
        /*       if (_selectedPlayer != null) {
        if ((_selectedPlayer.getX() == x_coord / 30) &&
        (_selectedPlayer.getY() == y_coord / 30)) {
        _selectedSquare = new dSquare(x_coord / 30, y_coord / 30);
        }
        }*/
    }

    public void selectSquareBlock(int x_coord, int y_coord) {
        Vector v = _squares.getBlockSquares();

        for (int i = 0; i < v.size(); i++) {
            if (((((dSquare) (v.get(i))).getX() == x_coord / 30)
                    && (((dSquare) (v.get(i))).getY() == y_coord / 30))) {
                _selectedSquare = new dSquare(x_coord / 30, y_coord / 30);
            }
        }
    }

    public void unselectPlayer(int x_coord, int y_coord) {
        /* if (_selectedPlayer != null) {
        if ((_selectedPlayer._X == x_coord / 30) && (_selectedPlayer._Y == y_coord / 30)) {
        _selectedPlayer._hasPlayed = true;
        _selectedPlayer._isActive = false;
        _selectedPlayer = null;
        }
        }*/
    }

    public void selectFlyingSquare(int x_coord, int y_coord) {
        //_onFlySquare = new dSquare(x_coord / 30, y_coord / 30);
    }

    public boolean selectFlyingPlayer(int x_coord, int y_coord) {
        boolean found = false;
        /*
         * Boucle sur les joueurs
         */
        for (short i = 0; i < _teamRight._players.size(); i++) {

            /*
             * Recherche en X
             */
            if ((((dPlayer) _teamRight._players.get(i)).getX() * 30 <= x_coord) && ((((dPlayer) _teamRight._players.get(i)).getX() * 30 + 30 > x_coord))) {
                /*
                 * Recherche en Y
                 */
                if ((((dPlayer) _teamRight._players.get(i)).getY() * 30 <= y_coord) && ((((dPlayer) _teamRight._players.get(i)).getY() * 30 + 30 > y_coord))) {
                    /*
                     * S?lection du nouveau joueur
                     */
                    //      _onFlyPlayer = ((dPlayer) _teamRight._players.get(i));
                    found = true;
                }
            }
        }

        for (short i = 0; i < _teamLeft._players.size(); i++) {

            /*
             * Recherche en X
             */
            if ((((dPlayer) _teamLeft._players.get(i)).getX() * 30 <= x_coord) && ((((dPlayer) _teamLeft._players.get(i)).getX() * 30 + 30 > x_coord))) {
                /*
                 * Recherche en Y
                 */
                if ((((dPlayer) _teamLeft._players.get(i)).getY() * 30 <= y_coord) && ((((dPlayer) _teamLeft._players.get(i)).getY() * 30 + 30 > y_coord))) {
                    /*
                     * S?lection du nouveau joueur
                     */
                    //        _onFlyPlayer = ((dPlayer) _teamLeft._players.get(i));
                    found = true;
                }
            }
        }
        return found;
    }

    public boolean getGrabbedPlayer(int x_coord, int y_coord) {
        boolean found = false;

        /*
         * Boucle sur les joueurs
         */
        // for (short i = 0; (i < _activeTeam._players.size()) && (!found); i++) {

        /*
         * Recherche en X
         */
        // if ((((dPlayer) _activeTeam._players.get(i)).getX() * 30 <= x_coord) && ((((dPlayer) _activeTeam._players.get(i)).getX() * 30 + 30 > x_coord))) {
                /*
         * Recherche en Y
         */
        //   if ((((dPlayer) _activeTeam._players.get(i)).getY() * 30 <= y_coord) && ((((dPlayer) _activeTeam._players.get(i)).getY() * 30 + 30 > y_coord))) {
                    /*
         * S?lection du nouveau joueur
         */
        /*                    if (((dPlayer) _activeTeam._players.get(i))._Injury == 0) {
        _grabPlayer = ((dPlayer) _activeTeam._players.get(i));
        found = true;
        }*/
        /*          }
        }
        }*/
        return found;
    }

    public boolean isAlreadyAPlayer(dPlayer player) {
        boolean found = false;

        /*
         * Boucle sur les joueurs
         */
        for (short i = 0; i < _teamRight._players.size(); i++) {
            if (((dPlayer) _teamRight._players.get(i)) != player) {
                /*
                 * Recherche en X
                 */
                if (((dPlayer) _teamRight._players.get(i)).getX() == player.getX()) {
                    /*
                     * Recherche en Y
                     */
                    if (((dPlayer) _teamRight._players.get(i)).getY() == player.getY()) {
                        found = true;
                    }
                }
            }
        }

        for (short i = 0; i < _teamLeft._players.size(); i++) {
            if (((dPlayer) _teamLeft._players.get(i)) != player) {
                /*
                 * Recherche en X
                 */
                if (((dPlayer) _teamLeft._players.get(i)).getX() == player.getX()) {
                    /*
                     * Recherche en Y
                     */
                    if (((dPlayer) _teamLeft._players.get(i)).getY() == player.getY()) {
                        found = true;
                    }
                }
            }
        }

        return found;
    }

    public dPlayer getPlayer(int x, int y) {
        boolean found = false;
        dPlayer player = null;

        /*
         * Boucle sur les joueurs
         */
        player = _teamLeft.getPlayer(x, y);
        if (player == null) {
            player = _teamRight.getPlayer(x, y);
        }
        return player;
    }

    public dPlayer getPlayer(dSquare s) {
        return this.getPlayer(s._X, s._Y);
    }
}
