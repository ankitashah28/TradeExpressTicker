package com.mobicule;

import java.util.Vector;

import com.mobicule.DFC.LogCache;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class TickerLogs extends MainScreen
{
	public TickerLogs()
	{
		super(NO_VERTICAL_SCROLL);
		init();
	}

	public void init()
	{
		Vector exceptionLogVec = LogCache.getInstance().getExceptionLogs();
		Vector recycledLogVec = LogCache.getInstance().getRecycledLogs();
		Vector logVec = LogCache.getInstance().getLoggingMessages();
		Vector allMessagesLogVec = new Vector();

		if (exceptionLogVec.size() > 0)
		{
			allMessagesLogVec.addElement("-------Exceptions-----\n");
			for (int i = 0; i < exceptionLogVec.size(); i++)
			{
				allMessagesLogVec.addElement(exceptionLogVec.elementAt(i));
			}
		}

		if (recycledLogVec.size() > 0)
		{
			allMessagesLogVec.addElement("\n-------RecycledLogs-----\n");
			for (int i = 0; i < recycledLogVec.size(); i++)
			{
				allMessagesLogVec.addElement(recycledLogVec.elementAt(i));
			}
		}

		if (logVec.size() > 0)
		{
			allMessagesLogVec.addElement("\n-------Logs-----\n");
			for (int i = 0; i < logVec.size(); i++)
			{
				allMessagesLogVec.addElement(logVec.elementAt(i));
			}
		}
		
		Logger.event("******TickerLogs: allMessagesLogVec: " + allMessagesLogVec.size() + "........." + allMessagesLogVec.toString());
		
		VerticalFieldManager fieldManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR)
		{
			protected void sublayout(int maxWidth, int maxHeight)
			{
				super.sublayout(maxWidth, maxHeight);
				maxWidth = Display.getWidth();
				maxHeight = Display.getHeight();
				setExtent(maxWidth, maxHeight);
			}
		};
		
		for(int i=0;i<allMessagesLogVec.size();i++)
		{
			LabelField field = new LabelField(allMessagesLogVec.elementAt(i).toString(),FOCUSABLE);
			fieldManager.add(field);
		}
		
		add(fieldManager);
	}

}
