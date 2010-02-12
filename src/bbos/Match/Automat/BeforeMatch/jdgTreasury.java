/*
 * jfCommunicationSettings.java
 *
 * Created on 11 mai 2008, 16:43
 */
package bbos.Match.Automat.BeforeMatch;

import bbos.General.Views.*;
import bbos.*;
import bbos.Match.Model.dTeam;
import bbos.Match.Model.rmiTeam;
import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

/**
 *
 * @author  frederic
 */
public class jdgTreasury extends JDialog {

    long _initialTreasury;
    long _tv_diff;
    rmiTeam _model;

    /** Creates new form jfCommunicationSettings */
    public jdgTreasury(rmiTeam model, long tv_diff) {
        initComponents();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }

        try {
            _initialTreasury = model.getTreasury();
             this.setTitle("Treasury to Petty Cash: "+model.getName());
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
        _tv_diff = tv_diff;
        _model = model;

       

        repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlbTreasury = new javax.swing.JLabel();
        jtfTreasury = new javax.swing.JTextField();
        jlbPattyCash = new javax.swing.JLabel();
        jspPettyCash = new javax.swing.JSpinner();
        jlbTV = new javax.swing.JLabel();
        jtfTV = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Treasury to Petty Cash");
        setMinimumSize(new java.awt.Dimension(200, 100));
        setModal(true);
        setResizable(false);

        jPanel1.setLayout(new java.awt.GridLayout(3, 0));

        jlbTreasury.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbTreasury.setText("Treasury :");
        jPanel1.add(jlbTreasury);

        jtfTreasury.setEditable(false);
        jPanel1.add(jtfTreasury);

        jlbPattyCash.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbPattyCash.setText("Petty cash:");
        jPanel1.add(jlbPattyCash);

        jspPettyCash.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(10000L)));
        jspPettyCash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspPettyCashMouseClicked(evt);
            }
        });
        jspPettyCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jspPettyCashKeyPressed(evt);
            }
        });
        jPanel1.add(jspPettyCash);

        jlbTV.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbTV.setText("TV difference :");
        jPanel1.add(jlbTV);

        jtfTV.setEditable(false);
        jPanel1.add(jtfTV);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel2.add(jbtOK);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbtOKActionPerformed
    {//GEN-HEADEREND:event_jbtOKActionPerformed
        try
        {
            Object tmp=jspPettyCash.getValue();
            long spent=((Long)tmp).longValue();
            _model.setTreasury(_initialTreasury - spent);
        
        long value=(Long) jspPettyCash.getModel().getValue();
        if (_tv_diff<0)
        {
            value-=_tv_diff;
        }
        _model.setPettyCash(value);
        
        }
        catch (RemoteException e)
        {
            System.err.println(e.getMessage());
        }
        this.setVisible(false);
}//GEN-LAST:event_jbtOKActionPerformed

    private void jspPettyCashKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jspPettyCashKeyPressed
        repaint();
    }//GEN-LAST:event_jspPettyCashKeyPressed

    private void jspPettyCashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspPettyCashMouseClicked
      repaint();
    }//GEN-LAST:event_jspPettyCashMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtOK;
    private javax.swing.JLabel jlbPattyCash;
    private javax.swing.JLabel jlbTV;
    private javax.swing.JLabel jlbTreasury;
    private javax.swing.JSpinner jspPettyCash;
    private javax.swing.JTextField jtfTV;
    private javax.swing.JTextField jtfTreasury;
    // End of variables declaration//GEN-END:variables
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        long pettyCash = (Long) jspPettyCash.getValue();
        ((SpinnerNumberModel)jspPettyCash.getModel()).setMaximum(_initialTreasury);
        
        jtfTreasury.setText(Long.toString(_initialTreasury - pettyCash));
        jtfTV.setText(Long.toString(_tv_diff));

    }
}
