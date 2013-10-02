/**
 *
 * @file    CString.h
 *
 * @authors M. Laporte, D. Mathieu
 *
 * @date    05/11/2007
 *
 * @version V1.0
 *
 * @brief   Extension de la classe standard string
 *
 **/
#if ! defined __CSTRING_H__
#define       __CSTRING_H__

#include <string>

namespace nsUtil
{
    class CString : public std::string
    {
    private :
    	typedef int (*FctIsOrTo_t) (int);
    	bool isXXX(FctIsOrTo_t func) throw();
    	CString & toXXX(FctIsOrTo_t func) throw();

      public :
        CString ();
        CString (const char * NTCTS);
        CString (const char * NTCTS, size_type Nbre);
        CString (const std::string & S, size_type Pos = 0,
                                   size_type Nbre = npos);
        CString (size_type Nbre, char C);

        CString & toupper (void)     throw ();
        CString & tolower (void)     throw ();

        bool isalpha  (void) const   throw ();
        bool isdigit  (void) const   throw ();
        bool isalnum  (void) const   throw ();
        bool isxdigit (void) const   throw ();
        bool islower  (void) const   throw ();
        bool isupper  (void) const   throw ();
        bool isspace  (void) const   throw ();

    }; // CString

} // namespace nsUtil

#endif        /* __CSTRING_H__ */
