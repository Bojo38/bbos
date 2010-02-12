/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match.Automat.DuringMatch;

import bbos.Match.Model.rmiMatch;
import java.rmi.RemoteException;
import javax.swing.AbstractListModel;

/**
 *
 * @author root
 */
public class jlmDiceHistorical extends AbstractListModel {

    rmiMatch _model;

    public jlmDiceHistorical(rmiMatch model) {
        _model = model;
    }

    public Object getElementAt(int index) {
        try {
            return _model.getDiceHistory().get(index);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int getSize() {
        try {
            return _model.getDiceHistorySize();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
