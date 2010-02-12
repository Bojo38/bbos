/*
 * dcDodge.java
 *
 * Created on 28 novembre 2007, 13:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Competences.Agility;

import bbos.Match.Model.Actions.daBlock;
import bbos.Match.Model.Competences.General.dcTackle;
import bbos.Match.Model.Competences.dCompetence;
import bbos.Match.Model.Competences.dCompetencesFactory;
import bbos.Match.Model.Roll.iRoll;
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
public class dcDodge extends dCompetence
{
    
    /** Creates a new instance of dcDodge */
    public dcDodge (String name)
    {
        super(name);
        _type=dCompetencesFactory.Agility;
    }

        public boolean canReroll(int RollType,rmiPlayer player)
    {
        if (RollType==iRoll.C_DODGE)
        {
            _used=true;
            return true;
        }
        return false;
    }

     public Vector modifyActionList(Vector actionList,rmiMatch model, rmiPlayer player, rmiTeam opponent, rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers,boolean challenger)
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
        try {
            Vector<dCompetence> comps = opp.getCompetences();
            for (int i = 0; i < comps.size(); i++) {
                dCompetence comp = comps.get(i);
                if (comp instanceof dcTackle) {
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
