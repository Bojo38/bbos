/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.Match.Automat;

/**
 *
 * @author Frederic Berger
 */
public interface iState {
    public int execute();
    public iState next();
}
