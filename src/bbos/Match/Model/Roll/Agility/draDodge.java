/*
 * draDodge.java
 *
 * Created on December 5, 2007, 6:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Roll.Agility;

import bbos.Match.Model.dMatch;
import bbos.Match.Model.dTeam;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.dSquare;
import bbos.Match.Views.cGround;
import bbos.*;
import bbos.Match.Model.Roll.drAgility;
import bbos.Match.Model.Roll.drArmor;
import bbos.Match.Model.Roll.drInjury;
import bbos.Match.Model.Roll.drScatter;
import bbos.Match.Model.Roll.iRoll;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import java.rmi.RemoteException;

/**
 *
 * @author moi
 */
public class draDodge extends drAgility {

    dSquare _square;
    rmiMatch _model;

    /**
     * Creates a new instance of draDodge
     */
    public draDodge(rmiPlayer player, dSquare square, rmiMatch model) {
        super(player);
        _square = square;
        _modifier = 1;
        _model = model;
    }

    public boolean applyEffects(int roll) {
        boolean success = true;

        if (isSuccess(roll)) {
            success = true;
        } else {
            try {
                _player.setX(_square.getX());
                _player.setY(_square.getY());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            failure(_player);
            success = false;
        }
        return success;
    }

    protected void failure(rmiPlayer player) {
        try {


            /*
             * Armor Roll
             */
            drArmor armor = new drArmor();
            int armorRoll = armor.rollDices();
            if (armor.isSuccess(player, armorRoll)) {
                drInjury injury = new drInjury();
                int injuryRoll = injury.rollDices();
                injury.applyEffects(injuryRoll, player);
            } else {
                player.setState(dPlayer.C_STATE_PRONE);
            }

            player.hasPlayed(true);

            if (player.hasBall()) {
                player.setBall(false);
                drScatter sc = new drScatter(_model, new dSquare(player.getX(), player.getY()), true, true);
                int roll = sc.rollDices();
                sc.applyEffects(roll);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
