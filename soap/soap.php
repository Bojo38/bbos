<?php

function isValidUser($user,$pass)
{
	include('config_inc.php');
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	$request='select COUNT(*) from coach where nickname=\''.$user.'\' and PWD=\''.$pass.'\'';
	$result=mysql_query($request);
	$donnees = mysql_fetch_array($result);
	if ($donnees[0]==1)
	{
		$resultat=1;
	}
	mysql_close();
	return $resultat;
}

function soapTest()
{
	return 1;
}

?>