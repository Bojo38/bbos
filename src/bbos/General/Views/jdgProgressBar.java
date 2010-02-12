/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.General.Views;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import java.lang.reflect.InvocationTargetException; 
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities; 

/**
 *
 * @author root
 */
public class jdgProgressBar extends JDialog {
    
    private JProgressBar progressBar = null; 
    private int maxValue = 0; 

    private static jdgProgressBar _singleton;
    
    public static void createSingleton(String filename, int intProgressMaxValue)
    {
         _singleton=new jdgProgressBar(filename, intProgressMaxValue);
    }
             
    public jdgProgressBar getSingleton()
    {
        return _singleton;
    }
    
    private jdgProgressBar(String filename, int intProgressMaxValue) {
        super();
        
        //initialise la valeur a laquelle le splash screen doit etre fermé
        this.maxValue = intProgressMaxValue;
        
       // this.setAlwaysOnTop(true);
                
        //ajoute la progress bar
        progressBar = new JProgressBar(0, intProgressMaxValue);
        getContentPane().add(progressBar, BorderLayout.SOUTH);
        
        java.net.URL path=getClass().getResource(filename);
        ImageIcon img=new ImageIcon(path);
       JLabel image = new JLabel(img);
        // ajoute le label au panel
        getContentPane().add(image, BorderLayout.CENTER);
        pack(); 
        // centre le splash screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = image.getPreferredSize();
        setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2)); 
        // rend le splash screen invisible lorsque l'on clique dessus
        // affiche le splash screen
        setVisible(true); 
        
        progressBar.setString("");
        progressBar.setStringPainted(true);
        setModal(true);
        
        
    }
    
     
    //change la valeur de la progress bar
    public static void setProgressValue(int value,String text) {
        _singleton.progressBar.setValue(value);
        _singleton.progressBar.setString(text);
        //si est arrivé a la valeur max : ferme le splash screen en lancant le thread
        if (value >= _singleton.maxValue) {
         //   try {
                _singleton.setVisible(false);

        }
        
        
    }
    
   /* // thread pour fermer le splash screen
    final Runnable closerRunner = new Runnable() {
        public void run() {
            setVisible(false);
            dispose();
        }
    };*/
}

