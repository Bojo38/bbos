/*
 * draPass.java
 *
 * Created on December 16, 2007, 5:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Roll.Agility;

import bbos.Match.Model.rmiPlayer;
import bbos.*;
import bbos.Match.Model.Roll.drAgility;

/**
 *
 * @author moi
 */
public class draThrowTeamMate extends drAgility
{
    public static final int QUICK=1;
    public static final int SHORT=2;
    
    
    /** Creates a new instance of draPass */
    public draThrowTeamMate(rmiPlayer player)
    {
        super(player);
        _modifier=-2;
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
        }
    }
    
}
