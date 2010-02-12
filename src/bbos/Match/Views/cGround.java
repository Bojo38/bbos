/*
 * cGround.java
 *
 * Created on 24 novembre 2007, 10:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Views;

import bbos.Match.Model.dMatch;
import bbos.Match.Model.dTeam;
import bbos.Match.Model.dPlayer;
import bbos.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import javax.swing.*;

/**
 *
 * @author Administrateur
 */
public class cGround implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener {
    /*
     * Possible actions
     */

    public static int _step = 0;
    public final static int STEP_NONE = 0;
    public final static int STEP_SELECT_PLAYER = 1;
    public final static int STEP_SELECT_PLAYER_FOR_ACTION = 6;
    public final static int STEP_PLACE_BALL_LEFT = 2;
    public final static int STEP_PLACE_BALL_RIGHT = 3;
    public final static int STEP_SET_PLAYER = 4;
    public final static int STEP_SELECT_SQUARE = 5;
    public final static int STEP_SELECT_SQUARE_PUSH = 10;
    public final static int STEP_PASS = 7;
    public final static int STEP_INTERCEPTION = 8;
    public final static int STEP_SELECT_BLOCK = 9;
    dMatch _model;

    /**
     * Creates a new instance of cGround
     */
    public cGround(dMatch model) {
        _model = model;
    }

    public void setModel(dMatch model) {
        _model = model;
    }

    public void mouseClicked(MouseEvent e) {
        dTeam team = null;

        /*if (e.getClickCount() == 2) {
            if (_step == STEP_SET_PLAYER) {
                //try {
                    team = _model._activeTeam;
                    for (int i = 0; i < team.getPlayers().size(); i++) {
                        dPlayer player = (dPlayer) team.getPlayers().get(i);
                        //if (player._Injury == dPlayer.C_INJURY_NONE) 
                        {
                            /*if ((player._X * vGround.c_squareSize < e.getX()) &&
                                    (((player._X + 1) * vGround.c_squareSize >= e.getX()))) {
                                if ((player._Y * vGround.c_squareSize < e.getY()) &&
                                        ((player._Y * vGround.c_squareSize + vGround.c_squareSize > e.getY()))) {
                                    player.setOnPitch(false);
                                }
                            }*/
                     /*   }
                    }
                /*} catch (RemoteException ex) {
                    System.err.println(ex.getMessage());
                }*/
            /*}
        }

        /*
         * Si phase de selection de joueur
         */

       /* if (_step == STEP_SELECT_PLAYER) {
            _model.selectPlayer(e.getX(), e.getY());
            if (_model._selectedPlayer != null) {
                _model._validated = true;
            }
        }

        if (_step == STEP_INTERCEPTION) {
            _model.selectSquare(e.getX(), e.getY());
            _model._selectedPlayer._currentAction.step();
        }

        if (_step == STEP_SELECT_PLAYER_FOR_ACTION) {
            if (_model != null) {
                if (_model._selectedPlayer != null) {
                    if (_model._selectedPlayer._currentAction != null) {
                        _model._selectedPlayer._currentAction.postStep();
                    }
                }
            }
            _model.selectPlayer(e.getX(), e.getY());
        }

        if (_step == STEP_SELECT_SQUARE) {
            if (_model != null) {
                if (_model._selectedPlayer != null) {
/*                    if ((_model._selectedPlayer._X == e.getX() / 30) &&
                            (_model._selectedPlayer._Y == e.getY() / 30)) {
                        if (_model._selectedPlayer._currentAction != null) {
                            _model._selectedPlayer._currentAction.postStep();
                        }
                    }*/
           /*     }
            }

            _model.selectSquare(e.getX(), e.getY());
            if (_model != null) {
                if (_model._selectedPlayer != null) {
                    if (_model._selectedPlayer._currentAction != null) {
                        _model._selectedPlayer._currentAction.step();

                    }
                }
            }
            MainForm.getSingleton().repaint();
        }

        if (_step == STEP_SELECT_SQUARE_PUSH) {
            if (_model != null) {
                _model.selectSquare(e.getX(), e.getY());
                if (_model._selectedPlayer != null) {
                    if (_model._selectedPlayer._currentAction != null) {
                        _model._selectedPlayer._currentAction.step();

                    }
                }
            }
            MainForm.getSingleton().repaint();
        }

        if (_step == STEP_PASS) {
            _model.selectSquare(e.getX(), e.getY());
            if (_model != null) {
                if (_model._selectedPlayer != null) {
                    if (_model._selectedPlayer._currentAction != null) {
                        _model._selectedPlayer._currentAction.step();
                    }
                }
            }
            MainForm.getSingleton().repaint();
        }

        /*
         * Si phase de Kickoff
         */

       /* if (STEP_PLACE_BALL_LEFT == _step) {
            if ((e.getX() > 60) && (e.getX() < 30 * 15)) {
                //_model._squares.removeAllBalls();
                _model.placeBall(e.getX() / 30, e.getY() / 30);
            }
        }*/

        /*if (STEP_PLACE_BALL_RIGHT == _step) {
            if ((e.getX() < 30 * 28) && (e.getX() > 30 * 15)) {
                _model.placeBall(e.getX() / 30, e.getY() / 30);
            }
        }*/
        /*
         * Si phase de Blocage
         */
      /*  if (STEP_SELECT_BLOCK == _step) {
            _model.selectSquareBlock(e.getX(), e.getY());
            _model._selectedPlayer._currentAction.step();
        }
        /*
         * Si phase d'interception
         */

        /*
         * Si phase d'aggression
         */

        MainForm.getSingleton().redraw();
        MainForm.getSingleton().repaint();
    }

    public void mousePressed(MouseEvent e) {
        /*
         * If the step is Set players to the fields
         */
        if (STEP_SET_PLAYER == _step) {
            _model.getGrabbedPlayer(e.getX(), e.getY());
        }
    }

    public void mouseReleased(MouseEvent e) {
        /*
         * If the step is Set players to the fields
         */
       /* if (STEP_SET_PLAYER == _step) {
            dPlayer player = _model._grabPlayer;
            if (player != null) {
                _model._grabPlayer = null;
            }
        }*/
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        /*
         * If the step is Set players to the fields
         */
     /*   if (STEP_SET_PLAYER == _step) {
            dPlayer player = _model._grabPlayer;
            dPlayer other_player = _model.getPlayer(e.getX() / 30, e.getY() / 30);
            if (player != null) {
                if ((other_player == null) || (other_player == player)) {
                    player.setX(e.getX() / 30);
                    player.setY(e.getY() / 30);
                }
            }
            MainForm.getSingleton().redraw();
            MainForm.getSingleton().repaint();
        }*/
    }

    public void mouseMoved(MouseEvent e) {
        boolean found = false;
        if (_model != null) {
            found = _model.selectFlyingPlayer(e.getX(), e.getY());
            if (found) {
                MainForm.getSingleton().redraw();
                MainForm.getSingleton().repaint();
            }
        }

        if (_step == STEP_PASS) {
            _model.selectFlyingSquare(e.getX(), e.getY());
//            _model._passRule.computePassRule();
            MainForm.getSingleton().redraw();
            MainForm.getSingleton().repaint();
        }
    }
}
