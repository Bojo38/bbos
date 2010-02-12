/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.Match.Model.Inducements.Cards.SpecialPlay;

import bbos.Match.Model.Inducements.Cards.diCard;
import bbos.Match.Model.Inducements.Cards.diCardFactory;

/**
 *
 * @author root
 */
public class dicOptionPlay  extends diCard{
    
    /** Creates a new instance of diCard */
    public dicOptionPlay()
    {
        super("Option play");
        _cardType=diCardFactory.C_SPECIAL_PLAY;
    }

}
