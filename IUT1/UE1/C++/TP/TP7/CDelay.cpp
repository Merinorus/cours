#include "CDelay.h"

CDelay::CDelay(long int Seconds, long int uSeconds)
{
	m_Seconds = Seconds;
	m_uSeconds = uSeconds;
}

CDelay::CDelay(CDelay &delay)
{
	m_Seconds = delay.m_Seconds;
	m_uSeconds = delay.m_uSeconds;
}

bool CDelay::operator ==(const CDelay &delay) const
{
	return (m_Seconds == delay.m_Seconds) && (m_uSeconds == delay.m_uSeconds);
}

bool CDelay::operator <(const CDelay &delay) const
{
	return (m_Seconds > delay.m_Seconds) ||
			((m_Seconds == delay.m_Seconds) && (m_uSeconds > delay.m_uSeconds));
}

CDelay CDelay::operator+(const CDelay &delay) const
{
	return CDelay(m_Seconds + delay.m_Seconds, m_uSeconds + delay.m_uSeconds);
}

CDelay CDelay::operator*(const CDelay &delay) const
{
	return CDelay(m_Seconds * delay.m_Seconds, m_uSeconds * delay.m_uSeconds);
}
