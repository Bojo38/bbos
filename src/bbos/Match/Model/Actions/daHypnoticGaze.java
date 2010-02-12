/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match.Model.Actions;

import bbos.Match.Model.Roll.drAgility;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.dSquare;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiTeam;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author root
 */
public class daHypnoticGaze extends dAction {

    protected static ImageIcon _icon = new ImageIcon(dAction.class.getResource("/resources/images/actions/hypnoticgaze.gif"));
    rmiMatch _model;
    rmiPlayer _player;
    rmiTeam _opponent;
    rmiTeam _myTeam;
    Vector _opponentPlayers;
    Vector _myPlayers;
    daMove _move;

    public ImageIcon getIcon() {
        return _icon;
    }

    public daHypnoticGaze(rmiMatch model, rmiPlayer player, rmiTeam opponent, rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers,boolean isChallenger) {
        super(C_HYPNOTIC_GAZE);
        _model = model;
        _player = player;
        _move = new daMove(model, player);
        _opponentPlayers = opponentPlayers;
        _opponent = opponent;
        _myPlayers = myPlayers;
        _myTeam = myTeam;
    }

    public void selectSquares() {
        try {
            Vector v = _player.getAroundSquares();
            int i = 0;
            for (i = 0; i < v.size(); i++) {
                dSquare s = (dSquare) v.get(i);
                if (_player.canHypno(s)) {
                    _model.setSquareState(s, dSquare.C_SQUARE_OPPONENT_TO_BLOCK);
                }
            }
            _move.selectSquares();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void preStep() {
        try {
            _move.preStep();

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
            _model.resetSquarePass();
            _model.resetSquareFoul();

            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);
            _step = 0;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void step(dSquare s) {
        try {
            int square_state = _model.getSquareState(s);
            if (square_state == dSquare.C_SQUARE_MOVE) {
                _move.step(s);
                _model.setLeftRefresh(true);
                _model.setRightRefresh(true);
            }

            if (square_state == dSquare.C_SQUARE_OPPONENT_TO_BLOCK) {

                int nb = _opponent.getPlayerNumber(s.getX(), s.getY());
                if (nb > -1) {
                    rmiPlayer opp=(rmiPlayer)_opponentPlayers.get(nb);
                    
                    drAgility agility = new drAgility(_player);
                    int roll = agility.rollDices();
                    if (agility.isSuccess(roll)) {
                        opp.setState(dPlayer.C_STATE_NO_ZONE);
                    }
                    _player.hasPlayed(true);
                    _player.isActive(false);
                    _player.isPlaying(false);

                    _model.setLeftRefresh(true);
                    _model.setRightRefresh(true);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void postStep() {
    }

    public String getName() {
        return "Hypnotic gaze";
    }

    public int getAvailableMove() {
        return _move.getAvailableMove();
    }

    public int getAdditionalMove() {
        return _move.getAdditionalMove();
    }
}
