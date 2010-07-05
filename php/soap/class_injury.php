<?php

class Injury
{

	public $name='';
	public $id=-1;
	public $code=0;
	
	function __construct($inj_id) {
	
		$result=mysql_query("SELECT * FROM bbos_injuries WHERE idInjuries=$inj_id");
		$row = mysql_fetch_assoc($result);	
		$this->id=$row['idInjuries'];
		$this->name=$row['Name'];
		$this->code=$row['Code'];
	}	
		
	public static function getInjuries($player_id)	
	{
		$result=mysql_query("SELECT Injuries_idInjuries FROM bbos_player_has_injuries WHERE (Player_idPlayer=$player_id)");	
		$injuries=array();
		
		if (mysql_num_rows($result) > 0) {
            while ($row = mysql_fetch_assoc($result)) {
                $injury=new Injury($row['Injuries_idInjuries']);
				$injuries[]=$injury;
            }
        }		
		return $injuries;
	}
	
}

?>