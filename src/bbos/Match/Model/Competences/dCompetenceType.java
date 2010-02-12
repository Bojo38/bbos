/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match.Model.Competences;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author frederic
 */
public class dCompetenceType implements Serializable {

    protected String _name;

    public dCompetenceType(String Name) {
        _name = Name;
    }

    public String getName() {
        return _name;
    }

    public String getAccronym() {
        return _name.substring(0, 1);
    }

    public Vector getCompetenceStringList() {
        Vector comps = new Vector();
        if (_name.equals("Agility")) {
            comps.add("Catch");
            comps.add("Diving catch");
            comps.add("Diving tackle");
            comps.add("Dodge");
            comps.add("Dump off");
            comps.add("Jump up");
            comps.add("Leap");
            comps.add("Side step");
            comps.add("Sneaky gits");
            comps.add("Sprint");
            comps.add("Sure feet");

        }

        if (_name.equals("Attributes")) {
            comps.add("+1 Strength");
            comps.add("+1 Agility");
            comps.add("+1 Armor");
            comps.add("+1 Movement");
        }

        if (_name.equals("Extraordinary")) {
            comps.add("Always hungry");
            comps.add("Animosity");
            comps.add("Ball and chain");
            comps.add("Blood lust");
            comps.add("Bombardier");
            comps.add("Bone head");
            comps.add("Chainsaw");
            comps.add("Decay");
            comps.add("Fan favorite");
            comps.add("Hypnotic gaze");
            comps.add("Loner");
            comps.add("No hand");
            comps.add("Nurgle's rot");
            comps.add("Really stupid");
            comps.add("Regeneration");
            comps.add("Right stuff");
            comps.add("Secret weapon");
            comps.add("Stab");
            comps.add("Stakes");
            comps.add("Stunty");
            comps.add("Take root");
            comps.add("Titchy");
            comps.add("Wild animal");
        }

        if (_name.equals("General")) {
            comps.add("Block");
            comps.add("Dauntless");
            comps.add("Dirty player");
            comps.add("Fend");
            comps.add("Frenzy");
            comps.add("Kick");
            comps.add("Kick-off return");
            comps.add("Pass block");
            comps.add("Pro");
            comps.add("Shadowing");
            comps.add("Strip ball");
            comps.add("Sure hands");
            comps.add("Tackle");
            comps.add("Wrestle");
        }


        if (_name.equals("Mutation")) {
            comps.add("Big hand");
            comps.add("Claws");
            comps.add("Disturbing presence");
            comps.add("Extra arm");
            comps.add("Foul appearance");
            comps.add("Horns");
            comps.add("Prehensile tail");
            comps.add("Tentacles");
            comps.add("Two heads");
            comps.add("Very long legs");
        }

        if (_name.equals("Pass")) {
            comps.add("Accurate");
            comps.add("Hail Mary pass");
            comps.add("Leader");
            comps.add("Nerves of steel");
            comps.add("Pass");
            comps.add("Safe throw");
        }

        if (_name.equals("Strength")) {
            comps.add("Break tackle");
            comps.add("Grab");
            comps.add("Guard");
            comps.add("Juggernaut");
            comps.add("Mighty blow");
            comps.add("Multiple block");
            comps.add("Piling on");
            comps.add("Stand firm");
            comps.add("Strong arm");
            comps.add("Thick skull");
            comps.add("Throw a team mate");
        }
        return comps;
    }
}
