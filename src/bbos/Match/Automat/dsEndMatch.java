/*
 * dsEndMatch.java
 *
 * Created on November 29, 2007, 11:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Automat;

import bbos.Match.Automat.iSequence;
import bbos.Match.Model.rmiMatch;

/**
 *
 * @author moi
 */
public class dsEndMatch implements bbos.Match.Automat.iSequence
{
    rmiMatch _model;
    /** Creates a new instance of dsEndMatch */
    public dsEndMatch(rmiMatch model)
    {
    }
    
    public void nextStep()
    {
        System.out.println("End match");
    }
    
    
    public void resetStep()
    {
        
    }
    
    public boolean isFinished()
    {
        return true;
    }

}
