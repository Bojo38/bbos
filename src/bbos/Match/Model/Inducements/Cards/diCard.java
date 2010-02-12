/*
 * diCard.java
 *
 * Created on 28 novembre 2007, 13:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Inducements.Cards;

import bbos.Match.Model.Inducements.*;
import bbos.Match.Model.Inducements.dInducement;

/**
 *
 * @author Administrateur
 */
public abstract class diCard extends dInducement {

    protected int _cardType;

    /** Creates a new instance of diCard */
    public diCard(String name) {
        super(name);
        _type=dInducement.C_CARD;
    }

    public String getPublicName() {
        String name = super.getPublicName();
        switch (_cardType) {
            case diCardFactory.C_DESPERATE_MEASURE:
                name = "Card : Desperate measure";
                break;
            case diCardFactory.C_DIRTY_TRICK:
                name = "Card : Dirty trick";
                break;
            case diCardFactory.C_GOOD_KARMA:
                name = "Card : Good karma";
                break;
            case diCardFactory.C_MAGIC_ITEM:
                name = "Card : Magic item";
                break;
            case diCardFactory.C_MISC_MAYHEM:
                name = "Card : Misc. Mayhem";
                break;
            case diCardFactory.C_RANDOM_EVENT:
                name = "Card : Random event";
                break;
            case diCardFactory.C_SPECIAL_PLAY:
                name = "Card : Special play";
                break;
        }
        return name;
    }
    
    public int getCardType()
    {
        return _cardType;
    }
}
