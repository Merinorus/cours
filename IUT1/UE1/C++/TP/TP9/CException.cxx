/**
 *
 * @file     CException.cxx
 *
 * @authors  M. Laporte, D. Mathieu
 *
 * @date     06/11/2007
 *
 * @version  V1.0
 *
 * @brief    classe CException
 *
 **/
#include <iostream>
#include <string>

#include "CException.h"

using namespace std;

#define CEXC nsUtil::CException

//==========================
// Classe nsUtil::CException
//==========================

CEXC::CException (const string & Libelle /* = string () */,
                  const unsigned CodErr  /* = 0  */) throw ()
    : m_Libelle (Libelle), m_CodErr (CodErr) {}

const string & CEXC::GetLibelle (void) const throw ()
{
    return m_Libelle;

} // GetLibelle()

unsigned CEXC::GetCodErr (void) const throw () { return m_CodErr;  }

CEXC::~CException (void) throw () {}

const char* CEXC::what() const throw()  { return m_Libelle.c_str(); }

void CEXC::Afficher() const
{
    cout << "Exception : " << m_Libelle << '\n'
         << "Code      : " << m_CodErr;

} // Afficher()

#undef CEXC
