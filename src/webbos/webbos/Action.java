
package webbos.webbos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Action complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Action">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="ActionType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PlayerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PlayerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PlayerNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TeamId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OpponentPlayerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OpponentPlayerNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OpponentPlayerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Data" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Turn" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ActionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Action", propOrder = {

})
public class Action {

    @XmlElement(name = "ActionType")
    protected int actionType;
    @XmlElement(name = "Id")
    protected int id;
    @XmlElement(name = "PlayerId")
    protected int playerId;
    @XmlElement(name = "PlayerName", required = true)
    protected String playerName;
    @XmlElement(name = "PlayerNumber")
    protected int playerNumber;
    @XmlElement(name = "TeamId")
    protected int teamId;
    @XmlElement(name = "OpponentPlayerId")
    protected int opponentPlayerId;
    @XmlElement(name = "OpponentPlayerNumber")
    protected int opponentPlayerNumber;
    @XmlElement(name = "OpponentPlayerName", required = true)
    protected String opponentPlayerName;
    @XmlElement(name = "Data", required = true)
    protected String data;
    @XmlElement(name = "Turn")
    protected int turn;
    @XmlElement(name = "ActionName", required = true)
    protected String actionName;

    /**
     * Gets the value of the actionType property.
     * 
     */
    public int getActionType() {
        return actionType;
    }

    /**
     * Sets the value of the actionType property.
     * 
     */
    public void setActionType(int value) {
        this.actionType = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the playerId property.
     * 
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Sets the value of the playerId property.
     * 
     */
    public void setPlayerId(int value) {
        this.playerId = value;
    }

    /**
     * Gets the value of the playerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the value of the playerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlayerName(String value) {
        this.playerName = value;
    }

    /**
     * Gets the value of the playerNumber property.
     * 
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Sets the value of the playerNumber property.
     * 
     */
    public void setPlayerNumber(int value) {
        this.playerNumber = value;
    }

    /**
     * Gets the value of the teamId property.
     * 
     */
    public int getTeamId() {
        return teamId;
    }

    /**
     * Sets the value of the teamId property.
     * 
     */
    public void setTeamId(int value) {
        this.teamId = value;
    }

    /**
     * Gets the value of the opponentPlayerId property.
     * 
     */
    public int getOpponentPlayerId() {
        return opponentPlayerId;
    }

    /**
     * Sets the value of the opponentPlayerId property.
     * 
     */
    public void setOpponentPlayerId(int value) {
        this.opponentPlayerId = value;
    }

    /**
     * Gets the value of the opponentPlayerNumber property.
     * 
     */
    public int getOpponentPlayerNumber() {
        return opponentPlayerNumber;
    }

    /**
     * Sets the value of the opponentPlayerNumber property.
     * 
     */
    public void setOpponentPlayerNumber(int value) {
        this.opponentPlayerNumber = value;
    }

    /**
     * Gets the value of the opponentPlayerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpponentPlayerName() {
        return opponentPlayerName;
    }

    /**
     * Sets the value of the opponentPlayerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpponentPlayerName(String value) {
        this.opponentPlayerName = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setData(String value) {
        this.data = value;
    }

    /**
     * Gets the value of the turn property.
     * 
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Sets the value of the turn property.
     * 
     */
    public void setTurn(int value) {
        this.turn = value;
    }

    /**
     * Gets the value of the actionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * Sets the value of the actionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionName(String value) {
        this.actionName = value;
    }

}
