
package webbos.webbos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for Match complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Match">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OpponentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OpponentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LeagueId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Round" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MyScore" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OpponentScore" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="WinnerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ExtraTime" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Public" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MyFAME" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OpponentFAME" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MyWinnings" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OpponentWinnings" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Actions" type="{http://Webbos/Webbos}ActionArray"/>
 *         &lt;element name="ChallengerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Data" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Match", propOrder = {

})
public class Match {

    @XmlElement(name = "Id")
    protected int id;
    @XmlElement(name = "State")
    protected int state;
    @XmlElement(name = "OpponentId")
    protected int opponentId;
    @XmlElement(name = "OpponentName", required = true)
    protected String opponentName;
    @XmlElement(name = "Date", required = true)
    protected String date;
    @XmlElement(name = "LeagueId")
    protected int leagueId;
    @XmlElement(name = "Round")
    protected int round;
    @XmlElement(name = "MyScore")
    protected int myScore;
    @XmlElement(name = "OpponentScore")
    protected int opponentScore;
    @XmlElement(name = "WinnerId")
    protected int winnerId;
    @XmlElement(name = "ExtraTime")
    protected boolean extraTime;
    @XmlElement(name = "Public")
    protected int _public;
    @XmlElement(name = "MyFAME")
    protected int myFAME;
    @XmlElement(name = "OpponentFAME")
    protected int opponentFAME;
    @XmlElement(name = "MyWinnings")
    protected int myWinnings;
    @XmlElement(name = "OpponentWinnings")
    protected int opponentWinnings;
    @XmlElement(name = "Actions", required = true)
    protected ActionArray actions;
    @XmlElement(name = "ChallengerId")
    protected int challengerId;
    @XmlElement(name = "Data", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] data;

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
     * Gets the value of the state property.
     * 
     */
    public int getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     */
    public void setState(int value) {
        this.state = value;
    }

    /**
     * Gets the value of the opponentId property.
     * 
     */
    public int getOpponentId() {
        return opponentId;
    }

    /**
     * Sets the value of the opponentId property.
     * 
     */
    public void setOpponentId(int value) {
        this.opponentId = value;
    }

    /**
     * Gets the value of the opponentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpponentName() {
        return opponentName;
    }

    /**
     * Sets the value of the opponentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpponentName(String value) {
        this.opponentName = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the leagueId property.
     * 
     */
    public int getLeagueId() {
        return leagueId;
    }

    /**
     * Sets the value of the leagueId property.
     * 
     */
    public void setLeagueId(int value) {
        this.leagueId = value;
    }

    /**
     * Gets the value of the round property.
     * 
     */
    public int getRound() {
        return round;
    }

    /**
     * Sets the value of the round property.
     * 
     */
    public void setRound(int value) {
        this.round = value;
    }

    /**
     * Gets the value of the myScore property.
     * 
     */
    public int getMyScore() {
        return myScore;
    }

    /**
     * Sets the value of the myScore property.
     * 
     */
    public void setMyScore(int value) {
        this.myScore = value;
    }

    /**
     * Gets the value of the opponentScore property.
     * 
     */
    public int getOpponentScore() {
        return opponentScore;
    }

    /**
     * Sets the value of the opponentScore property.
     * 
     */
    public void setOpponentScore(int value) {
        this.opponentScore = value;
    }

    /**
     * Gets the value of the winnerId property.
     * 
     */
    public int getWinnerId() {
        return winnerId;
    }

    /**
     * Sets the value of the winnerId property.
     * 
     */
    public void setWinnerId(int value) {
        this.winnerId = value;
    }

    /**
     * Gets the value of the extraTime property.
     * 
     */
    public boolean isExtraTime() {
        return extraTime;
    }

    /**
     * Sets the value of the extraTime property.
     * 
     */
    public void setExtraTime(boolean value) {
        this.extraTime = value;
    }

    /**
     * Gets the value of the public property.
     * 
     */
    public int getPublic() {
        return _public;
    }

    /**
     * Sets the value of the public property.
     * 
     */
    public void setPublic(int value) {
        this._public = value;
    }

    /**
     * Gets the value of the myFAME property.
     * 
     */
    public int getMyFAME() {
        return myFAME;
    }

    /**
     * Sets the value of the myFAME property.
     * 
     */
    public void setMyFAME(int value) {
        this.myFAME = value;
    }

    /**
     * Gets the value of the opponentFAME property.
     * 
     */
    public int getOpponentFAME() {
        return opponentFAME;
    }

    /**
     * Sets the value of the opponentFAME property.
     * 
     */
    public void setOpponentFAME(int value) {
        this.opponentFAME = value;
    }

    /**
     * Gets the value of the myWinnings property.
     * 
     */
    public int getMyWinnings() {
        return myWinnings;
    }

    /**
     * Sets the value of the myWinnings property.
     * 
     */
    public void setMyWinnings(int value) {
        this.myWinnings = value;
    }

    /**
     * Gets the value of the opponentWinnings property.
     * 
     */
    public int getOpponentWinnings() {
        return opponentWinnings;
    }

    /**
     * Sets the value of the opponentWinnings property.
     * 
     */
    public void setOpponentWinnings(int value) {
        this.opponentWinnings = value;
    }

    /**
     * Gets the value of the actions property.
     * 
     * @return
     *     possible object is
     *     {@link ActionArray }
     *     
     */
    public ActionArray getActions() {
        return actions;
    }

    /**
     * Sets the value of the actions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionArray }
     *     
     */
    public void setActions(ActionArray value) {
        this.actions = value;
    }

    /**
     * Gets the value of the challengerId property.
     * 
     */
    public int getChallengerId() {
        return challengerId;
    }

    /**
     * Sets the value of the challengerId property.
     * 
     */
    public void setChallengerId(int value) {
        this.challengerId = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getData() {
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
    public void setData(byte[] value) {
        this.data = ((byte[]) value);
    }

}
