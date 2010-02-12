/*
 * daMove.java
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
import bbos.Match.Model.Roll.Agility.draDodge;
import bbos.Match.Model.Roll.Agility.draPickUp;
import bbos.Match.Model.Roll.drGoForIt;
import bbos.Match.Model.Roll.drScatter;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import bbos.Tools.bbTool;
import java.rmi.RemoteException;
import java.util.*;
import javax.swing.ImageIcon;

/**
 *
 * @author frederic
 */
public class daMove extends dAction {

    protected static ImageIcon _icon = new ImageIcon(dAction.class.getResource("/resources/images/actions/move.gif"));

    public ImageIcon getIcon() {
        return _icon;
    }

    public void resetStep() {
        try {
            _model.resetSquareMove();
            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);
            _step = 0;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    protected rmiMatch _model;
    protected rmiPlayer _player;
    protected int _available_move;
    protected int _additional_move;

    public void selectSquares() {
        if (_player != null) {
            try {
                _model.resetSquareMove();
                if (_player.getState() == dPlayer.C_STATE_PRONE) {
                    _model.setSquareState(new dSquare(_player.getX(), _player.getY()), dSquare.C_SQUARE_MOVE);
                } else {
                    Vector v = _player.getAroundSquares();
                    for (int i = 0; i < v.size(); i++) {
                        dSquare s = (dSquare) v.get(i);
                        if (!_model.isAPlayer((dSquare) v.get(i))) {
                            _model.setSquareState(s, dSquare.C_SQUARE_MOVE);
                            if ((_available_move == 0) && (_additional_move > 0)) {
                                _model.setSquareSpecialState(s, true);
                            }
                        }
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /** Creates a new instance of daMove */
    public daMove(rmiMatch model, rmiPlayer player) {
        super(C_MOVE);
        _model = model;
        _player = player;
        _additional_move = 2;

    }

    public void setAvailableMove(int move) {
        _available_move = move;
    }

    public void setAdditionalMove(int move) {
        _additional_move = move;
    }

    public int getAvailableMove() {
        return _available_move;
    }

    public int getAdditionalMove() {
        return _additional_move;
    }

    public void preStep() {
        try {
            this._available_move = _player.getMovement();
            if (_player.getState() == dPlayer.C_STATE_PRONE) {
                _available_move = _available_move - 3;
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

            if (_model.getSquareState(s) == dSquare.C_SQUARE_MOVE) {
                /**
                 * Si le joueur est à terre
                 */
                if (_player.getState() == dPlayer.C_STATE_PRONE) {
                    if ((s.getX() == _player.getX()) && (s.getY() == _player.getY())) {
                        boolean canGetUp = true;
                        if (_available_move == -1) {
                            int value = bbTool.getd6();
                            if (value >= 4) {
                                canGetUp = true;
                            } else {
                                canGetUp = false;
                            }
                        }
                        if (canGetUp) {
                            _player.setState(dPlayer.C_STATE_OK);
                            _player.isPlaying(true);
                            selectSquares();
                        } else {
                            _player.hasPlayed(true);
                            _player.isActive(false);
                            _end = true;
                        }
                    }
                } /**
                 * Si joueur debout
                 */
                else {

                    _player.isPlaying(true);
                    /**
                     * Peut-il bouger ?
                     */
                    boolean canMove = true;

                    if (_available_move > 0) {
                        _available_move--;
                    } else {
                        if (_additional_move > 0) {
                            _additional_move--;
                            drGoForIt gfiRoll = new drGoForIt(_player, _model);
                            int roll = gfiRoll.rollDices();
                            _model.AddDiary("Go For it roll : " + roll);
                            gfiRoll.applyEffects(roll);
                            if (!gfiRoll.isSuccess(roll)) {
                                if (_player.hasBall())
                                {
                                        drScatter scatter = new drScatter(_model, s, true, true);
                                        int dices = scatter.rollDices();
                                        scatter.applyEffects(dices);
                                        _model.setBall(scatter.getCurrentSquare());
                                        _player.setBall(false);
                                }        
                                _player.hasPlayed(true);
                                _player.isActive(false);
                                _player.isPlaying(false);
                                _model.turnover(true);
                            }

                        } else {
                            canMove = false;
                            _model.resetSquareMove();
                            _player.hasPlayed(true);
                            _player.isActive(false);
                            _player.isPlaying(false);
                        }
                    }

                    if (canMove) {
                        /*
                         * Si le joueur est en zone de tacle Esquive
                         */
                        boolean move_ok = true;
                        int nbTackleZone = _player.getTackleZoneNumber();
                        if (nbTackleZone > 0) {
                            draDodge dodge = new draDodge(_player, s, _model);
                            int nbTackleZoneDest = _player.getTackleZoneNumber(s);
                            dodge.addModifiers(-nbTackleZoneDest);
                            int dodge_value = dodge.rollDices();
                            _model.AddDiary("Dodge roll : " + dodge_value);
                            move_ok = dodge.applyEffects(dodge_value);
                            if (!dodge.isSuccess(dodge_value)) {
                                if (_player.hasBall())
                                {
                                        drScatter scatter = new drScatter(_model, s, true, true);
                                        int dices = scatter.rollDices();
                                        scatter.applyEffects(dices);
                                        _model.setBall(scatter.getCurrentSquare());
                                        _player.setBall(false);
                                }
                                _model.turnover(true);
                                _model.setRightRefresh(true);
                                _model.setLeftRefresh(true);
                            }
                        }

                        /**
                         * Si on est passé
                         */
                        if (move_ok) {
                            _player.setX(s.getX());
                            _player.setY(s.getY());

                            boolean square_has_ball = false;
                            /*
                             * Est-on dans une case à ballon
                             */
                            Vector squares = _model.getSquares().getBallSquares();
                            for (int i = 0; i < squares.size(); i++) {
                                dSquare tmp = (dSquare) squares.get(i);
                                if (tmp.getBallState() == dSquare.C_SQUARE_BALL_HAS_BALL) {
                                    if ((tmp.getX() == s.getX()) && (tmp.getY() == s.getY())) {
                                        square_has_ball = true;
                                    }
                                }
                            }
                            if (square_has_ball) {
                                if (_player.canGetBall()) {
                                    draPickUp pickUp = new draPickUp(_player);
                                    int dice = pickUp.rollDices();
                                    boolean picked_up = pickUp.isSuccess(dice);
                                    if (picked_up) {
                                        _player.setBall(true);
                                        _model.removeBall();
                                    } /*
                                     * Sinon dipersion
                                     */ else {
                                        drScatter scatter = new drScatter(_model, s, true, true);
                                        int dices = scatter.rollDices();
                                        scatter.applyEffects(dices);
                                        _model.turnover(true);
                                    }
                                }
                            }

                            if (_additional_move + _available_move > 0) {
                                selectSquares();
                            } else {
                                postStep();
                            }
                        } else {
                            _model.setRightRefresh(true);
                            _model.setLeftRefresh(true);
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void postStep() {
        try {
            _player.isPlaying(false);
            _player.isActive(false);
            _player.hasPlayed(true);
            _model.resetSquareBlock();
            _model.resetSquareFoul();
            _model.resetSquareMove();
            _model.resetSquarePass();
            _model.setRightRefresh(true);
            _model.setLeftRefresh(true);

            _step = 0;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return "Move";
    }
}
