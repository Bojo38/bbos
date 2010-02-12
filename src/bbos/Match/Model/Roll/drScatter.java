/*
 * drScatter.java
 *
 * Created on December 1, 2007, 11:14 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Roll;

import bbos.Tools.bbTool;
import bbos.Match.Model.dMatch;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.dSquareCollection;
import bbos.Match.Model.dSquare;
import bbos.*;
import bbos.Match.Model.Roll.Agility.*;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.tNetworkConnexion;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author moi
 */
public class drScatter {

    public dSquare _squareBall;
    boolean _enable_lineout;
    rmiMatch _model;
    boolean _canBeCatched;

    /** Creates a new instance of drScatter */
    public drScatter(rmiMatch model,dSquare s, boolean lineout, boolean canBeCatched) {
        _squareBall = s;
        _model=model;
        _enable_lineout = lineout;
        _canBeCatched = canBeCatched;
    }

    public int rollDices() {
        return bbTool.getd8();
    }

    public void applyEffects(int direction) {
        int x = _squareBall.getX();
        int y = _squareBall.getY();

        switch (direction) {
            case 1:
                x--;
                y--;
                break;
            case 2:
                y--;
                break;
            case 3:
                x++;
                y--;
                break;
            case 4:
                x--;
                break;
            case 5:
                x++;
                break;
            case 6:
                x--;
                y++;
                break;
            case 7:
                y++;
                break;
            case 8:
                x++;
                y++;
                break;
        }

        if (_enable_lineout) {
            lineout(x, y);
        } else {
            if ((x < 0) || (x > 25) || (y < 0) || (y > 15)) {
                _squareBall.setX(-10);
                _squareBall.setY(-10);
            } else {
                _squareBall.setX(x);
                _squareBall.setY(y);
            }
        }

        if (_canBeCatched) {
            try {
                if (_model.isAPlayer(_squareBall)) {

                    Vector _leftPlayers = tNetworkConnexion.getConnexion().getLeftPlayers();
                    rmiPlayer player = null;
                    for (int i = 0; i < _leftPlayers.size(); i++) {
                        rmiPlayer p = (rmiPlayer) _leftPlayers.get(i);
                        if ((p.getX() == _squareBall.getX()) && (p.getY() == _squareBall.getY())) {
                            player = p;
                        }
                    }
                    if (player == null) {
                        Vector _rightPlayers = tNetworkConnexion.getConnexion().getRightPlayers();
                        for (int i = 0; i < _rightPlayers.size(); i++) {
                            rmiPlayer p = (rmiPlayer) _rightPlayers.get(i);
                            if ((p.getX() == _squareBall.getX()) && (p.getY() == _squareBall.getY())) {
                                player = p;
                            }
                        }
                    }
                    if (player != null) {
                        draGetBall getBall = new draGetBall(player);
                        int roll = getBall.rollDices();
                        if (getBall.isSuccess(roll)) {
                            player.setBall(true);
                        } else {
                            this.applyEffects(this.rollDices());
                        }
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void scatterMissedPass() {
        int x, y;
        x = _squareBall.getX();
        y = _squareBall.getY();
        for (int i = 0; i < 3; i++) {
            int direction = bbTool.getd8();

            x = _squareBall.getX();
            y = _squareBall.getY();
            //dSquareCollection sc = _model._squares;
            // sc.removeBall(x, y);

            switch (direction) {
                case 1:
                    x--;
                    y--;
                    break;
                case 2:
                    y--;
                    break;
                case 3:
                    x++;
                    y--;
                    break;
                case 4:
                    x--;
                    break;
                case 5:
                    x++;
                    break;
                case 6:
                    x--;
                    y++;
                    break;
                case 7:
                    y++;
                    break;
                case 8:
                    x++;
                    y++;
                    break;
            }

            if (_enable_lineout) {
                i = 3;
                lineout(x, y);
            } else {
                _squareBall.setX(x);
                _squareBall.setY(y);
            }



        //sc.setBall(_squareBall.getX(), _squareBall.getY());
        }

    /*rmiPlayer player = _model.getPlayer(x, y);
    if (player != null)
    {
    draGetBall getBall = new draGetBall(player);
    int roll = getBall.rollDices();
    if (getBall.isSuccess(roll))
    {
    //player._State = dPlayer.C_PLAYER_STATE_WITH_BALL;
    } else
    {
    this.applyEffects(this.rollDices());
    }
    }*/
    }

    protected void lineout(int x, int y) {
        boolean valid = false;
        while (!valid) {
            if (x < 0) {
                /*
                 * Renvoi de la touche
                 */
                x = -1;

                int direction3 = bbTool.getd3();
                int distance = bbTool.get2d6();
                x += distance;
                switch (direction3) {
                    case 1:
                        y -= distance;
                        break;
                    case 3:
                        y += distance;
                        break;
                }
            }

            if (y < 0) {
                y = -1;

                int direction3 = bbTool.getd3();
                int distance = bbTool.get2d6();
                y += distance;
                switch (direction3) {
                    case 1:
                        x += distance;
                    case 3:
                        x -= distance;
                        break;
                }
            }

            if (x > 25) {
                x = 26;
                int direction3 = bbTool.getd3();
                int distance = bbTool.get2d6();
                x -= distance;
                switch (direction3) {
                    case 1:
                        y += distance;
                        break;
                    case 3:
                        y -= distance;
                        break;
                }
            }

            if (y > 15) {
                y = 16;

                int direction3 = bbTool.getd3();
                int distance = bbTool.get2d6();
                y -= distance;
                switch (direction3) {
                    case 1:
                        x -= distance;
                        break;
                    case 3:
                        x += distance;
                        break;
                }
            }

            valid = (x >= 0) && (x < 26) && (y >= 0) && (y < 15);
        }
        _squareBall.setX(x);
        _squareBall.setY(y);
    }

    public dSquare getCurrentSquare() {
        return _squareBall;
    }
}
