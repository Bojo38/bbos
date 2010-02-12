/*
 * jpnCellMatchDone.java
 *
 * Created on 14 ao�t 2008, 15:46
 */

package bbos.General.Views.Match;

import bbos.General.Model.mMatch;
import bbos.General.Model.mTeam;
import java.awt.Color;

/**
 *
 * @author  root
 */
public class jpnCellMatchInCourse extends javax.swing.JPanel {
    
    mMatch _match;
    mTeam _team;
    /** Creates new form jpnCellMatchDone */
    public jpnCellMatchInCourse(int number,mMatch match,mTeam team) {
        initComponents();
        
        _match=match;
        _team=team;
        if (number%2==0)
        {
            this.setBackground(Color.WHITE);
        }
        
        jlbMatchDescription.setText("You have started a match against "+_match.getOppponent().getName());
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jlbMatchDescription = new javax.swing.JLabel();
        jlbIcon = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jbtContinueMatch = new javax.swing.JButton();
        jbtMatchDetails1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("ToolBar.dockingForeground")));
        setPreferredSize(new java.awt.Dimension(310, 60));
        setLayout(new java.awt.GridLayout(2, 0));

        jPanel2.setLayout(new java.awt.BorderLayout());

        jlbMatchDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMatchDescription.setText("You have a match in course against");
        jPanel2.add(jlbMatchDescription, java.awt.BorderLayout.CENTER);
        jPanel2.add(jlbIcon, java.awt.BorderLayout.LINE_START);

        add(jPanel2);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 2, 2));

        jbtContinueMatch.setText("Continue");
        jbtContinueMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtContinueMatchActionPerformed(evt);
            }
        });
        jPanel1.add(jbtContinueMatch);

        jbtMatchDetails1.setText("Abandon");
        jPanel1.add(jbtMatchDetails1);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtContinueMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtContinueMatchActionPerformed
        jdgNetwork window=new jdgNetwork(_team, _match);
        window.setVisible(true);
}//GEN-LAST:event_jbtContinueMatchActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtContinueMatch;
    private javax.swing.JButton jbtMatchDetails1;
    private javax.swing.JLabel jlbIcon;
    private javax.swing.JLabel jlbMatchDescription;
    // End of variables declaration//GEN-END:variables
    
}