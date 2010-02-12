/*
 * draCatch.java
 *
 * Created on December 10, 2007, 11:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Roll.Agility;


import bbos.*;
import bbos.Match.Model.Roll.drAgility;
import bbos.Match.Model.rmiPlayer;

/**
 *
 * @author moi
 */
public class draCatch extends drAgility
{
    
    /** Creates a new instance of draCatch */
    public draCatch(rmiPlayer player)
    {
        super(player);
        _modifier=1;
    }
    
}
