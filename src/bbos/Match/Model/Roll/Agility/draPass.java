/*
 * draPass.java
 *
 * Created on December 16, 2007, 5:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Roll.Agility;

import bbos.Match.Model.dPlayer;
import bbos.*;
import bbos.Match.Model.Roll.drAgility;
import bbos.Match.Model.rmiPlayer;

/**
 *
 * @author moi
 */
public class draPass extends drAgility
{
    public static final int QUICK=1;
    public static final int SHORT=2;
    public static final int LONG=3;
    public static final int BOMB=4;
    
    
    /** Creates a new instance of draPass */
    public draPass(rmiPlayer player)
    {
        super(player);
        _modifier=0;
    }
    
    public void setPassRange(int range)
    {
        switch (range)
        {
            case QUICK:
                _modifier=1;
                break;
            case SHORT:
                _modifier=0;
                break;
            case LONG:
                _modifier=-1;
                break;
            case BOMB:
                _modifier=-2;
                break;
        }
    }
    
}
