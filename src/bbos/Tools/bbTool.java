/*
 * bbTool.java
 *
 * Created on 24 novembre 2007, 15:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Tools;

import bbos.*;
import java.util.*;
import java.awt.image.*;
import java.awt.*;


/**
 * Class designed to generate dice rolls and some pictures information
 * @author Administrateur
 */
public class bbTool
{
   
    /** Creates a new instance of bbTool */
    public bbTool()
    {
        
    }
    
    
    public static void init()
    {
        Math.random();
    }
    
    public static int getd6()
    {
        int roll=(int)(Math.floor(Math.random() * (7-1))) +1;
        return roll;
    }
    
    public static int getd3()
    {
        int roll= (int)(Math.floor(Math.random() * (4-1))) + 1;
        return roll;
    }
    
    public static int getd8()
    {
        return (int)(Math.floor(Math.random() * (9-1))) + 1;
    }
    public static int getd2()
    {
        return (int)(Math.floor(Math.random() * (3-1))) + 1;
    }
    
    public static int getdN(int n)
    {
        return (int)(Math.floor(Math.random() * n)) + 1;
    }
    
    public static int get2d6()
    {
        return (int)(getd6()+getd6());
    }
    
    public static boolean isValidImage(String url)
    {
        boolean valid=true;
        try
        {
            MainForm.getSingleton().getToolkit().getImage(MainForm.getSingleton().getClass().getResource(url));
        }
        catch(Exception e)
        {
            valid=false;
        }
        return valid;
    }
    
    public static String getDiceUrl(int dice_result)
    {
        String name="/bbos/Images/dices/";
        if (dice_result==1)
        {
            name=name+"aFall.gif";
        }
        if (dice_result==2)
        {
            name=name+"bFall.gif";
        }
        if (dice_result==3)
        {
            name=name+"curpush.gif";
        }
        if (dice_result==4)
        {
            name=name+"curpush.gif";
        }
        if (dice_result==5)
        {
            name=name+"dDodge.gif";
        }
        if (dice_result==6)
        {
            name=name+"dFall.gif";
        }
        return name;
    }
    
    public static Image getImage(String url,String defaut,int size)
    {
        Image image=null;
        try
        {
            image=MainForm.getSingleton().getToolkit().getImage(MainForm.getSingleton().getClass().getResource(url));
        }
        catch(Exception e)
        {
            image=null;
        }
        
        if (image==null)
        {
            Image img = new BufferedImage(size,size,BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D)img.getGraphics();
            g2d.drawOval(size/2,size/2,size,size);
            g2d.drawString(defaut,size/2,size/2);            
        }
        return image;
    }
    
    public static String _matchHistory;
    
    public static void addLog(String message)
    {
        if (_matchHistory==null)
            _matchHistory="";
        _matchHistory+=message;
    }
}
