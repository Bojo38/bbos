/*
 * drKickOff.java
 *
 * Created on December 1, 2007, 11:59 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Roll;

import bbos.Tools.bbTool;
import bbos.*;
/**
 *
 * @author moi
 */ 
public class drKickOff implements iRoll
{
    
    public static final int C_KICK_GET_THE_REF=2;
    public static final int C_KICK_RIOT=3;
    public static final int C_KICK_PERFECT_DEFENSE=4;
    public static final int C_KICK_HIGH_KICK=5;
    public static final int C_KICK_CHEERING_FANS=6;
    public static final int C_KICK_METEO=7;
    public static final int C_KICK_BRILLIANT_COACH=8;
    public static final int C_KICK_QUICK_SNAP=9;
    public static final int C_KICK_BLITZ=10;
    public static final int C_KICK_ROCK=11;
    public static final int C_KICK_PITCH_INVASION=12;
    
    /**
     * Creates a new instance of drKickOff
     */
    public drKickOff()
    {
    }
    
    public int rollDices()
    {
        int result=(bbTool.get2d6());
        return result;
    }
}
