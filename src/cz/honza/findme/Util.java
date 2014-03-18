package cz.honza.findme;

import android.content.Context;
import android.content.Intent;
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
	
	protected static void sendSMS(String number, String message, boolean direct)
	{
		if (!direct)
		{
			SharedPreferences prefs = getPreferences();
			boolean processSms = prefs.getBoolean(Preferences.SETTINGS_PROCESS_SMS,
				Preferences.SETTINGS_PROCESS_SMS_DEFAULT);
			if (processSms)
			{
				Intent intent = new Intent(FindMeApplication.sInstance, ConfirmSMSActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra(ConfirmSMSActivity.EXTRA_AUTOREPLY, false);
				intent.putExtra(ConfirmSMSActivity.EXTRA_MESSAGE, message);
				intent.putExtra(ConfirmSMSActivity.EXTRA_TO, number);
				FindMeApplication.sInstance.startActivity(intent);
				return;
			}
		}
		SmsManager manager = SmsManager.getDefault();
		manager.sendTextMessage(number, null, message, null, null);
	}
}
