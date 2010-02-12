<?php

include('lib/nusoap.php');

$serveur = new soap_server;

// Initialise le support WSDL
$serveur->configureWSDL('Webbos', 'http://Webbos/Webbos');

/* Declare array of String*/
$serveur->wsdl->addComplexType('StringArray','complexType','struct','sequence','',
   array(
      'String'   => array('name' => 'String' , 'type'=> 'xsd:string', 'minOccurs' => "0", 'maxOccurs'=>'unbounded')
   )
);

/* Declare Player type */
$serveur->wsdl->addComplexType('PlayerType','complexType','struct','all','',
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

/* Declare player type array*/
$serveur->wsdl->addComplexType('PlayerTypeArray','complexType','struct','sequence', '',
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
	'PlayerNumber' => array('name'=>'PlayerNumber','type'=>'xsd:int'),
	'TeamId' => array('name'=>'TeamId','type'=>'xsd:int'),
	'OpponentPlayerId' => array('name'=>'OpponentPlayerId','type'=>'xsd:int'),
	'OpponentPlayerNumber' => array('name'=>'OpponentPlayerNumber','type'=>'xsd:int'),
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
	'Round' => array('name'=>'Round','type'=>'xsd:int'),
	'MyScore' => array('name'=>'MyScore','type'=>'xsd:int'),
	'OpponentScore' => array('name'=>'OpponentScore','type'=>'xsd:int'),
	'WinnerId' => array('name'=>'WinnerId','type'=>'xsd:int'),
	'ExtraTime' => array('name'=>'ExtraTime','type'=>'xsd:boolean'),
	'Public' => array('name'=>'Public','type'=>'xsd:int'),
	'MyFAME' => array('name'=>'MyFAME','type'=>'xsd:int'),
	'OpponentFAME' => array('name'=>'OpponentFAME','type'=>'xsd:int'),
	'MyWinnings' => array('name'=>'MyWinnings','type'=>'xsd:int'),
	'OpponentWinnings' => array('name'=>'OpponentWinnings','type'=>'xsd:int'),
	'Actions' => array('name'=>'Actions','type'=>'tns:ActionArray'),
	'ChallengerId' => array('name'=>'ChallengerId','type'=>'xsd:int')
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
	'Retired' => array('name'=>'Retired','type'=>'xsd:boolean'),
	'Dead' => array('name'=>'Dead','type'=>'xsd:boolean'),
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
	'Matches' => array('name'=>'Matches','type'=>'tns:MatchArray'),
	'Coach' => array('name'=>'Coach','type'=>'xsd:string')
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
  
 $serveur->register('soapTest',
		array(),      // input parameters
   		array('return' => 'xsd:int'),    // output parameters
		$webserver.'/service',          // namespace (espace de nommage unique)
		$webserver.'/service#soapTest', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'Return 1 if soap is OK'         // documentation
);


 $serveur->register('retirePlayer',
		array('id' => 'xsd:int'),      // input parameters
   		array('return' => 'xsd:int'),    // output parameters
		$webserver.'/service',          // namespace (espace de nommage unique)
		$webserver.'/service#retirePlayer', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'retire player'         // documentation
);

$serveur->register('removeTeam',
		array('id' => 'xsd:int'),      // input parameters
   		array('return' => 'xsd:int'),    // output parameters
		$webserver.'/service',          // namespace (espace de nommage unique)
		$webserver.'/service#removeTeam', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'retire player'         // documentation
);

$serveur->register('updateTeam',
		array('team' => 'tns:Team'),      // input parameters
   		array('return' => 'xsd:int'),    // output parameters
		$webserver.'/service',          // namespace (espace de nommage unique)
		$webserver.'/service#updateTeam', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'update team'         // documentation
);

$serveur->register('addNewPlayer',
		array('team_id' => 'xsd:int', 'player' => 'tns:Player'),      // input parameters
   		array('return' => 'xsd:int'),    // output parameters
		$webserver.'/service',          // namespace (espace de nommage unique)
		$webserver.'/service#addNewPlayer', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'add new player'         // documentation
);

$serveur->register('getTeam',
		array('team_id' => 'xsd:int'),      // input parameters
   		array('return' => 'tns:Team'),    // output parameters
		$webserver.'/service',          // namespace (espace de nommage unique)
		$webserver.'/service#getTeam', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'return a team'         // documentation
);

$serveur->register('getTeamsForChallenge',
		array('leagueId' => 'xsd:int'),      // input parameters
   		array('return' => 'tns:TeamArray'),    // output parameters
		$webserver.'/service',          // namespace (espace de nommage unique)
		$webserver.'/service#getTeamsForChallenge', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'get challengeable teams'         // documentation
);

$serveur->register('newChallenge',
		array('challengerId' => 'xsd:int','opponentId' => 'xsd:int','leagueId' => 'xsd:int'),      // input parameters
   		array('return' => 'xsd:int'),    // output parameters
		$webserver.'/service',          // namespace (espace de nommage unique)
		$webserver.'/service#newChallenge', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'create new challenge'         // documentation
);

$serveur->register('cancelMatch',
		array('matchId' => 'xsd:int'),      // input parameters
   		array('return' => 'xsd:int'),    // output parameters
		$webserver.'/service',          // namespace (espace de nommage unique)
		$webserver.'/service#cancelMatch', // soapaction (fonction)
	   'rpc',                              // style
	   'literal',                          // use
	   'cancel match'         // documentation
);
include('soap_test/roster.php');
include('soap_test/team.php');
include('soap_test/soap.php');
include('soap_test/player.php');

$serveur->service($HTTP_RAW_POST_DATA);
exit();
?>
