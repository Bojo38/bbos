/*
 * vPitch.java
 *
 * Created on 29 décembre 2007, 17:13
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
 * @author moi
 */
public class vPitch extends javax.swing.JPanel implements MouseListener, MouseMotionListener {

    public static final int c_squareSize = 30;
    dTeam _team;
    dMatch _model;

    /** Creates a new instance of vPitch */
    public vPitch() {
        super();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setModel(dTeam team) {
        _team = team;
    }

    void paintTeam(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (_team != null) {
            /*try {*/
                for (int i = 0; i < _team.getPlayers().size(); i++) {
                    dPlayer player = (dPlayer) _team.getPlayers().get(i);
                    if (player.isOnThePitch()) {
                    /*
                     * Draw icons
                     */
                    /*                   Image photo;
                    photo= player.getIcon();
                    int width=photo.getWidth(this);
                    int heigth=photo.getHeight(this);
                    g2.drawImage(photo, player._reserveX*c_squareSize+(c_squareSize-width)/2, player._reserveY*c_squareSize+(c_squareSize-heigth)/2, this);
                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER,1.0f));
                    Image state_image = getToolkit().getImage(getClass().getResource(player.getStateUrl())) ;
                    g.drawImage(state_image, player._reserveX*c_squareSize, player._reserveY*c_squareSize, this);
                     */
                    }
                }
            /*} catch (RemoteException e) {
                System.err.println(e.getMessage());
            }*/
        }
    }

    public void paint(Graphics g) {

        super.paint(g);
        paintTeam(g);
    }

    public void mouseClicked(MouseEvent e) {
        /*
         * Si double click sur le joueur
         * Alors joueur sur le terrain
         */
        if (e.getClickCount() == 2) {
            if (cGround._step == cGround.STEP_SET_PLAYER) {
             //   if (_model._activeTeam == _team) {
                   /* try {*/
                        for (int i = 0; i < _team.getPlayers().size(); i++) {
                            dPlayer player = (dPlayer) _team.getPlayers().get(i);
                            //if (player._Injury == dPlayer.C_INJURY_NONE)
                            {
/*                                if ((player._reserveX * c_squareSize < e.getX()) &&
                                        ((player._reserveX * c_squareSize + c_squareSize > e.getX()))) {
                                    if ((player._reserveY * c_squareSize < e.getY()) &&
                                            ((player._reserveY * c_squareSize + c_squareSize > e.getY()))) {
                                        player._reserve = false;
                                        _model.refresh();
                                    }
                                }*/
                            }
                        }
                    /*} catch (RemoteException ex) {
                        System.err.println(ex.getMessage());
                    }*/
             //   }
            }
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        boolean found = false;
        if (_model != null) {
            //try {
                for (int i = 0; i < _team.getPlayers().size(); i++) {
                    dPlayer player = (dPlayer) _team.getPlayers().get(i);
/*                    if ((player._reserveX * c_squareSize < e.getX()) &&
                            ((player._reserveX * c_squareSize + c_squareSize > e.getX()))) {
                        if ((player._reserveY * c_squareSize < e.getY()) &&
                                ((player._reserveY * c_squareSize + c_squareSize > e.getY()))) {
                            _model._onFlyPlayer = player;
                        }
                    }*/

                }
                MainForm.getSingleton().redraw();
                MainForm.getSingleton().repaint();
            /*} catch (RemoteException ex) {
                System.err.println(ex.getMessage());
            }*/
        }
    }
}
