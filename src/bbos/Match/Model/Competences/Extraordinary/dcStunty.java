/*
 * dcStunty.java
 *
 * Created on 28 novembre 2007, 13:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Competences.Extraordinary;

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
public class dcStunty extends bbos.Match.Model.Competences.dCompetence
{
    
    /** Creates a new instance of dcStunty */
    public dcStunty (String name)
    {
        super(name);
        _type=dCompetencesFactory.Extraordinary;
    }

    public boolean canReroll(int RollType,rmiPlayer player)
    {
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

    public boolean ignoreModifiers(int rollType, int modifierType) {
        if ((rollType==iRoll.C_DODGE)&&(modifierType==iRoll.C_TACKLE_ZONE_MODIFIER))
        {
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
        if (rollType==iRoll.C_INJURY)
        {
            return 1;
        }
       return 0;
    }

    public int modifyRollValue(int rollType, int rollValue, int actionType) {
        if (rollType==iRoll.C_PASS)
        {
            return rollValue-1;
        }
        return rollValue;
    }
}
