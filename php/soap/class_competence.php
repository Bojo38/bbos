<?php

class Competence
{
	public $name='';
	public $id=-1;
	public $type_id;
	
	function __construct($comp_id) {
	
		$result=mysql_query("SELECT * FROM bbos_competence WHERE idCompetence=$comp_id");
		$row = mysql_fetch_assoc($result);	
		$this->id=$row['idCompetence'];
		$this->name=$row['Name'];
		$this->type_id=$row['Competence_Type_idCompetence_Type'];
	}	
		
	public static function getCompetencesForPlayer($player_id)	
	{
		$result=mysql_query("SELECT Competence_idCompetence FROM bbos_player_has_competence WHERE (Player_idPlayer=$player_id) ");	
		$competences=array();
		
		if (mysql_num_rows($result) > 0) {            
            while ($row = mysql_fetch_assoc($result)) {
                $competence=new Competence($row['Competence_idCompetence']);
				$competences[]=$competence;
            }
        }
		
		return $competences;
	}
	
	public static function getCompetencesForPlayerType($playertype_id)	
	{
		$result=mysql_query("SELECT Competence_idCompetence FROM bbos_player_type_has_competence WHERE (Player_Type_idPlayer_Type=$playertype_id)");	
		$competences=array();
		
		if (mysql_num_rows($result) > 0) {
            while ($row = mysql_fetch_assoc($result)) {
                $competence=new Competence($row['Competence_idCompetence']);
				$competences[]=$competence;
            }
        }
		
		return $competences;
	}
	
	public function print_object()
	{
		echo "Competence : <br>Id: $this->id<br>Name: $this->name<br>Type id: $this->type_id<br>";
	}

}

?>