#ifndef __CDELAY_H__
#define __CDELAY_H__

class CDelay
{
	private:
		long int m_Seconds;
		long int m_uSeconds;
	public:
		CDelay(long int Seconds = 0, long int uSeconds = 0);
		CDelay(CDelay &delay);

		bool operator == (const CDelay &delay) const;
		bool operator < (const CDelay &delay) const;
		CDelay operator +(const CDelay &delay) const;
		CDelay operator *(const CDelay &delay) const;
};

#endif // __CDELAY_H__
