/*
 * daPass.java
 *
 * Created on 28 novembre 2007, 18:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Actions;

import bbos.Match.Model.dPlayer;
import bbos.Match.Model.dSquare;
import bbos.*;
import bbos.Match.Automat.DuringMatch.jdgMatch;
import bbos.Match.Model.Roll.Agility.draGetBall;
import bbos.Match.Model.Roll.Agility.draPass;
import bbos.Match.Model.Roll.Agility.draThrowTeamMate;
import bbos.Match.Model.Roll.drAgility;
import bbos.Match.Model.Roll.drArmor;
import bbos.Match.Model.Roll.drInjury;
import bbos.Match.Model.Roll.drScatter;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiTeam;
import java.awt.Color;
import java.rmi.RemoteException;
import java.util.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrateur
 */
public class daThrowTeamMate extends dAction {

    final static int C_STEP_NONE = 0;
    final static int C_STEP_MOVE = 1;
    final static int C_STEP_TARGET = 2;
    final static int C_STEP_THROW = 3;
    final static int C_STEP_LAND = 4;
    final static int C_STEP_END = 5;
    daMove _move;
    rmiPlayer _thrower;
    rmiPlayer _thrown;
    dSquare _target;
    rmiMatch _model;
    rmiTeam _myTeam;
    Vector _myPlayers;
    rmiTeam _opponentTeam;
    Vector _opponentPlayers;
    boolean _displayPassRules = false;
    protected static ImageIcon _icon = new ImageIcon(dAction.class.getResource("/resources/images/actions/throwteammate.gif"));

    public ImageIcon getIcon() {
        return _icon;
    }

    public void resetStep() {
        try {

            _model.resetSquareMove();
            _model.resetSquarePass();
            _model.resetSquareBlock();
            _model.resetSquareFoul();

            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);

            _step = 0;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /** Creates a new instance of daPass */
    public daThrowTeamMate(rmiMatch model, rmiPlayer player, rmiTeam opponent, rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers,boolean isChallenger) {
        super(C_THROW_TEAM_MATE);

        _model = model;
        _thrower = player;
        _move = new daMove(model, player);

        _myPlayers = myPlayers;
        _myTeam = myTeam;

        _step = C_STEP_MOVE;
    }

    protected double distance(int X1, int Y1, int X2, int Y2) {
        return Math.sqrt((X1 - X2) * (X1 - X2) + (Y1 - Y2) * (Y1 - Y2));
    }

    public void selectSquares() {
        /*
         * Select squares for move.
         */

        try {
            if (_thrown != null) {
                _displayPassRules = true;
            } else {
                _move.selectSquares();

                Vector v = _thrower.getAroundSquares();
                int i = 0;
                for (i = 0; i < v.size(); i++) {
                    dSquare s = (dSquare) v.get(i);
                    if (_thrower.isNeighbourRightStuff(s)) {
                        _model.setSquareState(s, dSquare.C_SQUARE_PARTNER_TO_PASS);
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public boolean displayPassRange() {
        return _displayPassRules;
    }

    public void preStep() {
        try {
            _move.setAvailableMove(_thrower.getMovement());
            _move.setAdditionalMove(2);
            if (_thrower.getState() == dPlayer.C_STATE_PRONE) {
                _move.setAvailableMove(Math.max(_move.getAvailableMove() - 3, 0));
            }

            selectSquares();

            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void step(dSquare s) {
        try {
            if (_step == C_STEP_MOVE) {
                int state = _model.getSquareState(s);

                if (state == dSquare.C_SQUARE_MOVE) {
                    _move.step(s);
                } else {
                    if (state == dSquare.C_SQUARE_PARTNER_TO_PASS) {

                        int nb = _myTeam.getPlayerNumber(s.getX(), s.getY());
                        _thrown = (rmiPlayer) _myPlayers.get(nb);
                        _step = C_STEP_TARGET;
                    }
                }
            }

            if (_step == C_STEP_TARGET) {

                if (distance(_thrower.getX(), _thrower.getY(), s.getX(), s.getY()) < (13 / 4.0) * 2) {
                    _target = s;
                    computePassRule();
                    _step = C_STEP_THROW;
                }
            }

            if (_step == C_STEP_THROW) {
                draThrowTeamMate pass = new draThrowTeamMate(_thrower);
                pass.setPassRange(_range);
                pass.addModifiers(0 - _thrower.getTackleZoneNumber());
                int roll = pass.rollDices();
                if (pass.isSuccess(roll)) {
                    PlayerScatters(3, s);
                } else {
                    /**
                     * Atterrissage forcé !
                     **/
                    PlayerScatters(1, s);

                }
                _step = C_STEP_LAND;
            }

            if (_step == C_STEP_LAND) {

                /**
                 * Atterrissage
                 */
                while (_model.isAPlayer(new dSquare(_thrown.getX(), _thrown.getY()))) {

                    int nb = _opponentTeam.getPlayerNumber(_thrown.getX(), _thrown.getY());
                    if (nb > -1) {
                        rmiPlayer opp = (rmiPlayer) _opponentPlayers.get(nb);
                        opp.setState(dPlayer.C_STATE_PRONE);

                        if (opp.hasBall()) {
                            ballBounces(new dSquare(_thrown.getX(), _thrown.getY()));
                        }

                        drArmor armor = new drArmor();
                        drInjury injury = new drInjury();
                        int ar = armor.rollDices();
                        if (armor.isSuccess(opp, ar)) {
                            int inj = injury.rollDices();
                            injury.applyEffects(inj, opp);
                        }

                    } else {

                        nb = _myTeam.getPlayerNumber(_thrown.getX(), _thrown.getY());
                        if (nb > -1) {
                            rmiPlayer me = (rmiPlayer) _opponentPlayers.get(nb);
                            me.setState(dPlayer.C_STATE_PRONE);

                            if (me.hasBall()) {
                                ballBounces(new dSquare(_thrown.getX(), _thrown.getY()));
                            }

                            drArmor armor = new drArmor();
                            drInjury injury = new drInjury();
                            int ar = armor.rollDices();
                            if (armor.isSuccess(me, ar)) {
                                int inj = injury.rollDices();
                                injury.applyEffects(inj, me);
                            }

                            _model.turnover(true);
                        }
                    }

                    this.PlayerScatters(1, new dSquare(_thrown.getX(), _thrown.getY()));
                }

                if (_thrown.hasBall()) {
                    _thrown.setBall(false);
                    ballBounces(new dSquare(_thrown.getX(), _thrown.getY()));
                    _model.turnover(true);
                }


                /**
                 * Atterrissage normal
                 */
                int tz = _thrown.getTackleZoneNumber();
                drAgility ag = new drAgility(_thrown);
                ag.addModifiers(-tz);
                int roll = ag.rollDices();
                if (ag.isSuccess(roll)) {
                    _step = C_STEP_END;
                } else {
                    drArmor armor = new drArmor();
                    drInjury injury = new drInjury();
                    int ar = armor.rollDices();
                    if (armor.isSuccess(_thrown, ar)) {
                        int inj = injury.rollDices();
                        injury.applyEffects(inj, _thrown);
                    }

                    if (_thrown.hasBall()) {
                        _thrown.setBall(false);
                        ballBounces(new dSquare(_thrown.getX(), _thrown.getY()));
                        _model.turnover(true);
                    }
                }

                _thrower.hasPlayed(true);
                _thrower.isPlaying(false);
                _thrower.isActive(false);
                _step = C_STEP_END;

            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void postStep() {
        try {
            _thrower.hasPlayed(true);
            _thrower.isPlaying(false);
            _thrower.isActive(false);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return "Throw a team mate";
    }

    public int getAvailableMove() {
        return _move.getAvailableMove();
    }

    public int getAdditionalMove() {
        return _move.getAdditionalMove();
    }
    int _x_dots[];
    int _y_dots[];
    Color _color;
    int _range;
    double _passRatio = 3.4;

    public int[] getXdots() {
        return _x_dots;
    }

    public int[] getYdots() {
        return _y_dots;
    }

    protected void computePassRule() {
        try {
            int x_ref = _thrower.getX() * jdgMatch.C_SQUARE_SIZE + jdgMatch.C_SQUARE_SIZE / 2;
            int y_ref = _thrower.getY() * jdgMatch.C_SQUARE_SIZE + jdgMatch.C_SQUARE_SIZE / 2;

            if (_target != null) {
                int x = _target.getX() * jdgMatch.C_SQUARE_SIZE + jdgMatch.C_SQUARE_SIZE / 2;
                int y = _target.getY() * jdgMatch.C_SQUARE_SIZE + jdgMatch.C_SQUARE_SIZE / 2;
                double distance = Math.sqrt((x_ref - x) * (x_ref - x) + (y_ref - y) * (y_ref - y));

                _range = 0;
                int max_range = 2;
                if (distance > 0) {
                    int dx = (x - x_ref);
                    int dy = (y - y_ref);
                    double costheta = dx / distance;
                    double sintheta = dy / distance;

                    if ((distance < _passRatio * jdgMatch.C_SQUARE_SIZE) && (max_range >= 1)) {
                        _color = new Color(7, 119, 39);
                        _range = draPass.QUICK;
                    } else {
                        if ((distance < 2 * _passRatio * jdgMatch.C_SQUARE_SIZE) && (max_range >= 2)) {
                            _color = new Color(237, 239, 27);
                            _range = draPass.SHORT;
                        }
                    }

                    if (_range != 0) {
                        /* Segment initial*/
                        _x_dots[0] = 0;
                        _x_dots[1] = 0;
                        _x_dots[2] = (int) (distance * costheta);
                        _x_dots[3] = (int) (distance * costheta);

                        _y_dots[0] = 0;
                        _y_dots[1] = 0;
                        _y_dots[2] = (int) (distance * sintheta);
                        _y_dots[3] = (int) (distance * sintheta);

                        /*
                         * Transation suivants 2 vecteurs
                         */
                        _x_dots[0] -= (int) (jdgMatch.C_SQUARE_SIZE * sintheta);
                        _x_dots[1] += (int) (jdgMatch.C_SQUARE_SIZE * sintheta);
                        _x_dots[2] += (int) (jdgMatch.C_SQUARE_SIZE * sintheta);
                        _x_dots[3] -= (int) (jdgMatch.C_SQUARE_SIZE * sintheta);

                        _y_dots[0] += (int) (jdgMatch.C_SQUARE_SIZE * costheta);
                        _y_dots[1] -= (int) (jdgMatch.C_SQUARE_SIZE * costheta);
                        _y_dots[2] -= (int) (jdgMatch.C_SQUARE_SIZE * costheta);
                        _y_dots[3] += (int) (jdgMatch.C_SQUARE_SIZE * costheta);

                        /*
                         * Translation
                         */
                        _x_dots[0] += x_ref;
                        _x_dots[1] += x_ref;
                        _x_dots[2] += x_ref;
                        _x_dots[3] += x_ref;

                        _y_dots[0] += y_ref;
                        _y_dots[1] += y_ref;
                        _y_dots[2] += y_ref;
                        _y_dots[3] += y_ref;

                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void ballBounces(dSquare s) {
        boolean ballStop = false;
        dSquare fromSq = s;
        boolean catched = false;

        try {
            while ((!ballStop) && (!catched)) {
                drScatter rollscatter = new drScatter(_model, fromSq, true, true);
                int r = rollscatter.rollDices();
                _model.AddDiary("Scatter roll: " + r);
                rollscatter.applyEffects(r);
                fromSq = rollscatter.getCurrentSquare();

                rmiPlayer tmp_player = null;
                int i = _myTeam.getPlayerNumber(fromSq.getX(), fromSq.getY());
                {
                    if (i > -1) {
                        tmp_player = (rmiPlayer) _myPlayers.get(i);
                    } else {
                        i = _opponentTeam.getPlayerNumber(fromSq.getX(), fromSq.getY());
                        if (i > -1) {
                            tmp_player = (rmiPlayer) _opponentPlayers.get(i);
                        }
                    }
                }
                if (tmp_player != null) {
                    draGetBall getBall_roll = new draGetBall(tmp_player);
                    int tz = tmp_player.getTackleZoneNumber();
                    getBall_roll.addModifiers(-tz);
                    int result = getBall_roll.rollDices();
                    _model.AddDiary("Get the ball roll: " + result);
                    if (getBall_roll.isSuccess(result)) {
                        tmp_player.setBall(true);
                        catched = true;
                    }

                } else {
                    ballStop = true;
                    _model.setBall(fromSq);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void PlayerScatters(int nb, dSquare s) {

        dSquare fromSq = s;

        try {

            drScatter rollscatter = new drScatter(_model, fromSq, false, false);
            int r = rollscatter.rollDices();
            for (int i = 0; i < nb; i++) {
                _model.AddDiary("Scatter roll: " + r);
                rollscatter.applyEffects(r);
                fromSq = rollscatter.getCurrentSquare();
            }

            if (fromSq.isOnGround()) {
                _thrown.setX(fromSq.getX());
                _thrown.setY(fromSq.getY());
            } else {
                drInjury injury = new drInjury();
                int inj = injury.rollDices();
                injury.applyEffects(inj, _thrown);
                _thrown.setOnPitch(false);
                if (_thrown.hasBall()) {
                    _thrown.setBall(false);
                    scatterBall(fromSq);
                }
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void scatterBall(dSquare s) {
        boolean ballStop = false;
        dSquare fromSq = s;
        boolean catched = false;

        try {
            while ((!ballStop) && (!catched)) {
                drScatter rollscatter = new drScatter(_model, fromSq, true, true);
                int r = rollscatter.rollDices();
                _model.AddDiary("Scatter roll: " + r);
                rollscatter.applyEffects(r);
                fromSq = rollscatter.getCurrentSquare();

                rmiPlayer tmp_player = null;
                int i = _myTeam.getPlayerNumber(fromSq.getX(), fromSq.getY());
                {
                    if (i > -1) {
                        tmp_player = (rmiPlayer) _myPlayers.get(i);
                    } else {
                        i = _opponentTeam.getPlayerNumber(fromSq.getX(), fromSq.getY());
                        if (i > -1) {
                            tmp_player = (rmiPlayer) _opponentPlayers.get(i);
                        }
                    }
                }
                if (tmp_player != null) {
                    draGetBall getBall_roll = new draGetBall(tmp_player);
                    int tz = tmp_player.getTackleZoneNumber();
                    getBall_roll.addModifiers(-tz);
                    int result = getBall_roll.rollDices();
                    _model.AddDiary("Get the ball roll: " + result);
                    if (getBall_roll.isSuccess(result)) {
                        tmp_player.setBall(true);
                        catched = true;
                    }

                } else {
                    ballStop = true;
                    _model.setBall(fromSq);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
