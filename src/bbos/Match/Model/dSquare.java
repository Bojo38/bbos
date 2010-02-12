/*
 * dSquare.java
 *
 * Created on December 3, 2007, 9:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *
 * @author moi
 */
public class dSquare implements Serializable
{
    
    /*
     * Square States
     * EMPTY
     * PUSH_ZONE
     * PARTNER_TO_PASS
     * OPPONENT_TO_BLOCK
     * OPPONENT_INTERCEPT
     * HAVE_BALL
     * WAIT_BALL
     */
    
    /**
     * State of the square
     */
    int _state;
    /**
     * State of the ball on the square
     */
    int _ballstate;
    
    /**
     * State constant for empty square
     */
    public static final int C_SQUARE_EMPTY=0;
     /**
     * State constant for a square where a player can be pushed
     */
    public static final int C_SQUARE_PUSH_ZONE=1;
         /**
     * State constant for a square where a partner can receive the ball
     */
    public static final int C_SQUARE_PARTNER_TO_PASS=2;
    /**
     * State constant for a square where an opponent can be blocked
     */
    public static final int C_SQUARE_OPPONENT_TO_BLOCK=3;
         /**
     * State constant for a square where an opponent can intercept
     */
    public static final int C_SQUARE_OPPONENT_INTERCEPT=4;
         /**
     * State constant for a square where a player can move
     */
    public static final int C_SQUARE_MOVE=5;
    /**
     * State constant for a square where an opponent can be fouled
     */
    public static final int C_SQUARE_FOUL=6;

     /**
     * State constant for a square where a ball is on the ground
     */
    public static final int C_SQUARE_BALL_HAS_BALL=1;
    
     /**
     * State constant for a square where a ball is up
     */
    public static final int C_SQUARE_BALL_WAIT_BALL=2;
    
     /**
     * State constant for a square where the is no ball
     */
    public static final int C_SQUARE_BALL_NO=0;
    
    /**
     * X coordinate of the square
     */
    int _X;
    /**
     * Y Coordinate of the square
     */
    int _Y;
    
    /**
     * Flag for special square
     */
    boolean _special=false;
    
    /**
     * Number of blockDices
     */
    int _blockDices;
    
    /** Creates a new instance of dSquare */
    public dSquare(int x, int y)
    {
        setX(x);
        setY(y);
        _ballstate=0;
    }
    
    /**
     * Build new square at X,y and with predefined state
     * @param x x coordinate
     * @param y y coordinate
     * @param state State of the square
     */
    public dSquare(int x, int y,int state)
    {
        setX(x);
        setY(y);
        _state=state;
    }   
     
    /**
     * Set the new X value
     * @param x x value
     */
    public void setX(int x)
    {
        _X=x;
    }
    
    /**
     * Set the Y value
     * @param y y value
     */
    public void setY(int y)
    {
        _Y=y;
    }
    
    /**
     * Return the X value
     * @return X value
     */
    public int getX()
    {
        return _X;
    }
    
    /**
     * Return the Y value
     * @return Y value
     */
    public int getY()
    {
        return _Y;
    }
    
    /**
     * Return the tackle zone number on the square
     * @param opposingTeam Opposing team
     * @return The tackle zone number
     */
    public int getTackleZone(rmiTeam opposingTeam) throws RemoteException
    {
        int nbTackleZone=0;
        int x=_X;
        int y=_Y;
        if ((_X>2)&&(_Y>0))
        {
            dSquare s1=new dSquare(x-1,y-1);
            if (opposingTeam.isAPlayerWithTackleZone(s1))
            {
                
                nbTackleZone++;
            }
        }
        
        if (_Y>0)
        {
            dSquare s2=new dSquare(x,y-1);
            if (opposingTeam.isAPlayerWithTackleZone(s2))
            {
                nbTackleZone++;
            }
        }
        if ((_X<26)&&(_Y>0))
        {
            dSquare s3=new dSquare(x+1,y-1);
            if (opposingTeam.isAPlayerWithTackleZone(s3))
            {
                nbTackleZone++;
            }
        }
        if (_X>2)
        {
            dSquare s4=new dSquare(x-1,y);
            if (opposingTeam.isAPlayerWithTackleZone(s4))
            {
                nbTackleZone++;
            }
        }
        if (_X<26)
        {
            dSquare s5=new dSquare(x+1,y);
            if (opposingTeam.isAPlayerWithTackleZone(s5))
            {
                nbTackleZone++;
            }
        }
        
        if ((_X>2)&&(_Y<=14))
        {
            dSquare s6=new dSquare(x-1,y+1);
            if (opposingTeam.isAPlayerWithTackleZone(s6))
            {
                nbTackleZone++;
            }
        }
        if (_Y<=14)
        {
            dSquare s7=new dSquare(x,y+1);
            if (opposingTeam.isAPlayerWithTackleZone(s7))
            {
                nbTackleZone++;
            }
        }
        if ((_X<26)&&(_Y<=14))
        {
            dSquare s8=new dSquare(x+1,y+1);
            if (opposingTeam.isAPlayerWithTackleZone(s8))
            {
                nbTackleZone++;
            }
        }
        return nbTackleZone;
    }
    
    /**
     * Returns the square state with the ball
     * @return ball state in the square
     */
    public int getBallState()
    {
        return _ballstate;
    }
    
        /**
     * Returns the square state w
     * @return square state
     */
    public int getState()
    {
        return _state;
    }
    
            /**
     * Returns special state of the square
     * @return square special state
     */
    public boolean isSpecial()
    {
        return _special;
    }
       /**
     * Returns the block dices number
     * @return block dices number
     */
    public int getBlockDices()
    {
        return _blockDices;
    }
    
    /**
     * set a new ballState for this square
     * @param ballState new ball state
     */
    public void setBallState(int ballState)
    {
        _ballstate=ballState;
    }
    
        /**
     * set a new State for this square
         * @param State new square state
     */
    public void setState(int State)
    {
        _state=State;
    }
    
            /**
     * set a new special State for this square for the border display
             * @param special  New special state
     */
    public void setSpecial(boolean special)
    {
       _special=special;
    }

     public boolean isOnGround()
    {
        if ((_X>=0)&&(_X<26)&&(_Y>=0)&&(_Y<16))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
