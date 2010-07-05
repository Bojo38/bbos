<?php

function isValidUser($user,$pass)
{
	include('config_inc.php');
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);

	$resultat=Coach::isValid($user,$pass);

	mysql_close();
	return $resultat;
}

?>
