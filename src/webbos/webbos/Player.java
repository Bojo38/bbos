
package webbos.webbos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Player complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Player">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Ranking" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Completion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Interception" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Touchdowns" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Casualties" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MVP" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Persistant" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MissNextGame" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Competences" type="{http://Webbos/Webbos}StringArray"/>
 *         &lt;element name="Number" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Injuries" type="{http://Webbos/Webbos}StringArray"/>
 *         &lt;element name="Retired" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Dead" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Icon" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Player", propOrder = {

})
public class Player {

    @XmlElement(name = "Id")
    protected int id;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Ranking", required = true)
    protected String ranking;
    @XmlElement(name = "TypeId")
    protected int typeId;
    @XmlElement(name = "Completion")
    protected int completion;
    @XmlElement(name = "Interception")
    protected int interception;
    @XmlElement(name = "Touchdowns")
    protected int touchdowns;
    @XmlElement(name = "Casualties")
    protected int casualties;
    @XmlElement(name = "MVP")
    protected int mvp;
    @XmlElement(name = "Persistant")
    protected int persistant;
    @XmlElement(name = "MissNextGame")
    protected boolean missNextGame;
    @XmlElement(name = "Competences", required = true)
    protected StringArray competences;
    @XmlElement(name = "Number")
    protected int number;
    @XmlElement(name = "Injuries", required = true)
    protected StringArray injuries;
    @XmlElement(name = "Retired")
    protected boolean retired;
    @XmlElement(name = "Dead")
    protected boolean dead;
    @XmlElement(name = "Icon", required = true)
    protected String icon;

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
     * Gets the value of the ranking property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRanking() {
        return ranking;
    }

    /**
     * Sets the value of the ranking property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRanking(String value) {
        this.ranking = value;
    }

    /**
     * Gets the value of the typeId property.
     * 
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * Sets the value of the typeId property.
     * 
     */
    public void setTypeId(int value) {
        this.typeId = value;
    }

    /**
     * Gets the value of the completion property.
     * 
     */
    public int getCompletion() {
        return completion;
    }

    /**
     * Sets the value of the completion property.
     * 
     */
    public void setCompletion(int value) {
        this.completion = value;
    }

    /**
     * Gets the value of the interception property.
     * 
     */
    public int getInterception() {
        return interception;
    }

    /**
     * Sets the value of the interception property.
     * 
     */
    public void setInterception(int value) {
        this.interception = value;
    }

    /**
     * Gets the value of the touchdowns property.
     * 
     */
    public int getTouchdowns() {
        return touchdowns;
    }

    /**
     * Sets the value of the touchdowns property.
     * 
     */
    public void setTouchdowns(int value) {
        this.touchdowns = value;
    }

    /**
     * Gets the value of the casualties property.
     * 
     */
    public int getCasualties() {
        return casualties;
    }

    /**
     * Sets the value of the casualties property.
     * 
     */
    public void setCasualties(int value) {
        this.casualties = value;
    }

    /**
     * Gets the value of the mvp property.
     * 
     */
    public int getMVP() {
        return mvp;
    }

    /**
     * Sets the value of the mvp property.
     * 
     */
    public void setMVP(int value) {
        this.mvp = value;
    }

    /**
     * Gets the value of the persistant property.
     * 
     */
    public int getPersistant() {
        return persistant;
    }

    /**
     * Sets the value of the persistant property.
     * 
     */
    public void setPersistant(int value) {
        this.persistant = value;
    }

    /**
     * Gets the value of the missNextGame property.
     * 
     */
    public boolean isMissNextGame() {
        return missNextGame;
    }

    /**
     * Sets the value of the missNextGame property.
     * 
     */
    public void setMissNextGame(boolean value) {
        this.missNextGame = value;
    }

    /**
     * Gets the value of the competences property.
     * 
     * @return
     *     possible object is
     *     {@link StringArray }
     *     
     */
    public StringArray getCompetences() {
        return competences;
    }

    /**
     * Sets the value of the competences property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringArray }
     *     
     */
    public void setCompetences(StringArray value) {
        this.competences = value;
    }

    /**
     * Gets the value of the number property.
     * 
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     */
    public void setNumber(int value) {
        this.number = value;
    }

    /**
     * Gets the value of the injuries property.
     * 
     * @return
     *     possible object is
     *     {@link StringArray }
     *     
     */
    public StringArray getInjuries() {
        return injuries;
    }

    /**
     * Sets the value of the injuries property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringArray }
     *     
     */
    public void setInjuries(StringArray value) {
        this.injuries = value;
    }

    /**
     * Gets the value of the retired property.
     * 
     */
    public boolean isRetired() {
        return retired;
    }

    /**
     * Sets the value of the retired property.
     * 
     */
    public void setRetired(boolean value) {
        this.retired = value;
    }

    /**
     * Gets the value of the dead property.
     * 
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Sets the value of the dead property.
     * 
     */
    public void setDead(boolean value) {
        this.dead = value;
    }

    /**
     * Gets the value of the icon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets the value of the icon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIcon(String value) {
        this.icon = value;
    }

}
