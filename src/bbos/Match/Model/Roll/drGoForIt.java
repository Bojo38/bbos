/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match.Model.Roll;

import bbos.Tools.bbTool;
import bbos.Match.Model.dMatch;
import bbos.Match.Model.dPlayer;
import bbos.Match.Views.cGround;
import bbos.*;
import bbos.Match.Model.dSquare;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import java.rmi.RemoteException;

/**
 *
 * @author frederic
 */
public class drGoForIt implements bbos.Match.Model.Roll.iRoll
{

    rmiPlayer _player;
    int _modifier;
    rmiMatch _model;
    int _owner;

    public drGoForIt(rmiPlayer player, rmiMatch model)
    {
        _player = player;
        _model = model;
        try
        {
            if (player.isChallenger())
            {
                _owner = 1;
            }
            else
            {
                _owner = 2;
            }
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    public int rollDices()
    {
        int n = bbTool.getd6();

        try
        {
            _model.AddRollHistory(n, _owner, "Go for it (" + _modifier + ")");
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }

        if (n == 1)
        {
            return 1;
        }
        else
        {
            if (n == 6)
            {
                return 6;
            }
            else
            {
                return n + _modifier;
            }
        }
    }

    public void addModifiers(int mod)
    {
        _modifier = _modifier + mod;
    }

    public boolean isSuccess(int roll)
    {
        int target;
        target = 2;

        if (roll == 1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean applyEffects(int roll)
    {
        boolean success = true;

        if (isSuccess(roll))
        {
            success = true;
        }
        else
        {
            failure(_player);
            success = false;
        }
        return success;
    }

    protected void failure(rmiPlayer player)
    {

        drArmor armor = new drArmor();
        int armorRoll = armor.rollDices();
        if (armor.isSuccess(player, armorRoll))
        {
            drInjury injury = new drInjury();
            int injuryRoll = injury.rollDices();
            injury.applyEffects(injuryRoll, player);
        }
        else
        {
            try
            {

                player.setState(dPlayer.C_STATE_PRONE);
            } catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }
}
