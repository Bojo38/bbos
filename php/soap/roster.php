<?php

function getAllTeamTypes()
{
	include('config_inc.php');
	$resultat = array();
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	$team_types=Team_Type::getTeamTypes();
	
	foreach ($team_types as $tt)
	{	
		$player=array();
		$players=array();
	
		foreach ($tt->players_type as $pt)
		{
			$competence = array();
			foreach ($pt->competences as $c)
			{			
				$competence[] = $c->name;
			}
			$competences=array('String'=> $competence);

			$simpleroll = array();
			foreach($pt->simple_competence_type as $sct)
			{
				$simpleroll[] = $sct->name;
			}
			$simplerolls=array('String'=> $simpleroll);

			$doubleroll = array();
			foreach($pt->double_competence_type as $sct)
			{
				$doubleroll[] = $sct->name;
			}
			$doublerolls=array('String'=> $doubleroll);

			$player[]=array(
					'Id' => $pt->id,
					'Name' => $pt->name,
					'Position' => $pt->position,
					'Cost' => $pt->cost,
					'Movement' => $pt->movement,
					'Strength' => $pt->strength,
					'Agility' => $pt->agility,
					'Armor' => $pt->armor,
					'Limit' => $pt->limit,
					'IsStar' => $pt->is_star,
					'Competences' => $competences,
					'SimpleRoll' => $simplerolls,
					'DoubleRoll' => $doublerolls,
			);
			$players=array('PlayerType'=>$player);
		}

		$teamtype=array(
			'Id' => $tt->id,
			'Name' => $tt->name,
			'RerollCost' => $tt->reroll_cost,
			'Apothecary' => $tt->apothecary,
			'ApothecaryCost' => $tt->apothecary_cost,
			'WizardCost' => $tt->wizard_cost,
			'CanRaise' => $tt->can_raise,
			'BribeCost' => $tt->bribe_cost,
			'Chef' => $tt->chef_cost,
			'LogoURL' => $tt->logo_url,
        	'PlayersType' => $players,
			'Description' => $tt->description);
	
		$teamtypes[]=	$teamtype;	
	}
	$resultat= array( 'TeamType' => $teamtypes);
	mysql_close();
  	return $resultat;
  }

?>