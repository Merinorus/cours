
/**
 *
 * @file   tp_03.cxx
 *
 * @author Alain DRILLON
 *
 * @date   16/11/2012
 *
 * @brief  TP 03 C++
 *
 **/
#include <iostream>
#include <iomanip>
#include <string>
#include <cctype>
#include <fstream>

using namespace std;

namespace
{
    bool SaisirNomFic(fstream& fs, ios_base::openmode Mode, unsigned int MaxErrs, string InMsg)
    {
       unsigned int NumErrs = 0;
       string NomFic;
       while(NumErrs < MaxErrs)
       {
            cout << InMsg;
            cin >> NomFic;

            fs.open(NomFic.c_str(), Mode);
            if(fs.is_open())
            {
                return true;
            }
            else
            {
                NumErrs++;
                if(NumErrs >= MaxErrs)
                {
                    cerr << NumErrs << " echecs d'ouverture du fichier." << endl;
                    return false;
                }
            }
       }
       return false;
    } // SaisirNomFic

    bool OuvrirSrcDestStreams(fstream& streamSrc, fstream& streamDest)
    {
        if(!SaisirNomFic(streamSrc, ios_base::in, 3, "Nom du fichier source     : "))
            return false;
        else if(!SaisirNomFic(streamDest, ios_base::out, 3, "Nom du fichier destination : "))
            return false;
        else
            return true;
    } // OuvrirSrcDestStreams

    void CopieFic(fstream& streamSrc, fstream& streamDest)
    {
        string Ligne;
        for(unsigned int NumLigne = 1; !streamSrc.eof(); NumLigne++)
        {
            getline(streamSrc, Ligne);
            streamDest << setw(4) << NumLigne << "\t" << Ligne << endl;
        }
    } // CopieFic

    bool NomFichAuKbd()
    {
        string NomFicSrc, NomFicDest, Ligne;
        fstream is, os;

        if(!SaisirNomFic(is, ios_base::in, 3, "Nom du fichier source : ") ||
           !SaisirNomFic(os, ios_base::out, 3, "Nom du fichier dest. : "))
            return false;

        CopieFic(is, os);
        is.close(); os.close();
        
        return true;
    } // NomFichAuKbd

    template <class T> T Extraction()
    {
        T ext;
        while(true)
        {
            cin >> ext;
            if(cin.eof() || cin.fail()) break;
            cout << ext << endl;
        }
        return ext;
    } // Extraction

    void FonctionGet()
    {
        fstream is;
        SaisirNomFic(is, ios_base::in, 3, "Nom du fichier source : ");
        char Carac;
        while(!is.eof())
        {
            Carac = is.get();
            cout << Carac;
        }
    } // FonctionGet

    unsigned int SeekgTellg()
    {
        fstream is;
        if(!SaisirNomFic(is, ios_base::in, 3, "Nom du fichier source : "))
            return 0;

        unsigned int Pos;
        for(Pos = 0; is.get() != -1; Pos++)
        {
            is.seekg(Pos);
        }
        return Pos - 1;
    } // SeekgTellg

    void ConcatString()
    {
        fstream is;
        if(!SaisirNomFic(is, ios_base::in, 3, "Nom du fichier source : "))
            return;

        string Lignes, Ligne;
        while(!is.eof())
        {
            getline(is, Ligne);
            Lignes += Ligne + '\n';
        }

        cout << Lignes;
    } // ConcatString

    void CleanFile()
    {
        double val;
        char car;
        for(cin >> val; !cin.eof(); cin >> val)
        {
            if(cin.fail())
            {
                cin >> car;
                cin.clear();
                cerr << car;
            }
            else
            {
                cout << val << '\n';
            }
        }
    } // CleanFile

    void Decrypt()
    {
        fstream is;
        if(!SaisirNomFic(is, ios_base::in, 3, "Nom du fichier : "))
            return;
        unsigned int Debut, Pas, Pos;
        cout << "Position de début : ";
        cin >> Debut; Pos = Debut;
        cout << "Pas : ";
        cin >> Pas; Pas--;
        cout << endl;

        is.seekp(Debut);
        char carac;
        while(carac != -1)
        {
           carac = is.get(); 
           cout << carac;
           Pos += Pas;
           is.seekp(Pas);
        }
    } // Decrypt
} // namespace

int main()
{
    Decrypt();
} // main()
