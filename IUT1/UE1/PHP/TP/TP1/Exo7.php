<?php
    function remplissageTableau(&$array) {
        while(fscanf(STDIN, "%d",$num)) {
            array_push($array, $num);
        }
    }

    function remplissageEtAffichageTableau(&$array) {
        remplissageTableau($array);
        echo "Tableau non-trié :\n";
        print_r($array);
        echo "Tableau trié : \n";
        sort($array);
        print_r($array);
        echo "Tableau mélangé : \n";
        shuffle($array);
        print_r($array);
    }

    $array = array();
    remplissageEtAffichageTableau($array);

?>
