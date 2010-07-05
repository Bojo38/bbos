<?php

class Competence_Type
{
	public $name='';
	public $id=-1;
	
	function __construct($comp_id) {
	
		$result=mysql_query("SELECT * FROM bbos_competence_type WHERE idCompetence_Type=$comp_id");
		$row = mysql_fetch_assoc($result);	
		$this->id=$row['idCompetence_Type'];
		$this->name=$row['Name'];
	}	
		
	public static function getSimple($player_type_id)	
	{
		$result=mysql_query("SELECT Competence_Type_idCompetence_Type FROM bbos_player_type_has_simplecompetence_type WHERE (Player_Type_idPlayer_Type=$player_type_id) ");	
		$competences=array();
		
		if (mysql_num_rows($result) > 0) {
            while ($row = mysql_fetch_assoc($result)) {
                $competence=new Competence_Type($row['Competence_Type_idCompetence_Type']);
				$competences[]=$competence;
            }
        }
		
		return $competences;
	}
	
	public static function getDouble($player_type_id)	
	{
		$result=mysql_query("SELECT Competence_Type_idCompetence_Type FROM bbos_player_type_has_doublecompetence_type WHERE (Player_Type_idPlayer_Type=$player_type_id) ");	
		$competences=array();
		
		if (mysql_num_rows($result) > 0) {
            while ($row = mysql_fetch_assoc($result)) {
                $competence=new Competence_Type($row['Competence_Type_idCompetence_Type']);
				$competences[]=$competence;
            }
        }
		
		return $competences;
	}

}

?>