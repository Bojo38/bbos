/*
 * drAgility.java
 *
 * Created on December 2, 2007, 6:37 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Roll;

import bbos.Tools.bbTool;

import bbos.*;
import bbos.Match.Model.rmiPlayer;
import java.rmi.RemoteException;

/**
 *
 * @author moi
 */
public class drAgility implements iRoll {

    protected int _modifier;
    protected rmiPlayer _player;
    protected int _owner;

    /** Creates a new instance of drAgility */
    public drAgility(rmiPlayer player) {
        _player = player;
        try {
            if (player.isChallenger()) {
                _owner = 1;
            } else {
                _owner = 2;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int rollDices() {
        int n = bbTool.getd6();
        if (n == 1) {
            return 1;
        } else {
            if (n == 6) {
                return 6;
            } else {
                return n + _modifier;
            }
        }
    }

    public void addModifiers(int mod) {
        _modifier = _modifier + mod;
    }

    public boolean isSuccess(int roll) {
        int target = 0;
        try {
            target = 7 - _player.getAgility();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (roll == 1) {
            return false;
        }

        if (roll == 6) {
            return true;
        }

        if (roll+_modifier >= target) {
            return true;
        } else {
            return false;
        }
    }
}
