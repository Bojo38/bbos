/*
 * jfCommunicationSettings.java
 *
 * Created on 11 mai 2008, 16:43
 */
package bbos.Match.Automat.DuringMatch;

import bbos.Match.Automat.BeforeMatch.*;
import bbos.General.Views.*;
import bbos.*;

import bbos.Match.Model.Actions.dActionsFactory;
import bbos.Match.Model.Actions.dAction;
import bbos.Match.Model.Actions.daBlitz;
import bbos.Match.Model.Actions.daBlitzStab;
import bbos.Match.Model.Actions.daBlock;
import bbos.Match.Model.Actions.daBlockStab;
import bbos.Match.Model.Actions.daFanatic;
import bbos.Match.Model.Actions.daFoul;
import bbos.Match.Model.Actions.daHandOff;
import bbos.Match.Model.Actions.daHypnoticGaze;
import bbos.Match.Model.Actions.daMove;
import bbos.Match.Model.Actions.daPass;
import bbos.Match.Model.Actions.daThrowTeamMate;
import bbos.Match.Model.Competences.dCompetence;
import bbos.Match.Model.Inducements.Cards.diCard;
import bbos.Match.Model.Inducements.Cards.diCardFactory;
import bbos.Match.Model.Inducements.dInducement;
import bbos.Match.Model.Roll.drScatter;
import bbos.Match.Model.dMeteo;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.dSquareCollection;
import bbos.Match.Model.dSquare;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiTeam;
import bbos.Match.tNetworkConnexion;
import bbos.Tools.bbTool;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Vector;

class CompListModel extends AbstractListModel {

    String[] strings = {};

    CompListModel(String[] comps) {
        strings = comps;
    }

    public int getSize() {
        return strings.length;
    }

    public Object getElementAt(int i) {
        return strings[i];
    }
}

/**
 *
 * @author  frederic
 */
public class jdgMatch extends JDialog {

    rmiMatch _model;
    rmiTeam _leftTeam;
    rmiTeam _rightTeam;
    Vector _leftInducements;
    Vector _rightInducements;
    Vector _leftPlayers;
    Vector _rightPlayers;
    HashMap _activeIcons;
    HashMap _passiveIcons;
    public static final int C_SQUARE_SIZE = 30;
    rmiPlayer _flyingPlayer = null;
    rmiPlayer _selectedPlayer = null;
    rmiPlayer _grabbedPlayer = null;
    boolean _isChallenger = false;
    ImageIcon _ballImage = null;
    ImageIcon _holdBallImage = null;
    Vector _actionButtons = new Vector();
    dAction _currentAction = null;
    Vector _actions;
    boolean _standalone = false;

    /** Creates new form jfCommunicationSettings */
    public jdgMatch(rmiMatch model, rmiTeam left, rmiTeam right, boolean isChallenger, boolean standalone) {
        initComponents();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }

        this.setPreferredSize(new Dimension(781, 631));

        _standalone = standalone;
        _model = model;
        _leftTeam = left;
        _rightTeam = right;

        try {
            _leftInducements = _leftTeam.getInducements();
            _rightInducements = _rightTeam.getInducements();

            /* Get Players */
            _leftPlayers = tNetworkConnexion.getConnexion().getLeftPlayers();
            _rightPlayers = tNetworkConnexion.getConnexion().getRightPlayers();

            /* Used for less network data to download */
            fillIconMap();


        } catch (RemoteException e) {
            e.printStackTrace();
        }

        _isChallenger = isChallenger;

        jlmDiceHistorical list_model = new jlmDiceHistorical(model);
        jlsDiceHistorical.setModel(list_model);
        jlsDiceHistorical.setCellRenderer(new jlrDiceHistorical());

        /* Display Bonuses information*/
        paintBonuses();
        paintMatchData();
        paintPlayers();

        _ballImage = new ImageIcon(MainForm.getSingleton().getClass().getResource("/resources/images/match/sball.gif"));
        _holdBallImage = new ImageIcon(MainForm.getSingleton().getClass().getResource("/resources/images/match/tball.gif"));

        repaint();
        jbtNext.setEnabled(true);

        _actionButtons.add(jtbAction1);
        _actionButtons.add(jtbAction2);
        _actionButtons.add(jtbAction3);
        _actionButtons.add(jtbAction4);
        _actionButtons.add(jtbAction5);
        _actionButtons.add(jtbAction6);
        _actionButtons.add(jtbAction7);
        _actionButtons.add(jtbAction8);
        _actionButtons.add(jtbAction9);

        for (int i = 0; i < _actionButtons.size(); i++) {
            JToggleButton button = (JToggleButton) _actionButtons.get(i);
            button.setEnabled(false);
            button.setVisible(false);
            button.setIcon(null);
        }
    }

    protected void fillIconMap() {
        _activeIcons = new HashMap();
        _passiveIcons = new HashMap();

        for (int i = 0; i < _leftPlayers.size(); i++) {
            rmiPlayer player = (rmiPlayer) _leftPlayers.get(i);

            ImageIcon icon1 = null;
            String address = "";
            try {
                address = player.getActiveIcone();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (address != null) {
                icon1 = new ImageIcon(address);
            }
            if ((icon1 == null) || (icon1.getImage() == null)) {
                try {
                    if (!player.isStarPlayer()) {
                        String path = "/resources/images/players/" + _leftTeam.getRace() + "/left/active/" + player.getPosition().toLowerCase() + ".gif";
                        path = path.replace(" ", "_");
                        path = path.replace("'", "_");
                        try {
                            icon1 = new ImageIcon(MainForm.getSingleton().getClass().getResource(path));
                        } catch (NullPointerException e) {
                            System.err.println(path + " not found");
                            //e.printStackTrace();
                        }
                    } else {
                        String path = "/resources/images/players/StarPlayers/left/active/" + player.getName().toLowerCase() + ".gif";
                        path = path.replace(" ", "_");
                        path = path.replace("'", "_");
                        try {
                            icon1 = new ImageIcon(MainForm.getSingleton().getClass().getResource(path));
                        } catch (NullPointerException e) {
                            System.err.println(path + " not found");
                            //e.printStackTrace();
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            if ((icon1 == null) || (icon1.getImage() == null)) {
                icon1 = new ImageIcon(MainForm.getSingleton().getClass().getResource("/resources/images/players/Unknown/left/active/unknown.gif"));
            }
            _activeIcons.put(player, icon1);

            ImageIcon icon2 = null;
            try {
                address = player.getPassiveIcone();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (address != null) {
                icon2 = new ImageIcon(address);
            }
            if ((icon2 == null) || (icon2.getImage() == null)) {
                try {
                    if (!player.isStarPlayer()) {
                        String path = "/resources/images/players/" + _leftTeam.getRace() + "/left/passive/" + player.getPosition().toLowerCase() + ".gif";
                        path = path.replace(" ", "_");
                        path = path.replace("'", "_");
                        try {
                            icon2 = new ImageIcon(MainForm.getSingleton().getClass().getResource(path));
                        } catch (NullPointerException e) {
                            System.err.println(path + " not found");
                        }
                    } else {
                        String path = "/resources/images/players/StarPlayers/left/passive/" + player.getName().toLowerCase() + ".gif";
                        path = path.replace(" ", "_");
                        path = path.replace("'", "_");
                        try {
                            icon2 = new ImageIcon(MainForm.getSingleton().getClass().getResource(path));
                        } catch (NullPointerException e) {
                            System.err.println(path + " not found");
                            //e.printStackTrace();
                        }
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            if ((icon2 == null) || (icon2.getImage() == null)) {
                icon2 = new ImageIcon(MainForm.getSingleton().getClass().getResource("/resources/images/players/Unknown/left/passive/unknown.gif"));
            }

            _passiveIcons.put(player, icon2);
        }

        for (int i = 0; i
                < _rightPlayers.size(); i++) {

            rmiPlayer player = (rmiPlayer) _rightPlayers.get(i);
            ImageIcon icon1 = null;
            String address = "";
            try {
                address = player.getActiveIcone();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            if (address != null) {
                icon1 = new ImageIcon(address);
            }

            if ((icon1 == null) || (icon1.getImage() == null)) {
                try {
                    if (!player.isStarPlayer()) {
                        String path = "/resources/images/players/" + _rightTeam.getRace() + "/right/active/" + player.getPosition().toLowerCase() + ".gif";
                        path = path.replace(" ", "_");
                        path = path.replace("'", "_");
                        try {
                            icon1 = new ImageIcon(MainForm.getSingleton().getClass().getResource(path));
                        } catch (NullPointerException e) {
                            System.err.println(path + " not found");
                        }
                    } else {
                        String path = "/resources/images/players/StarPlayers/right/active/" + player.getName().toLowerCase() + ".gif";
                        path = path.replace(" ", "_");
                        path = path.replace("'", "_");
                        try {
                            icon1 = new ImageIcon(MainForm.getSingleton().getClass().getResource(path));
                        } catch (NullPointerException e) {
                            System.err.println(path + " not found");
                            //e.printStackTrace();
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
            if ((icon1 == null) || (icon1.getImage() == null)) {
                icon1 = new ImageIcon(MainForm.getSingleton().getClass().getResource("/resources/images/players/Unknown/right/active/unknown.gif"));
            }

            _activeIcons.put(player, icon1);

            ImageIcon icon2 = null;
            try {
                address = player.getPassiveIcone();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            if (address != null) {
                icon2 = new ImageIcon(address);
            }

            if ((icon2 == null) || (icon2.getImage() == null)) {
                try {
                    if (!player.isStarPlayer()) {
                        String path = "/resources/images/players/" + _rightTeam.getRace() + "/right/passive/" + player.getPosition().toLowerCase() + ".gif";
                        path = path.replace(" ", "_");
                        path = path.replace("'", "_");
                        icon2 = new ImageIcon(MainForm.getSingleton().getClass().getResource(path));
                    } else {
                        String path = "/resources/images/players/StarPlayers/right/passive/" + player.getName().toLowerCase() + ".gif";
                        path = path.replace(" ", "_");
                        path = path.replace("'", "_");
                        try {
                            icon2 = new ImageIcon(MainForm.getSingleton().getClass().getResource(path));
                        } catch (NullPointerException e) {
                            System.err.println(path + "not found");
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
            if ((icon2 == null) || (icon2.getImage() == null)) {
                icon2 = new ImageIcon(MainForm.getSingleton().getClass().getResource("/resources/images/players/Unknown/right/passive/unknown.gif"));
            }
            _passiveIcons.put(player, icon2);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnTop = new javax.swing.JPanel();
        jpnTopLeft = new javax.swing.JPanel();
        jpnRerolls = new javax.swing.JPanel();
        jlbRerollLeft = new javax.swing.JLabel();
        jlbMinus2 = new javax.swing.JLabel();
        jlbRerollRight = new javax.swing.JLabel();
        jpnTurn = new javax.swing.JPanel();
        jlbTurnLeft = new javax.swing.JLabel();
        jlbMinus1 = new javax.swing.JLabel();
        jlbTurnRight = new javax.swing.JLabel();
        jpnScore = new javax.swing.JPanel();
        jlbScoreLeft = new javax.swing.JLabel();
        jlbMinus = new javax.swing.JLabel();
        jlbScoreRight = new javax.swing.JLabel();
        jpnFAME = new javax.swing.JPanel();
        jlbFAMELeft = new javax.swing.JLabel();
        jlbMinus3 = new javax.swing.JLabel();
        jlbFAMERight = new javax.swing.JLabel();
        jlbWeatherIcon = new javax.swing.JLabel();
        jpnAverage = new javax.swing.JPanel();
        jlbTurnLeft1 = new javax.swing.JLabel();
        jlbMinus4 = new javax.swing.JLabel();
        jlbTurnRight1 = new javax.swing.JLabel();
        jpnTopRight = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaLastRolls = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlsDiceHistorical = new javax.swing.JList();
        jpnTopMiddle = new javax.swing.JPanel();
        jpnRerollPanel = new javax.swing.JPanel();
        jbtReroll = new javax.swing.JButton();
        jbtProReroll = new javax.swing.JButton();
        jbtNext = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jtbAction1 = new javax.swing.JToggleButton();
        jtbAction2 = new javax.swing.JToggleButton();
        jtbAction3 = new javax.swing.JToggleButton();
        jtbAction4 = new javax.swing.JToggleButton();
        jtbAction5 = new javax.swing.JToggleButton();
        jtbAction6 = new javax.swing.JToggleButton();
        jtbAction7 = new javax.swing.JToggleButton();
        jtbAction8 = new javax.swing.JToggleButton();
        jtbAction9 = new javax.swing.JToggleButton();
        jpnBottom = new javax.swing.JPanel();
        jpnLeftDugout = new javax.swing.JPanel();
        jlbDugoutLeft = new javax.swing.JLabel();
        jpnSouth = new javax.swing.JPanel();
        jpnBonusesLeft = new javax.swing.JPanel();
        jlbApothecaryLeft = new javax.swing.JLabel();
        jlbCheerleadersLeft = new javax.swing.JLabel();
        jlbAssistsLeft = new javax.swing.JLabel();
        jlbLocalApothecaryLeft = new javax.swing.JLabel();
        jlbIgorLeft = new javax.swing.JLabel();
        jlbBabesLeft = new javax.swing.JLabel();
        jlbBribeTheRefLeft = new javax.swing.JLabel();
        jlbWizardLeft = new javax.swing.JLabel();
        jlbChefLeft = new javax.swing.JLabel();
        jlbMagicItemLeft = new javax.swing.JLabel();
        jlbDirtyTrickLeft = new javax.swing.JLabel();
        jlbMiscMayhemLeft = new javax.swing.JLabel();
        jlbSpecialPlayLeft = new javax.swing.JLabel();
        jlbGoodKarmaLeft = new javax.swing.JLabel();
        jlbRandomEventLeft = new javax.swing.JLabel();
        jlbDesperateMeasureLeft = new javax.swing.JLabel();
        jpnBonusesRight = new javax.swing.JPanel();
        jlbApothecaryRight = new javax.swing.JLabel();
        jlbCheerleadersRight = new javax.swing.JLabel();
        jlbAssistsRight = new javax.swing.JLabel();
        jlbLocalApothecaryRight = new javax.swing.JLabel();
        jlbIgorRight = new javax.swing.JLabel();
        jlbBabesRight = new javax.swing.JLabel();
        jlbBribeTheRefRight = new javax.swing.JLabel();
        jlbWizardRight = new javax.swing.JLabel();
        jlbChefRight = new javax.swing.JLabel();
        jlbMagicItemRight = new javax.swing.JLabel();
        jlbDirtyTrickRight = new javax.swing.JLabel();
        jlbMiscMayhemRight = new javax.swing.JLabel();
        jlbSpecialPlayRight = new javax.swing.JLabel();
        jlbGoodKarmaRight = new javax.swing.JLabel();
        jlbRandomEventRight = new javax.swing.JLabel();
        jlbDesperateMeasureRight = new javax.swing.JLabel();
        jpnPlayerFlying = new javax.swing.JPanel();
        jpnLeftPlayer1 = new javax.swing.JPanel();
        jpnTopLeftPlayer1 = new javax.swing.JPanel();
        jlbFlyingPlayerName = new javax.swing.JLabel();
        jlbFlyingPlayerPosition = new javax.swing.JLabel();
        jpnBottomLeftPlayer1 = new javax.swing.JPanel();
        jlbTextMovement1 = new javax.swing.JLabel();
        jlbTextStrength1 = new javax.swing.JLabel();
        jlbTextAgility1 = new javax.swing.JLabel();
        jlbTextArmor1 = new javax.swing.JLabel();
        jlbFlyingMovement = new javax.swing.JLabel();
        jlbFlyingStrength = new javax.swing.JLabel();
        jlbFlyingAgility = new javax.swing.JLabel();
        jlbFlyingArmor = new javax.swing.JLabel();
        jspCompetences1 = new javax.swing.JScrollPane();
        jlsFlyingCompetences = new javax.swing.JList();
        jpnRightDugout = new javax.swing.JPanel();
        jlbDugoutRight = new javax.swing.JLabel();
        jpnPlayer = new javax.swing.JPanel();
        jpnLeftPlayer = new javax.swing.JPanel();
        jpnTopLeftPlayer = new javax.swing.JPanel();
        jlbPlayerName = new javax.swing.JLabel();
        jlbPlayerPosition = new javax.swing.JLabel();
        jpnBottomLeftPlayer = new javax.swing.JPanel();
        jlbTextMovement = new javax.swing.JLabel();
        jlbTextStrength = new javax.swing.JLabel();
        jlbTextAgility = new javax.swing.JLabel();
        jlbTextArmor = new javax.swing.JLabel();
        jlbMovement = new javax.swing.JLabel();
        jlbStrength = new javax.swing.JLabel();
        jlbAgility = new javax.swing.JLabel();
        jlbArmor = new javax.swing.JLabel();
        jspCompetences = new javax.swing.JScrollPane();
        jlsCompetences = new javax.swing.JList();
        jlbGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Match"); // NOI18N
        setMinimumSize(new java.awt.Dimension(781, 631));
        setModal(true);
        setResizable(false);

        jpnTop.setMaximumSize(new java.awt.Dimension(781, 70));
        jpnTop.setMinimumSize(new java.awt.Dimension(781, 60));
        jpnTop.setPreferredSize(new java.awt.Dimension(781, 80));
        jpnTop.setLayout(new java.awt.BorderLayout(2, 2));

        jpnTopLeft.setPreferredSize(new java.awt.Dimension(200, 120));
        jpnTopLeft.setLayout(new java.awt.GridLayout(2, 0));

        jpnRerolls.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rerolls", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jpnRerolls.setPreferredSize(new java.awt.Dimension(50, 60));
        jpnRerolls.setLayout(new java.awt.BorderLayout());

        jlbRerollLeft.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbRerollLeft.setForeground(new java.awt.Color(255, 0, 0));
        jlbRerollLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbRerollLeft.setText("0"); // NOI18N
        jpnRerolls.add(jlbRerollLeft, java.awt.BorderLayout.WEST);

        jlbMinus2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jlbMinus2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMinus2.setText("-"); // NOI18N
        jpnRerolls.add(jlbMinus2, java.awt.BorderLayout.CENTER);

        jlbRerollRight.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbRerollRight.setForeground(new java.awt.Color(0, 0, 255));
        jlbRerollRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbRerollRight.setText("0"); // NOI18N
        jpnRerolls.add(jlbRerollRight, java.awt.BorderLayout.EAST);

        jpnTopLeft.add(jpnRerolls);

        jpnTurn.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1st Half", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jpnTurn.setPreferredSize(new java.awt.Dimension(50, 60));
        jpnTurn.setLayout(new java.awt.BorderLayout());

        jlbTurnLeft.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbTurnLeft.setForeground(new java.awt.Color(255, 0, 0));
        jlbTurnLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTurnLeft.setText("0"); // NOI18N
        jpnTurn.add(jlbTurnLeft, java.awt.BorderLayout.WEST);

        jlbMinus1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jlbMinus1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMinus1.setText("-"); // NOI18N
        jpnTurn.add(jlbMinus1, java.awt.BorderLayout.CENTER);

        jlbTurnRight.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbTurnRight.setForeground(new java.awt.Color(0, 0, 255));
        jlbTurnRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTurnRight.setText("0"); // NOI18N
        jpnTurn.add(jlbTurnRight, java.awt.BorderLayout.EAST);

        jpnTopLeft.add(jpnTurn);

        jpnScore.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Score", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jpnScore.setPreferredSize(new java.awt.Dimension(50, 60));
        jpnScore.setLayout(new java.awt.BorderLayout());

        jlbScoreLeft.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbScoreLeft.setForeground(new java.awt.Color(255, 0, 0));
        jlbScoreLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbScoreLeft.setText("0"); // NOI18N
        jpnScore.add(jlbScoreLeft, java.awt.BorderLayout.WEST);

        jlbMinus.setFont(new java.awt.Font("Tahoma", 1, 18));
        jlbMinus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMinus.setText("-"); // NOI18N
        jpnScore.add(jlbMinus, java.awt.BorderLayout.CENTER);

        jlbScoreRight.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbScoreRight.setForeground(new java.awt.Color(0, 0, 255));
        jlbScoreRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbScoreRight.setText("0"); // NOI18N
        jpnScore.add(jlbScoreRight, java.awt.BorderLayout.EAST);

        jpnTopLeft.add(jpnScore);

        jpnFAME.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FAME", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        jpnFAME.setPreferredSize(new java.awt.Dimension(50, 60));
        jpnFAME.setLayout(new java.awt.BorderLayout());

        jlbFAMELeft.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbFAMELeft.setForeground(new java.awt.Color(255, 0, 0));
        jlbFAMELeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbFAMELeft.setText("0"); // NOI18N
        jpnFAME.add(jlbFAMELeft, java.awt.BorderLayout.WEST);

        jlbMinus3.setFont(new java.awt.Font("Tahoma", 1, 18));
        jlbMinus3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMinus3.setText("-"); // NOI18N
        jpnFAME.add(jlbMinus3, java.awt.BorderLayout.CENTER);

        jlbFAMERight.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbFAMERight.setForeground(new java.awt.Color(0, 0, 255));
        jlbFAMERight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbFAMERight.setText("0"); // NOI18N
        jpnFAME.add(jlbFAMERight, java.awt.BorderLayout.EAST);

        jpnTopLeft.add(jpnFAME);

        jlbWeatherIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbWeatherIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/weather/nice.png"))); // NOI18N
        jpnTopLeft.add(jlbWeatherIcon);

        jpnAverage.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Average", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jpnAverage.setPreferredSize(new java.awt.Dimension(50, 60));
        jpnAverage.setLayout(new java.awt.BorderLayout());

        jlbTurnLeft1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbTurnLeft1.setForeground(new java.awt.Color(255, 0, 0));
        jlbTurnLeft1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTurnLeft1.setText("3,5"); // NOI18N
        jpnAverage.add(jlbTurnLeft1, java.awt.BorderLayout.WEST);

        jlbMinus4.setFont(new java.awt.Font("Tahoma", 1, 18));
        jlbMinus4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMinus4.setText("-"); // NOI18N
        jpnAverage.add(jlbMinus4, java.awt.BorderLayout.CENTER);

        jlbTurnRight1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbTurnRight1.setForeground(new java.awt.Color(0, 0, 255));
        jlbTurnRight1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTurnRight1.setText("3,5"); // NOI18N
        jpnAverage.add(jlbTurnRight1, java.awt.BorderLayout.EAST);

        jpnTopLeft.add(jpnAverage);

        jpnTop.add(jpnTopLeft, java.awt.BorderLayout.WEST);

        jpnTopRight.setPreferredSize(new java.awt.Dimension(370, 45));
        jpnTopRight.setLayout(new java.awt.BorderLayout());

        jtaLastRolls.setColumns(20);
        jtaLastRolls.setEditable(false);
        jtaLastRolls.setLineWrap(true);
        jtaLastRolls.setRows(5);
        jtaLastRolls.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jtaLastRolls);

        jpnTopRight.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(80, 120));

        jlsDiceHistorical.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jlsDiceHistorical);

        jpnTopRight.add(jScrollPane2, java.awt.BorderLayout.WEST);

        jpnTop.add(jpnTopRight, java.awt.BorderLayout.EAST);

        jpnTopMiddle.setPreferredSize(new java.awt.Dimension(159, 60));
        jpnTopMiddle.setLayout(new java.awt.BorderLayout(2, 2));

        jpnRerollPanel.setOpaque(false);
        jpnRerollPanel.setLayout(new java.awt.GridLayout(3, 1));

        jbtReroll.setFont(new java.awt.Font("Tahoma", 0, 10));
        jbtReroll.setText("Reroll"); // NOI18N
        jpnRerollPanel.add(jbtReroll);

        jbtProReroll.setFont(new java.awt.Font("Tahoma", 0, 10));
        jbtProReroll.setText("Specific"); // NOI18N
        jbtProReroll.setToolTipText("Reroll with Pro competence"); // NOI18N
        jpnRerollPanel.add(jbtProReroll);

        jbtNext.setFont(new java.awt.Font("Tahoma", 0, 10));
        jbtNext.setText("Next/End"); // NOI18N
        jbtNext.setToolTipText("Reroll with competence : XXX"); // NOI18N
        jbtNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextActionPerformed(evt);
            }
        });
        jpnRerollPanel.add(jbtNext);

        jpnTopMiddle.add(jpnRerollPanel, java.awt.BorderLayout.WEST);

        jPanel3.setPreferredSize(new java.awt.Dimension(80, 60));
        jPanel3.setLayout(new java.awt.GridLayout(3, 3));

        jtbAction1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/actions/move.gif"))); // NOI18N
        jtbAction1.setToolTipText("Movement"); // NOI18N
        jtbAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbAction1ActionPerformed(evt);
            }
        });
        jPanel3.add(jtbAction1);

        jtbAction2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/actions/block.gif"))); // NOI18N
        jtbAction2.setToolTipText("Block"); // NOI18N
        jtbAction2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbAction2ActionPerformed(evt);
            }
        });
        jPanel3.add(jtbAction2);

        jtbAction3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/actions/blitz.gif"))); // NOI18N
        jtbAction3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbAction3ActionPerformed(evt);
            }
        });
        jPanel3.add(jtbAction3);

        jtbAction4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/actions/pass.gif"))); // NOI18N
        jtbAction4.setToolTipText("Pass"); // NOI18N
        jtbAction4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbAction4ActionPerformed(evt);
            }
        });
        jPanel3.add(jtbAction4);

        jtbAction5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/actions/handoff.gif"))); // NOI18N
        jtbAction5.setToolTipText("HandOff"); // NOI18N
        jtbAction5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbAction5ActionPerformed(evt);
            }
        });
        jPanel3.add(jtbAction5);

        jtbAction6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/actions/foul.gif"))); // NOI18N
        jtbAction6.setToolTipText("Foul"); // NOI18N
        jtbAction6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbAction6ActionPerformed(evt);
            }
        });
        jPanel3.add(jtbAction6);

        jtbAction7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/actions/throwteammate.gif"))); // NOI18N
        jtbAction7.setToolTipText("Throw a team mate"); // NOI18N
        jtbAction7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbAction7ActionPerformed(evt);
            }
        });
        jPanel3.add(jtbAction7);

        jtbAction8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/actions/chainsaw.gif"))); // NOI18N
        jtbAction8.setToolTipText("Block with Chainsaw/Stab"); // NOI18N
        jtbAction8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbAction8ActionPerformed(evt);
            }
        });
        jPanel3.add(jtbAction8);

        jtbAction9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/actions/chainsawblitz.gif"))); // NOI18N
        jtbAction9.setToolTipText("Blitz with Chainsaw/Stab"); // NOI18N
        jtbAction9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbAction9ActionPerformed(evt);
            }
        });
        jPanel3.add(jtbAction9);

        jpnTopMiddle.add(jPanel3, java.awt.BorderLayout.CENTER);

        jpnTop.add(jpnTopMiddle, java.awt.BorderLayout.CENTER);

        getContentPane().add(jpnTop, java.awt.BorderLayout.NORTH);

        jpnBottom.setMaximumSize(new java.awt.Dimension(783, 61));
        jpnBottom.setMinimumSize(new java.awt.Dimension(783, 61));
        jpnBottom.setPreferredSize(new java.awt.Dimension(783, 120));
        jpnBottom.setLayout(new java.awt.BorderLayout());

        jpnLeftDugout.setMinimumSize(new java.awt.Dimension(390, 60));
        jpnLeftDugout.setLayout(new java.awt.BorderLayout());

        jlbDugoutLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDugoutLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ground/dugout.png"))); // NOI18N
        jlbDugoutLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbDugoutLeftMouseClicked(evt);
            }
        });
        jlbDugoutLeft.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlbDugoutLeftMouseMoved(evt);
            }
        });
        jpnLeftDugout.add(jlbDugoutLeft, java.awt.BorderLayout.CENTER);

        jpnBottom.add(jpnLeftDugout, java.awt.BorderLayout.WEST);

        jpnSouth.setPreferredSize(new java.awt.Dimension(781, 60));
        jpnSouth.setLayout(new java.awt.BorderLayout());

        jpnBonusesLeft.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnBonusesLeft.setMaximumSize(new java.awt.Dimension(150, 55));
        jpnBonusesLeft.setMinimumSize(new java.awt.Dimension(150, 45));
        jpnBonusesLeft.setPreferredSize(new java.awt.Dimension(300, 60));
        jpnBonusesLeft.setLayout(new java.awt.GridLayout(2, 8));

        jlbApothecaryLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbApothecaryLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/apothecary.png"))); // NOI18N
        jlbApothecaryLeft.setText("0"); // NOI18N
        jlbApothecaryLeft.setToolTipText("Apthecary : 0"); // NOI18N
        jpnBonusesLeft.add(jlbApothecaryLeft);

        jlbCheerleadersLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbCheerleadersLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/pompom.png"))); // NOI18N
        jlbCheerleadersLeft.setText("0"); // NOI18N
        jlbCheerleadersLeft.setToolTipText("Pom Pom girls : 0"); // NOI18N
        jpnBonusesLeft.add(jlbCheerleadersLeft);

        jlbAssistsLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbAssistsLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/assists.png"))); // NOI18N
        jlbAssistsLeft.setText("0"); // NOI18N
        jlbAssistsLeft.setToolTipText("Assists : 0"); // NOI18N
        jpnBonusesLeft.add(jlbAssistsLeft);

        jlbLocalApothecaryLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbLocalApothecaryLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/local.png"))); // NOI18N
        jlbLocalApothecaryLeft.setText("0"); // NOI18N
        jpnBonusesLeft.add(jlbLocalApothecaryLeft);

        jlbIgorLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbIgorLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/igor.png"))); // NOI18N
        jlbIgorLeft.setText("0"); // NOI18N
        jlbIgorLeft.setToolTipText("Igor : 0"); // NOI18N
        jpnBonusesLeft.add(jlbIgorLeft);

        jlbBabesLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbBabesLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/babes.png"))); // NOI18N
        jlbBabesLeft.setText("0"); // NOI18N
        jlbBabesLeft.setToolTipText("Bloodweiser babes : 0"); // NOI18N
        jpnBonusesLeft.add(jlbBabesLeft);

        jlbBribeTheRefLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbBribeTheRefLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/ref.png"))); // NOI18N
        jlbBribeTheRefLeft.setText("0"); // NOI18N
        jlbBribeTheRefLeft.setToolTipText("Bribe the ref : 0"); // NOI18N
        jpnBonusesLeft.add(jlbBribeTheRefLeft);

        jlbWizardLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbWizardLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/wizard.png"))); // NOI18N
        jlbWizardLeft.setText("0"); // NOI18N
        jlbWizardLeft.setToolTipText("Wizard : 0"); // NOI18N
        jpnBonusesLeft.add(jlbWizardLeft);

        jlbChefLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbChefLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/chef.png"))); // NOI18N
        jlbChefLeft.setText("0"); // NOI18N
        jlbChefLeft.setToolTipText("Halfling chef : 0"); // NOI18N
        jpnBonusesLeft.add(jlbChefLeft);

        jlbMagicItemLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMagicItemLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/magicitem.png"))); // NOI18N
        jlbMagicItemLeft.setText("0"); // NOI18N
        jlbMagicItemLeft.setToolTipText("Magic item cards : 0"); // NOI18N
        jpnBonusesLeft.add(jlbMagicItemLeft);

        jlbDirtyTrickLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDirtyTrickLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/dirtytrick.png"))); // NOI18N
        jlbDirtyTrickLeft.setText("0"); // NOI18N
        jlbDirtyTrickLeft.setToolTipText("Dirty trick card : 0"); // NOI18N
        jpnBonusesLeft.add(jlbDirtyTrickLeft);

        jlbMiscMayhemLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMiscMayhemLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/misc.png"))); // NOI18N
        jlbMiscMayhemLeft.setText("0"); // NOI18N
        jlbMiscMayhemLeft.setToolTipText("Misc. Mayhem cards : 0"); // NOI18N
        jpnBonusesLeft.add(jlbMiscMayhemLeft);

        jlbSpecialPlayLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbSpecialPlayLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/specialplay.png"))); // NOI18N
        jlbSpecialPlayLeft.setText("0"); // NOI18N
        jlbSpecialPlayLeft.setToolTipText("Special Play : 0"); // NOI18N
        jpnBonusesLeft.add(jlbSpecialPlayLeft);

        jlbGoodKarmaLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbGoodKarmaLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/goodkarma.png"))); // NOI18N
        jlbGoodKarmaLeft.setText("0"); // NOI18N
        jlbGoodKarmaLeft.setToolTipText("Good Karma : 0"); // NOI18N
        jpnBonusesLeft.add(jlbGoodKarmaLeft);

        jlbRandomEventLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbRandomEventLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/randomevent.png"))); // NOI18N
        jlbRandomEventLeft.setText("0"); // NOI18N
        jlbRandomEventLeft.setToolTipText("Random event : 0"); // NOI18N
        jpnBonusesLeft.add(jlbRandomEventLeft);

        jlbDesperateMeasureLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDesperateMeasureLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/desperatemeasure.png"))); // NOI18N
        jlbDesperateMeasureLeft.setText("0"); // NOI18N
        jlbDesperateMeasureLeft.setToolTipText("Desperate measure : 0"); // NOI18N
        jpnBonusesLeft.add(jlbDesperateMeasureLeft);

        jpnSouth.add(jpnBonusesLeft, java.awt.BorderLayout.WEST);

        jpnBonusesRight.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnBonusesRight.setMaximumSize(new java.awt.Dimension(150, 55));
        jpnBonusesRight.setMinimumSize(new java.awt.Dimension(150, 45));
        jpnBonusesRight.setPreferredSize(new java.awt.Dimension(300, 60));
        jpnBonusesRight.setLayout(new java.awt.GridLayout(2, 8));

        jlbApothecaryRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbApothecaryRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/apothecary.png"))); // NOI18N
        jlbApothecaryRight.setText("0"); // NOI18N
        jlbApothecaryRight.setToolTipText("Apthecary : 0"); // NOI18N
        jpnBonusesRight.add(jlbApothecaryRight);

        jlbCheerleadersRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbCheerleadersRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/pompom.png"))); // NOI18N
        jlbCheerleadersRight.setText("0"); // NOI18N
        jlbCheerleadersRight.setToolTipText("Pom Pom girls : 0"); // NOI18N
        jpnBonusesRight.add(jlbCheerleadersRight);

        jlbAssistsRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbAssistsRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/assists.png"))); // NOI18N
        jlbAssistsRight.setText("0"); // NOI18N
        jlbAssistsRight.setToolTipText("Assists : 0"); // NOI18N
        jpnBonusesRight.add(jlbAssistsRight);

        jlbLocalApothecaryRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbLocalApothecaryRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/local.png"))); // NOI18N
        jlbLocalApothecaryRight.setText("0"); // NOI18N
        jpnBonusesRight.add(jlbLocalApothecaryRight);

        jlbIgorRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbIgorRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/igor.png"))); // NOI18N
        jlbIgorRight.setText("0"); // NOI18N
        jlbIgorRight.setToolTipText("Igor : 0"); // NOI18N
        jpnBonusesRight.add(jlbIgorRight);

        jlbBabesRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbBabesRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/babes.png"))); // NOI18N
        jlbBabesRight.setText("0"); // NOI18N
        jlbBabesRight.setToolTipText("Bloodweiser babes : 0"); // NOI18N
        jpnBonusesRight.add(jlbBabesRight);

        jlbBribeTheRefRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbBribeTheRefRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/ref.png"))); // NOI18N
        jlbBribeTheRefRight.setText("0"); // NOI18N
        jlbBribeTheRefRight.setToolTipText("Bribe the ref : 0"); // NOI18N
        jpnBonusesRight.add(jlbBribeTheRefRight);

        jlbWizardRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbWizardRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/wizard.png"))); // NOI18N
        jlbWizardRight.setText("0"); // NOI18N
        jlbWizardRight.setToolTipText("Wizard : 0"); // NOI18N
        jpnBonusesRight.add(jlbWizardRight);

        jlbChefRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbChefRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/chef.png"))); // NOI18N
        jlbChefRight.setText("0"); // NOI18N
        jlbChefRight.setToolTipText("Halfling chef : 0"); // NOI18N
        jpnBonusesRight.add(jlbChefRight);

        jlbMagicItemRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMagicItemRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/magicitem.png"))); // NOI18N
        jlbMagicItemRight.setText("0"); // NOI18N
        jlbMagicItemRight.setToolTipText("Magic item cards : 0"); // NOI18N
        jpnBonusesRight.add(jlbMagicItemRight);

        jlbDirtyTrickRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDirtyTrickRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/dirtytrick.png"))); // NOI18N
        jlbDirtyTrickRight.setText("0"); // NOI18N
        jlbDirtyTrickRight.setToolTipText("Dirty trick card : 0"); // NOI18N
        jpnBonusesRight.add(jlbDirtyTrickRight);

        jlbMiscMayhemRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMiscMayhemRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/misc.png"))); // NOI18N
        jlbMiscMayhemRight.setText("0"); // NOI18N
        jlbMiscMayhemRight.setToolTipText("Misc. Mayhem cards : 0"); // NOI18N
        jpnBonusesRight.add(jlbMiscMayhemRight);

        jlbSpecialPlayRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbSpecialPlayRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/specialplay.png"))); // NOI18N
        jlbSpecialPlayRight.setText("0"); // NOI18N
        jlbSpecialPlayRight.setToolTipText("Special Play : 0"); // NOI18N
        jpnBonusesRight.add(jlbSpecialPlayRight);

        jlbGoodKarmaRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbGoodKarmaRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/goodkarma.png"))); // NOI18N
        jlbGoodKarmaRight.setText("0"); // NOI18N
        jlbGoodKarmaRight.setToolTipText("Good Karma : 0"); // NOI18N
        jpnBonusesRight.add(jlbGoodKarmaRight);

        jlbRandomEventRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbRandomEventRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/randomevent.png"))); // NOI18N
        jlbRandomEventRight.setText("0"); // NOI18N
        jlbRandomEventRight.setToolTipText("Random event : 0"); // NOI18N
        jpnBonusesRight.add(jlbRandomEventRight);

        jlbDesperateMeasureRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDesperateMeasureRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/desperatemeasure.png"))); // NOI18N
        jlbDesperateMeasureRight.setText("0"); // NOI18N
        jlbDesperateMeasureRight.setToolTipText("Desperate measure : 0"); // NOI18N
        jpnBonusesRight.add(jlbDesperateMeasureRight);

        jpnSouth.add(jpnBonusesRight, java.awt.BorderLayout.EAST);

        jpnPlayerFlying.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnPlayerFlying.setPreferredSize(new java.awt.Dimension(90, 60));
        jpnPlayerFlying.setLayout(new java.awt.GridLayout(1, 0));

        jpnLeftPlayer1.setLayout(new java.awt.BorderLayout());

        jpnTopLeftPlayer1.setPreferredSize(new java.awt.Dimension(90, 30));
        jpnTopLeftPlayer1.setLayout(new java.awt.GridLayout(2, 0));

        jlbFlyingPlayerName.setBackground(new java.awt.Color(255, 255, 153));
        jlbFlyingPlayerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbFlyingPlayerName.setText("Player Name"); // NOI18N
        jpnTopLeftPlayer1.add(jlbFlyingPlayerName);

        jlbFlyingPlayerPosition.setBackground(new java.awt.Color(255, 255, 153));
        jlbFlyingPlayerPosition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbFlyingPlayerPosition.setText("Player position"); // NOI18N
        jpnTopLeftPlayer1.add(jlbFlyingPlayerPosition);

        jpnLeftPlayer1.add(jpnTopLeftPlayer1, java.awt.BorderLayout.NORTH);

        jpnBottomLeftPlayer1.setPreferredSize(new java.awt.Dimension(90, 30));
        jpnBottomLeftPlayer1.setLayout(new java.awt.GridLayout(2, 4));

        jlbTextMovement1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTextMovement1.setText("Mo"); // NOI18N
        jlbTextMovement1.setToolTipText("Movement"); // NOI18N
        jpnBottomLeftPlayer1.add(jlbTextMovement1);

        jlbTextStrength1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTextStrength1.setText("St"); // NOI18N
        jlbTextStrength1.setToolTipText("Strength"); // NOI18N
        jpnBottomLeftPlayer1.add(jlbTextStrength1);

        jlbTextAgility1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTextAgility1.setText("Ag"); // NOI18N
        jlbTextAgility1.setToolTipText("Agility"); // NOI18N
        jpnBottomLeftPlayer1.add(jlbTextAgility1);

        jlbTextArmor1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTextArmor1.setText("Ar"); // NOI18N
        jlbTextArmor1.setToolTipText("Armor"); // NOI18N
        jpnBottomLeftPlayer1.add(jlbTextArmor1);

        jlbFlyingMovement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbFlyingMovement.setText("6"); // NOI18N
        jlbFlyingMovement.setToolTipText("Movement : 6"); // NOI18N
        jpnBottomLeftPlayer1.add(jlbFlyingMovement);

        jlbFlyingStrength.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbFlyingStrength.setText("3"); // NOI18N
        jlbFlyingStrength.setToolTipText("Strength : 3"); // NOI18N
        jpnBottomLeftPlayer1.add(jlbFlyingStrength);

        jlbFlyingAgility.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbFlyingAgility.setText("3"); // NOI18N
        jlbFlyingAgility.setToolTipText("Agility : 3"); // NOI18N
        jpnBottomLeftPlayer1.add(jlbFlyingAgility);

        jlbFlyingArmor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbFlyingArmor.setText("8"); // NOI18N
        jlbFlyingArmor.setToolTipText("Armor : 8"); // NOI18N
        jpnBottomLeftPlayer1.add(jlbFlyingArmor);

        jpnLeftPlayer1.add(jpnBottomLeftPlayer1, java.awt.BorderLayout.CENTER);

        jpnPlayerFlying.add(jpnLeftPlayer1);

        jlsFlyingCompetences.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Block", "Dodge" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlsFlyingCompetences.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlsFlyingCompetences.setToolTipText("Competences"); // NOI18N
        jspCompetences1.setViewportView(jlsFlyingCompetences);

        jpnPlayerFlying.add(jspCompetences1);

        jpnSouth.add(jpnPlayerFlying, java.awt.BorderLayout.CENTER);

        jpnBottom.add(jpnSouth, java.awt.BorderLayout.SOUTH);

        jpnRightDugout.setPreferredSize(new java.awt.Dimension(300, 60));
        jpnRightDugout.setLayout(new java.awt.BorderLayout());

        jlbDugoutRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDugoutRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ground/dugout.png"))); // NOI18N
        jlbDugoutRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbDugoutRightMouseClicked(evt);
            }
        });
        jlbDugoutRight.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlbDugoutRightMouseMoved(evt);
            }
        });
        jpnRightDugout.add(jlbDugoutRight, java.awt.BorderLayout.CENTER);

        jpnBottom.add(jpnRightDugout, java.awt.BorderLayout.EAST);

        jpnPlayer.setBackground(new java.awt.Color(204, 204, 204));
        jpnPlayer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnPlayer.setPreferredSize(new java.awt.Dimension(90, 60));
        jpnPlayer.setLayout(new java.awt.GridLayout(1, 0));

        jpnLeftPlayer.setBackground(new java.awt.Color(204, 204, 204));
        jpnLeftPlayer.setLayout(new java.awt.BorderLayout());

        jpnTopLeftPlayer.setPreferredSize(new java.awt.Dimension(90, 30));
        jpnTopLeftPlayer.setLayout(new java.awt.GridLayout(2, 0));

        jlbPlayerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPlayerName.setText("Player Name"); // NOI18N
        jpnTopLeftPlayer.add(jlbPlayerName);

        jlbPlayerPosition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPlayerPosition.setText("Player position"); // NOI18N
        jpnTopLeftPlayer.add(jlbPlayerPosition);

        jpnLeftPlayer.add(jpnTopLeftPlayer, java.awt.BorderLayout.NORTH);

        jpnBottomLeftPlayer.setPreferredSize(new java.awt.Dimension(90, 30));
        jpnBottomLeftPlayer.setLayout(new java.awt.GridLayout(2, 4));

        jlbTextMovement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTextMovement.setText("Mo"); // NOI18N
        jlbTextMovement.setToolTipText("Movement"); // NOI18N
        jpnBottomLeftPlayer.add(jlbTextMovement);

        jlbTextStrength.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTextStrength.setText("St"); // NOI18N
        jlbTextStrength.setToolTipText("Strength"); // NOI18N
        jpnBottomLeftPlayer.add(jlbTextStrength);

        jlbTextAgility.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTextAgility.setText("Ag"); // NOI18N
        jlbTextAgility.setToolTipText("Agility"); // NOI18N
        jpnBottomLeftPlayer.add(jlbTextAgility);

        jlbTextArmor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTextArmor.setText("Ar"); // NOI18N
        jlbTextArmor.setToolTipText("Armor"); // NOI18N
        jpnBottomLeftPlayer.add(jlbTextArmor);

        jlbMovement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbMovement.setText("6"); // NOI18N
        jlbMovement.setToolTipText("Movement : 6"); // NOI18N
        jpnBottomLeftPlayer.add(jlbMovement);

        jlbStrength.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbStrength.setText("3"); // NOI18N
        jlbStrength.setToolTipText("Strength : 3"); // NOI18N
        jpnBottomLeftPlayer.add(jlbStrength);

        jlbAgility.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbAgility.setText("3"); // NOI18N
        jlbAgility.setToolTipText("Agility : 3"); // NOI18N
        jpnBottomLeftPlayer.add(jlbAgility);

        jlbArmor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbArmor.setText("8"); // NOI18N
        jlbArmor.setToolTipText("Armor : 8"); // NOI18N
        jpnBottomLeftPlayer.add(jlbArmor);

        jpnLeftPlayer.add(jpnBottomLeftPlayer, java.awt.BorderLayout.CENTER);

        jpnPlayer.add(jpnLeftPlayer);

        jspCompetences.setBackground(new java.awt.Color(204, 204, 204));

        jlsCompetences.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Block", "Dodge" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlsCompetences.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlsCompetences.setToolTipText("Competences"); // NOI18N
        jspCompetences.setViewportView(jlsCompetences);

        jpnPlayer.add(jspCompetences);

        jpnBottom.add(jpnPlayer, java.awt.BorderLayout.CENTER);

        getContentPane().add(jpnBottom, java.awt.BorderLayout.SOUTH);

        jlbGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ground/ground.png"))); // NOI18N
        jlbGround.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jlbGround.setMaximumSize(new java.awt.Dimension(781, 431));
        jlbGround.setMinimumSize(new java.awt.Dimension(781, 431));
        jlbGround.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jlbGround.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbGroundMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jlbGroundMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlbGroundMouseReleased(evt);
            }
        });
        jlbGround.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jlbGroundMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlbGroundMouseMoved(evt);
            }
        });
        getContentPane().add(jlbGround, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jlbDugoutLeftMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbDugoutLeftMouseMoved
        int Xpos = evt.getX() / C_SQUARE_SIZE;
        int Ypos = evt.getY() / C_SQUARE_SIZE;

        int number = Xpos + Ypos * (jlbDugoutLeft.getWidth() / C_SQUARE_SIZE);

        if (number < _leftPlayers.size()) {
            rmiPlayer player = (rmiPlayer) _leftPlayers.get(number);
            try {
                if (!player.isOnPitch()) {
                    _flyingPlayer = player;
                    repaintFlyingPlayer();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        repaintFlyingPlayer();
    }//GEN-LAST:event_jlbDugoutLeftMouseMoved

    private void jlbDugoutRightMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbDugoutRightMouseMoved
        int Xpos = evt.getX() / C_SQUARE_SIZE;
        int Ypos = evt.getY() / C_SQUARE_SIZE;

        int number = Xpos + Ypos * (jlbDugoutRight.getWidth() / C_SQUARE_SIZE);

        if (number < _rightPlayers.size()) {
            rmiPlayer player = (rmiPlayer) _rightPlayers.get(number);
            try {
                if (!player.isOnPitch()) {
                    _flyingPlayer = player;
                    repaintFlyingPlayer();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jlbDugoutRightMouseMoved

    private void jlbGroundMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbGroundMouseMoved
        int Xpos = evt.getX() / C_SQUARE_SIZE;
        int Ypos = evt.getY() / C_SQUARE_SIZE;

        try {
            int number = _rightTeam.getPlayerNumber(Xpos, Ypos);
            if (number >= 0) {
                rmiPlayer player = (rmiPlayer) _rightPlayers.get(number);
                if (player.isOnPitch()) {
                    _flyingPlayer = player;
                    repaintFlyingPlayer();
                }
            } else {
                number = _leftTeam.getPlayerNumber(Xpos, Ypos);
                if (number >= 0) {
                    rmiPlayer player = (rmiPlayer) _leftPlayers.get(number);
                    if (player.isOnPitch()) {
                        _flyingPlayer = player;
                        repaintFlyingPlayer();
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jlbGroundMouseMoved

    private void jlbDugoutRightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbDugoutRightMouseClicked
        if (evt.getClickCount() == 2) {
            try {
                if (!_isChallenger || _standalone) {
                    /* If step is Match (1)- set right team on the pitch (3) */
                    if ((_model.getMainStep() == 1) && (_model.getSubStep() == 3)) {
                        int X = evt.getX() / C_SQUARE_SIZE;
                        int Y = evt.getY() / C_SQUARE_SIZE;
                        int number = X + Y * (jlbDugoutRight.getWidth() / C_SQUARE_SIZE);

                        if (number < _rightPlayers.size()) {
                            rmiPlayer player = (rmiPlayer) _rightPlayers.get(number);
                            if ((!player.isOnPitch() && (player.getState() == dPlayer.C_STATE_OK))) {
                                int nbPosY = jlbGround.getHeight() / C_SQUARE_SIZE;
                                // Compute X and Y (in square number)
                                int Ypos = number - (number / nbPosY);
                                int Xpos = (jlbGround.getWidth() / C_SQUARE_SIZE) - 1 - (number / nbPosY);
                                player.setX(Xpos);
                                player.setY(Ypos);
                                player.setOnPitch(true);
                                jbtNext.setEnabled(true);
                                refresh();
                            }
                        }
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jlbDugoutRightMouseClicked

    private void jlbDugoutLeftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbDugoutLeftMouseClicked
        if (evt.getClickCount() == 2) {
            try {
                if (_isChallenger || _standalone) {
                    /* If step is Match (1)- set left team on the pitch (2) */
                    if ((_model.getMainStep() == 1) && (_model.getSubStep() == 2)) {
                        int X = evt.getX() / C_SQUARE_SIZE;
                        int Y = evt.getY() / C_SQUARE_SIZE;
                        int number = X + Y * (jlbDugoutLeft.getWidth() / C_SQUARE_SIZE);

                        if (number < _leftPlayers.size()) {
                            rmiPlayer player = (rmiPlayer) _leftPlayers.get(number);
                            if ((!player.isOnPitch() && (player.getState() == dPlayer.C_STATE_OK))) {
                                int nbPosY = jlbGround.getHeight() / C_SQUARE_SIZE;
                                // Compute X and Y (in square number)
                                int Ypos = number - (number / nbPosY);
                                int Xpos = (number / nbPosY);
                                player.setX(Xpos);
                                player.setY(Ypos);
                                System.out.println("Number: " + Integer.toString(number) + " X: " + Integer.toString(Xpos) + " Y: " + Integer.toString(Ypos));
                                //player.setPosition(number, number);
                                player.setOnPitch(true);
                                refresh();
                                jbtNext.setEnabled(true);
                            }
                        }
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jlbDugoutLeftMouseClicked

    private void jbtNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextActionPerformed
        try {
            if (_model.getMainStep() == 1) {
                /* If step is Match (1)- set left team on the pitch (2) */
                /* Or perfect defense kick */
                if (_isChallenger || _standalone) {
                    if ((_model.getSubStep() == 2)
                            || ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 2) && (_model.getKickinkgTeam() == 1))) {
                        //Check if left team is correctly set on the pitch
                        int set = _leftTeam.isSetOnThePitch(1);
                        switch (set) {
                            case 0:
                                _leftTeam.setPlayersOnThePitch(true);
                                jbtNext.setEnabled(false);
                                break;
                            case 1:
                                // wrong players number on the pitch
                                JOptionPane.showMessageDialog(this, "Wrong player numbers on the pitch", "Error setting players on the ground", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 2:
                                // Too many players on the side
                                JOptionPane.showMessageDialog(this, "Too many player on the side", "Error setting players on the ground", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 3:
                                //not enough players on the scrimmage line
                                JOptionPane.showMessageDialog(this, "Not enough players on the scrimmage line", "Error setting players on the ground", JOptionPane.INFORMATION_MESSAGE);
                                break;
                        }
                    }

                    /* High Kick */
                    if ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 1)) {

                        for (int i = 0; i < _leftPlayers.size(); i++) {
                            rmiPlayer player = (rmiPlayer) _leftPlayers.get(i);
                            player.hasPlayed(false);
                        }
                        _model.setSubSubStep(3);
                        _model.setCurrentStepData(0);
                    }

                    /* Quick Snap */
                    if ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 3) && (_model.getKickinkgTeam() == 2)) {
                        for (int i = 0; i < _leftPlayers.size(); i++) {
                            rmiPlayer player = (rmiPlayer) _leftPlayers.get(i);
                            player.hasPlayed(false);
                        }
                        _model.setSubSubStep(3);
                        _model.setCurrentStepData(0);
                    }

                    /* BLITZ */
                    if ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 4) && (_model.getKickinkgTeam() == 1)) {
                        for (int i = 0; i < _leftPlayers.size(); i++) {
                            rmiPlayer player = (rmiPlayer) _leftPlayers.get(i);
                            player.hasPlayed(false);
                        }
                        _model.setSubSubStep(3);
                        _model.setCurrentStepData(0);
                    }

                }
                if (!_isChallenger || _standalone) {

                    /* If step is Match (1)- set right team on the pitch (3) */
                    /* Or perfect defense kick */
                    if ((_model.getSubStep() == 3)
                            || ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 2) && (_model.getKickinkgTeam() == 2))) {
                        //Check if left team is correctly set on the pitch
                        int set = _rightTeam.isSetOnThePitch(2);
                        switch (set) {
                            case 0:
                                _rightTeam.setPlayersOnThePitch(true);
                                jbtNext.setEnabled(false);
                                break;
                            case 1:
                                // wrong players number on the pitch
                                JOptionPane.showMessageDialog(this, "Wrong player numbers on the pitch", "Error setting players on the ground", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 2:
                                // Too many players on the side
                                JOptionPane.showMessageDialog(this, "Too many player on the side", "Error setting players on the ground", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 3:
                                //not enough players on the scrimmage line
                                JOptionPane.showMessageDialog(this, "Not enough players on the scrimmage line", "Error setting players on the ground", JOptionPane.INFORMATION_MESSAGE);
                                break;
                        }
                    }

                    /* Quick Snap */
                    if ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 3) && (_model.getKickinkgTeam() == 1)) {
                        for (int i = 0; i < _rightPlayers.size(); i++) {
                            rmiPlayer player = (rmiPlayer) _rightPlayers.get(i);
                            player.hasPlayed(false);
                        }
                        _model.setSubSubStep(3);
                        _model.setCurrentStepData(0);
                    }

                    /* BLITZ */
                    if ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 4) && (_model.getKickinkgTeam() == 2)) {
                        for (int i = 0; i < _rightPlayers.size(); i++) {
                            rmiPlayer player = (rmiPlayer) _rightPlayers.get(i);
                            player.hasPlayed(false);
                        }
                        _model.setSubSubStep(3);
                        _model.setCurrentStepData(0);
                    }

                    /* High Kick */
                    if ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 1)) {

                        for (int i = 0; i < _rightPlayers.size(); i++) {
                            rmiPlayer player = (rmiPlayer) _rightPlayers.get(i);
                            player.hasPlayed(false);
                        }
                        _model.setSubSubStep(3);
                        _model.setCurrentStepData(0);
                    }
                }

                // Ball placed
                if (_model.getSubStep() == 4) {
                    boolean condition = ((_model.isBallPlacedOnThePitch(2) && (_model.getKickinkgTeam() == 1))
                            || (_model.isBallPlacedOnThePitch(1) && (_model.getKickinkgTeam() == 2)));
                    if (condition) {

                        // Dispersion
                        dSquare s = _model.getBallSquare();
                        drScatter scatterRoll = new drScatter(_model, s, false, false);
                        int distance = bbTool.getd6();
                        int direction = scatterRoll.rollDices();
                        for (int i = 0; i < distance; i++) {
                            scatterRoll.applyEffects(direction);
                            s = scatterRoll.getCurrentSquare();
                        }
                        _model.removeBall();
                        _model.setWaitingBall(s);
                        _model.setBall(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Place the ball on the right half of the ground", "Error kicking ball on the ground", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                if ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 2)) {
                    _model.setSubSubStep(3);
                    _model.setCurrentStepData(0);
                }

                // Si il s'agissait du tour d'un joueur, déclaration en turnover pour passer au tour suivent
                if (_isChallenger || _standalone) {
                    if ((_model.getSubStep() == 8) && (_model.getCurrentStepData() == 1)) {
                        _model.turnover(true);
                        _model.setCurrentStepData(0);
                    }
                }
                if (!_isChallenger || _standalone) {
                    if ((_model.getSubStep() == 9) && (_model.getCurrentStepData() == 1)) {
                        _model.turnover(true);
                        _model.setCurrentStepData(0);
                    }
                }

                _selectedPlayer = null;
                _grabbedPlayer = null;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jbtNextActionPerformed

    private void jlbGroundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbGroundMouseClicked
        if (evt.getClickCount() == 2) {
            groundDoubleClick(evt);
        }

        if (evt.getClickCount() == 1) {
            groundSimpleClick(evt);
        }

    }//GEN-LAST:event_jlbGroundMouseClicked

    private void jlbGroundMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbGroundMouseDragged
        groundDrag(evt);
    }//GEN-LAST:event_jlbGroundMouseDragged

    private void jlbGroundMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbGroundMouseReleased

        try {
            if (_model.getMainStep() == 1) {
                if (_isChallenger || _standalone) {
                    /* Quick Snap */
                    if ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 3) && (_model.getKickinkgTeam() == 2)) {
                        int X = evt.getX() / C_SQUARE_SIZE;
                        int Y = evt.getY() / C_SQUARE_SIZE;
                        if (X < 13) {
                            if (!_model.isAPlayer(new dSquare(X, Y))) {
                                if ((Math.abs(X - _grabbedPlayer.getX()) < 2) && (Math.abs(Y - _grabbedPlayer.getY()) < 2)) {
                                    _grabbedPlayer.setX(X);
                                    _grabbedPlayer.setY(Y);
                                    _grabbedPlayer.hasPlayed(true);
                                    _grabbedPlayer = null;
                                }
                                refresh();
                            }
                        }
                    }
                }
                if (!_isChallenger || _standalone) {
                    /* Quick Snap */
                    if ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 3) && (_model.getKickinkgTeam() == 1)) {
                        int X = evt.getX() / C_SQUARE_SIZE;
                        int Y = evt.getY() / C_SQUARE_SIZE;
                        if (X > 12) {
                            if (!_model.isAPlayer(new dSquare(X, Y))) {
                                if ((Math.abs(X - _grabbedPlayer.getX()) < 2) && (Math.abs(Y - _grabbedPlayer.getY()) < 2)) {
                                    _grabbedPlayer.setX(X);
                                    _grabbedPlayer.setY(Y);
                                    _grabbedPlayer.hasPlayed(true);
                                    _grabbedPlayer = null;
                                }
                                refresh();
                            }
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        _grabbedPlayer = null;
    }//GEN-LAST:event_jlbGroundMouseReleased

    private void jlbGroundMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbGroundMousePressed
        try {
            if (_model.getMainStep() == 1) {
                if (_isChallenger || _standalone) {
                    /* If step is Match (1)- set left team on the pitch (2) */
                    /* Perfect Defense*/
                    if ((_model.getSubStep() == 2)
                            || ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 2) && (_model.getKickinkgTeam() == 1))) {
                        jbtNext.setEnabled(true);
                        int X = evt.getX() / C_SQUARE_SIZE;
                        int Y = evt.getY() / C_SQUARE_SIZE;
                        int number = _leftTeam.getPlayerNumber(X, Y);
                        if (number > -1) {
                            _grabbedPlayer = (rmiPlayer) _leftPlayers.get(number);
                        }
                    }

                    /* If Kickoff is QuickSnap*/
                    if ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 3) && (_model.getKickinkgTeam() == 2)) {
                        jbtNext.setEnabled(true);
                        int X = evt.getX() / C_SQUARE_SIZE;
                        int Y = evt.getY() / C_SQUARE_SIZE;
                        int number = _leftTeam.getPlayerNumber(X, Y);
                        if (number > -1) {
                            _grabbedPlayer = (rmiPlayer) _leftPlayers.get(number);
                            if (_grabbedPlayer.hasPlayed()) {
                                _grabbedPlayer = null;
                            }
                        }
                    }

                }
                if (!_isChallenger || _standalone) {
                    /* If step is Match (1)- set                        int X = evt.getX() / C_SQUARE_SIZE;
                    int Y = evt.getY() / C_SQUARE_SIZE;
                    int number = _rightTeam.getPlayerNumber(X, Y);
                    right team on the pitch (3) */
                    /* Or Reorganise defense SubStep (5) - SubSubStep 2 - Data (2) - kicking team 1*/
                    if ((_model.getSubStep() == 3)
                            || ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 2) && (_model.getKickinkgTeam() == 2))) {
                        jbtNext.setEnabled(true);
                        int X = evt.getX() / C_SQUARE_SIZE;
                        int Y = evt.getY() / C_SQUARE_SIZE;
                        int number = _rightTeam.getPlayerNumber(X, Y);
                        if (number > -1) {
                            _grabbedPlayer = (rmiPlayer) _rightPlayers.get(number);
                        }
                    }

                    /* If Kickoff is QuickSnap*/
                    if ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 3) && (_model.getKickinkgTeam() == 1)) {
                        jbtNext.setEnabled(true);
                        int X = evt.getX() / C_SQUARE_SIZE;
                        int Y = evt.getY() / C_SQUARE_SIZE;
                        int number = _rightTeam.getPlayerNumber(X, Y);
                        if (number > -1) {
                            _grabbedPlayer = (rmiPlayer) _rightPlayers.get(number);
                            if (_grabbedPlayer.hasPlayed()) {
                                _grabbedPlayer = null;
                            }
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jlbGroundMousePressed

    private void jtbAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbAction1ActionPerformed
        pressAction(0);
    }//GEN-LAST:event_jtbAction1ActionPerformed

    private void jtbAction2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbAction2ActionPerformed
        pressAction(1);
    }//GEN-LAST:event_jtbAction2ActionPerformed

    private void jtbAction3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbAction3ActionPerformed
        pressAction(2);
    }//GEN-LAST:event_jtbAction3ActionPerformed

    private void jtbAction4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbAction4ActionPerformed
        pressAction(3);
    }//GEN-LAST:event_jtbAction4ActionPerformed

    private void jtbAction5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbAction5ActionPerformed
        pressAction(4);
    }//GEN-LAST:event_jtbAction5ActionPerformed

    private void jtbAction6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbAction6ActionPerformed
        pressAction(5);
    }//GEN-LAST:event_jtbAction6ActionPerformed

    private void jtbAction7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbAction7ActionPerformed
        pressAction(6);
    }//GEN-LAST:event_jtbAction7ActionPerformed

    private void jtbAction8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbAction8ActionPerformed
        pressAction(7);
    }//GEN-LAST:event_jtbAction8ActionPerformed

    private void jtbAction9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbAction9ActionPerformed
        pressAction(8);
    }//GEN-LAST:event_jtbAction9ActionPerformed

    protected void pressAction(int index) {
        if (_selectedPlayer != null) {
            try {
                if (((_model.getSubStep() == 8) && (_isChallenger || _standalone)) || ((_model.getSubStep() == 9) && (!_isChallenger || _standalone))) {
                    if (_currentAction == null) {
                        _currentAction = (dAction) _actions.get(index);
                        _currentAction.preStep();

                        for (int i = 0; i < _actionButtons.size(); i++) {
                            if (i != index) {
                                ((JToggleButton) _actionButtons.get(i)).setEnabled(false);
                            }
                        }
                        ((JToggleButton) _actionButtons.get(index)).setSelected(true);
                        _model.AddDiary("Current action is: " + _currentAction.getName());

                    } else {
                        if (_currentAction.getStep() == 0) {
                            _currentAction.resetStep();
                            _currentAction = null;
                            for (int i = 0; i < _actionButtons.size(); i++) {
                                ((JToggleButton) _actionButtons.get(i)).setEnabled(true);
                            }
                            ((JToggleButton) _actionButtons.get(index)).setSelected(false);
                        }
                    }

                    _model.setLeftRefresh(true);
                    _model.setRightRefresh(true);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    protected void groundDrag(MouseEvent evt) {
        try {
            if (_grabbedPlayer != null) {
                if (_model.getMainStep() == 1) {
                    /* If step is Match (1)- set left team on the pitch (2) */
                    /* Or Reorganise defense SubStep (5) - SubSubStep 2 - Data (2) - kicking team 1*/
                    if (_isChallenger || _standalone) {
                        if ((_model.getSubStep() == 2)
                                || ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 2) && (_model.getKickinkgTeam() == 1))) {
                            int X = evt.getX() / C_SQUARE_SIZE;
                            int Y = evt.getY() / C_SQUARE_SIZE;
                            if (!_model.isAPlayer(new dSquare(X, Y))) {
                                _grabbedPlayer.setX(X);
                                _grabbedPlayer.setY(Y);
                            }
                            refresh();
                        }

                    }
                    if (!_isChallenger || _standalone) {

                        /* If step is Match (1)- set right team on the pitch (3) */
                        /* Or Reorganise defense SubStep (5) - SubSubStep 2 - Data (2) - kicking team 1*/
                        if ((_model.getSubStep() == 3)
                                || ((_model.getSubStep() == 5) && (_model.getSubSubStep() == 2) && (_model.getCurrentStepData() == 2) && (_model.getKickinkgTeam() == 2))) {

                            int X = evt.getX() / C_SQUARE_SIZE;
                            int Y = evt.getY() / C_SQUARE_SIZE;
                            if (!_model.isAPlayer(new dSquare(X, Y))) {
                                _grabbedPlayer.setX(X);
                                _grabbedPlayer.setY(Y);
                            }
                            refresh();
                        }

                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * Treatment of a double click on the ground 
     * @param evt
     */
    protected void groundDoubleClick(MouseEvent evt) {
        try {
            if (_model.getMainStep() == 1) {
                /* If step is Match (1) */
                if (_isChallenger || _standalone) {

                    /* set left team on the pitch SubStep(2)*/
                    if (_model.getSubStep() == 2) {
                        jbtNext.setEnabled(true);
                        int X = evt.getX() / C_SQUARE_SIZE;
                        int Y = evt.getY() / C_SQUARE_SIZE;
                        int number = _leftTeam.getPlayerNumber(X, Y);
                        if (number > -1) {
                            rmiPlayer player = (rmiPlayer) _leftPlayers.get(number);
                            if (player.isOnPitch()) {
                                player.setOnPitch(false);
                                refresh();
                            }
                        }
                    }

                }
                if (!_isChallenger || _standalone) {

                    /* set right team on the pitch (3) */
                    if (_model.getSubStep() == 3) {
                        jbtNext.setEnabled(true);
                        int X = evt.getX() / C_SQUARE_SIZE;
                        int Y = evt.getY() / C_SQUARE_SIZE;
                        int number = _rightTeam.getPlayerNumber(X, Y);
                        if (number > -1) {
                            rmiPlayer player = (rmiPlayer) _rightPlayers.get(number);
                            if (player.isOnPitch()) {
                                player.setOnPitch(false);
                                refresh();
                            }
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Treatment of a double click on the ground 
     * @param evt
     */
    protected void groundSimpleClick(MouseEvent evt) {
        try {
            int mainStep = _model.getMainStep();
            int subStep = _model.getSubStep();
            int subSubStep = _model.getSubSubStep();

            if (mainStep == 1) {
                /* If step is Match (1)- place the ball (4) */
                if (subStep == 4) {

                    int X = evt.getX() / C_SQUARE_SIZE;
                    int Y = evt.getY() / C_SQUARE_SIZE;

                    if ((_model.getKickinkgTeam() == 1) && (_isChallenger || _standalone)) {
                        if (X >= 13) {
                            _model.removeBall();
                            _model.setWaitingBall(new dSquare(X, Y));
                            jbtNext.setEnabled(true);
                        }
                        refresh();
                    }
                    if ((_model.getKickinkgTeam() == 2) && (!_isChallenger || _standalone)) {
                        if (X < 13) {
                            jbtNext.setEnabled(true);
                            _model.removeBall();
                            _model.setWaitingBall(new dSquare(X, Y));
                        }

                        refresh();
                    }
                }

                /* If step is Match (1) - SubStep KickOff (5) - SubSubStep Effects (2) - */
                if ((subStep == 5) && (subSubStep == 2)) {
                    /* If kickoff is HighKick (1)*/
                    if (_model.getCurrentStepData() == 1) {
                        int kicking = _model.getKickinkgTeam();
                        rmiTeam team = null;
                        Vector players;
                        if (kicking == 1) {
                            team = _rightTeam;
                            players = _rightPlayers;
                        } else {
                            team = _leftTeam;
                            players = _leftPlayers;
                        }
                        if ((kicking == 1) && (!_isChallenger || _standalone)) {
                            dSquare s = _model.getBallSquare();
                            int i = team.getPlayerNumber(s.getX(), s.getY());
                            if (i == -1) {
                                int X = evt.getX() / C_SQUARE_SIZE;
                                int Y = evt.getY() / C_SQUARE_SIZE;
                                i = team.getPlayerNumber(X, Y);
                                if (i > -1) {
                                    rmiPlayer player = (rmiPlayer) players.get(i);
                                    if (!player.hasPlayed()) {

                                        player.setX(s.getX());
                                        player.setY(s.getY());
                                        for (int j = 0; j < players.size(); j++) {
                                            rmiPlayer p = (rmiPlayer) players.get(j);
                                            p.hasPlayed(false);
                                        }
                                        _model.setRightRefresh(true);
                                        _model.setLeftRefresh(true);
                                        _model.setSubSubStep(3);
                                    }
                                }
                            }
                        }

                        if ((kicking == 2) && (_isChallenger || _standalone)) {
                            int X = evt.getX() / C_SQUARE_SIZE;
                            int Y = evt.getY() / C_SQUARE_SIZE;
                            int i = team.getPlayerNumber(X, Y);
                            if (i > -1) {
                                rmiPlayer player = (rmiPlayer) players.get(i);
                                if (!player.hasPlayed()) {
                                    dSquare s = _model.getBallSquare();
                                    player.setX(s.getX());
                                    player.setY(s.getY());
                                    _model.setRightRefresh(true);
                                    _model.setLeftRefresh(true);
                                    _model.setSubSubStep(3);
                                }
                            }
                        }
                    }
                }

                /* If step is Match (1)- Ball falls and bounces (6) - data (1) bad kick */
                if ((subStep == 6) && (_model.getCurrentStepData() == 1)) {
                    int kicking = _model.getKickinkgTeam();
                    rmiTeam team = null;
                    Vector players;

                    if (kicking == 1) {
                        team = _rightTeam;
                        players = _rightPlayers;
                    } else {
                        team = _leftTeam;
                        players = _leftPlayers;
                    }
                    if ((kicking == 2) && (_isChallenger || _standalone)) {
                        int X = evt.getX() / C_SQUARE_SIZE;
                        int Y = evt.getY() / C_SQUARE_SIZE;
                        int i = team.getPlayerNumber(X, Y);
                        if (i > -1) {
                            rmiPlayer player = (rmiPlayer) players.get(i);
                            player.setBall(true);
                            _model.setSubStep(7);
                        }

                    }
                    if ((kicking == 1) && (!_isChallenger || _standalone)) {
                        int X = evt.getX() / C_SQUARE_SIZE;
                        int Y = evt.getY() / C_SQUARE_SIZE;
                        int i = team.getPlayerNumber(X, Y);
                        if (i > -1) {
                            rmiPlayer player = (rmiPlayer) players.get(i);
                            player.setBall(true);
                            _model.setSubStep(7);
                        }
                    }
                }

                /* Tour */
                if ((subStep == 8) && (_isChallenger || _standalone)) {
                    manageTurnGroundClick(evt, _leftTeam, _leftPlayers);
                }

                if ((subStep == 9) && (!_isChallenger || _standalone)) {
                    manageTurnGroundClick(evt, _rightTeam, _rightPlayers);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void manageTurnGroundClick(MouseEvent evt, rmiTeam team, Vector players) {
        try {
            int X = evt.getX() / C_SQUARE_SIZE;
            int Y = evt.getY() / C_SQUARE_SIZE;

            /**
             * Si pas de joueur sélectionné
             */
            if (_selectedPlayer == null) {
                int i = team.getPlayerNumber(X, Y);
                if (i > -1) {
                    rmiPlayer player = (rmiPlayer) players.get(i);
                    if (!player.hasPlayed()) {
                        _selectedPlayer = player;
                        _selectedPlayer.isActive(true);
                        refreshActions();
                        refresh();
                    }
                }
            } else {
                /**
                 * Si joueur sélectionné
                 */
                if (_currentAction == null) {
                    /**
                     * Si pas d'action séléctionnée
                     */
                    int i = team.getPlayerNumber(X, Y);
                    if (i > -1) {
                        rmiPlayer player = (rmiPlayer) players.get(i);
                        if (!player.hasPlayed() || !player.isPlaying()) {
                            if (player == _selectedPlayer) {

                                _selectedPlayer.isActive(false);
                                _selectedPlayer = null;
                                if (_currentAction != null) {
                                    _currentAction.resetStep();
                                    _currentAction = null;
                                }
                                refreshActions();
                                refresh();
                            } else {
                                _currentAction = null;
                                _selectedPlayer.isActive(false);
                                _selectedPlayer = player;
                                _selectedPlayer.isActive(true);
                                refreshActions();
                                refresh();
                            }
                        }
                    }
                } /**
                 * Si action sélectionnée
                 */
                else {

                    if (_selectedPlayer.hasPlayed()) {
                        int i = team.getPlayerNumber(X, Y);
                        if (i > -1) {
                            rmiPlayer player = (rmiPlayer) players.get(i);
                            if (!player.hasPlayed()) {
                                _selectedPlayer = player;
                                _selectedPlayer.isActive(true);
                                refreshActions();
                                refresh();
                            }
                        }
                    } else {
                        /**
                         * Si click sur le joueur
                         */
                        if ((_selectedPlayer.getX() == X) && (_selectedPlayer.getY() == Y) && (_selectedPlayer.isPlaying())) {
                            /**
                             * Unselect player
                             */
                            _selectedPlayer.isActive(false);
                            _selectedPlayer.hasPlayed(true);
                            _selectedPlayer.isPlaying(false);

                            _model.resetSquareMove();
                            _model.resetSquareBlock();
                            _model.resetSquareFoul();
                            _model.resetSquarePass();

                            _currentAction = null;
                            _selectedPlayer = null;
                        } else {
                            /**
                             * Execution de l'action dans le rectangle
                             */
                            _currentAction.step(new dSquare(X, Y));
                            if (_currentAction.end()) {
                                _selectedPlayer.isActive(false);
                                _selectedPlayer.hasPlayed(true);
                                _selectedPlayer.isPlaying(false);

                                _selectedPlayer = null;
                                _currentAction = null;
                            }
                        }
                    }
                    refreshActions();
                    refresh();
                }
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Repaint the Actions buttons states
     */
    protected void refreshActions() {

        for (int i = 0; i < _actionButtons.size(); i++) {
            JToggleButton button = (JToggleButton) _actionButtons.get(i);
            button.setEnabled(false);
            button.setVisible(false);
            button.setIcon(null);
        }

        if (_selectedPlayer != null) {
            try {
                if (_selectedPlayer.isPlaying()) {
                    if (_currentAction != null) {
                        _actions = new Vector();
                        _actions.add(_currentAction);
                        jtbAction1.setVisible(true);
                        jtbAction1.setEnabled(false);
                        jtbAction1.setSelected(true);
                        switch (_currentAction.getAction()) {
                            case dAction.C_BLITZ:
                                jtbAction1.setToolTipText("Blitz");
                                jtbAction1.setIcon(((daBlitz) _currentAction).getIcon());
                                break;
                            case dAction.C_BLITZ_STAB:
                                jtbAction1.setToolTipText("Blitz with Stab or Chainsaw");
                                jtbAction1.setIcon(((daBlitzStab) _currentAction).getIcon());
                                break;
                            case dAction.C_BLOCK:
                                jtbAction1.setToolTipText("Block");
                                jtbAction1.setIcon(((daBlock) _currentAction).getIcon());
                                break;
                            case dAction.C_BLOCK_STAB:
                                jtbAction1.setToolTipText("Block with Stab or Chainsaw");
                                jtbAction1.setIcon(((daBlockStab) _currentAction).getIcon());
                                break;
                            case dAction.C_FANATIC:
                                jtbAction1.setToolTipText("Fanatic Move");
                                jtbAction1.setIcon(((daFanatic) _currentAction).getIcon());
                                break;
                            case dAction.C_FOUL:
                                jtbAction1.setToolTipText("Foul");
                                jtbAction1.setIcon(((daFoul) _currentAction).getIcon());
                                break;
                            case dAction.C_HANDOFF:
                                jtbAction1.setToolTipText("Handoff");
                                jtbAction1.setIcon(((daHandOff) _currentAction).getIcon());
                                break;
                            case dAction.C_MOVE:
                                jtbAction1.setToolTipText("Move");
                                jtbAction1.setIcon(((daMove) _currentAction).getIcon());
                                break;
                            case dAction.C_PASS:
                                jtbAction1.setToolTipText("Pass");
                                jtbAction1.setIcon(((daPass) _currentAction).getIcon());
                                break;
                            case dAction.C_THROW_TEAM_MATE:
                                jtbAction1.setToolTipText("Throw a team mate");
                                jtbAction1.setIcon(((daThrowTeamMate) _currentAction).getIcon());
                                break;
                            case dAction.C_HYPNOTIC_GAZE:
                                jtbAction1.setToolTipText("Hypnotic gaze");
                                jtbAction1.setIcon(((daHypnoticGaze) _currentAction).getIcon());
                                break;
                        }
                    }
                } else {
                    if (!_selectedPlayer.hasPlayed()) {
                        int[] actionsTag = _selectedPlayer.getAvailableActions();
                        _actions = new Vector();
                        for (int i = 0; i < actionsTag.length; i++) {
                            int a = actionsTag[i];
                            dAction action = null;
                            if (_isChallenger ) {
                                action = dActionsFactory.createAction(a, _model, _selectedPlayer, _rightTeam, _leftTeam, _rightPlayers, _leftPlayers, _isChallenger);
                                _actions.add(action);
                            }
                            if (!_isChallenger ) {
                                action = dActionsFactory.createAction(a, _model, _selectedPlayer, _leftTeam, _rightTeam, _leftPlayers, _rightPlayers, _isChallenger);
                                _actions.add(action);
                            }

                            JToggleButton toggle = (JToggleButton) _actionButtons.get(i);
                            toggle.setVisible(true);
                            toggle.setEnabled(true);
                            toggle.setSelected(false);
                            switch (action.getAction()) {
                                case dAction.C_BLITZ:
                                    toggle.setToolTipText("Blitz");
                                    toggle.setIcon(((daBlitz) action).getIcon());
                                    break;
                                case dAction.C_BLITZ_STAB:
                                    toggle.setToolTipText("Blitz with Stab or Chainsaw");
                                    toggle.setIcon(((daBlitzStab) action).getIcon());
                                    break;
                                case dAction.C_BLOCK:
                                    toggle.setToolTipText("Block");
                                    toggle.setIcon(((daBlock) action).getIcon());
                                    break;
                                case dAction.C_BLOCK_STAB:
                                    toggle.setToolTipText("Block with Stab or Chainsaw");
                                    toggle.setIcon(((daBlockStab) action).getIcon());
                                    break;
                                case dAction.C_FANATIC:
                                    toggle.setToolTipText("Fanatic Move");
                                    toggle.setIcon(((daFanatic) action).getIcon());
                                    break;
                                case dAction.C_FOUL:
                                    toggle.setToolTipText("Foul");
                                    toggle.setIcon(((daFoul) action).getIcon());
                                    break;
                                case dAction.C_HANDOFF:
                                    toggle.setToolTipText("Handoff");
                                    toggle.setIcon(((daHandOff) action).getIcon());
                                    break;
                                case dAction.C_MOVE:
                                    toggle.setToolTipText("Move");
                                    toggle.setIcon(((daMove) action).getIcon());
                                    break;
                                case dAction.C_PASS:
                                    toggle.setToolTipText("Pass");
                                    toggle.setIcon(((daPass) action).getIcon());
                                    break;
                                case dAction.C_THROW_TEAM_MATE:
                                    toggle.setToolTipText("Throw a team mate");
                                    toggle.setIcon(((daThrowTeamMate) action).getIcon());
                                    break;
                                case dAction.C_HYPNOTIC_GAZE:
                                    toggle.setToolTipText("Hypnotic gaze");
                                    toggle.setIcon(((daHypnoticGaze) action).getIcon());
                                    break;
                            }
                        }
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtNext;
    private javax.swing.JButton jbtProReroll;
    private javax.swing.JButton jbtReroll;
    private javax.swing.JLabel jlbAgility;
    private javax.swing.JLabel jlbApothecaryLeft;
    private javax.swing.JLabel jlbApothecaryRight;
    private javax.swing.JLabel jlbArmor;
    private javax.swing.JLabel jlbAssistsLeft;
    private javax.swing.JLabel jlbAssistsRight;
    private javax.swing.JLabel jlbBabesLeft;
    private javax.swing.JLabel jlbBabesRight;
    private javax.swing.JLabel jlbBribeTheRefLeft;
    private javax.swing.JLabel jlbBribeTheRefRight;
    private javax.swing.JLabel jlbCheerleadersLeft;
    private javax.swing.JLabel jlbCheerleadersRight;
    private javax.swing.JLabel jlbChefLeft;
    private javax.swing.JLabel jlbChefRight;
    private javax.swing.JLabel jlbDesperateMeasureLeft;
    private javax.swing.JLabel jlbDesperateMeasureRight;
    private javax.swing.JLabel jlbDirtyTrickLeft;
    private javax.swing.JLabel jlbDirtyTrickRight;
    private javax.swing.JLabel jlbDugoutLeft;
    private javax.swing.JLabel jlbDugoutRight;
    private javax.swing.JLabel jlbFAMELeft;
    private javax.swing.JLabel jlbFAMERight;
    private javax.swing.JLabel jlbFlyingAgility;
    private javax.swing.JLabel jlbFlyingArmor;
    private javax.swing.JLabel jlbFlyingMovement;
    private javax.swing.JLabel jlbFlyingPlayerName;
    private javax.swing.JLabel jlbFlyingPlayerPosition;
    private javax.swing.JLabel jlbFlyingStrength;
    private javax.swing.JLabel jlbGoodKarmaLeft;
    private javax.swing.JLabel jlbGoodKarmaRight;
    private javax.swing.JLabel jlbGround;
    private javax.swing.JLabel jlbIgorLeft;
    private javax.swing.JLabel jlbIgorRight;
    private javax.swing.JLabel jlbLocalApothecaryLeft;
    private javax.swing.JLabel jlbLocalApothecaryRight;
    private javax.swing.JLabel jlbMagicItemLeft;
    private javax.swing.JLabel jlbMagicItemRight;
    private javax.swing.JLabel jlbMinus;
    private javax.swing.JLabel jlbMinus1;
    private javax.swing.JLabel jlbMinus2;
    private javax.swing.JLabel jlbMinus3;
    private javax.swing.JLabel jlbMinus4;
    private javax.swing.JLabel jlbMiscMayhemLeft;
    private javax.swing.JLabel jlbMiscMayhemRight;
    private javax.swing.JLabel jlbMovement;
    private javax.swing.JLabel jlbPlayerName;
    private javax.swing.JLabel jlbPlayerPosition;
    private javax.swing.JLabel jlbRandomEventLeft;
    private javax.swing.JLabel jlbRandomEventRight;
    private javax.swing.JLabel jlbRerollLeft;
    private javax.swing.JLabel jlbRerollRight;
    private javax.swing.JLabel jlbScoreLeft;
    private javax.swing.JLabel jlbScoreRight;
    private javax.swing.JLabel jlbSpecialPlayLeft;
    private javax.swing.JLabel jlbSpecialPlayRight;
    private javax.swing.JLabel jlbStrength;
    private javax.swing.JLabel jlbTextAgility;
    private javax.swing.JLabel jlbTextAgility1;
    private javax.swing.JLabel jlbTextArmor;
    private javax.swing.JLabel jlbTextArmor1;
    private javax.swing.JLabel jlbTextMovement;
    private javax.swing.JLabel jlbTextMovement1;
    private javax.swing.JLabel jlbTextStrength;
    private javax.swing.JLabel jlbTextStrength1;
    private javax.swing.JLabel jlbTurnLeft;
    private javax.swing.JLabel jlbTurnLeft1;
    private javax.swing.JLabel jlbTurnRight;
    private javax.swing.JLabel jlbTurnRight1;
    private javax.swing.JLabel jlbWeatherIcon;
    private javax.swing.JLabel jlbWizardLeft;
    private javax.swing.JLabel jlbWizardRight;
    private javax.swing.JList jlsCompetences;
    private javax.swing.JList jlsDiceHistorical;
    private javax.swing.JList jlsFlyingCompetences;
    private javax.swing.JPanel jpnAverage;
    private javax.swing.JPanel jpnBonusesLeft;
    private javax.swing.JPanel jpnBonusesRight;
    private javax.swing.JPanel jpnBottom;
    private javax.swing.JPanel jpnBottomLeftPlayer;
    private javax.swing.JPanel jpnBottomLeftPlayer1;
    private javax.swing.JPanel jpnFAME;
    private javax.swing.JPanel jpnLeftDugout;
    private javax.swing.JPanel jpnLeftPlayer;
    private javax.swing.JPanel jpnLeftPlayer1;
    private javax.swing.JPanel jpnPlayer;
    private javax.swing.JPanel jpnPlayerFlying;
    private javax.swing.JPanel jpnRerollPanel;
    private javax.swing.JPanel jpnRerolls;
    private javax.swing.JPanel jpnRightDugout;
    private javax.swing.JPanel jpnScore;
    private javax.swing.JPanel jpnSouth;
    private javax.swing.JPanel jpnTop;
    private javax.swing.JPanel jpnTopLeft;
    private javax.swing.JPanel jpnTopLeftPlayer;
    private javax.swing.JPanel jpnTopLeftPlayer1;
    private javax.swing.JPanel jpnTopMiddle;
    private javax.swing.JPanel jpnTopRight;
    private javax.swing.JPanel jpnTurn;
    private javax.swing.JScrollPane jspCompetences;
    private javax.swing.JScrollPane jspCompetences1;
    private javax.swing.JTextArea jtaLastRolls;
    private javax.swing.JToggleButton jtbAction1;
    private javax.swing.JToggleButton jtbAction2;
    private javax.swing.JToggleButton jtbAction3;
    private javax.swing.JToggleButton jtbAction4;
    private javax.swing.JToggleButton jtbAction5;
    private javax.swing.JToggleButton jtbAction6;
    private javax.swing.JToggleButton jtbAction7;
    private javax.swing.JToggleButton jtbAction8;
    private javax.swing.JToggleButton jtbAction9;
    // End of variables declaration//GEN-END:variables

    protected void paintMatchData() {
        try {
            /*
             * Meteo 
             */
            switch (_model.getMeteo()) {
                case dMeteo.METEO_NICE:
                    jlbWeatherIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/weather/nice.png")));
                    jlbWeatherIcon.setToolTipText("Weather: Nice");
                    break;
                case dMeteo.METEO_BLIZZARD:
                    jlbWeatherIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/weather/blizzard.png")));
                    jlbWeatherIcon.setToolTipText("Weather: Blizzard");
                    break;
                case dMeteo.METEO_HEAT:
                    jlbWeatherIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/weather/heat.png")));
                    jlbWeatherIcon.setToolTipText("Weather: Heat");
                    break;
                case dMeteo.METEO_RAIN:
                    jlbWeatherIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/weather/rain.png")));
                    jlbWeatherIcon.setToolTipText("Weather: Rain");
                    break;
                case dMeteo.METEO_SUNNY:
                    jlbWeatherIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/weather/sunny.png")));
                    jlbWeatherIcon.setToolTipText("Weather: Sunny");
                    break;
            }

            /*
             * Turn counter
             */
            jlbTurnLeft.setText(Integer.toString(_leftTeam.getTurn()));
            jlbTurnRight.setText(Integer.toString(_rightTeam.getTurn()));
            if (_model.getHalf() == 1) {
                jpnTurn.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1st Half", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
            } else {
                jpnTurn.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2nd Half", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
            }

            /*
             * Score
             */
            jlbScoreLeft.setText(Integer.toString(_leftTeam.getScore()));
            jlbScoreRight.setText(Integer.toString(_rightTeam.getScore()));


            /*
             * Reroll
             */
            jlbRerollLeft.setText(Integer.toString(_leftTeam.getRerollLeft()));
            jlbRerollRight.setText(Integer.toString(_rightTeam.getRerollLeft()));

            /*
             * FAME
             */
            jlbFAMELeft.setText(Integer.toString(_leftTeam.getFAME()));
            jlbFAMERight.setText(Integer.toString(_rightTeam.getFAME()));

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * This function display the bonuses panels values.
     */
    protected void paintBonuses() {
        try {
            /***********************
             * LEFT
             ***********************/
            /* Assists */
            int assists = _leftTeam.getAssists();
            if (assists > 0) {
                jlbAssistsLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/assists.png")));

            } else {
                jlbAssistsLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/assists.png")));
            }

            jlbAssistsLeft.setText(Integer.toString(assists));
            jlbAssistsLeft.setToolTipText("Assists: " + Integer.toString(assists));

            /* Cheerleaders */
            int cheers = _leftTeam.getPomspoms();
            if (cheers > 0) {
                jlbCheerleadersLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/pompom.png")));

            } else {
                jlbCheerleadersLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/pompom.png")));
            }

            jlbCheerleadersLeft.setText(Integer.toString(cheers));
            jlbCheerleadersLeft.setToolTipText("Cheerleaders: " + Integer.toString(cheers));

            /* Inducements */
            int wizard = 0;
            int localApothecary = 0;
            int igor = 0;
            int bribe = 0;
            int chef = 0;
            int babes = 0;
            int miscmahyem = 0;
            int magicItem = 0;
            int goodkarma = 0;
            int randomevent = 0;
            int dirtytrick = 0;
            int desperateMeasure = 0;
            int specialPlay = 0;

            for (int i = 0; i
                    < _leftInducements.size(); i++) {
                dInducement induc = (dInducement) _leftInducements.get(i);
                if (!induc.isUsed()) {
                    int type = induc.getType();
                    switch (type) {
                        case dInducement.C_WIZARD:
                            wizard++;
                            break;
                        case dInducement.C_LOCAL_APOTHECARY:
                            localApothecary++;
                            break;
                        case dInducement.C_IGOR:
                            igor++;
                            break;
                        case dInducement.C_HALFLING_CHEF:
                            chef++;
                            break;
                        case dInducement.C_BRIBE_THE_REF:
                            bribe++;
                            break;
                        case dInducement.C_BLOODWEISER_BABE:
                            babes++;
                            break;
                        case dInducement.C_CARD:
                            int cardType = ((diCard) induc).getCardType();
                            switch (cardType) {
                                case diCardFactory.C_DESPERATE_MEASURE:
                                    desperateMeasure++;
                                    break;
                                case diCardFactory.C_DIRTY_TRICK:
                                    dirtytrick++;
                                    break;
                                case diCardFactory.C_GOOD_KARMA:
                                    goodkarma++;
                                    break;
                                case diCardFactory.C_MAGIC_ITEM:
                                    magicItem++;
                                    break;
                                case diCardFactory.C_MISC_MAYHEM:
                                    miscmahyem++;
                                    break;
                                case diCardFactory.C_RANDOM_EVENT:
                                    randomevent++;
                                    break;
                                case diCardFactory.C_SPECIAL_PLAY:
                                    specialPlay++;
                                    break;
                            }

                            break;
                    }

                }
            }

            /* Wizard */
            if (wizard > 0) {
                jlbWizardLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/wizard.png")));

            } else {
                jlbWizardLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/wizard.png")));
            }

            jlbWizardLeft.setText(Integer.toString(wizard));
            jlbWizardLeft.setToolTipText("Wizard: " + Integer.toString(wizard));

            /* Local Apothecary*/
            if (localApothecary > 0) {
                jlbLocalApothecaryLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/local.png")));

            } else {
                jlbLocalApothecaryLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/local.png")));
            }

            jlbLocalApothecaryLeft.setText(Integer.toString(localApothecary));
            jlbLocalApothecaryLeft.setToolTipText("Local apothecary: " + Integer.toString(localApothecary));

            /* Igor */
            if (igor > 0) {
                jlbIgorLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/igor.png")));

            } else {
                jlbIgorLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/igor.png")));
            }

            jlbIgorLeft.setText(Integer.toString(igor));
            jlbIgorLeft.setToolTipText("Igor: " + Integer.toString(igor));

            /* Bribe the ref */
            if (bribe > 0) {
                jlbBribeTheRefLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/ref.png")));

            } else {
                jlbBribeTheRefLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/ref.png")));
            }

            jlbBribeTheRefLeft.setText(Integer.toString(bribe));
            jlbBribeTheRefLeft.setToolTipText("Bribe the referee: " + Integer.toString(bribe));

            /* Chef */
            if (chef > 0) {
                jlbChefLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/chef.png")));

            } else {
                jlbChefLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/chef.png")));
            }

            jlbChefLeft.setText(Integer.toString(chef));
            jlbChefLeft.setToolTipText("Halfling Chef: " + Integer.toString(chef));

            /* Babes */
            if (babes > 0) {
                jlbBabesLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/babes.png")));

            } else {
                jlbBabesLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/babes.png")));
            }

            jlbBabesLeft.setText(Integer.toString(babes));
            jlbBabesLeft.setToolTipText("Bloodweiser babes: " + Integer.toString(babes));

            /* Misc Mahyem */
            if (miscmahyem > 0) {
                jlbMiscMayhemLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/misc.png")));

            } else {
                jlbMiscMayhemLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/misc.png")));
            }

            jlbMiscMayhemLeft.setText(Integer.toString(miscmahyem));
            jlbMiscMayhemLeft.setToolTipText("Misc. Mayhem: " + Integer.toString(miscmahyem));

            /* Magic Item */
            if (magicItem > 0) {
                jlbMagicItemLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/magicitem.png")));

            } else {
                jlbMagicItemLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/magicitem.png")));
            }

            jlbMagicItemLeft.setText(Integer.toString(magicItem));
            jlbMagicItemLeft.setToolTipText("Magic item: " + Integer.toString(magicItem));

            /* Good Karma */
            if (goodkarma > 0) {
                jlbGoodKarmaLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/goodkarma.png")));

            } else {
                jlbGoodKarmaLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/goodkarma.png")));
            }

            jlbGoodKarmaLeft.setText(Integer.toString(goodkarma));
            jlbGoodKarmaLeft.setToolTipText("Good Karma: " + Integer.toString(goodkarma));

            /* Random event */
            if (randomevent > 0) {
                jlbRandomEventLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/randomevent.png")));

            } else {
                jlbRandomEventLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/randomevent.png")));
            }

            jlbRandomEventLeft.setText(Integer.toString(randomevent));
            jlbRandomEventLeft.setToolTipText("Random event: " + Integer.toString(randomevent));

            /* Dirty trick */
            if (dirtytrick > 0) {
                jlbDirtyTrickLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/dirtytrick.png")));

            } else {
                jlbDirtyTrickLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/dirtytrick.png")));
            }

            jlbDirtyTrickLeft.setText(Integer.toString(dirtytrick));
            jlbDirtyTrickLeft.setToolTipText("Random event: " + Integer.toString(dirtytrick));

            /* Desperate measures */
            if (desperateMeasure > 0) {
                jlbDesperateMeasureLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/desperatemeasure.png")));

            } else {
                jlbDesperateMeasureLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/desperatemeasure.png")));
            }

            jlbDesperateMeasureLeft.setText(Integer.toString(desperateMeasure));
            jlbDesperateMeasureLeft.setToolTipText("Desperate measure: " + Integer.toString(desperateMeasure));

            /*Special Play */
            if (specialPlay > 0) {
                jlbSpecialPlayLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/specialplay.png")));

            } else {
                jlbSpecialPlayLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/specialplay.png")));
            }

            jlbSpecialPlayLeft.setText(Integer.toString(specialPlay));
            jlbSpecialPlayLeft.setToolTipText("Desperate measure: " + Integer.toString(specialPlay));

            /* Apothecary */
            if (_leftTeam.hasApothecary()) {
                jlbApothecaryLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/apothecary.png")));
                jlbApothecaryLeft.setText("1");
                jlbApothecaryLeft.setToolTipText("Apothecary : 1");
            } else {
                jlbApothecaryLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/apothecary.png")));
                jlbApothecaryLeft.setText("0");
                jlbApothecaryLeft.setToolTipText("Apothecary : 0");
            }

            /***********************
             * RIGHT
             ***********************/
            /* Assists */
            assists = _rightTeam.getAssists();
            if (assists > 0) {
                jlbAssistsRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/assists.png")));

            } else {
                jlbAssistsRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/assists.png")));
            }

            jlbAssistsRight.setText(Integer.toString(assists));
            jlbAssistsRight.setToolTipText("Assists: " + Integer.toString(assists));

            /* Cheerleaders */
            cheers =
                    _rightTeam.getPomspoms();
            if (cheers > 0) {
                jlbCheerleadersRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/pompom.png")));

            } else {
                jlbCheerleadersRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/pompom.png")));
            }

            jlbCheerleadersRight.setText(Integer.toString(cheers));
            jlbCheerleadersRight.setToolTipText("Cheerleaders: " + Integer.toString(cheers));

            /* Inducements */
            wizard =
                    0;
            localApothecary =
                    0;
            igor =
                    0;
            bribe =
                    0;
            chef =
                    0;
            babes =
                    0;
            miscmahyem =
                    0;
            magicItem =
                    0;
            goodkarma =
                    0;
            randomevent =
                    0;
            dirtytrick =
                    0;
            desperateMeasure =
                    0;
            specialPlay =
                    0;

            for (int i = 0; i
                    < _rightInducements.size(); i++) {
                dInducement induc = (dInducement) _rightInducements.get(i);
                if (!induc.isUsed()) {
                    int type = induc.getType();
                    switch (type) {
                        case dInducement.C_WIZARD:
                            wizard++;
                            break;
                        case dInducement.C_LOCAL_APOTHECARY:
                            localApothecary++;
                            break;
                        case dInducement.C_IGOR:
                            igor++;
                            break;
                        case dInducement.C_HALFLING_CHEF:
                            chef++;
                            break;
                        case dInducement.C_BRIBE_THE_REF:
                            bribe++;
                            break;
                        case dInducement.C_BLOODWEISER_BABE:
                            babes++;
                            break;
                        case dInducement.C_CARD:
                            int cardType = ((diCard) induc).getCardType();
                            switch (cardType) {
                                case diCardFactory.C_DESPERATE_MEASURE:
                                    desperateMeasure++;
                                    break;
                                case diCardFactory.C_DIRTY_TRICK:
                                    dirtytrick++;
                                    break;
                                case diCardFactory.C_GOOD_KARMA:
                                    goodkarma++;
                                    break;
                                case diCardFactory.C_MAGIC_ITEM:
                                    magicItem++;
                                    break;
                                case diCardFactory.C_MISC_MAYHEM:
                                    miscmahyem++;
                                    break;
                                case diCardFactory.C_RANDOM_EVENT:
                                    randomevent++;
                                    break;
                                case diCardFactory.C_SPECIAL_PLAY:
                                    specialPlay++;
                                    break;
                            }

                            break;
                    }

                }
            }

            /* Wizard */
            if (wizard > 0) {
                jlbWizardRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/wizard.png")));

            } else {
                jlbWizardRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/wizard.png")));
            }

            jlbWizardRight.setText(Integer.toString(wizard));
            jlbWizardRight.setToolTipText("Wizard: " + Integer.toString(wizard));

            /* Local Apothecary*/
            if (localApothecary > 0) {
                jlbLocalApothecaryRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/local.png")));

            } else {
                jlbLocalApothecaryRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/local.png")));
            }

            jlbLocalApothecaryRight.setText(Integer.toString(localApothecary));
            jlbLocalApothecaryRight.setToolTipText("Local apothecary: " + Integer.toString(localApothecary));

            /* Igor */
            if (igor > 0) {
                jlbIgorRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/igor.png")));

            } else {
                jlbIgorRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/igor.png")));
            }

            jlbIgorRight.setText(Integer.toString(igor));
            jlbIgorRight.setToolTipText("Igor: " + Integer.toString(igor));

            /* Bribe the ref */
            if (bribe > 0) {
                jlbBribeTheRefRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/ref.png")));

            } else {
                jlbBribeTheRefRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/ref.png")));
            }

            jlbBribeTheRefRight.setText(Integer.toString(bribe));
            jlbBribeTheRefRight.setToolTipText("Bribe the referee: " + Integer.toString(bribe));

            /* Chef */
            if (chef > 0) {
                jlbChefRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/chef.png")));

            } else {
                jlbChefRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/chef.png")));
            }

            jlbChefRight.setText(Integer.toString(chef));
            jlbChefRight.setToolTipText("Halfling Chef: " + Integer.toString(chef));

            /* Babes */
            if (babes > 0) {
                jlbBabesRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/babes.png")));

            } else {
                jlbBabesRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/babes.png")));
            }

            jlbBabesRight.setText(Integer.toString(babes));
            jlbBabesRight.setToolTipText("Bloodweiser babes: " + Integer.toString(babes));

            /* Misc Mahyem */
            if (miscmahyem > 0) {
                jlbMiscMayhemRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/misc.png")));

            } else {
                jlbMiscMayhemRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/misc.png")));
            }

            jlbMiscMayhemRight.setText(Integer.toString(miscmahyem));
            jlbMiscMayhemRight.setToolTipText("Misc. Mayhem: " + Integer.toString(miscmahyem));

            /* Magic Item */
            if (magicItem > 0) {
                jlbMagicItemRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/magicitem.png")));

            } else {
                jlbMagicItemRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/magicitem.png")));
            }

            jlbMagicItemRight.setText(Integer.toString(magicItem));
            jlbMagicItemRight.setToolTipText("Magic item: " + Integer.toString(magicItem));

            /* Good Karma */
            if (goodkarma > 0) {
                jlbGoodKarmaRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/goodkarma.png")));

            } else {
                jlbGoodKarmaRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/goodkarma.png")));
            }

            jlbGoodKarmaRight.setText(Integer.toString(goodkarma));
            jlbGoodKarmaRight.setToolTipText("Good Karma: " + Integer.toString(goodkarma));

            /* Random event */
            if (randomevent > 0) {
                jlbRandomEventRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/randomevent.png")));

            } else {
                jlbRandomEventRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/randomevent.png")));
            }

            jlbRandomEventRight.setText(Integer.toString(randomevent));
            jlbRandomEventRight.setToolTipText("Random event: " + Integer.toString(randomevent));

            /* Dirty trick */
            if (dirtytrick > 0) {
                jlbDirtyTrickRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/dirtytrick.png")));

            } else {
                jlbDirtyTrickRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/dirtytrick.png")));
            }

            jlbDirtyTrickRight.setText(Integer.toString(dirtytrick));
            jlbDirtyTrickRight.setToolTipText("Random event: " + Integer.toString(dirtytrick));

            /* Desperate measures */
            if (desperateMeasure > 0) {
                jlbDesperateMeasureRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/desperatemeasure.png")));

            } else {
                jlbDesperateMeasureRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/desperatemeasure.png")));
            }

            jlbDesperateMeasureRight.setText(Integer.toString(desperateMeasure));
            jlbDesperateMeasureRight.setToolTipText("Desperate measure: " + Integer.toString(desperateMeasure));

            /*Special Play */
            if (specialPlay > 0) {
                jlbSpecialPlayRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/specialplay.png")));

            } else {
                jlbSpecialPlayRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/specialplay.png")));
            }

            jlbSpecialPlayRight.setText(Integer.toString(specialPlay));
            jlbSpecialPlayRight.setToolTipText("Desperate measure: " + Integer.toString(specialPlay));

            /* Apothecary */
            if (_rightTeam.hasApothecary()) {
                jlbApothecaryRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/active/apothecary.png")));
                jlbApothecaryRight.setText("1");
                jlbApothecaryRight.setToolTipText("Apothecary : 1");
            } else {
                jlbApothecaryRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bonuses/passive/apothecary.png")));
                jlbApothecaryRight.setText("0");
                jlbApothecaryRight.setToolTipText("Apothecary : 0");
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    protected void paintPlayers() {
        /* Left Players*/
        for (int i = 0; i
                < _leftPlayers.size(); i++) {
            rmiPlayer player = (rmiPlayer) _leftPlayers.get(i);
            paintPlayer(player, i, true);
        }

        /* Left Players*/
        for (int i = 0; i
                < _rightPlayers.size(); i++) {
            rmiPlayer player = (rmiPlayer) _rightPlayers.get(i);
            paintPlayer(player, i, false);
        }

    }

    protected void paintPlayer(rmiPlayer player, int i, boolean left) {
        try {
            if (player.isOnPitch()) {
                /*
                 * Draw player on the pitch
                 */
                Graphics g = jlbGround.getGraphics();
                Graphics2D g2 = (Graphics2D) g;
                Image photo;

                if (player.isActive()) {
                    photo = ((ImageIcon) _activeIcons.get(player)).getImage();
                } else {
                    photo = ((ImageIcon) _passiveIcons.get(player)).getImage();
                }

                int width = photo.getWidth(this);
                int height = photo.getHeight(this);
                int Y = player.getY();
                int X = player.getX();
                if (player.hasPlayed()) {
                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.4f));
                }

                g2.drawImage(photo, X * C_SQUARE_SIZE + (C_SQUARE_SIZE - width) / 2, Y * C_SQUARE_SIZE + (C_SQUARE_SIZE - height) / 2, this);
                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));

                ImageIcon state_image = dPlayer.getStateIcon(player.getState());
                if (state_image != null) {
                    g2.drawImage(state_image.getImage(), X * C_SQUARE_SIZE, Y * C_SQUARE_SIZE, this);
                }

                if (player.hasBall()) {
                    Image ball_image = dPlayer.getBallIcon().getImage();
                    width = ball_image.getWidth(this);
                    height = ball_image.getHeight(this);
                    if (ball_image != null) {
                        g2.drawImage(ball_image, X * C_SQUARE_SIZE + (C_SQUARE_SIZE - width) / 2, Y * C_SQUARE_SIZE + (C_SQUARE_SIZE - height) / 2, this);
                    }
                }

                if (player.hasBomb()) {
                    ImageIcon bomb_image = dPlayer.getBombIcon();
                    if (bomb_image != null) {
                        g2.drawImage(bomb_image.getImage(), X * C_SQUARE_SIZE, Y * C_SQUARE_SIZE, this);
                    }
                }

                if (_selectedPlayer == player) {
                    if (_currentAction != null) {
                        int action = _currentAction.getAction();
                        int remainingMove = 0;
                        int additionalMove = 0;

                        switch (action) {
                            case dAction.C_BLITZ:
                                daBlitz blitz = (daBlitz) _currentAction;
                                remainingMove = blitz.getAvailableMove();
                                if (remainingMove > 0) {
                                    g.setColor(new Color(240, 240, 20));
                                    g.drawString("" + remainingMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);

                                } else {
                                    additionalMove = blitz.getAdditionalMove();
                                    g.setColor(new Color(240, 20, 20));
                                    g.drawString("" + additionalMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);
                                }
                                break;
                            case dAction.C_BLITZ_STAB:
                                daBlitzStab blitz_stab = (daBlitzStab) _currentAction;
                                remainingMove = blitz_stab.getAvailableMove();
                                if (remainingMove > 0) {
                                    g.setColor(new Color(240, 240, 20));
                                    g.drawString("" + remainingMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);

                                } else {
                                    additionalMove = blitz_stab.getAdditionalMove();
                                    g.setColor(new Color(240, 20, 20));
                                    g.drawString("" + additionalMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);
                                }
                                break;
                            case dAction.C_FANATIC:
                                daFanatic fanatic = (daFanatic) _currentAction;
                                remainingMove = fanatic.getAvailableMove();
                                if (remainingMove > 0) {
                                    g.setColor(new Color(240, 240, 20));
                                    g.drawString("" + remainingMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);

                                } else {
                                    additionalMove = fanatic.getAdditionalMove();
                                    g.setColor(new Color(240, 20, 20));
                                    g.drawString("" + additionalMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);
                                }
                                break;
                            case dAction.C_FOUL:
                                daFoul foul = (daFoul) _currentAction;
                                remainingMove = foul.getAvailableMove();
                                if (remainingMove > 0) {
                                    g.setColor(new Color(240, 240, 20));
                                    g.drawString("" + remainingMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);

                                } else {
                                    additionalMove = foul.getAdditionalMove();
                                    g.setColor(new Color(240, 20, 20));
                                    g.drawString("" + additionalMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);
                                }
                                break;
                            case dAction.C_HANDOFF:
                                daHandOff handoff = (daHandOff) _currentAction;
                                remainingMove = handoff.getAvailableMove();
                                if (remainingMove > 0) {
                                    g.setColor(new Color(240, 240, 20));
                                    g.drawString("" + remainingMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);

                                } else {
                                    additionalMove = handoff.getAdditionalMove();
                                    g.setColor(new Color(240, 20, 20));
                                    g.drawString("" + additionalMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);
                                }
                                break;
                            case dAction.C_HYPNOTIC_GAZE:
                                daHypnoticGaze hypno = (daHypnoticGaze) _currentAction;
                                remainingMove = hypno.getAvailableMove();
                                if (remainingMove > 0) {
                                    g.setColor(new Color(240, 240, 20));
                                    g.drawString("" + remainingMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);

                                } else {
                                    additionalMove = hypno.getAdditionalMove();
                                    g.setColor(new Color(240, 20, 20));
                                    g.drawString("" + additionalMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);
                                }
                                break;
                            case dAction.C_MOVE:
                                daMove move = (daMove) _currentAction;
                                remainingMove = move.getAvailableMove();
                                if (remainingMove > 0) {
                                    g.setColor(new Color(240, 240, 20));
                                    g.drawString("" + remainingMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);

                                } else {
                                    additionalMove = move.getAdditionalMove();
                                    g.setColor(new Color(240, 20, 20));
                                    g.drawString("" + additionalMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);
                                }
                                break;
                            case dAction.C_PASS:
                                daPass pass = (daPass) _currentAction;
                                remainingMove = pass.getAvailableMove();
                                if (remainingMove > 0) {
                                    g.setColor(new Color(240, 240, 20));
                                    g.drawString("" + remainingMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);

                                } else {
                                    additionalMove = pass.getAdditionalMove();
                                    g.setColor(new Color(240, 20, 20));
                                    g.drawString("" + additionalMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);
                                }
                                break;
                            case dAction.C_THROW_TEAM_MATE:
                                daThrowTeamMate throwTeamMate = (daThrowTeamMate) _currentAction;
                                remainingMove = throwTeamMate.getAvailableMove();
                                if (remainingMove > 0) {
                                    g.setColor(new Color(240, 240, 20));
                                    g.drawString("" + remainingMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);

                                } else {
                                    additionalMove = throwTeamMate.getAdditionalMove();
                                    g.setColor(new Color(240, 20, 20));
                                    g.drawString("" + additionalMove, X * C_SQUARE_SIZE + 2, (Y + 1) * C_SQUARE_SIZE - 2);
                                }
                                break;
                            case dAction.C_BLOCK:
                            case dAction.C_BLOCK_STAB:
                                break;
                        }
                    }
                }


            } else {
                /*
                 * Draw player in the dugout 
                 */
                Graphics g;
                int maxWidth;
                if (left) {
                    g = jlbDugoutLeft.getGraphics();
                    maxWidth =
                            jlbDugoutLeft.getWidth();
                } else {
                    g = jlbDugoutRight.getGraphics();
                    maxWidth =
                            jlbDugoutRight.getWidth();
                }

                Image photo;
                photo =
                        ((ImageIcon) _passiveIcons.get(player)).getImage();
                int width = photo.getWidth(this);
                int height = photo.getHeight(this);
                int nbPosX = maxWidth / C_SQUARE_SIZE;
                // Compute X and Y (in square number)
                int Y = (i / nbPosX);
                int X = (i - (i / nbPosX) * nbPosX);
                g.drawImage(photo, X * C_SQUARE_SIZE + (C_SQUARE_SIZE - width) / 2, Y * C_SQUARE_SIZE + (C_SQUARE_SIZE - height) / 2, this);
                ImageIcon state_image = dPlayer.getStateIcon(player.getState());
                if (state_image != null) {
                    g.drawImage(state_image.getImage(), X * C_SQUARE_SIZE, Y * C_SQUARE_SIZE, this);
                }

            }
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }

    }

    public void resetSelections() {
        _selectedPlayer = null;
        _currentAction = null;

    }

    protected void refresh() {
        try {
            _model.setRightRefresh(true);
            _model.setLeftRefresh(true);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);

        jlsDiceHistorical.repaint();

        paintActions();

        paintMatchData();
        paintPlayers();
        paintSquares();
        paintDiary();
        paintButtons();
    }

    protected void paintActions() {
        try {
            if (_selectedPlayer != null) {
                if (_selectedPlayer.hasPlayed()) {
                    _currentAction = null;
                }
            }

            if (_currentAction != null) {
                if (_currentAction instanceof daBlock) {
                    try {
                        daBlock block = (daBlock) _currentAction;
                        int step = block.getStep();
                        if ((step == daBlock.STEP_ATTACKER_CHOOSE_DICE) || (step == daBlock.STEP_DEFENDER_CHOOSE_DICE)) {
                            if (!_model.isWaitingForDiceChoice()) {
                                block.SelectDice(_model.getBlockDiceChosen());
                            }
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void paintButtons() {
        /**
         * Gestion du bouton Next
         */
        try {
            int mainStep = _model.getMainStep();
            int subStep = _model.getSubStep();
            int subSubStep = _model.getSubSubStep();

            boolean enabled = false;
            /**
             * Si courant de match
             */
            if (mainStep == 1) {
                switch (subStep) {
                    case 0:
                        /** 
                         * Build team for match
                         */
                        enabled = false;
                        break;
                    case 1:
                        /**
                         * Build class display =>
                         */
                        enabled = false;
                        break;
                    case 2:
                        /**
                         * Set players team 1 (challenger)
                         */
                        if (_isChallenger || _standalone) {
                            enabled = true;
                        } else {
                            enabled = false;
                        }
                        break;
                    case 3:
                        /**
                         * Set players team 2 (not challenger)
                         */
                        if (!_isChallenger || _standalone) {
                            enabled = true;
                        } else {
                            enabled = false;
                        }
                        break;
                    case 4:
                        /**
                         * Place the ball
                         */
                        int kicking = _model.getKickinkgTeam();
                        if (((_isChallenger || _standalone) && (kicking == 1)) || ((!_isChallenger || _standalone) && (kicking == 2))) {
                            enabled = true;
                        } else {
                            enabled = false;
                        }
                        break;
                    case 5:
                        /**
                         * Kickoff
                         */
                        if (subSubStep == 2) {
                            int currentStepData = _model.getCurrentStepData();
                            kicking = _model.getKickinkgTeam();
                            switch (currentStepData) {
                                case 1:
                                    /**
                                     * High Kick
                                     */
                                    if (((kicking == 1) && (!_isChallenger || _standalone)) || (kicking == 2) && (_isChallenger || _standalone)) {
                                        enabled = true;
                                    } else {
                                        enabled = false;
                                    }
                                    break;
                                case 2:
                                    /**
                                     * Perfect defense
                                     */
                                    if (((kicking == 1) && (_isChallenger || _standalone)) || (kicking == 2) && (!_isChallenger || _standalone)) {
                                        enabled = true;
                                    } else {
                                        enabled = false;
                                    }
                                    break;
                                case 3:
                                    /**
                                     * Quick Snap
                                     */
                                    if (((kicking == 1) && (!_isChallenger || _standalone)) || ((kicking == 2) && (_isChallenger || _standalone))) {
                                        enabled = true;
                                    } else {
                                        enabled = false;
                                    }
                                    break;
                                case 4:
                                    /**
                                     * BLITZ
                                     */
                                    if (((kicking == 1) && (_isChallenger || _standalone)) || ((kicking == 2) && (!_isChallenger || _standalone))) {
                                        enabled = true;
                                    } else {
                                        enabled = false;
                                    }
                                    break;
                                default:
                                    enabled = false;
                                    break;
                            }


                        } else {
                            enabled = false;
                        }
                        break;
                    case 6:
                        /**
                         * Ball Falls and scatter
                         */
                        enabled = false;
                        break;
                    case 7:
                        /**
                         * select team to play or halftime or end
                         */
                        enabled = false;
                        break;
                    case 8:
                        /**
                         * Turn team left
                         */
                        if (_isChallenger || _standalone) {
                            enabled = true;
                        } else {
                            enabled = false;
                        }
                        break;
                    case 9:
                        /**
                         * Turn team right
                         */
                        if (!_isChallenger || _standalone) {
                            enabled = true;
                        } else {
                            enabled = false;
                        }
                        break;
                    default:
                        enabled = false;
                        break;
                }
            } else {
                enabled = false;
            }
            jbtNext.setEnabled(enabled);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void paintDiary() {
        try {
            jtaLastRolls.setText(_model.getDiary());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void repaintFlyingPlayer() {
        if (_flyingPlayer != null) {
            try {
                jlbFlyingAgility.setText(Integer.toString(_flyingPlayer.getAgility()));
                jlbFlyingArmor.setText(Integer.toString(_flyingPlayer.getArmor()));
                jlbFlyingStrength.setText(Integer.toString(_flyingPlayer.getStrength()));
                jlbFlyingMovement.setText(Integer.toString(_flyingPlayer.getMovement()));

                jlbFlyingPlayerName.setText("#" + Integer.toString(_flyingPlayer.getNumber()) + " " + _flyingPlayer.getName());
                jlbFlyingPlayerPosition.setText(_flyingPlayer.getPosition());

                jlsFlyingCompetences.removeAll();

                Vector competences = _flyingPlayer.getCompetences();
                String[] comps = new String[competences.size()];
                for (int i = 0; i
                        < competences.size(); i++) {
                    dCompetence comp = (dCompetence) competences.get(i);
                    comps[i] = comp.getName();
                }

                jlsFlyingCompetences.setModel(new CompListModel(comps));

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }

    protected void paintSquares() {
        Graphics2D g2 = (Graphics2D) jlbGround.getGraphics();
        /*
         * Draw zones
         */
        if (_model != null) {
            try {
                dSquareCollection squares = _model.getSquares();
                if (squares != null) {
                    for (int i_x = 0; i_x
                            < squares.getNbX(); i_x++) {
                        for (int i_y = 0; i_y
                                < squares.getNbY(); i_y++) {
                            dSquare s = squares.getSquare(i_x, i_y);
                            paintSquare(s, g2);
                        }

                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }

    void paintSquare(dSquare s, Graphics2D g2) {
        int nbTackleZone;
        try {
            switch (s.getState()) {
                case dSquare.C_SQUARE_EMPTY:
                    break;
                case dSquare.C_SQUARE_OPPONENT_INTERCEPT:
                    // Si le substep et le tour de l'équipe de gauche                
                    if (_model.getSubStep() == 8) {
                        nbTackleZone = s.getTackleZone(_leftTeam);
                    } else {
                        nbTackleZone = s.getTackleZone(_rightTeam);
                    }
                    paintSquareBackground(s, g2, nbTackleZone);
                    break;
                case dSquare.C_SQUARE_OPPONENT_TO_BLOCK:
                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.7f));
                    switch (s.getBlockDices()) {
                        case daBlock.DICE_ONE:
                            g2.setColor(new Color(20, 240, 20));
                            g2.fillRect(s.getX() * C_SQUARE_SIZE + 1 + 10, s.getY() * C_SQUARE_SIZE + 1 + 10, 10, 10);
                            g2.setColor(new Color(0, 0, 0));
                            g2.drawRect(s.getX() * C_SQUARE_SIZE + 1 + 10, s.getY() * C_SQUARE_SIZE + 1 + 10, 10, 10);
                            break;
                        case daBlock.DICE_TWO_FOR:
                            g2.setColor(new Color(20, 240, 20));
                            g2.fillRect(s.getX() * C_SQUARE_SIZE + 1 + 5, s.getY() * C_SQUARE_SIZE + 1 + 5, 10, 10);
                            g2.fillRect(s.getX() * C_SQUARE_SIZE + 1 + 15, s.getY() * C_SQUARE_SIZE + 1 + 15, 10, 10);
                            g2.setColor(new Color(0, 0, 0));
                            g2.drawRect(s.getX() * C_SQUARE_SIZE + 1 + 5, s.getY() * C_SQUARE_SIZE + 1 + 5, 10, 10);
                            g2.drawRect(s.getX() * C_SQUARE_SIZE + 1 + 15, s.getY() * C_SQUARE_SIZE + 1 + 15, 10, 10);
                            break;
                        case daBlock.DICE_TWO_AGAINST:
                            g2.setColor(new Color(240, 20, 20));
                            g2.fillRect(s.getX() * C_SQUARE_SIZE + 1 + 5, s.getY() * C_SQUARE_SIZE + 1 + 5, 10, 10);
                            g2.fillRect(s.getX() * C_SQUARE_SIZE + 1 + 15, s.getY() * C_SQUARE_SIZE + 1 + 15, 10, 10);
                            g2.setColor(new Color(0, 0, 0));
                            g2.drawRect(s.getX() * C_SQUARE_SIZE + 1 + 5, s.getY() * C_SQUARE_SIZE + 1 + 5, 10, 10);
                            g2.drawRect(s.getX() * C_SQUARE_SIZE + 1 + 15, s.getY() * C_SQUARE_SIZE + 1 + 15, 10, 10);
                            break;
                        case daBlock.DICE_THREE_FOR:
                            g2.setColor(new Color(20, 240, 20));
                            g2.fillRect(s.getX() * C_SQUARE_SIZE + 1 + 5, s.getY() * C_SQUARE_SIZE + 1 + 5, 10, 10);
                            g2.fillRect(s.getX() * C_SQUARE_SIZE + 1 + 15, s.getY() * C_SQUARE_SIZE + 1 + 5, 10, 10);
                            g2.fillRect(s.getX() * C_SQUARE_SIZE + 1 + 10, s.getY() * C_SQUARE_SIZE + 1 + 15, 10, 10);
                            g2.setColor(new Color(0, 0, 0));
                            g2.drawRect(s.getX() * C_SQUARE_SIZE + 1 + 5, s.getY() * C_SQUARE_SIZE + 1 + 5, 10, 10);
                            g2.drawRect(s.getX() * C_SQUARE_SIZE + 1 + 15, s.getY() * C_SQUARE_SIZE + 1 + 5, 10, 10);
                            g2.drawRect(s.getX() * C_SQUARE_SIZE + 1 + 10, s.getY() * C_SQUARE_SIZE + 1 + 10, 10, 10);
                            break;
                        case daBlock.DICE_THREE_AGAINST:
                            g2.setColor(new Color(240, 20, 20));
                            g2.fillRect(s.getX() * C_SQUARE_SIZE + 1 + 5, s.getY() * C_SQUARE_SIZE + 1 + 5, 10, 10);
                            g2.fillRect(s.getX() * C_SQUARE_SIZE + 1 + 15, s.getY() * C_SQUARE_SIZE + 1 + 5, 10, 10);
                            g2.fillRect(s.getX() * C_SQUARE_SIZE + 1 + 10, s.getY() * C_SQUARE_SIZE + 1 + 15, 10, 10);
                            g2.setColor(new Color(0, 0, 0));
                            g2.drawRect(s.getX() * C_SQUARE_SIZE + 1 + 5, s.getY() * C_SQUARE_SIZE + 1 + 5, 10, 10);
                            g2.drawRect(s.getX() * C_SQUARE_SIZE + 1 + 15, s.getY() * C_SQUARE_SIZE + 1 + 5, 10, 10);
                            g2.drawRect(s.getX() * C_SQUARE_SIZE + 1 + 10, s.getY() * C_SQUARE_SIZE + 1 + 10, 10, 10);
                            break;
                    }

                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
                    break;
                case dSquare.C_SQUARE_PARTNER_TO_PASS:
                    // Si c'est le tour de l'équipe de gauche
                    if (_model.getSubStep() == 8) {
                        nbTackleZone = s.getTackleZone(_rightTeam);
                    } else {
                        nbTackleZone = s.getTackleZone(_leftTeam);
                    }

                    paintSquareBackground(s, g2, nbTackleZone);
                    break;
                case dSquare.C_SQUARE_PUSH_ZONE:
                    paintSquareBackground(s, g2, 0);
                    break;

                case dSquare.C_SQUARE_MOVE:
                    if (_model.getSubStep() == 8) {
                        nbTackleZone = s.getTackleZone(_rightTeam);
                    } else {
                        nbTackleZone = s.getTackleZone(_leftTeam);
                    }

                    paintSquareBackground(s, g2, nbTackleZone);
                    break;
                default:
                    break;
            }

            switch (s.getBallState()) {
                case dSquare.C_SQUARE_BALL_HAS_BALL:
                    int width = _ballImage.getImage().getWidth(this);
                    int heigth = _ballImage.getImage().getHeight(this);
                    g2.drawImage(_ballImage.getImage(), s.getX() * C_SQUARE_SIZE + ((C_SQUARE_SIZE - width) / 2), s.getY() * C_SQUARE_SIZE + (C_SQUARE_SIZE - heigth) / 2, this);
                    break;
                case dSquare.C_SQUARE_BALL_WAIT_BALL:
                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.6f));
                    int width2 = _ballImage.getImage().getWidth(this);
                    int heigth2 = _ballImage.getImage().getHeight(this);
                    g2.drawImage(_ballImage.getImage(), s.getX() * C_SQUARE_SIZE + ((C_SQUARE_SIZE - width2) / 2), s.getY() * C_SQUARE_SIZE + (C_SQUARE_SIZE - heigth2) / 2, this);
                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
                    break;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    void paintSquareBackground(dSquare s, Graphics2D g2, int nbTackleZone) {

        try {

            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.4f));
            switch (nbTackleZone) {
                case 0:
                    g2.setColor(new Color(7, 119, 39));
                    break;
                case 1:
                    g2.setColor(new Color(237, 239, 27));
                    break;
                case 2:
                    /* Orange */
                    g2.setColor(new Color(239, 147, 27));
                    break;
                case 3:
                    /* Red */
                    g2.setColor(new Color(191, 15, 48));
                    break;
                case 4:
                    /* Light blue */
                    g2.setColor(new Color(36, 215, 236));
                    break;
                case 5:
                    /* Blue */
                    g2.setColor(new Color(36, 69, 236));
                    break;
                case 6:
                    /* Pink */
                    g2.setColor(new Color(236, 36, 201));
                    break;
                case 7:
                    /* Violet */
                    g2.setColor(new Color(163, 33, 97));
                    break;
                default:
                    /* Grey */
                    g2.setColor(new Color(100, 100, 100));
                    break;
            }

            if (!_model.isAPlayer(s)) {
                g2.fillRect(s.getX() * C_SQUARE_SIZE + 1, s.getY() * C_SQUARE_SIZE + 1, 28, 28);
                if (s.isSpecial()) {
                    g2.setColor(new Color(255, 0, 0));
                } else {
                    g2.setColor(new Color(0, 0, 0));
                }

                g2.drawRect(s.getX() * C_SQUARE_SIZE + 1, s.getY() * C_SQUARE_SIZE + 1, 28, 28);
            } else {
                g2.fillOval(s.getX() * C_SQUARE_SIZE + 1, s.getY() * C_SQUARE_SIZE + 1, 28, 28);
                // Si substep 8 alors équipe courante est leftTeam.
                rmiTeam opponent;

                if (_model.getSubStep() == 8) {
                    opponent = _rightTeam;
                } else {
                    opponent = _leftTeam;
                }

                if (opponent.isAPlayer(s)) {
                    g2.setColor(new Color(240, 0, 0));
                } else {
                    g2.setColor(new Color(0, 0, 0));
                }

                g2.drawOval(s.getX() * C_SQUARE_SIZE + 1, s.getY() * C_SQUARE_SIZE + 1, 28, 28);
            }

            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

