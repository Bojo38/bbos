<?php
	include('config_inc.php');
	
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	
	$result_2=mysql_query('SELECT * FROM `match` WHERE (Team_idTeam_1=1) OR (Team_idTeam_2=1)');
	$num_matches=mysql_num_rows ($result_2);
	echo $num_matches.' Matches<br>';
	if ($num_matches>0)
	{
		while ($donnees_2=mysql_fetch_array($result_2))
		{
			$actions=array();
			$result_4=mysql_query('SELECT action.*,action_type.Name,p.Name,o.Name FROM action, action_type, player p, player o where (Match_idMatch='.$donnees_2[0].') AND (action_type.idAction_type=action.Action_type_idAction_type) and (o.idPlayer=action.Opponent_idPlayer) AND (p.idPlayer=action.Player_idPlayer)');
			$num_actions=mysql_num_rows ($result_4);
			echo $num_actions.' Actions<br>';
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
					echo $action['OpponentPlayerName'].'<br>';
				}
				
			}
		}
	}
	
	mysql_close();
	

?>


