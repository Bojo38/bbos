/*
 * daPass.java
 *
 * Created on 28 novembre 2007, 18:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Actions;

import bbos.Match.Model.dSquare;
import bbos.*;
import bbos.Match.Automat.DuringMatch.jdgMatch;
import bbos.Match.Model.Roll.Agility.draCatch;
import bbos.Match.Model.Roll.Agility.draGetBall;
import bbos.Match.Model.Roll.Agility.draInterception;
import bbos.Match.Model.Roll.Agility.draPass;
import bbos.Match.Model.Roll.drScatter;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiTeam;
import java.awt.Color;
import java.rmi.RemoteException;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrateur
 */
public class daPass extends dAction {

    final static int C_STEP_NONE = 0;
    final static int C_STEP_MOVE = 1;
    final static int C_STEP_CATCH = 2;
    final static int C_STEP_INTERCEPTION = 3;
    final static int C_STEP_THROW = 4;
    final static int C_STEP_INTERCEPTOR = 5;
    final static int C_STEP_TURNOVER = 6;
    final static int C_STEP_END = 7;
    boolean _displayPassRules = false;
    daMove _move;
    rmiPlayer _thrower;
    rmiPlayer _catcher;
    dSquare _target;
    rmiMatch _model;
    rmiTeam _myTeam;
    Vector _myPlayers;
    rmiTeam _opponentTeam;
    Vector _opponentPlayers;
    boolean _isChallenger;
    protected static ImageIcon _icon = new ImageIcon(dAction.class.getResource("/resources/images/actions/pass.gif"));

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
    public daPass(rmiMatch model, rmiPlayer player, rmiTeam opponent, rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers, boolean isChallenger) {
        super(C_PASS);

        _model = model;
        _thrower = player;
        _move = new daMove(model, player);

        _myPlayers = myPlayers;
        _myTeam = myTeam;
        _opponentPlayers = opponentPlayers;
        _opponentTeam = opponent;
        _isChallenger = isChallenger;
        _step = C_STEP_MOVE;
    }

    protected double distance(int X1, int Y1, int X2, int Y2) {
        return Math.sqrt((X1 - X2) * (X1 - X2) + (Y1 - Y2) * (Y1 - Y2));
    }

    public void selectSquares() {
        /*
         * Select squares for move.
         */
        if (_thrower != null) {
            _move.selectSquares();

            try {
                if (_thrower.hasBall()) {
                    _model.displayPassRule(true);

                    for (int i = 0; i < _myPlayers.size(); i++) {
                        rmiPlayer player = (rmiPlayer) _myPlayers.get(i);
                        int max_range = _model.getAllowedPassRange();
                        if (distance(_thrower.getX(), _thrower.getY(), player.getX(), player.getY()) <= ((13 / 4.0) * max_range)) {
                            _model.setSquareState(new dSquare(player.getX(), player.getY()), dSquare.C_SQUARE_PARTNER_TO_PASS);
                        }
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void preStep() {
        try {
            _move.setAvailableMove(_thrower.getMovement());
            _move.setAdditionalMove(2);

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

                        _step = C_STEP_INTERCEPTION;
                        int nb = _myTeam.getPlayerNumber(s.getX(), s.getY());
                        _catcher = (rmiPlayer) _myPlayers.get(nb);
                        _target = new dSquare(_catcher.getX(), _catcher.getY());
                        computePassRule();
                    } else {
                        if (distance(_thrower.getX(), _thrower.getY(), s.getX(), s.getY()) < (13 / 4.0) * _range) {
                            int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to send ball to an empty square ?", "Empty square", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
                            if (resp == JOptionPane.YES_OPTION) {
                                _target = s;
                                _step = C_STEP_INTERCEPTION;
                            }
                        }
                    }
                }
            }

            if (_step == C_STEP_INTERCEPTION) {
                if (_interceptorList.size() > 0) {
                    for (int i = 0; i < _interceptorList.size(); i++) {
                        rmiPlayer opp = (rmiPlayer) _interceptorList.get(i);
                        _model.setSquareState(new dSquare(opp.getX(), opp.getY()), dSquare.C_SQUARE_OPPONENT_INTERCEPT);
                    }
                    _step = C_STEP_INTERCEPTOR;
                } else {
                    _step = C_STEP_THROW;
                }
            }

            if (_step == C_STEP_INTERCEPTOR) {

                int chooser = 2;
                if (_isChallenger) {
                    chooser = 1;
                }
                _model.WaitForInterceptionChoice(chooser);
            }

            if (_step == C_STEP_THROW) {
                draPass pass = new draPass(_thrower);
                pass.setPassRange(_range);
                pass.addModifiers(0 - _thrower.getTackleZoneNumber());
                int roll = pass.rollDices();

                _thrower.setBall(false);

                /*
                 * Fumble
                 */
                if (roll == 1) {
                    this.ballBounces(new dSquare(_thrower.getX(), _thrower.getY()));
                    _step = C_STEP_TURNOVER;
                } else {
                    if (pass.isSuccess(roll)) {
                        _step = C_STEP_CATCH;
                    } else {
                        /**
                         * Dispersion
                         */
                        this.ballScatters3(_target);
                        _step = C_STEP_TURNOVER;
                    }
                }
            }

            if (_step == C_STEP_CATCH) {
                if (_catcher != null) {
                    draCatch catchRoll = new draCatch(_catcher);
                    catchRoll.addModifiers(0 - _catcher.getTackleZoneNumber());
                    if (catchRoll.isSuccess(catchRoll.rollDices())) {
                        _catcher.setBall(true);
                    } else {
                        ballBounces(_target);
                    }
                    _step = C_STEP_TURNOVER;
                } else {
                    ballBounces(_target);
                }
            }

            if (_step == C_STEP_TURNOVER) {
                boolean turnover = true;
                for (int i = 0; i < _myPlayers.size(); i++) {
                    rmiPlayer p = (rmiPlayer) _myPlayers.get(i);
                    if (p.hasBall()) {
                        turnover = false;
                        break;
                    }
                }
                if (turnover) {
                    _model.turnover(true);
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

    public void setInterceptor(dSquare s) {
        try {
            if (_model.getSquareState(s) == s.C_SQUARE_OPPONENT_INTERCEPT) {
                int nb=_opponentTeam.getPlayerNumber(s.getX(), s.getY());
                rmiPlayer interceptor=(rmiPlayer)_opponentPlayers.get(nb);
                
                /**
                 * Jet d'interception
                 */
                draInterception inter=new draInterception(interceptor);
                inter.addModifiers(-interceptor.getTackleZoneNumber());
                int roll=inter.rollDices();
                /**
                 * Si réussite turnover et step_end
                 */
                if (inter.isSuccess(roll))
                {
                    _thrower.hasPlayed(false);
                    interceptor.hasPlayed(true);
                    _model.turnover(true);
                    _step=C_STEP_TURNOVER;
                }
                /*
                 * sinon _step=C_CATCH et appel à step 
                 */
                else
                {
                    _step=C_STEP_THROW;
                }
                
                step(s);
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
        return "Pass";
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
    Vector _interceptorList;

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
                int max_range = _model.getAllowedPassRange();
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
                        } else {
                            if ((distance < 3 * _passRatio * jdgMatch.C_SQUARE_SIZE) && (max_range >= 3)) {
                                _range = draPass.LONG;
                                _color = new Color(239, 147, 27);
                            } else {
                                if ((distance < 4 * _passRatio * jdgMatch.C_SQUARE_SIZE) && (max_range >= 4)) {
                                    _range = draPass.BOMB;
                                    _color = new Color(191, 15, 48);
                                }
                            }
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


                        /*
                         * Interception detection
                         */
                        _interceptorList = new Vector();
                        /*
                         * Si le centre de la case a une distance inf?rieure ? la longueur
                         * de passe, et que la hauteur avec le segment est inf?rieur ? 30
                         */
                        for (int i = 0; i < _opponentPlayers.size(); i++) {

                            rmiPlayer opp = (rmiPlayer) _opponentPlayers.get(i);

                            int xc = opp.getX() * jdgMatch.C_SQUARE_SIZE + jdgMatch.C_SQUARE_SIZE / 2;
                            int yc = opp.getY() * jdgMatch.C_SQUARE_SIZE + jdgMatch.C_SQUARE_SIZE / 2;
                            double dac = Math.sqrt((x_ref - xc) * (x_ref - xc) + (y_ref - yc) * (y_ref - yc));
                            double dbc = Math.sqrt((x - xc) * (x - xc) + (y - yc) * (y - yc));
                            double h = Math.sqrt(dbc * dbc - (dbc * dbc + distance * distance - dac * dac) * (dbc * dbc + distance * distance - dac * dac) / (4 * distance * distance));

                            if (h <= jdgMatch.C_SQUARE_SIZE + 0.1) {
                                double dah = Math.sqrt(dac * dac - h * h);
                                double dbh = Math.sqrt(dbc * dbc - h * h);
                                if ((dah <= distance + 0.1) && (dbh <= distance + 0.1)) {
                                    _interceptorList.add(opp);
                                }
                            }
                        }
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

    protected void ballScatters3(dSquare s) {
        boolean ballStop = false;
        dSquare fromSq = s;
        boolean catched = false;

        try {
            while ((!ballStop) && (!catched)) {
                drScatter rollscatter = new drScatter(_model, fromSq, true, false);
                int r = rollscatter.rollDices();
                for (int i = 0; i < 3; i++) {
                    _model.AddDiary("Scatter roll: " + r);
                    rollscatter.applyEffects(r);
                    fromSq = rollscatter.getCurrentSquare();

                }

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
