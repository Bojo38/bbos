/*
 * jfCommunicationSettings.java
 *
 * Created on 11 mai 2008, 16:43
 */
package bbos.Match.Automat.BeforeMatch;

import bbos.General.Views.*;
import bbos.*;
import bbos.General.Model.mPlayerType;
import bbos.General.Model.mTeamRoster;
import bbos.General.Views.Rosters.jtcRenderer;
import bbos.Match.Model.Inducements.Cards.diCardFactory;
import bbos.Match.Model.Inducements.Cards.diCard;
import bbos.Match.Model.Inducements.dInducement;
import bbos.Match.Model.Inducements.diBabe;
import bbos.Match.Model.Inducements.diBribeTheRef;
import bbos.Match.Model.Inducements.diChef;
import bbos.Match.Model.Inducements.diExtraReroll;
import bbos.Match.Model.Inducements.diIgor;
import bbos.Match.Model.Inducements.diLocalApothecary;
import bbos.Match.Model.Inducements.diMercenary;
import bbos.Match.Model.Inducements.diStarPlayer;
import bbos.Match.Model.Inducements.diWizard;
import bbos.Match.Model.dMeteo;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiTeam;
import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.table.TableColumn;

/**
 *
 * @author  frederic
 */
public class jdgInducements extends JDialog {

    long _initialPettyCash;
    long _spentPettyCash;
    rmiTeam _model;
    rmiTeam __opponent;
    Vector _mercenaries;
    Vector _starPlayers;
    boolean _isChallenger;

    /** Creates new form jfCommunicationSettings */
    public jdgInducements(rmiTeam model, rmiTeam opponent, int meteo, boolean isChallenger) {
        initComponents();
        _isChallenger = isChallenger;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }

        _mercenaries = new Vector();
        _starPlayers = new Vector();

        _model = model;

        try {
            _initialPettyCash = _model.getPettyCash();

            jlbChefPrice.setText("(" + Integer.toString(mBBoS.getSingleton().getTeamType(_model.getRaceId()).getChef()) + " gc)");
            jlbBribePrice.setText("(" + Integer.toString(mBBoS.getSingleton().getTeamType(_model.getRaceId()).getBribeCost()) + "gc)");

            Vector opponentInducements = opponent.getInducements();

            switch (meteo) {
                case dMeteo.METEO_NICE:
                    jtaOpponentChoice.setText("The weather is nice !");
                    break;
                case dMeteo.METEO_BLIZZARD:
                    jtaOpponentChoice.setText("The weather is blizzard !");
                    break;
                case dMeteo.METEO_HEAT:
                    jtaOpponentChoice.setText("The weather is heat !");
                    break;
                case dMeteo.METEO_RAIN:
                    jtaOpponentChoice.setText("The weather is pourring rain !");
                    break;
                case dMeteo.METEO_SUNNY:
                    jtaOpponentChoice.setText("The weather is very sunny !");
                    break;
            }

            if (opponentInducements.size() == 0) {
                jtaOpponentChoice.setText(jtaOpponentChoice.getText() + "\nThe opponent inducements : \n\tNone");
            } else {
                String text = jtaOpponentChoice.getText() + "\nThe opponent inducements : ";
                for (int i = 0; i < opponentInducements.size(); i++) {
                    dInducement induc = (dInducement) opponentInducements.get(i);
                    int type = induc.getType();
                    if (type == dInducement.C_CARD) {
                        diCard card = (diCard) induc;
                        text += "\n\t- " + card.getPublicName();
                    } else {
                        if (type == dInducement.C_STAR_PLAYER) {
                            diStarPlayer star = (diStarPlayer) induc;
                            text += "\n\t- " + star.getPublicName();
                        } else {
                            if (type == dInducement.C_MERCENARY) {
                                diMercenary di = (diMercenary) induc;
                                text += "\n\t- " + di.getPublicName();
                            } else {
                                text += "\n\t- " + induc.getPublicName();
                            }
                        }
                    }

                }
                jtaOpponentChoice.setText(text);
            }
            this.setTitle("Select Inducements: "+model.getName());
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }

        

        repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtpInducements = new javax.swing.JTabbedPane();
        jpnStarPlayers = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbStarPlayers = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jbtAddStar = new javax.swing.JButton();
        jbtRemoveStar = new javax.swing.JButton();
        jpnMercenaries = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbMercenaries = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jbtAdd = new javax.swing.JButton();
        jbtRemove = new javax.swing.JButton();
        jpnSpecialCards = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jlbMiscMayhem = new javax.swing.JLabel();
        jlbCardIcon1 = new javax.swing.JLabel();
        jspMiscMayhem = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        jlbSpecialAction = new javax.swing.JLabel();
        jlbCardIcon2 = new javax.swing.JLabel();
        jspSpecialAction = new javax.swing.JSpinner();
        jPanel5 = new javax.swing.JPanel();
        jlbMagicItem = new javax.swing.JLabel();
        jlbCardIcon3 = new javax.swing.JLabel();
        jspMagicItem = new javax.swing.JSpinner();
        jPanel7 = new javax.swing.JPanel();
        jlbDirtyTrick = new javax.swing.JLabel();
        jlbCardIcon5 = new javax.swing.JLabel();
        jspDirtyTrick = new javax.swing.JSpinner();
        jPanel6 = new javax.swing.JPanel();
        jlbGoodKarma = new javax.swing.JLabel();
        jlbCardIcon4 = new javax.swing.JLabel();
        jspGoodKarma = new javax.swing.JSpinner();
        jPanel8 = new javax.swing.JPanel();
        jlbRandomEvent = new javax.swing.JLabel();
        jlbCardIcon6 = new javax.swing.JLabel();
        jspRandomEvent = new javax.swing.JSpinner();
        jPanel9 = new javax.swing.JPanel();
        jlbDesperateMeasure = new javax.swing.JLabel();
        jlbCardIcon7 = new javax.swing.JLabel();
        jspDesperateMeasure = new javax.swing.JSpinner();
        jpnOthers = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jlbBabes = new javax.swing.JLabel();
        jspBabes = new javax.swing.JSpinner();
        jlbBabesPrice = new javax.swing.JLabel();
        jcbWizard = new javax.swing.JCheckBox();
        jlbWizardPrice = new javax.swing.JLabel();
        jlbBribe = new javax.swing.JLabel();
        jspBribe = new javax.swing.JSpinner();
        jlbBribePrice = new javax.swing.JLabel();
        jcbHalfling = new javax.swing.JCheckBox();
        jlbChefPrice = new javax.swing.JLabel();
        jlbReroll = new javax.swing.JLabel();
        jspReroll = new javax.swing.JSpinner();
        jlbRerollPrice = new javax.swing.JLabel();
        jcbIgor = new javax.swing.JCheckBox();
        jlbIgorPrice = new javax.swing.JLabel();
        jlbApo = new javax.swing.JLabel();
        jspApo = new javax.swing.JSpinner();
        jlbApoPrice = new javax.swing.JLabel();
        jpnBottom = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtaOpponentChoice = new javax.swing.JTextArea();
        jpnPettyCash = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfPettyCashInitial = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfRemainginPettyCash = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Inducements");
        setMinimumSize(new java.awt.Dimension(200, 100));
        setModal(true);
        setResizable(false);

        jtpInducements.setMaximumSize(new java.awt.Dimension(550, 200));
        jtpInducements.setMinimumSize(new java.awt.Dimension(550, 200));
        jtpInducements.setPreferredSize(new java.awt.Dimension(550, 200));
        jtpInducements.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtpInducementsMouseClicked(evt);
            }
        });

        jpnStarPlayers.setPreferredSize(new java.awt.Dimension(540, 404));
        jpnStarPlayers.setLayout(new java.awt.BorderLayout());

        jtbStarPlayers.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbStarPlayers);

        jpnStarPlayers.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtAddStar.setText("Add");
        jbtAddStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddStarActionPerformed(evt);
            }
        });
        jPanel2.add(jbtAddStar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 80, -1));

        jbtRemoveStar.setText("Remove");
        jbtRemoveStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveStarActionPerformed(evt);
            }
        });
        jPanel2.add(jbtRemoveStar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 35, 80, -1));

        jpnStarPlayers.add(jPanel2, java.awt.BorderLayout.WEST);

        jtpInducements.addTab("Star players", jpnStarPlayers);

        jpnMercenaries.setPreferredSize(new java.awt.Dimension(540, 404));
        jpnMercenaries.setLayout(new java.awt.BorderLayout());

        jtbMercenaries.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jtbMercenaries);

        jpnMercenaries.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtAdd.setText("Add");
        jbtAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddActionPerformed(evt);
            }
        });
        jPanel1.add(jbtAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 80, -1));

        jbtRemove.setText("Remove");
        jbtRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveActionPerformed(evt);
            }
        });
        jPanel1.add(jbtRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 35, 80, -1));

        jpnMercenaries.add(jPanel1, java.awt.BorderLayout.WEST);

        jtpInducements.addTab("Mercenaries", jpnMercenaries);

        jpnSpecialCards.setBorder(javax.swing.BorderFactory.createTitledBorder("Special Cards"));
        jpnSpecialCards.setPreferredSize(new java.awt.Dimension(540, 132));
        jpnSpecialCards.setLayout(new java.awt.GridLayout(1, 4));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jlbMiscMayhem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMiscMayhem.setText("<html><center>Misc. Mayhem<br>\n50 000 gc");
        jPanel3.add(jlbMiscMayhem, java.awt.BorderLayout.PAGE_START);

        jlbCardIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbCardIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/cards/miscmayheim.png"))); // NOI18N
        jlbCardIcon1.setToolTipText("50 000");
        jlbCardIcon1.setMaximumSize(new java.awt.Dimension(50, 60));
        jlbCardIcon1.setMinimumSize(new java.awt.Dimension(40, 60));
        jlbCardIcon1.setPreferredSize(new java.awt.Dimension(40, 60));
        jPanel3.add(jlbCardIcon1, java.awt.BorderLayout.CENTER);

        jspMiscMayhem.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1));
        jspMiscMayhem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspMiscMayhemMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jspMiscMayhemMouseExited(evt);
            }
        });
        jspMiscMayhem.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspMiscMayhemStateChanged(evt);
            }
        });
        jPanel3.add(jspMiscMayhem, java.awt.BorderLayout.SOUTH);

        jpnSpecialCards.add(jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jlbSpecialAction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbSpecialAction.setText("<html><center>Special Play<br>50 000 gc");
        jPanel4.add(jlbSpecialAction, java.awt.BorderLayout.NORTH);

        jlbCardIcon2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbCardIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/cards/specialplay.png"))); // NOI18N
        jlbCardIcon2.setToolTipText("50 000");
        jlbCardIcon2.setMaximumSize(new java.awt.Dimension(50, 60));
        jlbCardIcon2.setMinimumSize(new java.awt.Dimension(40, 60));
        jlbCardIcon2.setPreferredSize(new java.awt.Dimension(40, 60));
        jPanel4.add(jlbCardIcon2, java.awt.BorderLayout.CENTER);

        jspSpecialAction.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1));
        jspSpecialAction.setEditor(new javax.swing.JSpinner.NumberEditor(jspSpecialAction, ""));
        jspSpecialAction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspSpecialActionMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jspSpecialActionMouseExited(evt);
            }
        });
        jPanel4.add(jspSpecialAction, java.awt.BorderLayout.SOUTH);

        jpnSpecialCards.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jlbMagicItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMagicItem.setText("<html><center>Magic Item<br>50 000 gc");
        jPanel5.add(jlbMagicItem, java.awt.BorderLayout.NORTH);

        jlbCardIcon3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbCardIcon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/cards/magicitem.png"))); // NOI18N
        jlbCardIcon3.setToolTipText("50 000");
        jlbCardIcon3.setMaximumSize(new java.awt.Dimension(50, 60));
        jlbCardIcon3.setMinimumSize(new java.awt.Dimension(40, 60));
        jlbCardIcon3.setPreferredSize(new java.awt.Dimension(40, 60));
        jPanel5.add(jlbCardIcon3, java.awt.BorderLayout.CENTER);

        jspMagicItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspMagicItemMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jspMagicItemMouseExited(evt);
            }
        });
        jPanel5.add(jspMagicItem, java.awt.BorderLayout.SOUTH);

        jpnSpecialCards.add(jPanel5);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jlbDirtyTrick.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDirtyTrick.setText("<html><center>DirtyTrick<br>50 000 gc");
        jPanel7.add(jlbDirtyTrick, java.awt.BorderLayout.NORTH);

        jlbCardIcon5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbCardIcon5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/cards/dirtytrick.png"))); // NOI18N
        jlbCardIcon5.setToolTipText("50 000");
        jlbCardIcon5.setMaximumSize(new java.awt.Dimension(50, 60));
        jlbCardIcon5.setMinimumSize(new java.awt.Dimension(40, 60));
        jlbCardIcon5.setPreferredSize(new java.awt.Dimension(40, 60));
        jPanel7.add(jlbCardIcon5, java.awt.BorderLayout.CENTER);

        jspDirtyTrick.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspDirtyTrickMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jspDirtyTrickMouseExited(evt);
            }
        });
        jPanel7.add(jspDirtyTrick, java.awt.BorderLayout.SOUTH);

        jpnSpecialCards.add(jPanel7);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jlbGoodKarma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbGoodKarma.setText("<html><center>Good Karma<br>100 000 gc");
        jPanel6.add(jlbGoodKarma, java.awt.BorderLayout.NORTH);

        jlbCardIcon4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbCardIcon4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/cards/goodkarma.png"))); // NOI18N
        jlbCardIcon4.setToolTipText("100 000");
        jlbCardIcon4.setMaximumSize(new java.awt.Dimension(50, 60));
        jlbCardIcon4.setMinimumSize(new java.awt.Dimension(40, 60));
        jlbCardIcon4.setPreferredSize(new java.awt.Dimension(40, 60));
        jPanel6.add(jlbCardIcon4, java.awt.BorderLayout.CENTER);

        jspGoodKarma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspGoodKarmaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jspGoodKarmaMouseExited(evt);
            }
        });
        jPanel6.add(jspGoodKarma, java.awt.BorderLayout.SOUTH);

        jpnSpecialCards.add(jPanel6);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jlbRandomEvent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbRandomEvent.setText("<html><center>Random Event<br>200 000 gc");
        jPanel8.add(jlbRandomEvent, java.awt.BorderLayout.NORTH);

        jlbCardIcon6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbCardIcon6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/cards/randomevent.png"))); // NOI18N
        jlbCardIcon6.setToolTipText("200 000");
        jlbCardIcon6.setMaximumSize(new java.awt.Dimension(50, 60));
        jlbCardIcon6.setMinimumSize(new java.awt.Dimension(40, 60));
        jlbCardIcon6.setPreferredSize(new java.awt.Dimension(40, 60));
        jPanel8.add(jlbCardIcon6, java.awt.BorderLayout.CENTER);

        jspRandomEvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspRandomEventMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jspRandomEventMouseExited(evt);
            }
        });
        jPanel8.add(jspRandomEvent, java.awt.BorderLayout.SOUTH);

        jpnSpecialCards.add(jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jlbDesperateMeasure.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDesperateMeasure.setText("<html><center>Desp. Measure<br>400 000 gc\n");
        jPanel9.add(jlbDesperateMeasure, java.awt.BorderLayout.NORTH);

        jlbCardIcon7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbCardIcon7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/cards/desperatemeasure.png"))); // NOI18N
        jlbCardIcon7.setToolTipText("400 000");
        jlbCardIcon7.setMaximumSize(new java.awt.Dimension(50, 60));
        jlbCardIcon7.setMinimumSize(new java.awt.Dimension(50, 60));
        jlbCardIcon7.setPreferredSize(new java.awt.Dimension(50, 60));
        jPanel9.add(jlbCardIcon7, java.awt.BorderLayout.CENTER);

        jspDesperateMeasure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspDesperateMeasureMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jspDesperateMeasureMouseExited(evt);
            }
        });
        jPanel9.add(jspDesperateMeasure, java.awt.BorderLayout.SOUTH);

        jpnSpecialCards.add(jPanel9);

        jtpInducements.addTab("Special cards", jpnSpecialCards);

        jpnOthers.setBorder(javax.swing.BorderFactory.createTitledBorder("Others"));
        jpnOthers.setPreferredSize(new java.awt.Dimension(540, 124));
        jpnOthers.setLayout(new java.awt.BorderLayout());

        jPanel10.setLayout(new java.awt.GridLayout(4, 3, 2, 2));

        jlbBabes.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbBabes.setText("Bloodweiser Babes :");
        jPanel10.add(jlbBabes);

        jspBabes.setModel(new javax.swing.SpinnerNumberModel(0, 0, 2, 1));
        jspBabes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jspBabesMouseExited(evt);
            }
        });
        jspBabes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jspBabesPropertyChange(evt);
            }
        });
        jPanel10.add(jspBabes);

        jlbBabesPrice.setText("(50 000 gc) ");
        jPanel10.add(jlbBabesPrice);

        jcbWizard.setText("Wizard");
        jcbWizard.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcbWizard.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcbWizard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbWizardActionPerformed(evt);
            }
        });
        jPanel10.add(jcbWizard);

        jlbWizardPrice.setText("(150 000 gc) ");
        jPanel10.add(jlbWizardPrice);

        jlbBribe.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbBribe.setText("Bribe the ref :");
        jPanel10.add(jlbBribe);

        jspBribe.setModel(new javax.swing.SpinnerNumberModel(0, 0, 3, 1));
        jspBribe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jspBribeMouseExited(evt);
            }
        });
        jspBribe.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jspBribePropertyChange(evt);
            }
        });
        jPanel10.add(jspBribe);

        jlbBribePrice.setText("(100 000 gc) ");
        jPanel10.add(jlbBribePrice);

        jcbHalfling.setText("Halfling Chef");
        jcbHalfling.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcbHalfling.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcbHalfling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbHalflingActionPerformed(evt);
            }
        });
        jPanel10.add(jcbHalfling);

        jlbChefPrice.setText("(300 000 gc) ");
        jPanel10.add(jlbChefPrice);

        jlbReroll.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbReroll.setText("Additional Reroll :");
        jPanel10.add(jlbReroll);

        jspReroll.setModel(new javax.swing.SpinnerNumberModel(0, 0, 4, 1));
        jspReroll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jspRerollMouseExited(evt);
            }
        });
        jspReroll.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jspRerollPropertyChange(evt);
            }
        });
        jPanel10.add(jspReroll);

        jlbRerollPrice.setText("(100 000 gc) ");
        jPanel10.add(jlbRerollPrice);

        jcbIgor.setText("Igor");
        jcbIgor.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcbIgor.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcbIgor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbIgorActionPerformed(evt);
            }
        });
        jPanel10.add(jcbIgor);

        jlbIgorPrice.setText("(100 000 gc) ");
        jPanel10.add(jlbIgorPrice);

        jlbApo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbApo.setText("Local Apothecary :");
        jPanel10.add(jlbApo);

        jspApo.setModel(new javax.swing.SpinnerNumberModel(0, 0, 2, 1));
        jspApo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jspApoMouseExited(evt);
            }
        });
        jspApo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspApoStateChanged(evt);
            }
        });
        jPanel10.add(jspApo);

        jlbApoPrice.setText("(100 000 gc) ");
        jPanel10.add(jlbApoPrice);

        jpnOthers.add(jPanel10, java.awt.BorderLayout.NORTH);

        jtpInducements.addTab("Others", jpnOthers);

        getContentPane().add(jtpInducements, java.awt.BorderLayout.CENTER);
        jtpInducements.getAccessibleContext().setAccessibleName("Inducements");

        jpnBottom.setMaximumSize(new java.awt.Dimension(460, 33));
        jpnBottom.setMinimumSize(new java.awt.Dimension(460, 33));
        jpnBottom.setPreferredSize(new java.awt.Dimension(460, 100));
        jpnBottom.setLayout(new java.awt.BorderLayout());

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel11.add(jbtOK);

        jpnBottom.add(jPanel11, java.awt.BorderLayout.SOUTH);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setPreferredSize(new java.awt.Dimension(166, 85));

        jtaOpponentChoice.setColumns(20);
        jtaOpponentChoice.setEditable(false);
        jtaOpponentChoice.setRows(8);
        jtaOpponentChoice.setText("The opponent has chosen :\n");
        jtaOpponentChoice.setToolTipText("Opponent choice");
        jScrollPane3.setViewportView(jtaOpponentChoice);

        jPanel12.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jpnBottom.add(jPanel12, java.awt.BorderLayout.NORTH);

        getContentPane().add(jpnBottom, java.awt.BorderLayout.SOUTH);

        jpnPettyCash.setBorder(javax.swing.BorderFactory.createTitledBorder("Petty Cash"));
        jpnPettyCash.setMaximumSize(new java.awt.Dimension(460, 46));
        jpnPettyCash.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Initial Petty Cash :");
        jpnPettyCash.add(jLabel1);

        jtfPettyCashInitial.setEditable(false);
        jtfPettyCashInitial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jpnPettyCash.add(jtfPettyCashInitial);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Remaining Petty Cash :");
        jpnPettyCash.add(jLabel2);

        jtfRemainginPettyCash.setEditable(false);
        jpnPettyCash.add(jtfRemainginPettyCash);

        getContentPane().add(jpnPettyCash, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbtOKActionPerformed
    {//GEN-HEADEREND:event_jbtOKActionPerformed
        try {
            /* Add Star player */
            for (int i = 0; i < _starPlayers.size(); i++) {
                dPlayer player = (dPlayer) _starPlayers.get(i);
                diStarPlayer induc = new diStarPlayer();
                induc.setPublicName(player.getName());
                //Vector players = _model.getPlayers();
                //players.add(player);
                //_model.setPlayers(players);
                _model.addPlayer(player);
                _model.addInducement(induc);
            }

            /* Add Mercenary */
            for (int i = 0; i < _mercenaries.size(); i++) {
                dPlayer player = (dPlayer) _mercenaries.get(i);
                diMercenary induc = new diMercenary();
                induc.setPublicName(player.getName());
                /*Vector players = _model.getPlayers();
                players.add(player);
                _model.setPlayers(players);*/
                _model.addPlayer(player);
                _model.addInducement(induc);
            }

            /* Add Cards */
            Vector cards = addCards();
            for (int i = 0; i < cards.size(); i++) {
                _model.addInducement((dInducement) cards.get(i));
            }

            /* Add Others */
            Vector others = addOthers();
            for (int i = 0; i < others.size(); i++) {
                _model.addInducement((dInducement) others.get(i));
            }

        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
        this.setVisible(false);
}//GEN-LAST:event_jbtOKActionPerformed

    protected Vector addCards() {
        Vector cards = new Vector();
        int desp = (Integer) jspDesperateMeasure.getValue();
        int dirty = (Integer) jspDirtyTrick.getValue();
        int karma = (Integer) jspGoodKarma.getValue();
        int magic = (Integer) jspMagicItem.getValue();
        int mayhem = (Integer) jspMiscMayhem.getValue();
        int random = (Integer) jspRandomEvent.getValue();
        int special = (Integer) jspSpecialAction.getValue();

        for (int i = 0; i < desp; i++) {
            boolean found = true;
            diCard card = null;
            while (found) {
                found = false;
                card = diCardFactory.createCard(diCardFactory.C_DESPERATE_MEASURE);
                for (int j = 0; j < cards.size(); j++) {
                    diCard cardChosen = (diCard) cards.get(j);
                    if (card.getName().equals(cardChosen.getName())) {
                        found = true;
                    }
                    break;
                }
            }
            cards.add(card);
        }

        for (int i = 0; i < dirty; i++) {
            boolean found = true;
            diCard card = null;
            while (found) {
                found = false;
                card = diCardFactory.createCard(diCardFactory.C_DIRTY_TRICK);
                for (int j = 0; j < cards.size(); j++) {
                    diCard cardChosen = (diCard) cards.get(j);
                    if (card.getName().equals(cardChosen.getName())) {
                        found = true;
                    }
                    break;
                }
            }
            cards.add(card);
        }

        for (int i = 0; i < karma; i++) {
            boolean found = true;
            diCard card = null;
            while (found) {
                found = false;
                card = diCardFactory.createCard(diCardFactory.C_GOOD_KARMA);
                for (int j = 0; j < cards.size(); j++) {
                    diCard cardChosen = (diCard) cards.get(j);
                    if (card.getName().equals(cardChosen.getName())) {
                        found = true;
                    }
                    break;
                }
            }
            cards.add(card);
        }

        for (int i = 0; i < magic; i++) {
            boolean found = true;
            diCard card = null;
            while (found) {
                found = false;
                card = diCardFactory.createCard(diCardFactory.C_MAGIC_ITEM);
                for (int j = 0; j < cards.size(); j++) {
                    diCard cardChosen = (diCard) cards.get(j);
                    if (card.getName().equals(cardChosen.getName())) {
                        found = true;
                    }
                    break;
                }
            }
            cards.add(card);
        }

        for (int i = 0; i < mayhem; i++) {
            boolean found = true;
            diCard card = null;
            while (found) {
                found = false;
                card = diCardFactory.createCard(diCardFactory.C_MISC_MAYHEM);
                for (int j = 0; j < cards.size(); j++) {
                    diCard cardChosen = (diCard) cards.get(j);
                    if (card.getName().equals(cardChosen.getName())) {
                        found = true;
                    }
                    break;
                }
            }
            cards.add(card);
        }

        for (int i = 0; i < random; i++) {
            boolean found = true;
            diCard card = null;
            while (found) {
                found = false;
                card = diCardFactory.createCard(diCardFactory.C_RANDOM_EVENT);
                for (int j = 0; j < cards.size(); j++) {
                    diCard cardChosen = (diCard) cards.get(j);
                    if (card.getName().equals(cardChosen.getName())) {
                        found = true;
                    }
                    break;
                }
            }
            cards.add(card);
        }
        for (int i = 0; i < special; i++) {
            boolean found = true;
            diCard card = null;
            while (found) {
                found = false;
                card = diCardFactory.createCard(diCardFactory.C_SPECIAL_PLAY);
                for (int j = 0; j < cards.size(); j++) {
                    diCard cardChosen = (diCard) cards.get(j);
                    if (card.getName().equals(cardChosen.getName())) {
                        found = true;
                    }
                    break;
                }
            }
            cards.add(card);
        }
        return cards;
    }

    public Vector addOthers() {
        Vector others = new Vector();

        if (jcbHalfling.isSelected()) {
            diChef chef = new diChef();
            others.add(chef);
        }

        if (jcbIgor.isSelected()) {
            diIgor igor = new diIgor();
            others.add(igor);
        }

        if (jcbWizard.isSelected()) {
            diWizard wizard = new diWizard();
            others.add(wizard);
        }

        int bribes = (Integer) jspBribe.getValue();
        for (int i = 0; i < bribes; i++) {
            diBribeTheRef bribe = new diBribeTheRef();
            others.add(bribe);
        }

        int babes = (Integer) jspBabes.getValue();
        for (int i = 0; i < babes; i++) {
            diBabe babe = new diBabe();
            others.add(babe);
        }

        int apot = (Integer) jspApo.getValue();
        for (int i = 0; i < apot; i++) {
            diLocalApothecary apo = new diLocalApothecary();
            others.add(apo);
        }

        int reroll = (Integer) jspReroll.getValue();
        for (int i = 0; i < reroll; i++) {
            diExtraReroll extra = new diExtraReroll();
            others.add(extra);
        }

        return others;
    }

    private void jcbWizardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbWizardActionPerformed
        repaint();
    }//GEN-LAST:event_jcbWizardActionPerformed

    private void jcbHalflingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbHalflingActionPerformed
        repaint();
    }//GEN-LAST:event_jcbHalflingActionPerformed

    private void jcbIgorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbIgorActionPerformed
        repaint();
    }//GEN-LAST:event_jcbIgorActionPerformed

    private void jspSpecialActionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspSpecialActionMouseClicked
        repaint();
    }//GEN-LAST:event_jspSpecialActionMouseClicked

    private void jspMagicItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspMagicItemMouseClicked
        repaint();
    }//GEN-LAST:event_jspMagicItemMouseClicked

    private void jspDirtyTrickMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspDirtyTrickMouseClicked
        repaint();
    }//GEN-LAST:event_jspDirtyTrickMouseClicked

    private void jspGoodKarmaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspGoodKarmaMouseClicked
        repaint();
    }//GEN-LAST:event_jspGoodKarmaMouseClicked

    private void jspRandomEventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspRandomEventMouseClicked
        repaint();
    }//GEN-LAST:event_jspRandomEventMouseClicked

    private void jspDesperateMeasureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspDesperateMeasureMouseClicked
        repaint();
    }//GEN-LAST:event_jspDesperateMeasureMouseClicked

    private void jbtAddStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddStarActionPerformed
        jdgChooseStarPlayer window = new jdgChooseStarPlayer(_model, _initialPettyCash - _spentPettyCash, _starPlayers, _isChallenger);
        window.setVisible(true);
        repaint();
        //jtbStarPlayers.repaint();
    }//GEN-LAST:event_jbtAddStarActionPerformed

    private void jspBabesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jspBabesPropertyChange
        repaint();
    }//GEN-LAST:event_jspBabesPropertyChange

    private void jspBribePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jspBribePropertyChange
        repaint();
    }//GEN-LAST:event_jspBribePropertyChange

    private void jspRerollPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jspRerollPropertyChange
        repaint();
    }//GEN-LAST:event_jspRerollPropertyChange

    private void jspApoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspApoStateChanged
        repaint();
    }//GEN-LAST:event_jspApoStateChanged

    private void jspMiscMayhemStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspMiscMayhemStateChanged
        repaint();
    }//GEN-LAST:event_jspMiscMayhemStateChanged

    private void jbtAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddActionPerformed
        jdgChooseMercenary window = new jdgChooseMercenary(_model, _initialPettyCash - _spentPettyCash, _mercenaries, _isChallenger);
        window.setVisible(true);
        repaint();
        //jtbMercenaries.repaint();
    }//GEN-LAST:event_jbtAddActionPerformed

    private void jbtRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveActionPerformed
        int index = jtbMercenaries.getSelectedRow();
        if (index >= 0) {
            _mercenaries.remove(index);
        } else {
            if (_mercenaries.size() > 0) {
                String list[] = new String[_mercenaries.size() + 1];
                list[0] = "None";
                for (int i = 0; i < _mercenaries.size(); i++) {
                    dPlayer player = (dPlayer) _mercenaries.get(i);
                    list[i + 1] = player.getName();
                }
                Object response = JOptionPane.showInputDialog(
                        null,
                        "Do you want to remove a mercenary ?",
                        "Select one",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        list, list[0]);
                for (int i = 0; i < _mercenaries.size(); i++) {
                    dPlayer player = (dPlayer) _mercenaries.get(i);
                    if (response.equals(player.getName())) {
                        _mercenaries.remove(player);
                        i = _mercenaries.size();
                    }
                }
            }
        }
        repaint();
    }//GEN-LAST:event_jbtRemoveActionPerformed

    private void jbtRemoveStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveStarActionPerformed
        int index = jtbStarPlayers.getSelectedRow();
        if (index >= 0) {
            _starPlayers.remove(index);
        } else {
            if (_starPlayers.size() > 0) {
                String list[] = new String[_starPlayers.size() + 1];
                list[0] = "None";
                for (int i = 0; i < _starPlayers.size(); i++) {
                    dPlayer player = (dPlayer) _starPlayers.get(i);
                    list[i + 1] = player.getName();
                }
                Object response = JOptionPane.showInputDialog(
                        null,
                        "Do you want to remove a star player ?",
                        "Select one",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        list, list[0]);
                for (int i = 0; i < _starPlayers.size(); i++) {
                    dPlayer player = (dPlayer) _starPlayers.get(i);
                    if (response.equals(player.getName())) {
                        _starPlayers.remove(player);
                        break;
                    }
                }
            }
        }
        repaint();
    }//GEN-LAST:event_jbtRemoveStarActionPerformed

    private void jspMiscMayhemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspMiscMayhemMouseClicked
        repaint();
    }//GEN-LAST:event_jspMiscMayhemMouseClicked

    private void jspMiscMayhemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspMiscMayhemMouseExited
        repaint();
    }//GEN-LAST:event_jspMiscMayhemMouseExited

    private void jspSpecialActionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspSpecialActionMouseExited
        repaint();
    }//GEN-LAST:event_jspSpecialActionMouseExited

    private void jspMagicItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspMagicItemMouseExited
        repaint();
    }//GEN-LAST:event_jspMagicItemMouseExited

    private void jspDirtyTrickMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspDirtyTrickMouseExited
        repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_jspDirtyTrickMouseExited

    private void jspGoodKarmaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspGoodKarmaMouseExited
        repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_jspGoodKarmaMouseExited

    private void jspRandomEventMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspRandomEventMouseExited
        repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_jspRandomEventMouseExited

    private void jspDesperateMeasureMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspDesperateMeasureMouseExited
        repaint();
    }//GEN-LAST:event_jspDesperateMeasureMouseExited

    private void jspBabesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspBabesMouseExited
        repaint();
    }//GEN-LAST:event_jspBabesMouseExited

    private void jspBribeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspBribeMouseExited
repaint();
    }//GEN-LAST:event_jspBribeMouseExited

    private void jspRerollMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspRerollMouseExited
repaint();
    }//GEN-LAST:event_jspRerollMouseExited

    private void jspApoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspApoMouseExited
repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_jspApoMouseExited

    private void jtpInducementsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtpInducementsMouseClicked
repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_jtpInducementsMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtAdd;
    private javax.swing.JButton jbtAddStar;
    private javax.swing.JButton jbtOK;
    private javax.swing.JButton jbtRemove;
    private javax.swing.JButton jbtRemoveStar;
    private javax.swing.JCheckBox jcbHalfling;
    private javax.swing.JCheckBox jcbIgor;
    private javax.swing.JCheckBox jcbWizard;
    private javax.swing.JLabel jlbApo;
    private javax.swing.JLabel jlbApoPrice;
    private javax.swing.JLabel jlbBabes;
    private javax.swing.JLabel jlbBabesPrice;
    private javax.swing.JLabel jlbBribe;
    private javax.swing.JLabel jlbBribePrice;
    private javax.swing.JLabel jlbCardIcon1;
    private javax.swing.JLabel jlbCardIcon2;
    private javax.swing.JLabel jlbCardIcon3;
    private javax.swing.JLabel jlbCardIcon4;
    private javax.swing.JLabel jlbCardIcon5;
    private javax.swing.JLabel jlbCardIcon6;
    private javax.swing.JLabel jlbCardIcon7;
    private javax.swing.JLabel jlbChefPrice;
    private javax.swing.JLabel jlbDesperateMeasure;
    private javax.swing.JLabel jlbDirtyTrick;
    private javax.swing.JLabel jlbGoodKarma;
    private javax.swing.JLabel jlbIgorPrice;
    private javax.swing.JLabel jlbMagicItem;
    private javax.swing.JLabel jlbMiscMayhem;
    private javax.swing.JLabel jlbRandomEvent;
    private javax.swing.JLabel jlbReroll;
    private javax.swing.JLabel jlbRerollPrice;
    private javax.swing.JLabel jlbSpecialAction;
    private javax.swing.JLabel jlbWizardPrice;
    private javax.swing.JPanel jpnBottom;
    private javax.swing.JPanel jpnMercenaries;
    private javax.swing.JPanel jpnOthers;
    private javax.swing.JPanel jpnPettyCash;
    private javax.swing.JPanel jpnSpecialCards;
    private javax.swing.JPanel jpnStarPlayers;
    private javax.swing.JSpinner jspApo;
    private javax.swing.JSpinner jspBabes;
    private javax.swing.JSpinner jspBribe;
    private javax.swing.JSpinner jspDesperateMeasure;
    private javax.swing.JSpinner jspDirtyTrick;
    private javax.swing.JSpinner jspGoodKarma;
    private javax.swing.JSpinner jspMagicItem;
    private javax.swing.JSpinner jspMiscMayhem;
    private javax.swing.JSpinner jspRandomEvent;
    private javax.swing.JSpinner jspReroll;
    private javax.swing.JSpinner jspSpecialAction;
    private javax.swing.JTextArea jtaOpponentChoice;
    private javax.swing.JTable jtbMercenaries;
    private javax.swing.JTable jtbStarPlayers;
    private javax.swing.JTextField jtfPettyCashInitial;
    private javax.swing.JTextField jtfRemainginPettyCash;
    private javax.swing.JTabbedPane jtpInducements;
    // End of variables declaration//GEN-END:variables
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        _spentPettyCash = 0;
        _spentPettyCash += repaint_others(_initialPettyCash);
        _spentPettyCash += repaint_stars(_initialPettyCash - _spentPettyCash);
        _spentPettyCash += repaint_mercenaries(_initialPettyCash - _spentPettyCash);
        _spentPettyCash += repaint_cards(_initialPettyCash - _spentPettyCash);
        long remaining = _initialPettyCash - _spentPettyCash;
        jtfPettyCashInitial.setText(Long.toString(_initialPettyCash));
        jtfRemainginPettyCash.setText(Long.toString(remaining));
    }

    protected long repaint_others(long initialPettyCash) {
        int spent = 0;
        try {
            mTeamRoster roster = mBBoS.getSingleton().getTeamType(_model.getRaceId());

            if (jcbHalfling.isSelected()) {
                spent += roster.getChef();
            }

            if (jcbIgor.isSelected()) {
                spent += 100000;
            }

            if (jcbWizard.isSelected()) {
                spent += 150000;
            }

            int bribes = (Integer) jspBribe.getValue();
            spent += bribes * roster.getBribeCost();

            int babes = (Integer) jspBabes.getValue();
            spent += babes * 50000;

            int apot = (Integer) jspApo.getValue();
            spent += apot * 100000;

            int reroll = (Integer) jspReroll.getValue();
            spent += reroll * 100000;

            long remaining = initialPettyCash - spent;
            boolean enableChef = (remaining >= roster.getChef()) || (jcbHalfling.isSelected());
            jcbHalfling.setEnabled(enableChef);
            jlbChefPrice.setEnabled(enableChef);

            boolean enableIgor = ((roster.isCanRaise()) && (remaining >= 100000)) || (jcbIgor.isSelected());
            jcbIgor.setEnabled(enableIgor);
            jlbIgorPrice.setEnabled(enableIgor);

            boolean enableWizard = (remaining >= 150000) || (jcbWizard.isSelected());
            jcbWizard.setEnabled(enableWizard);
            jlbWizardPrice.setEnabled(enableWizard);

            boolean enableBabes = (remaining >= 50000) || (babes > 0);
            jspBabes.setEnabled(enableBabes);
            jlbBabes.setEnabled(enableBabes);
            jlbBabesPrice.setEnabled(enableBabes);
            int max = Math.max(((Long) Math.min(2, remaining / 50000)).intValue(), babes);
            ((SpinnerNumberModel) jspBabes.getModel()).setMaximum(max);

            boolean enableBribes = (remaining >= roster.getBribeCost()) || (bribes > 0);
            jspBribe.setEnabled(enableBribes);
            jlbBribe.setEnabled(enableBribes);
            jlbBribePrice.setEnabled(enableBribes);
            max = Math.max(((Long) Math.min(3, remaining / roster.getBribeCost())).intValue(), bribes);
            ((SpinnerNumberModel) jspBribe.getModel()).setMaximum(max);

            boolean enableReroll = (remaining >= 100000) || (reroll > 0);
            jspReroll.setEnabled(enableReroll);
            jlbReroll.setEnabled(enableReroll);
            jlbRerollPrice.setEnabled(enableReroll);
            max = Math.max(((Long) Math.min(4, remaining / 100000)).intValue(), reroll);
            ((SpinnerNumberModel) jspReroll.getModel()).setMaximum(max);

            boolean enableApo = (remaining >= 100000) || (apot > 0);
            jspApo.setEnabled(enableApo);
            jlbApo.setEnabled(enableApo);
            jlbApoPrice.setEnabled(enableApo);
            max = Math.max(((Long) Math.min(2, remaining / 100000)).intValue(), apot);
            ((SpinnerNumberModel) jspApo.getModel()).setMaximum(max);

        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
        return spent;
    }

    protected long repaint_stars(long initialPettyCash) {
        long spent = 0;
        for (int i = 0; i < _starPlayers.size(); i++) {
            dPlayer player = (dPlayer) _starPlayers.get(i);
            spent += player.getCost();
        }

        long remaining = initialPettyCash - spent;

        try {
            mTeamRoster roster = mBBoS.getSingleton().getTeamType(_model.getRaceId());

            boolean enableAddStarPlayer = false;
            if (_starPlayers.size() < 2) {
                for (int i = 0; i < roster.getStarPlayersNumber(); i++) {
                    mPlayerType player = roster.getStarPlayer(i);
                    if (player.getCost() <= remaining) {
                        enableAddStarPlayer = true;
                    }
                }
            }

            if (_model.getPlayersNumber() + _mercenaries.size() + _starPlayers.size() >= 16) {
                enableAddStarPlayer = false;
            }
            jbtAddStar.setEnabled(enableAddStarPlayer);
            boolean enableRemoveStarPlayer = (_starPlayers.size() > 0);
            jbtRemoveStar.setEnabled(enableRemoveStarPlayer);

        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
        jtbStarPlayers.setModel(new jtmChosenStars(_starPlayers));
        jtbStarPlayers.setDefaultRenderer(String.class, new jtcRenderer());
        jtbStarPlayers.setDefaultRenderer(Integer.class, new jtcRenderer());

        TableColumn column = null;
        for (int i = 0; i < jtbStarPlayers.getModel().getColumnCount(); i++) {
            column = jtbStarPlayers.getColumnModel().getColumn(i);
            switch (i) {
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
                    column.setPreferredWidth(300);
                    break;
                case 6:
                    column.setPreferredWidth(40);
                    break;
            }
        }
        return spent;
    }

    protected long repaint_mercenaries(long initialPettyCash) {
        long spent = 0;
        for (int i = 0; i < _mercenaries.size(); i++) {
            dPlayer player = (dPlayer) _mercenaries.get(i);
            spent += player.getCost();
        }

        try {
            long remaining = initialPettyCash - spent;
            mTeamRoster roster = mBBoS.getSingleton().getTeamType(_model.getRaceId());

            boolean enableAddMercenary = false;
            for (int i = 0; i < roster.getRegularPlayersNumber(); i++) {
                mPlayerType player = roster.getPlayerType(i);
                int limit = player.getLimit();
                for (int j = 0; j < _model.getPlayersNumber(); j++) {
                    dPlayer p = _model.getPlayerCopy(j);
                    if (p.getPosition().equals(player.getPosition())) {
                        limit--;
                    }
                }
                if (((player.getCost() + 30000) <= remaining) && (limit > 0)) {
                    enableAddMercenary = true;
                }
            }
            if (_model.getPlayersNumber() + _mercenaries.size() + _starPlayers.size() >= 16) {
                enableAddMercenary = false;
            }
            jbtAdd.setEnabled(enableAddMercenary);

            boolean enableRemoveMercenary = (_mercenaries.size() > 0);
            jbtRemove.setEnabled(enableRemoveMercenary);
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }

        jtbMercenaries.setModel(new jtmChosenMercenaries(_mercenaries));
        jtbMercenaries.setDefaultRenderer(String.class, new jtcRenderer());
        jtbMercenaries.setDefaultRenderer(Integer.class, new jtcRenderer());

        TableColumn column = null;
        for (int i = 0; i < jtbMercenaries.getModel().getColumnCount(); i++) {
            column = jtbMercenaries.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(100);
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
                    column.setPreferredWidth(300);
                    break;
                case 7:
                    column.setPreferredWidth(40);
                    break;
            }
        }
        return spent;

    }

    protected long repaint_cards(long initialPettyCash) {
        long spent = 0;

        int special = (Integer) jspSpecialAction.getValue();
        spent += special * 50000;
        int desp = (Integer) jspDesperateMeasure.getValue();
        spent += desp * 400000;
        int dirty = (Integer) jspDirtyTrick.getValue();
        spent += dirty * 50000;
        int karma = (Integer) jspGoodKarma.getValue();
        spent += karma * 100000;
        int magic = (Integer) jspMagicItem.getValue();
        spent += magic * 50000;
        int mayhem = (Integer) jspMiscMayhem.getValue();
        spent += mayhem * 50000;
        int random = (Integer) jspRandomEvent.getValue();
        spent += random * 200000;


        long remaining = initialPettyCash - spent;

        boolean enableCardsMiscMayhem = (remaining >= 50000) || (mayhem > 0);
        jspMiscMayhem.setEnabled(enableCardsMiscMayhem);
        jlbMiscMayhem.setEnabled(enableCardsMiscMayhem);
        int max = ((Long)Math.min(13, Math.max(remaining / 50000, mayhem))).intValue();
        ((SpinnerNumberModel) jspMiscMayhem.getModel()).setMaximum(max);

        boolean enableCardsSpecialPlay = (remaining >= 50000) || (special > 0);
        jspSpecialAction.setEnabled(enableCardsSpecialPlay);
        jlbSpecialAction.setEnabled(enableCardsSpecialPlay);
        max = ((Long)Math.min(13, Math.max(remaining / 50000, special))).intValue();
        ((SpinnerNumberModel) jspSpecialAction.getModel()).setMaximum(max);

        boolean enableCardsMagicItem = (remaining >= 50000) || (magic > 0);
        jspMagicItem.setEnabled(enableCardsMagicItem);
        jlbMagicItem.setEnabled(enableCardsMagicItem);
        max = ((Long)Math.min(13, Math.max(remaining / 50000, magic))).intValue();
        ((SpinnerNumberModel) jspMagicItem.getModel()).setMaximum(max);

        boolean enableCardsDirtyTrick = (remaining >= 50000) || (dirty > 0);
        jspDirtyTrick.setEnabled(enableCardsDirtyTrick);
        jlbDirtyTrick.setEnabled(enableCardsDirtyTrick);
        max = ((Long)Math.min(13, Math.max(remaining / 50000, dirty))).intValue();
        ((SpinnerNumberModel) jspDirtyTrick.getModel()).setMaximum(max);

        boolean enableCardsGoodKarma = (remaining >= 100000) || (karma > 0);
        jspGoodKarma.setEnabled(enableCardsGoodKarma);
        jlbGoodKarma.setEnabled(enableCardsGoodKarma);
        max = ((Long)Math.min(13, Math.max(remaining / 100000, karma))).intValue();
        ((SpinnerNumberModel) jspGoodKarma.getModel()).setMaximum(max);

        boolean enableCardsRandomEvent = (remaining >= 200000) || (random > 0);
        jspRandomEvent.setEnabled(enableCardsRandomEvent);
        jlbRandomEvent.setEnabled(enableCardsRandomEvent);
        max = ((Long)Math.min(13, Math.max(remaining / 200000, random))).intValue();
        ((SpinnerNumberModel) jspRandomEvent.getModel()).setMaximum(max);

        boolean enableCardsDesperateMeasure = (remaining >= 400000) || (desp > 0);
        jspDesperateMeasure.setEnabled(enableCardsDesperateMeasure);
        jlbDesperateMeasure.setEnabled(enableCardsDesperateMeasure);
        max = ((Long)Math.min(13, Math.max(remaining / 400000, desp))).intValue();
        ((SpinnerNumberModel) jspDesperateMeasure.getModel()).setMaximum(max);

        return spent;
    }
}

