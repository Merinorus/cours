/**
 *
 * @file   tp_05.cxx
 *
 * @author Alain DRILLON
 *
 * @date   03/12/2012
 *
 * @brief  TP 05 C++
 *
 **/
#include <iostream>
#include <iomanip>
#include <vector>

using namespace std;

namespace
{
	// Couleurs
	void ClearScreen() { cout << "\033[H\033[2J"; }
	const string KReset   ("0");
	const string KNoir    ("30");
	const string KRouge   ("31");
	const string KVert    ("32");
	const string KJaune   ("33");
	const string KBleu    ("34");
	const string KMAgenta ("35");
	const string KCyan    ("36");
		
	void Couleur(const string & coul)
	{
		cout << "\033[" << coul.c_str() <<"m";
	}

	// Puissance 4

	typedef vector<char> CVLigne;
	typedef CVLigne::const_iterator ConstIt_Ligne;
	typedef vector<CVLigne> CVMatrice;
	typedef CVMatrice::const_iterator ConstIt_Matrice;

	// Valeurs des cases
	const char KVide = '.';
	const char KJoueur1 = 'X';
	const char KJoueur2 = 'O';
	
	// État du jeu
	enum Etat { EtatNormal, EtatVicJoueur1, EtatVicJoueur2, EtatEgalite };

	void AfficheLigne(const CVLigne Li)
	{
		for(ConstIt_Ligne it = Li.begin(); it < Li.end(); it++)
		{
			// Couleurs
			switch(*it)
			{
				case KJoueur1:
					Couleur(KRouge);
					break;
				case KJoueur2:
					Couleur(KBleu);
					break;
				default:
					Couleur(KReset);
					break;
			}

			cout << setw(3) << *it;
		}
		Couleur(KReset);
		cout << endl;
	} // AfficheLigne

	void AfficheMatrice(const CVMatrice &Mat)
	{
		ClearScreen();
		Couleur(KReset);
		for(ConstIt_Matrice it = Mat.begin(); it < Mat.end(); it++)
		{
			AfficheLigne(*it);
		}
	} // AfficheMatrice

	void AffichTirets()
	{
		for(unsigned int i = 0; i < 7; i++)
		{
			cout << setw(3) << "---";
		}
		cout << endl;
	} // AffichTirets

	void AffichePuissance4(const CVMatrice &Mat)
	{
		AfficheMatrice(Mat);
		AffichTirets();

		// Nom de colonnes
		for(char col = 'A'; col <= 'G'; col++)
		{
			cout << setw(3) << col;
		}

		cout << endl;
	} // AffichePuissance4

	void InitMat(CVMatrice &Mat)
	{
		Mat = CVMatrice(7,CVLigne(7,KVide));
	} // InitMat

	void PositionneJeton(CVMatrice &Mat, const unsigned NumCol, unsigned &NumLi, const bool Joueur1)
	{
		char Jeton = (Joueur1) ? KJoueur1 : KJoueur2;
		for(NumLi = 6; NumLi >= 0 && Mat[NumLi][NumCol] != KVide; NumLi--);

		Mat[NumLi][NumCol] = Jeton;
	} // PositionneJeton

	bool TestVictoireColonne(const CVMatrice &Mat, const unsigned NumLi, const unsigned NumCol, bool &Joueur)
	{
		char PremiereCase = Mat[NumLi][NumCol];
		if(PremiereCase == KVide) return false;

		for(unsigned Case = NumLi; Case < NumLi + 4; Case++)
		{
			if(Mat[Case][NumCol] != PremiereCase)
				return false;

			Joueur = (Mat[Case][NumCol] == KJoueur1) ? true : false;
		}

		return true;
	} // TestVictoireColonne

	bool TestVictoireLigne(const CVMatrice &Mat, const unsigned NumLi, const unsigned NumCol, bool &Joueur)
	{
		char PremiereCase = Mat[NumLi][NumCol];
		if(PremiereCase == KVide) return false;

		for(unsigned Case = NumCol; Case < NumCol + 4; Case++)
		{
			if(Mat[NumLi][Case] != PremiereCase)
				return false;

			Joueur = (Mat[NumLi][Case] == KJoueur1) ? true : false;
		}

		return true;
	} // TestVictoireLigne

	void ppal()
	{
		// Initialisation
		CVMatrice Grille;
		InitMat(Grille);
		AffichePuissance4(Grille);

		// Boucle principale
		Etat EtatJeu = EtatNormal;
		bool Joueur2 = false; // true = 2, false = 1
		unsigned NombreCoups = 0;
		while(EtatJeu == EtatNormal)
		{
			NombreCoups++;

			/* Placement d'un jeton
			 * Joueur2+1 permet d'afficher 1 pour false et 2 pour true
			 *
			 * !Joueur2 car PositionneJeton prend comme paramètre
			 * une valeur symbolisant Joueur1 (donc !Joueur2).
			 */
			cout << "Joueur " << (Joueur2+1) << "> ";
			unsigned Ligne;
			char Colonne; cin >> Colonne; Colonne -= 65;
			if(Colonne < 0 || Colonne > 7) continue;
				PositionneJeton(Grille, Colonne, Ligne, !Joueur2);

			// Tests de victoire
			bool Vic, JoueurVic;
			for(unsigned Col = 0; Col < 7 && !Vic; Col++)
				for(unsigned Li = 0; Li < 7 && !Vic; Li++)
				{
					if(Li < 4)
					{
						Vic = TestVictoireColonne(Grille, Li, Col, JoueurVic);
						if(Vic) break;
					}

					if(Col < 4)
					{
						Vic = TestVictoireLigne(Grille, Li, Col, JoueurVic);
						if(Vic) break;
					}
				}

			if(Vic)
				EtatJeu = (JoueurVic) ? EtatVicJoueur1 : EtatVicJoueur2;
			else if(!Vic && NombreCoups == 49)
				EtatJeu = EtatEgalite;

			Joueur2 = !Joueur2; // Changement de joueur
			AffichePuissance4(Grille);
		}

		switch(EtatJeu)
		{
			case EtatVicJoueur1:
				Couleur(KRouge);
				cout << "Joueur 1 ";
				Couleur(KReset);
				cout << "a gagné !" << endl;
				break;
			case EtatVicJoueur2:
				Couleur(KCyan);
				cout << "Joueur 2 ";
				Couleur(KReset);
				cout << "a gagné !" << endl;
				break;
			case EtatEgalite:
				Couleur(KJaune);
				cout << "Égalité ! " << endl;
				break;
			default:
				cout << "LOLWUT" << endl;
				break;
		}
		Couleur(KReset);
	} // ppal
} // namespace

int main ()
{
	ppal();
    return 0;
} // main()
