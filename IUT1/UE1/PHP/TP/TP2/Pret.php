<?php
    class Outils {
        public static $date = 2;
    }

    class Pret {
        protected $m_idPret;
        protected $m_idLivre;
        protected $m_idAdherent;
        protected $m_dateEmprunt;
        protected $m_dateARendre;
        protected $m_dateRendu;

        public function Pret($idPret, $idLivre, $idAdherent, $dateEmprunt, $dateARendre) {
            $this->m_idPret = $idPret;
            $this->m_idLivre = $idLivre;
            $this->m_idAdherent = $idAdherent;
            $this->m_dateEmprunt = $dateEmprunt;
            $this->m_dateARendre = $dateARendre;
            $this->m_dateRendu = 0;
        }

        public function getIdPret() { return $this->m_idPret; }
        public function getIdLivre() { return $this->m_idLivre; }
        public function getIdAdherent() { return $this->m_idAdherent; }
        public function getDateEmprunt() { return $this->m_dateEmprunt; }
        public function getDateARendre() { return $this->m_dateARendre; }
        public function getDateRendu() { return $this->m_dateRendu; }

        public function setDateARendre($dateARendre) {
            $this->m_dateARendre = $dateARendre;
        }

        public function setDateRendu() {
            $this->m_dateRendu = Outils::$date;
        }
    }

?>

<?php
    /* Test
    $pret = new Pret(0,1,2,3,4);
    $pret->setDateRendu();
    echo $pret->getDateRendu();
    //*/
?>
