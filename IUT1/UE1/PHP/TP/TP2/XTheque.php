<?php
    abstract class XTheque {
        protected $m_nom;
        protected $m_adresse;

        public function XTheque($nom, $adresse) {
            $this->m_nom = $nom;
            $thos->m_adresse = $adresse;
        }

        public function getNom() { return $this->m_nom; }
        public function getAdresse() { return $this->m_adresse; }

        abstract protected function enregistrer($oeuvre);
        abstract protected function supprimer($oeuvre);
        abstract protected function inscrire(&$utilisateur);
        abstract protected function radier($utilisateur);
        abstract protected function preter($utilisateur, $oeuvre);
        abstract protected function recuperer($pret);
    }

    class Bibliotheque extends XTheque {
        private $m_livres = array();
        private $m_adherents = array();
        private $m_prets = array();

        public static $m_numeroLivre = 0;
        public static $m_numeroAdherent = 0;
        public static $m_numeroPret = 0;

        public function enregistrer($livre) {
            Bibliotheque::$m_numeroLivre++;
            $this->m_livres[Bibliotheque::$m_numeroLivre] = $livre;
            //$this->m_livres[Bibliotheque::$m_numeroLivre]->setNumLivre(Bibliotheque::$m_numeroLivre);
        }
        
        public function supprimer($livre) {
            unset($this->m_livres[$livre]);
        }

        public function inscrire(&$utilisateur) {
            $this->m_numeroAdherent++;
            $utilisateur = $this->m_numeroAdherent;
            $m_adherents[$this->m_numeroAdherent] = $utilisateur;
        }

        public function radier($utilisateur) {
            unset($m_adherents[$utilisateur]);
        }

        public function preter($utilisateur, $livre) {
            //print_r($this->m_prets);
            Outils::$date++;
            $this->m_numeroPret++;
            //$pret = new Pret($this->m_numeroPret, $livre->getNumLivre(), $utilisateur, Outil::$date, Outil::$date + 15);
            $pret = new Pret($this->m_numeroPret, 12, $utilisateur, new Date(date('D')), new Date(date('D') + 15));
            $this->m_prets[$this->m_numeroPret] = $pret;
            //print_r($this->m_prets);
        }

        public function recuperer($pret) {
            $this->m_prets[$pret]->setDateRendu(new Date(date('D')));
            $this->m_prets[$pret]->setDateARendre(0);
            //print_r($this->m_prets);
        }
    }
?>

<?php
    // Test
    require("Date.php");
    require("Oeuvre.php");
    require("Pret.php");
    $bib = new Bibliotheque("IUT", "412 Avenue Gaston Berger");
    $livre = new Oeuvre("Le Seigneur des Anneaux", "Tolkien", 1954, 42);
    echo Bibliotheque::$m_numeroLivre;
    $bib->enregistrer($livre);
    echo Bibliotheque::$m_numeroLivre;
    $bib->preter(12, $livre);
    $bib->recuperer(1);
?>
