#include <iostream>
#include <string>
#include <stdexcept>
#include "CException.h"

using namespace std;
using namespace nsUtil;

namespace
{
	void TestExceptionInMain() throw(exception, int)
	{
		unsigned int ExcType = 1;
		switch(ExcType)
		{
			case 0: // Standard exception
				throw(exception());
				break;
			case 1:	// More specific standard exception
			{
				//throw(runtime_error("potato"));
				string s;
				cout << s.at(0);
				break;
			}
			case 2: // CException
				throw(CException("LOL", 666));
				break;
			case 3: // Whatever
				throw(0);
				break;
			default:
				throw(CException("Unknown exception", 0));
		}
	} // TestExceptionInMain

	void TestFailure()
	{
		cin.exceptions(ios_base::failbit | ios_base::eofbit);
		try
		{
			while(true)
			{
				unsigned test;
				cin >> test;
			}
		}
		catch(...) { throw; }
	}

	void Division() throw(CException)
	{
		unsigned nums[] = {32,16,8,4,2};
		unsigned denums[] = {789,78,0,32,899};
		for(unsigned i = 0; i < 5; ++i)
		{
			try
			{
				cout << (nums[i]/denums[i]);
			}
			catch(...)
			{
				throw(CException("Division by zero", KExcDivZero));
			}
		}
	}
}

int main() {
	try
	{
		Division();
		return KNoError;
	}
	catch(const CException &e)
	{
		e.Afficher();
		return 1;
	}
	catch(const runtime_error &e)
	{
		cout << e.what() << endl;
		return 1;
	}
	catch(const exception &e)
	{
		cout << e.what() << endl;
		return KExcStd;
	}
	catch(...)
	{
		cout << "Error :(" << endl;
		return KExcUnknown;
	}
}
