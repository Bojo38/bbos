/*
 * iSequence.java
 *
 * Created on November 29, 2007, 10:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Automat;

import bbos.Match.Model.dMatch;

/**
 * Interface pour déclarer 
 * @author frederic
 */
public interface iSequence
{

    /**
     * 
     */
    public void nextStep();

    public void resetStep();

    public boolean isFinished();
    
}
