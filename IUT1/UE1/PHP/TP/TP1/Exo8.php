<?php
    function comptageDynamique($str) {
        /*
        $count = array();
        for($i = 0; $i < strlen($str); $i++) {
            if(!array_key_exists($str[$i],$count)) {
                $count[$str[$i]] = 0;
            }
            $count[$str[$i]]++;
        }
        print_r($count);
        //*/
        $count = count_chars($str,1);
        foreach($count as $char => $num) { 
            echo chr($char)." apparait $num fois.\n";
        }
    }

    $str = trim(fgets(STDIN));
    comptageDynamique($str);
