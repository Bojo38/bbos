<?php

class Match
{
    public $id=0;
    public $date='';
    public $league_id=0;
    public $team1_id=0;
    public $team2_id=0;
    public $state=0;
    public $score1=0;
    public $score2=0;
    public $winner_id=0;
    public $round=0;
    public $extra_time=0;
    public $audience=0;
    public $FAME1=0;
    public $FAME2=0;
    public $winnings1=0;
    public $winnings2=0;
    public $challenger_id=0;
    public $data='';

    public $actions=array();
    public $inducements=array();

    function __construct($id)
    {
        $result=mysql_query("SELECT *,DATE_FORMAT(`date`, '%Y %m %d %H %i %s') as newdate FROM bbos_match WHERE idMatch=$id ORDER BY `date` DESC");
        $row = mysql_fetch_assoc($result);

        $this->id=$row['idMatch'];
        $this->date=$row['newdate'];
        $this->league_id=$row['League_idLeague'];
        $this->team1_id=$row['Team_idTeam_1'];
        $this->team2_id=$row['Team_idTeam_2'];
        $this->state=$row['State'];
        $this->score1=$row['Score_1'];
        $this->score2=$row['Score_2'];
        $this->winner_id=$row['Winner_idTeam'];
        $this->round=$row['Round'];
        $this->extra_time=$row['ExtraTime'];
        $this->audience=$row['Public'];
        $this->FAME2=$row['FAME_1'];
        $this->FAME1=$row['FAME_2'];
        $this->winnings1=$row['Winnings_1'];
        $this->winnings2=$row['Winnings_2'];
        $this->challenger_id=$row['challengerId'];
        $this->data=$row['Data'];

        $this->actions=Action::getActionsFromMatch($this->id);
        $this->inducements=Inducement::getInducementsFromMatch($this->id);
    }

    public static function getMatchesFromTeam($id)
    {
        $matches=array();
        $request="SELECT idMatch FROM bbos_match WHERE (Team_idTeam_1=$id) OR (Team_idTeam_2=$id) ORDER BY `date` DESC";
        $result=mysql_query($request);
        if (mysql_num_rows($result) > 0) {
            while ($row = mysql_fetch_assoc($result))
            {
                $matches[]=new Match($row['idMatch']);
            }
        }
        return $matches;
    }
}

?>
