/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Views;

import bbos.General.Model.mTeam;
import bbos.MainForm;
import bbos.mBBoS;

/**
 *
 * @author root
 */
public class tModelRefresh extends Thread {

    static tModelRefresh _singleton;

    public static tModelRefresh getSingleton() {
        if (_singleton == null) {
            _singleton = new tModelRefresh();
        }
        return _singleton;
    }
    public boolean _stop;

    tModelRefresh() {
        _stop = false;
    }

    public void run() {
        while (!_stop) {
            try {
                sleep(15000);
            } catch (InterruptedException e) {

            }
            /**
             * Refresh Match data of each team
             */
           /* for (int i = 0; i < mBBoS.getSingleton().getMyTeams().size(); i++) {
                mTeam team = (mTeam) mBBoS.getSingleton().getMyTeams().get(i);
                mTeam newTeam = mBBoS.getSingleton().getTeam(team.getId());
                team.setMatches(newTeam.getMatches());
                MainForm.getSingleton().refresh();
            }*/
        }

    }
}
