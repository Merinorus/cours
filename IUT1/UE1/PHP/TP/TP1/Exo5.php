<?php
    function ExpandTab($str, $szTab) {
        $expand="";
        for($RgReel = 0; $RgReel < strlen($str); $RgReel++) {
            if($str[$RgReel] == "\t") {
                $expand.=str_repeat(' ',$szTab);
            }
            else {
                $expand.=$str[$RgReel];
            }
        }

        return $expand;
    }

    while(true) {
        $line = trim(fgets(STDIN));
        if($line == "") break;
        echo ExpandTab($line,4);
    }
