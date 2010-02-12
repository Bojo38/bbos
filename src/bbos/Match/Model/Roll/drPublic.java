/*
 * drPublic.java
 *
 * Created on November 29, 2007, 11:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Roll;

import bbos.Tools.bbTool;
import bbos.*;
import java.util.*;
/**
 *
 * @author moi
 */
public class drPublic
{
    int _modifier;
    
    /** Creates a new instance of drPublic */
    public drPublic()
    {
        _modifier=0;
    }
    
    public int rollDices()
    {
        int result=Math.max(2,(int)(bbTool.get2d6()+_modifier));
        return result;
    }
    
    public void addModifiers(int mod)
    {
        _modifier=_modifier+mod;
    }
    
}
