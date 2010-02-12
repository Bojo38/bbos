/*
 * dcChainsaw.java
 *
 * Created on 28 novembre 2007, 13:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Competences.Extraordinary;

import bbos.Match.Model.Actions.dAction;
import bbos.Match.Model.Actions.daBlitz;
import bbos.Match.Model.Actions.daBlitzStab;
import bbos.Match.Model.Actions.daBlock;
import bbos.Match.Model.Actions.daBlockStab;
import bbos.Match.Model.Competences.dCompetence;
import bbos.Match.Model.Competences.dCompetencesFactory;
import bbos.Match.Model.Roll.drArmor;
import bbos.Match.Model.Roll.drInjury;
import bbos.Match.Model.Roll.iRoll;
import bbos.Match.Model.dPlayer;
import java.util.Vector;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiTeam;
import bbos.Match.Model.rmiPlayer;
import bbos.Tools.bbTool;
import java.rmi.RemoteException;

/**
 *
 * @author Administrateur
 */
public class dcChainsaw extends bbos.Match.Model.Competences.dCompetence {

    /** Creates a new instance of dcChainsaw */
    public dcChainsaw(String name) {
        super(name);
        _type = dCompetencesFactory.Extraordinary;
    }

    public boolean canReroll(int RollType, rmiPlayer player) {
        return false;
    }

    public Vector modifyActionList(Vector actionList, rmiMatch model, rmiPlayer player, rmiTeam opponent, rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers, boolean challenger) {
        for (int i = 0; i < actionList.size(); i++) {
            dAction d = (dAction) actionList.get(i);
            if ((d instanceof daBlock) || (d instanceof daBlitz)) {
                actionList.remove(d);
            }
        }

        daBlockStab a1 = new daBlockStab(model, player, opponent, myTeam, opponentPlayers, myPlayers, challenger);
        daBlitzStab a2 = new daBlitzStab(model, player, opponent, myTeam, opponentPlayers, myPlayers, challenger);

        actionList.add(a1);
        actionList.add(a2);

        return actionList;
    }

    public boolean canDoAction(int actionType, rmiPlayer player, Vector myPlayers) {
        return true;
    }

    public boolean ignoreModifiers(int rollType, int modifierTypes) {
        return false;
    }

    public boolean preRoll(int actionType, int rollTypes, rmiPlayer player) {

        if ((actionType == dAction.C_BLITZ_STAB) || (actionType == dAction.C_BLOCK_STAB)) {
            int roll = bbTool.getd6();
            if (roll == 1) {
                drArmor dra = new drArmor();
                dra.addModifiers(2);
                roll = dra.rollDices();
                if (dra.isSuccess(player, roll)) {
                    drInjury dri = new drInjury();
                    roll = dri.rollDices();
                    dri.applyEffects(roll, player);
                }
                return false;
            }
        }
        return true;
    }

    public boolean isUsable(int actionType, rmiPlayer player, rmiPlayer opp) {
        return true;
    }

    public int getOpponentRollModifier(int rollType, rmiPlayer opponent, rmiPlayer myPlayer) {
        return 0;
    }

    public int modifyRollValue(int rollType, int rollValue, int actionType) {

        if (rollType == iRoll.C_ARMOR) {
            if ((actionType == dAction.C_BLITZ_STAB) || (actionType == dAction.C_BLOCK_STAB) || (actionType == dAction.C_FOUL)) {
                return rollValue + 2;
            }
        }
        return rollValue;
    }
}
