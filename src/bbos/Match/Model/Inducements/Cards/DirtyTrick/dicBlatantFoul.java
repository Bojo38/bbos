/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.Match.Model.Inducements.Cards.DirtyTrick;

import bbos.Match.Model.Inducements.Cards.diCard;
import bbos.Match.Model.Inducements.Cards.diCardFactory;

/**
 *
 * @author root
 */
public class dicBlatantFoul  extends diCard{
    
    /** Creates a new instance of diCard */
    public dicBlatantFoul()
    {
        super("Blatant foul");
        _cardType=diCardFactory.C_DIRTY_TRICK;
    }

}
