/*
 * dAction.java
 *
 * Created on 28 novembre 2007, 18:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Actions;

import bbos.Match.Model.dSquare;
import javax.swing.Icon;

/**
 *
 * @author Administrateur
 */
public abstract class dAction {

    public final static int C_MOVE = 0;
    public final static int C_HANDOFF = 1;
    public final static int C_PASS = 2;
    public final static int C_BLOCK = 7;
    public final static int C_BLITZ = 3;
    public final static int C_FOUL = 4;
    public final static int C_BLOCK_STAB = 5;
    public final static int C_BLITZ_STAB = 6;
    public final static int C_FANATIC = 8;
    public final static int C_THROW_TEAM_MATE = 9;
    public final static int C_HYPNOTIC_GAZE = 10;
    public final static int C_BOMBER = 11;
    protected int _action = 0;
    protected int _step = 0;
    protected boolean _end=false;
    
    public dAction(int type) {
        _action = type;
    }

    public int getAction() {
        return _action;
    }

    public int getStep() {
        return _step;
    }

    public void setStep(int step)
    {
        _step=step;
    }  
    
    //public abstract void selectSquares();

    public abstract void preStep();

    public abstract void resetStep();

    public abstract void step(dSquare s);
    
    public boolean end()
    {
        return _end;
    }
    
    public void end(boolean value)
    {
        _end=value;
    }

    public abstract void postStep();

    public abstract Icon getIcon();
    
    public abstract String getName();
}
