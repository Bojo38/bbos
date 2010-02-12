/*
 * jfrNewTeam1.java
 *
 * Created on 3 août 2008, 17:59
 */
package bbos.General.Views.Match;

import bbos.General.Model.mMatch;
import bbos.General.Model.mTeam;
import bbos.General.Model.mTeamRoster;
import bbos.General.Views.Team.jdgTeam;
import bbos.General.Views.Team.jtmTeamPlayers;
import bbos.MainForm;
import bbos.mBBoS;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author  frederic
 */
public class jdgChallenge extends javax.swing.JDialog {

    mTeam _team;
    Vector _availableTeams;

    /** Creates new form jfrNewTeam1 */
    public jdgChallenge(mTeam team) {
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
        _availableTeams = mBBoS.getSingleton().getTeamsForChallenge(_team.getLeagueId(), _team.getId());

        Vector team_types = mBBoS.getSingleton().getTeamTypes();

        Vector races = new Vector();
        races.add("");
        for (int i = 0; i < team_types.size(); i++) {
            races.add(((mTeamRoster) team_types.get(i)).getName());
        }
        jcbTeamTypes.setModel(new DefaultComboBoxModel(races));

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
        jbtOK = new javax.swing.JButton();
        jbtCancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlsTeams = new javax.swing.JList();
        jPanel5 = new javax.swing.JPanel();
        jbtWatchOpponent = new javax.swing.JButton();
        jbtRefresh = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jlbLeagueName = new javax.swing.JLabel();
        jlbTeamType = new javax.swing.JLabel();
        jcbTeamTypes = new javax.swing.JComboBox();
        jlbMinRanking = new javax.swing.JLabel();
        jspMinRanking = new javax.swing.JSpinner();
        jlbMaxRanking = new javax.swing.JLabel();
        jspMaxRanking = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Match report");
        setMinimumSize(new java.awt.Dimension(400, 300));
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Team to challenge"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jlsTeams.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlsTeams);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jbtWatchOpponent.setText("See selected team");
        jbtWatchOpponent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtWatchOpponentActionPerformed(evt);
            }
        });
        jPanel5.add(jbtWatchOpponent);

        jbtRefresh.setText("Refresh team list");
        jbtRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRefreshActionPerformed(evt);
            }
        });
        jPanel5.add(jbtRefresh);

        jPanel3.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.GridLayout(4, 0));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("League name :");
        jLabel4.setEnabled(false);
        jPanel4.add(jLabel4);

        jlbLeagueName.setText("Eternal");
        jlbLeagueName.setEnabled(false);
        jPanel4.add(jlbLeagueName);

        jlbTeamType.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbTeamType.setText("Team race :");
        jPanel4.add(jlbTeamType);

        jcbTeamTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jcbTeamTypes);

        jlbMinRanking.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbMinRanking.setText("Minimum ranking :");
        jPanel4.add(jlbMinRanking);

        jspMinRanking.setModel(new javax.swing.SpinnerNumberModel(100, 50, 500, 1));
        jPanel4.add(jspMinRanking);

        jlbMaxRanking.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbMaxRanking.setText("Maximum ranking :");
        jPanel4.add(jlbMaxRanking);

        jspMaxRanking.setModel(new javax.swing.SpinnerNumberModel(250, 51, 501, 1));
        jPanel4.add(jspMaxRanking);

        jPanel2.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbtOKActionPerformed
    {//GEN-HEADEREND:event_jbtOKActionPerformed
        if (jlsTeams.getSelectedIndex() >= 0) {
            Vector teamList = new Vector();
            for (int i = 0; i < _availableTeams.size(); i++) {

                mTeam team = (mTeam) _availableTeams.get(i);

                if ((team.getRanking() >= (Integer) jspMinRanking.getValue()) && (team.getRanking() <= (Integer) jspMaxRanking.getValue())) {
                    if (jcbTeamTypes.getSelectedItem().equals("")) {
                        teamList.add(team);
                    } else {
                        if (jcbTeamTypes.getSelectedItem().equals(team.getTeamType().getName())) {
                            teamList.add(team);
                        }
                    }
                }
            }
            mTeam team = (mTeam) teamList.get(jlsTeams.getSelectedIndex());

            int answer=JOptionPane.showConfirmDialog(this,"You will challenge "+team.getName()+" of "+team.getCoach()+" with your team : "+ _team.getName()+".","New challenge ?",JOptionPane.YES_NO_OPTION);
            if (answer==JOptionPane.YES_OPTION)
            {
                int matchId=mBBoS.getSingleton().newChallenge(_team.getId(),team.getId(),_team.getLeagueId());                
                _team.getMatches().add(0,new mMatch(_team.getId(),team.getId(),matchId));
                setVisible(false);
                MainForm.getSingleton().redraw();
            }
        }
    }//GEN-LAST:event_jbtOKActionPerformed

    private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelActionPerformed
        setVisible(false);
}//GEN-LAST:event_jbtCancelActionPerformed

    private void jbtRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRefreshActionPerformed
        repaint();
    }//GEN-LAST:event_jbtRefreshActionPerformed

    private void jbtWatchOpponentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtWatchOpponentActionPerformed

        Vector teamList = new Vector();
        for (int i = 0; i < _availableTeams.size(); i++) {

            mTeam team = (mTeam) _availableTeams.get(i);

            if ((team.getRanking() >= (Integer) jspMinRanking.getValue()) && (team.getRanking() <= (Integer) jspMaxRanking.getValue())) {
                if (jcbTeamTypes.getSelectedItem().equals("")) {
                    teamList.add(team);
                } else {
                    if (jcbTeamTypes.getSelectedItem().equals(team.getTeamType().getName())) {
                        teamList.add(team);
                    }
                }
            }
        }

        if (jlsTeams.getSelectedIndex() >= 0) {
            mTeam team = (mTeam) teamList.get(jlsTeams.getSelectedIndex());
            jdgTeam window = new jdgTeam(team, jtmTeamPlayers.FULL_VIEW);
            window.setVisible(true);
        }
}//GEN-LAST:event_jbtWatchOpponentActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtOK;
    private javax.swing.JButton jbtRefresh;
    private javax.swing.JButton jbtWatchOpponent;
    private javax.swing.JComboBox jcbTeamTypes;
    private javax.swing.JLabel jlbLeagueName;
    private javax.swing.JLabel jlbMaxRanking;
    private javax.swing.JLabel jlbMinRanking;
    private javax.swing.JLabel jlbTeamType;
    private javax.swing.JList jlsTeams;
    private javax.swing.JSpinner jspMaxRanking;
    private javax.swing.JSpinner jspMinRanking;
    // End of variables declaration//GEN-END:variables
    public void paint(java.awt.Graphics g) {
        super.paint(g);

        jlsTeams.removeAll();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < _availableTeams.size(); i++) {

            mTeam team = (mTeam) _availableTeams.get(i);

            if ((team.getRanking() >= (Integer) jspMinRanking.getValue()) && (team.getRanking() <= (Integer) jspMaxRanking.getValue())) {
                if (jcbTeamTypes.getSelectedItem().equals("")) {
                    model.addElement(team.getName() + " (#" + Integer.toString(team.getRanking()) + ") " + team.getCoach() + " - " + team.getTeamType().getName());
                } else {
                    if (jcbTeamTypes.getSelectedItem().equals(team.getTeamType().getName())) {
                        model.addElement(team.getName() + " (#" + Integer.toString(team.getRanking()) + ") " + team.getCoach());
                    }
                }
            }
        }
        jlsTeams.setModel(model);
    }
}