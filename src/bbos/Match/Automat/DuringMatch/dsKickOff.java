/*
 * dsKickOff.java
 *
 * Created on November 29, 2007, 10:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Automat.DuringMatch;

import bbos.Match.Model.rmiMatch;
import bbos.*;
import bbos.Match.Automat.Steps.StepData.esdKickOff;
import bbos.Match.Automat.Steps.SubStep.SubSubStep.esssKickOff;
import bbos.Match.Model.Inducements.diBribeTheRef;
import bbos.Match.Model.Roll.drInjury;
import bbos.Match.Model.Roll.drKickOff;
import bbos.Match.Model.Roll.drMeteo;
import bbos.Match.Model.Roll.drScatter;
import bbos.Match.Model.dMeteo;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.dSquare;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiTeam;
import bbos.Match.tNetworkConnexion;
import bbos.Tools.bbTool;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author moi
 */
public class dsKickOff implements bbos.Match.Automat.iSequence {

    rmiMatch _model;
    rmiTeam _leftTeam;
    rmiTeam _rightTeam;
    boolean _isChallenger;
    boolean _standalone;
    int _roll = 0;

    /*
     * Steps:
     * 0 - begin
     * 1 - roll dices
     * 2 - apply effects
     * 3 - end
     */
    /** Creates a new instance of dsKickOff */
    public dsKickOff(rmiMatch match, rmiTeam left, rmiTeam right, boolean isChallenger, boolean standalone) {
        _model = match;
        _leftTeam = left;
        _rightTeam = right;
        _isChallenger = isChallenger;
        _standalone = standalone;

    }

    public void nextStep() {
        try {
            esssKickOff subSubStep = (esssKickOff) _model.getSubSubStep();

            switch (subSubStep) {
                case NONE:
                    _model.setSubSubStep(esssKickOff.ROLL);
                    break;
                case ROLL:
                    if (!_isChallenger || _standalone) {
                        drKickOff kickroll = new drKickOff();
                        _roll = kickroll.rollDices();
                        _model.AddDiary("Kickoff roll (2D6): " + _roll);
                        kickOffPrint();
                        _model.setSubSubStep(esssKickOff.EFFECT);
                    }
                    break;
                case EFFECT:
                    if (!_isChallenger || _standalone) {
                        kickOffEffect();
                    }
                    break;
                default:
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void resetStep() {
        try {
            _model.setSubSubStep(esssKickOff.NONE);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isFinished() {
        esssKickOff step = esssKickOff.NONE;
        try {
            step = (esssKickOff) _model.getSubSubStep();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (step == esssKickOff.END) {
            return true;
        }
        return false;
    }

    protected void kickOffPrint() {
        try {
            switch (_roll) {
                case drKickOff.C_KICK_HIGH_KICK:
                    /*
                     * High Kick effect
                     */
                    _model.AddDiary("KickOff : High Kick");
                    break;
                case drKickOff.C_KICK_BLITZ:
                    /*
                     * Disable players in tackle zone
                     * Execute turn action
                     */
                    _model.AddDiary("KickOff : Blitz");
                    break;
                case drKickOff.C_KICK_GET_THE_REF:
                    /*
                     *Get the ref effects
                     */
                    _model.AddDiary("KickOff : Get The Ref");
                    break;
                case drKickOff.C_KICK_BRILLIANT_COACH:
                    /*
                     * Brilliant coach effect
                     */
                    _model.AddDiary("KickOff : Brilliant Coach");
                    break;
                case drKickOff.C_KICK_CHEERING_FANS:
                    /*
                     * Cheering fans effect
                     */
                    _model.AddDiary("KickOff : Cheering fans");
                    break;
                case drKickOff.C_KICK_METEO:
                    /*
                     * Meteo effect
                     */
                    _model.AddDiary("KickOff : Meteo");
                    break;
                case drKickOff.C_KICK_PERFECT_DEFENSE:
                    /*
                     * Perfect defense effect
                     */
                    _model.AddDiary("KickOff : Perfect Defense");
                    break;
                case drKickOff.C_KICK_PITCH_INVASION:
                    /*
                     * Pitch Invasion effect
                     */
                    _model.AddDiary("KickOff : Pitch invasion");
                    break;
                case drKickOff.C_KICK_QUICK_SNAP:
                    /*
                     * Quick snap effect
                     */
                    _model.AddDiary("KickOff : Quick Snap");
                    break;
                case drKickOff.C_KICK_RIOT:
                    /*
                     * Riot effect
                     */
                    _model.AddDiary("KickOff : Riot");
                    break;
                case drKickOff.C_KICK_ROCK:
                    /*
                     * Rock effect
                     */
                    _model.AddDiary("KickOff : Rock");
                    break;
                default:
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void kickOffEffect() {
        try {
            esdKickOff stepData=(esdKickOff)_model.getCurrentStepData();
            /**
             * For test
             */
            //_roll=drKickOff.C_KICK_PITCH_INVASION;
            switch (_roll) {
                case drKickOff.C_KICK_HIGH_KICK:
                    /*
                     * High Kick effect
                     */
                    if (stepData==esdKickOff.NONE) {

                        Vector players;
                        if (_model.getKickinkgTeam() == 1) {
                            players = tNetworkConnexion.getConnexion().getRightPlayers();
                        } else {
                            players = tNetworkConnexion.getConnexion().getLeftPlayers();
                        }

                        for (int i = 0; i < players.size(); i++) {
                            rmiPlayer player = (rmiPlayer) players.get(i);
                            if (player.getTackleZoneNumber() > 0) {
                                player.hasPlayed(true);
                            }
                        }
                        _model.AddDiary("Select a player to catch the ball.");
                        _model.setCurrentStepData(esdKickOff.HIGH_KICK);
                        _model.setRightRefresh(true);
                        _model.setLeftRefresh(true);
                    }
                    break;
                case drKickOff.C_KICK_BLITZ:
                    /*
                     * Disable players in tackle zone
                     * Execute turn action
                     */
                    
                    if (stepData == esdKickOff.NONE) {
                        _model.AddDiary("Kicking team can play.");

                        /*
                         * Set players in tackle zone have played
                         */
                        Vector players;
                        if (_model.getKickinkgTeam() == 1) {
                            players = tNetworkConnexion.getConnexion().getLeftPlayers();
                        } else {
                            players = tNetworkConnexion.getConnexion().getRightPlayers();
                        }

                        for (int i = 0; i < players.size(); i++) {
                            rmiPlayer player = (rmiPlayer) players.get(i);
                            if (player.getTackleZoneNumber() > 0) {
                                player.hasPlayed(true);
                            }
                        }
                        _model.setRightRefresh(true);
                        _model.setLeftRefresh(true);
                        _model.setCurrentStepData(esdKickOff.BLITZ);
                    }
                    break;
                case drKickOff.C_KICK_GET_THE_REF:
                    /*
                     *Get the ref effects
                     */
                    _leftTeam.addInducement(new diBribeTheRef());
                    _rightTeam.addInducement(new diBribeTheRef());
                    /*_leftTeam.canBeSentOff(false);
                    _rightTeam.canBeSentOff(false);*/
                    _model.setSubSubStep(esssKickOff.END);
                    break;
                case drKickOff.C_KICK_BRILLIANT_COACH:
                    /*
                     * Brilliant coach effect
                     */
                    /*
                     * 1 D3 + FAME + Assistants => one more reroll
                     */
                    int team1_roll = bbTool.getd3() + _leftTeam.getFAME() + _leftTeam.getAssists();
                    int team2_roll = bbTool.getd3() + _rightTeam.getFAME() + _rightTeam.getAssists();

                    if (team1_roll > team2_roll) {
                        _model.AddDiary("Player 1 wins a reroll");
                        _leftTeam.setRerollLeft(_leftTeam.getRerollLeft() + 1);
                    }
                    if (team1_roll < team2_roll) {
                        _model.AddDiary("Player 2 wins a reroll");
                        _rightTeam.setRerollLeft(_rightTeam.getRerollLeft() + 1);
                    }
                    if (team1_roll == team2_roll) {
                        _model.AddDiary("Player 1 wins a reroll");
                        _model.AddDiary("Player 2 wins a reroll");
                        _rightTeam.setRerollLeft(_rightTeam.getRerollLeft() + 1);
                        _leftTeam.setRerollLeft(_leftTeam.getRerollLeft() + 1);
                    }
                    _model.setRightRefresh(true);
                    _model.setLeftRefresh(true);
                    _model.setSubSubStep(esssKickOff.END);
                    break;
                case drKickOff.C_KICK_CHEERING_FANS:
                    /*
                     * Cheering fans effect
                     */
                    /*
                     * 1 D3 + FAME + PomPom => one more reroll
                     */
                    team1_roll = bbTool.getd3() + _leftTeam.getFAME() + _leftTeam.getPomspoms();
                    team2_roll = bbTool.getd3() + _rightTeam.getFAME() + _rightTeam.getPomspoms();

                    if (team1_roll > team2_roll) {
                        _model.AddDiary("Player 1 wins a reroll");
                        _leftTeam.setRerollLeft(_leftTeam.getRerollLeft() + 1);
                    }
                    if (team1_roll < team2_roll) {
                        _model.AddDiary("Player 2 wins a reroll");
                        _rightTeam.setRerollLeft(_rightTeam.getRerollLeft() + 1);

                    }
                    if (team1_roll == team2_roll) {
                        _model.AddDiary("Player 1 wins a reroll");
                        _model.AddDiary("Player 2 wins a reroll");
                        _rightTeam.setRerollLeft(_rightTeam.getRerollLeft() + 1);
                        _leftTeam.setRerollLeft(_leftTeam.getRerollLeft() + 1);
                    }
                    _model.setRightRefresh(true);
                    _model.setLeftRefresh(true);
                    _model.setSubSubStep(esssKickOff.END);
                    break;
                case drKickOff.C_KICK_METEO:
                    /*
                     * Meteo effect
                     */
                    kick_meteo();
                    _model.setRightRefresh(true);
                    _model.setLeftRefresh(true);
                    _model.setSubSubStep(esssKickOff.END);
                    break;
                case drKickOff.C_KICK_PERFECT_DEFENSE:
                    /*
                     * Perfect defense effect
                     */
                    if (stepData==esdKickOff.NONE) {
                        _model.AddDiary("Defender can reorganize his players.");
                        _model.setCurrentStepData(esdKickOff.PERFECT_DEFENSE);
                    }
                    break;
                case drKickOff.C_KICK_PITCH_INVASION:
                    /*
                     * Pitch Invasion effect
                     */
                    kick_pitchInvasion();
                    _model.setRightRefresh(true);
                    _model.setLeftRefresh(true);
                    _model.setSubSubStep(esssKickOff.END);
                    break;
                case drKickOff.C_KICK_QUICK_SNAP:
                    /*
                     * Quick snap effect
                     */
                    if (stepData==esdKickOff.NONE) {
                        _model.AddDiary("Receving team can move every play by one square.");
                         _model.setCurrentStepData(esdKickOff.QUICK_SNAP);
                    }
                    break;
                case drKickOff.C_KICK_RIOT:
                    /*
                     * Riot effect
                     */
                    kick_riot();
                    _model.setRightRefresh(true);
                    _model.setLeftRefresh(true);
                    _model.setSubSubStep(esssKickOff.END);
                    break;
                case drKickOff.C_KICK_ROCK:
                    /*
                     * Rock effect
                     */
                    kick_rock();
                    _model.setRightRefresh(true);
                    _model.setLeftRefresh(true);
                    _model.setSubSubStep(esssKickOff.END);
                    break;
                default:
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void kick_riot() {
        try {
            /*
             * Roll 1 D6 if >=4 the turn-1 else turn +1,+2 or+3
             */
            int roll = bbTool.getd6();
            if (roll < 4) {
                _rightTeam.setTurn(_rightTeam.getTurn() + roll);
                _leftTeam.setTurn(_leftTeam.getTurn() + roll);
            } else {
                if ((_rightTeam.getTurn() != 1) && (_leftTeam.getTurn() != 1)) {
                    _rightTeam.setTurn(_rightTeam.getTurn() - 1);
                    _leftTeam.setTurn(_leftTeam.getTurn() - 1);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void kick_rock() {

        try {
            /*
             * Choose team who throw rock
             */
            int team1_roll = bbTool.getd6();
            _model.AddDiary("Team 1 roll : " + team1_roll);
            team1_roll += _leftTeam.getFAME();
            _model.AddDiary("Team 1 roll + FAME : " + team1_roll);
            int team2_roll = bbTool.getd6();
            _model.AddDiary("Team 2 roll : " + team2_roll);
            team2_roll += _leftTeam.getFAME();
            _model.AddDiary("Team 2 roll + FAME : " + team2_roll);

            if (team1_roll > team2_roll) {
                rmiPlayer player = null;
                if (_rightTeam.getPlayersOK() > 0) {
                    Vector players = tNetworkConnexion.getConnexion().getRightPlayers();
                    while (player == null) {
                        int number = bbTool.getdN(players.size());
                        player = (rmiPlayer) players.get(number - 1);
                        if (!player.isOnPitch()) {
                            player = null;
                        }
                    }
                    drInjury injury = new drInjury();
                    int injury_roll = injury.rollDices();
                    _model.AddDiary("Injury roll: " + injury_roll);
                    int state = injury.applyEffects(injury_roll, player);
                    player.setState(state);
                    //player.setState(state);
                }
            }

            if (team1_roll < team2_roll) {
                rmiPlayer player = null;
                if (_leftTeam.getPlayersOK() > 0) {
                    Vector players = tNetworkConnexion.getConnexion().getLeftPlayers();
                    while (player == null) {
                        int number = bbTool.getdN(players.size());
                        player = (rmiPlayer) players.get(number - 1);
                        if (!player.isOnPitch()) {
                            player = null;
                        }
                    }
                    drInjury injury = new drInjury();
                    int injury_roll = injury.rollDices();
                    _model.AddDiary("Injury roll: " + injury_roll);
                    int state = injury.applyEffects(injury_roll, player);
                    player.setState(state);
                    //player.setState(state);
                }
            }

            if (team1_roll == team2_roll) {
                rmiPlayer player = null;
                if (_rightTeam.getPlayersOK() > 0) {
                    Vector players = tNetworkConnexion.getConnexion().getRightPlayers();
                    while (player == null) {
                        int number = bbTool.getdN(players.size());
                        player = (rmiPlayer) players.get(number - 1);
                        if (!player.isOnPitch()) {
                            player = null;
                        }
                    }
                    drInjury injury = new drInjury();
                    int injury_roll = injury.rollDices();
                    _model.AddDiary("Injury roll: " + injury_roll);
                    int state = injury.applyEffects(injury_roll, player);
                    player.setState(state);
                    //player.setState(state);
                }
                player = null;
                if (_leftTeam.getPlayersOK() > 0) {
                    Vector players = tNetworkConnexion.getConnexion().getLeftPlayers();
                    while (player == null) {
                        int number = bbTool.getdN(players.size());
                        player = (rmiPlayer) players.get(number - 1);
                        if (!player.isOnPitch()) {
                            player = null;
                        }
                    }
                    drInjury injury = new drInjury();
                    int injury_roll = injury.rollDices();
                    _model.AddDiary("Injury roll: " + injury_roll);
                    int state = injury.applyEffects(injury_roll, player);
                    player.setState(state);
                    //player.setState(state);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void kick_meteo() {
        try {
            int _oldMeteo = _model.getMeteo();
            drMeteo rollMeteo = new drMeteo();
            _model.setMeteo(rollMeteo.rollDices());
            if ((_oldMeteo == dMeteo.METEO_NICE) && (_model.getMeteo() == dMeteo.METEO_NICE)) {
                /*
                 * The the weather was nice and is also nice after this roll
                 * the ball scatters 1 square
                 */
                dSquare s = _model.getBallSquare();
                drScatter scatterRoll = new drScatter(_model, s, false, false);
                int direction = scatterRoll.rollDices();
                scatterRoll.applyEffects(direction);
                s = scatterRoll.getCurrentSquare();
                _model.removeBall();
                _model.setWaitingBall(s);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected void kick_pitchInvasion() {
        /*
         * Roll 1d6 by player. Add the fame. If the result is
         * 6 the the player is stunned
         */
        try {
            /*
             * Team 1
             */
            Vector players = tNetworkConnexion.getConnexion().getLeftPlayers();
            for (int i = 0; i < players.size(); i++) {
                rmiPlayer player = (rmiPlayer) players.get(i);
                if (player.isOnPitch()) {
                    int roll = bbTool.getd6();
                    _model.AddDiary("Player " + i + " - Roll : " + roll);
                    roll += _leftTeam.getFAME();
                    if (roll >= 6) {
                        _model.AddDiary("+ FAME : " + roll + "");
                        //player.setState(dPlayer.C_STATE_STUNNED);
                        player.setState(dPlayer.C_STATE_STUNNED);
                    }
                }
            }
            /*
             * Team 2
             */
            players = tNetworkConnexion.getConnexion().getRightPlayers();
            for (int i = 0; i < players.size(); i++) {
                rmiPlayer player = (rmiPlayer) players.get(i);
                if (player.isOnPitch()) {
                    int roll = bbTool.getd6();
                    _model.AddDiary("Player " + i + " - Roll : " + roll);
                    roll += _rightTeam.getFAME();
                    if (roll >= 6) {
                        _model.AddDiary("+ FAME : " + roll + "");
                        //player.setState(dPlayer.C_STATE_STUNNED);
                        player.setState(dPlayer.C_STATE_STUNNED);
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
