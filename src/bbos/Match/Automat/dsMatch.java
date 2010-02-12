/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match.Automat;

import bbos.Match.Automat.DuringMatch.dsKickOff;
import bbos.Match.Model.Actions.daBlock;
import bbos.Match.Model.Roll.Agility.draGetBall;
import bbos.Match.Model.Roll.drScatter;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.dSquare;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiTeam;
import bbos.Match.tDisplayMatch;
import bbos.Match.tDisplayMatch;
import bbos.Match.tNetworkConnexion;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class dsMatch implements iSequence {

    rmiMatch _model;
    rmiTeam _leftTeam;
    rmiTeam _rightTeam;
    boolean _isChallenger;
    boolean _standalone;
    dsKickOff _kickOff;

    /** Creates a new instance of dsEndMatch */
    public dsMatch(rmiMatch match, rmiTeam left, rmiTeam right, boolean isChallenger,boolean standalone) {
        _model = match;
        _leftTeam = left;
        _rightTeam = right;
        _isChallenger = isChallenger;
        _kickOff = new dsKickOff(match, left, right, isChallenger,standalone);
        _standalone=standalone;
    }

    public void nextStep() {
        /*      0 : Build team for match => 1
         *      1 : Build class display => 2 or 3
         *      2 : Set players team 1 => 3 or 4
         *      3 : Set players team 2 => 2 or 4
         *      4 : Place the ball =>5
         *      5 : Kickoff => 6
         *      6 : Ball Falls and scatter => 7
         *      7 : Choose team to play or end => 10,2,3
         *      8 : Turn team left => 10,2,3,9
         *      9 : Turn team right => 10,2,2,8
         *      10 : End
         */
        try {

            if (tDisplayMatch.getMatchDisplay() == null) {
                tDisplayMatch display = tDisplayMatch.createMatchDisplay(_model, _leftTeam, _rightTeam, _isChallenger,_standalone);
                if (!display.isDisplayed()) {
                    display.start();
                }
            } else {
                tDisplayMatch.getMatchDisplay().refresh();
            }

            int subStep = _model.getSubStep();

            switch (subStep) {
                case 0:
                    if (_isChallenger||_standalone) {
                        _leftTeam.buildTeam();
                        _rightTeam.buildTeam();
                        _model.setSubStep(1);
                    }
                    break;
                case 1:
                    /*
                     * determine who starts, set active player and next step
                     */
                    if (_model.getKickinkgTeam() == 1) {
                        /**
                         * Left team kicks
                         */
                        _model.setSubStep(2);
                        _model.AddDiary("Team left (" + _leftTeam.getName() + "): set players on the pitch");
                    } else {
                        /**
                         * Right team kicks
                         */
                        _model.AddDiary("Team right (" + _rightTeam.getName() + "): set players on the pitch");
                        _model.setSubStep(3);
                    }
                    break;
                case 2:

                    /* Set players team Left */
                    if (_leftTeam.arePlayersOnThePitch()) {
                        if (_rightTeam.arePlayersOnThePitch()) {
                            _model.setSubStep(4);
                            if (_model.getKickinkgTeam() == 1) {
                                _model.AddDiary("Team Right (" + _rightTeam.getName() + "): set the ball on the pitch");
                                _model.setRightRefresh(true);
                            } else {
                                _model.AddDiary("Team Left (" + _leftTeam.getName() + "): set the ball on the pitch");
                                _model.setLeftRefresh(true);
                            }
                        } else {
                            _model.AddDiary("Team right (" + _rightTeam.getName() + "): set players on the pitch");
                            _model.setLeftRefresh(true);
                            _model.setSubStep(3);
                        }
                    }
                    break;
                case 3:

                    /* Set players team Right */
                    if (_rightTeam.arePlayersOnThePitch()) {
                        if (_leftTeam.arePlayersOnThePitch()) {
                            _model.setSubStep(4);
                            if (_model.getKickinkgTeam() == 2) {
                                _model.AddDiary("Team Right (" + _rightTeam.getName() + "): set the ball on the pitch");
                                _model.setRightRefresh(true);
                            } else {
                                _model.AddDiary("Team Left (" + _leftTeam.getName() + "): set the ball on the pitch");
                                _model.setLeftRefresh(true);
                            }
                        } else {
                            _model.AddDiary("Team Left (" + _leftTeam.getName() + "): set players on the pitch");
                            _model.setRightRefresh(true);
                            _model.setSubStep(2);
                        }
                    }
                    break;
                case 4:
                    /* Choose where the ball has fallen */
                    if (_model.isBallSet()) {
                        _model.setSubStep(5);
                         _model.setRightRefresh(true);
                         _model.setLeftRefresh(true);
                        _model.AddDiary("Kickoff roll : ");
                    }
                    break;
                case 5:
                    /* Kickoff roll and its effects */
                    _kickOff.nextStep();
                    if (_kickOff.isFinished()) {
                        _model.setSubStep(6);
                        _model.setCurrentStepData(0);
                    }
                    break;
                case 6:
                    /* Ball falls and bounces */
                    if (_isChallenger) {
                        if (_model.getCurrentStepData() == 0) {
                            ballBounce();
                        }
                        /* Si il n'y a pas de bad kick */
                        if (_model.getCurrentStepData() != 1) {
                            _model.setSubStep(7);
                        }
                    }
                    break;
                case 7:
                    if (_isChallenger) {
                        if ((_model.getHalf() == 2) && (_leftTeam.getTurn() == 8) && (_rightTeam.getTurn() == 8)) {
                            _model.setSubStep(10);
                        } else {
                            if (_model.getKickinkgTeam() == 1) {
                                /* Tour de l'équipe de droite */
                                _rightTeam.setTurn(_rightTeam.getTurn() + 1);
                                _model.setSubStep(9);
                                _model.setCurrentStepData(0);
                            } else {
                                /* Tour de l'équipe de gauche*/
                                _leftTeam.setTurn(_leftTeam.getTurn() + 1);
                                _model.setCurrentStepData(0);
                                _model.setSubStep(8);
                            }
                        }
                    }
                    break;
                case 8:

                    if (_model.isWaitingForDiceChoice()) {
                        int chooser = _model.getDiceBlockChooser();
                        if (((chooser == 1) && (_isChallenger)) || ((chooser == 2) && (!_isChallenger))) {
                            /**
                             * Display choice dialog
                             */
                            int diceNumber = _model.getBlockDiceNumber();
                            ImageIcon options[] = new ImageIcon[diceNumber];
                            for (int i = 1; i <= diceNumber; i++) {
                                int value = _model.getBlockDiceValue(i);
                                options[i - 1] = daBlock.getBlockDiceIcon(value, _isChallenger);
                            }

                            int choice = JOptionPane.showOptionDialog(null, "Choose dice", "Block dice choose", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]) + 1;
                            _model.stopWaitingForDiceChoice(choice);
                            _model.setLeftRefresh(true);
                            _model.setRightRefresh(true);
                        }
                    }

                    if (_isChallenger) {
                        if (_model.getCurrentStepData() == 0) {
                            _model.setCurrentStepData(1);
                            _leftTeam.rollsOver();
                            _rightTeam.setTeamHasPlayed(false);
                            _model.setLeftRefresh(true);
                            _model.setRightRefresh(true);
                        }

                        if (_leftTeam.isTouchdown()) {
                            _model.setCurrentStepData(0);
                            _model.resetSquareBlock();
                            _model.resetSquareFoul();
                            _model.resetSquareMove();
                            _model.resetSquarePass();

                            /**
                             * Set players to dugout
                             */
                            _model.sendPlayersToDugout();
                            _rightTeam.rollsKOs();
                            _leftTeam.rollsKOs();

                            _model.setKickinkgTeam(1);
                            _model.setSubStep(2);

                            if ((_leftTeam.getTurn() == 8) && (_rightTeam.getTurn() == 8)) {
                                /**
                                 * Manage turns
                                 */
                                if (_model.getHalf() == 1) {
                                    _leftTeam.setTurn(0);
                                    _rightTeam.setTurn(0);
                                    _model.setHalf(2);
                                    _model.setKickinkgTeam(2);

                                } else {
                                    _model.setSubStep(10);
                                }
                            }
                            _model.setLeftRefresh(true);
                            _model.setRightRefresh(true);
                        }

                        if (_rightTeam.isTouchdown()) {

                            tDisplayMatch.getMatchDisplay().resetSelections();
                            _model.setCurrentStepData(0);
                            _model.resetSquareBlock();
                            _model.resetSquareFoul();
                            _model.resetSquareMove();
                            _model.resetSquarePass();
                            _rightTeam.setTurn(_rightTeam.getTurn() + 1);

                            /**
                             * Set players to dugout
                             */
                            _model.sendPlayersToDugout();
                            _rightTeam.rollsKOs();
                            _leftTeam.rollsKOs();

                            _model.setKickinkgTeam(2);
                            _model.setSubStep(3);

                            if ((_leftTeam.getTurn() == 8) && (_rightTeam.getTurn() == 8)) {
                                /**
                                 * Manage turns
                                 */
                                if (_model.getHalf() == 1) {
                                    _leftTeam.setTurn(0);
                                    _rightTeam.setTurn(0);
                                    _model.setHalf(2);
                                    _model.setKickinkgTeam(1);

                                } else {
                                    _model.setSubStep(10);
                                }
                            }
                            _model.setLeftRefresh(true);
                            _model.setRightRefresh(true);
                        }

                        if (_model.isTurnover()) {

                            tDisplayMatch.getMatchDisplay().resetSelections();
                            _model.setCurrentStepData(0);
                            _model.resetSquareBlock();
                            _model.resetSquareFoul();
                            _model.resetSquareMove();
                            _model.resetSquarePass();

                            if ((_leftTeam.getTurn() == 8) && (_rightTeam.getTurn() == 8)) {
                                /**
                                 * Set players to dugout
                                 */
                                _model.sendPlayersToDugout();
                                _rightTeam.rollsKOs();
                                _leftTeam.rollsKOs();

                                /**
                                 * Manage turns
                                 */
                                if (_model.getHalf() == 1) {
                                    _leftTeam.setTurn(0);
                                    _rightTeam.setTurn(0);
                                    _model.setHalf(2);
                                    _model.setKickinkgTeam(2);
                                    _model.setSubStep(3);
                                } else {
                                    _model.setSubStep(10);
                                }

                            } else {
                                int turn = _rightTeam.getTurn();
                                _rightTeam.setTurn(turn + 1);
                                _model.setSubStep(9);
                            }
                            _model.setLeftRefresh(true);
                            _model.setRightRefresh(true);
                            _model.turnover(false);
                        }
                    }
                    break;
                case 9:
                    if (_model.isWaitingForDiceChoice()) {
                        int chooser = _model.getDiceBlockChooser();
                        if (((chooser == 1) && (_isChallenger)) || ((chooser == 2) && (!_isChallenger))) {
                            /**
                             * Display choice dialog
                             */
                            int diceNumber = _model.getBlockDiceNumber();
                            ImageIcon options[] = new ImageIcon[diceNumber];
                            for (int i = 1; i <= diceNumber; i++) {
                                int value = _model.getBlockDiceValue(i);
                                options[i - 1] = daBlock.getBlockDiceIcon(value, _isChallenger);
                            }

                            int choice = JOptionPane.showOptionDialog(null, "Choose dice", "Block dice choose", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]) + 1;
                            _model.stopWaitingForDiceChoice(choice);
                            _model.setLeftRefresh(true);
                            _model.setRightRefresh(true);
                        }
                    }

                    if (!_isChallenger) {
                        if (_model.getCurrentStepData() == 0) {
                            _model.setLeftRefresh(true);
                            _model.setRightRefresh(true);
                            _model.setCurrentStepData(1);
                            _rightTeam.rollsOver();
                            _leftTeam.setTeamHasPlayed(false);
                        }

                        if (_rightTeam.isTouchdown()) {
                            _model.setCurrentStepData(0);
                            _model.resetSquareBlock();
                            _model.resetSquareFoul();
                            _model.resetSquareMove();
                            _model.resetSquarePass();

                            /**
                             * Set players to dugout
                             */
                            _model.sendPlayersToDugout();
                            _rightTeam.rollsKOs();
                            _leftTeam.rollsKOs();

                            _model.setKickinkgTeam(2);
                            _model.setSubStep(3);

                            if ((_leftTeam.getTurn() == 8) && (_rightTeam.getTurn() == 8)) {
                                /**
                                 * Manage turns
                                 */
                                if (_model.getHalf() == 1) {
                                    _leftTeam.setTurn(0);
                                    _rightTeam.setTurn(0);
                                    _model.setHalf(2);
                                    _model.setKickinkgTeam(1);

                                } else {
                                    _model.setSubStep(10);
                                }
                            }
                            _model.setLeftRefresh(true);
                            _model.setRightRefresh(true);
                        }

                        if (_leftTeam.isTouchdown()) {

                            tDisplayMatch.getMatchDisplay().resetSelections();
                            _model.setCurrentStepData(0);
                            _model.resetSquareBlock();
                            _model.resetSquareFoul();
                            _model.resetSquareMove();
                            _model.resetSquarePass();
                            _leftTeam.setTurn(_leftTeam.getTurn() + 1);

                            /**
                             * Set players to dugout
                             */
                            _model.sendPlayersToDugout();
                            _rightTeam.rollsKOs();
                            _leftTeam.rollsKOs();

                            _model.setKickinkgTeam(1);
                            _model.setSubStep(2);

                            if ((_leftTeam.getTurn() == 8) && (_rightTeam.getTurn() == 8)) {
                                /**
                                 * Manage turns
                                 */
                                if (_model.getHalf() == 1) {
                                    _leftTeam.setTurn(0);
                                    _rightTeam.setTurn(0);
                                    _model.setHalf(2);
                                    _model.setKickinkgTeam(2);

                                } else {
                                    _model.setSubStep(10);
                                }
                            }
                            _model.setLeftRefresh(true);
                            _model.setRightRefresh(true);
                        }

                        if (_model.isTurnover()) {

                            tDisplayMatch.getMatchDisplay().resetSelections();
                            _model.setCurrentStepData(0);
                            _model.resetSquareBlock();
                            _model.resetSquareFoul();
                            _model.resetSquareMove();
                            _model.resetSquarePass();


                            if ((_leftTeam.getTurn() == 8) && (_rightTeam.getTurn() == 8)) {
                                /**
                                 * Set players to dugout
                                 */
                                _model.sendPlayersToDugout();
                                _rightTeam.rollsKOs();
                                _leftTeam.rollsKOs();

                                /**
                                 * Manage turns
                                 */
                                if (_model.getHalf() == 1) {
                                    _leftTeam.setTurn(0);
                                    _rightTeam.setTurn(0);
                                    _model.setHalf(2);
                                    _model.setKickinkgTeam(1);
                                    _model.setSubStep(3);
                                } else {
                                    _model.setSubStep(10);
                                }
                            } else {
                                int turn = _leftTeam.getTurn();
                                _leftTeam.setTurn(turn + 1);
                                _model.setSubStep(8);
                            }
                            _model.setLeftRefresh(true);
                            _model.setRightRefresh(true);
                            _model.turnover(false);
                        }
                    }
                    break;
                case 10:
                    break;
            }
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }

    }

    public void resetStep() {
        try {
            _model.setSubStep(0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isFinished() {
        try {
            if (_model.getSubStep() == 10) {
                return true;
            } else {
                return false;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void ballBounce() {
        try {
            rmiTeam receivingTeam = null;
            if (_model.getKickinkgTeam() == 1) {
                receivingTeam = _rightTeam;
            } else {
                receivingTeam = _leftTeam;
            }

            dSquare s = _model.getBallSquare();
            boolean ballOnGround = isBallOnGround(s);
            boolean ballIsCatched = false;
            boolean ballHasBounced = false;

            while ((!ballIsCatched) && (!ballHasBounced)) {
                /* Si la balle est en dehors du terrain */
                if (!ballOnGround) {
                    /* BAD KICK */
                    _model.setCurrentStepData(1);
                    _model.AddDiary(receivingTeam.getName() + ": Bad kick, select a player to get the ball");
                    _model.removeBall();
                    ballIsCatched = true;
                } else {
                    /* Si il y un joueur dessous*/
                    int i = receivingTeam.getPlayerNumber(s.getX(), s.getY());
                    if (i > -1) {
                        /* Il essaye de l'attraper */
                        Vector players = new Vector();
                        if (_model.getKickinkgTeam() == 1) {
                            players = tNetworkConnexion.getConnexion().getRightPlayers();
                        } else {
                            players = tNetworkConnexion.getConnexion().getLeftPlayers();
                        }
                        rmiPlayer player = (rmiPlayer) players.get(i);
                        draGetBall roll = new draGetBall(player);
                        int result = roll.rollDices();
                        _model.AddDiary("Get the ball roll: " + result);
                        if (roll.isSuccess(result)) {
                            _model.AddDiary("Get the ball success!");
                            /* si il réussit*/
                            ballIsCatched = true;
                            _model.removeBall();
                            player.setBall(true);
                        } else {
                            _model.AddDiary("Get the ball failure!");
                            /* Sinon elle rebondit sur une autre case*/
                            _model.removeBall();
                            drScatter rollscatter = new drScatter(_model, s, false, true);
                            int r = rollscatter.rollDices();
                            _model.AddDiary("Scatter roll: " + r);
                            rollscatter.applyEffects(r);
                            s = rollscatter.getCurrentSquare();
                            ballOnGround = isBallOnGround(s);
                            if (ballOnGround) {
                                _model.setBall(s);
                                i = receivingTeam.getPlayerNumber(s.getX(), s.getY());
                                if (i == -1) {
                                    ballHasBounced = true;
                                }
                            }
                        }
                    } else {
                        /* Sinon elle rebondit*/
                        _model.removeBall();
                        drScatter rollscatter = new drScatter(_model, s, false, true);
                        int r = rollscatter.rollDices();
                        _model.AddDiary("Scatter roll: " + r);
                        rollscatter.applyEffects(r);
                        s = rollscatter.getCurrentSquare();
                        ballOnGround = isBallOnGround(s);
                        if (ballOnGround) {
                            _model.setBall(s);
                            i = receivingTeam.getPlayerNumber(s.getX(), s.getY());
                            if (i == -1) {
                                ballHasBounced = true;
                            }

                        }
                    }
                }
                _model.setRightRefresh(true);
                _model.setLeftRefresh(true);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected boolean isBallOnGround(dSquare s) {
        try {
            /* tant que la balle est sur le terrain ? */
            if ((s.getX() > -1) && (s.getY() > -1)) {
                if (s.getY() < 15) {
                    if (_model.getKickinkgTeam() == 1) {
                        if ((s.getX() > 12) && (s.getX() < 26)) {
                            return true;
                        }

                    } else {
                        if (s.getX() < 13) {
                            return true;
                        }

                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
    }
}
