<?php

class Player_Type
{
	public $id=-1;

	public $name="";
    public $movement="";
	public $strength=false;
	public $agility=0;
	public $armor=0;
	public $cost=0;
	public $position=0;
	public $is_star=0;
	public $limit=0;
	
	public $players_type=array();
	public $simple_competence_type=array();
	public $double_competence_type=array();

	function __construct($player_type_id) {

		$result=mysql_query("SELECT * FROM bbos_player_type WHERE idPlayer_Type=$player_type_id");
		$row = mysql_fetch_assoc($result);

		$this->id=$row['idPlayer_Type'];
		$this->name=$row['Name'];
		$this->movement=$row['Ma'];
		$this->strength=$row['St'];
		$this->agility=$row['Ag'];
		$this->armor=$row['Ar'];
		$this->cost=$row['Cost'];
		$this->position=$row['Position'];
		$this->is_star=$row['isStar'];
		
		$this->competences=Competence::getCompetencesForPlayerType($this->id);
		$this->simple_competence_type=Competence_Type::getSimple($this->id);
		$this->double_competence_type=Competence_Type::getDouble($this->id);
    }
	

	public static function getPlayersType($teamtype_id)
	{
		$result=mysql_query("SELECT Player_Type_idPlayer_Type,MaxNumber FROM bbos_team_type_has_player_type WHERE (Team_Type_idTeam_Type=$teamtype_id)");	
		$players=array();
		
		if (mysql_num_rows($result) > 0) {
			while ($row = mysql_fetch_assoc($result)) {
				$player=new Player_Type($row['Player_Type_idPlayer_Type']);
				$player->limit=$row['MaxNumer'];
				$players[]=$player;
			}
		}		
		return $players;
	}
	
}
?>