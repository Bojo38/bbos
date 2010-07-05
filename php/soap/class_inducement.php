<?php

class Inducement
{
    public $id=0;
    public $match_id=0;
    public $player_type_id=0;
    public $inducement_type_id=0;
    public $cost=0;
    public $team_id=0;
    public $card_id=0;
    public $misc='';

    function __construct($id)
    {
        $result=mysql_query("SELECT * FROM bbos_match_has_inducement WHERE idMatch_has_inducement=$id");
        $row = mysql_fetch_assoc($result);

		$this->id=$row['idMatch_has_inducement'];
		$this->match_id=$row['Match_idMatch'];
        $this->team_id=$row['Team_idTeam'];
		$this->inducement_type_id=$row['Inducement_type_idInducementType'];
		$this->cost=$row['Cost'];
		$this->card_id=$row['Card_idCard'];
		$this->player_type_id=$row['Player_type_idPlayer_type'];
		$this->misc=$row['Data'];
    }

    public static function getInducementsFromMatch($id)
    {
        $inducements=array();
        $request="SELECT idMatch_has_inducement FROM bbos_match_has_inducement WHERE Match_idMatch=$id";
        $result=mysql_query($request);
        while ($row = mysql_fetch_assoc($result))
        {
            $inducements[]=new Inducement($row['idMatch_has_inducement']);
        }
        return $inducements;
    }
}

?>
