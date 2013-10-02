<?php
    function lectureChainesLire() {
        $file = fopen("/dev/stdin", 'r');
        while(!feof($file)) {
            $str = trim(fgets($file));
            echo $str;
            echo toLower($str);
            echo strtoupper($str);
        }
    }

    function toLower($str) {
        for($i = 0;$i < strlen($str);$i++) {
            $str[$i] = strtolower($str[$i]);
        }

        return $str;
    }

    lectureChainesLire();
?>
