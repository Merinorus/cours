#include "CDate.h"
#include <vector>
#include <string>
#include <iostream>

CDate::CDate(unsigned Day /* = 1 */, unsigned Month /* = 1 */, unsigned Year /* = 1900 */)
{
	if(Day < 1 || Day > 31)
	{
		Reset();
		return;
	}

	if(Month > 12 || Year < 1900 || Year > 2013)
	{
		Reset();
		return;
	}

	m_Year = Year;
	Leap();

	switch(Month)
	{
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if(Day > 31)
			{
				Reset();
				return;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			if(Day > 31)
			{
				Reset();
				return;
			}
			break;
		case 2:
			if((m_Leap && Day > 29) || (!m_Leap && Day > 28))
			{
				Reset();
				return;
			}
			break;
		default:
			Reset();
			return;
	}

	m_Month = Month;
	m_Day = Day;
}

void CDate::Reset() throw()
{
	m_Day = 1;
	m_Month = 1;
	m_Year = 1900;
	Leap();
}

void CDate::Leap()
{
	m_Leap = (m_Year%4u == 0) && (m_Year%100u != 0);
}

unsigned CDate::GetDayNumber() throw()
{
	unsigned DayN = 0;
	switch(m_Month)
	{
	case 1:
	case 2:
		DayN = m_Day;
		break;
	case 3:
		DayN = 28 + 31 + m_Day;
		break;
	case 4:
		DayN = 28 + 30 + 31 + m_Day;
		break;
	case 5:
		DayN = 28 + 30 + 31*2 + m_Day;
		break;
	case 6:
		DayN = 28 + 30*2 + 31*2 + m_Day;
		break;
	case 7:
		DayN = 28 + 30*3 + 31*2 + m_Day;
		break;
	case 8:
		DayN = 28 + 30*3 + 31*3 + m_Day;
		break;
	case 9:
		DayN = 28 + 30*3 + 31*4 + m_Day;
		break;
	case 10:
		DayN = 28 + 30*4 + 31*4 + m_Day;
		break;
	case 11:
		DayN = 28 + 30*4 + 31*5 + m_Day;
		break;
	case 12:
		DayN = 28 + 30*5 + 31*5 + m_Day;
		break;
	default:
		Reset();
		break;
	}

	if(m_Leap && m_Month > 2) DayN++;
	return DayN;
}

CDate CDate::GetEve() const throw()
{
	CDate eve(m_Day, m_Month, m_Year);
	if(eve.m_Day > 1)
	{
		eve.m_Day--;
	}
	else
	{
		switch(eve.m_Month)
		{
			case 1:
				eve.m_Year--;
				eve.m_Day = 31;
				eve.m_Month = 12;
				break;
			case 2:
				eve.m_Month--;
				eve.m_Day = (eve.m_Leap) ? 29 : 28;
				break;
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
				eve.m_Month--;
				eve.m_Day=30;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				eve.m_Month--;
				eve.m_Day=31;
				break;
			default:
				eve.Reset();
				break;
		}
	}

	return eve;
}

void CDate::Display() const throw()
{
	std::cout << m_Day << "/" << m_Month << "/" << m_Year;
}
