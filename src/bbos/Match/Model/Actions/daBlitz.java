/*
 * daBlitz.java
 *
 * Created on December 3, 2007, 9:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Actions;

import bbos.Match.Model.dMatch;
import bbos.Match.Model.dPlayer;
import bbos.Match.Views.cGround;
import bbos.*;
import bbos.Match.Model.dSquare;
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
public class daBlitz extends dAction {

    rmiMatch _model;
    rmiPlayer _player;
    rmiTeam _opponent;
    rmiTeam _myTeam;
    Vector _opponentPlayers;
    Vector _myPlayers;
    daMove _move;
    daBlock _block;
    boolean _block_is_done;
    protected static ImageIcon _icon = new ImageIcon(dAction.class.getResource("/resources/images/actions/blitz.gif"));

    public ImageIcon getIcon() {
        return _icon;
    }

    /** Creates a new instance of daBlitz */
    public daBlitz(rmiMatch model, rmiPlayer player, rmiTeam opponent, rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers, boolean isChallenger) {
        super(C_BLITZ);
        _model = model;
        _player = player;
        _block = new daBlock(model, player, opponent, myTeam, opponentPlayers, myPlayers, isChallenger);
        _move = new daMove(model, player);
        _block_is_done = false;
    }

     public daBlock getBlock() {
         return _block;
     }
    public void selectSquares() {
        if (!_block_is_done) {
            _block.selectSquares();
        }
        _move.selectSquares();
    }

    public void resetStep() {
        try {
            _block.resetStep();
            _move.resetStep();
            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);
            _step = 0;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void preStep() {
        try {
            _move.preStep();
            _block.preStep();

            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void step(dSquare s) {
        try {
            int square_state = _model.getSquareState(s);
            if (square_state == dSquare.C_SQUARE_MOVE) {
                _move.step(s);
                _myTeam.setBlitzDone(true);
            }

            if ((square_state == dSquare.C_SQUARE_OPPONENT_TO_BLOCK) ||
                    (square_state == dSquare.C_SQUARE_PUSH_ZONE)) {
                _block.step(s);
                _myTeam.setBlitzDone(true);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void postStep() {

    }

    public String getName() {
        return "Blitz";
    }

    public int getAvailableMove() {
        return _move.getAvailableMove();
    }

    public int getAdditionalMove() {
        return _move.getAdditionalMove();
    }
}
