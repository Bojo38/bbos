<?php
	include('config_inc.php');
	
	$user='lord bojo';

	$p=array();
	$players=array();
	
	$p[]=array(
					'Name' => 'A',
					'Ranking' => 'B',
					'TypeId' => 48,
					'Completion' => 0,
					'Interception' => 0,
					'Touchdowns' => 0,
					'Casualties' => 0,
					'MVP' => 0,
					'Persistant' => 0,
					'MissNextGame' => 0,
					'Competences' => array(),
					'Number' => 1,
					'Injuries' => 0,
					'Status' => 'Active'
			);
	$p[]=array(
					'Name' => 'C',
					'Ranking' => 'D',
					'TypeId' => 49,
					'Completion' => 0,
					'Interception' => 0,
					'Touchdowns' => 0,
					'Casualties' => 0,
					'MVP' => 0,
					'Persistant' => 0,
					'MissNextGame' => 0,
					'Competences' => array(),
					'Number' => 2,
					'Injuries' => 0,
					'Status' => 'Active'
			);
			
	$players=array('Player'=>$p);
	
	$team=array(
	'Name' => "Test Team",
	'Reroll' => 4,
	'Apothecary' => 1,
	'Treasury' => 0,
	'Cheerleaders' => 0,
	'Assists' => 0,
	'Popularity' => 1,
	'RaceId' => 1,
	'LeagueId' => 3,
	'Players' => $players);
			
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	$request='select * from coach where nickname=\''.$user.'\'';
	$result=mysql_query($request);
	$donnees = mysql_fetch_array($result);
	$user_id=$donnees[0];

	echo "User ID : ".$user_id."<br>";
	
	/*$team_request='INSERT INTO team (idTeam ,idLeague ,Team_Type_idTeam_Type ,Coach_idCoach ,Name ,Popularity ,Assists ,Cheerleaders ,Apothecary ,Treasury ,Reroll) VALUES (NULL ,';
	$team_request=$team_request.'3, '.$team['RaceId'].', '.$user_id.', \''.$team['Name'].'\', '.$team['Popularity'].', '.$team['Assists'].', '.$team['Cheerleaders'].', '.$team['Apothecary'].', '.$team['Treasury'].', '.$team['Reroll'].')';
	$result=mysql_query($team_request);
	if (!$result) 
	{
		echo "Requete : ".$team_request."<br>";
		die('Requête invalide : ' . mysql_error());
	}
	$team_id=mysql_insert_id();

	echo "Team ID : ".$team_id."<br>";	*/
	$team_id=1;
	
	$players=$team['Players'];
	$players=$players['Player'];
	$taille=count($players);
	echo "player count ".$taille."<br>";
	print_r(array_keys($players));
	for($i = 0; $i < $taille; $i++) 
	{
		// Create chapter
		$player=$players[$i];
		echo "Player ".$i." : ".$player."<br>";
		$player_request='INSERT INTO player (idPlayer, Player_Type_idPlayer_Type, Team_idTeam, Name, Ranking, MissNextGame, Completion, Touchdowns, Casualties, Interceptions, MVP, Persistant, Number, Status) VALUES ';
		$player_request=$player_request.'(NULL , '.$player['TypeId'].', '.$team_id.', \''.$player['Name'].'\', \''.$player['Ranking'].'\', '.$player['MissNextGame'].', '.$player['Completion'].', '.$player['Touchdowns'].', '.$player['Casualties'].', '.$player['Interception'].', '.$player['MVP'].', '.$player['Persistant'].', '.$player['Number'].', \''.$player['Status'].'\')';
		$result=mysql_query($player_request);
		echo 'Player '.mysql_insert_id().'inserted<br>';
		if (!$result) 
		{
			echo "Requete : ".$player_request."<br>";
			die('Requête invalide : ' . mysql_error());
		}
    }
	mysql_close();
	

?>


