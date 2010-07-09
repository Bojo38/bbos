/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match.Model;

import bbos.Match.Automat.Steps.eMainStep;
import bbos.ieStepData;
import bbos.Match.Automat.Steps.SubStep.ieSubStep;
import bbos.Match.Automat.Steps.SubStep.SubSubStep.ieSubSubStep;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author root
 */
public interface rmiMatch extends Remote {

    public int getActiveTeamId() throws RemoteException;

    public eMainStep getMainStep() throws RemoteException;

    public void setMainStep(eMainStep step) throws RemoteException;

    public void setActiveCoach(int coach) throws RemoteException;

    public int getActiveCoach() throws RemoteException;

    public void setSubStep(ieSubStep step) throws RemoteException;

    public ieSubStep getSubStep() throws RemoteException;

    public void setSubSubStep(ieSubSubStep step) throws RemoteException;

    public ieSubSubStep getSubSubStep() throws RemoteException;

    public int getMeteo() throws RemoteException;

    public void setMeteo(int meteo) throws RemoteException;

    public void setKickinkgTeam(int team) throws RemoteException;

    public int getKickinkgTeam() throws RemoteException;

    public int getHalf() throws RemoteException;

    public boolean NetworkPlayersDispatched() throws RemoteException;

    public void setLeftPlayersDispatched(boolean value) throws RemoteException;

    public void setRightPlayersDispatched(boolean value) throws RemoteException;

    public boolean needLeftRefresh() throws RemoteException;

    public void setLeftRefresh(boolean value) throws RemoteException;

    public boolean needRightRefresh() throws RemoteException;

    public void setRightRefresh(boolean value) throws RemoteException;

    public String getDiary() throws RemoteException;

    public void AddDiary(String value) throws RemoteException;

    public boolean isBallSet() throws RemoteException;

    public void setWaitingBall(dSquare s) throws RemoteException;

    public void setBall(boolean value) throws RemoteException;

    public void setBall(dSquare s) throws RemoteException;

    public dSquareCollection getSquares() throws RemoteException;

    public int getSquareState(dSquare s) throws RemoteException;

    ;

    public boolean isAPlayer(dSquare s) throws RemoteException;

    public void removeBall() throws RemoteException;

    public boolean isBallPlacedOnThePitch(
            int side) throws RemoteException;

    public dSquare getBallSquare() throws RemoteException;

    public ieStepData getCurrentStepData() throws RemoteException;

    public void setCurrentStepData(ieStepData value) throws RemoteException;

    public void setSquareState(dSquare square, int state) throws RemoteException;

    public void setSquareSpecialState(dSquare square,
            boolean state) throws RemoteException;

    public boolean hasThrowableTeammate(int x, int y) throws RemoteException;

    public void resetSquareMove() throws RemoteException;

    public void resetSquareBlock() throws RemoteException;

    public void resetSquarePass() throws RemoteException;

    public void resetSquareFoul() throws RemoteException;

    public void setBlockDiceNumber(dSquare s, int number) throws RemoteException;

    public void turnover(boolean value) throws RemoteException;

    public boolean isTurnover() throws RemoteException;

    public void setHalf(int half) throws RemoteException;

    public void sendPlayersToDugout() throws RemoteException;

    public void AddRollHistory(int dice, int owner, String description) throws RemoteException;

    public void AddRollHistory(int dice1, int dice2, int owner, String description) throws RemoteException;

    public void AddBlockRollHistory(int dice, int owner, String description) throws RemoteException;

    public void AddBlockRollHistory(int dice1, int dice2, int owner, String description) throws RemoteException;

    public void AddBlockRollHistory(int dice1, int dice2, int dice3, int owner, String description) throws RemoteException;

    public Vector getDiceHistory() throws RemoteException;

    public int getDiceHistorySize() throws RemoteException;

    public void waitForDiceChoice(int chooser, int _dices, int _dice1, int _dice2, int _dice3) throws RemoteException;

    public void WaitForInterceptionChoice(int chooser) throws RemoteException;

    public boolean isWaitingForDiceChoice() throws RemoteException;

    public int getDiceBlockChooser() throws RemoteException;

    public void stopWaitingForDiceChoice(int choice) throws RemoteException;

    public int getBlockDiceChosen() throws RemoteException;

    public int getBlockDiceNumber() throws RemoteException;

    public int getBlockDiceValue(int i) throws RemoteException;

    public void displayPassRule(boolean value) throws RemoteException;

    public boolean displayPassRule() throws RemoteException;

    public int getAllowedPassRange() throws RemoteException;

    ;
}
