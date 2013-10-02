#ifndef __CRATIONNEL_H__
#define __CRATIONNEL_H__

class CFraction
{
	private:
		int m_Num;
		int m_Denum;
		void Simplify();

	public:
		CFraction(const int Num = 0, const int Denum = 0);
		CFraction(CFraction &cfrac);

		void Display() const throw();
		unsigned GCD() const throw();

		bool operator == (CFraction &cfrac);
		bool operator != (CFraction &cfrac);
		bool operator < (CFraction &cfrac);
		bool operator > (CFraction &cfrac);
};

#endif // __CRATIONNEL_H__
