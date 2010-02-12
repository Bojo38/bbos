
package webbos.webbos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlayerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlayerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Position" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Cost" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Movement" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Strength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Agility" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Armor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Limit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IsStar" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Competences" type="{http://Webbos/Webbos}StringArray"/>
 *         &lt;element name="SimpleRoll" type="{http://Webbos/Webbos}StringArray"/>
 *         &lt;element name="DoubleRoll" type="{http://Webbos/Webbos}StringArray"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlayerType", propOrder = {

})
public class PlayerType {

    @XmlElement(name = "Id")
    protected int id;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Position", required = true)
    protected String position;
    @XmlElement(name = "Cost")
    protected int cost;
    @XmlElement(name = "Movement")
    protected int movement;
    @XmlElement(name = "Strength")
    protected int strength;
    @XmlElement(name = "Agility")
    protected int agility;
    @XmlElement(name = "Armor")
    protected int armor;
    @XmlElement(name = "Limit")
    protected int limit;
    @XmlElement(name = "IsStar")
    protected boolean isStar;
    @XmlElement(name = "Competences", required = true)
    protected StringArray competences;
    @XmlElement(name = "SimpleRoll", required = true)
    protected StringArray simpleRoll;
    @XmlElement(name = "DoubleRoll", required = true)
    protected StringArray doubleRoll;

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
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosition(String value) {
        this.position = value;
    }

    /**
     * Gets the value of the cost property.
     * 
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets the value of the cost property.
     * 
     */
    public void setCost(int value) {
        this.cost = value;
    }

    /**
     * Gets the value of the movement property.
     * 
     */
    public int getMovement() {
        return movement;
    }

    /**
     * Sets the value of the movement property.
     * 
     */
    public void setMovement(int value) {
        this.movement = value;
    }

    /**
     * Gets the value of the strength property.
     * 
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Sets the value of the strength property.
     * 
     */
    public void setStrength(int value) {
        this.strength = value;
    }

    /**
     * Gets the value of the agility property.
     * 
     */
    public int getAgility() {
        return agility;
    }

    /**
     * Sets the value of the agility property.
     * 
     */
    public void setAgility(int value) {
        this.agility = value;
    }

    /**
     * Gets the value of the armor property.
     * 
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Sets the value of the armor property.
     * 
     */
    public void setArmor(int value) {
        this.armor = value;
    }

    /**
     * Gets the value of the limit property.
     * 
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets the value of the limit property.
     * 
     */
    public void setLimit(int value) {
        this.limit = value;
    }

    /**
     * Gets the value of the isStar property.
     * 
     */
    public boolean isIsStar() {
        return isStar;
    }

    /**
     * Sets the value of the isStar property.
     * 
     */
    public void setIsStar(boolean value) {
        this.isStar = value;
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
     * Gets the value of the simpleRoll property.
     * 
     * @return
     *     possible object is
     *     {@link StringArray }
     *     
     */
    public StringArray getSimpleRoll() {
        return simpleRoll;
    }

    /**
     * Sets the value of the simpleRoll property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringArray }
     *     
     */
    public void setSimpleRoll(StringArray value) {
        this.simpleRoll = value;
    }

    /**
     * Gets the value of the doubleRoll property.
     * 
     * @return
     *     possible object is
     *     {@link StringArray }
     *     
     */
    public StringArray getDoubleRoll() {
        return doubleRoll;
    }

    /**
     * Sets the value of the doubleRoll property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringArray }
     *     
     */
    public void setDoubleRoll(StringArray value) {
        this.doubleRoll = value;
    }

}
