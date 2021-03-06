/*
 * jfrNewTeam1.java
 *
 * Created on 3 août 2008, 17:59
 */
package bbos.General.Views.Match;



import bbos.General.Model.mMatch;
import bbos.General.Model.mTeam;
import bbos.General.Views.Match.jtmActions;
import bbos.General.Views.Team.jpnTeam;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.table.TableColumn;

/**
 *
 * @author  frederic
 */
public class jdgMatchDone extends javax.swing.JDialog
{

    jpnTeam jpnteam;
    boolean _addTeam = false;
    mTeam _team;
    mMatch _match;

    /** Creates new form jfrNewTeam1 */
    public jdgMatchDone(mMatch match, mTeam team)
    {
        initComponents();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        if (dmode != null)
        {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }
        _match=match;
        _team=team;
        jtbActions.setModel(new jtmActions(match,_team.getId()));
        
        TableColumn column;
        for (int i = 0; i < jtbActions.getModel().getColumnCount(); i++) {
            column = jtbActions.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(350);
                    break;
                case 1:
                    column.setPreferredWidth(40);
                    break;
                case 2:
                    column.setPreferredWidth(350);
                    break;
                }
        }
        
        jlbTeam1.setText(_team.getName());
        jlbTeam2.setText(_match.getOppponent().getName());
        jlbScore1.setText(Integer.toString(_match.getMyScore()));
        jlbScore2.setText(Integer.toString(_match.getOppScore()));
        
        jlbFame1.setText(Integer.toString(_match.getMyFAME()));
        jlbFAME2.setText(Integer.toString(_match.getOppFAME()));
        jlbWinnings1.setText(Integer.toString(_match.getMyWinnings()));
        jlbWinnings2.setText(Integer.toString(_match.getOppWinnings()));
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbActions = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jlbEmpty = new javax.swing.JLabel();
        jlbFame1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlbFAME2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jlbWinnings1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jlbWinnings2 = new javax.swing.JLabel();
        jlbEmpty2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jlbTeam1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jlbScore1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlbScore2 = new javax.swing.JLabel();
        jlbTeam2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Match report");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setModal(true);

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel1.add(jbtOK);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Match events"));

        jtbActions.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbActions);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.GridLayout(2, 0));

        jlbEmpty.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel5.add(jlbEmpty);

        jlbFame1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbFame1.setText("0");
        jPanel5.add(jlbFame1);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("FAME");
        jPanel5.add(jLabel3);

        jlbFAME2.setText("0");
        jPanel5.add(jlbFAME2);
        jPanel5.add(jLabel6);
        jPanel5.add(jLabel7);

        jlbWinnings1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbWinnings1.setText("0");
        jPanel5.add(jlbWinnings1);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Winnings");
        jPanel5.add(jLabel9);

        jlbWinnings2.setText("0");
        jPanel5.add(jlbWinnings2);

        jlbEmpty2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel5.add(jlbEmpty2);

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.GridLayout());

        jlbTeam1.setFont(new java.awt.Font("DejaVu Sans", 0, 24));
        jlbTeam1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbTeam1.setText("Team 1");
        jPanel4.add(jlbTeam1);

        jPanel3.setLayout(new java.awt.GridLayout());

        jlbScore1.setFont(new java.awt.Font("DejaVu Sans", 0, 36));
        jlbScore1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbScore1.setText("0");
        jPanel3.add(jlbScore1);

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 36));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("-");
        jPanel3.add(jLabel4);

        jlbScore2.setFont(new java.awt.Font("DejaVu Sans", 0, 36));
        jlbScore2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbScore2.setText("0");
        jPanel3.add(jlbScore2);

        jPanel4.add(jPanel3);

        jlbTeam2.setFont(new java.awt.Font("DejaVu Sans", 0, 24));
        jlbTeam2.setText("Team 2");
        jPanel4.add(jlbTeam2);

        jPanel2.add(jPanel4, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbtOKActionPerformed
    {//GEN-HEADEREND:event_jbtOKActionPerformed
       
            setVisible(false);
    }//GEN-LAST:event_jbtOKActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtOK;
    private javax.swing.JLabel jlbEmpty;
    private javax.swing.JLabel jlbEmpty2;
    private javax.swing.JLabel jlbFAME2;
    private javax.swing.JLabel jlbFame1;
    private javax.swing.JLabel jlbScore1;
    private javax.swing.JLabel jlbScore2;
    private javax.swing.JLabel jlbTeam1;
    private javax.swing.JLabel jlbTeam2;
    private javax.swing.JLabel jlbWinnings1;
    private javax.swing.JLabel jlbWinnings2;
    private javax.swing.JTable jtbActions;
    // End of variables declaration//GEN-END:variables
}
