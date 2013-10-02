/**
 *
 * @file     CRationnel.cxx
 *
 * @authors  M. Laporte, D. Mathieu
 *
 * @date     04/11/2007
 *
 * @version  V1.0
 *
 * @brief    Definition des methodes de la classe CRationnel
 *               (version 1)
 *
 **/
#include <iostream>
#include <iomanip>
#include <cstdlib>    // abs()

#include "CRationnel.h"

#define CRATIONNEL nsMath::CRationnel

using namespace std;
using namespace nsMath;

namespace
{
/*
    unsigned PGDC (const unsigned a, const unsigned b) throw ()
    {
        if (a == b) return a;
        if (a < b) return PGDC (a, b - a);
        if (a > b) return PGDC (b, a - b);

    } // PGDC()
*/
    unsigned PGDC (unsigned a, unsigned b) throw ()
    {
        for ( ; a != b; )
        {
            if (a < b)
                b -= a;
            else
                a -= b;
        }
        return a;

    } // PGDC()

} // namespace anonyme

CRATIONNEL::CRationnel (const int Num   /* = 0 */,
                        const int Denom /* = 1 */) throw ()
    : m_Num (Num), m_Denom (Denom)
{
    Simplifier();

} // CRationnel()

CRATIONNEL::CRationnel (const CRationnel & R) throw ()
    : m_Num (R.m_Num), m_Denom (R.m_Denom) {}

void CRATIONNEL::Afficher (void) const throw ()
{
    cout << m_Num << '/' << m_Denom;

} // Afficher()

void CRATIONNEL::Simplifier (void) throw ()
{
    if (m_Denom < 0)
    {
        m_Num   = -m_Num;
        m_Denom = -m_Denom;
    }
    int pgdc = (m_Num == 0) ? m_Denom
                            : PGDC (abs (m_Num), abs (m_Denom));

    m_Num   /= pgdc;
    m_Denom /= pgdc;

} // Simplifier()

bool CRATIONNEL::operator < (const CRationnel & R) const throw ()
{
    return m_Num * R.m_Denom < m_Denom * R.m_Num;

} // operator <

bool CRATIONNEL::operator == (const CRationnel & R) const throw ()
{
    return m_Num == R.m_Num && m_Denom == R.m_Denom;

} // operator ==

CRATIONNEL::CRationnel CRATIONNEL::operator + (const CRationnel & R)
    const throw ()
{
    return CRationnel (m_Num   * R.m_Denom + R.m_Num * m_Denom,
                       m_Denom * R.m_Denom);

} // operator +

CRATIONNEL::CRationnel CRATIONNEL::operator - (const CRationnel & R)
    const throw ()
{
    return CRationnel (m_Num   * R.m_Denom - R.m_Num * m_Denom,
                       m_Denom * R.m_Denom);

} // operator -

CRATIONNEL::CRationnel CRATIONNEL::operator * (const CRationnel & R)
    const throw ()
{
    return CRationnel (m_Num   * R.m_Num,
                       m_Denom * R.m_Denom);

} // operator *

CRATIONNEL::CRationnel CRATIONNEL::operator / (const CRationnel & R)
    const throw ()
{
    return CRationnel (m_Num * R.m_Denom, m_Denom * R.m_Num);

} // operator /

#undef CRATIONNEL
