/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.Match.Model.Inducements.Cards.GoodKarma;

import bbos.Match.Model.Inducements.Cards.diCard;
import bbos.Match.Model.Inducements.Cards.diCardFactory;

/**
 *
 * @author root
 */
public class dicUnsportsmanlikeConduct  extends diCard{
    
    /** Creates a new instance of diCard */
    public dicUnsportsmanlikeConduct()
    {
        super("Unsportsmanlike conduct");
        _cardType=diCardFactory.C_GOOD_KARMA;
    }

}
