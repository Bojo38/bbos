/*
 * jpnTeam.java
 *
 * Created on 3 août 2008, 18:05
 */
package bbos.General.Views.Team;

import bbos.General.Model.mPlayer;
import bbos.General.Model.mTeam;
import bbos.MainForm;
import bbos.General.Views.Team.jtmSettings;
import java.awt.Graphics;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

/**
 *
 * @author  frederic
 */
public class jpnTeam extends javax.swing.JPanel {

    jtmTeamPlayers team_model;
    jtmTeamBonuses bonus_model;
    jtmSettings settings_model;
    
    mTeam _team;

    /** Creates new form jpnTeam */
    public jpnTeam(mTeam team, int view) {
        initComponents();
        
        if (jtmTeamPlayers.FULL_VIEW == view) {
            remove(jspPlayers);
            add(jspPlayers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 930, 400));
        }

        _team = team;

        if (_team.getTeamType() == null) {
            _team.setTeamRoster(0);
        }

        TableColumn column = null;
        team_model = new jtmTeamPlayers(_team, view);
        jtbPlayers.setModel(team_model);

        jtcRendererPlayers jtp = new jtcRendererPlayers(_team, this, view);
        if (jtmTeamPlayers.NEW_VIEW == view) {
            jtbPlayers.setDefaultEditor(String.class, jtp);
            jtbPlayers.setDefaultEditor(Integer.class, jtp);
        }
        jtbPlayers.setDefaultRenderer(String.class, jtp);
        jtbPlayers.setDefaultRenderer(Integer.class, jtp);
        jtbPlayers.setDefaultRenderer(Boolean.class, jtp);
        jtbPlayers.setRowHeight(20);
        jtbPlayers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                MainForm.getSingleton().updateMenuStates();
            }
        });
        
        
        for (int i = 0; i < jtbPlayers.getModel().getColumnCount(); i++) {
            column = jtbPlayers.getColumnModel().getColumn(i);
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
                    if (view == jtmTeamPlayers.FULL_VIEW) {
                        column.setPreferredWidth(200);
                    } else {
                        column.setPreferredWidth(30);
                    }
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

        bonus_model = new jtmTeamBonuses(_team, view);
        jtbBonuses.setModel(bonus_model);
        jtcRendererBonuses jtb = new jtcRendererBonuses(_team, this);
        jtbBonuses.setDefaultRenderer(String.class, jtb);
        jtbBonuses.setDefaultRenderer(Integer.class, jtb);
        if (jtmTeamPlayers.NEW_VIEW == view) {
            jtbBonuses.setDefaultEditor(Integer.class, jtb);
            jtbBonuses.setDefaultEditor(String.class, jtb);
        }
        jtbBonuses.setRowHeight(20);
        column = null;
        for (int i = 0; i < jtbBonuses.getModel().getColumnCount(); i++) {
            column = jtbBonuses.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(120);
                    break;
                case 1:
                    column.setPreferredWidth(40);
                    break;
                case 2:
                    column.setPreferredWidth(20);
                    break;
                case 3:
                    column.setPreferredWidth(80);
                    break;
                case 4:
                    column.setPreferredWidth(20);
                    break;
                case 5:
                    column.setPreferredWidth(100);
                    break;
            }
        }

        settings_model = new jtmSettings(_team, view);
        jtbSettings.setModel(settings_model);
        jtcRendererSettings jtc = new jtcRendererSettings(_team, this);
        jtbSettings.setDefaultRenderer(String.class, jtc);
        if (jtmTeamPlayers.NEW_VIEW == view) {
            jtbSettings.setDefaultEditor(String.class, jtc);
        }
        jtbSettings.setRowHeight(20);

        column = null;
        for (int i = 0; i < jtbSettings.getModel().getColumnCount(); i++) {
            column = jtbSettings.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(180);
                    break;
                case 1:
                    column.setPreferredWidth(220);
                    break;
            }
        }

    }

    
    public mPlayer getSelectedPlayer()
    {
        return _team.getPlayer(jtbPlayers.getSelectedRow()+1);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspPlayers = new javax.swing.JScrollPane();
        jtbPlayers = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jtbSettings = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jtbBonuses = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jspPlayers.setBorder(javax.swing.BorderFactory.createTitledBorder("Players"));
        jspPlayers.setMaximumSize(new java.awt.Dimension(800, 600));
        jspPlayers.setMinimumSize(new java.awt.Dimension(800, 600));
        jspPlayers.setPreferredSize(new java.awt.Dimension(800, 600));

        jtbPlayers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jspPlayers.setViewportView(jtbPlayers);

        add(jspPlayers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 780, 400));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("General Settings"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jtbSettings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jPanel1.add(jtbSettings, java.awt.BorderLayout.CENTER);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 150));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Bonuses"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jtbBonuses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jPanel2.add(jtbBonuses, java.awt.BorderLayout.CENTER);

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 430, 150));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jspPlayers;
    private javax.swing.JTable jtbBonuses;
    private javax.swing.JTable jtbPlayers;
    private javax.swing.JTable jtbSettings;
    // End of variables declaration//GEN-END:variables
    public void paint(Graphics g) {
        super.paint(g);
    }
}
