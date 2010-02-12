<?php

function getAllTeamTypes()
{
	include('config_inc.php');
	$resultat = array();
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	$result=mysql_query('SELECT * from team_type;');
	while ($donnees = mysql_fetch_array($result) )
	{
		$player=array();
		$players=array();
		$result_2= null;
		$result_2=mysql_query('SELECT * FROM team_type_has_player_type WHERE team_type_idTeam_Type='.$donnees[0]);
		while ($donnees_2=mysql_fetch_array($result_2))
		{

			$result_3=mysql_query('SELECT * FROM player_type WHERE idPlayer_Type='.$donnees_2[1]);
			$donnees_3=mysql_fetch_array($result_3);

			$result_4=mysql_query('SELECT competence.name FROM competence,player_type_has_competence WHERE player_type_idPlayer_Type='.$donnees_2[1].' and competence.idCompetence=player_type_has_competence.competence_idCompetence');

			$competence = array();
			while($donnees_4=mysql_fetch_array($result_4))
			{
				$competence[] = $donnees_4[0];
			}
			$competences=array('String'=> $competence);

			$result_5=mysql_query('SELECT competence_type.Name FROM competence_type,player_type_has_simplecompetence_type WHERE player_type_idPlayer_Type='.$donnees_2[1].' and competence_type.idCompetence_type=player_type_has_simplecompetence_type.competence_type_idCompetence_type');

			$simpleroll = array();
			while($donnees_5=mysql_fetch_array($result_5))
			{
				$simpleroll[] = $donnees_5[0];
			}
			$simplerolls=array('String'=> $simpleroll);

			$result_6=mysql_query('SELECT competence_type.Name FROM competence_type,player_type_has_doublecompetence_type WHERE player_type_idPlayer_Type='.$donnees_2[1].' and competence_type.idCompetence_type=player_type_has_doublecompetence_type.competence_type_idCompetence_type');

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

?>