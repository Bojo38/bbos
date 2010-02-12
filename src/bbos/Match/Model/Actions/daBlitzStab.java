/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match.Model.Actions;

import bbos.Match.Model.dSquare;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiTeam;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.ImageIcon;

/**
 *
 * @author root
 */
public class daBlitzStab extends dAction {

    rmiMatch _model;
    rmiPlayer _player;
    rmiTeam _opponent;
    rmiTeam _myTeam;
    Vector _opponentPlayers;
    Vector _myPlayers;
    daMove _move;
    daBlockStab _block;
    protected static ImageIcon _icon = new ImageIcon(dAction.class.getResource("/resources/images/actions/chainsawblitz.gif"));

    public ImageIcon getIcon() {
        return _icon;
    }

    /** Creates a new instance of daBlitz */
    public daBlitzStab(rmiMatch model, rmiPlayer player, rmiTeam opponent, rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers,boolean isChallenger) {
        super(C_BLITZ_STAB);
        _model = model;
        _player = player;
        _block = new daBlockStab(model, player, opponent, myTeam, opponentPlayers, myPlayers,isChallenger);
        _move = new daMove(model, player);
    }

    public void selectSquares() {
        _block.selectSquares();

        _move.selectSquares();
    }

    public void resetStep() {
        try {
            _block.resetStep();
            _move.resetStep();
            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);
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
                _myTeam.setBlitzDone(true);
                _move.step(s);
                _model.setLeftRefresh(true);
                _model.setRightRefresh(true);
            }

            if (square_state == dSquare.C_SQUARE_OPPONENT_TO_BLOCK) {
                _block.step(s);
                _myTeam.setBlitzDone(true);
                _player.hasPlayed(true);
                _player.isActive(false);
                _player.isPlaying(false);

                _model.setLeftRefresh(true);
                _model.setRightRefresh(true);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void postStep() {

    }

    public String getName() {
        return "Blitz with object (Chainsaw or Stab)";
    }

    public int getAvailableMove() {
        return _move.getAvailableMove();
    }

    public int getAdditionalMove() {
        return _move.getAdditionalMove();
    }
}
