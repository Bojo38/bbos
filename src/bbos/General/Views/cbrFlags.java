/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.General.Views;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import bbos.Tools.sdLang;


class cbrFlags extends JLabel implements ListCellRenderer, ActionListener {
     
     ImageIcon[] images;
     String[] Strings;
     jdgLogon _window;
        private Font uhOhFont;

        public cbrFlags(ImageIcon[] img,String[] str,jdgLogon window) 
        {
            _window=window;
            images=img;
            Strings=str;       
            setOpaque(true);
            //setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
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
            
                String selected = ((String)value);

                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }

                //Set the icon and text.  If icon was null, say so.
                ImageIcon icon;
                    String pet;
                if (selected.equals("English"))
                {
                     icon = images[1];                    
                     pet = Strings[1];
                }
                else
                {
                     icon = images[0];                    
                     pet = Strings[0];
                }
                setIcon(icon);
                if (icon != null) {
                    setText(pet);
                    setFont(list.getFont());
                } else {
                    setUhOhText(pet + " (no image available)",
                                list.getFont());
                }
            

            return this;
        }

        //Set the font and text when no image was found.
        protected void setUhOhText(String uhOhText, Font normalFont) {
            if (uhOhFont == null) { //lazily create this font
                uhOhFont = normalFont.deriveFont(Font.ITALIC);
            }
            setFont(uhOhFont);
            setText(uhOhText);
        }
        
      public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String Name = (String)cb.getSelectedItem();
        if (Name.equals("English"))
        {
            sdLang.getSingleton().setLocale(Locale.UK);
        }
        else
        {
            sdLang.getSingleton().setLocale(Locale.FRANCE);
        }
        
        _window.repaint();
    }
    }


