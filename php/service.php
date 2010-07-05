<?php

include('lib/nusoap.php');
include('config_inc.php');

$serveur = new soap_server;


// Initialise le support WSDL
$serveur->configureWSDL('Webbos', 'http://Webbos/Webbos');

$serveur->wsdl->addComplexType(
   'StringArray',
   'complexType',
   'struct',
   'sequence',
   '',
   array(
      'String'   => array('name' => 'String' , 'type'=> 'xsd:string', 'minOccurs' => "0", 'maxOccurs'=>'unbounded')
   )
);

$serveur->wsdl->addComplexType(
    'PlayerType',
    'complexType',
    'struct',
    'all',
    '',
    array(
	'Id' => array('name'=>'Id','type'=>'xsd:int'),
    'Name' => array('name'=>'Name','type'=>'xsd:string'),
	'Position' => array('name'=>'Position','type'=>'xsd:string'),
    'Cost' => array('name'=>'Cost','type'=>'xsd:int'),
	'Movement' => array('name'=>'Movement','type'=>'xsd:int'),
	'Strength' => array('name'=>'Strength','type'=>'xsd:int'),
	'Agility' => array('name'=>'Agility','type'=>'xsd:int'),
	'Armor' => array('name'=>'Armor','type'=>'xsd:int'),
	'Limit' => array('name'=>'Limit','type'=>'xsd:int'),
	'IsStar' => array('name'=>'IsStar','type'=>'xsd:boolean'),
	'Competences' => array('name'=>'Competences','type'=>'tns:StringArray'),
	'SimpleRoll' => array('name'=>'SimpleRoll','type'=>'tns:StringArray'),
	'DoubleRoll' => array('name'=>'DoubleRoll','type'=>'tns:StringArray')
    )
);


$serveur->wsdl->addComplexType(
   'PlayerTypeArray',
   'complexType',
   'struct',
   'sequence',
   '',
   array(
      'PlayerType'   => array('name' => 'PlayerType' , 'type'=> 'tns:PlayerType', 'minOccurs' => "0", 'maxOccurs'=>'unbounded')
   )
);

 
$serveur->wsdl->addComplexType(
    'TeamType',
    'complexType',
    'struct',
    'all',
    '',
    array(
	'Id' => array('name'=>'Id','type'=>'xsd:int'),
	'Name' => array('name'=>'Name','type'=>'xsd:string'),
    'RerollCost' => array('name'=>'RerollCost','type'=>'xsd:int'),
	'Apothecary' => array('name'=>'Apothecary','type'=>'xsd:boolean'),
	'ApothecaryCost' => array('name'=>'ApothecaryCost','type'=>'xsd:int'),
	'WizardCost' => array('name'=>'WizardCost','type'=>'xsd:int'),
	'CanRaise' => array('name'=>'CanRaise','type'=>'xsd:boolean'),
	'BribeCost' => array('name'=>'BribeCost','type'=>'xsd:int'),
	'Chef' => array('name'=>'Chef','type'=>'xsd:int'),
	'LogoURL' => array('name'=>'LogoURL','type'=>'xsd:string'),
    'PlayersType' => array('name'=>'PlayersType','type'=>'tns:PlayerTypeArray'),
	'Description' => array('name'=>'Description','type'=>'xsd:string')
    )
);

$serveur->wsdl->addComplexType(
    'Action',
    'complexType',
    'struct',
    'all',
    '',
    array(
	/*
	1  	Pass
	2 	Catch
	3 	Touchdown
	4 	Casualty
	5 	Aggression
	6 	Interception
	7 	MVP
	8 	Public
	*/
	'ActionType' => array('name'=>'ActionType','type'=>'xsd:int'),
	'Id' => array('name'=>'Id','type'=>'xsd:int'),
    'PlayerId' => array('name'=>'PlayerId','type'=>'xsd:int'),
	'PlayerName' => array('name'=>'PlayerName','type'=>'xsd:string'),
	'TeamId' => array('name'=>'TeamId','type'=>'xsd:int'),
	'OpponentPlayerId' => array('name'=>'OpponentPlayerId','type'=>'xsd:int'),
	'OpponentPlayerName' => array('name'=>'OpponentPlayerName','type'=>'xsd:string'),
	'Data' => array('name'=>'Data','type'=>'xsd:string'),
	'Turn' => array('name'=>'Turn','type'=>'xsd:int'),
	'ActionName' => array('name'=>'ActionName','type'=>'xsd:string')
    )
);

$serveur->wsdl->addComplexType(
   'ActionArray',
   'complexType',
   'struct',
   'sequence',
   '',
   array(
      'Action'   => array('name' => 'Action' , 'type'=> 'tns:Action', 'minOccurs' => "0", 'maxOccurs'=>'unbounded')
   )
);

$serveur->wsdl->addComplexType(
    'Match',
    'complexType',
    'struct',
    'all',
    '',
    array(
	'Id' => array('name'=>'Id','type'=>'xsd:int'),
	'State' => array('name'=>'State','type'=>'xsd:int'),
	'OpponentId' => array('name'=>'OpponentId','type'=>'xsd:int'),
	'OpponentName' => array('name'=>'OpponentName','type'=>'xsd:string'),
    'Date' => array('name'=>'date','type'=>'xsd:string'),
	'LeagueId' => array('name'=>'LeagueId','type'=>'xsd:int'),
	'MyScore' => array('name'=>'MyScore','type'=>'xsd:int'),
	'OpponentScore' => array('name'=>'OpponentScore','type'=>'xsd:int'),
	'WinnerId' => array('name'=>'WinnerId','type'=>'xsd:int'),
	'ExtraTime' => array('name'=>'ExtraTime','type'=>'xsd:boolean'),
	'Public' => array('name'=>'Public','type'=>'xsd:int'),
	'MyFAME' => array('name'=>'MyFAME','type'=>'xsd:int'),
    'OpponentFAME' => array('name'=>'OpponentFAME','type'=>'xsd:int'),
	'MyWinnings' => array('name'=>'MyWinnings','type'=>'xsd:int'),
	'OpponentWinnings' => array('name'=>'OpponentWinnings','type'=>'xsd:int'),
	'Actions' => array('name'=>'Actions','type'=>'tns:ActionArray')
    )
);

$serveur->wsdl->addComplexType(
   'MatchArray',
   'complexType',
   'struct',
   'sequence',
   '',
   array(
      'Match'   => array('name' => 'Match' , 'type'=> 'tns:Match', 'minOccurs' => "0", 'maxOccurs'=>'unbounded')
   )
);

$serveur->wsdl->addComplexType(
   'TeamTypeArray',
   'complexType',
   'struct',
   'sequence',
   '',
   array(
      'TeamType'   => array('name' => 'TeamType' , 'type'=> 'tns:TeamType', 'minOccurs' => "0", 'maxOccurs'=>'unbounded')
   )
);


$serveur->wsdl->addComplexType(
    'Player',
    'complexType',
    'struct',
    'all',
    '',
    array(
	'Id' => array('name'=>'Id','type'=>'xsd:int'),
    	'Name' => array('name'=>'Name','type'=>'xsd:string'),
	'Ranking' => array('name'=>'Ranking','type'=>'xsd:string'),
   	 'TypeId' => array('name'=>'TypeId','type'=>'xsd:int'),
	'Completion' => array('name'=>'Completion','type'=>'xsd:int'),
	'Interception' => array('name'=>'Interception','type'=>'xsd:int'),
	'Touchdowns' => array('name'=>'Touchdowns','type'=>'xsd:int'),
	'Casualties' => array('name'=>'Casualties','type'=>'xsd:int'),
	'MVP' => array('name'=>'MVP','type'=>'xsd:int'),
	'Persistant' => array('name'=>'Persistant','type'=>'xsd:int'),
	'MissNextGame' => array('name'=>'IsStar','type'=>'xsd:boolean'),
	'Competences' => array('name'=>'Competences','type'=>'tns:StringArray'),
	'Number' => array('name'=>'Number','type'=>'xsd:int'),
	'Injuries' => array('name'=>'Injuries','type'=>'tns:StringArray'),
	'Status' => array('name'=>'Status','type'=>'xsd:string')
    )
);

$serveur->wsdl->addComplexType(
   'PlayerArray',
   'complexType',
   'struct',
   'sequence',
   '',
   array(
      'Player'   => array('name' => 'Player' , 'type'=> 'tns:Player', 'minOccurs' => "0", 'maxOccurs'=>'unbounded')
   )
);

$serveur->wsdl->addComplexType(
    'Team',
    'complexType',
    'struct',
    'all',
    '',
    array(
	'Id' => array('name'=>'Id','type'=>'xsd:int'),
	'Name' => array('name'=>'Name','type'=>'xsd:string'),
    'Reroll' => array('name'=>'Reroll','type'=>'xsd:int'),
	'Apothecary' => array('name'=>'Apothecary','type'=>'xsd:boolean'),
	'Treasury' => array('name'=>'Treasury','type'=>'xsd:int'),
	'Cheerleaders' => array('name'=>'Cheerleaders','type'=>'xsd:int'),
	'Assists' => array('name'=>'Assists','type'=>'xsd:int'),
	'Popularity' => array('name'=>'Popularity','type'=>'xsd:int'),
	'RaceId' => array('name'=>'Race','type'=>'xsd:int'),
    'Players' => array('name'=>'Players','type'=>'tns:PlayerArray'),
    'LeagueId' => array('name'=>'League','type'=>'xsd:int'),
	'Matches' => array('name'=>'Matches','type'=>'tns:MatchArray')
    )
);


$serveur->wsdl->addComplexType(
   'TeamArray',
   'complexType',
   'struct',
   'sequence',
   '',
   array(
      'Team'   => array('name' => 'Team' , 'type'=> 'tns:Team', 'minOccurs' => "0", 'maxOccurs'=>'unbounded')
   )
);

$serveur->register('getMyTeams',
		array('user' => 'xsd:string'),      // input parameters
   		array('return' => 'tns:TeamArray'),    // output parameters
	   $webserver.'/service',          // namespace (espace de nommage unique)
	   $webserver.'/service#getMyTeams', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'Retrun my teams'         // documentation
);

$serveur->register('getAllTeamTypes',
		array(),      // input parameters
   		array('return' => 'tns:TeamTypeArray'),    // output parameters
	   $webserver.'/service',          // namespace (espace de nommage unique)
	   $webserver.'/service#getAllTeamTypes', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'Retrun all avilable team types'         // documentation
);

$serveur->register('getTeamTypeNumber',
		array(),      // input parameters
   		array('return' => 'xsd:int'),    // output parameters
	   $webserver.'/service',          // namespace (espace de nommage unique)
	   $webserver.'/service#getTeamTypeNumber', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'Retrun team number'         // documentation
);


$serveur->register('isValidUser',
		array('user' => 'xsd:string', 'pass' => 'xsd:string'),      // input parameters
   		array('return' => 'xsd:int'),    // output parameters
	   $webserver.'/service',          // namespace (espace de nommage unique)
	   $webserver.'/service#isValidUser', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'Return icon URL'         // documentation
);


$serveur->register('addTeam',
		array('user' => 'xsd:string', 'team' => 'tns:Team'),      // input parameters
   		array('return' => 'xsd:int'),    // output parameters
	   $webserver.'/service',          // namespace (espace de nommage unique)
	   $webserver.'/service#addTeam', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'Add new team'         // documentation
);

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

	$team_request='INSERT INTO bbos_team (idTeam ,idLeague ,Team_Type_idTeam_Type ,Coach_idCoach ,Name ,Popularity ,Assists ,Cheerleaders ,Apothecary ,Treasury ,Reroll) VALUES (NULL ,';
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
		$player_request='INSERT INTO bbos_player (idPlayer, Player_Type_idPlayer_Type, Team_idTeam, Name, Ranking, MissNextGame, Completion, Touchdowns, Casualties, Interceptions, MVP, Persistant, Number, Status) VALUES ';
		$player_request=$player_request.'(NULL , '.$player['TypeId'].', '.$team_id.', \''.$player['Name'].'\', \''.$player['Ranking'].'\', '.$player['MissNextGame'].', '.$player['Completion'].', '.$player['Touchdowns'].', '.$player['Casualties'].', '.$player['Interception'].', '.$player['MVP'].', '.$player['Persistant'].', '.$player['Number'].', \''.$player['Status'].'\')';
		$result=mysql_query($player_request);
		if (!$result) 
		{
			die('Requête invalide : ' . mysql_error());
		}
    }
	mysql_close();
	
	return $resultat;
}


function isValidUser($user,$pass)
{
	include('config_inc.php');
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	$request='select COUNT(*) from bbos_coach where nickname=\''.$user.'\' and PWD=\''.$pass.'\'';
	$result=mysql_query($request);
	$donnees = mysql_fetch_array($result);
	if ($donnees[0]==1)
	{
		$resultat=1;
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
	
	$result=mysql_query('SELECT bbos_team.*, bbos_coach.Name FROM bbos_team, bbos_coach WHERE ( bbos_team.Coach_idCoach = bbos_coach.idCoach ) AND (bbos_coach.Nickname=\''.$user.'\')');
	$num_team=mysql_num_rows ($result);
	if ($num_team>0)
	{
		while ($donnees = mysql_fetch_array($result))
		{
			$player=array();
			$players=array();
			$result_2= null;

			/* Get the players */
			$result_2=mysql_query('SELECT * FROM bbos_player WHERE team_idTeam='.$donnees[0]);
			while ($donnees_2=mysql_fetch_array($result_2))
			{
				$result_3=mysql_query('SELECT bbos_injuries.name FROM bbos_injuries,bbos_player_has_injuries WHERE (bbos_injuries.idInjuries= bbos_player_has_injuries.Injuries_idInjuries) AND (bbos_player_has_injuries.Player_idPlayer='.$donnees_2[0].')');
				$injury = array();
				while($donnees_3=mysql_fetch_array($result_3))
				{
					$injury[] = $donnees_3[0];
				}
				$injuries=array('String'=> $injury);
				
				$result_4=mysql_query('SELECT bbos_competence.name FROM bbos_competence,bbos_player_has_competence WHERE (bbos_player_has_competence.player_idPlayer='.$donnees_2[0].') AND (bbos_competence.idCompetence=bbos_player_has_competence.competence_idCompetence)');
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
						'Status' => $donnees_2[13]
				);
				$players=array('Player'=>$player);
			}
		
			$matches=array();
			/* Get the matches */
			
			$result_2=mysql_query('SELECT *,DATE_FORMAT(`date`, \'%M %e, %Y, %l:%i%p\') as newdate FROM `bbos_match` WHERE (Team_idTeam_1='.$donnees[0].') OR (Team_idTeam_2='.$donnees[0].')');
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
					
					$result_3=mysql_query('SELECT name FROM bbos_team where idTeam='.$opponentId);
					$num_names=mysql_num_rows ($result_3);
					
					$opponentName="";
					if ($num_names==1)
					{
						$donnees_3=mysql_fetch_array($result_3);
						$opponentName=$donnees_3[0];
					}
					
					$actions=array();
					$result_4=mysql_query('SELECT action.*,action_type.Name,p.Name,o.Name FROM bbos_action, bbos_action_type, bbos_player p, bbos_player o where (Match_idMatch='.$donnees_2[0].') AND (bbos_action_type.idAction_type=bbos_action.Action_type_idAction_type) and (o.idPlayer=action.Opponent_idPlayer) AND (p.idPlayer=action.Player_idPlayer)');
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
					
					$match[]=array(
						'Id' => $donnees_2[0],
						'LeagueId' => $donnees_2[2],
						'State' => $donnees_2[5],
						'OpponentId' => $opponentId,
						'MyScore' => $myScore,
						'OpponentScore' => $opponentScore,
						'WinnerId' => $donnees_2[8],
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
				}
				$matches=array('Match'=>$match);
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
				'Matches' => $matches);
				
			$teams[]=	$team;
		}
	}
	$resultat= array( 'Team' => $teams);
	mysql_close();

  	return $resultat;
  }

function getAllTeamTypes()
{
	include('config_inc.php');
	$resultat = array();
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	$result=mysql_query('SELECT * from bbos_team_type;');
	while ($donnees = mysql_fetch_array($result) )
	{
		$player=array();
		$players=array();
		$result_2= null;
		$result_2=mysql_query('SELECT * FROM bbos_team_type_has_player_type WHERE team_type_idTeam_Type='.$donnees[0]);
		while ($donnees_2=mysql_fetch_array($result_2))
		{

			$result_3=mysql_query('SELECT * FROM bbos_player_type WHERE idPlayer_Type='.$donnees_2[1]);
			$donnees_3=mysql_fetch_array($result_3);

			$result_4=mysql_query('SELECT competence.name FROM bbos_competence,bbos_player_type_has_competence WHERE player_type_idPlayer_Type='.$donnees_2[1].' and bbos_competence.idCompetence=player_type_has_competence.competence_idCompetence');

			$competence = array();
			while($donnees_4=mysql_fetch_array($result_4))
			{
				$competence[] = $donnees_4[0];
			}
			$competences=array('String'=> $competence);

			$result_5=mysql_query('SELECT bbos_competence_type.Name FROM bbos_competence_type,bbos_player_type_has_simplecompetence_type WHERE player_type_idPlayer_Type='.$donnees_2[1].' and bbos_competence_type.idCompetence_type=bbos_player_type_has_simplecompetence_type.competence_type_idCompetence_type');

			$simpleroll = array();
			while($donnees_5=mysql_fetch_array($result_5))
			{
				$simpleroll[] = $donnees_5[0];
			}
			$simplerolls=array('String'=> $simpleroll);

			$result_6=mysql_query('SELECT bbos_competence_type.Name FROM bbos_competence_type,bbos_player_type_has_doublecompetence_type WHERE player_type_idPlayer_Type='.$donnees_2[1].' and bbos_competence_type.idCompetence_type=bbos_player_type_has_doublecompetence_type.competence_type_idCompetence_type');

			$doubleroll = array();
			while($donnees_6=mysql_fetch_array($result_6))
			{
				$doubleroll[] = $donnees_6[0];
			}
			$doublerolls=array('String'=> $doubleroll);

			$player[]=array(
					'Id' => $donnees_3[0],
					'Name' => $donnees_3[1],
					'Position' => $donnees_3[7],
					'Cost' => $donnees_3[6],
					'Movement' => $donnees_3[2],
					'Strength' => $donnees_3[3],
					'Agility' => $donnees_3[4],
					'Armor' => $donnees_3[5],
					'Limit' => $donnees_2[2],
					'IsStar' => $donnees_3[8],
					'Competences' => $competences,
					'SimpleRoll' => $simplerolls,
					'DoubleRoll' => $doublerolls,
			);
			$players=array('PlayerType'=>$player);
		}

		$teamtype=array(
			'Id' => $donnees[0],
			'Name' => $donnees[1],
			'RerollCost' => $donnees[2],
			'Apothecary' => $donnees[3],
			'ApothecaryCost' => $donnees[4],
			'WizardCost' => $donnees[5],
			'CanRaise' => $donnees[6],
			'BribeCost' => $donnees[7],
			'Chef' => $donnees[8],
			'LogoURL' => $donnees[9],
        	'PlayersType' => $players,
			'Description' => $donnees[10]);
	
		$teamtypes[]=	$teamtype;	
	}
	$resultat= array( 'TeamType' => $teamtypes);
	mysql_close();

  	return $resultat;
  }

  
    $serveur->register('soapTest',
		array(),      // input parameters
   		array('return' => 'xsd:int'),    // output parameters
	   $webserver.'/service',          // namespace (espace de nommage unique)
	   $webserver.'/service#soapTest', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'Return 1 if soap is OK'         // documentation
);

function soapTest()
{
	return 1;
}

$serveur->service($HTTP_RAW_POST_DATA);
exit();
?>


