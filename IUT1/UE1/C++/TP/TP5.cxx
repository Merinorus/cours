/**
 *
 * @file   tp_05.cxx
 *
 * @author Alain DRILLON
 *
 * @date   14/12/2012
 *
 * @brief  TP 05 C++
 *
 **/
#include <iostream>
#include <iomanip>
#include <vector>
#include <math.h>

using namespace std;

namespace
{
	typedef vector<unsigned> CVUInt;
	typedef vector<CVUInt> CVVUInt;

	typedef CVUInt::size_type IndV_t;
	typedef CVVUInt::size_type IndVV_t;

	void LigneTrianglePascalV1(CVVUInt &Triangle, const IndVV_t Ligne)
	{
		Triangle[Ligne] = CVUInt(Ligne + 1);
		for(IndV_t colonne = 0; colonne < Ligne+1; colonne++)
		{
			if(colonne == 0 || colonne == Ligne) Triangle[Ligne][colonne] = 1;
			else
			{
				Triangle[Ligne][colonne] = Triangle[Ligne-1][colonne-1] + Triangle[Ligne-1][colonne];
			}
		}
	} // LigneTrianglePascalV1

	void GenerTrianglePascalV1(CVVUInt &Triangle, const IndVV_t Lignes)
	{
		// Initialiser
		Triangle = CVVUInt(Lignes);
		Triangle[0] = CVUInt(1);
		Triangle[0][0] = 1;

		// Remplir
		for(IndVV_t ligne = 1; ligne < Lignes; ligne++)
		{
			LigneTrianglePascalV1(Triangle, ligne);
		}
	} // GenerTrianglePascalV1

	void AffichLignePascal(const CVVUInt &Triangle, const IndVV_t Ligne)
	{
		cout << "\033[01;33m" << setw(log(Triangle.size()) + 2) << Ligne + 1 << "\x1B[0m";

		unsigned DerniereLigne = Triangle.size() - 1, ColonneMilieu = Triangle[DerniereLigne].size() / 2;
		unsigned TailleCol = log(Triangle[DerniereLigne][ColonneMilieu]) + 2;
		for(IndV_t colonne = 0; colonne < Triangle[Ligne].size(); colonne++)
		{
			cout << setw(TailleCol) << Triangle[Ligne][colonne];
		}
	} // AffichLignePascal

	void PascalV1()
	{
		// Lecture du nombre de lignes
		IndVV_t NLignes;
		cout << "Nombre de ligne : ";
		cin >> NLignes;

		// Générer le triangle
		CVVUInt Triangle;
		GenerTrianglePascalV1(Triangle, NLignes);

		// Afficher le triangle
		for(IndVV_t ligne = 0; ligne < Triangle.size(); ligne++)
		{
			AffichLignePascal(Triangle, ligne);
			cout << endl;
		}

		// Afficher une certaine ligne
		IndVV_t Ligne = 1;
		while(Ligne != 0)
		{
			cout << "Afficher la ligne (0 pour quitter) : ";
			cin >> Ligne;
			if(Ligne != 0)
			{
				if(Ligne > Triangle.size() || Ligne <= 0)
				{
					cerr << "Ligne invalide.";
				}
				else
				{
					AffichLignePascal(Triangle, Ligne-1);
				}
			}
			else
			{
				break;
			}
			cout << endl;
		}
	} // PascalV1

	void GenerPrimesInfEqN(CVUInt &LesPremiers, unsigned N)
	{
		LesPremiers.clear();
		LesPremiers.reserve((N+1) / 2);

		LesPremiers.push_back(2);
		for(unsigned i = 3; i <= N; i += 2) LesPremiers.push_back(i);

		for(IndV_t i = 1; LesPremiers[i] * LesPremiers[i] <= N; i++)
		{
			if(LesPremiers[i] == 0) continue;

			// Supprimer les multiples de LesPremiers[i]

			const IndV_t KIncr = LesPremiers[i];
			const IndV_t KSz = LesPremiers.size();
			for (IndV_t j = i + KIncr; j < KSz; j += KIncr)
			{
				LesPremiers[j] = 0;
			}
		}

		// Compactage
		unsigned NbPremiers = 0;
		for(IndV_t i = 0; i < LesPremiers.size(); i++)
		{
			if(LesPremiers[i] > 0)
			{
				LesPremiers[NbPremiers++] = LesPremiers[i];
			}
		}

		LesPremiers.resize(NbPremiers);
	} // GenerPrimesInfEqN

	void AffichUlam(const CVVUInt &Grille)
	{
		unsigned TailleCol = log(Grille[Grille.size() - 1][Grille.size() - 1]);
		for(IndVV_t ligne = 0; ligne < Grille.size(); ligne++)
		{
			for(IndV_t colonne = 0; colonne < Grille[ligne].size(); colonne++)
			{
				if(Grille[ligne][colonne] == 0)
				{
					cout << setw(TailleCol) << " ";
				}
				else
				{
					cout << setw(TailleCol) << Grille[ligne][colonne];
				}
			}
			cout << endl;
		}
	} // AffichUlam

	unsigned FindDichot(const unsigned Val, const CVUInt Tableau, const unsigned Beg, const unsigned End)
	{
		if(End <= Beg) return End;

		unsigned Debut = Beg, Fin = End;
		while(End != Debut)
		{
			unsigned Milieu = (Debut + Fin) / 2;
			if(Val > Tableau[Milieu]) Debut = Milieu + 1;
			else Fin = Milieu;
		}

		return Debut;
	} // FindDichot

	enum Direction {DirUp, DirLeft, DirDown, DirRight};
	CVVUInt UlamSpirale(CVVUInt Grille)
	{
		unsigned KNbElem = Grille.size() * Grille.size();
		unsigned KCentre = Grille.size() / 2;
		Grille[KCentre][KCentre] = 1;
		for(unsigned N = 1, Li = KCentre, Col = KCentre, NbParBoucle = 2; N < KNbElem; )
		{
			++Li; ++Col;
			for(unsigned i = 0; i < NbParBoucle; ++i)
				Grille[--Li][Col] = ++N;

			for(unsigned i = 0; i < NbParBoucle; ++i)
				Grille[Li][--Col] = ++N;

			for(unsigned i = 0; i < NbParBoucle; ++i)
				Grille[++Li][Col] = ++N;

			for(unsigned i = 0; i < NbParBoucle; ++i)
				Grille[Li][++Col] = ++N;

			NbParBoucle += 2;
		}

		return Grille;
	} // UlamSpirale

	void UlamV1()
	{
		// Saisie de la taille de la grille
		unsigned KSz;
		cout << "Taille de la grille : ";
		cin >> KSz;

		// Initialisation
		CVVUInt Grille(KSz, CVUInt(KSz, 2));
		Grille = UlamSpirale(Grille);

		// Nombres premiers
		CVUInt Premiers;
		GenerPrimesInfEqN(Premiers, KSz * KSz);
		for(unsigned i = 0;i < KSz; ++i)
		{
			for(unsigned j = 0;j < KSz; ++j)
			{
				if(Premiers.size() == FindDichot(Grille[i][j], Premiers, 0u, Premiers.size()))
				{
					Grille[i][j] = 0;
				}
			}
		}

		// Affichage
		AffichUlam(Grille);
	} // UlamV1

} // namespace

int main ()
{
	UlamV1();
    return 0;
} // main()
