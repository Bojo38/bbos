/*
 * dcDisturbingPresence.java
 *
 * Created on 28 novembre 2007, 14:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Competences.Mutation;

import bbos.Match.Model.Competences.dCompetence;
import bbos.Match.Model.Competences.dCompetencesFactory;
import bbos.Match.Model.Roll.iRoll;
import bbos.Match.Model.dPlayer;
import java.util.Vector;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiTeam;
import bbos.Match.Model.rmiPlayer;
import java.rmi.RemoteException;

/**
 *
 * @author Administrateur
 */
public class dcDisturbingPresence extends bbos.Match.Model.Competences.dCompetence {

    /** Creates a new instance of dcDisturbingPresence */
    public dcDisturbingPresence(String name) {
        super(name);
        _type = dCompetencesFactory.Mutation;
    }

    public boolean canReroll(int RollType, rmiPlayer player) {
        return false;
    }

    public Vector modifyActionList(Vector actionList, rmiMatch model, rmiPlayer player) {
        return actionList;
    }

    public boolean canDoAction(int actionType, rmiPlayer player, Vector myPlayers) {
        return true;
    }

    public boolean ignoreModifiers(int rollType, int modifierTypes) {
        return false;
    }

    public boolean preRoll(int actionType, int rollTypes,rmiPlayer player) {
        return true;
    }

    public boolean isUsable(int actionType, rmiPlayer player, rmiPlayer opp) {
        return true;
    }

    public int getOpponentRollModifier(int rollType, rmiPlayer opponent, rmiPlayer myPlayer) {
        try {
            if ((rollType == iRoll.C_PASS) ||
                    (rollType == iRoll.C_CATCH) ||
                    (rollType == iRoll.C_INTERCEPTION)) {
                if ((myPlayer.getState() == dPlayer.C_STATE_OK) ||
                        (myPlayer.getState() == dPlayer.C_STATE_PRONE) ||
                        (myPlayer.getState() == dPlayer.C_STATE_STUNNED) ||
                        (myPlayer.getState() == dPlayer.C_STATE_ROOTED)) {
                    if (opponent.getDistance(myPlayer) <= 3) {
                        return -1;
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

     public int modifyRollValue(int rollType, int rollValue, int actionType) {

        return rollValue;
    }
}
