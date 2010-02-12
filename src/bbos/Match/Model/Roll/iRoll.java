/*
 * iRoll.java
 *
 * Created on November 29, 2007, 11:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Roll;

/**
 *
 * @author moi
 */
public interface iRoll
{
    public static final int C_ANY=0;
    public static final int C_ARMOR=1;
    public static final int C_CASUALTY=2;
    public static final int C_DIRECTION=3;
    public static final int C_GFI=4;
    public static final int C_INJURY=5;
    public static final int C_KICKOFF=6;
    public static final int C_METEO=7;
    public static final int C_PUBLIC=8;
    public static final int C_SCATTER=9;
    public static final int C_CATCH=10;
    public static final int C_DODGE=11;
    public static final int C_GETBALL=12;
    public static final int C_INTERCEPTION=13;
    public static final int C_PASS=14;
    public static final int C_PICKUP=15;
    public static final int C_THROWTEAMMATE=16;
    public static final int C_LEAP=17;
    public static final int C_BLOCK=18;
    public static final int C_BLITZ=19;

    public static final int C_NO_MODIFIER=0;
    public static final int C_TACKLE_ZONE_MODIFIER=1;
    public static final int C_METEO_MODIFIER=2;
    public static final int C_OTHER_MODIFIER=3;

    public int rollDices();
    
}
