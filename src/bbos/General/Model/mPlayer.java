/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Model;

import bbos.Match.Model.Competences.dCompetence;
import bbos.Match.Model.Competences.dCompetenceType;
import bbos.Match.Model.Competences.dCompetencesFactory;
import java.util.StringTokenizer;
import java.util.Vector;
import webbos.webbos.Player;

/**
 *
 * @author root
 */
public class mPlayer {

    mPlayerType _playerType;
    int _id;
    String _name;
    int _number;
    int _completions;
    int _casualties;
    int _interceptions;
    int _touchdowns;
    int _mvp;
    boolean _miss;
    int _persistant;
    Vector _competences;
    Vector _injuries;
    int _status;
    public static int STATUS_ACTIVE = 1;
    public static int STATUS_RETIRED = 2;
    public static int STATUS_DEAD = 3;
    String _leftPassiveIcon;
    String _leftActiveIcon;
    String _rightPassiveIcon;
    String _rightActiveIcon;

    public mPlayer(Player player, mTeamRoster roster) {
        _number = player.getNumber();
        _id = player.getId();
        Vector playersTypes = roster.getPlayersType();
        for (int j = 0; j < playersTypes.size(); j++) {
            mPlayerType type = (mPlayerType) playersTypes.get(j);
            if (type.getId() == player.getTypeId()) {
                _playerType = type;
                break;
            }

        }
        _name = player.getName();

        if (player.isRetired()) {
            _status = STATUS_RETIRED;
        } else if (player.isDead()) {
            _status = STATUS_DEAD;
        } else {
            _status = STATUS_ACTIVE;
        }

        _completions = player.getCompletion();
        _casualties = player.getCasualties();
        _interceptions = player.getInterception();
        _touchdowns = player.getTouchdowns();
        _mvp = player.getMVP();

        _miss = player.isMissNextGame();

        _persistant = player.getPersistant();

        _competences = new Vector();
        for (int i = 0; i < player.getCompetences().getString().size(); i++) {
            _competences.add(dCompetencesFactory.createCompetence((String) player.getCompetences().getString().get(i)));
        }

        _injuries = new Vector();
        for (int i = 0; i < player.getInjuries().getString().size(); i++) {
            _injuries.add(((String) player.getInjuries().getString().get(i)));
        }

        String tmp = player.getIcon();
        if (tmp != null) {
            StringTokenizer tokenize = new StringTokenizer(tmp, ";");
            if (tokenize.countTokens() > 0) {
                while (tokenize.hasMoreTokens())
                {
                     switch(tokenize.countTokens())
                     {
                         case 1:
                             _rightPassiveIcon=tokenize.nextToken();
                             break;
                         case 2:
                             _leftPassiveIcon=tokenize.nextToken();
                             break;
                         case 3:
                             _rightActiveIcon=tokenize.nextToken();
                             break;
                         case 4:
                              _leftActiveIcon=tokenize.nextToken();
                             break;
                         default:
                             tokenize.nextToken();
                             break;
                     }
                }
            }
        }
    }

    public mPlayer(String name, mPlayerType type, int number) {
        _name = name;
        _playerType = type;
        _status = STATUS_ACTIVE;
        _completions = 0;
        _casualties = 0;
        _interceptions = 0;
        _touchdowns = 0;
        _mvp = 0;
        _miss = false;
        _persistant = 0;
        _number = number;

        _competences = new Vector();
        _injuries = new Vector();
    }

    public int getStatus() {
        return _status;
    }

    public void setNumber(int number) {
        _number = number;
    }

    public int getExperiencePoints() {
        return _completions * 1 + _casualties * 2 + _interceptions * 2 + _touchdowns * 3 + _mvp * 5;
    }

    public int getNumber() {
        return _number;
    }

    public mPlayer(mPlayerType type, int number) {
        _playerType = type;
        _number = number;
        _competences = new Vector();
        _injuries = new Vector();
    }

    public int getCost() {
        int value = _playerType.getCost();

        for (int i = 0; i < _competences.size(); i++) {
            dCompetence comp = (dCompetence) _competences.get(i);
            dCompetenceType ctype = comp.getType();

            if (ctype == dCompetencesFactory.Attributes) {
                if (comp.getName().equals("+1 Movement")) {
                    value += 30000;
                } else if (comp.getName().equals("+1 Armor")) {
                    value += 30000;
                } else if (comp.getName().equals("+1 Agility")) {
                    value += 40000;
                } else if (comp.getName().equals("+1 Strength")) {
                    value += 50000;
                }
            } else {
                for (int j = 0; j < _playerType.getSimpleRoll().size(); j++) {
                    dCompetenceType ct = (dCompetenceType) _playerType.getSimpleRoll().get(j);
                    if (ct == ctype) {
                        value += 20000;
                    }
                }

                for (int j = 0; j < _playerType.getDoubleRoll().size(); j++) {
                    dCompetenceType ct = (dCompetenceType) _playerType.getDoubleRoll().get(j);
                    if (ct == ctype) {
                        value += 30000;
                    }
                }
            }



        }

        return value;
    }

    public int getMovement() {
        int value = _playerType.getMovement();

        for (int i = 0; i < _competences.size(); i++) {
            if (((dCompetence) _competences.get(i)).getName().equals("+1 Movement")) {
                value++;
            }
        }
        for (int i = 0; i < _injuries.size(); i++) {
            if (((String) _injuries.get(i)).equals("-1 Movement")) {
                value--;
            }
        }
        return value;
    }

    public int getStrength() {
        int value = _playerType.getStrength();
        for (int i = 0; i < _competences.size(); i++) {
            if (((dCompetence) _competences.get(i)).getName().equals("+1 Strength")) {
                value++;
            }
        }
        for (int i = 0; i < _injuries.size(); i++) {
            if (((String) _injuries.get(i)).equals("-1 Strength")) {
                value--;
            }
        }
        return value;
    }

    public int getAgility() {
        int value = _playerType.getAgility();
        for (int i = 0; i < _competences.size(); i++) {
            if (((dCompetence) _competences.get(i)).getName().equals("+1 Agility")) {
                value++;
            }
        }
        for (int i = 0; i < _injuries.size(); i++) {
            if (((String) _injuries.get(i)).equals("-1 Agility")) {
                value--;
            }
        }
        return value;
    }

    public int getArmor() {
        int value = _playerType.getArmor();
        for (int i = 0; i < _competences.size(); i++) {
            if (((dCompetence) _competences.get(i)).getName().equals("+1 Armor")) {
                value++;
            }
        }
        for (int i = 0; i < _injuries.size(); i++) {
            if (((String) _injuries.get(i)).equals("-1 Armor")) {
                value--;
            }
        }
        return value;
    }

    public Vector getCompetences() {
        return _competences;
    }

    public Vector getPlayerTypeCompetences() {
        return _playerType.getCompetences();
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getPosition() {
        return _playerType.getPosition();
    }

    public int getCompletions() {
        return _completions;
    }

    public int getTouchdowns() {
        return _touchdowns;
    }

    public int getInterceptions() {
        return _interceptions;
    }

    public int getCasualties() {
        return _casualties;
    }

    public int getPersistant() {
        return _persistant;
    }

    public boolean MissNewMatch() {
        return _miss;
    }

    public int getMVP() {
        return _mvp;
    }

    public mPlayerType getPlayerType() {
        return _playerType;
    }

    public void setStatus(int status) {
        _status = status;
    }

    public int getId() {
        return _id;
    }

    public Vector getInjuries() {
        return _injuries;
    }
    
    public String getLeftActiveIcon()
    {
        return _leftActiveIcon;
    }
    
    public String getLeftPassiveIcon()
    {
        return _leftPassiveIcon;
    }
    
    public String getRightActiveIcon()
    {
        return _rightActiveIcon;
    }
    
    public String getRightPassiveIcon()
    {
        return _rightPassiveIcon;
    }
    
}
