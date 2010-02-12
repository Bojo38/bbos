/*
 * jifTeamRoster.java
 *
 * Created on 16 mai 2008, 14:31
 */

package bbos.General.Views.Rosters;
import javax.swing.*;
import bbos.General.Model.*;
import java.awt.Color;
import java.net.*; 
import java.io.*;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.TableColumn;
import bbos.Tools.sdLang;

/**
 *
 * @author  frederic
 */
public class jpnTeamRoster extends JPanel implements ActionListener
{
    mTeamRoster _team;
    /** Creates new form jifTeamRoster */
    public jpnTeamRoster(mTeamRoster team)
    {
        super();
        initComponents();
        _team=team;
        
        jtbRoster.setModel(new jtmPlayerTypes(team));
        jtbStarPlayers.setModel(new jtmPlayerStars(team));
        
        jtbRoster.setDefaultRenderer(String.class, new jtcRenderer());
        jtbRoster.setDefaultRenderer(Integer.class, new jtcRenderer());
        
        jtbStarPlayers.setDefaultRenderer(String.class, new jtcRenderer());
        jtbStarPlayers.setDefaultRenderer(Integer.class, new jtcRenderer());
        
        TableColumn column = null;
        for (int i = 0; i < jtbRoster.getModel().getColumnCount(); i++)
        {
            column = jtbRoster.getColumnModel().getColumn(i);
            switch (i)
            {
                case 0:
                    column.setPreferredWidth(30);
                    break;
                case 1:
                     column.setPreferredWidth(100);
                    break;
                case 2:
                     column.setPreferredWidth(15);
                    break;
                case 3:
                     column.setPreferredWidth(15);
                    break;
                case 4:
                    column.setPreferredWidth(15);
                    break;
                case 5:
                    column.setPreferredWidth(15);
                    break;
                case 6:
                    column.setPreferredWidth(350);
                    break;
                case 7:
                    column.setPreferredWidth(40);
                    break;
                case 8:
                    column.setPreferredWidth(40);
                    break;
                case 9:
                    column.setPreferredWidth(40);
                    break;
            }
        }
        
            column = null;
        for (int i = 0; i < jtbStarPlayers.getModel().getColumnCount(); i++)
        {
            column = jtbStarPlayers.getColumnModel().getColumn(i);
            switch (i)
            {
                case 0:
                    column.setPreferredWidth(100);
                    break;
                case 1:
                     column.setPreferredWidth(15);
                    break;
                case 2:
                     column.setPreferredWidth(15);
                    break;
                case 3:
                     column.setPreferredWidth(15);
                    break;
                case 4:
                    column.setPreferredWidth(15);
                    break;
                case 5:
                    column.setPreferredWidth(450);
                    break;
                case 6:
                    column.setPreferredWidth(40);
                    break;
            }
        }
        
             
           try {
        // Create a URL for the image's location
        URL url = new URL(_team.getLogoURL());
    
        // Get the image
        java.awt.Image image = java.awt.Toolkit.getDefaultToolkit().getDefaultToolkit().createImage(url);
        ImageIcon icon=new ImageIcon(image.getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        jlbIcon.setIcon(icon);
    } catch (MalformedURLException e) {
    } catch (IOException e) {
    }
        this.setForeground(Color.black);
        jcbApothecary.repaint();
        jcbCanRaise.repaint();
        jtfBribe.setText(Integer.toString(_team.getBribeCost()));
        jtfChef.setText(Integer.toString(_team.getChef()));
        jtfReroll.setText(Integer.toString(_team.getRerollCost()));
        jtfWizard.setText(Integer.toString(_team.getWizardCost()));
        jtxDescription.setText(_team.getDescription());
        
        jcbApothecary.setSelected(_team.isApothecary());
        jcbCanRaise.setSelected(_team.isCanRaise());
        
        jcbApothecary.addActionListener(this);
        jcbCanRaise.addActionListener(this);
        
        
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
        jlbIcon = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtxDescription = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jspAllowedPlayers = new javax.swing.JScrollPane();
        jtbRoster = new javax.swing.JTable();
        jspStarsPlayers = new javax.swing.JScrollPane();
        jtbStarPlayers = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jlbReroll = new javax.swing.JLabel();
        jtfReroll = new javax.swing.JTextField();
        jlbChef = new javax.swing.JLabel();
        jtfChef = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jcbApothecary = new javax.swing.JCheckBox();
        jlbWizard = new javax.swing.JLabel();
        jtfWizard = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jcbCanRaise = new javax.swing.JCheckBox();
        jlbBribe = new javax.swing.JLabel();
        jtfBribe = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jlbIcon.setText("ICON");
        jlbIcon.setMaximumSize(new java.awt.Dimension(128, 128));
        jlbIcon.setMinimumSize(new java.awt.Dimension(128, 128));
        jlbIcon.setPreferredSize(new java.awt.Dimension(64, 64));
        jPanel1.add(jlbIcon, java.awt.BorderLayout.WEST);

        jtxDescription.setEditable(false);
        jtxDescription.setText("Bla Bla Bla");
        jScrollPane3.setViewportView(jtxDescription);

        jPanel1.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.GridLayout(2, 0));

        jspAllowedPlayers.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Allowed players"));

        jtbRoster.setBorder(null);
        jtbRoster.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Limit", "Name", "M", "S", "Ag", "Av", "Competences", "S", "D", "Cost"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jtbRoster.setColumnSelectionAllowed(true);
        jspAllowedPlayers.setViewportView(jtbRoster);
        jtbRoster.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jtbRoster.getColumnModel().getColumn(0).setMinWidth(30);
        jtbRoster.getColumnModel().getColumn(0).setPreferredWidth(40);
        jtbRoster.getColumnModel().getColumn(1).setMinWidth(120);
        jtbRoster.getColumnModel().getColumn(1).setPreferredWidth(200);
        jtbRoster.getColumnModel().getColumn(1).setMaxWidth(200);
        jtbRoster.getColumnModel().getColumn(2).setMinWidth(20);
        jtbRoster.getColumnModel().getColumn(2).setPreferredWidth(40);
        jtbRoster.getColumnModel().getColumn(2).setMaxWidth(40);
        jtbRoster.getColumnModel().getColumn(3).setMinWidth(20);
        jtbRoster.getColumnModel().getColumn(3).setPreferredWidth(40);
        jtbRoster.getColumnModel().getColumn(3).setMaxWidth(40);
        jtbRoster.getColumnModel().getColumn(4).setMinWidth(20);
        jtbRoster.getColumnModel().getColumn(4).setPreferredWidth(40);
        jtbRoster.getColumnModel().getColumn(4).setMaxWidth(40);
        jtbRoster.getColumnModel().getColumn(5).setMinWidth(20);
        jtbRoster.getColumnModel().getColumn(5).setPreferredWidth(40);
        jtbRoster.getColumnModel().getColumn(5).setMaxWidth(40);
        jtbRoster.getColumnModel().getColumn(6).setPreferredWidth(200);
        jtbRoster.getColumnModel().getColumn(7).setMinWidth(30);
        jtbRoster.getColumnModel().getColumn(7).setPreferredWidth(30);
        jtbRoster.getColumnModel().getColumn(8).setMinWidth(30);
        jtbRoster.getColumnModel().getColumn(8).setPreferredWidth(30);
        jtbRoster.getColumnModel().getColumn(8).setMaxWidth(30);
        jtbRoster.getColumnModel().getColumn(9).setMinWidth(50);
        jtbRoster.getColumnModel().getColumn(9).setPreferredWidth(50);
        jtbRoster.getColumnModel().getColumn(9).setMaxWidth(50);

        jPanel2.add(jspAllowedPlayers);

        jspStarsPlayers.setBorder(javax.swing.BorderFactory.createTitledBorder("Allowed Star players"));

        jtbStarPlayers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Name", "M", "S", "Ag", "Av", "Competences", "S", "D", "Cost"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jspStarsPlayers.setViewportView(jtbStarPlayers);
        jtbStarPlayers.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jtbStarPlayers.getColumnModel().getColumn(0).setMinWidth(120);
        jtbStarPlayers.getColumnModel().getColumn(0).setPreferredWidth(200);
        jtbStarPlayers.getColumnModel().getColumn(0).setMaxWidth(200);
        jtbStarPlayers.getColumnModel().getColumn(1).setMinWidth(20);
        jtbStarPlayers.getColumnModel().getColumn(1).setPreferredWidth(40);
        jtbStarPlayers.getColumnModel().getColumn(1).setMaxWidth(40);
        jtbStarPlayers.getColumnModel().getColumn(2).setMinWidth(20);
        jtbStarPlayers.getColumnModel().getColumn(2).setPreferredWidth(40);
        jtbStarPlayers.getColumnModel().getColumn(2).setMaxWidth(40);
        jtbStarPlayers.getColumnModel().getColumn(3).setMinWidth(20);
        jtbStarPlayers.getColumnModel().getColumn(3).setPreferredWidth(40);
        jtbStarPlayers.getColumnModel().getColumn(3).setMaxWidth(40);
        jtbStarPlayers.getColumnModel().getColumn(4).setMinWidth(20);
        jtbStarPlayers.getColumnModel().getColumn(4).setPreferredWidth(40);
        jtbStarPlayers.getColumnModel().getColumn(4).setMaxWidth(40);
        jtbStarPlayers.getColumnModel().getColumn(5).setPreferredWidth(200);
        jtbStarPlayers.getColumnModel().getColumn(6).setMinWidth(30);
        jtbStarPlayers.getColumnModel().getColumn(6).setPreferredWidth(30);
        jtbStarPlayers.getColumnModel().getColumn(7).setMinWidth(30);
        jtbStarPlayers.getColumnModel().getColumn(7).setPreferredWidth(30);
        jtbStarPlayers.getColumnModel().getColumn(7).setMaxWidth(30);

        jPanel2.add(jspStarsPlayers);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setPreferredSize(new java.awt.Dimension(600, 60));
        jPanel3.setLayout(new java.awt.GridLayout(3, 4));

        jlbReroll.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbReroll.setText("Reroll cost :");
        jPanel3.add(jlbReroll);

        jtfReroll.setEditable(false);
        jtfReroll.setText("0");
        jPanel3.add(jtfReroll);

        jlbChef.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbChef.setText("Chef cost :");
        jPanel3.add(jlbChef);

        jtfChef.setEditable(false);
        jtfChef.setText("0");
        jPanel3.add(jtfChef);
        jPanel3.add(jLabel3);

        jcbApothecary.setForeground(java.awt.SystemColor.activeCaptionText);
        jcbApothecary.setText("Apothecary");
        jcbApothecary.setFocusable(false);
        jPanel3.add(jcbApothecary);

        jlbWizard.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbWizard.setText("Wizard cost:");
        jPanel3.add(jlbWizard);

        jtfWizard.setEditable(false);
        jtfWizard.setText("0");
        jPanel3.add(jtfWizard);
        jPanel3.add(jLabel5);

        jcbCanRaise.setForeground(java.awt.SystemColor.activeCaptionText);
        jcbCanRaise.setText("Can Raise dead players");
        jcbCanRaise.setFocusable(false);
        jPanel3.add(jcbCanRaise);

        jlbBribe.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbBribe.setText("Bribe cost :");
        jPanel3.add(jlbBribe);

        jtfBribe.setEditable(false);
        jtfBribe.setText("0");
        jPanel3.add(jtfBribe);

        add(jPanel3, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JCheckBox jcbApothecary;
    private javax.swing.JCheckBox jcbCanRaise;
    private javax.swing.JLabel jlbBribe;
    private javax.swing.JLabel jlbChef;
    private javax.swing.JLabel jlbIcon;
    private javax.swing.JLabel jlbReroll;
    private javax.swing.JLabel jlbWizard;
    private javax.swing.JScrollPane jspAllowedPlayers;
    private javax.swing.JScrollPane jspStarsPlayers;
    private javax.swing.JTable jtbRoster;
    private javax.swing.JTable jtbStarPlayers;
    private javax.swing.JTextField jtfBribe;
    private javax.swing.JTextField jtfChef;
    private javax.swing.JTextField jtfReroll;
    private javax.swing.JTextField jtfWizard;
    private javax.swing.JTextPane jtxDescription;
    // End of variables declaration//GEN-END:variables
    
public void actionPerformed(ActionEvent e)
{
    
       jcbApothecary.setSelected(_team.isApothecary());
       jcbCanRaise.setSelected(_team.isCanRaise());
       jtxDescription.setText(sdLang.getSingleton().getResource(_team.getName()+"_description"));
       
       jlbBribe.setText(sdLang.getSingleton().getResource("Bribe")+" :");
       jlbChef.setText(sdLang.getSingleton().getResource("Chef")+" :");
       jlbReroll.setText(sdLang.getSingleton().getResource("Reroll")+" :");
       jlbWizard.setText(sdLang.getSingleton().getResource("Wizard")+" :");
       jcbApothecary.setText(sdLang.getSingleton().getResource("Apothecary"));
       jcbCanRaise.setText(sdLang.getSingleton().getResource("Can raise"));
       
       jspStarsPlayers.setBorder(javax.swing.BorderFactory.createTitledBorder(sdLang.getSingleton().getResource("Star players")));
       jspAllowedPlayers.setBorder(javax.swing.BorderFactory.createTitledBorder(sdLang.getSingleton().getResource("Allowed players")));
       
    repaint();
} 
    
}