/*
 * jfrNewTeam1.java
 *
 * Created on 3 août 2008, 17:59
 */
package bbos.General.Views.Match;

import bbos.General.Model.mMatch;
import bbos.General.Model.mTeam;

import bbos.General.Views.jdgProgressBar;
import bbos.Match.tMatch;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author  frederic
 */
public class jdgNetwork extends javax.swing.JDialog {

    mTeam _team;
    mMatch _match;

    /** Creates new form jfrNewTeam1 */
    public jdgNetwork(mTeam team, mMatch match) {
        initComponents();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }
        _team = team;
        _match = match;

        jrbServer.setSelected(true);
        repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgConnectionOptions = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtCancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jlbTeamType = new javax.swing.JLabel();
        jrbServer = new javax.swing.JRadioButton();
        jlbEmpty = new javax.swing.JLabel();
        jrbClient = new javax.swing.JRadioButton();
        jlbEmpty1 = new javax.swing.JLabel();
        jrbStandAlone = new javax.swing.JRadioButton();
        jlbIP = new javax.swing.JLabel();
        jtfAddress = new javax.swing.JTextField();
        jlbServerPort = new javax.swing.JLabel();
        jtfPort = new javax.swing.JTextField();
        jlbTimeout = new javax.swing.JLabel();
        jtfTimeout = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Match report");
        setMinimumSize(new java.awt.Dimension(400, 200));
        setModal(true);
        setResizable(false);

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel1.add(jbtOK);

        jbtCancel.setText("Cancel");
        jbtCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jbtCancel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Network settings"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridLayout(6, 0));

        jlbTeamType.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbTeamType.setText("Team race :");
        jPanel4.add(jlbTeamType);

        bgConnectionOptions.add(jrbServer);
        jrbServer.setText("Server");
        jrbServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbServerActionPerformed(evt);
            }
        });
        jPanel4.add(jrbServer);

        jlbEmpty.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel4.add(jlbEmpty);

        bgConnectionOptions.add(jrbClient);
        jrbClient.setText("Client");
        jrbClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbClientActionPerformed(evt);
            }
        });
        jPanel4.add(jrbClient);

        jlbEmpty1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel4.add(jlbEmpty1);

        bgConnectionOptions.add(jrbStandAlone);
        jrbStandAlone.setLabel("Stand alone");
        jrbStandAlone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbStandAloneActionPerformed(evt);
            }
        });
        jPanel4.add(jrbStandAlone);

        jlbIP.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbIP.setText("Server address :");
        jPanel4.add(jlbIP);

        jtfAddress.setText("127.0.0.1");
        jtfAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfAddressActionPerformed(evt);
            }
        });
        jtfAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfAddressFocusLost(evt);
            }
        });
        jPanel4.add(jtfAddress);

        jlbServerPort.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbServerPort.setText("Port :");
        jPanel4.add(jlbServerPort);

        jtfPort.setText("8080");
        jtfPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfPortActionPerformed(evt);
            }
        });
        jtfPort.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfPortFocusLost(evt);
            }
        });
        jPanel4.add(jtfPort);

        jlbTimeout.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbTimeout.setText("Timeout (seconds) :");
        jPanel4.add(jlbTimeout);

        jtfTimeout.setText("60");
        jtfTimeout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfTimeoutActionPerformed(evt);
            }
        });
        jtfTimeout.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfTimeoutFocusLost(evt);
            }
        });
        jPanel4.add(jtfTimeout);

        jPanel2.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbtOKActionPerformed
    {//GEN-HEADEREND:event_jbtOKActionPerformed
        mTeam challenger;
        mTeam opponent;
        if (_team.getId()==_match.getChallengerId())
        {
            challenger=_team;
            opponent=_match.getOppponent();
        }
        else
        {
            opponent=_team;
            challenger=_match.getOppponent();
        }
        tMatch match=tMatch.createMatch(jrbStandAlone.isSelected(),jrbServer.isSelected(), jtfAddress.getText(), Integer.valueOf(jtfPort.getText()),challenger, opponent,_match,_team.getId(),Integer.valueOf(jtfTimeout.getText()));
        match.start();
        setVisible(false);
                
        jdgProgressBar.createSingleton("/resources/images/look/ain_pacte.png", 1);
        jdgProgressBar.setProgressValue(0, "Waiting for connexion");
    }//GEN-LAST:event_jbtOKActionPerformed

    private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelActionPerformed
        setVisible(false);
}//GEN-LAST:event_jbtCancelActionPerformed

    private void jrbServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbServerActionPerformed
        repaint();
    }//GEN-LAST:event_jrbServerActionPerformed

    private void jrbClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbClientActionPerformed
        repaint();               
    }//GEN-LAST:event_jrbClientActionPerformed

    private void jtfAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfAddressFocusLost
        repaint();
    }//GEN-LAST:event_jtfAddressFocusLost

    private void jtfAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfAddressActionPerformed
        repaint();
    }//GEN-LAST:event_jtfAddressActionPerformed

    private void jtfPortFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfPortFocusLost
        repaint();
    }//GEN-LAST:event_jtfPortFocusLost

    private void jtfPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfPortActionPerformed
        repaint();
    }//GEN-LAST:event_jtfPortActionPerformed

    private void jtfTimeoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTimeoutActionPerformed
       repaint(); // TODO add your handling code here:
}//GEN-LAST:event_jtfTimeoutActionPerformed

    private void jtfTimeoutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfTimeoutFocusLost
       repaint(); // TODO add your handling code here:
}//GEN-LAST:event_jtfTimeoutFocusLost

    private void jrbStandAloneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbStandAloneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbStandAloneActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgConnectionOptions;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtOK;
    private javax.swing.JLabel jlbEmpty;
    private javax.swing.JLabel jlbEmpty1;
    private javax.swing.JLabel jlbIP;
    private javax.swing.JLabel jlbServerPort;
    private javax.swing.JLabel jlbTeamType;
    private javax.swing.JLabel jlbTimeout;
    private javax.swing.JRadioButton jrbClient;
    private javax.swing.JRadioButton jrbServer;
    private javax.swing.JRadioButton jrbStandAlone;
    private javax.swing.JTextField jtfAddress;
    private javax.swing.JTextField jtfPort;
    private javax.swing.JTextField jtfTimeout;
    // End of variables declaration//GEN-END:variables

    public void paint(java.awt.Graphics g) {
        super.paint(g);

        if (jrbServer.isSelected()) {
            jtfAddress.setEnabled(false);
            jlbIP.setEnabled(false);
        } else {
            jtfAddress.setEnabled(true);
            jlbIP.setEnabled(true);
        }
    }
}
