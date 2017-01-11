<?php
	/* ETAPE 0 : TINCLUDE DE FONCTIONS ET PARAMETRAGE */
	$GLOBALS['json']=1;
	include('inc/erreurs.inc');


	/* ETAPE 1 : CONNEXION A LA BASE DE DONNEES */
	include('inc/db.inc');

	/*$unique = false;
	if(!empty($_GET['unique'])){
		if($_GET['unique'] == 1){
			$unique = true;
		}
	}*/

	/* ETAPE 2 : RECUPERATION DE LA LISTE DES JEUX */
	// 2.1. On exécute la requête
	try{	
		//if($unique){
			$requete="SELECT distinct jeu FROM scores";
		/*}else{
			$requete="SELECT jeu FROM scores";
		}*/
		
		$stm= $bdd->prepare($requete);
		$stm->execute();	
	}catch(Exception $e){
		RetournerErreur(2003);
	}

	// 2.2. On vérifie si on a trouvé au moins un jeu
	if($row = $stm->fetch()){
		$chaine_jeux = '{"nom_jeu": "' . $row["jeu"] . '"}';
	}else{
		RetournerErreur(300);
	}

	// 2.3. On construit le fichier JSON des jeux
	while ($row = $stm->fetch()) {
		$chaine_jeux .= ', {"nom_jeu": "' . $row["jeu"] . '"}';
	}


	/* ETAPE 3 : SI ON EST ARRIVE JUSQU'ICI, C'EST QUE TOUT EST CORRECT */
	$resultat='{ "code": 0, "jeux": [' . $chaine_jeux . '] }';
	echo $resultat;


	/* Valeurs de retour
	 * 00 : OK
	 * 300 : Aucun jeu trouvé
	 * 1000 : problème de connexion à la DB
	 * 20XX : autre problème
	 */
?>
