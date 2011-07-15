package com.mobicule;

import java.util.Vector;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;

import org.json.me.JSONArray;
import org.json.me.JSONObject;

import com.mobicule.DFC.DFCEstablishment;
import com.mobicule.DFC.DFCListener;

public class TradeScreen extends MainScreen implements DFCListener
{
	JSONObject jsonObject;

	public TradeScreen()
	{
		super(NO_VERTICAL_SCROLL);

		try
		{
			jsonObject = new JSONObject("{details:[{scrip:ACCEQ,exchange:NSE}" + ",{scrip:DABUR,exchange:BSE}"
					+ ",{scrip:ABB,exchange:BSE}" + ",{scrip:INFOSYS,exchange:BSE}" + ",{scrip:INFORTEC,exchange:BSE}"
					+ ",{\"scrip\":\"ACCEQ~F:1-MON\",\"exchange\":\"NSE\"}"
					+ ",{\"scrip\":\"ACCEQ~F:2-MON\",exchange:NSE}"
					+ ",{\"scrip\":\"ABBEQ~O:1-MON:CA:780\",\"exchange\":\"NSE\"}"
					+ ",{\"scrip\":\"BHELEQ~F:1-MON\",\"exchange\":\"NSE\"}"
					+ ",{\"scrip\":\"RELINFRAEQ~F:1-MON\",\"exchange\":\"NSE\"}" + "]}");

			initiateTicker();
			init();
		}
		catch (Throwable e)
		{
			Logger.event("TradeScreen: Exception..." + e.getMessage());
			e.printStackTrace();
		}
	}

	private void init()
	{
		LabelField labelField = new LabelField("Initialising Ticker........");
		add(labelField);
	}

	private void initiateTicker()
	{
		try
		{
			JSONObject jsonO = new JSONObject(
					"{\"isItMarketMaker\":\"N\",\"tsNumber\":1,\"password\":\"password1\",\"introductoryID\":null,\"exchangeDeliveryTermsList\":\"BSE^OD^NSE^D^NCDEX^D^MCX^D^CDNSE^D\",\"ipaddress\":\"10.0.0.54\",\"loginName\":\"jayesh1\",\"version\":null,\"exchangeProductType\":\"BSE^CASH^NSE^CASH^NCDEX^FUTURE^MCX^FUTURE^CDNSE^FUTURE\",\"sessionNo\":\"jayesh1        4050917043\",\"errorCode\":0,\"tickerDetails\":{\"serverIP\":\"feed.religaresecurities.com\",\"serviceLevel\":\"29\",\"group\":\"*\",\"serverPort\":\"80\",\"protocol\":\"TCP\"},\"profiles\":[],\"message\":\"Login successful.Welcome to TradeanyWhere.Your last login was on 24 Mar 2011 04:17:30.Your password will expire in 10 days.\",\"instrumentType\":\"BSE^^NSE^^NCDEX^FUTCOM^MCX^FUTCOM^CDNSE^FUTCUR\",\"exchangeTypeOfOrders\":\"BSE^NORMAL^NSE^NORMAL^NCDEX^NORMAL^MCX^NORMAL^CDNSE^NORMAL\",\"password1\":\"password2\",\"accRefCodeList\":\"\",\"delivery\":1,\"exchangeTermsList\":\"BSE^DAY~EOS~PFC~PFK^NSE^DAY~IOC^NCDEX^DAY~IOC~GTD~GTC^MCX^DAY~IOC~GTD~GTC^CDNSE^DAY~IOC\",\"source\":1,\"clientName\":\"jayesh1\",\"clientCode\":\"JAYESH1\",\"newPassword\":null,\"exchangeMarketSegmentList\":\"BSE^RL^NSE^NL^NCDEX^RL^MCX^RL^CDNSE^RL\",\"tradingAccountExchangeList\":\"BSE$EQUITY|RTGSLDEL,DEL~RTGSLMARPLUS,MPT~RTGSLMTF,MTF~RTGSLOBL,OBL^CDNSE$EQUITY|RTGSLINTRA,MAR^MCX$EQUITY|RTGSLINTRA,MAR^NCDEX$EQUITY|RTGSLINTRA,MAR^NSE$EQUITY|RTGSLDEL,DEL~RTGSLINTRA,MAR~RTGSLMARPLUS,MPT~RTGSLOBL,OBL~RTGSLMTF,MTF\",\"aMOBulk\":0,\"exchangeCurrencyList\":\"BSE^RUPEE^CDNSE^RUPEE^MCX^RUPEE^NCDEX^RUPEE^NSE^RUPEE\",\"optionType\":\"BSE^^NSE^^NCDEX^^MCX^^CDNSE^XX\",\"clientType\":\"C\",\"expiryDate\":\"BSE^^CDNSE^~29-MAR-11~27-APR-11~27-MAY-11~28-JUN-11~27-JUL-11^MCX^~29-MAR-11~27-APR-11~27-MAY-11~28-JUN-11~27-JUL-11~29-MAR-11~31-MAR-11~05-APR-11~15-APR-11~18-APR-11~29-APR-11~30-APR-11~05-MAY-11~14-MAY-11~04-JUN-11~30-JUN-11~05-JUL-11~15-DEC-11~14-DEC-12^NCDEX^~29-MAR-11~27-APR-11~27-MAY-11~28-JUN-11~27-JUL-11~29-MAR-11~31-MAR-11~05-APR-11~15-APR-11~18-APR-11~29-APR-11~30-APR-11~05-MAY-11~14-MAY-11~04-JUN-11~30-JUN-11~05-JUL-11~15-DEC-11~14-DEC-12~29-MAR-11~31-MAR-11~04-APR-11~20-APR-11~29-APR-11~03-MAY-11~20-MAY-11~20-JUN-11~28-JUN-11~04-JUL-11^NSE^\"}");
			DFCEstablishment dfcEstablishment = DFCEstablishment.getInstance(jsonO);

			if (dfcEstablishment != null)
			{

				dfcEstablishment.deRegisterKeys();
			}

			Logger.event("*******TradeScreen: deRegisterKeys: ");

			Vector exchangeScripSetVector = new Vector();

			JSONArray jsonArray = jsonObject.getJSONArray("details");

			Logger.event("TradeScreen: initialiseTicker: jsonArray: " + jsonArray.length() + "...."
					+ jsonArray.toString());
			for (int i = 0; i < jsonArray.length(); i++)
			{
				Vector exchangeScripVector = new Vector();
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				String exch = jsonObj.getString("exchange");
				exchangeScripVector.addElement(exch);
				String scrp = jsonObj.getString("scrip");
				exchangeScripVector.addElement(scrp);
				exchangeScripSetVector.addElement(exchangeScripVector);
			}

			Logger.event("*******TradeScreen: exchangeScripSetVector: " + exchangeScripSetVector.toString());

			DFCEstablishment.getInstance().setExchangeScripSet(exchangeScripSetVector);

			Logger.event("*******TradeScreen: Started: ");

			DFCEstablishment.getInstance().setDFCListener(this);
			DFCEstablishment.getInstance().addUMCKey();

			Logger.event("*******TradeScreen: Complete: ");
		}
		catch (Throwable e)
		{
			Logger.event("TradeScreen: initialiseTicker:Exception..." + e.getMessage());
			e.printStackTrace();
		}
	}

	public void acknowledgePlaceOrder(JSONArray arg0)
	{
		Logger.event("********TradeScreen: acknowledgePlaceOrder: " + arg0.toString());
	}

	public void updateSripDetails(JSONObject arg0)
	{
		Logger.event("********TradeScreen: updateSripDetails: " + arg0.toString());
	}

	protected void makeMenu(Menu menu, int instance)
	{
		super.makeMenu(menu, instance);
		menu.deleteAll();
		menu.add(logMenuItem);
	}

	protected MenuItem logMenuItem = new MenuItem("ShowLogs", 2, 0)
	{
		public void run()
		{
			UiApplication.getUiApplication().pushScreen(new TickerLogs());
		}
	};
}
