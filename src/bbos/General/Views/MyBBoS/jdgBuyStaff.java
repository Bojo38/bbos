/*
 * jdgBuyStaff.java
 *
 * Created on 13 ao�t 2008, 17:13
 */
package bbos.General.Views.MyBBoS;

import bbos.General.Model.mTeam;
import bbos.mBBoS;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author  root
 */
public class jdgBuyStaff extends javax.swing.JDialog {

    mTeam _team;
    int _remaining_treasury;
    int _reroll;
    int _assists;
    int _cheerleaders;
    boolean _apothecary;
    int _rerollPrice;
    int _total;

    /** Creates new form jdgBuyStaff */
    public jdgBuyStaff(mTeam team) {

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
        _remaining_treasury = _team.getTreasury();

        _rerollPrice = _team.getTeamType().getRerollCost() * 2;
        jlbRerollPrice.setText(Integer.toString(_rerollPrice));

        jspAssists.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                _assists = (Integer) jspAssists.getValue();
                repaint();
            }
        });

        jspCheerleaders.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                _cheerleaders = (Integer) jspCheerleaders.getValue();
                repaint();
            }
        });

        jspReroll.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                _reroll = (Integer) jspReroll.getValue();
                repaint();
            }
        });

        jcbApothecary.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                _apothecary = jcbApothecary.isSelected();
                repaint();
            }
        });


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
        jPanel2 = new javax.swing.JPanel();
        jlbCheerleaders = new javax.swing.JLabel();
        jspCheerleaders = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        jlbX1 = new javax.swing.JLabel();
        jlbCheerleadersPrice = new javax.swing.JLabel();
        jlbEq1 = new javax.swing.JLabel();
        jtfCheerleaders = new javax.swing.JTextField();
        jlbAssists = new javax.swing.JLabel();
        jspAssists = new javax.swing.JSpinner();
        jPanel5 = new javax.swing.JPanel();
        jlbX2 = new javax.swing.JLabel();
        jlbAssistsPrice = new javax.swing.JLabel();
        jlbEq2 = new javax.swing.JLabel();
        jtfAssistPrice = new javax.swing.JTextField();
        jlbReroll = new javax.swing.JLabel();
        jspReroll = new javax.swing.JSpinner();
        jPanel6 = new javax.swing.JPanel();
        jlbX3 = new javax.swing.JLabel();
        jlbRerollPrice = new javax.swing.JLabel();
        jlbEq3 = new javax.swing.JLabel();
        jtfRerollPrice = new javax.swing.JTextField();
        jlbApothecary = new javax.swing.JLabel();
        jcbApothecary = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jlbX4 = new javax.swing.JLabel();
        jlbApothecaryPrice = new javax.swing.JLabel();
        jlbEq4 = new javax.swing.JLabel();
        jtfApothecary = new javax.swing.JTextField();
        jlbEmpty1 = new javax.swing.JLabel();
        jlbEmpty2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtfTotal = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Treasury"));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jlbTreasury.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbTreasury.setText("Remaining treasury :");
        jPanel1.add(jlbTreasury);

        jtfTreasury.setEditable(false);
        jPanel1.add(jtfTreasury);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Staff")));
        jPanel2.setLayout(new java.awt.GridLayout(5, 4));

        jlbCheerleaders.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbCheerleaders.setText("Cheerleaders :");
        jPanel2.add(jlbCheerleaders);

        jspCheerleaders.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        jPanel2.add(jspCheerleaders);

        jPanel4.setLayout(new java.awt.GridLayout(1, 3));

        jlbX1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbX1.setText("X");
        jPanel4.add(jlbX1);

        jlbCheerleadersPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbCheerleadersPrice.setText("10 000");
        jPanel4.add(jlbCheerleadersPrice);

        jlbEq1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbEq1.setText("=");
        jPanel4.add(jlbEq1);

        jPanel2.add(jPanel4);

        jtfCheerleaders.setEditable(false);
        jtfCheerleaders.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfCheerleaders.setText("0");
        jPanel2.add(jtfCheerleaders);

        jlbAssists.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbAssists.setText("Assists :");
        jPanel2.add(jlbAssists);

        jspAssists.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        jPanel2.add(jspAssists);

        jPanel5.setLayout(new java.awt.GridLayout(1, 3));

        jlbX2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbX2.setText("X");
        jPanel5.add(jlbX2);

        jlbAssistsPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbAssistsPrice.setText("10 000");
        jPanel5.add(jlbAssistsPrice);

        jlbEq2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbEq2.setText("=");
        jPanel5.add(jlbEq2);

        jPanel2.add(jPanel5);

        jtfAssistPrice.setEditable(false);
        jtfAssistPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfAssistPrice.setText("0");
        jPanel2.add(jtfAssistPrice);

        jlbReroll.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbReroll.setText("Rerolls :");
        jPanel2.add(jlbReroll);

        jspReroll.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        jPanel2.add(jspReroll);

        jPanel6.setLayout(new java.awt.GridLayout(1, 3));

        jlbX3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbX3.setText("X");
        jPanel6.add(jlbX3);

        jlbRerollPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbRerollPrice.setText("0");
        jPanel6.add(jlbRerollPrice);

        jlbEq3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbEq3.setText("=");
        jPanel6.add(jlbEq3);

        jPanel2.add(jPanel6);

        jtfRerollPrice.setEditable(false);
        jtfRerollPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfRerollPrice.setText("0");
        jPanel2.add(jtfRerollPrice);

        jlbApothecary.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbApothecary.setText("Apothecary :");
        jPanel2.add(jlbApothecary);

        jcbApothecary.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jcbApothecary);

        jPanel7.setLayout(new java.awt.GridLayout(1, 3));

        jlbX4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbX4.setText("X");
        jPanel7.add(jlbX4);

        jlbApothecaryPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbApothecaryPrice.setText("50 000");
        jPanel7.add(jlbApothecaryPrice);

        jlbEq4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbEq4.setText("=");
        jPanel7.add(jlbEq4);

        jPanel2.add(jPanel7);

        jtfApothecary.setEditable(false);
        jtfApothecary.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfApothecary.setText("0");
        jPanel2.add(jtfApothecary);
        jPanel2.add(jlbEmpty1);
        jPanel2.add(jlbEmpty2);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Total :");
        jPanel2.add(jLabel4);

        jtfTotal.setEditable(false);
        jtfTotal.setFont(new java.awt.Font("Tahoma", 1, 11));
        jtfTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfTotal.setText("0");
        jPanel2.add(jtfTotal);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setMinimumSize(new java.awt.Dimension(50, 50));
        jPanel3.setPreferredSize(new java.awt.Dimension(50, 50));

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });

        jbtCancel.setText("Cancel");
        jbtCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(347, Short.MAX_VALUE)
                .addComponent(jbtCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtOK)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtOK)
                    .addComponent(jbtCancel))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed

        int option = JOptionPane.showConfirmDialog(this, "You will spend " + Integer.toString(_total)+". Are you sure ?", "Buy Staff", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            
            _team.setReroll(_reroll+_team.getReroll());
            _team.setPomPom(_cheerleaders+_team.getPomspoms());
            _team.setAssist(_assists+_team.getAssists());
            _team.setApothecary(_apothecary||_team.hasApothecary());
            _team.setTreasury(_remaining_treasury);
            mBBoS.getSingleton().updateTeam(_team);
            this.setVisible(false);
        }
       
    }//GEN-LAST:event_jbtOKActionPerformed

    private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtCancelActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtOK;
    private javax.swing.JCheckBox jcbApothecary;
    private javax.swing.JLabel jlbApothecary;
    private javax.swing.JLabel jlbApothecaryPrice;
    private javax.swing.JLabel jlbAssists;
    private javax.swing.JLabel jlbAssistsPrice;
    private javax.swing.JLabel jlbCheerleaders;
    private javax.swing.JLabel jlbCheerleadersPrice;
    private javax.swing.JLabel jlbEmpty1;
    private javax.swing.JLabel jlbEmpty2;
    private javax.swing.JLabel jlbEq1;
    private javax.swing.JLabel jlbEq2;
    private javax.swing.JLabel jlbEq3;
    private javax.swing.JLabel jlbEq4;
    private javax.swing.JLabel jlbReroll;
    private javax.swing.JLabel jlbRerollPrice;
    private javax.swing.JLabel jlbTreasury;
    private javax.swing.JLabel jlbX1;
    private javax.swing.JLabel jlbX2;
    private javax.swing.JLabel jlbX3;
    private javax.swing.JLabel jlbX4;
    private javax.swing.JSpinner jspAssists;
    private javax.swing.JSpinner jspCheerleaders;
    private javax.swing.JSpinner jspReroll;
    private javax.swing.JTextField jtfApothecary;
    private javax.swing.JTextField jtfAssistPrice;
    private javax.swing.JTextField jtfCheerleaders;
    private javax.swing.JTextField jtfRerollPrice;
    private javax.swing.JTextField jtfTotal;
    private javax.swing.JTextField jtfTreasury;
    // End of variables declaration//GEN-END:variables
    public void paint(Graphics g) {
        super.paint(g);

        jspAssists.setValue(_assists);
        jspCheerleaders.setValue(_cheerleaders);
        jspReroll.setValue(_reroll);
        jcbApothecary.setSelected(_apothecary);

        _total = _assists * 10000 + _cheerleaders * 10000 + _reroll * _rerollPrice;
        if (_apothecary) {
            _total = _total + 50000;
        }
        _remaining_treasury = _team.getTreasury() - _total;

        jtfTreasury.setText(Integer.toString(_remaining_treasury));
        jtfTotal.setText(Integer.toString(_total));
        jtfAssistPrice.setText(Integer.toString(_assists * 10000));
        jtfCheerleaders.setText(Integer.toString(_cheerleaders * 10000));
        jtfRerollPrice.setText(Integer.toString(_reroll * _rerollPrice));
        if (_apothecary) {
            jtfApothecary.setText(Integer.toString(50000));
        } else {
            jtfApothecary.setText(Integer.toString(0));
        }


        jlbApothecary.setEnabled(true);
        jcbApothecary.setEnabled(true);
        jlbX4.setEnabled(true);
        jlbApothecaryPrice.setEnabled(true);
        jlbEq4.setEnabled(true);
        jtfApothecary.setEnabled(true);

        if ((_team.hasApothecary()) || (!_team.getTeamType().isApothecary()) || (_remaining_treasury < 50000)) {
            jlbApothecary.setEnabled(false);
            jcbApothecary.setEnabled(false);
            jlbX4.setEnabled(false);
            jlbApothecaryPrice.setEnabled(false);
            jlbEq4.setEnabled(false);
            jtfApothecary.setEnabled(false);
        }

        if (_remaining_treasury < _rerollPrice) {
            SpinnerNumberModel model = (SpinnerNumberModel) jspReroll.getModel();
            model.setMaximum(Integer.valueOf(_reroll));
        }

        if (_remaining_treasury < 10000) {
            SpinnerNumberModel model = (SpinnerNumberModel) jspCheerleaders.getModel();
            model.setMaximum(Integer.valueOf(_cheerleaders));
            model = (SpinnerNumberModel) jspAssists.getModel();
            model.setMaximum(Integer.valueOf(_assists));
        }



    }
}