/*
 * dInducement.java
 *
 * Created on 28 novembre 2007, 13:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Inducements;

import java.io.Serializable;
import java.rmi.Remote;

/**
 *
 * @author Administrateur
 */
public abstract class dInducement implements Remote, Serializable
{
    String _name;
    boolean _used=false;
    protected int _type;
    
    public static final int C_BLOODWEISER_BABE=0;
    public static final int C_BRIBE_THE_REF=1;
    public static final int C_HALFLING_CHEF=2;
    public static final int C_EXTRA_REROLL=3;
    public static final int C_IGOR=4;
    public static final int C_LOCAL_APOTHECARY=5;
    public static final int C_MERCENARY=6;
    public static final int C_STAR_PLAYER=7;
    public static final int C_WIZARD=8;
    public static final int C_CARD=9;
    
    /** Creates a new instance of dInducement */
    public dInducement (String name)
    {
        _name=name;
    }
    
    public String getPublicName()
    {
        return _name;
    }
    
    public String getName()
    {
        return _name;
    }
    
    public boolean isUsed()
    {
        return _used;
    }
    
    public int getType()
    {
        return _type;
    }
}
