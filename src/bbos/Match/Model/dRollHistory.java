/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match.Model;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author root
 */
public class dRollHistory implements Serializable {

    /**
     *  Type : 1 : d6 - 2 : Block
     *      
     */
    protected int _type = 0;
    /**
     * Owner 1 : team left - 2: team right
     */
    protected int _owner = 0;
    /**
     * Dices
     */
    protected int _number = 0;
    protected int _dice1 = 0;
    protected int _dice2 = 0;
    protected int _dice3 = 0;
    protected String _description;

    protected static ImageIcon I_HOME_1=null;
    protected static ImageIcon I_HOME_2=null;
    protected static ImageIcon I_HOME_3=null;
    protected static ImageIcon I_HOME_4=null;
    protected static ImageIcon I_HOME_5=null;
    protected static ImageIcon I_HOME_6=null;
    
    protected static ImageIcon I_AWAY_1=null;
    protected static ImageIcon I_AWAY_2=null;
    protected static ImageIcon I_AWAY_3=null;
    protected static ImageIcon I_AWAY_4=null;
    protected static ImageIcon I_AWAY_5=null;
    protected static ImageIcon I_AWAY_6=null;
    
    protected static ImageIcon I_HOME_BLOCK_1=null;
    protected static ImageIcon I_HOME_BLOCK_2=null;
    protected static ImageIcon I_HOME_BLOCK_3=null;
    protected static ImageIcon I_HOME_BLOCK_4=null;
    protected static ImageIcon I_HOME_BLOCK_5=null;
    protected static ImageIcon I_HOME_BLOCK_6=null;
    
    protected static ImageIcon I_AWAY_BLOCK_1=null;
    protected static ImageIcon I_AWAY_BLOCK_2=null;
    protected static ImageIcon I_AWAY_BLOCK_3=null;
    protected static ImageIcon I_AWAY_BLOCK_4=null;
    protected static ImageIcon I_AWAY_BLOCK_5=null;
    protected static ImageIcon I_AWAY_BLOCK_6=null;
    
    public ImageIcon getIcon(int number)
    {
        int n=0;
        switch (number) {
            case 1:
                n= _dice1;
                break;
            case 2:
                n= _dice2;
                break;
            case 3:
                n= _dice3;
                break;
        }
        
        String ad="/resources/images/dices/";
        if (_type==1)
        {
            ad=ad+"d6/";
        }
        else
        {
            ad=ad+"block/";
        }
        
        if (_owner==1)
        {
            ad=ad+"home-";
        }
        else
        {
            ad=ad+"away-";
        }
        
        ad=ad+Integer.toString(this.getDiceValue(number))+".png";
        
        return new ImageIcon(this.getClass().getResource(ad));
        
    }
    
    public dRollHistory(int type, int owner, String description, int dice) {
        _type = type;
        _owner = owner;
        _description = description;
        _dice1 = dice;
        _number=1;
    }

    public dRollHistory(int type, int owner, String description, int dice1, int dice2) {
        this(type, owner, description, dice1);
        _dice2 = dice2;
        _number=2;
    }

    public dRollHistory(int type, int owner, String description, int dice1, int dice2, int dice3) {
        this(type, owner, description, dice1, dice2);
        _dice3 = dice3;
        _number=3;
    }

    public int getType() {
        return _type;
    }

    public int getOwner() {
        return _owner;
    }

    public int getNumber() {
        return _number;
    }

    public int getDiceValue(int number) {
        switch (number) {
            case 1:
                return _dice1;
            case 2:
                return _dice2;
            case 3:
                return _dice3;
        }
        return 0;
    }

    public String getDescription() {
        return _description;
    }
}
