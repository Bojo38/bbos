/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.Match.Automat.DuringMatch;

import bbos.Match.Model.dRollHistory;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author root
 */
public class jlrDiceHistorical extends JPanel implements ListCellRenderer  {
    
    protected jlrDiceHistorical()
    {
        super();
    }
    
    protected jlrDiceHistorical buildPanel(dRollHistory history)
    {
        jlrDiceHistorical panel=new jlrDiceHistorical();
        panel.setLayout(new GridLayout(1,2));
        
        JLabel label=new JLabel(history.getDescription());
        panel.add(label);
        
        JPanel p2=new JPanel();
        p2.setLayout(new GridLayout(1,history.getNumber()));
        
        for (int i=0; i<history.getNumber(); i++)
        {            
            ImageIcon icon=history.getIcon(i);
            JLabel l=new JLabel(icon);
            p2.add(l);
        }
        
        return panel;
        
    }
    
    
     /*
         * This method finds the image and text corresponding
         * to the selected value and returns the label, set up
         * to display the text and image.
         */
        public Component getListCellRendererComponent(
                                           JList list,
                                           Object value,
                                           int index,
                                           boolean isSelected,
                                           boolean cellHasFocus) {
            //Get the selected index. (The index param isn't
            //always valid, so just use the value.)
            
               if (value instanceof dRollHistory)
               {
                   return buildPanel((dRollHistory)value);
               }
            

            return new JPanel();
        }

        //Set the font and text when no image was found.
        protected void setUhOhText(String uhOhText, Font normalFont) {
            /*if (uhOhFont == null) { //lazily create this font
                uhOhFont = normalFont.deriveFont(Font.ITALIC);
            }*/
           /* setFont(uhOhFont);
            setText(uhOhText);*/
        }

}
