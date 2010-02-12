/*
 * drCasualty.java
 *
 * Created on December 1, 2007, 12:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Roll;

import bbos.Tools.bbTool;
import bbos.Match.Model.dPlayer;
import bbos.*;
import bbos.Match.Model.rmiPlayer;

/**
 *
 * @author moi
 */
public class drCasualty implements iRoll
{
    
    /** Creates a new instance of drCasualty */
    public drCasualty()
    {
    }
    
    
    public int rollDices()
    {
        return bbTool.getd6()*10+bbTool.getd8();
    }
    
    public int applyEffects(int roll, rmiPlayer player)
    {
        if (roll<40)
        {
            /*
             * Commotion (BH)
             */
            return dPlayer.C_STATE_BADLY_HURT;
        }
        else if (roll<50)
        {
            /*
             * Miss a match (SI)
             */
            return dPlayer.C_STATE_SERIOUSLY_INJURED_MISS;
        }        
        else if (roll<53)
        {
            return dPlayer.C_STATE_SERIOUSLY_INJURED_PERS;
        }
        
        else if (roll<55)
        {
            /*
             * -1 M (SI)
             */
            return dPlayer.C_STATE_SERIOUSLY_INJURED_MA;
        }
        
        else if (roll<57)
        {
            /*
             * -1 Ar (SI)
             */
            return dPlayer.C_STATE_SERIOUSLY_INJURED_AR;
        }
        else if (roll<58)
        {
            /*
             * -1 Ag (SI)
             */
            return dPlayer.C_STATE_SERIOUSLY_INJURED_AG;
        }
        else if (roll<59)
        {
            /*
             * -1 F (SI)
             */
           return dPlayer.C_STATE_SERIOUSLY_INJURED_ST;
        }
        else 
        {
            return dPlayer.C_STATE_DEAD;
        }
    }
}
