/*
 * vMatch.java
 *
 * Created on November 26, 2007, 9:38 PM
 */

package bbos.Match.Views;

import java.awt.*;
import bbos.Match.Model.dMatch;
import bbos.Tools.bbTool;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author  moi
 */
public class vMatchBottom extends javax.swing.JPanel
{
    dMatch _model;
    
    /** Creates new form vMatch */
    public vMatchBottom()
    {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jpnExtrasRight = new javax.swing.JPanel();
        jpnExtrasLeft = new javax.swing.JPanel();
        jlbMeteoIcon = new javax.swing.JLabel();
        jpnSubCenter = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaMatchHistory = new javax.swing.JTextArea();

        setPreferredSize(new java.awt.Dimension(869, 260));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnExtrasRight.setBorder(javax.swing.BorderFactory.createTitledBorder("Extras"));

        javax.swing.GroupLayout jpnExtrasRightLayout = new javax.swing.GroupLayout(jpnExtrasRight);
        jpnExtrasRight.setLayout(jpnExtrasRightLayout);
        jpnExtrasRightLayout.setHorizontalGroup(
            jpnExtrasRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
        );
        jpnExtrasRightLayout.setVerticalGroup(
            jpnExtrasRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        jPanel1.add(jpnExtrasRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 150, 60));

        jpnExtrasLeft.setBorder(javax.swing.BorderFactory.createTitledBorder("Extras"));

        javax.swing.GroupLayout jpnExtrasLeftLayout = new javax.swing.GroupLayout(jpnExtrasLeft);
        jpnExtrasLeft.setLayout(jpnExtrasLeftLayout);
        jpnExtrasLeftLayout.setHorizontalGroup(
            jpnExtrasLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
        );
        jpnExtrasLeftLayout.setVerticalGroup(
            jpnExtrasLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        jPanel1.add(jpnExtrasLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 150, 60));

        jlbMeteoIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMeteoIcon.setPreferredSize(new java.awt.Dimension(50, 10));
        jPanel1.add(jlbMeteoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, 80, 60));

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jpnSubCenter.setLayout(new java.awt.BorderLayout());
        jpnSubCenter.add(jPanel3, java.awt.BorderLayout.WEST);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 120));

        jtaMatchHistory.setColumns(20);
        jtaMatchHistory.setEditable(false);
        jtaMatchHistory.setLineWrap(true);
        jtaMatchHistory.setRows(5);
        jScrollPane1.setViewportView(jtaMatchHistory);

        jpnSubCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jpnSubCenter, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents
    
    public void setModel(dMatch model)
    {
        _model=model;
        /*
         * Display selected player
         */
       // vPlayerSelected.setPlayer(model._selectedPlayer);
    /*
     * Display flying player
     */
      //  vPlayerOnFly.setPlayer(model._onFlyPlayer);
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbMeteoIcon;
    private javax.swing.JPanel jpnExtrasLeft;
    private javax.swing.JPanel jpnExtrasRight;
    private javax.swing.JPanel jpnSubCenter;
    private javax.swing.JTextArea jtaMatchHistory;
    // End of variables declaration//GEN-END:variables
    
   
    
    public void paint(Graphics g)
    { 
        super.paint(g) ;
        jtaMatchHistory.setText(bbTool._matchHistory);
        if (_model!=null)
        {
           /*vPitchLeft.setModel(_model.getLeftTeam());
           vPitchRight.setModel(_model.getRightTeam());*/
        }
    }
    
}