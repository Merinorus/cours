/**
 *
 * @file    TestCString.cxx
 *
 * @authors M. Laporte, D. Mathieu
 *
 * @date    05/11/2007
 *
 * @version V1.0
 *
 * @brief   Test de la classe CString, extension de string
 *
 **/

#include <iostream>

#include "CString.h"

using namespace std;
using namespace nsUtil;

namespace
{
    void TestCString (void)
    {
        cout << "Test des constructeurs\n\n";

        CString S1;
        cout << '"' << S1 << "\"\n";
        cout << S1.append ("S1") << '\n';
        CString S2 ("Coucou");
        cout << (S2 + ' ' + S1) << '\n';
        CString S3 ("Coucou", 4);
        cout << S3 << '\n';
        CString S4 (10, '#');
        cout << S4 << '\n';

        CString Str ("AbCdEf1234");
        cout << Str << '\n';
        cout << Str.toupper() << '\n';
        cout << Str << '\n';
        cout << Str.tolower() << '\n';
        cout << Str << '\n';

        cout << "\nTest des fonctions\n\n";
        CString STR ("MAJUSCULE");
        CString str ("minuscule");
        CString Space ("  \t\t\n\n");

        cout << boolalpha;

        cout << Str   << " alpha ? "  << Str.isalpha   () << '\n';
        cout << Str   << " alnum ? "  << Str.isalnum   () << '\n';
        cout << Str   << " digit ?  " << Str.isdigit   () << '\n';
        cout << Str   << " xdigit ? " << Str.isxdigit  () << '\n';
        cout << Str   << " lower ? "  << Str.islower   () << '\n';
        cout << Str   << " upper ? "  << Str.isupper   () << '\n';
        cout << Str   << " space ? "  << Str.isspace   () << '\n';
        cout << STR   << " upper ? "  << STR.isupper   () << '\n';
        cout << str   << " lower ? "  << str.islower   () << '\n';
        cout << Space << " space ? "  << Space.isspace () << '\n';

        cout << noboolalpha;

    } // TestCString ()

} // namespace

int main (void)
{
    TestCString();

    return 0;

} // main()
