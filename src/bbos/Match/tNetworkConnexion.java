/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match;

import bbos.Match.Model.dMatch;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.dTeam;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiTeam;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class tNetworkConnexion extends Thread {

    String _address;
    int _timeout;
    int _myTeamId;
    boolean _isServer = false;
    rmiMatch _model;
    rmiTeam _leftTeam;
    rmiTeam _rightTeam;
    long _ping = 0;
    boolean _sender = false;
    boolean _standalone = false;
    boolean _leftPlayersExported = false;
    boolean _rightPlayersExported = false;
    protected static tNetworkConnexion _singleton;

    public static tNetworkConnexion createConnextion(boolean standalone, boolean server, String IP, int timeout, rmiMatch model, int myTeamId) {
        _singleton = new tNetworkConnexion(standalone, server, IP, timeout, model, myTeamId);
        return _singleton;
    }

    public static tNetworkConnexion getConnexion() {
        return _singleton;
    }

    protected tNetworkConnexion(boolean standalone, boolean server, String IP, int timeout, rmiMatch model, int myTeamId) {
        _address = IP;
        _timeout = timeout;
        _isServer = server;
        _myTeamId = myTeamId;
        _sender = _isServer;
        _standalone = standalone;
        if (!standalone) {
            if (server) {
                try {
                    // Instancier un annuaire (Registry) sur la machine locale et sur le port 1099.
                    LocateRegistry.createRegistry(1099);
                    // Enregistrement de l'objet Bonjour dans l'annuaire de RMI
                    UnicastRemoteObject.exportObject(model);
                    Naming.rebind("rmi://" + IP + "/Match", model);
                    UnicastRemoteObject.exportObject(((dMatch) model).getLeftTeam());
                    Naming.rebind("rmi://" + IP + "/Left", ((dMatch) model).getLeftTeam());
                    UnicastRemoteObject.exportObject(((dMatch) model).getRightTeam());
                    Naming.rebind("rmi://" + IP + "/Right", ((dMatch) model).getRightTeam());
                    _model = model;
                    _leftTeam = ((dMatch) model).getLeftTeam();
                    _rightTeam = ((dMatch) model).getRightTeam();
                }/*catch (AlreadyBoundException e) {
                System.out.println("Erreur de publication: " + e);
                }*/ catch (RemoteException e) {
                    System.out.println("Erreur de publication: " + e);
                } catch (MalformedURLException e) {
                    System.out.println("Erreur de publication: " + e);
                }
            } else {
                try {
                    _model = (rmiMatch) Naming.lookup("rmi://" + IP + "/Match");
                    _leftTeam = (rmiTeam) Naming.lookup("rmi://" + IP + "/Left");
                    _rightTeam = (rmiTeam) Naming.lookup("rmi://" + IP + "/Right");
                } catch (MalformedURLException e) {
                    System.out.println("Erreur :" + e.getMessage());
                } catch (RemoteException e) {
                    System.out.println("Erreur :" + e.getMessage());
                } catch (NotBoundException e) {
                    System.out.println("Erreur :" + e.getMessage());
                }
            }
        } else {
            _model = model;
            _leftTeam = ((dMatch) model).getLeftTeam();
            _rightTeam = ((dMatch) model).getRightTeam();
        }
    }

    public Vector getLeftPlayers() {
        Vector players = new Vector();
        if (!_standalone) {
            if (this._isServer) {
                try {
                    if (!_leftPlayersExported) {
                        // Enregistrement de l'objet Bonjour dans l'annuaire de RMI
                        int nb = _leftTeam.getPlayersNumber();
                        for (int i = 0; i < nb; i++) {
                            dPlayer player = ((dTeam) _leftTeam).getPlayer(i);
                            UnicastRemoteObject.exportObject(player);
                            Naming.rebind("rmi://" + _address + "/LeftPlayer" + Integer.toString(i), player);
                            players.add(player);
                        }

                        _model.setLeftPlayersDispatched(true);
                        _leftPlayersExported = true;
                    } else {
                        int nb = _leftTeam.getPlayersNumber();
                        for (int i = 0; i < nb; i++) {
                            dPlayer player = ((dTeam) _leftTeam).getPlayer(i);
                            players.add(player);
                        }
                    }

                }/*catch (AlreadyBoundException e) {
                System.out.println("Erreur de publication: " + e);
                }*/ catch (RemoteException e) {
                    System.err.println("Erreur de publication: " + e);
                } catch (MalformedURLException e) {
                    System.err.println("Erreur de publication: " + e);
                }
            } else {
                try {
                    int counter = 0;
                    while ((!_model.NetworkPlayersDispatched()) && (counter < 100)) {
                        try {
                            sleep(100);
                            counter++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (counter < 100) {
                        int nb = _leftTeam.getPlayersNumber();
                        for (int i = 0; i < nb; i++) {
                            rmiPlayer player = (rmiPlayer) Naming.lookup("rmi://" + _address + "/LeftPlayer" + Integer.toString(i));
                            players.add(player);
                        }

                    } else {
                        System.err.println("Unable to get server objects");
                    }
                } catch (MalformedURLException e) {
                    System.out.println("Erreur :" + e.getMessage());
                } catch (RemoteException e) {
                    System.out.println("Erreur :" + e.getMessage());
                } catch (NotBoundException e) {
                    System.out.println("Erreur :" + e.getMessage());
                }
            }
        } else {
            try {
                int nb = _leftTeam.getPlayersNumber();
                for (int i = 0; i < nb; i++) {
                    dPlayer player = ((dTeam) _leftTeam).getPlayer(i);
                    players.add(player);
                }
            } catch (RemoteException e) {
                System.out.println("Erreur :" + e.getMessage());
            }
        }

        return players;
    }

    public Vector getRightPlayers() {
        Vector players = new Vector();
        if (!_standalone) {
            if (this._isServer) {
                try {
                    if (!_rightPlayersExported) {
                        // Enregistrement de l'objet Bonjour dans l'annuaire de RMI
                        int nb = _rightTeam.getPlayersNumber();
                        for (int i = 0; i < nb; i++) {
                            dPlayer player = ((dTeam) _rightTeam).getPlayer(i);
                            UnicastRemoteObject.exportObject(player);
                            Naming.rebind("rmi://" + _address + "/RightPlayer" + Integer.toString(i), player);
                            players.add(player);
                        }
                        _model.setRightPlayersDispatched(true);
                        _rightPlayersExported = true;
                    } else {
                        int nb = _rightTeam.getPlayersNumber();
                        for (int i = 0; i < nb; i++) {
                            dPlayer player = ((dTeam) _rightTeam).getPlayer(i);
                            players.add(player);
                        }
                    }

                }/*catch (AlreadyBoundException e) {
                System.out.println("Erreur de publication: " + e);
                }*/ catch (RemoteException e) {
                    System.err.println("Erreur de publication: " + e);
                } catch (MalformedURLException e) {
                    System.err.println("Erreur de publication: " + e);
                }
            } else {
                try {
                    int counter = 0;
                    while ((!_model.NetworkPlayersDispatched()) && (counter < 100)) {
                        try {
                            sleep(100);
                            counter++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (counter < 100) {
                        int nb = _rightTeam.getPlayersNumber();
                        for (int i = 0; i < nb; i++) {
                            rmiPlayer player = (rmiPlayer) Naming.lookup("rmi://" + _address + "/RightPlayer" + Integer.toString(i));
                            players.add(player);
                        }
                    } else {
                        System.err.println("Unable to get server objects");
                    }
                } catch (MalformedURLException e) {
                    System.out.println("Erreur :" + e.getMessage());
                } catch (RemoteException e) {
                    System.out.println("Erreur :" + e.getMessage());
                } catch (NotBoundException e) {
                    System.out.println("Erreur :" + e.getMessage());
                }
            }
        } else {
            try {
                int nb = _rightTeam.getPlayersNumber();
                for (int i = 0; i < nb; i++) {
                    dPlayer player = ((dTeam) _rightTeam).getPlayer(i);
                    players.add(player);
                }
            } catch (RemoteException e) {
                System.out.println("Erreur :" + e.getMessage());
            }
        }
        return players;
    }

    public rmiMatch getMatchModel() {
        return _model;
    }

    public rmiTeam getLeftTeam() {
        return _leftTeam;
    }

    public rmiTeam getRightTeam() {
        return _rightTeam;
    }

    public long testConnection() {
        try {
            long beginTime = System.currentTimeMillis();
            boolean status = InetAddress.getByName(_address).isReachable(_timeout);
            return System.currentTimeMillis() - beginTime;
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Unknown target address");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error with target address");
        }

        return -1;
    }

    public void run() {
        while (!_stop) {

            try {
                sleep(1500);
            } catch (InterruptedException e) {
            }
            _ping = testConnection();
        }

    }

    public long getPing() {
        return _ping;
    }
    boolean _stop = false;

    public void stopThread() {
        _stop = true;
    }
}
