<?php

class Action
{
    public $id=0;
    public $match_id=0;
    public $player_id=0;
    public $action_type_id=0;
    public $action_type_name='';
    public $opponent_player_id=0;
    public $team_id=0;
    public $turn=0;
    public $misc='';

    function __construct($id)
    {
        $result=mysql_query("SELECT * FROM bbos_action WHERE idAction=$id");
        $row = mysql_fetch_assoc($result);

		$this->id=$row['idAction'];
		$this->match_id=$row['Match_idMatch'];
		$this->player_id=$row['Player_idPlayer'];
		$this->action_type_id=$row['Action_type_idAction_type'];
		$this->opponent_player_id=$row['Opponent_idPlayer'];
		$this->team_id=$row['Team_idTeam'];
		$this->turn=$row['Turn'];
		$this->misc=$row['Divers'];

        $result=mysql_query("SELECT Name FROM bbos_action_type WHERE idAction_type=$this->action_type_id");
        $row = mysql_fetch_assoc($result);
        $this->action_type_name=$row['Name'];
    }

    public static function getActionsFromMatch($id)
    {
        $actions=array();
        $request="SELECT idAction FROM bbos_action WHERE Match_idMatch=$id";
        $result=mysql_query($request);
        while ($row = mysql_fetch_assoc($result))
        {
            $actions[]=new Action($row['idAction']);
        }
        return $actions;
    }
}

?>
