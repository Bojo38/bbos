
package webbos.webbos;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0_02-b08-fcs
 * Generated source version: 2.0
 * 
 */
@WebService(name = "WebbosPortType", targetNamespace = "http://Webbos/Webbos")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface WebbosPortType {


    /**
     * Retrun my teams
     * 
     * @param user
     * @return
     *     returns webbos.webbos.TeamArray
     */
    @WebMethod(action = "/service#getMyTeams")
    @WebResult(partName = "return")
    public TeamArray getMyTeams(
        @WebParam(name = "user", partName = "user")
        String user);

    /**
     * Retrun all avilable team types
     * 
     * @return
     *     returns webbos.webbos.TeamTypeArray
     */
    @WebMethod(action = "/service#getAllTeamTypes")
    @WebResult(partName = "return")
    public TeamTypeArray getAllTeamTypes();

    /**
     * Retrun team number
     * 
     * @return
     *     returns int
     */
    @WebMethod(action = "/service#getTeamTypeNumber")
    @WebResult(partName = "return")
    public int getTeamTypeNumber();

    /**
     * Return icon URL
     * 
     * @param user
     * @param pass
     * @return
     *     returns int
     */
    @WebMethod(action = "/service#isValidUser")
    @WebResult(partName = "return")
    public int isValidUser(
        @WebParam(name = "user", partName = "user")
        String user,
        @WebParam(name = "pass", partName = "pass")
        String pass);

    /**
     * Add new team
     * 
     * @param team
     * @param user
     * @return
     *     returns int
     */
    @WebMethod(action = "/service#addTeam")
    @WebResult(partName = "return")
    public int addTeam(
        @WebParam(name = "user", partName = "user")
        String user,
        @WebParam(name = "team", partName = "team")
        Team team);

    /**
     * Return 1 if soap is OK
     * 
     * @return
     *     returns int
     */
    @WebMethod(action = "/service#soapTest")
    @WebResult(partName = "return")
    public int soapTest();

    /**
     * retire player
     * 
     * @param id
     * @return
     *     returns int
     */
    @WebMethod(action = "/service#retirePlayer")
    @WebResult(partName = "return")
    public int retirePlayer(
        @WebParam(name = "id", partName = "id")
        int id);

    /**
     * retire player
     * 
     * @param id
     * @return
     *     returns int
     */
    @WebMethod(action = "/service#removeTeam")
    @WebResult(partName = "return")
    public int removeTeam(
        @WebParam(name = "id", partName = "id")
        int id);

    /**
     * update team
     * 
     * @param team
     * @return
     *     returns int
     */
    @WebMethod(action = "/service#updateTeam")
    @WebResult(partName = "return")
    public int updateTeam(
        @WebParam(name = "team", partName = "team")
        Team team);

    /**
     * add new player
     * 
     * @param player
     * @param teamId
     * @return
     *     returns int
     */
    @WebMethod(action = "/service#addNewPlayer")
    @WebResult(partName = "return")
    public int addNewPlayer(
        @WebParam(name = "team_id", partName = "team_id")
        int teamId,
        @WebParam(name = "player", partName = "player")
        Player player);

    /**
     * return a team
     * 
     * @param teamId
     * @return
     *     returns webbos.webbos.Team
     */
    @WebMethod(action = "/service#getTeam")
    @WebResult(partName = "return")
    public Team getTeam(
        @WebParam(name = "team_id", partName = "team_id")
        int teamId);

    /**
     * get challengeable teams
     * 
     * @param leagueId
     * @return
     *     returns webbos.webbos.TeamArray
     */
    @WebMethod(action = "/service#getTeamsForChallenge")
    @WebResult(partName = "return")
    public TeamArray getTeamsForChallenge(
        @WebParam(name = "leagueId", partName = "leagueId")
        int leagueId);

    /**
     * create new challenge
     * 
     * @param leagueId
     * @param opponentId
     * @param challengerId
     * @return
     *     returns int
     */
    @WebMethod(action = "/service#newChallenge")
    @WebResult(partName = "return")
    public int newChallenge(
        @WebParam(name = "challengerId", partName = "challengerId")
        int challengerId,
        @WebParam(name = "opponentId", partName = "opponentId")
        int opponentId,
        @WebParam(name = "leagueId", partName = "leagueId")
        int leagueId);

    /**
     * cancel match
     * 
     * @param matchId
     * @return
     *     returns int
     */
    @WebMethod(action = "/service#cancelMatch")
    @WebResult(partName = "return")
    public int cancelMatch(
        @WebParam(name = "matchId", partName = "matchId")
        int matchId);

    /**
     * accept match
     * 
     * @param matchId
     * @return
     *     returns int
     */
    @WebMethod(action = "/service#acceptMatch")
    @WebResult(partName = "return")
    public int acceptMatch(
        @WebParam(name = "matchId", partName = "matchId")
        int matchId);

    /**
     * save match data
     * 
     * @param matchId
     * @param data
     * @return
     *     returns int
     */
    @WebMethod(action = "/service#acceptMatch")
    @WebResult(partName = "return")
    public int saveMatchData(
        @WebParam(name = "matchId", partName = "matchId")
        int matchId,
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @WebParam(name = "Data", partName = "Data")
        byte[] data);

}