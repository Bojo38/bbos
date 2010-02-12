<?php

function retirePlayer($id)
{
	include('config_inc.php');
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	$request='UPDATE player SET Retired = 1 WHERE player.idPlayer ='.$id;
	$result=mysql_query($request);
	
	mysql_close();
	return $resultat;
}

function addNewPlayer($team_id,$player)
{
	include('config_inc.php');
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	$request='INSERT INTO player (idPlayer , Player_Type_idPlayer_Type , Team_idTeam , Name , Ranking , MissNextGame , Completion , Touchdowns , Casualties';
	$request=$request.', Interceptions , MVP , Persistant , Number , Status , Retired , Dead ) VALUES (';
	$request=$request.'NULL , '.$player['TypeId'].','.$team_id.', \''.$player['Name'].'\', \'Rookie\' , 0 , 0 , 0 , 0 , 0 , 0 , 0, '.$player['Number'].', \'Active\', 0, 0)';

	$result=mysql_query($request);
	
	mysql_close();
	return $resultat;
}

?>