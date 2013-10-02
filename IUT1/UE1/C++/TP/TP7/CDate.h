#ifndef __CDATE_H__
#define __CDATE_H__

class CDate
{
	private:
		unsigned m_Day;
		unsigned m_Month;
		unsigned m_Year;
		bool m_Leap;
		void Leap();

	public:
		CDate(unsigned Day = 1, unsigned Month = 1, unsigned Year = 1900);

		void Reset() throw();
		unsigned GetDayNumber() throw();
		CDate GetEve() const throw();
		void Display() const throw();
};

#endif // __CDATE_H__
