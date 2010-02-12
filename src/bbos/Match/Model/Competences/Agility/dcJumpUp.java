/*
 * dcJumpUp.java
 *
 * Created on 28 novembre 2007, 13:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Competences.Agility;

import bbos.Match.Model.Competences.dCompetence;
import bbos.*;
import bbos.Match.Model.Actions.dAction;
import bbos.Match.Model.Competences.dCompetencesFactory;
import bbos.Match.Model.Roll.drAgility;
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
public class dcJumpUp extends dCompetence
{
    
    /** Creates a new instance of dcJumpUp */
    public dcJumpUp (String name)
    {
        super(name);
        _type=dCompetencesFactory.Agility;
    }

    public boolean canReroll(int RollType,rmiPlayer player)
    {
        return false;
    }

    public Vector modifyActionList(Vector actionList,rmiMatch model, rmiPlayer player)
    {
        try
        {
         if (player.getState() == dPlayer.C_STATE_PRONE) {
                actionList.add(dAction.C_BLOCK);
            }
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return actionList;
    }
    
    public boolean canDoAction(int actionType, rmiPlayer player,Vector myPlayers)
    {
        try
        {
            if ((actionType==dAction.C_BLOCK)&&(player.getState()==dPlayer.C_STATE_PRONE))
            {
                drAgility r=new drAgility(player);
                r.addModifiers(2);
                int res=r.rollDices();
                if (r.isSuccess(res))
                {
                    player.setState(dPlayer.C_STATE_OK);
                    return true;
                }
                else
                {
                    player.hasPlayed(true);
                }
            }
        }
        catch (RemoteException e)
        {
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

    public int modifyRollValue(int rollType,int rollValue, int actionType)
    {
        return rollValue;
    }

}
