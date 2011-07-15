/*
 * Logger.java
 *
 * © Compliant Phones, 2007
 * Confidential and proprietary.
 */

package com.mobicule;

import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.system.EventLogger;
import net.rim.device.api.ui.component.Status;

/**
 * Simple Logging class
 */
public class Logger
{
	public final static boolean DEBUG = true;

	// private
	// {CD6B6D51-6DF1-4919-9754-AA87583DBD4F}

	private Logger()
	{
		System.out.println("Starting SSFirstCitizen Logger");
	}

	public static void log(String s)
	{
		s_instance.impl_log(s);
		// event(s);
	}

	public static void event(String s)
	{
		s_instance.impl_log(s);
		s_instance.impl_event(s);
	}

	public static void log(Exception e)
	{
		s_instance.impl_event(e.toString());
	}

	/**
	 * Popup a message on screen for a short while
	 */
	public static void popup(String s, int delayms)
	{
		s_instance.impl_popup(s, delayms);
	}

	public static void error(Throwable t, String why)
	{
		s_instance.impl_error(t, why);
	}

	private SimpleDateFormat s_dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

	synchronized private void impl_log(String s)
	{
		String timeStr = s_dateFormat.formatLocal(System.currentTimeMillis());
		System.out.print(m_appName + ":" + timeStr + ": ");
		for (int i = 0; i < s.length(); ++i)
		{
			char c = s.charAt(i);
			if ((c >= 0x20) && (c < 0x7f))
			{
				System.out.print(c);
			}
			else
			{
				System.out.print("\\x");
				System.out.print(Integer.toHexString(c));
			}
		}
		System.out.println();
	}

	// private void impl_log(Exception e)
	// {
	// impl_event(e.toString());
	// e.printStackTrace();
	// }

	private void impl_error(Throwable t, String why)
	{
		impl_event(why + ": " + t.toString());
		t.printStackTrace();
	}

	private void impl_popup(String s, int delayms)
	{
		impl_log(s);
		Status.show(s, delayms);
	}

	private void impl_event(String s)
	{
		if (!m_hasRegisteredEventLog)
		{
			EventLogger.register(s_eventGuid, m_appName, EventLogger.VIEWER_STRING);
			m_hasRegisteredEventLog = true;
		}

		if (DEBUG)
		{
			EventLogger.logEvent(s_eventGuid, s.getBytes(), EventLogger.WARNING);
		}

	}

	public static String showString(Object ob)
	{
		if (ob == null)
		{
			return "null";
		}
		else if (ob instanceof String)
		{
			return (String) ob;
		}
		else
		{
			return ob.toString();
		}
	}

	public static String showObject(Object ob)
	{
		StringBuffer buf = new StringBuffer();
		buf.append(ob.toString());
		buf.append('(');
		buf.append(ob.getClass().getName());
		buf.append(')');
		return buf.toString();
	}

	private static Logger s_instance = new Logger();

	private String m_appName = "SSFirstCitizen";

	private static final long s_eventGuid = 0xa9db7be72ec3edc0L;

	private boolean m_hasRegisteredEventLog = false;

	public static void setAppName(String name)
	{
		s_instance.m_appName = name;
	}
}
