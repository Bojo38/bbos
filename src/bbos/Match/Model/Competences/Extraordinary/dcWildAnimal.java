/*
 * dcWildAnimal.java
 *
 * Created on 28 novembre 2007, 13:50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Competences.Extraordinary;

import bbos.Match.Model.Actions.dAction;
import bbos.Match.Model.Competences.dCompetence;
import bbos.Match.Model.Competences.dCompetencesFactory;
import bbos.Match.Model.dPlayer;
import java.util.Vector;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiTeam;
import bbos.Match.Model.rmiPlayer;
import bbos.Tools.bbTool;
import java.rmi.RemoteException;

/**
 *
 * @author Administrateur
 */
public class dcWildAnimal extends bbos.Match.Model.Competences.dCompetence {

    /** Creates a new instance of dcWildAnimal */
    public dcWildAnimal(String name) {
        super(name);
        _type = dCompetencesFactory.Extraordinary;
    }

    public boolean canReroll(int RollType, rmiPlayer player) {
        return false;
    }

    public Vector modifyActionList(Vector actionList, rmiMatch model, rmiPlayer player, rmiTeam opponent, rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers, boolean challenger) {
        return actionList;
    }

    public boolean canDoAction(int actionType, rmiPlayer player, Vector myPlayers) {
        try {
            int res = bbTool.getd6();

            if ((actionType == dAction.C_BLITZ) || (actionType == dAction.C_BLOCK)) {
                if (res == 1) {

                    player.hasPlayed(true);
                    return false;
                }
            } else {
                if (res < 4) {

                    player.hasPlayed(true);
                    return false;
                }
            }

        } catch (RemoteException e) {
            System.out.println(e.getLocalizedMessage());
        }
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

    public int getOpponentRollModifier(int rollType,rmiPlayer opponent, rmiPlayer myPlayer)
    {
       return 0;
    }

    public int modifyRollValue(int rollType, int rollValue, int actionType) {
        return rollValue;
    }
}
