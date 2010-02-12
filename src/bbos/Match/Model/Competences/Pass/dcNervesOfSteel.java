/*
 * dcNervesOfSteel.java
 *
 * Created on 28 novembre 2007, 18:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Competences.Pass;

import bbos.Match.Model.Competences.dCompetence;
import bbos.Match.Model.Competences.dCompetencesFactory;
import bbos.Match.Model.Roll.iRoll;
import bbos.Match.Model.dPlayer;
import java.util.Vector;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiTeam;
import bbos.Match.Model.rmiPlayer;

/**
 *
 * @author Administrateur
 */
public class dcNervesOfSteel extends bbos.Match.Model.Competences.dCompetence {

    /** Creates a new instance of dcNervesOfSteel */
    public dcNervesOfSteel(String name) {
        super(name);
        _type = dCompetencesFactory.Pass;
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

    public boolean ignoreModifiers(int rollType, int modifierType) {
        if (((rollType == iRoll.C_PASS) ||
                (rollType == iRoll.C_INTERCEPTION) ||
                (rollType == iRoll.C_PASS)) &&
                (modifierType == iRoll.C_TACKLE_ZONE_MODIFIER)) {
            return true;
        }
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
