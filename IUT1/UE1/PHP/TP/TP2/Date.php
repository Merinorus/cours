<?php
    class Date {
        private $m_date;
        
        public function Date($date = 0) {
            $this->m_date = $date;
        }

        public static function calculerDifference($date1, $date2) {
            return $date1->getDate() - $date2->getDate();
        }

        public function getDate() {
            return $this->m_date;
        }

        public function ajouterJours($jours = 1) {
            $this->m_date+=$jours;
            return $this;
        }
    }

?>

<?php
    // Test
    $date1 = new Date(16);
    $date2 = new Date(9);
    echo Date::calculerDifference($date1, $date2);
?>
