/*
 * dTeam.java
 *
 * Created on 22 novembre 2007, 20:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.General.Model;

import bbos.*;
import java.util.*;
import java.awt.*;

/**
 * This class contains all the data and methods relative to a team during a match.
 * @author Frederic
 */
public class mTeam {

    /**
     * Race of the Team
     */
    protected mTeamRoster _team_type;
    /**
     * Name of the team
     */
    protected String _name;
    /**
     * Popularity factor
     */
    protected int _popfactor;
    /**
     * Players of teh team
     */
    protected HashMap _players;
    protected Vector _exPlayers;
    /**
     * Total number of reroll
     */
    protected int _reroll;
    /**
     * Pompom number
     */
    protected int _pomPoms;
    /**
     * Number of assists
     */
    protected int _assists;
    protected boolean _apothecary;
    protected int _treasury;
    protected int _id;
    protected int _leagueId;
    protected Vector _matches;

    protected String _coach;
            
    /**
     * Creates a new instance of dTeam
     * @param race Race of the new team
     */
    public mTeam() {
        _name = "";
        _players = new HashMap();
        _exPlayers = new Vector();
        _matches=new Vector();
    }

    /**
     * Returns a reference to the players list
     * @return Players lis
     */
    public HashMap getPlayers() {
        return _players;
    }

    /**
     * Return the popuylarity factor
     * @return Popularity factor
     */
    public int getPopFactor() {
        return _popfactor;
    }

    /**
     * Return the Cheerleaders number
     * @return cheerleaders
     */
    public int getPomspoms() {
        return _pomPoms;
    }

    /**
     * Return the number of assists
     * @return assists
     */
    public int getAssists() {
        return _assists;
    }

    public mTeamRoster getTeamType() {
        return _team_type;
    }

    public int getReroll() {
        return _reroll;
    }

    public boolean hasApothecary() {
        return _apothecary;
    }

    public int getTreasury() {
        return _treasury;
    }

    public String getName() {
        return _name;
    }

    public int getRanking() {
        int ranking = 0;

        for (int i = 1; i <= 16; i++) {
            mPlayer player = (mPlayer) _players.get(i);
            if (player != null) {
                if (!player.MissNewMatch()) {
                    ranking = ranking + player.getCost() / 10000;
                }
            }
        }

        ranking += _assists;
        ranking += _pomPoms;
        ranking += _popfactor;
        ranking += _reroll * _team_type.getRerollCost() / 10000;
        if (_apothecary) {
            ranking += _team_type.getApothecaryCost() / 10000;
        }

        return ranking;
    }

    public void setTeamRoster(int index) {
        _team_type = (mTeamRoster) mBBoS.getSingleton().getTeamTypes().get(index);
        _players = new HashMap();
        if (!_team_type.isApothecary()) {
            _apothecary = false;
        }
    }

    public void setTeamName(String name) {
        _name = name;
    }

    public void setReroll(int reroll) {
        _reroll = reroll;
    }

    public void setAssist(int assist) {
        _assists = assist;
    }

    public void setPomPom(int value) {
        _pomPoms = value;
    }

    public void setPopFactor(int value) {
        _popfactor = value;
    }

    public void setApothecary(boolean apo) {
        _apothecary = apo;
    }

    public mPlayer getPlayer(int i) {
        return (mPlayer) _players.get(i);
    }

    public Vector getAvailablePositions() {
        Vector result = new Vector();
        Map positions = new HashMap();

        for (int i = 0; i < _team_type.getRegularPlayersNumber(); i++) {
            positions.put(_team_type.getPlayerType(i).getId(), _team_type.getPlayerType(i).getLimit());
        }

        Object[] values = _players.values().toArray();
        for (int i = 0; i < values.length; i++) {
            mPlayer player = (mPlayer) values[i];
            if (player != null) {
                int nb = (Integer) positions.get(player.getPlayerType().getId());
                positions.put(player.getPlayerType().getId(), nb - 1);
            }
        }
        for (int i = 0; i < _team_type.getRegularPlayersNumber(); i++) {
            int nb = (Integer) positions.get(_team_type.getPlayerType(i).getId());
            if (nb > 0) {
                result.add(_team_type.getPlayerType(i).getPosition());
            }
        }
        return result;
    }

    public Vector getAvailablePositions(int maxPrize) {
        Vector result = new Vector();
        Map positions = new HashMap();

        for (int i = 0; i < _team_type.getRegularPlayersNumber(); i++) {
            if (_team_type.getPlayerType(i).getCost() <= maxPrize) {
                positions.put(_team_type.getPlayerType(i).getId(), _team_type.getPlayerType(i).getLimit());
            }
        }

        Object[] values = _players.values().toArray();
        for (int i = 0; i < values.length; i++) {
            mPlayer player = (mPlayer) values[i];
            if (player != null) {
                if (positions.get(player.getPlayerType().getId()) != null) {
                    int nb = (Integer) positions.get(player.getPlayerType().getId());
                    positions.put(player.getPlayerType().getId(), nb - 1);
                }
            }
        }
        for (int i = 0; i < _team_type.getRegularPlayersNumber(); i++) {
            int nb = (Integer) positions.get(_team_type.getPlayerType(i).getId());
            if (nb > 0) {
                result.add(_team_type.getPlayerType(i).getPosition());
            }
        }
        return result;
    }

    public Vector getAvailableNumbers() {
        Vector result = new Vector();

        for (int i = 0; i < 16; i++) {
            result.add(i + 1);
        }
        Object[] values = _players.keySet().toArray();
        for (int i = 0; i < values.length; i++) {
            result.remove(values[i]);
        }
        return result;
    }

    public Vector getAvailablePlayersTypes() {
        Vector result = new Vector();
        Map positions = new HashMap();

        for (int i = 0; i < _team_type.getRegularPlayersNumber(); i++) {
            positions.put(_team_type.getPlayerType(i).getId(), _team_type.getPlayerType(i).getLimit());
        }

        Object[] values = _players.values().toArray();
        for (int i = 0; i < values.length; i++) {
            mPlayer player = (mPlayer) values[i];
            if (player != null) {
                int nb = (Integer) positions.get(player.getPlayerType().getId());
                positions.put(player.getPlayerType().getId(), nb - 1);
            }
        }
        for (int i = 0; i < _team_type.getRegularPlayersNumber(); i++) {
            int nb = (Integer) positions.get(_team_type.getPlayerType(i).getId());
            if (nb > 0) {
                result.add(_team_type.getPlayerType(i));
            }
        }
        return result;
    }

    public void setPlayerType(int row, String position) {
        mPlayer player = (mPlayer) _players.get(row);
        String name = "";
        if (player != null) {
            _name = player.getName();
        }

        player = new mPlayer(_team_type.getPlayerType(position), row);
        if (_team_type.getPlayerType(position) != null) {
            player.setName(name);
            _players.put(row, player);
        } else {
            _players.remove(row);
        }
    }

    public mTeam(webbos.webbos.Team team, Vector team_types) {
        _name = team.getName();
        _popfactor = team.getPopularity();
        _reroll = team.getReroll();

        int raceid = team.getRaceId();
        for (int i = 0; i < team_types.size(); i++) {
            mTeamRoster roster = (mTeamRoster) team_types.get(i);
            if (roster.getId() == raceid) {
                _team_type = roster;
            }
        }

        _pomPoms = team.getCheerleaders();
        _assists = team.getAssists();
        _apothecary = team.isApothecary();
        _treasury = team.getTreasury();

        _players = new HashMap();
        _exPlayers = new Vector();
        if (team.getPlayers() != null) {
            for (int i = 0; i < team.getPlayers().getPlayer().size(); i++) {
                mPlayer player = new mPlayer((webbos.webbos.Player) team.getPlayers().getPlayer().get(i), _team_type);
                if (player.getStatus() == mPlayer.STATUS_ACTIVE) {
                    _players.put(player.getNumber(), player);
                } else {
                    _exPlayers.add(player);
                }
            }
        }
        _id = team.getId();
        _matches=new Vector();
        if (team.getMatches()!=null)
        {
            for (int i=0; i<team.getMatches().getMatch().size(); i++)
            {
                mMatch match=new mMatch((webbos.webbos.Match)team.getMatches().getMatch().get(i));
                _matches.add(match);
            }
        }
        _leagueId=team.getLeagueId();
        _coach=team.getCoach();
    }

    public Vector getExPlayers() {
        return _exPlayers;
    }

    public void retirePlayer(mPlayer player) {
        _players.remove(player.getNumber());
        player.setStatus(mPlayer.STATUS_RETIRED);
        _exPlayers.add(player);
    }

    public int getId() {
        return _id;
    }

    public void setTreasury(int treasury) {
        _treasury = treasury;
    }
    
    public Vector getMatches()
    {
        return _matches;
    }

    public int getLeagueId()
    {
        return _leagueId;
    }
    
    public String getCoach()
    {
        return _coach;
    }
    
    public void setMatches(Vector matches)
    {
        _matches=matches;
    }
}
