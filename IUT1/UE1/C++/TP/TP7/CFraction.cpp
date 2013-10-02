#include "CFraction.h"
#include <iostream>
#include <stdlib.h> // abs

using namespace std;

CFraction::CFraction(int Num /* = 0 */, int Denum /* = 0 */)
{
	m_Num = Num;
	m_Denum = Denum;
}

CFraction::CFraction(CFraction &cfrac)
{
	m_Num = cfrac.m_Num;
	m_Denum = cfrac.m_Denum;
	Simplify();
}

void CFraction::Simplify()
{
	if(m_Num == 0)
	{
		// If numerator=0, the fraction can be simplifyed with denum=1
		m_Denum = 1;
		return;
	}
	else if(m_Num < 0 && m_Denum < 0)
	{
		// If both members are negative, the fraction is positive
		m_Num = abs(m_Num);
		m_Denum = abs(m_Denum);
	}
	else if(m_Denum < 0)
	{
		// If the denumerator is negative, it is converted to positive
		// and the numerator becomes negative
		m_Denum = abs(m_Denum);
		m_Num = -m_Num;
	}

	// Simplifying with GCD
	unsigned gcd = GCD();
	m_Num/=(int)gcd;
	m_Denum/=(int)gcd;
}

unsigned CFraction::GCD() const throw()
{
	unsigned a = abs(m_Num);
	unsigned b = abs(m_Denum);
	while(a != b)
	{
		if(a < b)
			b-=a;
		else
			a-=b;
	}

	return b;
}

void CFraction::Display() const throw()
{
	cout << m_Num << "/" << m_Denum;
}

bool CFraction::operator ==(CFraction &cfrac)
{
	cfrac.Simplify();
	Simplify();

	return (m_Num == cfrac.m_Num) && (m_Denum == cfrac.m_Denum);
}

bool CFraction::operator !=(CFraction &cfrac)
{
	return (m_Num != cfrac.m_Num) || (m_Denum != cfrac.m_Denum);
}

bool CFraction::operator >(CFraction &cfrac)
{
	cfrac.Simplify();
	Simplify();

	return (double)(m_Num/m_Denum) > (double)(cfrac.m_Num/cfrac.m_Denum);
}

bool CFraction::operator <(CFraction &cfrac)
{
	cfrac.Simplify();
	Simplify();

	return (double)(m_Num/m_Denum) < (double)(cfrac.m_Num/cfrac.m_Denum);
}
