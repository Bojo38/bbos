/*
 * dCompetence.java
 *
 * Created on 24 novembre 2007, 11:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Competences;
import bbos.General.Model.mPlayer;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiTeam;
import bbos.Match.Model.rmiPlayer;
import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Administrateur
 */
public abstract class dCompetence implements Serializable
{
    protected String _name;
    protected dCompetenceType _type;
    protected boolean _used=false;

    /**
     * Creates a new instance of dCompetence
     */
    public dCompetence (String name)
    {
        _name=name;
    }
      
    public String getName()
    {
        return _name;
    }
    
    public dCompetenceType getType()
    {
        return _type;
    }
    
    public void setContent(dCompetence comp)
    {
        _name=comp._name;
        _type=comp._type;
    }
   
    public void reset()
    {
        _used=false;
    }

    public boolean isUsed()
    {
        return _used;
    }

    public abstract boolean canDoAction(int actionType, rmiPlayer player,Vector myPlayers);
    public abstract boolean isUsable(int actionType,rmiPlayer player, rmiPlayer opp);
    public abstract boolean ignoreModifiers(int rollType,int modifierTypes);
    public abstract boolean preRoll(int actionType,int rollTypes,rmiPlayer player);
    public abstract int modifyRollValue(int rollType,int rollValue, int actionType);
    public abstract boolean canReroll(int RollType,rmiPlayer player);
    public abstract int getOpponentRollModifier(int rollType,rmiPlayer opponent, rmiPlayer myPlayer);
    //public abstract int getRollModifier(int rollType,int actionType);
    public abstract Vector modifyActionList(Vector actionList,rmiMatch model, rmiPlayer player);  


}
