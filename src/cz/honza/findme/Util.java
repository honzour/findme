package cz.honza.findme;

import android.telephony.SmsManager;
import android.widget.Toast;

public class Util {
	public static void toast(String s)
	{
		Toast.makeText(FindMeApplication.sInstance, s, Toast.LENGTH_LONG).show();
	}
	
	public static void toast(int res)
	{
		Toast.makeText(FindMeApplication.sInstance, res, Toast.LENGTH_LONG).show();
	}
	
	protected static void sendSMS(String number, String message)
	{
		if (Settings.fakeSMS)
		{
			toast(number + ' ' + message);
			return;
		}
		SmsManager manager = SmsManager.getDefault();
		manager.sendTextMessage(number, null, message, null, null);
	}
}
