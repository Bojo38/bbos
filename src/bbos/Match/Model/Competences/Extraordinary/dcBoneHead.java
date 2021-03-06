/*
 * dcBoneHead.java
 *
 * Created on 28 novembre 2007, 13:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Competences.Extraordinary;

import bbos.Match.Model.Actions.dAction;
import bbos.Match.Model.Actions.daBlitz;
import bbos.Match.Model.Actions.daBlitzStab;
import bbos.Match.Model.Actions.daBlock;
import bbos.Match.Model.Actions.daBlockStab;
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
public class dcBoneHead extends bbos.Match.Model.Competences.dCompetence
{
    
    /** Creates a new instance of dcBoneHead */
    public dcBoneHead (String name)
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
        try
        {
                int res=bbTool.getd6();
                if (res==1)
                {
                    player.setState(dPlayer.C_STATE_NO_ZONE);
                    player.hasPlayed(true);
                    return false;
                }
                else
                {
                    return true;
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
