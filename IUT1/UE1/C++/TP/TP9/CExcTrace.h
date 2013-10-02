#ifndef __CEXTTRACE_H__
#define __CEXTTRACE_H__

#include <string>
#include <exception>
#include <vector>
#include "CException.h"
#include "CstCodErr.h"

namespace nsUtil
{
	class CExcTrace : public CException
	{
		private:
			std::vector<std::string> m_trace;
		public:
			CExcTrace(const std::string & Trace = std::string(),
					  const std::string & Libelle = std::string(),
			          const unsigned      CodErr  = KNoError)	  throw();
			virtual ~CExcTrace(void) 							  throw();

			const std::vector<std::string> & GetTrace() 	const throw();
			void AddFct(std::string fct) throw();
	};
}

#endif // __CEXTTRACE_H__
