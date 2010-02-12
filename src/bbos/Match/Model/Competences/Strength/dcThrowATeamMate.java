/*
 * dcThrowATeamMate.java
 *
 * Created on 28 novembre 2007, 18:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Competences.Strength;

import bbos.Match.Model.Actions.daBlitzStab;
import bbos.Match.Model.Actions.daBlockStab;
import bbos.Match.Model.Actions.daThrowTeamMate;
import bbos.Match.Model.Competences.dCompetence;
import bbos.Match.Model.Competences.dCompetencesFactory;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiTeam;
import bbos.Match.Model.rmiPlayer;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Administrateur
 */
public class dcThrowATeamMate extends bbos.Match.Model.Competences.dCompetence {

    /** Creates a new instance of dcThrowATeamMate */
    public dcThrowATeamMate(String name) {
        super(name);
        _type = dCompetencesFactory.Strength;
    }

    public boolean canReroll(int RollType, rmiPlayer player) {
        return false;
    }

    public Vector modifyActionList(Vector actionList, rmiMatch model, rmiPlayer player, rmiTeam opponent, rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers, boolean challenger) {
        for (int i = 0; i < myPlayers.size(); i++) {
            rmiPlayer p = (rmiPlayer) myPlayers.get(i);

            try {
                if ((p.isOnPitch()) && (p.getState() == dPlayer.C_STATE_OK)) {

                    Vector comp = p.getCompetences();
                    for (int j = 0; j < comp.size(); j++) {
                        if (((dCompetence) comp.get(j)).getName().equals("Right Stuff")) {
                            daThrowTeamMate a2 = new daThrowTeamMate(model, player, opponent, myTeam, opponentPlayers, myPlayers, challenger);
                            actionList.add(a2);
                            break;
                        }
                    }
                }
            } catch (RemoteException e) {
                System.out.println(e.getStackTrace());
            }
        }

        return actionList;
    }

    public boolean canDoAction(int actionType, rmiPlayer player,Vector myPlayers)
    {
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
