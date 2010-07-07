/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.Match.Automat;

/**
 *
 * @author Frederic Berger
 */
public class stBeforeMatch implements iState{

    boolean _finished=false;
    
    public int execute()
    {
        return 0;
    }
    
    public iState next()
    {
        if (_finished)
        {
            return new stMatch();
        }
        else
            return this;
    }

}
