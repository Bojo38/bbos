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
public class dicStolenPlaybook  extends diCard{
    
    /** Creates a new instance of diCard */
    public dicStolenPlaybook()
    {
        super("Stolen playbook");
        _cardType=diCardFactory.C_DIRTY_TRICK;
    }

}
