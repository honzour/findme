package cz.honza.findme;

import android.content.Context;
import android.content.SharedPreferences;
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
	
	public static SharedPreferences getPreferences()
	{
		return FindMeApplication.sInstance.getSharedPreferences(Preferences.PRORAM_SETTINGS, Context.MODE_PRIVATE);
	}
	
	protected static void sendSMS(String number, String message)
	{
		SharedPreferences prefs = getPreferences();
		boolean processSms = prefs.getBoolean(Preferences.REPLY_SETTINGS_PROCESS_SMS,
				Preferences.REPLY_SETTINGS_PROCESS_SMS_DEFAULT);
		if (processSms)
		{
			toast(number + ' ' + message);
			return;
		}
		SmsManager manager = SmsManager.getDefault();
		manager.sendTextMessage(number, null, message, null, null);
	}
}
