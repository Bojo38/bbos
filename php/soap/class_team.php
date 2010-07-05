<?php

class Team
{
	public $id=0;
	public $league_id=0;
	public $team_type_id=0;
	public $coach_id=0;
	public $name='';
	
	public $fan_factor=0;
	public $assists=0;
	public $cheerleaders=0;
	public $apothecary=0;
	public $treasury=0;
	public $reroll=0;
	public $active=1;
  
	public $players=array();
	public $matches=array();
  
	function __construct($team_id) {

		$result=mysql_query("SELECT * FROM bbos_team WHERE idTeam=$team_id");
		$row = mysql_fetch_assoc($result);

		$this->id=$row['idTeam'];
		$this->league_id=$row['idLeague'];
		$this->team_type_id=$row['Team_Type_idTeam_Type'];
		$this->name=$row['Name'];
		$this->coach=$row['Coach_idCoach'];
		$this->fan_factor=($row['Popularity']==1);
		$this->assists=$row['Assists'];
		$this->cheerleaders=$row['Cheerleaders'];
		$this->apothecary=($row['Apothecary']==1);
		$this->treasury=$row['Treasury'];
		$this->reroll=$row['Reroll'];
		$this->active=$row['Active'];
		
		$this->players=Player::getPlayers($this->id);
        $this->matches=Match::getMatchesFromTeam($this->id);
    }
	
	public static function getCoachTeams($user_id)
	{
		$result=mysql_query("SELECT idTeam FROM bbos_team WHERE (Coach_idCoach=$user_id)");	
		$teams=array();
		
		if (mysql_num_rows($result) > 0) {
			while ($row = mysql_fetch_assoc($result)) {
				$team=new Team($row['idTeam']);
				$teams[]=$team;
			}
		}		
		return $teams;
	}

    public static function getTeamsFromLeague($league_id)
	{
		$result=mysql_query("SELECT idTeam FROM bbos_team WHERE (idLeague=$league_id)");
		$teams=array();

		if (mysql_num_rows($result) > 0) {
			while ($row = mysql_fetch_assoc($result)) {
				$team=new Team($row['idTeam']);
				$teams[]=$team;
			}
		}
		return $teams;
	}
	
	public function print_object()
	{
		echo "Team:<br> Id: $this->id<br> Name: $this->name<br>";
	
		foreach ($this->players as $p)
		{
			$p->print_object();
		}
	}

    public function setActive($active)
    {
        $this->active=$active;
        $request_team="UPDATE bbos_team SET Active=$active WHERE bbos_team.idTeam =$this->id";
        mysql_query($request_team);
    }

    public static function create($league_id,$race_id,$user_id, $name,$fan_factor,$assists,$cheers, $apothecary,$treasury, $reroll)
    {
        $team_request="INSERT INTO bbos_team (idTeam ,idLeague ,Team_Type_idTeam_Type ,Coach_idCoach ,Name ,Popularity ,Assists ,Cheerleaders ,Apothecary ,Treasury ,Reroll) VALUES (NULL ,";
        $team_request="$team_request $league_id, $race_id, $user_id, '$name', $fan_factor, $assists, $cheers, $apothecary, $treasury, $reroll)";
        $result=mysql_query($team_request);
        if (!$result)
        {
            die('Requ�te invalide : ' . mysql_error());
        }
        $team_id=mysql_insert_id();
        return $team_id;
    }

}
?>