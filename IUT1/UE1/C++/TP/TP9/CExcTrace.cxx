#include "CExcTrace.h"
#include "CException.h"
#include <iostream>
#include <string>

using namespace nsUtil;

CExcTrace::CExcTrace(const std::string & Trace,
					 const std::string & Libelle,
					 const unsigned      CodErr)  throw ()
					 : CException(Libelle, CodErr)
{
	this->m_trace.push_back(Trace);
}

CExcTrace::~CExcTrace(void) throw() { }

const std::vector<std::string> & CExcTrace::GetTrace() 	const throw()
{
	return m_trace;
}

void CExcTrace::AddFct(std::string fct) throw()
{
	this->m_trace.push_back(fct);
}
