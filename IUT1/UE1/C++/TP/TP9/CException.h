/**
 *
 * @file     CException.h
 *
 * @authors  M. Laporte, D. Mathieu
 *
 * @date     26/10/2007
 *
 * @version  V1.0
 *
 * @brief    Declaration de la classe CException
 *
 **/
#if ! defined __CEXCEPTION_H__
#define       __CEXCEPTION_H__

#include <string>
#include <exception>
#include "CstCodErr.h"

namespace nsUtil
{
    class CException : public std::exception
    {
        std::string m_Libelle;
        unsigned    m_CodErr;

      public :
        CException (const std::string & Libelle = std::string(),
                    const unsigned      CodErr  = KNoError)	  throw ();
        virtual ~CException (void)                            throw ();

        const std::string & GetLibelle (void) const           throw ();
        unsigned            GetCodErr  (void) const           throw ();

        virtual const char* what() const                      throw();

        void Afficher (void) const;

    }; // CException

} // namespace nsUtil

#endif        /* __CEXCEPTION_H__ */
