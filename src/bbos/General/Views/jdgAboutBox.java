/*
 * jfCommunicationSettings.java
 *
 * Created on 11 mai 2008, 16:43
 */
package bbos.General.Views;

import bbos.Match.Automat.BeforeMatch.*;
import bbos.General.Views.*;
import bbos.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author  frederic
 */
public class jdgAboutBox extends JDialog {

    /** Creates new form jfCommunicationSettings */
    public jdgAboutBox() {
        initComponents();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlbIcon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Treasury to Petty Cash");
        setMinimumSize(new java.awt.Dimension(200, 100));
        setModal(true);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(650, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jlbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/look/ain_pacte.png"))); // NOI18N
        jPanel1.add(jlbIcon);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(320, 250));

        jTextArea1.setColumns(15);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jTextArea1.setRows(15);
        jTextArea1.setText("Games Workshop, the Games Workshop logo, Blood Bowl, the \nBlood Bowl logo, the Blood Bowl Spike device, and all associated\nmarks, names, races, race insignia, characters, vehicles,\nllocations, units, illustrations and images from the Blood Bowl are\neither �, TM and/or � Games Workshop Ltd 2000-2008, variably\nregistered in the UK and  other countries around the world. Used\nwithout permission. No challenge to their status intended. All\nRights Reserved to their respective owners.\n\nYou must own a legitimate copy of Blood Bowl (the board game\nproduced by Games Workshop Ltd) to legally use this program.\n\nIn no event shall we be liable for any special, incidental, indirect,\nor consequential damages whatsoever arising out of the use of\nor inability to use this program.\n\nThis program has been designed for private use of the Ain \nPacte team.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setMinimumSize(new java.awt.Dimension(320, 250));
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(350, 33));

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel2.add(jbtOK);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel3.setPreferredSize(new java.awt.Dimension(650, 33));
        getContentPane().add(jPanel3, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
       
            this.setVisible(false);

}//GEN-LAST:event_jbtOKActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jbtOK;
    private javax.swing.JLabel jlbIcon;
    // End of variables declaration//GEN-END:variables
    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }
}
