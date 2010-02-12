<?php

function updateTeam($team)
{
	include('config_inc.php');
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	$team_id=$team['Id'];
	
	$request_team='UPDATE team SET Popularity = '.$team['Popularity'].', Assists='.$team['Assists'].', Cheerleaders=';
	$request_team=$request_team.$team['Cheerleaders'].', Apothecary='.$team['Apothecary'].', Treasury='.$team['Treasury'];
	$request_team=$request_team.', Reroll='.$team['Reroll'].' WHERE team.idTeam ='.$team_id;
	
	$result=mysql_query($request_team);
	if (!$result) 
	{
		echo "Requete : ".$team_request."<br>";
		die('Requête invalide : ' . mysql_error());
	}

	$players=$team['Players'];
	$players=$players['Player'];
	$taille_players=count($players);	
	for($i = 0; $i < $taille_players; $i++) 
	{
		// update player table
		$player=$players[$i];
		$player_id=$player['Id'];
		
		$request_player='UPDATE player SET MissNextGame = '.$player['MissNextGame'].', Completion = '.$player['Completion'].', Touchdowns = '.$player['Touchdowns'];
		$request_player=$request_player.', Casualties = '.$player['Casualties'].', Interceptions = '.$player['Interception'].', MVP = '.$player['MVP'];
		$request_player=$request_player.', Persistant = '.$player['Persistant'].' WHERE player.idPlayer = '.$player_id;
		
		$result=mysql_query($request_player);
		if (!$result) 
		{
			die('Requête invalide : ' . mysql_error());
		}
		
		
		// update injury
		$injuries=$player['Injuries'];
		$injuries=$injuries['String'];
		$taille_injuries=count($injuries);
		for ($j=0; $j<$taille_injuries; $j++)
		{
			$injury=$injuries[$j];
			$injuries_select='SELECT * FROM injuries WHERE Name='.$injury;
			$injury_result=mysql_request($injuries_select);
			if (!$injury_result) 
			{
				die('Requête invalide : ' . mysql_error());
			}
			if (mysql_num_rows($injury_result)>0)
			{
				$data_injuries = mysql_fetch_array($injury_result);
				$player_has_injuries_select='SELECT * FROM player_has_injuries WHERE (Injuries_idInjuries='.$data_injuries[0].') AND (Player_idPlayer='.$player['Id'].')';
				$player_has_injuries_result=mysql_request($player_has_injuries_select);
				if (!$player_has_injuries_result) 
				{
					die('Requête invalide : ' . mysql_error());
				}
				if (mysql_num_rows($player_has_injuries_result)==0)
				{
					$player_has_injuries_insert='INSERT INTO player_has_injuries (Injuries_idInjuries,Player_idPlayer) VALUES('.$data_injuries[0].','.$player['Id'].')';
					$player_has_injuries_result=mysql_request($player_has_injuries_insert);
				}
			}
		}
		// update competences
		$competences=$player['Competences'];
		$competences=$competences['String'];
		$taille_competences=count($competences);
		for ($j=0; $j<$taille_competences; $j++)
		{
			$competence=$competences[$j];
			$competence_select='SELECT * FROM competences WHERE Name='.$competence;
			$competence_result=mysql_request($competence_select);
			if (!$competence_result) 
			{
				die('Requête invalide : ' . mysql_error());
			}
			if (mysql_num_rows($competence_result)>0)
			{
				$data_competence = mysql_fetch_array($competence_result);
				$player_has_competence_select='SELECT * FROM player_has_competence WHERE (Competence_idCompetence='.$data_competence[0].') AND (Player_idPlayer='.$player['Id'].')';
				$player_has_competence_result=mysql_request($player_has_competence_select);
				if (!$player_has_competence_result) 
				{
					die('Requête invalide : ' . mysql_error());
				}
				if (mysql_num_rows($player_has_competence_result)==0)
				{
					$player_has_competence_insert='INSERT INTO player_has_competence (Competence_idCompetence,Player_idPlayer) VALUES('.$data_competence[0].','.$player['Id'].')';
					$player_has_competence_result=mysql_request($player_has_competence_insert);
				}
			}
		}
    }
	mysql_close();
	return $resultat;
}

function addTeam($user,$team)
{
	include('config_inc.php');
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	$request='select * from coach where Nickname=\''.$user.'\'';
	$result=mysql_query($request);
	$donnees = mysql_fetch_array($result);
	$user_id=$donnees[0];

	$team_request='INSERT INTO team (idTeam ,idLeague ,Team_Type_idTeam_Type ,Coach_idCoach ,Name ,Popularity ,Assists ,Cheerleaders ,Apothecary ,Treasury ,Reroll) VALUES (NULL ,';
	$team_request=$team_request.'3, '.$team['RaceId'].', '.$user_id.', \''.$team['Name'].'\', '.$team['Popularity'].', '.$team['Assists'].', '.$team['Cheerleaders'].', '.$team['Apothecary'].', '.$team['Treasury'].', '.$team['Reroll'].')';
	$result=mysql_query($team_request);
	if (!$result) 
	{
		echo "Requete : ".$team_request."<br>";
		die('Requête invalide : ' . mysql_error());
	}
	$team_id=mysql_insert_id();
	
	$players=$team['Players'];
	$players=$players['Player'];
	$taille=count($players);
	for($i = 0; $i < $taille; $i++) 
	{
		// Create chapter
		$player=$players[$i];
		$player_request='INSERT INTO player (idPlayer, Player_Type_idPlayer_Type, Team_idTeam, Name, Ranking, MissNextGame, Completion, Touchdowns, Casualties, Interceptions, MVP, Persistant, Number) VALUES ';
		$player_request=$player_request.'(NULL , '.$player['TypeId'].', '.$team_id.', \''.$player['Name'].'\', \''.$player['Ranking'].'\', '.$player['MissNextGame'].', '.$player['Completion'].', '.$player['Touchdowns'].', '.$player['Casualties'].', '.$player['Interception'].', '.$player['MVP'].', '.$player['Persistant'].', '.$player['Number'].')';
		$result=mysql_query($player_request);
		if (!$result) 
		{
			die('Requête invalide : ' . mysql_error());
		}
    }
	mysql_close();
	return $resultat;
}



function newChallenge($challengerId,$opponentId,$leagueId)
{
	include('config_inc.php');
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);

	$request='INSERT INTO `match` (idMatch , `date`, League_idLeague, Team_idTeam_1, Team_idTeam_2, State, Score_1, Score_2, Winner_idTeam, Round, ExtraTime, Public, FAME_1, FAME_2, Winnings_1, Winnings_2, challengerId) VALUES (NULL ,';
	/*DATE */
	$date=getdate();
	$request=$request.'\''.$date['year'].'-'.$date['mon'].'-'.$date['mday'].' '.$date['hours'].':'.$date['minutes'].':'.$date['seconds'].'\'';
	$request=$request.', '.$leagueId.', '.$challengerId.', '.$opponentId.', 1 , NULL , NULL , NULL , NULL , NULL , NULL , NULL , NULL , NULL , NULL ,'.$challengerId.')';
	$result=mysql_query($request);
	$resultat=mysql_insert_id();
	if (!$result) 
	{
		echo "Requete : ".$team_request."<br>";
		die('Requête invalide : ' . mysql_error());
	}
	
	mysql_close();
	return $resultat;
}

function getMyTeams($user)
{
	include('config_inc.php');
	$resultat = array();
	$teams=array();
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	$result=mysql_query('SELECT team.*, coach.Nickname FROM team, coach WHERE ( team.Coach_idCoach = coach.idCoach ) AND (coach.Nickname=\''.$user.'\')');
	$num_team=mysql_num_rows ($result);
	if ($num_team>0)
	{
		while ($donnees = mysql_fetch_array($result))
		{
			$player=array();
			$players=array();
			$result_2= null;

			/* Get the players */
			$result_2=mysql_query('SELECT * FROM player WHERE team_idTeam='.$donnees[0]);
			while ($donnees_2=mysql_fetch_array($result_2))
			{
				$result_3=mysql_query('SELECT injuries.name FROM injuries,player_has_injuries WHERE (injuries.idInjuries= player_has_injuries.Injuries_idInjuries) AND (player_has_injuries.Player_idPlayer='.$donnees_2[0].')');
				$injury = array();
				while($donnees_3=mysql_fetch_array($result_3))
				{
					$injury[] = $donnees_3[0];
				}
				$injuries=array('String'=> $injury);
				
				$result_4=mysql_query('SELECT competence.name FROM competence,player_has_competence WHERE (player_has_competence.player_idPlayer='.$donnees_2[0].') AND (competence.idCompetence=player_has_competence.competence_idCompetence)');
				$competence = array();
				while($donnees_4=mysql_fetch_array($result_4))
				{
					$competence[] = $donnees_4[0];
				}
				$competences=array('String'=> $competence);

				$player[]=array(
						'Id' => $donnees_2[0],
						'Name' => $donnees_2[3],
						'Ranking' => $donnees_2[4],
						'TypeId' => $donnees_2[1],
						'Completion' => $donnees_2[6],
						'Interception' => $donnees_2[9],
						'Touchdowns' => $donnees_2[7],
						'Casualties' => $donnees_2[8],
						'MVP' => $donnees_2[10],
						'Persistant' => $donnees_2[11],
						'MissNextGame' => $donnees_2[5],
						'Competences' => $competences,
						'Number' => $donnees_2[12],
						'Injuries' => $injuries,
						'Retired' => $donnees_2[14],
						'Dead' => $donnees_2[15],
						'Icon' => $donnees_2[16]
				);
				$players=array('Player'=>$player);
			}
		
			$matches=array();
			$matchs	=array();		
			/* Get the matches */
			
			$result_2=mysql_query('SELECT *,DATE_FORMAT(`date`, \'%Y %m %d %H %i %s\') as newdate FROM `match` WHERE (Team_idTeam_1='.$donnees[0].') OR (Team_idTeam_2='.$donnees[0].') ORDER BY `date` DESC');
			$num_matches=mysql_num_rows ($result_2);
			if ($num_matches>0)
			{
				while ($donnees_2=mysql_fetch_array($result_2))
				{
					$match=array();
					$opponentId=0;
					$myScore=0;
					$opponentScore=0;
					$myWinnings=0;
					$opponentWinnings=0;
						
					if ($donnees[0]==$donnees_2[3])
					{
						$opponentId=$donnees_2[4];
						$myScore=$donnees_2[6];
						$opponentScore=$donnees_2[7];
						$myWinnings=$donnees_2[14];
						$opponentWinnings=$donnees_2[15];
					}
					else
					{
						$opponentId=$donnees_2[3];
						$myScore=$donnees_2[7];
						$opponentScore=$donnees_2[6];
						$myFAME=$donnees_2[13];
						$opponentFAME=$donnees_2[12];
						$myWinnings=$donnees_2[15];
						$opponentWinnings=$donnees_2[14];
					}
					
					$result_3=mysql_query('SELECT name FROM team where idTeam='.$opponentId);
					$num_names=mysql_num_rows ($result_3);
					
					$opponentName="";
					if ($num_names==1)
					{
						$donnees_3=mysql_fetch_array($result_3);
						$opponentName=$donnees_3[0];
					}
					
					$actions=array();
					$result_4=mysql_query('SELECT action.*,action_type.Name,p.Name,o.Name,p.Number, o.Number FROM action, action_type, player p, player o where (Match_idMatch='.$donnees_2[0].') AND (action_type.idAction_type=action.Action_type_idAction_type) and (o.idPlayer=action.Opponent_idPlayer) AND (p.idPlayer=action.Player_idPlayer)');
					$num_actions=mysql_num_rows ($result_4);
					if ($num_actions>0)
					{
						$actiontab=array();
						while ($donnees_4=mysql_fetch_array($result_4))
						{
							$action=array(
							'Id' => $donnees_4[0],	
							'ActionType' => $donnees_4[3],
							'Data' => $donnees_4[7],
							'TeamId' => $donnees_4[5],
							'PlayerNumber' => $donnees_4[11],
							'OpponentPlayerNumber' => $donnees_4[12],
							'PlayerId' => $donnees_4[2],
							'OpponentPlayerId' => $donnees_4[4],
							'Turn' => $donnees_4[6],
							'ActionName' => $donnees_4[8],
							'PlayerName' => $donnees_4[9],
							'OpponentPlayerName' => $donnees_4[10]
						);
						$actiontab[]=$action;
						$actions=array('Action'=>$actiontab);
						}
					}
					
					$match=array(
						'Id' => $donnees_2[0],
						'LeagueId' => $donnees_2[2],
						'State' => $donnees_2[5],
						'OpponentId' => $opponentId,
						'MyScore' => $myScore,
						'OpponentScore' => $opponentScore,
						'WinnerId' => $donnees_2[8],
						'Round' => $donnees_2[9],
						'Public' => $donnees_2[11],
						'ExtraTime' => $donnees_2[10],
						'MyFAME' => $myFAME,
						'OpponentFAME' => $opponentFAME,
						'MyWinnings' => $myWinnings,
						'OpponentWinnings' => $opponentWinnings,
						'OpponentName' => $opponentName,
						'Date' => $donnees_2[18],
						'Actions' => $actions,
						'ChallengerId' => $donnees_2[16]
					);
					$matchs[]=$match;
				}
				$matches=array('Match'=>$matchs);
			}

			$team=array(
				'Id' => $donnees[0],
				'Name' => $donnees[4],
				'Reroll' => $donnees[10],
				'Apothecary' => $donnees[8],
				'Treasury' => $donnees[9],
				'Cheerleaders' => $donnees[7],
				'Assists' => $donnees[6],
				'Popularity' => $donnees[5],
				'RaceId' => $donnees[2],
	        	'Players' => $players,
				'LeagueId' => $donnees[1],
				'Matches' => $matches,
				'Coach' => $donnees[12]
				);
				
			$teams[]=	$team;
		}
	}
	$resultat= array( 'Team' => $teams);
	mysql_close();

  	return $resultat;
  }

  
function getTeamsForChallenge($leagueId)
{
	include('config_inc.php');
	$resultat = array();
	$teams=array();
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	$result=mysql_query('SELECT team.*, coach.Nickname FROM team, coach WHERE ( team.Coach_idCoach = coach.idCoach ) AND (team.idLeague='.$leagueId.')');
	$num_team=mysql_num_rows ($result);
	if ($num_team>0)
	{
		while ($donnees = mysql_fetch_array($result))
		{
			$player=array();
			$players=array();
			$result_2= null;

			/* Get the players */
			$result_2=mysql_query('SELECT * FROM player WHERE team_idTeam='.$donnees[0]);
			while ($donnees_2=mysql_fetch_array($result_2))
			{
				$result_3=mysql_query('SELECT injuries.name FROM injuries,player_has_injuries WHERE (injuries.idInjuries= player_has_injuries.Injuries_idInjuries) AND (player_has_injuries.Player_idPlayer='.$donnees_2[0].')');
				$injury = array();
				while($donnees_3=mysql_fetch_array($result_3))
				{
					$injury[] = $donnees_3[0];
				}
				$injuries=array('String'=> $injury);
				
				$result_4=mysql_query('SELECT competence.name FROM competence,player_has_competence WHERE (player_has_competence.player_idPlayer='.$donnees_2[0].') AND (competence.idCompetence=player_has_competence.competence_idCompetence)');
				$competence = array();
				while($donnees_4=mysql_fetch_array($result_4))
				{
					$competence[] = $donnees_4[0];
				}
				$competences=array('String'=> $competence);

				$player[]=array(
						'Id' => $donnees_2[0],
						'Name' => $donnees_2[3],
						'Ranking' => $donnees_2[4],
						'TypeId' => $donnees_2[1],
						'Completion' => $donnees_2[6],
						'Interception' => $donnees_2[9],
						'Touchdowns' => $donnees_2[7],
						'Casualties' => $donnees_2[8],
						'MVP' => $donnees_2[10],
						'Persistant' => $donnees_2[11],
						'MissNextGame' => $donnees_2[5],
						'Competences' => $competences,
						'Number' => $donnees_2[12],
						'Injuries' => $injuries,
						'Retired' => $donnees_2[14],
						'Dead' => $donnees_2[15],
						'Icon' => $donnees_2[16]
				);
				$players=array('Player'=>$player);
			}
		
			$matches=array();
			$matchs	=array();		
			/* Get the matches */
			
			$result_2=mysql_query('SELECT *,DATE_FORMAT(`date`, \'%Y %m %d %H %i %s\') as newdate FROM `match` WHERE (Team_idTeam_1='.$donnees[0].') OR (Team_idTeam_2='.$donnees[0].') ORDER BY `date` DESC');
			$num_matches=mysql_num_rows ($result_2);
			if ($num_matches>0)
			{
				while ($donnees_2=mysql_fetch_array($result_2))
				{
					$match=array();
					$opponentId=0;
					$myScore=0;
					$opponentScore=0;
					$myWinnings=0;
					$opponentWinnings=0;
						
					if ($donnees[0]==$donnees_2[3])
					{
						$opponentId=$donnees_2[4];
						$myScore=$donnees_2[6];
						$opponentScore=$donnees_2[7];
						$myWinnings=$donnees_2[14];
						$opponentWinnings=$donnees_2[15];
					}
					else
					{
						$opponentId=$donnees_2[3];
						$myScore=$donnees_2[7];
						$opponentScore=$donnees_2[6];
						$myFAME=$donnees_2[13];
						$opponentFAME=$donnees_2[12];
						$myWinnings=$donnees_2[15];
						$opponentWinnings=$donnees_2[14];
					}
					
					$result_3=mysql_query('SELECT name FROM team where idTeam='.$opponentId);
					$num_names=mysql_num_rows ($result_3);
					
					$opponentName="";
					if ($num_names==1)
					{
						$donnees_3=mysql_fetch_array($result_3);
						$opponentName=$donnees_3[0];
					}
					
					$actions=array();
					$result_4=mysql_query('SELECT action.*,action_type.Name,p.Name,o.Name,p.Number, o.Number FROM action, action_type, player p, player o where (Match_idMatch='.$donnees_2[0].') AND (action_type.idAction_type=action.Action_type_idAction_type) and (o.idPlayer=action.Opponent_idPlayer) AND (p.idPlayer=action.Player_idPlayer)');
					$num_actions=mysql_num_rows ($result_4);
					if ($num_actions>0)
					{
						$actiontab=array();
						while ($donnees_4=mysql_fetch_array($result_4))
						{
							$action=array(
							'Id' => $donnees_4[0],	
							'ActionType' => $donnees_4[3],
							'Data' => $donnees_4[7],
							'TeamId' => $donnees_4[5],
							'PlayerNumber' => $donnees_4[11],
							'OpponentPlayerNumber' => $donnees_4[12],
							'PlayerId' => $donnees_4[2],
							'OpponentPlayerId' => $donnees_4[4],
							'Turn' => $donnees_4[6],
							'ActionName' => $donnees_4[8],
							'PlayerName' => $donnees_4[9],
							'OpponentPlayerName' => $donnees_4[10]
						);
						$actiontab[]=$action;
						$actions=array('Action'=>$actiontab);
						}
					}
					
					$match=array(
						'Id' => $donnees_2[0],
						'LeagueId' => $donnees_2[2],
						'State' => $donnees_2[5],
						'OpponentId' => $opponentId,
						'MyScore' => $myScore,
						'OpponentScore' => $opponentScore,
						'WinnerId' => $donnees_2[8],
						'Round' => $donnees_2[9],
						'Public' => $donnees_2[11],
						'ExtraTime' => $donnees_2[10],
						'MyFAME' => $myFAME,
						'OpponentFAME' => $opponentFAME,
						'MyWinnings' => $myWinnings,
						'OpponentWinnings' => $opponentWinnings,
						'OpponentName' => $opponentName,
						'Date' => $donnees_2[18],
						'Actions' => $actions,
						'ChallengerId' => $donnees_2[16]
					);
					$matchs[]=$match;
				}
				$matches=array('Match'=>$matchs);
			}

			$team=array(
				'Id' => $donnees[0],
				'Name' => $donnees[4],
				'Reroll' => $donnees[10],
				'Apothecary' => $donnees[8],
				'Treasury' => $donnees[9],
				'Cheerleaders' => $donnees[7],
				'Assists' => $donnees[6],
				'Popularity' => $donnees[5],
				'RaceId' => $donnees[2],
	        	'Players' => $players,
				'LeagueId' => $donnees[1],
				'Matches' => $matches,
				'Coach' => $donnees[12]
				);
				
			$teams[]=	$team;
		}
	}
	$resultat= array( 'Team' => $teams);
	mysql_close();

  	return $resultat;
  }
  
 function removeTeam($id)
{
	include('config_inc.php');
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	$request='UPDATE team SET Active = 0 WHERE team.idTeam ='.$id;
	$result=mysql_query($request);
	
	mysql_close();
	return $resultat;
}


function getTeam($id)
{
	include('config_inc.php');
	$resultat = array();

	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	$result=mysql_query('SELECT team.*,coach.Nickname FROM team,coach WHERE (team.idTeam='.$id.') AND ( team.Coach_idCoach = coach.idCoach )');
	$num_team=mysql_num_rows ($result);
	if ($num_team>0)
	{
		while ($donnees = mysql_fetch_array($result))
		{
			$player=array();
			$players=array();
			$result_2= null;

			/* Get the players */
			$result_2=mysql_query('SELECT * FROM player WHERE team_idTeam='.$donnees[0]);
			while ($donnees_2=mysql_fetch_array($result_2))
			{
				$result_3=mysql_query('SELECT injuries.name FROM injuries,player_has_injuries WHERE (injuries.idInjuries= player_has_injuries.Injuries_idInjuries) AND (player_has_injuries.Player_idPlayer='.$donnees_2[0].')');
				$injury = array();
				while($donnees_3=mysql_fetch_array($result_3))
				{
					$injury[] = $donnees_3[0];
				}
				$injuries=array('String'=> $injury);
				
				$result_4=mysql_query('SELECT competence.name FROM competence,player_has_competence WHERE (player_has_competence.player_idPlayer='.$donnees_2[0].') AND (competence.idCompetence=player_has_competence.competence_idCompetence)');
				$competence = array();
				while($donnees_4=mysql_fetch_array($result_4))
				{
					$competence[] = $donnees_4[0];
				}
				$competences=array('String'=> $competence);

				$player[]=array(
						'Id' => $donnees_2[0],
						'Name' => $donnees_2[3],
						'Ranking' => $donnees_2[4],
						'TypeId' => $donnees_2[1],
						'Completion' => $donnees_2[6],
						'Interception' => $donnees_2[9],
						'Touchdowns' => $donnees_2[7],
						'Casualties' => $donnees_2[8],
						'MVP' => $donnees_2[10],
						'Persistant' => $donnees_2[11],
						'MissNextGame' => $donnees_2[5],
						'Competences' => $competences,
						'Number' => $donnees_2[12],
						'Injuries' => $injuries,
						'Retired' => $donnees_2[14],
						'Dead' => $donnees_2[15],
						'Icon' => $donnees_2[16],
				);
				$players=array('Player'=>$player);
			}
		
			$matches=array();
			$matchs	=array();		
			
			/* Get the matches */
			$result_2=mysql_query('SELECT *,DATE_FORMAT(`date`, \'%Y %m %d %H %i %s\') as newdate FROM `match` WHERE (Team_idTeam_1='.$donnees[0].') OR (Team_idTeam_2='.$donnees[0].') ORDER BY `date` DESC');
			$num_matches=mysql_num_rows ($result_2);
			if ($num_matches>0)
			{
				while ($donnees_2=mysql_fetch_array($result_2))
				{
					$match=array();
					$opponentId=0;
					$myScore=0;
					$opponentScore=0;
					$myWinnings=0;
					$opponentWinnings=0;
						
					if ($donnees[0]==$donnees_2[3])
					{
						$opponentId=$donnees_2[4];
						$myScore=$donnees_2[6];
						$opponentScore=$donnees_2[7];
						$myWinnings=$donnees_2[14];
						$opponentWinnings=$donnees_2[15];
					}
					else
					{
						$opponentId=$donnees_2[3];
						$myScore=$donnees_2[7];
						$opponentScore=$donnees_2[6];
						$myFAME=$donnees_2[13];
						$opponentFAME=$donnees_2[12];
						$myWinnings=$donnees_2[15];
						$opponentWinnings=$donnees_2[14];
					}
					
					$result_3=mysql_query('SELECT name FROM team where idTeam='.$opponentId);
					$num_names=mysql_num_rows ($result_3);
					
					$opponentName="";
					if ($num_names==1)
					{
						$donnees_3=mysql_fetch_array($result_3);
						$opponentName=$donnees_3[0];
					}
					
					$actions=array();
					$result_4=mysql_query('SELECT action.*,action_type.Name,p.Name,o.Name,p.Number, o.Number FROM action, action_type, player p, player o where (Match_idMatch='.$donnees_2[0].') AND (action_type.idAction_type=action.Action_type_idAction_type) and (o.idPlayer=action.Opponent_idPlayer) AND (p.idPlayer=action.Player_idPlayer)');
					$num_actions=mysql_num_rows ($result_4);
					if ($num_actions>0)
					{
						$actiontab=array();
						while ($donnees_4=mysql_fetch_array($result_4))
						{
							$action=array(
							'Id' => $donnees_4[0],	
							'ActionType' => $donnees_4[3],
							'Data' => $donnees_4[7],
							'TeamId' => $donnees_4[5],
							'PlayerNumber' => $donnees_4[11],
							'OpponentPlayerNumber' => $donnees_4[12],
							'PlayerId' => $donnees_4[2],
							'OpponentPlayerId' => $donnees_4[4],
							'Turn' => $donnees_4[6],
							'ActionName' => $donnees_4[8],
							'PlayerName' => $donnees_4[9],
							'OpponentPlayerName' => $donnees_4[10]
						);
						$actiontab[]=$action;
						$actions=array('Action'=>$actiontab);
						}
					}
					
					$match=array(
						'Id' => $donnees_2[0],
						'LeagueId' => $donnees_2[2],
						'State' => $donnees_2[5],
						'OpponentId' => $opponentId,
						'MyScore' => $myScore,
						'OpponentScore' => $opponentScore,
						'WinnerId' => $donnees_2[8],
						'Round' => $donnees_2[9],
						'Public' => $donnees_2[11],
						'ExtraTime' => $donnees_2[10],
						'MyFAME' => $myFAME,
						'OpponentFAME' => $opponentFAME,
						'MyWinnings' => $myWinnings,
						'OpponentWinnings' => $opponentWinnings,
						'OpponentName' => $opponentName,
						'Date' => $donnees_2[16],
						'Actions' => $actions
					);
					$matchs[]=$match;
				}
				$matches=array('Match'=>$matchs);
			}
			
			$team=array(
				'Id' => $donnees[0],
				'Name' => $donnees[4],
				'Reroll' => $donnees[10],
				'Apothecary' => $donnees[8],
				'Treasury' => $donnees[9],
				'Cheerleaders' => $donnees[7],
				'Assists' => $donnees[6],
				'Popularity' => $donnees[5],
				'RaceId' => $donnees[2],
		        'Players' => $players,
				'LeagueId' => $donnees[1],
				'Matches' => $matches,
				'Coach' => $donnees[12]
				);
		}
	}
	$resultat= $team;
	mysql_close();

  	return $resultat;
  }
  
  function cancelMatch($matchId)
  {
  include('config_inc.php');
	$resultat = 0;

	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	$result=mysql_query('DELETE FROM `match` WHERE idMatch='.$matchId);
	
	 mysql_close();

  	return $resultat;
  }
?>
