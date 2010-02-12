/*
 * daFoul.java
 *
 * Created on December 3, 2007, 9:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Actions;

import bbos.Match.Model.Roll.drInjury;
import bbos.Match.Model.Roll.drArmor;
import bbos.Match.Model.dMatch;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.dSquare;
import bbos.Match.Views.cGround;
import bbos.*;
import bbos.Match.Model.Roll.Agility.draGetBall;
import bbos.Match.Model.Roll.drScatter;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiTeam;
import java.rmi.RemoteException;
import java.util.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author moi
 */
public class daFoul extends dAction {

    rmiMatch _model;
    rmiPlayer _player;
    daMove _move;
    protected static ImageIcon _icon = new ImageIcon(dAction.class.getResource("/resources/images/actions/foul.gif"));
    rmiTeam _myTeam;
    Vector _myPlayers;
    rmiTeam _opponentTeam;
    Vector _opponentPlayers;

    public ImageIcon getIcon() {
        return _icon;
    }

    /** Creates a new instance of daFoul */
    public daFoul(rmiMatch model, rmiPlayer player, rmiTeam opponent, rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers,boolean isChallenger) {
        super(C_FOUL);
        _model = model;
        _player = player;
        _move = new daMove(model, player);
        _myPlayers = myPlayers;
        _myTeam = myTeam;
        _opponentPlayers = opponentPlayers;
        _opponentTeam = opponent;

    }

    public void selectSquares() {
        try {
            Vector v = _player.getAroundSquares();
            int i = 0;
            for (i = 0; i < v.size(); i++) {
                dSquare s = (dSquare) v.get(i);
                if (_player.canFoul(s)) {
                    _model.setSquareState(s, dSquare.C_SQUARE_FOUL);
                }
            }
            _move.selectSquares();

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

    public void preStep() {
        try {
            _move.setAvailableMove(_player.getMovement());
            _move.setAdditionalMove(2);
            if (_player.getState() == dPlayer.C_STATE_PRONE) {
                _move.setAvailableMove(Math.max(_move.getAvailableMove() - 3, 0));
            }

            selectSquares();

            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void resetStep() {
        try {

            _model.resetSquareMove();
            _model.resetSquareBlock();
            _model.resetSquareFoul();
            _model.resetSquarePass();

            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);
            _step = 0;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void step(dSquare s) {
        try {

            if (_model.getSquareState(s) == dSquare.C_SQUARE_MOVE) {
                _move.step(s);
                _myTeam.setFoulDone(true);
            }

            if (_model.getSquareState(s) == dSquare.C_SQUARE_FOUL) {
                int nb = _opponentTeam.getPlayerNumber(s.getX(), s.getY());
                if (nb > -1) {
                    _myTeam.setFoulDone(true);
                    rmiPlayer victim = (rmiPlayer) _opponentPlayers.get(nb);
                    /**
                     * Nombre de zones de tacle - l'attaquant - zones de tacles sur l'attaquant
                     */
                    int modifier = victim.getTackleZoneNumber() - 1 - _player.getTackleZoneNumber();

                    boolean sent_off = false;

                    drArmor armor_roll = new drArmor();
                    armor_roll.addModifiers(modifier);
                    int roll = armor_roll.rollDices();

                    sent_off = armor_roll.isDouble();

                    if (armor_roll.isSuccess(victim, roll)) {
                        drInjury injury_roll = new drInjury();
                        int injury = injury_roll.rollDices();
                        injury_roll.applyEffects(injury, victim);
                        sent_off = sent_off || injury_roll.isDouble();
                    }

                    if (sent_off) {
                        _model.AddDiary("Player is sent off for fouling: " + _player.getName());
                        _player.setState(dPlayer.C_STATE_SENT_OFF);
                        _player.setOnPitch(false);
                        if (_player.hasBall()) {
                            scatterBall(s);
                        }
                        _model.turnover(true);
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    public void postStep() {
    /* _model._selectedPlayer._hasPlayed=true;
    _model._selectedPlayer._isActive=false;
    _model._selectedPlayer=null;
    cGround._step=cGround.STEP_SELECT_PLAYER_FOR_ACTION;*/
    }

    public String getName() {
        return "Foul";
    }

    public int getAvailableMove() {
        return _move.getAvailableMove();
    }

    public int getAdditionalMove() {
        return _move.getAdditionalMove();
    }
}
