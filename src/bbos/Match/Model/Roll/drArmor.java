/*
 * drArmor.java
 *
 * Created on December 5, 2007, 7:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Roll;

import bbos.Tools.bbTool;
import bbos.*;
import bbos.Match.Model.rmiPlayer;
import java.rmi.RemoteException;
/**
 *
 * @author moi
 */
public class drArmor
{
    
   int _modifier;
    boolean _isDouble;
    /** Creates a new instance of drAgility */
    public drArmor()
    {
        _isDouble=false;
    }
    
    public int rollDices()
    {
        int n1=bbTool.getd6();
        int n2=bbTool.getd6();
        if (n1==n2)
        {
            _isDouble=true;
        }
        return n1+n2+_modifier;
    }
    
    public void addModifiers(int mod)
    {
        _modifier=_modifier+mod;
    }
    
    
    public boolean isSuccess(rmiPlayer player, int roll)
    {
        try
        {
        if (roll>player.getArmor())
            return true;
        else
            return false;
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isDouble()
    {
        return _isDouble;
    }
}
