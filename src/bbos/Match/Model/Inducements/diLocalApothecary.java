/*
 * diLocalApothecary.java
 *
 * Created on 28 novembre 2007, 13:20
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
public class diLocalApothecary extends bbos.Match.Model.Inducements.dInducement
{
    
    /** Creates a new instance of diLocalApothecary */
    public diLocalApothecary ()
    {
        super("Local apothecary");
        _type=dInducement.C_LOCAL_APOTHECARY;
    }
    
}
