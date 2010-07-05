<?php

class Coach
{
	public $id=0;
	public $name='';
	public $nickname='';
	public $password='';
  
	function __construct($id) {

		$result=mysql_query("SELECT * FROM bbos_coach WHERE idCoach=$id");
		$row = mysql_fetch_assoc($result);

		$this->id=$row['idCoach'];
		$this->name=$row['Name'];
		$this->nickname=$row['NickName'];
		$this->password=($row['PWD']==1);
    }
	
	public static function getCoachID($nickname)
	{
		$result=mysql_query("SELECT idCoach FROM bbos_coach WHERE (Nickname='$nickname')");

		if (mysql_num_rows($result) > 0) {
			while ($row = mysql_fetch_assoc($result)) {
				$id=$row['idCoach'];
			}
		}		
		return $id;
	}
	
	public static function isValid($user,$pwd)
	{
		$resultat=0;
		$request="select COUNT(*) from bbos_coach where NickName='$user' and PWD='$pwd'";
		$result=mysql_query($request);
        $number=mysql_num_rows($result);
		if ($number> 0) {
			$donnees = mysql_fetch_array($result);
			if ($donnees[0]==1)
			{
				$resultat=1;
			}
		}
		return $resultat;
	}
}
?>