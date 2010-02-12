/*
 * drDirection.java
 *
 * Created on December 1, 2007, 11:12 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Roll;

import bbos.Tools.bbTool;
import bbos.*;
/**
 *
 * @author moi
 */
public class drDirection implements bbos.Match.Model.Roll.iRoll
{
    
    /** Creates a new instance of drDirection */
    public drDirection()
    {
    }
    
    public int rollDices()
    {
        return bbTool.getd8();
    }
}
