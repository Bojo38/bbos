
package webbos.webbos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Team complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Team">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Reroll" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Apothecary" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Treasury" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Cheerleaders" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Assists" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Popularity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RaceId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Players" type="{http://Webbos/Webbos}PlayerArray"/>
 *         &lt;element name="LeagueId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Matches" type="{http://Webbos/Webbos}MatchArray"/>
 *         &lt;element name="Coach" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Team", propOrder = {

})
public class Team {

    @XmlElement(name = "Id")
    protected int id;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Reroll")
    protected int reroll;
    @XmlElement(name = "Apothecary")
    protected boolean apothecary;
    @XmlElement(name = "Treasury")
    protected int treasury;
    @XmlElement(name = "Cheerleaders")
    protected int cheerleaders;
    @XmlElement(name = "Assists")
    protected int assists;
    @XmlElement(name = "Popularity")
    protected int popularity;
    @XmlElement(name = "RaceId")
    protected int raceId;
    @XmlElement(name = "Players", required = true)
    protected PlayerArray players;
    @XmlElement(name = "LeagueId")
    protected int leagueId;
    @XmlElement(name = "Matches", required = true)
    protected MatchArray matches;
    @XmlElement(name = "Coach", required = true)
    protected String coach;

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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the reroll property.
     * 
     */
    public int getReroll() {
        return reroll;
    }

    /**
     * Sets the value of the reroll property.
     * 
     */
    public void setReroll(int value) {
        this.reroll = value;
    }

    /**
     * Gets the value of the apothecary property.
     * 
     */
    public boolean isApothecary() {
        return apothecary;
    }

    /**
     * Sets the value of the apothecary property.
     * 
     */
    public void setApothecary(boolean value) {
        this.apothecary = value;
    }

    /**
     * Gets the value of the treasury property.
     * 
     */
    public int getTreasury() {
        return treasury;
    }

    /**
     * Sets the value of the treasury property.
     * 
     */
    public void setTreasury(int value) {
        this.treasury = value;
    }

    /**
     * Gets the value of the cheerleaders property.
     * 
     */
    public int getCheerleaders() {
        return cheerleaders;
    }

    /**
     * Sets the value of the cheerleaders property.
     * 
     */
    public void setCheerleaders(int value) {
        this.cheerleaders = value;
    }

    /**
     * Gets the value of the assists property.
     * 
     */
    public int getAssists() {
        return assists;
    }

    /**
     * Sets the value of the assists property.
     * 
     */
    public void setAssists(int value) {
        this.assists = value;
    }

    /**
     * Gets the value of the popularity property.
     * 
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     * Sets the value of the popularity property.
     * 
     */
    public void setPopularity(int value) {
        this.popularity = value;
    }

    /**
     * Gets the value of the raceId property.
     * 
     */
    public int getRaceId() {
        return raceId;
    }

    /**
     * Sets the value of the raceId property.
     * 
     */
    public void setRaceId(int value) {
        this.raceId = value;
    }

    /**
     * Gets the value of the players property.
     * 
     * @return
     *     possible object is
     *     {@link PlayerArray }
     *     
     */
    public PlayerArray getPlayers() {
        return players;
    }

    /**
     * Sets the value of the players property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlayerArray }
     *     
     */
    public void setPlayers(PlayerArray value) {
        this.players = value;
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
     * Gets the value of the matches property.
     * 
     * @return
     *     possible object is
     *     {@link MatchArray }
     *     
     */
    public MatchArray getMatches() {
        return matches;
    }

    /**
     * Sets the value of the matches property.
     * 
     * @param value
     *     allowed object is
     *     {@link MatchArray }
     *     
     */
    public void setMatches(MatchArray value) {
        this.matches = value;
    }

    /**
     * Gets the value of the coach property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoach() {
        return coach;
    }

    /**
     * Sets the value of the coach property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoach(String value) {
        this.coach = value;
    }

}
