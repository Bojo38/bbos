/*
 * dPlayer.java
 *
 * Created on 21 novembre 2007, 22:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model;

import bbos.*;
import bbos.General.Model.mPlayer;
import bbos.General.Model.mPlayerType;
import bbos.Match.Model.Actions.dAction;
import bbos.Match.Model.Actions.daBlock;
import bbos.Match.Model.Competences.Extraordinary.dcStunty;
import bbos.Match.Model.Competences.Extraordinary.dcRightStuff;
import bbos.Match.Model.Competences.dCompetence;
import java.util.*;
import java.awt.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrateur
 */
public class dPlayer implements rmiPlayer, Serializable {

    int _movement;
    int _strength;
    int _agility;
    int _armor;
    int _cost;
    String _position;
    String _name;
    int _number;
    Vector<dCompetence> _competences;
    boolean _onPitch = false;
    boolean _isChallenger;
    String _race;
    public int _state = 0;
    /*
     * Player Injury
     * the values are   0 : OK
     *                  1 : KO
     *                  2 : Badly Hurt
     *                  3 : Seriously Injured
     *                  4 : Dead
     *                  5 : Sent Off
     *                  6 : with ball
     *                  7 : stunned
     *                  8 : Prone
     *                  9 : No Zone
     *                  10 : Rooted
     *                  11 : With Bomb
     */
    public static final int C_STATE_OK = 0;
    public static final int C_STATE_KO = 1;
    public static final int C_STATE_BADLY_HURT = 2;
    public static final int C_STATE_SERIOUSLY_INJURED = 17;
    public static final int C_STATE_SERIOUSLY_INJURED_MISS = 3;
    public static final int C_STATE_SERIOUSLY_INJURED_PERS = 12;
    public static final int C_STATE_SERIOUSLY_INJURED_MA = 13;
    public static final int C_STATE_SERIOUSLY_INJURED_AG = 14;
    public static final int C_STATE_SERIOUSLY_INJURED_AR = 15;
    public static final int C_STATE_SERIOUSLY_INJURED_ST = 16;
    public static final int C_STATE_DEAD = 4;
    public static final int C_STATE_SENT_OFF = 5;
    public static final int C_STATE_STUNNED = 7;
    public static final int C_STATE_PRONE = 8;
    public static final int C_STATE_NO_ZONE = 9;
    public static final int C_STATE_ROOTED = 10;
    protected boolean _hasBomb = false;
    protected boolean _hasBall = false;
    protected boolean _canGetBall = true;

    public boolean canGetBall() {
        if (_hasBall) {
            return false;

        } else {
            return _canGetBall;
        }
    }

    public String getName() {
        return _name;
    }

    public String getPosition() {
        return _position;
    }

    public int getNumber() {
        return _number;
    }

    public int getArmor() {
        return _armor;
    }

    public int getMovement() {
        return _movement;
    }

    public int getAgility() {
        return _agility;
    }

    public int getStrength() {
        return _strength;
    }
    String _activeIcon;
    String _passiveIcon;

    public String getActiveIcone() {
        return _activeIcon;
    }

    public String getPassiveIcone() {
        return _passiveIcon;
    }
    dMatch _match;

    public void setBall(boolean value) {
        _hasBall = value;
    }

    public boolean hasBall() {
        return _hasBall;
    }

    public void setBomb(boolean value) {
        _hasBomb = value;
    }

    public boolean hasBomb() {
        return _hasBomb;
    }

    public dPlayer(mPlayer player, boolean isChallenger, String race, dMatch match) {
        _currentAction = null;
        _position = player.getPosition();
        _state = 0;
        _movement = player.getMovement();
        _strength = player.getStrength();
        _agility = player.getAgility();
        _armor = player.getArmor();
        _competences = new Vector();
        _race = race;

        _match = match;
        for (short j = 0; j < player.getPlayerType().getCompetences().size(); j++) {
            _competences.add((dCompetence) player.getPlayerType().getCompetences().get(j));
        }

        for (short j = 0; j < player.getCompetences().size(); j++) {
            _competences.add((dCompetence) player.getCompetences().get(j));
        }
        _name = player.getName();
        _number = player.getNumber();
        _cost = player.getCost();

        _isChallenger = isChallenger;

        if (_isChallenger) {
            _activeIcon = player.getLeftActiveIcon();
            _passiveIcon = player.getLeftPassiveIcon();
        } else {
            _activeIcon = player.getRightActiveIcon();
            _passiveIcon = player.getRightPassiveIcon();
        }

        _isStarPlayer = player.getPlayerType().isStar();
    }

    public dPlayer(dPlayer player) {
        _currentAction = null;
        _position = player.getPosition();
        _state = 0;
        _movement = player.getMovement();
        _strength = player.getStrength();
        _agility = player.getAgility();
        _armor = player.getArmor();
        _competences = new Vector();
        _race = player._race;

        _match = player._match;

        for (short j = 0; j < player.getCompetences().size(); j++) {
            _competences.add((dCompetence) player.getCompetences().get(j));
        }
        _name = player.getName();
        _number = player.getNumber();
        _cost = player.getCost();

        _isChallenger = player._isChallenger;

        _activeIcon = player._activeIcon;
        _passiveIcon = player._passiveIcon;
        _isStarPlayer = player._isStarPlayer;

        _persistantInjuries = player._persistantInjuries;
    }

    public dPlayer(mPlayerType player, boolean isChallenger, String race, dMatch match) {
        _currentAction = null;
        _position = player.getPosition();
        _state = 0;
        _movement = player.getMovement();
        _strength = player.getStrength();
        _agility = player.getAgility();
        _armor = player.getArmor();
        _competences = new Vector();

        for (short j = 0; j < player.getCompetences().size(); j++) {
            _competences.add((dCompetence) player.getCompetences().get(j));
        }
        _name = player.getName();
        _number = 0;
        _cost = player.getCost();

        _match = match;
        _isChallenger = isChallenger;

        _isStarPlayer = player.isStar();

    }

    public boolean isChallenger() {
        return _isChallenger;
    }

    /* static protected Image getIcon(String url) {
    Image image = null;
    try {
    image = MainForm.getSingleton().getToolkit().getImage(url);
    } catch (Exception e) {
    image = null;
    }
    return image;
    }*/
    public int getCost() {
        return _cost;
    }

    public void setModel(dMatch model) {
        _match = model;
    }

    public void setContent(dPlayer player) {
        _movement = player._movement;
        _strength = player._strength;
        _agility = player._agility;
        _armor = player._armor;
        _cost = player._cost;

        _position = player._position;
        _name = player._name;
        _number = player._number;

        for (int i = 0; i < _competences.size(); i++) {
            dCompetence comp = (dCompetence) _competences.get(i);
            dCompetence comp2 = (dCompetence) _competences.get(i);
            comp.setContent(comp2);
        }
    }

    public Vector getCompetences() {
        return _competences;
    }

    public void setCost(int cost) {
        _cost = cost;
    }

    public void addCompetence(dCompetence comp) {
        _competences.add(comp);
    }

    public void setName(String name) {
        _name = name;
    }

    public boolean isOnPitch() {
        return _onPitch;
    }

    public int getState() {
        return _state;
    }

    public int getX() {
        return _X;
    }

    public int getY() {
        return _Y;
    }
    protected static ImageIcon I_STATE_KO = null;
    protected static ImageIcon I_STATE_BADLY_HURT = null;
    protected static ImageIcon I_STATE_SERIOUSLY_INJURED = null;
    protected static ImageIcon I_STATE_DEAD = null;
    protected static ImageIcon I_STATE_SENT_OFF = null;
    protected static ImageIcon I_STATE_STUNNED = null;
    protected static ImageIcon I_STATE_PRONE = null;
    protected static ImageIcon I_STATE_NO_ZONE = null;
    protected static ImageIcon I_STATE_ROOTED = null;
    protected static ImageIcon I_STATE_WITH_BOMB = null;
    protected static ImageIcon I_STATE_WITH_BALL = null;

    public static ImageIcon getStateIcon(int state) {
        switch (state) {
            case C_STATE_OK:
                return null;
            case C_STATE_KO:
                if (I_STATE_KO == null) {
                    I_STATE_KO = new ImageIcon(dPlayer.class.getResource("/resources/images/states/KO.png"));
                }
                return I_STATE_KO;
            case C_STATE_BADLY_HURT:
                if (I_STATE_BADLY_HURT == null) {
                    I_STATE_BADLY_HURT = new ImageIcon(dPlayer.class.getResource("/resources/images/states/BH.png"));
                }
                return I_STATE_BADLY_HURT;
            case C_STATE_SERIOUSLY_INJURED:
                if (I_STATE_SERIOUSLY_INJURED == null) {
                    I_STATE_SERIOUSLY_INJURED = new ImageIcon(dPlayer.class.getResource("/resources/images/states/SI.png"));
                }
                return I_STATE_SERIOUSLY_INJURED;
            case C_STATE_SERIOUSLY_INJURED_MISS:
                if (I_STATE_SERIOUSLY_INJURED == null) {
                    I_STATE_SERIOUSLY_INJURED = new ImageIcon(dPlayer.class.getResource("/resources/images/states/SI.png"));
                }
                return I_STATE_SERIOUSLY_INJURED;
            case C_STATE_SERIOUSLY_INJURED_PERS:
                if (I_STATE_SERIOUSLY_INJURED == null) {
                    I_STATE_SERIOUSLY_INJURED = new ImageIcon(dPlayer.class.getResource("/resources/images/states/SI.png"));
                }
                return I_STATE_SERIOUSLY_INJURED;
            case C_STATE_SERIOUSLY_INJURED_MA:
                if (I_STATE_SERIOUSLY_INJURED == null) {
                    I_STATE_SERIOUSLY_INJURED = new ImageIcon(dPlayer.class.getResource("/resources/images/states/SI.png"));
                }
                return I_STATE_SERIOUSLY_INJURED;
            case C_STATE_SERIOUSLY_INJURED_AG:
                if (I_STATE_SERIOUSLY_INJURED == null) {
                    I_STATE_SERIOUSLY_INJURED = new ImageIcon(dPlayer.class.getResource("/resources/images/states/SI.png"));
                }
                return I_STATE_SERIOUSLY_INJURED;
            case C_STATE_SERIOUSLY_INJURED_AR:
                if (I_STATE_SERIOUSLY_INJURED == null) {
                    I_STATE_SERIOUSLY_INJURED = new ImageIcon(dPlayer.class.getResource("/resources/images/states/SI.png"));
                }
                return I_STATE_SERIOUSLY_INJURED;
            case C_STATE_SERIOUSLY_INJURED_ST:
                if (I_STATE_SERIOUSLY_INJURED == null) {
                    I_STATE_SERIOUSLY_INJURED = new ImageIcon(dPlayer.class.getResource("/resources/images/states/SI.png"));
                }
                return I_STATE_SERIOUSLY_INJURED;
            case C_STATE_DEAD:
                if (I_STATE_DEAD == null) {
                    I_STATE_DEAD = new ImageIcon(dPlayer.class.getResource("/resources/images/states/RIP.png"));
                }
                return I_STATE_DEAD;
            case C_STATE_SENT_OFF:
                if (I_STATE_SENT_OFF == null) {
                    I_STATE_SENT_OFF = new ImageIcon(dPlayer.class.getResource("/resources/images/states/sent_off.png"));
                }
                return I_STATE_SENT_OFF;
            case C_STATE_STUNNED:
                if (I_STATE_STUNNED == null) {
                    I_STATE_STUNNED = new ImageIcon(dPlayer.class.getResource("/resources/images/states/stunned.png"));
                }
                return I_STATE_STUNNED;
            case C_STATE_PRONE:
                if (I_STATE_PRONE == null) {
                    I_STATE_PRONE = new ImageIcon(dPlayer.class.getResource("/resources/images/states/prone.png"));
                }
                return I_STATE_PRONE;
            case C_STATE_NO_ZONE:
                if (I_STATE_NO_ZONE == null) {
                    I_STATE_NO_ZONE = new ImageIcon(dPlayer.class.getResource("/resources/images/states/no_zone.png"));
                }
                return I_STATE_NO_ZONE;
            case C_STATE_ROOTED:
                if (I_STATE_ROOTED == null) {
                    I_STATE_ROOTED = new ImageIcon(dPlayer.class.getResource("/resources/images/states/rooted.png"));
                }
                return I_STATE_ROOTED;
        }
        return null;
    }

    public static ImageIcon getBallIcon() {
        if (I_STATE_WITH_BALL == null) {
            I_STATE_WITH_BALL = new ImageIcon(dPlayer.class.getResource("/resources/images/states/holdball.png"));
        }
        return I_STATE_WITH_BALL;

    }

    public static ImageIcon getBombIcon() {
        if (I_STATE_WITH_BOMB == null) {
            I_STATE_WITH_BOMB = new ImageIcon(dPlayer.class.getResource("/resources/images/states/bomb.png"));
        }
        return I_STATE_WITH_BOMB;

    }

    public void setX(int x) {
        //System.out.println("setX " + Integer.toString(x));
        _X = x;
    }

    public void setY(int y) {
        _Y = y;
    }
    /*
     * Player Position
     * The system is X : 0->25
     *               Y : 0->14
     */
    int _X = 0;
    int _Y = 0;
    boolean _hasPlayed = false;
    boolean _isActive = false;
    boolean _isPlaying = false;

    public boolean isActive() {
        return _isActive;
    }

    public void isActive(boolean value) {
        _isActive = value;
    }

    public boolean isPlaying() {
        return _isPlaying;
    }

    public void isPlaying(boolean value) {
        _isPlaying = value;
    }

    public boolean hasPlayed() {
        return _hasPlayed;
    }

    public void hasPlayed(boolean value) {
        _hasPlayed = value;
    }
    boolean _isStarPlayer = false;

    public boolean isStarPlayer() {
        return _isStarPlayer;
    }

    public void setOnPitch(boolean value) {
        _onPitch = value;
    }

    public boolean isOnTheHalfPitch(int side) {
        boolean result = false;
        if (_onPitch) {
            if ((_Y >= 0) && (_Y < 15)) {
                if (side == 1) {
                    if ((_X >= 0) && (_X < 13)) {
                        result = true;
                    }

                } else {
                    if ((_X >= 13) && (_X < 26)) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public boolean isOnThePitch() {

        return _onPitch;
    }

    public boolean isOnScrimmage(int side) {
        boolean result = false;
        if (_onPitch) {
            if ((_Y >= 4) && (_Y < 11)) {
                if (side == 1) {
                    if (_X == 12) {
                        result = true;
                    }

                } else {
                    if (_X == 13) {
                        result = true;
                    }

                }
            }
        }
        return result;
    }

    public boolean isOnSideTop(int side) {
        boolean result = false;
        if (_onPitch) {
            if ((_Y >= 0) && (_Y < 4)) {
                if (side == 1) {
                    if ((_X >= 0) && (_X < 13)) {
                        result = true;
                    }

                } else {
                    if ((_X >= 13) && (_X < 26)) {
                        result = true;
                    }

                }
            }
        }
        return result;
    }

    public boolean isOnSideBottom(int side) {
        boolean result = false;
        if (_onPitch) {
            if ((_Y >= 11) && (_Y < 15)) {
                if (side == 1) {
                    if ((_X >= 0) && (_X < 13)) {
                        result = true;
                    }

                } else {
                    if ((_X >= 13) && (_X < 26)) {
                        result = true;
                    }

                }
            }
        }
        return result;
    }

    public void setState(int state) {
        _state = state;

        if ((state == dPlayer.C_STATE_BADLY_HURT) ||
                (state == dPlayer.C_STATE_DEAD) ||
                (state == dPlayer.C_STATE_KO) ||
                (state == dPlayer.C_STATE_SENT_OFF) ||
                (state == dPlayer.C_STATE_SERIOUSLY_INJURED_AG) ||
                (state == dPlayer.C_STATE_SERIOUSLY_INJURED_AR) ||
                (state == dPlayer.C_STATE_SERIOUSLY_INJURED_MISS) ||
                (state == dPlayer.C_STATE_SERIOUSLY_INJURED_PERS) ||
                (state == dPlayer.C_STATE_SERIOUSLY_INJURED_ST)) {
            _onPitch = false;
        }
    }

    public int getTackleZoneNumber() {
        int n = 0;

        Vector opponentPlayers = new Vector();

        try {
            if (_isChallenger) {
                opponentPlayers = _match.getRightTeam().getPlayers();
            } else {
                opponentPlayers = _match.getLeftTeam().getPlayers();
            }

            for (int i = 0; i < opponentPlayers.size(); i++) {
                rmiPlayer opponent = (rmiPlayer) opponentPlayers.get(i);

                if (opponent.isOnPitch()) {
                    if ((opponent.getState() == C_STATE_OK) ||
                            (opponent.getState() == C_STATE_ROOTED)) {
                        int dX = Math.abs(opponent.getX() - _X);
                        int dY = Math.abs(opponent.getY() - _Y);
                        int d = dX * dX + dY * dY;
                        if (d <= 2) {
                            n++;
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return n;
    }

    public int getTackleZoneNumber(dSquare s) {
        int n = 0;

        Vector opponentPlayers = new Vector();

        try {
            if (_isChallenger) {
                opponentPlayers = _match.getRightTeam().getPlayers();
            } else {
                opponentPlayers = _match.getLeftTeam().getPlayers();
            }

            for (int i = 0; i < opponentPlayers.size(); i++) {
                rmiPlayer opponent = (rmiPlayer) opponentPlayers.get(i);

                if (opponent.isOnPitch()) {
                    if ((opponent.getState() == C_STATE_OK) ||
                            (opponent.getState() == C_STATE_ROOTED)) {
                        int dX = Math.abs(opponent.getX() - s.getX());
                        int dY = Math.abs(opponent.getY() - s.getY());
                        int d = dX * dX + dY * dY;
                        if (d <= 2) {
                            n++;
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return n;
    }

    public int getAdjacentOpponentNumber() {
        int n = 0;

        Vector opponentPlayers = new Vector();

        try {
            if (_isChallenger) {
                opponentPlayers = _match.getRightTeam().getPlayers();
            } else {
                opponentPlayers = _match.getLeftTeam().getPlayers();
            }


            for (int i = 0; i < opponentPlayers.size(); i++) {
                rmiPlayer opponent = (rmiPlayer) opponentPlayers.get(i);

                if (opponent.isOnPitch()) {
                    if ((opponent.getState() == C_STATE_OK) ||
                            (opponent.getState() == C_STATE_ROOTED) || (opponent.getState() == C_STATE_NO_ZONE)) {
                        int dX = Math.abs(opponent.getX() - _X);
                        int dY = Math.abs(opponent.getY() - _Y);
                        int d = dX * dX + dY * dY;
                        if (d <= 2) {
                            n++;
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return n;
    }

    public int getDiceForBlock(dSquare s) {
        int dices = daBlock.DICE_NO;

        dTeam opponentTeam = null;
        dTeam myTeam = null;

        if (_isChallenger) {
            myTeam = _match.getLeftTeam();
            opponentTeam = _match.getRightTeam();
        } else {
            myTeam = _match.getRightTeam();
            opponentTeam = _match.getLeftTeam();
        }

        if (opponentTeam.isAPlayer(s)) {
            dPlayer defender = opponentTeam.getPlayer(s);
            if ((defender.getState() == C_STATE_NO_ZONE) ||
                    (defender.getState() == C_STATE_OK) ||
                    (defender.getState() == C_STATE_ROOTED)) {
                int forceA = this.getStrength();
                int forceD = defender.getStrength();

                /*
                 * Soutient defenseur
                 */
                for (int j = 0; j < opponentTeam.getPlayers().size(); j++) {
                    dPlayer player = (dPlayer) opponentTeam.getPlayers().get(j);
                    if (player != defender) {
                        if (player.isAdjacent(this)) {
                            if (player.getTackleZoneNumber() == 1) {
                                forceD++;
                            }
                        }
                    }
                }
                /*
                 * Soutient attaquant
                 */
                for (int j = 0; j < myTeam.getPlayers().size(); j++) {
                    dPlayer player = (dPlayer) myTeam.getPlayers().get(j);
                    if (player != this) {
                        if (player.isAdjacent(defender)) {
                            if (player.getAdjacentOpponentNumber() == 1) {
                                forceA++;
                            }
                        }
                    }
                }

                if (forceA == forceD) {
                    dices = daBlock.DICE_ONE;
                } else {
                    if (forceA > 2 * forceD) {
                        dices = daBlock.DICE_THREE_FOR;
                    } else {
                        if (2 * forceA < forceD) {
                            dices = daBlock.DICE_THREE_AGAINST;
                        } else {
                            if (forceA > forceD) {
                                dices = daBlock.DICE_TWO_FOR;
                            } else {
                                dices = daBlock.DICE_TWO_AGAINST;
                            }
                        }
                    }
                }
            }
        }
        return dices;
    }

    public boolean canFoul(dSquare s) {
        boolean foul = false;

        dTeam opponentTeam = null;
        dTeam myTeam = null;

        if (_isChallenger) {
            myTeam = _match.getLeftTeam();
            opponentTeam = _match.getRightTeam();
        } else {
            myTeam = _match.getLeftTeam();
            opponentTeam = _match.getRightTeam();
        }

        if (opponentTeam.isAPlayer(s)) {
            dPlayer defender = opponentTeam.getPlayer(s);
            if ((defender.getState() == C_STATE_PRONE) ||
                    (defender.getState() == C_STATE_STUNNED)) {
                foul = true;

            }
        }
        return foul;
    }

    public boolean canHypno(dSquare s) {
        boolean Hypno = false;

        dTeam opponentTeam = null;
        dTeam myTeam = null;

        if (_isChallenger) {
            myTeam = _match.getLeftTeam();
            opponentTeam = _match.getRightTeam();
        } else {
            myTeam = _match.getLeftTeam();
            opponentTeam = _match.getRightTeam();
        }

        if (opponentTeam.isAPlayer(s)) {
            dPlayer defender = opponentTeam.getPlayer(s);
            if (defender.getState() == C_STATE_OK) {
                Hypno = true;

            }
        }
        return Hypno;
    }

    public boolean canHandOff(dSquare s) {
        boolean handoff = false;

        dTeam myTeam = null;

        if (_isChallenger) {
            myTeam = _match.getLeftTeam();
        } else {
            myTeam = _match.getLeftTeam();
        }

        if (myTeam.isAPlayer(s)) {
            dPlayer partner = myTeam.getPlayer(s);
            if ((partner.getState() == C_STATE_OK) ||
                    (partner.getState() == C_STATE_ROOTED)) {
                handoff = true;

            }
        }
        return handoff;
    }

    public boolean isAnOpponent(dSquare s) {
        dTeam opponentTeam = null;
        if (_isChallenger) {
            opponentTeam = _match.getRightTeam();
        } else {
            opponentTeam = _match.getRightTeam();
        }
        return opponentTeam.isAPlayer(s);
    }

    public boolean isNeighbourRightStuff(dSquare s) {
        boolean stunty = false;

        dTeam myTeam = null;

        if (_isChallenger) {
            myTeam = _match.getLeftTeam();
        } else {
            myTeam = _match.getLeftTeam();
        }

        if (myTeam.isAPlayer(s)) {
            dPlayer partner = myTeam.getPlayer(s);
            if (partner.isRightStuff()) {
                stunty = true;

            }
        }
        return stunty;
    }

    public boolean isAdjacent(rmiPlayer p) {
        boolean present = false;

        try {
            double dist = (p.getX() - getX()) * (p.getX() - getX()) + ((p.getY() - getY()) * (p.getY() - getY()));
            if (dist < 3) {
                present = true;
            }
        } catch (RemoteException e) {
            System.out.println(e.getLocalizedMessage());
        }


        return present;
    }

    protected boolean isRightStuff() {
        for (int i = 0; i < _competences.size(); i++) {
            dCompetence comp = (dCompetence) _competences.get(i);
            if (comp instanceof dcRightStuff) {
                return true;
            }
        }
        return false;
    }

    public Vector getPushedSquares(int X, int Y) {
        Vector Squares = new Vector();

        /**
         * Buil the square list
         */
        if (_X - X == 0) {
            if (_Y - Y == 1) {
                /*
                Y+1
                X-1,X,X+1*/
                Squares.add(new dSquare(_X - 1, _Y + 1));
                Squares.add(new dSquare(_X, _Y + 1));
                Squares.add(new dSquare(_X + 1, _Y + 1));
            } else {
                Squares.add(new dSquare(_X - 1, _Y - 1));
                Squares.add(new dSquare(_X, _Y - 1));
                Squares.add(new dSquare(_X + 1, _Y - 1));
            }
        } else {
            if (_X - X == 1) {
                if (_Y - Y == 0) {
                    Squares.add(new dSquare(_X + 1, _Y - 1));
                    Squares.add(new dSquare(_X + 1, _Y));
                    Squares.add(new dSquare(_X + 1, _Y + 1));
                } else {
                    if (_Y - Y == 1) {
                        Squares.add(new dSquare(_X + 1, _Y + 1));
                        Squares.add(new dSquare(_X + 1, _Y));
                        Squares.add(new dSquare(_X, _Y + 1));
                    } else {
                        Squares.add(new dSquare(_X + 1, _Y - 1));
                        Squares.add(new dSquare(_X, _Y - 1));
                        Squares.add(new dSquare(_X + 1, _Y));
                    }
                }
            } else {
                if (_Y - Y == 0) {
                    Squares.add(new dSquare(_X - 1, _Y - 1));
                    Squares.add(new dSquare(_X - 1, _Y));
                    Squares.add(new dSquare(_X - 1, _Y + 1));
                } else {
                    if (_Y - Y == 1) {
                        Squares.add(new dSquare(_X - 1, _Y + 1));
                        Squares.add(new dSquare(_X - 1, _Y));
                        Squares.add(new dSquare(_X, _Y + 1));
                    } else {
                        Squares.add(new dSquare(_X - 1, _Y - 1));
                        Squares.add(new dSquare(_X, _Y - 1));
                        Squares.add(new dSquare(_X - 1, _Y));
                    }
                }
            }
        }

        /**
         * Remove the squares out of ground
         */
        Vector tmp = new Vector(Squares);
        for (int i = 0; i < Squares.size(); i++) {
            dSquare s = (dSquare) Squares.get(i);
            if (s.getX() > 25) {
                tmp.remove(s);
            }
            if (s.getX() < 0) {
                tmp.remove(s);
            }
            if (s.getY() > 14) {
                tmp.remove(s);
            }
            if (s.getY() < 0) {
                tmp.remove(s);
            }
        }

        if (tmp.size() == 0) {
            return tmp;
        }

        /**
         * Remove squares where a player is
         */
        Squares = tmp;
        tmp = new Vector(Squares);
        for (int i = 0; i < Squares.size(); i++) {
            dSquare s = (dSquare) Squares.get(i);
            if (_match.isAPlayer(s)) {
                tmp.remove(s);
            }
        }

        if (tmp.size() == 0) {
            return Squares;
        } else {
            return tmp;
        }
    }
    /**
     * OLD
     */
//dMatch _model;
    public dAction _currentAction;
    public int _moveLeft;
    public boolean _missMatch;
    public int _persistantInjuries;
    public boolean _mustDodge;
    public boolean _canBlock = false;
    public boolean _canBlitz = false;
    public boolean _canPass = false;
    public boolean _canHandOff = false;
    public boolean _canFoul = false;

    public int[] getAvailableActions() {
        Vector v = new Vector();

        v.add(dAction.C_MOVE);
        v.add(dAction.C_HANDOFF);
        v.add(dAction.C_PASS);
        v.add(dAction.C_FOUL);
        v.add(dAction.C_BLITZ);

        if (getAdjacentOpponentNumber() > 0) {
            v.add(dAction.C_BLOCK);
        }

        int[] tab = new int[v.size()];

        for (int i = 0; i < v.size(); i++) {
            tab[i] = (Integer) v.get(i);
        }

        return tab;
    }

    /**
     * Creates a new instance of dPlayer
     */
    public dPlayer(dMatch model, String position, String race, int teamNumber) {
        _currentAction = null;
        //_model = model;
        _position =
                position;
        _race =
                race;
        _state =
                0;
        _movement =
                6;
        _strength =
                3;
        _agility =
                3;
        _armor =
                7;

        _competences =
                new Vector();
        for (short j = 0; j <
                3; j++) {
        }
    }

    public Vector getAroundSquares() {
        Vector v = new Vector();
        int x = _X;
        int y = _Y;

        if ((x > 0) && (y > 0)) {
            dSquare s1 = new dSquare(x - 1, y - 1);
            v.add(s1);
        }

        if (y > 0) {
            dSquare s2 = new dSquare(x, y - 1);
            v.add(s2);
        }

        if ((x < 25) && (y > 0)) {
            dSquare s3 = new dSquare(x + 1, y - 1);
            v.add(s3);
        }

        if (x > 0) {
            dSquare s4 = new dSquare(x - 1, y);
            v.add(s4);
        }

        if (x < 25) {
            dSquare s5 = new dSquare(x + 1, y);
            v.add(s5);
        }

        if ((x > 0) && (y < 14)) {
            dSquare s6 = new dSquare(x - 1, y + 1);
            v.add(s6);
        }

        if (y < 14) {
            dSquare s7 = new dSquare(x, y + 1);
            v.add(s7);
        }

        if ((x < 25) && (y < 14)) {
            dSquare s8 = new dSquare(x + 1, y + 1);
            v.add(s8);
        }

        return v;
    }

    public Vector getFanaticSquares() {
        Vector v = new Vector();
        int x = _X;
        int y = _Y;

        if (y > 0) {
            dSquare s2 = new dSquare(x, y - 1);
            v.add(s2);
        }

        if (x > 2) {
            dSquare s4 = new dSquare(x - 1, y);
            v.add(s4);
        }

        if (x < 26) {
            dSquare s5 = new dSquare(x + 1, y);
            v.add(s5);
        }

        if (y <= 14) {
            dSquare s7 = new dSquare(x, y + 1);
            v.add(s7);
        }

        return v;
    }

    public String getStringMovement() {
        return Integer.toString(_movement);
    }

    public String getStringStrength() {
        return Integer.toString(_strength);
    }

    public String getStringAgility() {
        return Integer.toString(_agility);
    }

    public String getStringArmor() {
        return Integer.toString(_armor);
    }

    /* public boolean isAvailable() {
    boolean result = false;
    if (_Injury == 0) {
    result = true;
    }
    return result;
    }
    public boolean isSelectable() {
    if (_Injury == 0) {
    if (!_havePlayed) {
    /*
     * Stunned
     */
    /*      if (_State != 2) {
    return true;
    }
    }
    }
    return false;
    }*/
    public void setSquare(dSquare square) {
        setX(square.getX());
        setY(square.getY());
    }

    public int getRelativePosition(dPlayer player) {
        int position = 0;

        if (_X == player._X) {
            if (_Y - 1 == player._X) {
                position = 2;
            } else {
                position = 7;
            }

        } else {
            if (_X - 1 == player._X) {
                if (_Y - 1 == player._Y) {
                    position = 1;
                } else {
                    if (_Y + 1 == player._Y) {
                        position = 6;
                    } else {
                        position = 4;
                    }

                }
            } else {
                if (_Y - 1 == player._Y) {
                    position = 3;
                } else {
                    if (_Y + 1 == player._Y) {
                        position = 8;
                    } else {
                        position = 5;
                    }

                }
            }
        }
        return position;
    }

    public boolean mustFollow() {
        return false;
    }

    public double getDistance(rmiPlayer p) {
        double dist=0;
        try {
            dist = (p.getX() - getX()) * (p.getX() - getX()) + ((p.getY() - getY()) * (p.getY() - getY()));
            dist=Math.sqrt(dist);
        } catch (RemoteException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return dist;
    }
}
