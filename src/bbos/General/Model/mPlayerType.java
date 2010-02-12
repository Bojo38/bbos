/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.General.Model;

import bbos.Match.Model.Competences.dCompetencesFactory;
import java.util.Vector;

/**
 *
 * @author frederic
 */
public class mPlayerType {

    protected String name;
    protected String position;
    protected int cost;
    protected int movement;
    protected int strength;
    protected int agility;
    protected int armor;
    protected int limit;
    protected boolean isStar;
    protected Vector competences;
    protected Vector simpleRoll;
    protected Vector doubleRoll;
    protected int id;
    
    public mPlayerType()
    {
        
    }
    
    public mPlayerType(webbos.webbos.PlayerType player)
    {
        name=player.getName();
        position=player.getPosition();
        cost=player.getCost();
        movement=player.getMovement();
        strength=player.getStrength();
        agility=player.getAgility();
        armor=player.getArmor();
        limit=player.getLimit();
        isStar=player.isIsStar();
        
        competences=new Vector();
        simpleRoll=new Vector();
        doubleRoll=new Vector();
        
         id=player.getId();
        
        for (int i=0; i<player.getSimpleRoll().getString().size(); i++)
        {
             simpleRoll.add(dCompetencesFactory.getCompetenceType((String)player.getSimpleRoll().getString().get(i)));
        }
        
        for (int i=0; i<player.getDoubleRoll().getString().size(); i++)
        {
             doubleRoll.add(dCompetencesFactory.getCompetenceType((String)player.getDoubleRoll().getString().get(i)));
        }
        
        for (int i=0; i<player.getCompetences().getString().size(); i++)
        {
             competences.add(dCompetencesFactory.createCompetence((String)player.getCompetences().getString().get(i)));
        }
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
    public boolean isStar() {
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
     *     {@link Vector }
     *     
     */
    public Vector getCompetences() {
        return competences;
    }

    /**
     * Sets the value of the competences property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vector }
     *     
     */
    public void setCompetences(Vector value) {
        this.competences = value;
    }

    /**
     * Gets the value of the simpleRoll property.
     * 
     * @return
     *     possible object is
     *     {@link Vector }
     *     
     */
    public Vector getSimpleRoll() {
        return simpleRoll;
    }

    /**
     * Sets the value of the simpleRoll property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vector }
     *     
     */
    public void setSimpleRoll(Vector value) {
        this.simpleRoll = value;
    }

    /**
     * Gets the value of the doubleRoll property.
     * 
     * @return
     *     possible object is
     *     {@link Vector }
     *     
     */
    public Vector getDoubleRoll() {
        return doubleRoll;
    }

    /**
     * Sets the value of the doubleRoll property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vector }
     *     
     */
    public void setDoubleRoll(Vector value) {
        this.doubleRoll = value;
    }

    public int getId()
    {
        return id;
    }
}
