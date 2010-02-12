/*
 * drInjury.java
 *
 * Created on December 1, 2007, 12:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Roll;

import bbos.Tools.bbTool;
import bbos.*;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.rmiPlayer;
import java.rmi.RemoteException;

/**
 *
 * @author moi
 */
public class drInjury implements bbos.Match.Model.Roll.iRoll
{

    public final static int C_INJURY_FINE = 0;
    public final static int C_INJURY_KO = 0;
    public final static int C_INJURY_BH = 0;
    public final static int C_INJURY_SI = 0;
    public final static int C_INJURY_DEAD = 0;
    boolean _isDouble;
    int _modifier = 0;

    /**
     * Creates a new instance of drInjury
     */
    public drInjury()
    {
        _isDouble = false;
    }

    public int rollDices()
    {
        int n1 = bbTool.getd6();
        int n2 = bbTool.getd6();
        if (n1 == n2)
        {
            _isDouble = true;
        }
        return n1 + n2 + _modifier;
    }

    public void addModifiers(int mod)
    {
        _modifier = _modifier + mod;
    }

    public int applyEffects(int injury_roll, rmiPlayer player)
    {
        int state = dPlayer.C_STATE_OK;
        if (injury_roll < 8)
        {

            state = dPlayer.C_STATE_STUNNED;
        }
        else
        {
            if (injury_roll < 10)
            {
                state = dPlayer.C_STATE_KO;
            }
            else
            {
                /*
                 * Casualty roll effect
                 */
                drCasualty casualty = new drCasualty();
                int casualty_roll = casualty.rollDices();
                state = casualty.applyEffects(casualty_roll, player);
            }
        }
        try
        {
            player.setState(state);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return state;

    }

    public boolean isDouble()
    {
        return _isDouble;
    }
}
