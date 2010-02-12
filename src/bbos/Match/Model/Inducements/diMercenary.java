/*
 * diMercenary.java
 *
 * Created on 28 novembre 2007, 13:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Inducements;

import bbos.Match.Model.Inducements.dInducement;
import bbos.Match.Model.dPlayer;



/**
 *
 * @author Administrateur
 */
public class diMercenary extends dInducement
{
    
    /** Creates a new instance of diMercenary */
    public diMercenary ()
    {
        super ("Mercenary");
        _type=dInducement.C_MERCENARY;
    }

    
    String _publicName;
    
    public String getPublicName()
    {
        return "Mercenary : "+_publicName;
    }
    
    public void setPublicName(String name)
    {
        _publicName=name;
    }
}
