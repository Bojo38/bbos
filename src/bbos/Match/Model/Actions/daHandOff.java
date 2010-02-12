/*
 * daHandOff.java
 *
 * Created on 28 novembre 2007, 18:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Actions;

import bbos.Match.Model.Roll.Agility.draCatch;
import bbos.Match.Model.Roll.Agility.draDodge;
import bbos.Match.Model.Roll.Agility.draPickUp;
import bbos.Match.Model.dMatch;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.dSquare;
import bbos.Match.Views.cGround;
import java.util.*;
import bbos.*;
import bbos.Match.Model.Roll.Agility.draGetBall;
import bbos.Match.Model.Roll.drScatter;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiTeam;
import java.rmi.RemoteException;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrateur
 */
public class daHandOff extends dAction {

    protected static ImageIcon _icon = new ImageIcon(dAction.class.getResource("/resources/images/actions/handoff.gif"));

    public ImageIcon getIcon() {
        return _icon;
    }
    rmiMatch _model;
    rmiPlayer _player;
    daMove _move;
    rmiTeam _myTeam;
    Vector _myPlayers;
    rmiTeam _opponentTeam;
    Vector _opponentPlayers;

    /** Creates a new instance of daHandOff
     * @param model Model data of the macth
     * @param player Active player.
     */
    public daHandOff(rmiMatch model, rmiPlayer player, rmiTeam opponent, rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers,boolean isChallenger) {
        super(C_HANDOFF);
        _model = model;
        _player = player;
        _move = new daMove(model, player);
        _myPlayers = myPlayers;
        _myTeam = myTeam;
        _opponentPlayers = opponentPlayers;
        _opponentTeam = opponent;
    }

    public void resetStep() {
        try {

            _model.resetSquareMove();
            _model.resetSquarePass();
            _model.resetSquareFoul();
            _model.resetSquareBlock();

            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);
            _step = 0;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void selectSquares() {

        try {
            if (_player.hasBall()) {
                Vector v = _player.getAroundSquares();
                int i = 0;
                for (i = 0; i < v.size(); i++) {
                    dSquare s = (dSquare) v.get(i);
                    if (_player.canHandOff(s)) {
                        _model.setSquareState(s, dSquare.C_SQUARE_PARTNER_TO_PASS);
                    }
                }
            }
            _move.selectSquares();

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

    /**
     * Pas principal de l'action HandOff
     */
    public void step(dSquare s) {
        try {

            if (_model.getSquareState(s) == dSquare.C_SQUARE_MOVE) {
                _move.step(s);
                _myTeam.setHandOffDone(true);
            }

            if (_model.getSquareState(s) == dSquare.C_SQUARE_PARTNER_TO_PASS) {
                int nb = _myTeam.getPlayerNumber(s.getX(), s.getY());
                if (nb > -1) {
                    _myTeam.setHandOffDone(true);
                    rmiPlayer catcher = (rmiPlayer) _myPlayers.get(nb);
                    int state = catcher.getState();
                    if ((state == dPlayer.C_STATE_OK) || (state == dPlayer.C_STATE_ROOTED)) {
                        _player.isActive(false);
                        _player.isPlaying(false);
                        _player.hasPlayed(true);

                        draCatch catch_roll = new draCatch(catcher);
                        int tz = catcher.getTackleZoneNumber();
                        catch_roll.addModifiers(-tz);
                        int result = catch_roll.rollDices();
                        _model.AddDiary("Catch the ball roll: " + result);
                        if (catch_roll.isSuccess(result)) {
                            _model.AddDiary("Catch the ball success!");
                            _player.setBall(false);
                            catcher.setBall(true);
                        } else {
                            _model.turnover(true);
                            _model.AddDiary("Catch the ball failure!");
                            /* Sinon elle rebondit sur une autre case*/
                            _player.setBall(false);

                            boolean ballStop = false;
                            dSquare fromSq = s;
                            boolean catched = false;

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
                                    tz = tmp_player.getTackleZoneNumber();
                                    getBall_roll.addModifiers(-tz);
                                    result = getBall_roll.rollDices();
                                    _model.AddDiary("Get the ball roll: " + result);
                                    if (getBall_roll.isSuccess(result))
                                    {
                                        tmp_player.setBall(true);
                                        catched=true;
                                    }

                                } else {
                                    ballStop = true;
                                    _model.setBall(fromSq);
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

    public void postStep() {
   
    }

    public String getName() {
        return "Handoff";
    }

    public int getAvailableMove() {
        return _move.getAvailableMove();
    }

    public int getAdditionalMove() {
        return _move.getAdditionalMove();
    }
    
}
