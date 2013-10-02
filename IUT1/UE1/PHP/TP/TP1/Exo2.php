<?php
    function lectureChainesDansFichier() {
        $file = fopen("Exo2.php", 'r');
        $str=null;
        while(!feof($file)) {
            $str = $str.fgetc($file);
        }
            echo $str;
    }

    lectureChainesDansFichier();
?>
