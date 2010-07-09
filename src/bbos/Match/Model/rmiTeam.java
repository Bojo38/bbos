/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.Match.Model;

import bbos.General.Model.mPlayer;
import bbos.General.Model.mPlayerType;
import bbos.Match.Model.Inducements.dInducement;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author root
 */
public interface rmiTeam extends Remote{

    public boolean isPettyCashChosen()throws RemoteException;    
    public void setPettyCashChosen(boolean petty)throws RemoteException;
    public int getPomspoms() throws RemoteException;
    public int getAssists() throws RemoteException;
    public int getId() throws RemoteException;
    public int getRaceId()throws RemoteException;   
    public int getPopFactor() throws RemoteException;
    public int getPublic()throws RemoteException;
    public int getFAME() throws RemoteException;
    public void setPublic(int v_public)throws RemoteException;
    public void setFAME(int FAME)throws RemoteException;
    public long getRanking() throws RemoteException;  
    public long getMatchRanking() throws RemoteException;
    public long getTreasury()throws RemoteException;
    public long getPettyCash()throws RemoteException;    
    public void setTreasury(long treasury)throws RemoteException;    
    public void setPettyCash(long petty)throws RemoteException;
    public boolean areInducementsChosen()throws RemoteException;
    public void setInducementsChosen(boolean chosen)throws RemoteException;
    //public Vector getPlayers()throws RemoteException;
    public dPlayer getPlayerCopy(int i) throws RemoteException;
    public int getPlayersNumber() throws RemoteException;
    public void addInducement(dInducement inducement)throws RemoteException;
    public Vector getInducements()throws RemoteException;
    public void buildTeam()throws RemoteException;
    public boolean hasApothecary() throws RemoteException;;
    public int getRerollLeft() throws RemoteException;
    public int getScore()throws RemoteException;             
    public int getTurn() throws RemoteException;
    public String getRace() throws RemoteException;
    public int getPlayerNumber(int X, int Y) throws RemoteException;
    public boolean arePlayersOnThePitch() throws RemoteException;
    public void setPlayersOnThePitch(boolean value) throws RemoteException;
    public String getName()throws RemoteException;
    public int isSetOnThePitch(int side)throws RemoteException;
    public boolean isAPlayer(dSquare s)throws RemoteException;
    public boolean isAPlayerWithTackleZone(dSquare s)throws RemoteException;
    public void setRerollLeft(int reroll)throws RemoteException;
    public void setPlayerState(int i,int state)throws RemoteException;
    public void setTurn(int turn) throws RemoteException;
    public int getPlayersOK() throws RemoteException;
    public void addPlayer(dPlayer player) throws RemoteException;
    public void rollsOver() throws RemoteException;
    public boolean isTouchdown() throws RemoteException;
    public void rollsKOs() throws RemoteException;
    public void setTeamHasPlayed(boolean value) throws RemoteException;
    
    public void setHandOffDone(boolean value) throws RemoteException;
    public void setPassDone(boolean value) throws RemoteException;
    public void setBlitzDone(boolean value) throws RemoteException;
    public void setFoulDone(boolean value) throws RemoteException;
    public boolean isHandOffDone() throws RemoteException;
    public boolean isPassDone() throws RemoteException;
    public boolean isBlitzDone() throws RemoteException;
    public boolean isFoolDone() throws RemoteException;

}
