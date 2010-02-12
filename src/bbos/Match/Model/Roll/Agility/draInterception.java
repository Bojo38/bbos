/*
 * draInterception.java
 *
 * Created on December 16, 2007, 7:58 PM
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
public class draInterception extends drAgility
{
    
    /** Creates a new instance of draInterception */
    public draInterception(rmiPlayer player)
    {
        super(player);
        _modifier=-2;
    }
    
}
