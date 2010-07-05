<?php

class Player
{
	public $id=-1;
	public $team_id=-1;
	public $name="";
    public $ranking="";
	
	public $miss_next_game=0;
	public $completions=0;
	public $touchdowns=0;
	public $casualties=0;
	public $interceptions=0;
	public $mvp=0;
	public $persistant=0;
  
	public $number=0;
	public $status="Active";
	public $retired=0;
	public $dead=0;
	public $icon_url="";
	
	public $player_type_id=-1;
	
	public $competences=array();
	public $injuries=array();

	function __construct($player_id) {
		$result=mysql_query("SELECT * FROM bbos_player WHERE idPlayer=$player_id");
		$row = mysql_fetch_assoc($result);

		$this->id=$row['idPlayer'];
		$this->player_type_id=$row['Player_Type_idPlayer_Type'];
		$this->team_id=$row['Team_idTeam'];
		$this->name=$row['Name'];
		$this->ranking=$row['Ranking'];
		$this->miss_next_game=$row['MissNextGame'];
		$this->completions=$row['Completion'];
		$this->touchdowns=$row['Touchdowns'];
		$this->casualties=$row['Casualties'];
		$this->interceptions=$row['Interceptions'];
		$this->mvp=$row['MVP'];
		$this->persistant=$row['Persistant'];
		$this->number=$row['Number'];
		$this->status=$row['Status'];
		$this->retired=$row['Retired'];
		$this->dead=$row['Dead'];
		$this->icon_url=$row['icon_address'];
		
		$this->competences=Competence::getCompetencesForPlayer($this->id);
		$this->injuries=Injury::getInjuries($this->id);
    }
	
	public function print_object()
	{
		echo "Player:<br> Id: $this->id<br> Name: $this->name<br>team_id: $this->team_id<br> Ranking: $this->ranking<br>";
		echo "Miss next game: $this->miss_next_game<br>Completions: $this->completions<br> Touchdowns: $this->touchdowns<br>";
		echo "Casualties: $this->casualties<br>Interceptions: $this->interceptions<br> MVP: $this->mvp<br>";
		echo "Persistant: $this->persistant<br>Number: $this->number<br> Status: $this->status<br>Retired: $this->retired<br>Dead: $this->dead<br>";
		echo "Icon: $this->icon_url<br>Player_type: $this->player_type<br>";
	
		foreach ($this->competences as $comp)
		{
			$comp->print_object();
		}	
	}
	
	public static function getPlayers($team_id)
	{
		$result=mysql_query("SELECT idPlayer FROM bbos_player WHERE (bbos_player.Team_idTeam=$team_id)");	
		$players=array();		
		if (mysql_num_rows($result) > 0) {
			while ($row = mysql_fetch_assoc($result)) {
				$player=new Player($row['idPlayer']);
				$players[]=$player;
			}
		}		
		return $players;
	}
	
	public function retire()
	{
		$request="UPDATE bbos_player SET Retired = 1 WHERE player.idPlayer =$this->id";
		$result=mysql_query($request);
		$retire=true;
	}
	
	public static function create($team_id,$type,$name,$number)
	{
		$request='INSERT INTO bbos_player (idPlayer , Player_Type_idPlayer_Type , Team_idTeam , Name , Ranking , MissNextGame , Completion , Touchdowns , Casualties';
		$request=$request.', Interceptions , MVP , Persistant , Number , Status , Retired , Dead ) VALUES (';
		$request=$request.'NULL , '.$type.','.$team_id.', \''.$name.'\', \'Rookie\' , 0 , 0 , 0 , 0 , 0 , 0 , 0, '.$number.', \'Active\', 0, 0)';

		$result=mysql_query($request);
        $player_id=mysql_insert_id();
        return $player_id;
	}
    
}
?>