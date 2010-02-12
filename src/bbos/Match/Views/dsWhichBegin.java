/*
 * dsWhichBegin.java
 *
 * Created on November 30, 2007, 12:05 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Views;

import bbos.Tools.bbTool;
import bbos.Match.Model.rmiMatch;
import javax.swing.*;
import bbos.*;
import bbos.Match.Automat.iSequence;
import java.rmi.RemoteException;
/**
 *
 * @author moi
 */
public class dsWhichBegin implements iSequence
{
    rmiMatch _model;
    /**
     * Creates a new instance of dsWhichBegin
     */
    public dsWhichBegin(rmiMatch model)
    {
        _model=model;
    }
    
    public void nextStep()
    {
        try
        {
        Object[] possibleValues = { "Heads", "Tail" };
        Object selectedValue = JOptionPane.showInputDialog(null, "Choose one", "Input", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
        if (selectedValue==possibleValues[bbTool.getd2()-1])
        {
            Object[] possibleOptions = { "Kick", "Receive" };
            Object choix = JOptionPane.showInputDialog(null, "Player 1 choose ", "Input", JOptionPane.INFORMATION_MESSAGE, null, possibleOptions, possibleOptions[0]);
            if (choix==possibleOptions[0])
            {
                _model.setActiveCoach(1);
                bbTool.addLog("Player 1 : Set players on the pitch\n");
            }
            else
            {
               _model.setActiveCoach(2);
                bbTool.addLog("Player 2 : Set players on the pitch\n");
            }
        }
        else
        {
            Object[] possibleOptions = { "Kick", "Receive" };
            Object choix = JOptionPane.showInputDialog(null, "Player 2 choose", "Input", JOptionPane.INFORMATION_MESSAGE, null, possibleOptions, possibleOptions[0]);
            if (choix==possibleOptions[0])
            {
                _model.setActiveCoach(2);
                bbTool.addLog("Player 2 : Set players on the pitch\n");
            }
            else
            {
                _model.setActiveCoach(1);
                bbTool.addLog("Player 1 : Set players on the pitch\n");
            }
        }
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
    
    public void resetStep()
    {
    }
    
    public boolean isFinished()
    {
        return true;
    }

    
}
