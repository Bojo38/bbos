<?php

function getPlayers($team_id)
{
	$team=new Team($team_id);
	$players=$team->players;
	
	$a_player=array();
	$a_players=array();

	foreach ($players as $player)
	{
		foreach($player->injuries as $inj)
		{
			$injury[] = $inj->name;
		}
		$injuries=array('String'=> $injury);
		
		foreach($player->competences as $comp)
		{
			$competence[] = $comp->name;
		}
		$competences=array('String'=> $competence);

		$a_player[]=array(
				'Id' => $player->$id,
				'Name' => $player->name,
				'Ranking' => $player->ranking,
				'TypeId' => $player->type_id,
				'Completion' => $player->completions,
				'Interception' => $player->interceptions,
				'Touchdowns' => $player->touchdowns,
				'Casualties' => $player->casualties,
				'MVP' => $player->mvp,
				'Persistant' => $player->persistant,
				'MissNextGame' => $player->miss_next_game,
				'Competences' => competences,
				'Number' => $player->number,
				'Injuries' => $injuries,
				'Retired' => $player->retired,
				'Dead' => $player->dead
		);
		$a_players=array('Player'=>$a_player);
	}
	return $a_players;
}

function retirePlayer($id)
{
	include('config_inc.php');
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	$player=new Player($id);
	$player->retire();
	
	mysql_close();
	return $resultat;
}

function addNewPlayer($team_id,$player)
{
	include('config_inc.php');
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	Player::create($team_id,$player['TypeId'],$player['Name'],$player['Number']);
	
	mysql_close();
	return $resultat;
}

?>