package cz.honza.findme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

	boolean handleSMS(String from, String message)
	{
		Uri uri = FindMeUrl.uriFromSms(message);
		if (uri == null)
		{
			return false;
		}
		else
		{
			FindMeUrl.handleUri(from, uri);
			return true;
		}
	}
	
	@Override
	public void onReceive(Context content, Intent intent) {
		
		SharedPreferences prefs = FindMeApplication.sInstance.getSharedPreferences(Preferences.PRORAM_SETTINGS, Context.MODE_PRIVATE);
		int mode = prefs.getInt(Preferences.REPLY_SETTINGS_MODE, Preferences.REPLY_SETTINGS_MODE_REPLY_ALL);
		
		if (mode == Preferences.REPLY_SETTINGS_MODE_DO_NOT_REPLY)
			return;
		
		Bundle extras = intent.getExtras();

	    Object[] pdus = (Object[])extras.get("pdus");
	    boolean allOur = true;
	    for (Object pdu: pdus)
	    {
	      final SmsMessage msg = SmsMessage.createFromPdu((byte[])pdu);

	      final String message = msg.getMessageBody();
	      String from = msg.getOriginatingAddress();
	      allOur = handleSMS(from, message) && allOur; 
	    }
	    
	    if (allOur)
	    	abortBroadcast();
	}

}
