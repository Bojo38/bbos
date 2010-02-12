/*
 * dcDirtyPlayer.java
 *
 * Created on 28 novembre 2007, 13:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Competences.General;

import bbos.Match.Model.Actions.dAction;
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
public class dcDirtyPlayer extends bbos.Match.Model.Competences.dCompetence {

    /** Creates a new instance of dcDirtyPlayer */
    public dcDirtyPlayer(String name) {
        super(name);
        _type = dCompetencesFactory.General;
    }

    public boolean canReroll(int RollType, rmiPlayer player) {
        return false;
    }

    

    public Vector modifyActionList(Vector actionList,rmiMatch model, rmiPlayer player)
    {
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

        if (actionType == dAction.C_FOUL) {
            if (iRoll.C_ARMOR == rollType) {
                _used = true;
                return 1;
            }

            if ((iRoll.C_INJURY == rollType) && (!_used)) {
                return 1;
            }
        }
        return rollValue;
    }

}
