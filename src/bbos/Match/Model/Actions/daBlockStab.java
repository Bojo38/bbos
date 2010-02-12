/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match.Model.Actions;

import bbos.Match.Model.Roll.drArmor;
import bbos.Match.Model.Roll.drInjury;
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
public class daBlockStab extends dAction {

    protected static ImageIcon _icon = new ImageIcon(dAction.class.getResource("/resources/images/actions/chainsaw.gif"));

    public ImageIcon getIcon() {
        return _icon;
    }
    rmiPlayer _attacker;
    rmiMatch _model;
    rmiTeam _opponent;
    rmiTeam _myTeam;
    Vector _opponentPlayers;
    Vector _myPlayers;

    public daBlockStab(rmiMatch model, rmiPlayer player, rmiTeam opponent, rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers,boolean isChallenger) {
        super(C_BLOCK_STAB);
        _model = model;
        _attacker = player;
        _step = 0;
        _opponent = opponent;
        _myTeam = myTeam;
        _opponentPlayers = opponentPlayers;
        _myPlayers = myPlayers;
    }

    public void selectSquares() {
        try {
            Vector squares = _attacker.getAroundSquares();
            for (int i = 0; i < squares.size(); i++) {
                dSquare s = (dSquare) squares.get(i);
                _model.setSquareState(s, dSquare.C_SQUARE_OPPONENT_TO_BLOCK);
                _model.setBlockDiceNumber(s, 1);
            }

        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }

    }

    public void resetStep() {
        try {
            _model.resetSquareBlock();

            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);
            _step = 0;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void preStep() {
        this.selectSquares();

        try {
            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void step(dSquare s) {
        try {
            int state = _model.getSquareState(s);
            if (state == dSquare.C_SQUARE_OPPONENT_TO_BLOCK) {
                int nb = _opponent.getPlayerNumber(s.getX(), s.getY());
                if (nb > -1) {
                    rmiPlayer victim = (rmiPlayer) _opponentPlayers.get(nb);
                    drArmor armor = new drArmor();
                    int roll = armor.rollDices();
                    if (armor.isSuccess(victim, roll)) {
                        drInjury injury = new drInjury();
                        roll = injury.rollDices();
                        injury.applyEffects(roll, victim);
                    }

                    _attacker.hasPlayed(true);
                    _attacker.isActive(false);
                    _attacker.isPlaying(false);

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
        return "Block with object (Chainsaw or Stab)";
    }
}
