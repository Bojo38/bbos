/*
 * dcMultipleBlock.java
 *
 * Created on 28 novembre 2007, 18:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Competences.Strength;

import bbos.Match.Model.Competences.dCompetence;
import bbos.Match.Model.Competences.dCompetencesFactory;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiTeam;
import bbos.Match.Model.rmiPlayer;
import java.util.Vector;

/**
 *
 * @author Administrateur
 */
public class dcMultipleBlock extends bbos.Match.Model.Competences.dCompetence
{
    
    /** Creates a new instance of dcMultipleBlock */
    public dcMultipleBlock (String name)
    {
        super(name);
        _type=dCompetencesFactory.Strength;
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
