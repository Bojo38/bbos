/*
 * dsWholeMatch.java
 *
 * Created on November 29, 2007, 10:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Automat;

import bbos.*;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiTeam;
import java.rmi.RemoteException;

/**
 *
 * @author moi
 */
public class dsWholeMatch implements bbos.Match.Automat.iSequence {
    /*
     * Step number
     */

    /*    public int s_step;
    public static final int STEP_NONE = 0;
    public static final int STEP_BEFORE_MATCH = 1;
    public static final int STEP_KICK_OFF = 2;
    public static final int STEP_TURN = 3;
    public static final int STEP_END_MATCH = 4;
    public static final int STEP_END = 5;*/
    rmiMatch _model;
    boolean _isChallenger;

    public dsWholeMatch(rmiMatch match, rmiTeam leftTeam, rmiTeam rightTeam, boolean isChallenger,boolean standalone) {
        _model = match;
        _beforeMatch = new dsBeforeMatch(_model, leftTeam, rightTeam, isChallenger,standalone);
        _match = new dsMatch(_model, leftTeam, rightTeam, isChallenger,standalone);
        _endMatch = new dsEndMatch(_model);
        _isChallenger = isChallenger;
    }
    /*
     * Sequences to Execute
     */
    dsBeforeMatch _beforeMatch;
    dsMatch _match;
    dsEndMatch _endMatch;

    public void nextStep() {
        int mainStep = -1;
        try {
            mainStep = _model.getMainStep();
        } catch (RemoteException e) {
            e.printStackTrace();

        }

        switch (mainStep) {
            /**
             * Pre match sequence
             */
            case 0:
                _beforeMatch.nextStep();
                if (_beforeMatch.isFinished()) {
                    _match.resetStep();
                    try {
                        _model.setMainStep(1);
                        _model.setSubStep(0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            /**
             *  match sequence
             */
            case 1:
                _match.nextStep();
                if (_match.isFinished()) {
                    _endMatch.resetStep();
                    try {
                        _model.setMainStep(2);
                        _model.setSubStep(0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            /**
             * End match sequence
             */
            case 2:
                _endMatch.nextStep();
                if (_endMatch.isFinished()) {
                    try {
                        _model.setMainStep(3);
                        _model.setSubStep(0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            /**
             * final sequence
             */
            case 3:
                break;
        }

        return;



    /*    if (s_step==STEP_BEFORE_MATCH)
    {
    _beforeMatch.nextStep();
    if (_beforeMatch.isFinished())
    {
    _kickOff.resetStep();
    s_step=STEP_KICK_OFF;
    }
    }
    /*
     * Coup d'envoi
     */
    /*     if (s_step==STEP_KICK_OFF)
    {
    _kickOff.nextStep();
    if (_kickOff.isFinished())
    {
    _turn.resetStep();
    s_step=STEP_TURN;
    }
    }
    /*
     * Tour
     */
    /*        if (s_step==STEP_TURN)
    {
    _turn.nextStep();
    if (_turn.isFinished())
    {
    /*
     * Si Mi-temps, alors kickoff
     */
    /*           if ((_model.getLeftTeam().getTurn()==(int)8)&&(_model.getRightTeam().getTurn()==(int)8))
    {
    _kickOff.resetStep();
    s_step=STEP_KICK_OFF;
    }
    else
    {
    /*
     * Si fin du match alors séquence d'après match
     */
    /*               if ((_model.getLeftTeam().getTurn()==(int)16)&&(_model.getRightTeam().getTurn()==(int)16))
    {
    _endMatch.resetStep();
    s_step=STEP_END_MATCH;
    }
    else
    {
    /*
     * Si touchdown, alors kickoff
     */
    /*               if (_model._touchdownScored)
    {
    _kickOff.resetStep();
    s_step=STEP_KICK_OFF;
    }
    /*
     * Sinon, prochain tour
     */
    /*          else
    {
    _model.swapTeam();
    _model.initTeamTurn();
    _model._activeTeam.setTurn(_model._activeTeam.getTurn()+1);
    _turn.resetStep();
    s_step=STEP_TURN;
    }
    }
    }
    }
    }
    /*
     * Séquence d'après match
     */
    /*  if (s_step==STEP_END_MATCH)
    {
    _endMatch.nextStep();
    if (_endMatch.isFinished())
    {
    s_step=STEP_END;
    }
    }*/
    }

    public void resetStep() {

    }

    public boolean isFinished() {
        try {
            if (_model.getMainStep() == 3) {
                return true;
            } else {
                return false;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
}
