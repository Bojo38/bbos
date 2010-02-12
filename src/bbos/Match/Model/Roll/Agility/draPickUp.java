/*
 * draPickUp.java
 *
 * Created on December 6, 2007, 9:39 PM
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
public class draPickUp extends drAgility
{
    
    /** Creates a new instance of draPickUp */
    public draPickUp(rmiPlayer player)
    {
        super(player);
        _modifier=1;
    }
    
}
