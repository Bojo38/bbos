/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.Match.Model.Inducements.Cards.RandomEvent;

import bbos.Match.Model.Inducements.Cards.diCard;
import bbos.Match.Model.Inducements.Cards.diCardFactory;

/**
 *
 * @author root
 */
public class dicTacklingMachine  extends diCard{
    
    /** Creates a new instance of diCard */
    public dicTacklingMachine()
    {
        super("Tackling machine");
        _cardType=diCardFactory.C_RANDOM_EVENT;
    }

}
