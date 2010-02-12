/*
 * dcBallAndChain.java
 *
 * Created on 28 novembre 2007, 13:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Competences.Extraordinary;

import bbos.Match.Model.Actions.dAction;
import bbos.Match.Model.Actions.daFanatic;
import bbos.Match.Model.Competences.dCompetence;
import bbos.Match.Model.Competences.dCompetencesFactory;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiTeam;
import java.util.Vector;

/**
 *
 * @author Administrateur
 */
public class dcBallAndChain extends bbos.Match.Model.Competences.dCompetence
{
    
    /** Creates a new instance of dcBallAndChain */
    public dcBallAndChain (String name)
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
        Vector v=new Vector();        
        v.add(dAction.C_FANATIC);
        return v;
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

    public int modifyRollValue(int rollType,int rollValue, int actionType)
    {
        return rollValue;
    }
}
