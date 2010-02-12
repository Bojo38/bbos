/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match.Model.Actions;

import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiTeam;
import java.util.Vector;

/**
 *
 * @author root
 */
public class dActionsFactory {

    public static dAction createAction(int action, rmiMatch model, rmiPlayer player,rmiTeam opponent,rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers, boolean isChallenger) {
        switch (action) {
            case dAction.C_BLITZ:
                return new daBlitz(model, player,opponent,myTeam,opponentPlayers,myPlayers,isChallenger);
            case dAction.C_BLITZ_STAB:
                return new daBlitzStab(model, player,opponent,myTeam,opponentPlayers,myPlayers,isChallenger);
            case dAction.C_BLOCK:
                return new daBlock(model, player,opponent,myTeam,opponentPlayers,myPlayers,isChallenger);
            case dAction.C_BLOCK_STAB:
                return new daBlockStab(model, player,opponent,myTeam,opponentPlayers,myPlayers,isChallenger);
            case dAction.C_FANATIC:
                return new daFanatic(model, player,opponent,myTeam,opponentPlayers,myPlayers,isChallenger);
            case dAction.C_FOUL:
                return new daFoul(model, player,opponent,myTeam,opponentPlayers,myPlayers,isChallenger);
            case dAction.C_HANDOFF:
                return new daHandOff(model, player, opponent, myTeam,  opponentPlayers,  myPlayers,isChallenger);
            case dAction.C_MOVE:
                return new daMove(model, player);
            case dAction.C_PASS:
                return new daPass(model, player,opponent,myTeam,opponentPlayers,myPlayers,isChallenger);
            case dAction.C_HYPNOTIC_GAZE:
                return new daHypnoticGaze(model, player,opponent,myTeam,opponentPlayers,myPlayers,isChallenger);
            case dAction.C_THROW_TEAM_MATE:
                return new daThrowTeamMate(model, player,opponent,myTeam,opponentPlayers,myPlayers,isChallenger);
                case dAction.C_BOMBER:
                return new daBomber(model, player,opponent,myTeam,opponentPlayers,myPlayers,isChallenger);
        }
        return null;
    }
}
