/*
 * dcPilingOn.java
 *
 * Created on 28 novembre 2007, 18:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Competences.Strength;

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
public class dcPilingOn extends bbos.Match.Model.Competences.dCompetence
{
    
    /** Creates a new instance of dcPilingOn */
    public dcPilingOn (String name)
    {
        super(name);
        _type=dCompetencesFactory.Strength;
    }

    public boolean canReroll(int RollType,rmiPlayer player)
    {
        if ((RollType==iRoll.C_ARMOR)||(RollType==iRoll.C_CASUALTY))
        {
            try
            {
                player.setState(dPlayer.C_STATE_PRONE);
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
            return true;
        }
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
