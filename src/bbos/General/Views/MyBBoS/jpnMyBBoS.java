/*
 * jpnMyBBoS.java
 *
 * Created on 3 août 2008, 16:49
 */

package bbos.General.Views.MyBBoS;

import bbos.General.Model.mPlayer;
import bbos.General.Model.mTeam;
import bbos.MainForm;
import bbos.General.Views.MyBBoS.jpnMyTeam;
import bbos.mBBoS;
import java.util.Vector;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author  frederic
 */
public class jpnMyBBoS extends javax.swing.JPanel
{
    
    /** Creates new form jpnMyBBoS */
    public jpnMyBBoS()
    {
        initComponents();
               
        mBBoS instance=mBBoS.getSingleton();
        Vector teams=instance.getMyTeams();
        for (int i=0; i<teams.size(); i++)
        {
            mTeam team=(mTeam) teams.get(i);
            jtpMyBBoS.add(new jpnMyTeam(team),team.getName());
        }
        
        jtpMyBBoS.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                MainForm.getSingleton().updateMenuStates();
                MainForm.getSingleton().redraw();
            }
        });
        
    }
    
    public void refresh()
    {
        int index=jtpMyBBoS.getSelectedIndex();
        if (index>=0)
        {
            jpnMyTeam jpn=( jpnMyTeam)jtpMyBBoS.getSelectedComponent();
            jpn.refresh();
        }
    }
    
    public mTeam getActiveTeam()
    {
        int index=jtpMyBBoS.getSelectedIndex();
        if ((index>=0)&&(index<mBBoS.getSingleton().getMyTeams().size()))
        {
            return (mTeam) mBBoS.getSingleton().getMyTeams().get(index);
        }
        else
            return null;
    }
    
    public mPlayer getSelectedPlayer()
    {
        jpnMyTeam tpanel=(jpnMyTeam)jtpMyBBoS.getSelectedComponent();
        return tpanel.getSelectedPlayer();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtpMyBBoS = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());
        add(jtpMyBBoS, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jtpMyBBoS;
    // End of variables declaration//GEN-END:variables
    
}