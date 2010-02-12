/*
 * drMeteo.java
 *
 * Created on November 29, 2007, 11:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Roll;

import bbos.Tools.bbTool;
import bbos.*;
import bbos.Match.Model.rmiMatch;
/**
 *
 * @author moi
 */
public class drMeteo implements bbos.Match.Model.Roll.iRoll
{
    /** Creates a new instance of drMeteo */
    public drMeteo()
    {
    }
    
    public int rollDices()
    {
        int dice1;
        int dice2;
        
        dice1=bbTool.getd6();
        dice2=bbTool.getd6();
        
        
        return dice1+dice2;
    }
    
}
