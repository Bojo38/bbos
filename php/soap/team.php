<?php

/**
 * @todo Update function to use classes
 * @param <type> $t
 * @return <type>
 */
function getSoapTeam($t)
{
    $player=array();
    $matches=array();
    $players=array();

    foreach ($t->players as $p)
    {
        $injury = array();
        foreach ($p->injuries as $i)
        {
            $injury[] = $i->name;
        }
        $injuries=array('String'=> $injury);

        $competence = array();
        foreach ($p->competences as $c)
        {
            $competence[] = $c->name;
        }
        $competences=array('String'=> $competence);

        $player[]=array(
                    'Id' => $p->id,
                    'Name' => $p->name,
                    'Ranking' => $p->ranking,
                    'TypeId' => $p->player_type_id,
                    'Completion' => $p->completions,
                    'Interception' => $p->interceptions,
                    'Touchdowns' => $p->touchdowns,
                    'Casualties' => $p->casualties,
                    'MVP' => $p->mvp,
                    'Persistant' => $p->persistant,
                    'MissNextGame' => $p->miss_next_game,
                    'Competences' => $competences,
                    'Number' => $p->number,
                    'Injuries' => $injuries,
                    'Retired' => $p->retired,
                    'Dead' => $p->dead,
        );
        $players=array('Player'=>$player);
    }

    $matchs	=array();

    foreach($t->matches as $m)
    {
        $match=array();
        $opponentId=0;
        $myScore=0;
        $opponentScore=0;
        $myWinnings=0;
        $opponentWinnings=0;
        $myFAME=0;
        $opponentFAME=0;

        if ($t->id==$m->team1_id)
        {
            $opponentId=$m->team2_id;
            $myScore=$m->score1;
            $opponentScore=$m->score2;
            $myWinnings=$m->winnings1;
            $opponentWinnings=$m->winnings2;
            $myFAME=$m->FAME1;
            $opponentFAME=$m->FAME2;
        }
        else
        {
            $opponentId=$m->team1_id;
            $myScore=$m->score2;
            $opponentScore=$m->score1;
            $myWinnings=$m->winnings2;
            $opponentWinnings=$m->winnings1;
            $myFAME=$m->FAME2;
            $opponentFAME=$m->FAME1;
        }

        $tmp_team=new Team($opponentId);
        $opponentName=$tmp_team->name;

        $actions=array();
        foreach ($m->actions as $a)
        {
            $my_player=new Player($a->player_id);
            $opp_player=new Player($a->opponent_player_id);

            $action=array(
                        'Id' => $a->id,
                        'ActionType' => $a->action_type_id,
                        'Data' => $a->misc,
                        'TeamId' => $a->team_id,
                        'PlayerNumber' => $my_player->number,
                        'OpponentPlayerNumber' => $opp_player->number,
                        'PlayerId' => $my_player->id,
                        'OpponentPlayerId' => $opp_player->id,
                        'Turn' => $a->turn,
                        'ActionName' => $a->action_type_name,
                        'PlayerName' => $my_player->name,
                        'OpponentPlayerName' =>$opp_player->name
            );
            $actiontab[]=$action;
            $actions=array('Action'=>$actiontab);
        }

        $match=array(
                    'Id' => $m->id,
                    'LeagueId' => $m->league_id,
                    'State' => $m->state,
                    'OpponentId' => $opponentId,
                    'MyScore' => $myScore,
                    'OpponentScore' => $opponentScore,
                    'WinnerId' => $m->winner_id,
                    'Round' => $m->round,
                    'Public' => $m->audience,
                    'ExtraTime' => $m->extra_time,
                    'MyFAME' => $myFAME,
                    'OpponentFAME' => $opponentFAME,
                    'MyWinnings' => $myWinnings,
                    'OpponentWinnings' => $opponentWinnings,
                    'OpponentName' => $opponentName,
                    'Date' => $m->date,
                    'Actions' => $actions,
                    'ChallengerId' => $m->challenger_id,
                    'Data' => $m->misc
        );
        $matchs[]=$match;
    }
    $matches=array('Match'=>$matchs);

    $team=array(
        'Id' => $t->id,
        'Name' => $t->name,
        'Reroll' => $t->reroll,
        'Apothecary' => $t->apothecary,
        'Treasury' => $t->treasury,
        'Cheerleaders' => $t->cheerleaders,
        'Assists' => $t->assists,
        'Popularity' => $t->fan_factor,
        'RaceId' => $t->team_type_id,
        'Players' => $players,
        'LeagueId' => $t->league_id,
        'Matches' => $matches,
        'Coach' => $t->coach_id);

    return $team;
}


/*
 *
 *  Update the teams, not the matches of the team */
function updateTeam($team)
{
    include('config_inc.php');
    $resultat=0;
    mysql_connect($db_server,$db_login,$db_pass);
    mysql_select_db($db_name);


    $team_id=$team['Id'];

    $request_team='UPDATE bbos_team SET Popularity = '.$team['Popularity'].', Assists='.$team['Assists'].', Cheerleaders=';
    $request_team=$request_team.$team['Cheerleaders'].', Apothecary='.$team['Apothecary'].', Treasury='.$team['Treasury'];
    $request_team=$request_team.', Reroll='.$team['Reroll'].' WHERE bbos_team.idTeam ='.$team_id;

    $result=mysql_query($request_team);
    if (!$result)
    {
        die('Requ�te invalide : ' . mysql_error());
    }

    /*$players=$team['Players'];
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
            die('Requ�te invalide : ' . mysql_error());
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
                die('Requ�te invalide : ' . mysql_error());
            }
            if (mysql_num_rows($injury_result)>0)
            {
                $data_injuries = mysql_fetch_array($injury_result);
                $player_has_injuries_select='SELECT * FROM player_has_injuries WHERE (Injuries_idInjuries='.$data_injuries[0].') AND (Player_idPlayer='.$player['Id'].')';
                $player_has_injuries_result=mysql_request($player_has_injuries_select);
                if (!$player_has_injuries_result)
                {
                    die('Requ�te invalide : ' . mysql_error());
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
                die('Requ�te invalide : ' . mysql_error());
            }
            if (mysql_num_rows($competence_result)>0)
            {
                $data_competence = mysql_fetch_array($competence_result);
                $player_has_competence_select='SELECT * FROM player_has_competence WHERE (Competence_idCompetence='.$data_competence[0].') AND (Player_idPlayer='.$player['Id'].')';
                $player_has_competence_result=mysql_request($player_has_competence_select);
                if (!$player_has_competence_result)
                {
                    die('Requ�te invalide : ' . mysql_error());
                }
                if (mysql_num_rows($player_has_competence_result)==0)
                {
                    $player_has_competence_insert='INSERT INTO player_has_competence (Competence_idCompetence,Player_idPlayer) VALUES('.$data_competence[0].','.$player['Id'].')';
                    $player_has_competence_result=mysql_request($player_has_competence_insert);
                }
            }
        }
    }*/
    mysql_close();
    return $resultat;
}

function addTeam($user,$team)
{
    include('config_inc.php');
    $resultat=0;
    mysql_connect($db_server,$db_login,$db_pass);
    mysql_select_db($db_name);

    $user_id=Coach::getCoachID($user);

    $team_id=Team::create(3,$team['RaceId'],$user_id, $team['Name'],$team['Popularity'],$team['Assists'],$team['Cheerleaders'], $team['Apothecary'], $team['Treasury'], $team['Reroll']);
    $team=new Team($team_id);

    foreach ($team['Players']['Player'] as $p)
    {
        Player::create($team_id,$player['TypeId'], $player['Name'], $player['Number']);
    }

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

    $rteams=Team::getTeamsFromLeague($leagueId);
    foreach ($rteams as $t)
    {
        if ($t->active==1)
        {
            $teams[]=getSoapTeam($t);
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

    $t=new Team($id);
    $t->setActive(0);

    mysql_close();
    return $resultat;
}

function getTeam($id)
{
    include('config_inc.php');
    $resultat = array();

    mysql_connect($db_server,$db_login,$db_pass);
    mysql_select_db($db_name);

    $t=new Team($id);
    $resultat= getSoapTeam($t);
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

    $rteams=Team::getCoachTeams(Coach::getCoachID($user));

    foreach ($rteams as $t)
    {
        if ($t->active==1)
        {
            $teams[]=getSoapTeam($t);
        }
    }

    $resultat= array( 'Team' => $teams);
    mysql_close();
    return $resultat;
}




?>
