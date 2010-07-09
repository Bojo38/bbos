/*
 * dsBeforeMatch.java
 *
 * Created on November 29, 2007, 10:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Automat;

import bbos.Tools.bbTool;
import bbos.Match.Model.Roll.drPublic;
import bbos.Match.Model.Roll.drMeteo;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.dMeteo;
import bbos.*;
import bbos.General.Model.mPlayerType;
import bbos.General.Model.mPlayer;
import bbos.General.Model.mTeamRoster;
import bbos.General.Views.jdgProgressBar;

import bbos.Match.Automat.BeforeMatch.jdgInducements;
import bbos.Match.Automat.BeforeMatch.jdgTreasury;
import bbos.Match.Automat.Steps.SubStep.essPreMatch;
import bbos.Match.Model.Competences.dCompetencesFactory;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.rmiTeam;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author moi
 */
public class dsBeforeMatch implements bbos.Match.Automat.iSequence {

    rmiMatch _model;
    rmiTeam _leftTeam;
    rmiTeam _rightTeam;
    boolean _isChallenger;
    boolean _standalone;

    /** Creates a new instance of dsBeforeMatch */
    public dsBeforeMatch(rmiMatch model, rmiTeam left, rmiTeam right, boolean isChallenger,boolean standalone) {
        _model = model;
        _leftTeam = left;
        _rightTeam = right;
        _isChallenger = isChallenger;
        _standalone=standalone;
    }

    /*        : Add JourneyMen, compute ranking
     *      1 : Meteo
     *      2 : Select tresury
     *      3 : Select inducement
     *      4 : FAME
     *      5 : Toas*/
    public void nextStep() {
        try {
            essPreMatch substep=(essPreMatch)_model.getSubStep();
            switch (substep) {
                case JOURNEYMEN:
                    /**
                     * Add JourneyMen
                     */
                    if (_isChallenger||_standalone) {
                        addJourneyMen(_leftTeam,true);
                        addJourneyMen(_rightTeam,false);
                        _model.setSubStep(essPreMatch.METEO);
                        _model.setHalf(1);
                    }
                    break;
                case METEO:
                    /**
                     * Roll Meteo
                     */
                    if (_isChallenger||_standalone) {
                        setMeteo();
                        _model.setSubStep(essPreMatch.METEO2);
                    }
                    break;
                case METEO2:
                    showMeteo();
                    _model.setSubStep(essPreMatch.TREASURY);
                case TREASURY:
                    /**
                     * Select Treasury
                     */
                    selectTreasury();

                    if (_rightTeam.isPettyCashChosen() && _leftTeam.isPettyCashChosen()) {
                        _model.setSubStep(essPreMatch.INDUCEMENTS);
                    }
                    break;
                case INDUCEMENTS:
                    /**
                     * Select Inducements 
                     */
                    if (_rightTeam.getMatchRanking() > _leftTeam.getMatchRanking()) {
                        _leftTeam.setPettyCash(_leftTeam.getPettyCash() + (_rightTeam.getMatchRanking() - _leftTeam.getMatchRanking()) * 10000);
                    } else {
                        _rightTeam.setPettyCash(_rightTeam.getPettyCash() + (_leftTeam.getMatchRanking() - _rightTeam.getMatchRanking()) * 10000);
                    }
                    selectInducements();
                    if (_rightTeam.areInducementsChosen() && _leftTeam.areInducementsChosen()) {
                        _model.setSubStep(essPreMatch.FAME);
                    }
                    break;
                case FAME:
                    /**
                     * FAME
                     */
                    if (_isChallenger||_standalone) {
                        setFAME();
                        _model.setActiveCoach(0);
                        _model.setSubStep(essPreMatch.TOAS);
                    }
                    break;
                case TOAS:
                    /**
                     * Toas
                     */
                    if (_model.getActiveCoach() == 0) {
                        if (_isChallenger||_standalone) {
                            _model.setActiveCoach(whoStart());
                        }
                    }

                    if (_model.getActiveCoach() == 1) {
                        if (_isChallenger||_standalone) {
                            _model.AddDiary("Left team ("+_leftTeam.getName()+") wins the toss.");
                            int result = JOptionPane.showConfirmDialog(null, _leftTeam.getName()+": Do you want to receive the ball ?", "Kickoff chocie", JOptionPane.YES_NO_OPTION);
                            if (result==JOptionPane.YES_OPTION)
                            {
                                _model.AddDiary("Right team ("+_rightTeam.getName()+") kicks first.");
                                _model.setKickinkgTeam(2);
                            }
                            else
                            {
                                _model.AddDiary("Left team ("+_leftTeam.getName()+") kicks first.");
                                _model.setKickinkgTeam(1);
                            }
                            _model.setSubStep(essPreMatch.WAIT);
                        } else {
                            if (!_waitCreated) {
                                jdgProgressBar.createSingleton("/resources/images/look/ain_pacte.png", 1);
                                _waitCreated = true;
                            }
                            jdgProgressBar.setProgressValue(0, "Waiting for opponent choosing who will kick the ball.");
                        }
                    }


                    if (_model.getActiveCoach() == 2) {
                         _model.AddDiary("Right team ("+_rightTeam.getName()+") wins the toss.");
                         
                        if (!_isChallenger||_standalone) {
                            int result = JOptionPane.showConfirmDialog(null, _rightTeam.getName()+": Do you want to receive the ball ?", "Kickoff chocie", JOptionPane.YES_NO_OPTION);
                            if (result==JOptionPane.YES_OPTION)
                            {
                                _model.AddDiary("Left team ("+_leftTeam.getName()+") kicks first.");
                                _model.setKickinkgTeam(1);
                            }
                            else
                            {
                                _model.AddDiary("Right team ("+_rightTeam.getName()+") kicks first.");
                                _model.setKickinkgTeam(2);
                            }
                            _model.setSubStep(essPreMatch.WAIT);
                        } else {
                            if (!_waitCreated) {
                                jdgProgressBar.createSingleton("/resources/images/look/ain_pacte.png", 1);
                                _waitCreated = true;
                            }
                            jdgProgressBar.setProgressValue(0, "Waiting for opponent choosing who will kick the ball.");
                        }
                    }
                    break;
                case WAIT:
                    /**
                     * End of pre-sequence
                     */
                    if (!_waitCreated) {
                        jdgProgressBar.setProgressValue(1, "");
                        _waitCreated = false;
                    }
                    
                     _model.setSubStep(essPreMatch.END);
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void resetStep() {
        try
        {
        _model.setSubStep(essPreMatch.JOURNEYMEN);
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isFinished() {
        try {
            if (_model.getSubStep() == essPreMatch.END) {
                return true;
            } else {
                return false;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void addJourneyMen(rmiTeam team,boolean isChallenger) {
        try {
            int number=11-team.getPlayersNumber();
             //Vector players = team.getPlayers();
            //if (players.size() < 11) 
            if (number >0)
            {
                /**
                 * Get Journeyman playerType
                 */
                mTeamRoster roster = null;
                Vector rosters = mBBoS.getSingleton().getTeamTypes();
                for (int i = 0; i < rosters.size(); i++) {
                    roster = (mTeamRoster) rosters.get(i);
                    if (team.getRaceId() == roster.getId()) {
                        break;
                    }
                }

                mPlayerType playerType = null;
                for (int i = 0; i < roster.getRegularPlayersNumber(); i++) 
                {
                    mPlayerType tmp=roster.getPlayerType(i);
                    if (tmp.getLimit() >= 12) {
                        playerType = tmp;
                    }
                }

                /**
                 * Add as many player as needed
                 */
                if (playerType != null) {
                    for (int i=0; i<number; i++) {
                        dPlayer p = new dPlayer(playerType,_isChallenger,team.getRace(), null);
                        p.getCompetences().add(dCompetencesFactory.createCompetence("Loner"));
                        _model.AddDiary("Journeyman added for team: "+team.getName());
                        team.addPlayer(p);
                    }
                }
            }
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

    protected void setMeteo() throws RemoteException {

        /*
         * Automatic Step of Meteo
         */
        drMeteo rollMeteo = new drMeteo();
        int roll = rollMeteo.rollDices();

        _model.setMeteo(rollMeteo.rollDices());

        switch (_model.getMeteo()) {
            case dMeteo.METEO_NICE:
                _model.AddDiary("Meteo is : Nice");
                break;
            case dMeteo.METEO_HEAT:
                _model.AddDiary("Meteo is : Heat");
                break;
            case dMeteo.METEO_SUNNY:
                _model.AddDiary("Meteo is : Very sunny");
                break;
            case dMeteo.METEO_RAIN:
                _model.AddDiary("Meteo is : Rain");
                break;
            case dMeteo.METEO_BLIZZARD:
                _model.AddDiary("Meteo is : Blizzard");
                break;
            }
    }

    protected void showMeteo() throws RemoteException {

        switch (_model.getMeteo()) {
            case dMeteo.METEO_NICE:
                JOptionPane.showMessageDialog(null, "The weather is : Nice !");
                break;
            case dMeteo.METEO_HEAT:
                JOptionPane.showMessageDialog(null, "The weather is  : Suffering heat !");
                break;
            case dMeteo.METEO_SUNNY:
                JOptionPane.showMessageDialog(null, "The weather is  : Very sunny !");
                break;
            case dMeteo.METEO_RAIN:
                JOptionPane.showMessageDialog(null, "The weather is  : Pouring Rain !");
                break;
            case dMeteo.METEO_BLIZZARD:
                JOptionPane.showMessageDialog(null, "The weather is  : Blizzard !");
                break;
            }
    }

    protected void setFAME() throws RemoteException {
        drPublic public_left = new drPublic();
        public_left.addModifiers(_leftTeam.getPopFactor());
        drPublic public_right = new drPublic();
        public_right.addModifiers(_rightTeam.getPopFactor());

        _leftTeam.setPublic(public_left.rollDices());
        _rightTeam.setPublic(public_right.rollDices());


        if (_leftTeam.getPublic() == _rightTeam.getPublic()) {
            _leftTeam.setFAME(1);
            _rightTeam.setFAME(1);
        }
        if (_leftTeam.getPublic() < _rightTeam.getPublic()) {
            _leftTeam.setFAME(0);
            _rightTeam.setFAME(1);
        }
        if (_leftTeam.getPublic() > _rightTeam.getPublic()) {
            _leftTeam.setFAME(1);
            _rightTeam.setFAME(0);
        }
        if (_leftTeam.getPublic() > (2 * _rightTeam.getPublic() + 1)) {
            _leftTeam.setFAME(2);
            _rightTeam.setFAME(0);
        }
        if ((2 * _leftTeam.getPublic() + 1) < _rightTeam.getPublic()) {
            _leftTeam.setFAME(0);
            _rightTeam.setFAME(2);
        }
        _model.AddDiary("Public : " + (_leftTeam.getPublic() + _rightTeam.getPublic()) + " 000");
        _model.AddDiary("Left Team FAME : " +_leftTeam.getFAME());
        _model.AddDiary("Right Team FAME : " + _rightTeam.getFAME());
    }
    boolean _waitCreated = false;
    boolean step_init = false;

    protected void selectTreasury() throws RemoteException {
        if (!step_init) {
            if ((!_leftTeam.isPettyCashChosen()) && (!_rightTeam.isPettyCashChosen())) {
                if (_leftTeam.getRanking() >= _rightTeam.getRanking()) {
                    _model.setActiveCoach(0);
                } else {
                    _model.setActiveCoach(1);
                }
            }
            step_init = true;
        }

        int activeCoach = _model.getActiveCoach();
        boolean right = _rightTeam.isPettyCashChosen();
        boolean left = _leftTeam.isPettyCashChosen();

        /**
         * Select Treasury Player 1
         */
        if ((_model.getActiveCoach() == 0) && (!_leftTeam.isPettyCashChosen())) {
            /**
             * Si le joueur est le challenger
             */
            if (_isChallenger||_standalone) {
                jdgProgressBar.setProgressValue(1, "OK");
                jdgTreasury window = new jdgTreasury(_leftTeam, _leftTeam.getMatchRanking() - _rightTeam.getMatchRanking());
                window.setVisible(true);
                System.out.println("Challenger treasury set");
                _leftTeam.setPettyCashChosen(true);
                _model.setActiveCoach(1);
            } else {
                if (!_waitCreated) {
                    jdgProgressBar.createSingleton("/resources/images/look/ain_pacte.png", 1);
                    _waitCreated = true;
                }
                jdgProgressBar.setProgressValue(0, "Waiting for opponent choosing treasury to set into petty cash");
            }
        }
        /**
         * Select Treasury Player 2
         */
        if ((_model.getActiveCoach() == 1) && (!_rightTeam.isPettyCashChosen())) {
            /**
             * Si le joueur est le challenger
             */
            if (!_isChallenger||_standalone) {
                jdgProgressBar.setProgressValue(1, "OK");
                jdgTreasury window = new jdgTreasury(_rightTeam, _rightTeam.getMatchRanking() - _leftTeam.getMatchRanking());
                window.setVisible(true);
                _rightTeam.setPettyCashChosen(true);
                _model.setActiveCoach(0);
                System.out.println("Challenged treasury set");
            } else {
                if (!_waitCreated) {
                    jdgProgressBar.createSingleton("/resources/images/look/ain_pacte.png", 1);
                    _waitCreated = true;
                }
                jdgProgressBar.setProgressValue(0, "Waiting for opponent choosing treasury to set into petty cash");
            }
        }
    }

    protected void selectInducements() throws RemoteException {

        /**
         * Select Treasury Player 1
         */
        if ((_model.getActiveCoach() == 0) && (!_leftTeam.areInducementsChosen())) {
            /**
             * Si le joueur est le challenger
             */
            if (_isChallenger||_standalone) {
                jdgProgressBar.setProgressValue(1, "OK");
                jdgInducements window = new jdgInducements(_leftTeam, _rightTeam, _model.getMeteo(),_isChallenger);
                window.setVisible(true);
                _leftTeam.setInducementsChosen(true);
                _model.setActiveCoach(1);
            } else {
                if (!_waitCreated) {
                    jdgProgressBar.createSingleton("/resources/images/look/ain_pacte.png", 1);
                    _waitCreated = true;
                }
                jdgProgressBar.setProgressValue(0, "Waiting for opponent choosing inducements");
            }
        }
        /**
         * Select Treasury Player 2
         */
        if ((_model.getActiveCoach() == 1) && (!_rightTeam.areInducementsChosen())) {
            /**
             * Si le joueur est le challenger
             */
            if (!_isChallenger||_standalone) {
                jdgProgressBar.setProgressValue(1, "OK");
                jdgInducements window = new jdgInducements(_rightTeam, _leftTeam, _model.getMeteo(),_isChallenger);
                window.setVisible(true);
                _rightTeam.setInducementsChosen(true);
                _model.setActiveCoach(0);
            } else {
                if (!_waitCreated) {
                    jdgProgressBar.createSingleton("/resources/images/look/ain_pacte.png", 1);
                    _waitCreated = true;
                }
                jdgProgressBar.setProgressValue(0, "Waiting for opponent choosing inducements");
            }
        }
    }

    protected int whoStart() {
        return bbTool.getdN(2);
    }
}

