<?php

class Team_Type
{
	public $id=0;
	public $name='';
	public $reroll_cost=0;
	public $apothecary=true;
	public $apothecary_cost=0;
	public $wizard_cost=0;
	public $can_raise=0;
	public $bribe_cost=0;
	public $chef_cost=0;
	public $logo_url='';
	public $description='';
	
	public $players_type=array();
		
	function __construct($team_type_id) {

		$result=mysql_query("SELECT * FROM bbos_team_type WHERE idTeam_Type=$team_type_id");
		$row = mysql_fetch_assoc($result);

		$this->id=$row['idTeam_Type'];
		$this->name=$row['Name'];		
		$this->reroll_cost=$row['RerollCost'];
		$this->apothecary=($row['Apothecary']==1);
		$this->apothecary_cost=($row['ApothecaryCost']==1);
		$this->wizard_cost=($row['WizardCost']==1);
		$this->can_raise=($row['CanRaise']==1);
		$this->bribe_cost=$row['BribeCost'];
		$this->chef_cost=$row['ChefCost'];		
		$this->logo_url=$row['LogoURL'];
		$this->description=$row['Description'];
		
		$this->players_type=Player_Type::getPlayersType($this->id);
    }
		
	public static function getTeamTypes()
	{
		$teams=array();
		$result=mysql_query("SELECT idTeam_Type FROM bbos_team_type");
		while ($row = mysql_fetch_assoc($result))
		{
			$teams[]=new Team_Type($row['idTeam_Type']);
		}		
		return $teams;
	}
}
?>