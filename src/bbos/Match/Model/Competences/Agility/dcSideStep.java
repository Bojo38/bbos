/*
 * dcSideStep.java
 *
 * Created on 28 novembre 2007, 13:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Competences.Agility;

import bbos.Match.Model.Competences.dCompetence;
import bbos.*;
import bbos.Match.Model.Competences.Strength.dcGrab;
import bbos.Match.Model.Competences.dCompetencesFactory;
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
public class dcSideStep extends dCompetence {

    /** Creates a new instance of dcSideStep */
    public dcSideStep(String name) {
        super(name);
        _type = dCompetencesFactory.Agility;
    }

    public boolean canReroll(int RollType, rmiPlayer player) {
        return false;
    }

    public Vector modifyActionList(Vector actionList,rmiMatch model, rmiPlayer player) {
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
        try {
            Vector<dCompetence> comps = opp.getCompetences();
            for (int i = 0; i < comps.size(); i++) {
                dCompetence comp = comps.get(i);
                if (comp instanceof dcGrab) {
                    return false;
                }
            }
        } catch (RemoteException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return true;
    }

    public int getOpponentRollModifier(int rollType,rmiPlayer opponent, rmiPlayer myPlayer)
    {
       return 0;
    }

    public int modifyRollValue(int rollType,int rollValue, int actionType)
    {
        return rollValue;
    }
}
