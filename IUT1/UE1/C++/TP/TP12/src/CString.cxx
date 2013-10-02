/**
 *
 * @file    CString.cxx
 *
 * @authors M. Laporte, D. Mathieu
 *
 * @date    04/02/2011
 *
 * @version V1.0
 *
 * @brief   Definition des methodes de la classe CString
 *
 **/
#include <string>
#include <cctype>   // tolower(), toupper(), isalnum(), isdigit(), ...

#include "CString.h"

using namespace std;

#define CSTR nsUtil::CString

// =========================
// Classe nsUtil::CString
// =========================

CSTR::CString () : string () {}

CSTR::CString (const char * NTCTS) : string (NTCTS) {}

CSTR::CString (const char * NTCTS, size_type Nbre)
    : string (NTCTS, Nbre) {}

CSTR::CString (const string & S, size_type Pos  /* = 0    */,
                                 size_type Nbre /* = npos */)
    : string (S, Pos, Nbre) {}

CSTR::CString (size_type Nbre, char C)
    : string (Nbre, C) {}

CSTR & CSTR::toupper (void) throw ()
{
    for (size_type i (size()); i-- > 0; )
        (*this) [i] =std::toupper ((*this) [i]);

    return *this;

} // toupper()

CSTR & CSTR::tolower (void) throw ()
{
    for (size_type i (size()); i-- > 0; )
        (*this) [i] =std::tolower ((*this) [i]);

    return *this;

} // tolower()

bool CSTR::isalpha  (void) const   throw ()
{
    for (size_type i (size()); i-- > 0; )
        if (! std::isalpha ((*this)[i])) return false;

    return true;

} // isalpha()

bool CSTR::isdigit  (void) const   throw ()
{
    for (size_type i (size()); i-- > 0; )
        if (! std::isdigit ((*this)[i])) return false;

    return true;

} // isdigit()

bool CSTR::isalnum  (void) const   throw ()
{
    for (size_type i (size()); i-- > 0; )
        if (! std::isalnum ((*this)[i])) return false;

    return true;

} // isalnum()

bool CSTR::isxdigit (void) const   throw ()
{
    for (size_type i (size()); i-- > 0; )
        if (! std::isxdigit ((*this)[i])) return false;

    return true;

} // isxdigit()

bool CSTR::islower  (void) const   throw ()
{
    for (size_type i (size()); i-- > 0; )
        if (! std::islower ((*this)[i])) return false;

    return true;

} // islower()

bool CSTR::isupper  (void) const   throw ()
{
    for (size_type i (size()); i-- > 0; )
        if (! std::isupper ((*this)[i])) return false;

    return true;

} // isupper()

bool CSTR::isspace  (void) const   throw ()
{
    for (size_type i (size()); i-- > 0; )
        if (! std::isspace ((*this)[i])) return false;

    return true;

} // isspace()

bool CSTR::isXXX(FctIsOrTo_t func) throw()
{
	for(size_type i (size()); i-- > 0; )
		if(! func((*this)[i])) return false;

	return true;
} // isXXX()

CSTR & CSTR::toXXX (FctIsOrTo_t func) throw ()
{
    for (size_type i (size()); i-- > 0; )
        (*this) [i] = func ((*this) [i]);

    return *this;

} // toXXX()

#undef CSTR
