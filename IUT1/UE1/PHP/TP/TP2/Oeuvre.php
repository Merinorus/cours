<?php
    class Oeuvre {
        protected $m_titre;
        protected $m_auteur;

        public function Oeuvre($titre, $auteur) {
            $this->m_titre = $titre;
            $this->m_auteur = $auteur;
        }

        public function getTitre() {
            return $this->m_titre;
        }

        public function getAuteur() {
            return $this->m_auteur;
        }
    }

    class Livre extends Oeuvre {
        protected $m_datePublication;
        protected $m_numeroEdition;
        protected $m_numeroLivre;

        public function Livre($titre, $auteur, $datePub, $numEdit, $numLivre = 0) {
            parent::Oeuvre($titre, $auteur);
            $this->m_datePublication = $datePub;
            $this->m_numeroEdition = $numEdit;
            $this->m_numeroLivre = $numLivre;
        }

        public function getDatePub() {
            return $this->m_datePublication;
        }

        public function getNumEdit() {
            return $this->m_numeroEdition;
        }

        public function getNumLivre() {
            return $this->m_numeroLivre;
        }

        public function setNumLivre($num) {
            $this->m_numeroLivre = $num;
        }
    }
?>

<?php
    /* Test
    $oeuvre = new Oeuvre("Le Seigneur des Anneaux", "Tolkien");
    echo $oeuvre->getTitre()."\n";

    $livre = new Livre("Le Seigneur des Anneaux", "Tolkien", 1954, 42, 1337);
    echo $livre->getNumLivre().",".$livre->getNumEdit()." : ".$livre->getTitre()." (".$livre->getAuteur().", ".$livre->getDatePub().")";
    //*/
?>
