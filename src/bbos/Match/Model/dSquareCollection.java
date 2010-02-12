/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match.Model;

import java.io.Serializable;
import java.util.*;

/**
 * Manage a collection of squares
 * @author frederic
 */
public class dSquareCollection implements Serializable
{

    /**
     * Squares of the collection
     */
    dSquare _squares[][];
    /**
     * Number of squares in X
     */
    int _nbX;
    /**
     * Number of squares in Y
     */
    int _nbY;

    /**
     * Constructor of the collection
     * @param nbX number in X
     * @param nbY number of Y
     */
    public dSquareCollection(int nbX, int nbY)
    {
        _squares = new dSquare[nbX][nbY];
        _nbX = nbX;
        _nbY = nbY;

        for (int i_x = 0; i_x < nbX; i_x++)
        {
            for (int i_y = 0; i_y < nbY; i_y++)
            {
                _squares[i_x][i_y] = new dSquare(i_x, i_y);
                _squares[i_x][i_y]._state = dSquare.C_SQUARE_EMPTY;
            }
        }
    }

    /**
     * Set blocking dice number
     * @param x X coordinate
     * @param y Y coordinate
     * @param nbDices NUmber of dices
     */
    public void setBlockDiceNumber(int x, int y, int nbDices)
    {
        _squares[x][y]._state = dSquare.C_SQUARE_OPPONENT_TO_BLOCK;
        _squares[x][y]._blockDices = nbDices;

    }

    /**
     * Set the intercepton square
     * @param s Square
     */
    public void setInterception(dSquare s)
    {
        _squares[s._X][s._Y]._state = dSquare.C_SQUARE_OPPONENT_INTERCEPT;
    }

    /**
     * Set the catch square
     * @param s
     */
    public void setCatch(dSquare s)
    {
        _squares[s._X][s._Y]._state = dSquare.C_SQUARE_PARTNER_TO_PASS;
    }

    /**
     * Reset squares before a pass
     */
    public void removePass()
    {
        for (int i_x = 0; i_x < _nbX; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if ((_squares[i_x][i_y]._state == dSquare.C_SQUARE_OPPONENT_INTERCEPT) ||
                        (_squares[i_x][i_y]._state == dSquare.C_SQUARE_PARTNER_TO_PASS)||
                        (_squares[i_x][i_y]._state == dSquare.C_SQUARE_OPPONENT_INTERCEPT))
                {
                    _squares[i_x][i_y]._state = dSquare.C_SQUARE_EMPTY;
                }
            }
        }
    }
    
     public void removeFoul()
    {
        for (int i_x = 0; i_x < _nbX; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if (_squares[i_x][i_y]._state == dSquare.C_SQUARE_FOUL)
                {
                    _squares[i_x][i_y]._state = dSquare.C_SQUARE_EMPTY;
                }
            }
        }
    }
    
    /**
     * Reset squares before the beginning of a turn
     */
     public void resetTurn()
    {
        for (int i_x = 0; i_x < _nbX; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if ((_squares[i_x][i_y]._state != dSquare.C_SQUARE_BALL_HAS_BALL) &&
                        (_squares[i_x][i_y]._state != dSquare.C_SQUARE_BALL_WAIT_BALL))
                {
                    _squares[i_x][i_y]._state = dSquare.C_SQUARE_EMPTY;
                }
            }
        }
    }

     /**
      * Set the ball on the square
      * @param s Square
      */
    public void setBall(dSquare s)
    {
        _squares[s._X][s._Y]._ballstate = dSquare.C_SQUARE_BALL_HAS_BALL;
    }

    /**
     * Set the ball square
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setBall(int x, int y)
    {
        _squares[x][y]._ballstate = dSquare.C_SQUARE_BALL_HAS_BALL;
    }

    /**
     * Set the ball square as waiting
     * @param s square
     */
    public void setWaitBall(dSquare s)
    {
        _squares[s._X][s._Y]._ballstate = dSquare.C_SQUARE_BALL_WAIT_BALL;
    }

    /**
     * Set the pushable square
     * @param s square
     */
    public void setPush(dSquare s)
    {
        _squares[s._X][s._Y]._state = dSquare.C_SQUARE_PUSH_ZONE;
    }

    /**
     * Set the fouling square
     * @param s square
     */
    public void setFoul(dSquare s)
    {
        _squares[s._X][s._Y]._state = dSquare.C_SQUARE_FOUL;
    }

    /**
     * Set the move square
     * @param s square
     */
    public void setMove(dSquare s)
    {
        _squares[s._X][s._Y]._state = dSquare.C_SQUARE_MOVE;
    }

    /**
     * Returns the squares available for pushing
     * @return square colllection
     */
    public Vector getPushSquares()
    {
        Vector v = new Vector();
        for (int i_x = 0; i_x < _nbX; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if (_squares[i_x][i_y]._state == dSquare.C_SQUARE_PUSH_ZONE)
                {
                    v.add(_squares[i_x][i_y]);
                }
            }
        }
        return v;
    }

    /**
     * Returns the squares for interception
     * @return square collection
     */
    public Vector getInterceptionSquares()
    {
        Vector v = new Vector();
        for (int i_x = 0; i_x < _nbX; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if (_squares[i_x][i_y]._state == dSquare.C_SQUARE_OPPONENT_INTERCEPT)
                {
                    v.add(_squares[i_x][i_y]);
                }
            }
        }
        return v;
    }

     /**
     * Returns the squares for fouling
     * @return square collection
     */
    public Vector getFoulSquares()
    {
        Vector v = new Vector();
        for (int i_x = 0; i_x < _nbX; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if (_squares[i_x][i_y]._state == dSquare.C_SQUARE_FOUL)
                {
                    v.add(_squares[i_x][i_y]);
                }
            }
        }
        return v;
    }

     /**
     * Returns the squares for block
     * @return square collection
     */
    public Vector getBlockSquares()
    {
        Vector v = new Vector();
        for (int i_x = 0; i_x < _nbX; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if (_squares[i_x][i_y]._state == dSquare.C_SQUARE_OPPONENT_TO_BLOCK)
                {
                    v.add(_squares[i_x][i_y]);
                }
            }
        }
        return v;
    }

     /**
     * Remove all the blocking squares
     */
    public void removeBlock()
    {
        for (int i_x = 0; i_x < _nbX; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if (_squares[i_x][i_y]._state == dSquare.C_SQUARE_OPPONENT_TO_BLOCK)
                {
                    _squares[i_x][i_y]._state = dSquare.C_SQUARE_EMPTY;
                }
                 if (_squares[i_x][i_y]._state == dSquare.C_SQUARE_PUSH_ZONE)
                {
                    _squares[i_x][i_y]._state = dSquare.C_SQUARE_EMPTY;
                }
                _squares[i_x][i_y]._special=false;
            }
        }
    }

    /**
     * Remove all the movement squares
     */
     public void removeMove()
    {
        for (int i_x = 0; i_x < _nbX; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if (_squares[i_x][i_y]._state == dSquare.C_SQUARE_MOVE)
                {
                    _squares[i_x][i_y]._state = dSquare.C_SQUARE_EMPTY;
                }
                _squares[i_x][i_y]._special=false;
            }
        }
    }
    
     /**
      * Returns the squares with balls
      * @return Squares collection
      */
    public Vector getBallSquares()
    {
        Vector v = new Vector();
        for (int i_x = 0; i_x < _nbX; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if (_squares[i_x][i_y]._ballstate == dSquare.C_SQUARE_BALL_HAS_BALL)
                {
                    v.add(_squares[i_x][i_y]);
                }
                if (_squares[i_x][i_y]._ballstate == dSquare.C_SQUARE_BALL_WAIT_BALL)
                {
                    v.add(_squares[i_x][i_y]);
                }
            }
        }
        return v;
    }

    /**
     * Returns the squares for a pass and move
     * @return Square collection 
     */
    public Vector getPassAndMoveSquares()
    {
        Vector v = new Vector();
        for (int i_x = 0; i_x < _nbX; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if ((_squares[i_x][i_y]._state == dSquare.C_SQUARE_MOVE) ||
                        (_squares[i_x][i_y]._state == dSquare.C_SQUARE_PARTNER_TO_PASS))
                {
                    v.add(_squares[i_x][i_y]);
                }
            }
        }
        return v;
    }

    /**
     * remove ball from square
     * @param x x coordinate
     * @param y y coordinate
     */
    public void removeBall(int x, int y)
    {

        if (_squares[x][y]._ballstate == dSquare.C_SQUARE_BALL_HAS_BALL)
        {
            _squares[x][y]._ballstate = dSquare.C_SQUARE_BALL_NO;
        }
        if (_squares[x][y]._ballstate == dSquare.C_SQUARE_BALL_WAIT_BALL)
        {
            _squares[x][y]._ballstate = dSquare.C_SQUARE_BALL_NO;
        }
        _squares[x][y]._special=false;
    }

    /**
     * Remove all the balls from the pitch
     */
    public void removeAllBalls()
    {
        for (int i_x = 0; i_x < _nbX; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if (_squares[i_x][i_y]._ballstate == dSquare.C_SQUARE_BALL_HAS_BALL)
                {
                    _squares[i_x][i_y]._ballstate = dSquare.C_SQUARE_BALL_NO;
                }
                if (_squares[i_x][i_y]._ballstate == dSquare.C_SQUARE_BALL_WAIT_BALL)
                {
                    _squares[i_x][i_y]._ballstate = dSquare.C_SQUARE_BALL_NO;
                }
                _squares[i_x][i_y]._special=false;
            }
        }
    }

    /**
     * Returns if the ball is on the pitch
     * @param side side of the pitch to control
     * @return state of the ball
     */
    public boolean isBallOnThePitch(int side)
    {
        int min;
        int max;
        if (side == 1)
        {
            min = 0;
            max = _nbX / 2;
        }
        else
        {
            min = _nbX / 2 + 1;
            max = _nbX;
        }

        for (int i_x = min; i_x < max; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if (_squares[i_x][i_y]._ballstate == dSquare.C_SQUARE_BALL_HAS_BALL)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns if teh ball have been placed on the pitch
     * @param side Side of the ground to control
     * @return state of the ball
     */
    public boolean isBallPlacedOnThePitch(int side)
    {
        int min;
        int max;
        if (side == 1)
        {
            min = 0;
            max = _nbX / 2;
        }
        else
        {
            min = _nbX / 2 + 1;
            max = _nbX;
        }

        for (int i_x = min; i_x < max; i_x++)
        {
            for (int i_y = 0; i_y < _nbY; i_y++)
            {
                if ((_squares[i_x][i_y]._ballstate == dSquare.C_SQUARE_BALL_WAIT_BALL)
                        ||(_squares[i_x][i_y]._ballstate == dSquare.C_SQUARE_BALL_HAS_BALL))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Return the max number of squares in X
     * @return Max number of squares in X
     */
    public int getNbX()
    {
        return _nbX;
    }
    
    /**
     * Returns the number of squares in Y
     * @return Max number of squares in Y
     */
    public int getNbY()
    {
        return _nbY;
    }
    
    /**
     * Return the Square
     * @param x x coordinate
     * @param y y coordinate
     * @return square
     */
    public dSquare getSquare(int x, int y)
    {
        return _squares[x][y];
    }
    
   public void setState(dSquare square,int state)
   {
      _squares[square.getX()][square.getY()].setState(state);
   }
   
   public void setSpecialState(dSquare square,boolean state)
   {
      _squares[square.getX()][square.getY()].setSpecial(state);
   }
}
