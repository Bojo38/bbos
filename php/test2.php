<?php


	$resultat = array();
	mysql_connect("mysql5-29","ainpactebbos","lancie");
	mysql_select_db("ainpactebbos");
	$result=mysql_query('SELECT * from team_type;');
	while ($donnees = mysql_fetch_array($result) )
	{
		echo 'Team roster name : '.$donnees[1];
	}

	mysql_close();

?>


