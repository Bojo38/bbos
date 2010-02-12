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
public class dicGimmeThat extends diCard{
    
    /** Creates a new instance of diCard */
    public dicGimmeThat()
    {
        super("Gimme that !");
        _cardType=diCardFactory.C_RANDOM_EVENT;
    }

}
