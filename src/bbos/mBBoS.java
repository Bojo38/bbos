/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos;

import bbos.Match.Model.Competences.dCompetence;
import bbos.General.Model.mPlayer;
import bbos.General.Model.mTeam;
import bbos.General.Model.mTeamRoster;
import bbos.General.Views.jdgProgressBar;
import bbos.Match.Model.dMatch;
import bbos.Tools.dSound;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.MalformedURLException;
import javax.swing.*;
import java.util.Vector;
import javax.xml.namespace.QName;
import webbos.webbos.Player;
import webbos.webbos.PlayerArray;
import webbos.webbos.StringArray;
import webbos.webbos.Team;
import webbos.webbos.TeamArray;

/**
 * Main Data Model
 * @author frederic
 */
public class mBBoS {

    protected String _login;
    protected String _password;
    /**
     * URL of the webservice used by BBoS
     */
    protected URL _webservice;
    protected static mBBoS _singleton;
    protected Vector _teamTypes;
    protected Vector _myTeams;

    protected mBBoS(String login, String password, String webservice) {
        try {
            _webservice = new URL(webservice);
        //_webservice=new URL("http://bbos.ainpacte.org/service.php?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        _login = login;
        _password = password;

        String imgName = "/resources/images/look/ain_pacte.png";

        //affichage du splash screen 
        jdgProgressBar.createSingleton(imgName, 4);
        
        _teamTypes = new Vector();
        _myTeams = new Vector();
        webbos.webbos.TeamTypeArray teams;
        webbos.webbos.TeamArray myteams;
        webbos.webbos.Webbos soap = new webbos.webbos.Webbos(_webservice, new QName("http://Webbos/Webbos", "Webbos"));
        try {
            int test = soap.getWebbosPort().soapTest();
            jdgProgressBar.setProgressValue(0, "Loading team rosters ...");
            teams = soap.getWebbosPort().getAllTeamTypes();
            for (int i = 0; i < teams.getTeamType().size(); i++) {
                _teamTypes.add(new bbos.General.Model.mTeamRoster((webbos.webbos.TeamType) teams.getTeamType().get(i)));
            }

            jdgProgressBar.setProgressValue(1, "Loading league data ...");
            // League Data

            jdgProgressBar.setProgressValue(2, "Loading user data ...");
            myteams = soap.getWebbosPort().getMyTeams(_login);

            for (int i = 0; i < myteams.getTeam().size(); i++) {
                if (((webbos.webbos.Team) myteams.getTeam().get(i)).getId() != 0) {
                    _myTeams.add(new bbos.General.Model.mTeam((webbos.webbos.Team) myteams.getTeam().get(i), _teamTypes));
                }
            }

            jdgProgressBar.setProgressValue(3, "Initalizing HMI ...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void newModel(String login, String password, String webservice) {
        _singleton = new mBBoS(login, password, webservice);
    }

    public static mBBoS getSingleton() {
        return _singleton;
    }

    public void addTeam(mTeam team) {
        Team wteam = new Team();
        String imgName = "/resources/images/look/ain_pacte.png";
        jdgProgressBar.createSingleton(imgName, 3);

        PlayerArray wplayers = new PlayerArray();
        for (int i = 0; i < 16; i++) {
            mPlayer player = team.getPlayer(i + 1);
            if (player != null) {
                Player wplayer = new Player();
                wplayer.setName(player.getName());
                wplayer.setRanking("Rookie");
                wplayer.setNumber(player.getNumber());
                wplayer.setRetired(false);
                wplayer.setDead(false);
                wplayer.setTypeId(player.getPlayerType().getId());
                wplayers.getPlayer().add(wplayer);
            }
        }
        wteam.setApothecary(team.hasApothecary());
        wteam.setAssists(team.getAssists());
        wteam.setCheerleaders(team.getPomspoms());
        wteam.setName(team.getName());
        wteam.setPopularity(team.getPopFactor());
        wteam.setRaceId(team.getTeamType().getId());
        wteam.setReroll(team.getReroll());
        wteam.setTreasury(team.getTreasury());
        wteam.setPlayers(wplayers);

        webbos.webbos.Webbos soap = new webbos.webbos.Webbos(_webservice, new QName("http://Webbos/Webbos", "Webbos"));

        jdgProgressBar.setProgressValue(0, "Adding roster ...");

        soap.getWebbosPort().addTeam(_login, wteam);

        jdgProgressBar.setProgressValue(1, "Reloading teams ...");

        webbos.webbos.TeamArray myteams;
        _myTeams = new Vector();
        myteams = soap.getWebbosPort().getMyTeams(_login);
        for (int i = 0; i < myteams.getTeam().size(); i++) {
            _myTeams.add(new bbos.General.Model.mTeam((webbos.webbos.Team) myteams.getTeam().get(i), _teamTypes));
        }

        jdgProgressBar.setProgressValue(2, "Refreshing HMI ...");
        MainForm.getSingleton().redraw();
        jdgProgressBar.setProgressValue(3, "Done.");
    }

    public void setWebServiceURL(String new_url) {
        try {
            _webservice = new URL(new_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String getWebServiceURL() {
        return _webservice.toString();
    }

    public Vector getTeamTypes() {
        return _teamTypes;
    }

    public Vector getMyTeams() {
        return _myTeams;
    }

    public String getCoachName() {
        return _login;
    }

    public void retirePlayer(mPlayer player) {
        webbos.webbos.Webbos soap = new webbos.webbos.Webbos(_webservice, new QName("http://Webbos/Webbos", "Webbos"));
        soap.getWebbosPort().retirePlayer(player.getId());
    }

    public void removeTeam(mTeam team) {
        webbos.webbos.Webbos soap = new webbos.webbos.Webbos(_webservice, new QName("http://Webbos/Webbos", "Webbos"));
        soap.getWebbosPort().removeTeam(team.getId());
        _myTeams.remove(team);
    }

    public void updateTeam(mTeam team) {
        Team wteam = new Team();

        PlayerArray wplayers = new PlayerArray();
        for (int i = 0; i < 16; i++) {
            mPlayer player = team.getPlayer(i + 1);
            if (player != null) {
                Player wplayer = new Player();
                wplayer.setName(player.getName());
                wplayer.setNumber(player.getNumber());
                wplayer.setRetired(false);
                wplayer.setDead(false);
                wplayer.setTypeId(player.getPlayerType().getId());
                wplayer.setCasualties(player.getCasualties());
                wplayer.setInterception(player.getInterceptions());
                wplayer.setMVP(player.getMVP());
                wplayer.setPersistant(player.getPersistant());
                wplayer.setTouchdowns(player.getTouchdowns());
                wplayer.setCompletion(player.getCompletions());
                wplayer.setMissNextGame(player.MissNewMatch());
                StringArray competences = new StringArray();
                for (int j = 0; j < player.getCompetences().size(); j++) {
                    competences.getString().add(((dCompetence) player.getCompetences().get(j)).getName());
                }
                wplayer.setCompetences(competences);

                StringArray injuries = new StringArray();
                for (int j = 0; j < player.getInjuries().size(); j++) {
                    injuries.getString().add((String) player.getInjuries().get(j));
                }
                wplayer.setInjuries(injuries);
                wplayers.getPlayer().add(wplayer);
            }
        }

        for (int i = 0; i < team.getExPlayers().size(); i++) {
            mPlayer player = (mPlayer) team.getExPlayers().get(i);
            if (player != null) {
                Player wplayer = new Player();
                wplayer.setName(player.getName());
                wplayer.setNumber(player.getNumber());
                wplayer.setRetired(player.getStatus() == mPlayer.STATUS_RETIRED);
                wplayer.setDead(player.getStatus() == mPlayer.STATUS_DEAD);
                wplayer.setTypeId(player.getPlayerType().getId());
                wplayer.setCasualties(player.getCasualties());
                wplayer.setInterception(player.getInterceptions());
                wplayer.setMVP(player.getMVP());
                wplayer.setPersistant(player.getPersistant());
                wplayer.setTouchdowns(player.getTouchdowns());
                wplayer.setCompletion(player.getCompletions());

                StringArray competences = new StringArray();
                for (int j = 0; j < player.getCompetences().size(); j++) {
                    competences.getString().add(((dCompetence) player.getCompetences().get(j)).getName());
                }
                wplayer.setCompetences(competences);

                StringArray injuries = new StringArray();
                for (int j = 0; j < player.getInjuries().size(); j++) {
                    injuries.getString().add((String) player.getInjuries().get(j));
                }
                wplayer.setInjuries(injuries);
                wplayers.getPlayer().add(wplayer);
            }
        }

        wteam.setApothecary(team.hasApothecary());
        wteam.setAssists(team.getAssists());
        wteam.setCheerleaders(team.getPomspoms());
        wteam.setName(team.getName());
        wteam.setPopularity(team.getPopFactor());
        wteam.setRaceId(team.getTeamType().getId());
        wteam.setReroll(team.getReroll());
        wteam.setTreasury(team.getTreasury());
        wteam.setPlayers(wplayers);
        wteam.setId(team.getId());

        webbos.webbos.Webbos soap = new webbos.webbos.Webbos(_webservice, new QName("http://Webbos/Webbos", "Webbos"));
        soap.getWebbosPort().updateTeam(wteam);
    }

    public void addNewPlayer(mTeam team, mPlayer player) {

        Player wplayer = new Player();
        wplayer.setName(player.getName());
        wplayer.setNumber(player.getNumber());
        wplayer.setTypeId(player.getPlayerType().getId());

        webbos.webbos.Webbos soap = new webbos.webbos.Webbos(_webservice, new QName("http://Webbos/Webbos", "Webbos"));
        soap.getWebbosPort().addNewPlayer(team.getId(), wplayer);
    }

    public mTeam getTeam(int team_id) {

        webbos.webbos.Webbos soap = new webbos.webbos.Webbos(_webservice, new QName("http://Webbos/Webbos", "Webbos"));
        webbos.webbos.Team team = soap.getWebbosPort().getTeam(team_id);
        return new mTeam(team, _teamTypes);
    }

    public Vector getTeamsForChallenge(int leagueId, int team_id) {
        Vector result = new Vector();
        webbos.webbos.Webbos soap = new webbos.webbos.Webbos(_webservice, new QName("http://Webbos/Webbos", "Webbos"));
        TeamArray array = soap.getWebbosPort().getTeamsForChallenge(leagueId);

        for (int i = 0; i < array.getTeam().size(); i++) {
            Team team = (Team) array.getTeam().get(i);
            if (team.getId() != team_id) {
                mTeam tmp = new mTeam(team, _teamTypes);
                result.add(tmp);
            }
        }
        return result;
    }

    public int newChallenge(int challenger_id, int opponent_id, int league_id) {
        webbos.webbos.Webbos soap = new webbos.webbos.Webbos(_webservice, new QName("http://Webbos/Webbos", "Webbos"));
        return soap.getWebbosPort().newChallenge(challenger_id, opponent_id, league_id);
    }

    public void cancelMatch(int matchId) {
        webbos.webbos.Webbos soap = new webbos.webbos.Webbos(_webservice, new QName("http://Webbos/Webbos", "Webbos"));
        soap.getWebbosPort().cancelMatch(matchId);
    }

    public void acceptMatch(int matchId) {
        webbos.webbos.Webbos soap = new webbos.webbos.Webbos(_webservice, new QName("http://Webbos/Webbos", "Webbos"));
        soap.getWebbosPort().acceptMatch(matchId);
    }

    public void saveMatchData(int matchId, dMatch matchData) {
        
        byte[] array=null;
        try {
            ByteArrayOutputStream writer = new ByteArrayOutputStream();
            ObjectOutputStream ow = new ObjectOutputStream(writer);
            ow.writeObject(matchData);
            array=writer.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        webbos.webbos.Webbos soap = new webbos.webbos.Webbos(_webservice, new QName("http://Webbos/Webbos", "Webbos"));
        soap.getWebbosPort().saveMatchData(matchId,array);
    }
    
    public mTeamRoster getTeamType(int id)
    {
        for (int i=0; i<_teamTypes.size(); i++)
        {
            mTeamRoster roster=(mTeamRoster)_teamTypes.get(i);
            if (roster.getId()==id)
            {
                return roster;
            }
        }
        return null;
    }
}
