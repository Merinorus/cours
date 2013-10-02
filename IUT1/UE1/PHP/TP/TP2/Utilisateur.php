<?php
    class Utilisateur {
        private $m_nom;
        private $m_prenom;
        private $m_numeroAdherent;

        public function Utilisateur($nom, $prenom, $numero = 0) {
            $this->m_nom = $nom;
            $this->m_prenom = $prenom;
            $this->m_numeroAdherent = $numero;
        }

        public function getNom() {
            return $this->m_nom;
        }

        public function getPrenom() {
            return $this->m_prenom;
        }

        public function getNumeroAdherent() {
            return $this->m_numeroAdherent;
        }

        public function setNumeroAdherent($num = 0) {
            $this->m_numeroAdherent = $num;
        }
    }
?>

<?php
    // Test
    $user = new Utilisateur("DORMONT", "Florian", 42);
    echo $user->getNumeroAdherent()." : ".$user->getNom()." ".$user->getPrenom()."\n";
    $user->setNumeroAdherent();
    echo $user->getNumeroAdherent()." : ".$user->getNom()." ".$user->getPrenom()."\n";
?>
