package com.mobicule;

import net.rim.device.api.ui.UiApplication;

public class TradeTicker extends UiApplication
{
	public TradeTicker()
	{
		pushScreen(new TradeScreen());
	}
	
	public static void main(String[] args)
	{
		TradeTicker tradeTicker = new TradeTicker();
		tradeTicker.enterEventDispatcher();
	}
}
