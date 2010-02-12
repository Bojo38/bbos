
package webbos.webbos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TeamType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TeamType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RerollCost" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Apothecary" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ApothecaryCost" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="WizardCost" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CanRaise" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="BribeCost" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Chef" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LogoURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PlayersType" type="{http://Webbos/Webbos}PlayerTypeArray"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TeamType", propOrder = {

})
public class TeamType {

    @XmlElement(name = "Id")
    protected int id;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "RerollCost")
    protected int rerollCost;
    @XmlElement(name = "Apothecary")
    protected boolean apothecary;
    @XmlElement(name = "ApothecaryCost")
    protected int apothecaryCost;
    @XmlElement(name = "WizardCost")
    protected int wizardCost;
    @XmlElement(name = "CanRaise")
    protected boolean canRaise;
    @XmlElement(name = "BribeCost")
    protected int bribeCost;
    @XmlElement(name = "Chef")
    protected int chef;
    @XmlElement(name = "LogoURL", required = true)
    protected String logoURL;
    @XmlElement(name = "PlayersType", required = true)
    protected PlayerTypeArray playersType;
    @XmlElement(name = "Description", required = true)
    protected String description;

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
     * Gets the value of the rerollCost property.
     * 
     */
    public int getRerollCost() {
        return rerollCost;
    }

    /**
     * Sets the value of the rerollCost property.
     * 
     */
    public void setRerollCost(int value) {
        this.rerollCost = value;
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
     * Gets the value of the apothecaryCost property.
     * 
     */
    public int getApothecaryCost() {
        return apothecaryCost;
    }

    /**
     * Sets the value of the apothecaryCost property.
     * 
     */
    public void setApothecaryCost(int value) {
        this.apothecaryCost = value;
    }

    /**
     * Gets the value of the wizardCost property.
     * 
     */
    public int getWizardCost() {
        return wizardCost;
    }

    /**
     * Sets the value of the wizardCost property.
     * 
     */
    public void setWizardCost(int value) {
        this.wizardCost = value;
    }

    /**
     * Gets the value of the canRaise property.
     * 
     */
    public boolean isCanRaise() {
        return canRaise;
    }

    /**
     * Sets the value of the canRaise property.
     * 
     */
    public void setCanRaise(boolean value) {
        this.canRaise = value;
    }

    /**
     * Gets the value of the bribeCost property.
     * 
     */
    public int getBribeCost() {
        return bribeCost;
    }

    /**
     * Sets the value of the bribeCost property.
     * 
     */
    public void setBribeCost(int value) {
        this.bribeCost = value;
    }

    /**
     * Gets the value of the chef property.
     * 
     */
    public int getChef() {
        return chef;
    }

    /**
     * Sets the value of the chef property.
     * 
     */
    public void setChef(int value) {
        this.chef = value;
    }

    /**
     * Gets the value of the logoURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoURL() {
        return logoURL;
    }

    /**
     * Sets the value of the logoURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoURL(String value) {
        this.logoURL = value;
    }

    /**
     * Gets the value of the playersType property.
     * 
     * @return
     *     possible object is
     *     {@link PlayerTypeArray }
     *     
     */
    public PlayerTypeArray getPlayersType() {
        return playersType;
    }

    /**
     * Sets the value of the playersType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlayerTypeArray }
     *     
     */
    public void setPlayersType(PlayerTypeArray value) {
        this.playersType = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
