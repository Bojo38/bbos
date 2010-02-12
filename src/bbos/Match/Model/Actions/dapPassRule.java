/*
 * dapPassRule.java
 *
 * Created on December 16, 2007, 3:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Actions;

import bbos.Match.Automat.DuringMatch.jdgMatch;
import bbos.Match.Model.*;
import bbos.Match.Model.Roll.Agility.draPass;
import java.util.*;
import java.awt.*;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *
 * @author moi
 */
public class dapPassRule implements Serializable {

    int x_points[];
    int y_points[];
    rmiMatch _model;
    rmiPlayer _player;
    Color _color;
    int _range;
    int _squareSize;
    double _passRatio = 3.4;

    /** Creates a new instance of dapPassRule */
    public dapPassRule(rmiMatch model, rmiPlayer player) {
        x_points = new int[4];
        y_points = new int[4];
        _model = model;
        _squareSize = jdgMatch.C_SQUARE_SIZE;
        _player = player;
    }

    public void computePassRule(dSquare dest, int range) {
        try {
            int x_ref = _player.getX() * _squareSize + _squareSize / 2;
            int y_ref = _player.getY() * _squareSize + _squareSize / 2;

            if (dest != null) {
                int x = dest.getX() * _squareSize + _squareSize / 2;
                int y = dest.getY() * _squareSize + _squareSize / 2;
                double distance = Math.sqrt((x_ref - x) * (x_ref - x) + (y_ref - y) * (y_ref - y));

                _range = 0;
                if (distance > 0) {
                    int dx = (x - x_ref);
                    int dy = (y - y_ref);
                    double costheta = dx / distance;
                    double sintheta = dy / distance;

                    if ((distance < _passRatio * _squareSize) && (range >= 1)) {
                        _color = new Color(7, 119, 39);
                        _range = draPass.QUICK;
                    } else {
                        if ((distance < 2 * _passRatio * _squareSize) && (range >= 2)) {
                            _color = new Color(237, 239, 27);
                            _range = draPass.SHORT;
                        } else {
                            if ((distance < 3 * _passRatio * _squareSize) && (range >= 3)) {
                                _range = draPass.LONG;
                                _color = new Color(239, 147, 27);
                            } else {
                                if ((distance < 4 * _passRatio * _squareSize) && (range >= 4)) {
                                    _range = draPass.BOMB;
                                    _color = new Color(191, 15, 48);
                                }
                            }
                        }
                    }

                    if (_range != 0) {
                        /* Segment initial*/
                        x_points[0] = 0;
                        x_points[1] = 0;
                        x_points[2] = (int) (distance * costheta);
                        x_points[3] = (int) (distance * costheta);

                        y_points[0] = 0;
                        y_points[1] = 0;
                        y_points[2] = (int) (distance * sintheta);
                        y_points[3] = (int) (distance * sintheta);

                        /*
                         * Transation suivants 2 vecteurs
                         */
                        x_points[0] -= (int) (_squareSize * sintheta);
                        x_points[1] += (int) (_squareSize * sintheta);
                        x_points[2] += (int) (_squareSize * sintheta);
                        x_points[3] -= (int) (_squareSize * sintheta);

                        y_points[0] += (int) (_squareSize * costheta);
                        y_points[1] -= (int) (_squareSize * costheta);
                        y_points[2] -= (int) (_squareSize * costheta);
                        y_points[3] += (int) (_squareSize * costheta);

                        /*
                         * Translation
                         */
                        x_points[0] += x_ref;
                        x_points[1] += x_ref;
                        x_points[2] += x_ref;
                        x_points[3] += x_ref;

                        y_points[0] += y_ref;
                        y_points[1] += y_ref;
                        y_points[2] += y_ref;
                        y_points[3] += y_ref;


                        /*
                         * Interception detection
                         */
                        /*
                         * Si le centre de la case a une distance inf?rieure ? la longueur
                         * de passe, et que la hauteur avec le segment est inf?rieur ? 30
                         */
                        /* Vector v = new Vector();*/
                        for (int x_cpt = 0; x_cpt < 26; x_cpt++) {
                            for (int y_cpt = 0; y_cpt < 15; y_cpt++) {
                                dSquare s = new dSquare(x_cpt, y_cpt);
                                if (_player.isAnOpponent(new dSquare(x_cpt, y_cpt))) {
                                    int xc = x_cpt * _squareSize + _squareSize / 2;
                                    int yc = y_cpt * _squareSize + _squareSize / 2;
                                    double dac = Math.sqrt((x_ref - xc) * (x_ref - xc) + (y_ref - yc) * (y_ref - yc));
                                    double dbc = Math.sqrt((x - xc) * (x - xc) + (y - yc) * (y - yc));
                                    double h = Math.sqrt(dbc * dbc - (dbc * dbc + distance * distance - dac * dac) * (dbc * dbc + distance * distance - dac * dac) / (4 * distance * distance));

                                    if (h <= _squareSize + 0.1) {
                                        double dah = Math.sqrt(dac * dac - h * h);
                                        double dbh = Math.sqrt(dbc * dbc - h * h);
                                        if ((dah <= distance + 0.1) && (dbh <= distance + 0.1)) {
                                            _model.setSquareState(s, dSquare.C_SQUARE_OPPONENT_INTERCEPT);
                                        }
                                    }
                                }
                            }
                        }

                        /*
                         * Nombre de zones de tacle
                         * a la reception
                         */
                        if (_player.isAnOpponent(dest)) {
                            _model.setSquareState(dest, dSquare.C_SQUARE_PARTNER_TO_PASS);
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
