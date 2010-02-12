/*
 * jpnMyTeam.java
 *
 * Created on 8 ao�t 2008, 13:45
 */
package bbos.General.Views.MyBBoS;

import bbos.General.Views.Match.jtmMatches;
import bbos.General.Views.Match.jtrMatch;
import bbos.General.Model.mMatch;
import bbos.General.Model.mPlayer;
import bbos.General.Views.Team.*;
import bbos.General.Model.mTeam;
import bbos.MainForm;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;

/**
 *
 * @author  root
 */
public class jpnMyTeam extends javax.swing.JPanel {

    mTeam _team;
    jtmTeamPlayers team_model;

    /** Creates new form jpnMyTeam */
    public jpnMyTeam(mTeam team) {
        initComponents();
        _team = team;

        jtpGeneral.add(new jpnTeam(_team, jtmTeamPlayers.FULL_VIEW), "Roster");
        jtpGeneral.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                MainForm.getSingleton().updateMenuStates();
            }
        });

        TableColumn column;

        team_model = new jtmTeamPlayers(_team, jtmTeamPlayers.EX_VIEW);
        jtbExPlayers.setModel(team_model);

        jtcRendererPlayers jtp = new jtcRendererPlayers(_team, this, jtmTeamPlayers.EX_VIEW);
        jtbExPlayers.setDefaultRenderer(String.class, jtp);
        jtbExPlayers.setDefaultRenderer(Integer.class, jtp);
        jtbExPlayers.setDefaultRenderer(Boolean.class, jtp);
        jtbExPlayers.setRowHeight(20);

        for (int i = 0; i < jtbExPlayers.getModel().getColumnCount(); i++) {
            column = jtbExPlayers.getColumnModel().getColumn(i);
            column.setHeaderRenderer(jtp);
            switch (i) {
                case 0:
                    column.setPreferredWidth(10);
                    break;
                case 1:
                    column.setPreferredWidth(100);
                    break;
                case 2:
                    column.setPreferredWidth(40);
                    break;
                case 3:
                    column.setPreferredWidth(10);
                    break;
                case 4:
                    column.setPreferredWidth(10);
                    break;
                case 5:
                    column.setPreferredWidth(10);
                case 6:
                    column.setPreferredWidth(10);
                    break;
                case 7:
                    column.setPreferredWidth(200);
                case 8:
                    column.setPreferredWidth(200);
                    break;
                case 9:
                    column.setPreferredWidth(30);
                    break;
                case 10:
                    column.setPreferredWidth(10);
                    break;
                case 11:
                    column.setPreferredWidth(10);
                    break;
                case 12:
                    column.setPreferredWidth(10);
                    break;
                case 13:
                    column.setPreferredWidth(10);
                    break;
                case 14:
                    column.setPreferredWidth(10);
                case 15:
                    column.setPreferredWidth(10);
                    break;
                case 16:
                    column.setPreferredWidth(10);
                case 17:
                    column.setPreferredWidth(20);
                    break;
                }
        }


        jtbMatches.setModel(new jtmMatches(_team.getMatches()));
        jtbMatches.setDefaultRenderer(mMatch.class, new jtrMatch(_team));
        jtbMatches.setDefaultEditor(mMatch.class, new jtrMatch(_team));

        jtbMatches.setRowHeight(60);
        jtbMatches.setIntercellSpacing(new Dimension(5, 5));
        jtbMatches.getTableHeader().setVisible(false);

    }

    public void refresh() {
        int index = jtpGeneral.getSelectedIndex();
        if (index == 0) {
            jtbMatches.setModel(new jtmMatches(_team.getMatches()));
            jtbMatches.setDefaultRenderer(mMatch.class, new jtrMatch(_team));
            jtbMatches.setDefaultEditor(mMatch.class, new jtrMatch(_team));

            jtbMatches.setRowHeight(60);
            jtbMatches.setIntercellSpacing(new Dimension(5, 5));
            jtbMatches.getTableHeader().setVisible(false);
        } else {
            repaint();
        }
    }

    public mPlayer getSelectedPlayer() {
        if (jtpGeneral.getSelectedIndex() == 1) {
            jpnTeam teampanel = (jpnTeam) jtpGeneral.getSelectedComponent();
            return teampanel.getSelectedPlayer();
        } else {
            return null;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jtpGeneral = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbExPlayers = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtaDiary = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbMatches = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ex-players"));

        jtbExPlayers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jtbExPlayers);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 930, 250));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Current League"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("League name :");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Current position :");

        jLabel3.setText("League");
        jLabel3.setEnabled(false);

        jLabel4.setText("Position");
        jLabel4.setEnabled(false);

        jButton1.setText("Change League");
        jButton1.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)))
                    .addComponent(jButton1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 520, 90));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Team diary"));

        jtaDiary.setColumns(20);
        jtaDiary.setEditable(false);
        jtaDiary.setRows(5);
        jScrollPane3.setViewportView(jtaDiary);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 520, 210));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Matches"));

        jtbMatches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbMatches);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        jtpGeneral.addTab("General Settings", jPanel1);

        add(jtpGeneral, java.awt.BorderLayout.CENTER);
        jtpGeneral.getAccessibleContext().setAccessibleName("Management");
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtaDiary;
    private javax.swing.JTable jtbExPlayers;
    private javax.swing.JTable jtbMatches;
    private javax.swing.JTabbedPane jtpGeneral;
    // End of variables declaration//GEN-END:variables
    public void paint(Graphics g) {
        super.paint(g);

        team_model = new jtmTeamPlayers(_team, jtmTeamPlayers.EX_VIEW);
        jtbExPlayers.setModel(team_model);
    }
}
