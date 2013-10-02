/**
 *
 * @file     CRationnel.h
 *
 * @authors  M. Laporte, D. Mathieu
 *
 * @date     04/11/2007
 *
 * @version  V1.0
 *
 * @brief    Declaration de la classe CRationnel (V1)
 *             Ajout des operateurs de relation
 *
 **/
#if ! defined __CRATIONNEL_H__
#define       __CRATIONNEL_H__

typedef unsigned long long ULLong_t;

namespace nsMath
{
    class CRationnel
    {
        int m_Num;
        int m_Denom;

        void Simplifier (void)                              throw ();

      public :
        CRationnel (const int Num = 0, const int Denom = 1) throw ();
        CRationnel (const CRationnel & R)                   throw ();

        void Afficher (void) const throw ();

        bool operator <       (const CRationnel & R)  const throw ();
        bool operator ==      (const CRationnel & R)  const throw ();

        CRationnel operator + (const CRationnel & R)  const throw ();
        CRationnel operator - (const CRationnel & R)  const throw ();
        CRationnel operator * (const CRationnel & R)  const throw ();
        CRationnel operator / (const CRationnel & R)  const throw ();

    }; // CRationnel

} // namespace nsMath

#endif  /* __CRATIONNEL_H__ */
