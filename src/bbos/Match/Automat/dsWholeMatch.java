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
import bbos.Match.Automat.Steps.eMainStep;
import bbos.Match.Automat.Steps.SubStep.essMatch;
import bbos.Match.Automat.Steps.SubStep.essPostMatch;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiTeam;
import java.rmi.RemoteException;

/**
 *
 * @author moi
 */
public class dsWholeMatch implements bbos.Match.Automat.iSequence {


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
        eMainStep mainStep = eMainStep.PREMATCH;
        try {

            mainStep = _model.getMainStep();
        } catch (RemoteException e) {
            e.printStackTrace();

        }

        switch (mainStep) {
            /**
             * Pre match sequence
             */
            case PREMATCH:
                _beforeMatch.nextStep();
                if (_beforeMatch.isFinished()) {
                    _match.resetStep();
                    try {
                        _model.setMainStep(eMainStep.MATCH);
                        _model.setSubStep(essMatch.BUILD_TEAM);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            /**
             *  match sequence
             */
            case MATCH:
                _match.nextStep();
                if (_match.isFinished()) {
                    _endMatch.resetStep();
                    try {
                        _model.setMainStep(eMainStep.POSTMATCH);
                        _model.setSubStep(essPostMatch.NONE);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            /**
             * End match sequence
             */
            case POSTMATCH:
                _endMatch.nextStep();
                if (_endMatch.isFinished()) {
                    try {
                        _model.setMainStep(eMainStep.END);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            /**
             * final sequence
             */
            case END:
                break;
        }

        return;


    }

    public void resetStep() {

    }

    public boolean isFinished() {
        try {
            if (_model.getMainStep() == eMainStep.END) {
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
