/*
 * diExtraReroll.java
 *
 * Created on 28 novembre 2007, 13:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Inducements;

import bbos.Match.Model.Inducements.dInducement;

/**
 *
 * @author Administrateur
 */
public class diExtraReroll extends bbos.Match.Model.Inducements.dInducement
{
    
    /** Creates a new instance of diExtraReroll */
    public diExtraReroll ()
    {
        super("Extra Reroll");
        _type=dInducement.C_EXTRA_REROLL;
    }
    
}
