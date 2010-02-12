/*
 * diStarPlayer.java
 *
 * Created on 28 novembre 2007, 13:20
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
public class diStarPlayer extends dInducement
{
    /**
     * Creates a new instance of diStarPlayer
     */
    public diStarPlayer ()
    {
        super("StarPlayer");
        _type=dInducement.C_STAR_PLAYER;
    }
    
    String _publicName;
    
    public String getPublicName()
    {
        return "Star player : "+_publicName;
    }
    
    public void setPublicName(String name)
    {
        _publicName=name;
    }
}
