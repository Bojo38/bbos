/*
 * Main.java
 *
 * Created on 21 novembre 2007, 21:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos;

import bbos.General.Views.jdgLogon;
import bbos.General.Views.tModelRefresh;
import bbos.Tools.bbTool;
import bbos.Tools.dSound;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel;

/**
 *
 * @author Administrateur
 */
public class MainBBoS
{

    /** Creates a new instance of Main */
    public MainBBoS()
    {

    }

    /**
     * Main function of the project
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.setProperty("javax.xml.soap.MessageFactory","com.sun.xml.messaging.saaj.soap.ver1_1.SOAPMessageFactory1_1Impl");
        JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            SubstanceLookAndFeel lf=new SubstanceCremeCoffeeLookAndFeel();
            UIManager.setLookAndFeel(lf);
        } catch (Exception e) {
            System.out.println("Substance Creme Coffee failed to initialize");
        }

        /**dSound.sBOO.start();*/

        jdgLogon.getSingleton().setVisible(true);

        bbTool.init();
        
        mBBoS.newModel(jdgLogon._login, jdgLogon._password, jdgLogon._webservice);
        tModelRefresh modelrefresh=tModelRefresh.getSingleton();
        modelrefresh.start();
        MainForm fenetre = MainForm.getSingleton();
        fenetre.setVisible(true);
    }
}
