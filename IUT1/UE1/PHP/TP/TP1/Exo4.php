<?php
    function SeparMots($str) {
        /*
        $nbCar=0;
        for($i = 0; $i < strlen($str); $i++) {
            if(!ctype_space($str[$i])) {
                $str[$nbCar++] = $str[$i];
            } elseif($nbCar > 0 && $str[$nbCar - 1] != ' ') {
                $str[$nbCar++] = ' ';
            }
        }
        $str = substr($str, 0, $nbCar);
        //*/

        $str = preg_replace("# +#"," ",$str);
        $str = trim($str);
        return $str;
    }

    while(true) {
        $line = trim(fgets(STDIN));
        if($line == "") break;
        echo SeparMots($line)."\n";
    }
