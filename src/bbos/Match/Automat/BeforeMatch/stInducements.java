/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.Match.Automat.BeforeMatch;

import bbos.Match.Automat.iState;

/**
 *
 * @author Frederic Berger
 */
public class stInducements implements iState{
    boolean _finished=false;

    public int execute()
    {
        return 0;
    }

    public iState next()
    {
        if (_finished)
        {
            return null;
        }
        else
            return this;
    }

}
