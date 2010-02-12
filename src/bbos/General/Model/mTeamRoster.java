/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Model;

import java.util.Vector;
import webbos.webbos.PlayerType;
/**
 *
 * @author frederic
 */
public class mTeamRoster 
{
    protected int id;
    protected String name;
    protected int rerollCost;
    protected boolean apothecary;
    protected int apothecaryCost;
    protected int wizardCost;
    protected boolean canRaise;
    protected int bribeCost;
    protected int chef;
    protected String logoURL;
    protected Vector playersType;
    protected String description;

    public mTeamRoster()
    {
        
    }
    
    public mTeamRoster(webbos.webbos.TeamType team)
    {
        name=team.getName();
        rerollCost=team.getRerollCost();
        apothecaryCost=team.getApothecaryCost();
        apothecary=team.isApothecary();
        wizardCost=team.getWizardCost();
        canRaise=team.isCanRaise();
        bribeCost=team.getBribeCost();
        chef=team.getChef();
        logoURL=team.getLogoURL();
        playersType=new Vector();
        description=team.getDescription();
        for (int i=0; i<team.getPlayersType().getPlayerType().size(); i++)
        {
            playersType.add(new mPlayerType((webbos.webbos.PlayerType)team.getPlayersType().getPlayerType().get(i)));
        }
        id=team.getId();
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

    
    public String getDescription()
    {
        return description;
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
     * Gets the value of the chefRaise property.
     * 
     */
    public int getChef() {
        return chef;
    }

    /**
     * Sets the value of the chefRaise property.
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
    public Vector getPlayersType() {
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
    public void setPlayersType(Vector value) {
        this.playersType = value;
    }
    
    public int getRegularPlayersNumber()
    {
        int nb=0;
        for (int i=0; i<playersType.size(); i++)
        {
            mPlayerType player=(mPlayerType)playersType.get(i);
            if (!player.isStar)
            {
                nb=nb+1;
            }
        }
        return nb;
    }
    
    public int getStarPlayersNumber()
    {
        int nb=0;
        for (int i=0; i<playersType.size(); i++)
        {
            mPlayerType player=(mPlayerType)playersType.get(i);
            if (player.isStar)
            {
                nb=nb+1;
            }
        }
        return nb;
    }
    
    public mPlayerType getPlayerType(int n)
    {
        int nb=0;
        for (int i=0; i<playersType.size(); i++)
        {
            mPlayerType player=(mPlayerType)playersType.get(i);
            if (!player.isStar)
            {
                if (nb==n)
                {
                    return player;
                }
                nb=nb+1;
            }
        }
        return null;
    }
    
    public mPlayerType getStarPlayer(int n)
    {
        int nb=0;
        for (int i=0; i<playersType.size(); i++)
        {
            mPlayerType player=(mPlayerType)playersType.get(i);
            if (player.isStar)
            {
                if (nb==n)
                {
                    return player;
                }
                nb=nb+1;
            }
        }
        return null;
    }
    
        
    public mPlayerType getPlayerType(String position)
    {
        for (int i=0; i<playersType.size(); i++)
        {
            String tmp=((mPlayerType)playersType.get(i)).getPosition();
            if (position.equals(tmp))
            {
                return (mPlayerType)playersType.get(i);
            }
        }
        return null;
    }
    
    public int getId()
    {
        return id;
    }
}
