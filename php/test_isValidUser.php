<?php

	include('config_inc.php');
	$user='Lord Bojo';
	$pass='lancie';
	
	$resultat=0;
	mysql_connect($db_server,$db_login,$db_pass);
	mysql_select_db($db_name);
	$request='select COUNT(*) from bbos_coach';
	$result=mysql_query($request);
	$donnees = mysql_fetch_array($result);
	echo "$donnees[0]";
	if ($donnees[0]==1)
	{
		$resultat=1;
	}
	mysql_close();



?>