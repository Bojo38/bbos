<?php



 function cancelMatch($matchId)
  {
  include('config_inc.php');
	$resultat = 0;

	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);

	$result=mysql_query('DELETE FROM `bbos_match` WHERE idMatch='.$matchId);

	 mysql_close();

  	return $resultat;
  }

 function acceptMatch($matchId)
  {
	include('config_inc.php');
	$resultat = 0;

	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);

	$result=mysql_query('UPDATE `bbos_match` SET `State` = 2 WHERE idMatch='.$matchId);

	 mysql_close();

  	return $resultat;
  }

   function saveMatchData($matchId,$Data)
  {
  include('config_inc.php');
	$resultat = 0;

	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);

	$result=mysql_query("UPDATE `bbos_match` SET `Data` = '".addslashes($Data)."' WHERE idMatch=".$matchId);

	 mysql_close();

  	return $resultat;
  }

  function newChallenge($challengerId,$opponentId,$leagueId)
{
	include('config_inc.php');
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);

	$request='INSERT INTO `bbos_match` (idMatch , `date`, League_idLeague, Team_idTeam_1, Team_idTeam_2, State, Score_1, Score_2, Winner_idTeam, Round, ExtraTime, Public, FAME_1, FAME_2, Winnings_1, Winnings_2, challengerId) VALUES (NULL ,';
	/*DATE */
	$date=getdate();
	$request=$request.'\''.$date['year'].'-'.$date['mon'].'-'.$date['mday'].' '.$date['hours'].':'.$date['minutes'].':'.$date['seconds'].'\'';
	$request=$request.', '.$leagueId.', '.$challengerId.', '.$opponentId.', 1 , NULL , NULL , NULL , NULL , NULL , NULL , NULL , NULL , NULL , NULL ,'.$challengerId.')';
	$result=mysql_query($request);
	$resultat=mysql_insert_id();
	if (!$result)
	{
		echo "Requete : ".$team_request."<br>";
		die('Requ�te invalide : ' . mysql_error());
	}

	mysql_close();
	return $resultat;
}

?>
