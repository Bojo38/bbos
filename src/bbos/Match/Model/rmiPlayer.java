/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match.Model;

import bbos.Match.Model.Competences.dCompetence;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.ImageIcon;

/**
 *
 * @author root
 */
public interface rmiPlayer extends Remote {

    public String getName() throws RemoteException;
    public String getPosition() throws RemoteException;
    public int getNumber() throws RemoteException;
    public int getArmor() throws RemoteException;
    public int getMovement() throws RemoteException;
    public int getAgility() throws RemoteException;
    public int getStrength() throws RemoteException;
    public int getCost() throws RemoteException;
    public Vector<dCompetence> getCompetences() throws RemoteException;
    public void setCost(int cost) throws RemoteException;
    public void addCompetence(dCompetence comp) throws RemoteException;
    public void setName(String name) throws RemoteException;
    public boolean isOnPitch() throws RemoteException;
    public void setOnPitch(boolean value) throws RemoteException;
    public int getState() throws RemoteException;
    public int getX() throws RemoteException;
    public int getY() throws RemoteException;
    public void setX(int x) throws RemoteException;
    public void setY(int y) throws RemoteException;
    public boolean isActive() throws RemoteException;
    public boolean isPlaying() throws RemoteException;
    public void isPlaying(boolean value) throws RemoteException;
    public void isActive(boolean value) throws RemoteException;
    public boolean hasPlayed() throws RemoteException;
    public void hasPlayed(boolean value) throws RemoteException;
    public String getActiveIcone() throws RemoteException;
    public String getPassiveIcone() throws RemoteException;
    public boolean isStarPlayer() throws RemoteException;
    public int getTackleZoneNumber() throws RemoteException;    
    public int getTackleZoneNumber(dSquare s) throws RemoteException;    
    public int getAdjacentOpponentNumber() throws RemoteException;
    public void setState(int state) throws RemoteException;
    public void setBall(boolean value) throws RemoteException;
    public boolean hasBall() throws RemoteException;
    public void setBomb(boolean value) throws RemoteException;
    public boolean hasBomb() throws RemoteException;
    public int[] getAvailableActions() throws RemoteException;
    public Vector getAroundSquares() throws RemoteException;
    public Vector getFanaticSquares() throws RemoteException;
    public int getDiceForBlock(dSquare s) throws RemoteException;
    public boolean canFoul(dSquare s) throws RemoteException;
    public boolean canHandOff(dSquare s) throws RemoteException;
    public boolean canHypno(dSquare s) throws RemoteException;
    public boolean isAnOpponent(dSquare s) throws RemoteException;
    public boolean isNeighbourRightStuff(dSquare s) throws RemoteException;
    public boolean isAdjacent(rmiPlayer p) throws RemoteException;
    public boolean canGetBall() throws RemoteException;
    public boolean isChallenger() throws RemoteException;
    public Vector getPushedSquares(int X, int Y) throws RemoteException;
    public boolean mustFollow() throws RemoteException;
    public double getDistance(rmiPlayer player) throws RemoteException;
}
