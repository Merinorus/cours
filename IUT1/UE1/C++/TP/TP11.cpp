#include <iostream>
#include <string>
#include <cctype>
#include <vector>
#include <algorithm>
using namespace std;

namespace {
	void Ex2() {
		string *tab_pStr[10];

		unsigned* pInt = new unsigned[10];
		for(unsigned i = 0; i < 10; ++i) {
			pInt[i] = i + 1;
		}

		for(unsigned* i = pInt; i < pInt + 10; i++) {
			cout << *i;
		}

		for(int i = 0; i < 10; ++i) {
			tab_pStr[i] = new string;
			cin >> *tab_pStr[i];
		}

		for(int d = 0; d < 10; ++d) {
			cout << *tab_pStr[d] << endl;
			delete tab_pStr[d];
		}
	}

	void Proc1(int* num) {
		cout << *num << '\t';
	}

	void Proc2(string* str1, string* str2) {
		for(string* i = str1; i < str2; ++i) {
			cout << *i;
		}
	}

	void Proc3(string* str, string::iterator index) {
		*index = toupper(*index);
		cout << *index;
	}

	void Ex3() {
		string strTest;
		cin >> strTest;
		for(string::iterator i = strTest.begin(); i < strTest.end(); ++i) {
			Proc3(&strTest, i);
		}
	}

	void Test_copy() {
		int tab1[10], tab2[10];
		for(unsigned i = 0; i < 10; ++i) {
			tab1[i] = i;
			tab2[i] = i*2;
		}

		copy(tab1, tab1+3, tab2 + 2);

		for(unsigned d = 0; d < 10; ++d) {
			cout << tab1[d] << ',' << tab2[d] << endl;
		}
	}

	void Test_equal() {
		vector<int> TabInt1(2), TabInt2;
		copy(TabInt1.begin(), TabInt1.end(), TabInt2.begin());
		TabInt2.at(9) = 20;
		if(equal(TabInt1.begin(), TabInt1.begin() + 9, TabInt1.begin() + 1)) cout << "ok";
	}

	void equalPalindrome() {
		string mot;
		cin >> mot;
		if(equal(mot.begin(), mot.begin() + mot.length()/2, mot.rbegin())) cout << "ok";
	}

	bool equalNTCTS(char* str1, char* str2) {
		bool equal = true;
		for(int i = 0; equal; ++i) {
			equal = ( *(str1+i) == *(str2+i));

			if(*(str1+i) == '\0' || *(str2+1) == '\0') {
				break;
			}
		}
		return equal;
	}
}

int main() {
	char * NTCTS1    = "Coucou";
	char   NTCTS2 [] = "Coucou";
	cout << equalNTCTS(NTCTS1, NTCTS2);
	return 0;
}
