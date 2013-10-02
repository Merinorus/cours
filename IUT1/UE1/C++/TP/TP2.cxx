/**
 *
 * @file   tp_02.cxx
 *
 * @author Alain DRILLON
 *
 * @date   09/11/2012
 *
 * @brief  TP 02 C++
 *
 **/
#include <iostream>
#include <iomanip>
#include <string>
#include <cctype>

using namespace std;

namespace
{
	bool IsLower(const char Carac)
	{
		return (Carac >= 'a' && Carac <= 'z');
	} // IsLower()

	bool IsUpper(const char Carac)
	{
		return (Carac >= 'A' && Carac <= 'Z');
	} // IsUpper()
	
    void LectureStrings()
    {
		cout << "Lecture de chaîne jusqu'à 'fin'." << endl;

		string ChaineLue;
		while(true)
		{
			cout << "Saisir une chaîne : ";
			cin >> ChaineLue;

			if (ChaineLue == "fin") break;

			cout << "Chaine lue : " << ChaineLue << endl;
		}
    } // LectureStrings()

    string InverserString(string Chaine)
    {
		string ChaineInv;

		for(unsigned int i = Chaine.length(); i >= 0;i--)
		{
			ChaineInv += Chaine[i];
		}

		return ChaineInv;
    } // InverserString()

    void LongueurStrings()
    {
    	cout << "Longueur d'une chaîne \"tableau de caractères\"" << endl << endl;

		string ChaineLue;
		while(true)
		{
			cout << "Tapez une chaîne (\"fin\" pour terminer le programme) : ";
			cin >> ChaineLue;

			if(ChaineLue == "fin") break;
			cout << "Chaine inversée : " << setw(4) << ChaineLue.length() << " - " << InverserString(ChaineLue) << endl;
		}
	} // LongueurString()

	void CharEtCodeASCII()
	{
		cout << "Les minuscules : " << endl;
		char TabMinusc[26];
		for(char minusc = 'a';minusc <= 'z';minusc++)
		{
			TabMinusc[minusc - 'a'] = minusc;
			cout << TabMinusc[minusc - 'a'];
		}
		cout << endl << endl;
		
		cout << "Les chiffres à l'envers : " << endl;
		char TabChiffresInv[10];
		for(unsigned int chiffre = 9;chiffre >= 0;chiffre--)
		{
			TabChiffresInv[9 - chiffre] = '0' + chiffre;
			cout << TabChiffresInv[9 - chiffre];
		}
		cout << endl << endl;

		cout << "Les couples majuscules/minuscules : " << endl;
		for(unsigned int maj = 0; maj < 26; maj++)
		{
			cout << (char)('A' + maj) << TabMinusc[maj] << " ";
		}
	} // CharEtCodeASCII()

	string ToUpper(string &Str)
	{
		for(unsigned int car = 0;car < Str.length();car++)
		{
			Str[car] = toupper(Str[car]);
		}
		return Str;
	} // ToUpper()

	string ToLower(string &Str)
	{
		for(unsigned int car = 0;car < Str.length();car++)
		{
			Str[car] = tolower(Str[car]);
		}
		return Str;
	} // ToLower()

	void MajuscMinusc()
	{
		cout << "Majuscules <--> Minuscules" << endl << endl;
		string ChaineLue;
		while(true)
		{
			cout << "Tapez une ligne (ligne vide pour terminer le programme) : ";
			getline(cin, ChaineLue);
			if(ChaineLue.empty())  break;

			cout << "Ligne lue : " << ChaineLue << endl;
			cout << "Ligne en majuscule : " << ToUpper(ChaineLue) << endl;
			cout << "Ligne en minuscule : " << ToLower(ChaineLue) << endl;
		}
	} // MajuscMinusc()

	unsigned int FindCarInStr(const char Recherche, const string Str, const unsigned int Debut)
	{
		unsigned int Pos = Debut;
		while(Str[Pos] != Recherche && Pos < Str.length())
		{
			Pos++;
		}
		return Pos;
	} // FindCarInStr()
	
	void TestFindCarInStr()
	{
		cout << "Recherche d'un caractère dans une chaîne" << endl << endl;

		cout << "Tapez une ligne terminée par <enter> : ";
		string ChaineLue;
		cin >> ChaineLue;

		cout << "Caractères non-espace à rechercher  (finir par un .) : ";
		string Recherches;
		cin >> Recherches;

		// Ajout d'un point en fin de chaîne
		if(Recherches[Recherches.length() - 1] != '.')
		{
			Recherches += '.';
		}

		for(unsigned int rech = 0; rech < Recherches.length(); rech++)
		{ 
			cout << "Le caractère " << Recherches[rech] << " ";

			unsigned int Pos = FindCarInStr(Recherches[rech], ChaineLue, 0);
			if(Pos < ChaineLue.length())
			{
				cout << "est à la position " << Pos << "." << endl;
			}
			else
			{
				cout << "n'est pas présent dans la chaîne." << endl;
			}
		}
	} // TestFindCarInStr()

	unsigned int FindSubstrInStr(const string Recherche, const string Str, const unsigned int Debut)
	{
		if(Str.length() < Recherche.length()) return Str.length();

		unsigned int Pos = Debut;
		unsigned int PosFinale = Str.length() - Recherche.length();

		while(Pos < PosFinale)
		{
			unsigned int i;
			for(i = 0;i <= Recherche.length();i++)
			{
				if(Recherche[i] != Str[Pos + i]) break;
			}

			if(i == Recherche.length()) return Pos;
			Pos++;
		}

		return Str.length();
	} // FindSubstrInStr()

	void TestFindSubstrInStr()
	{
		cout << "Recherche d'une sous chaîne dans une chaîne" << endl << endl;

		cout << "Tapez une ligne terminée par <enter> : ";
		string ChaineLue;
		cin >> ChaineLue;

		cout << endl << "Sous-chaine à rechercher : ";
		string Recherche;
		cin >> Recherche;

		cout << "La sous-chaîne ";

		unsigned int Pos = FindSubstrInStr(Recherche, ChaineLue, 0);
		if(Pos < ChaineLue.length())
		{
			cout << "est à la position " << Pos << "." << endl;
		}
		else
		{
			cout << "n'est pas présente." << endl;
		}
	} // TestFindSubstrInStr()

	string CompactStr(string &Str)
	{
		unsigned int NbCar = 0;
		for(unsigned int i = 0;i < Str.length();i++)
		{
			if(!isspace(Str[i]))
			{
				Str[NbCar] = Str[i];
				NbCar++;
			}
		}
		Str.resize(NbCar);
		return Str;
	} // CompactStr()

	void SupprSpaces()
	{
		cout << "Suppression des espacements" << endl << endl;

		string ChaineLue;
		while(true)
		{
			getline(cin, ChaineLue);
			if(ChaineLue.empty()) break;
			cout << CompactStr(ChaineLue) << endl;
		}
	} // SupprSpaces()

	string SeparMots(string &Str)
	{
		unsigned int NbCar = 0;
		for(unsigned int i = 0; i < Str.length(); i++)
		{
			if(!isspace(Str[i]))
			{
				Str[NbCar + 1] = Str[i];
			}
			else if(NbCar > 0 && Str[NbCar - 1] != ' ')
			{
				Str[NbCar + 1] = ' ';
			}
		}

		if(NbCar > 0 && Str[NbCar - 1] == ' ')
		{
			NbCar--;
		}

		Str.resize(NbCar);
		return Str;
	} // SeparMots()

	void SeparMots()
	{
		cout << "Suppression des espaces inutiles\n\n";
		while(true)
		{
			string LigneLue;
			getline(cin, LigneLue);
			if(LigneLue.empty()) break;
			cout << SeparMots(LigneLue) << endl;
		}
	} // SeparMots()

	string ExpandTab(string &Str, const unsigned int Align)
	{
		string LExpanded;
		for(unsigned int RgReel = 0;RgReel < Str.length();RgReel++)
		{
			if(Str[RgReel] == '\t')
			{
				for(unsigned int i = 1;i < Align - LExpanded.length() % Align;i++)
				{
					LExpanded += ' ';
				}
			}
			else
			{
				LExpanded += Str[RgReel];
			}
		}
		Str = LExpanded;
		return Str;
	} // ExpandTab()

	void ExpandTab()
	{
		cout << "Expansion\n\n";
		while(true)
		{
			string LigneLue;
			getline(cin, LigneLue);
			if(LigneLue.empty()) break;
			cout << LigneLue << "#\n" << ExpandTab(LigneLue, 8) << "#\n";
		}
	} // ExpandTab()

	string Justification(string &Chaine, unsigned int NbInterv, unsigned int LgMax)
	{
		unsigned int NbCar = Chaine.length();
		unsigned int NbSpEnPlus = LgMax - NbCar;

		Chaine.resize(LgMax);

		unsigned int NextPos = LgMax - 1;
		for(unsigned int i = NbCar;i > 0;i--)
		{
			if(Chaine[i] == ' ')
			{
				unsigned NbSpacesParInterv = NbSpEnPlus / NbInterv;
				NbSpEnPlus -= NbSpacesParInterv;
				NbInterv--;

				for(unsigned int j = 1; j <= NbSpacesParInterv; j++)
				{
					Chaine[NextPos - 1] = ' ';
				}
			}
			Chaine[NextPos - 1] = Chaine[i];
		}

		return Chaine;
	} // Justification

	void Justification()
	{
		cout << "Justification\n";

		const unsigned int KLgMax = 50;

		while(true)
		{
			string LigneLue;
			getline(cin, LigneLue);
			if(LigneLue.empty()) break;

			// Suppression des espaces inutiles
			string LigneCopie(LigneLue);
			SeparMots(LigneCopie);

			// Calcul du nombre d'intervalles
			unsigned int NbInterv = 0;
			for(unsigned int i = 0; i < LigneCopie.length();i++)
			{
				if(isspace(LigneCopie[i]))
				{
					NbInterv++;
				}
			}

			// Validation de la longueur
			if(LigneCopie.length() > KLgMax)
			{
				cout << "La ligne suivante dépasse " << KLgMax << " caractères.\n";
				cout << LigneLue << endl;
			}
			else if(NbInterv == 0)
			{
				cout << "La ligne suivante ne peut être justifiée." << endl;
				cout << LigneLue << endl;
			}
			else
			{
				cout << Justification(LigneCopie, NbInterv, KLgMax) << endl;
			}
		}
	} // Justification
} // namespace

int main ()
{
	Justification();
    return 0;
} // main()
