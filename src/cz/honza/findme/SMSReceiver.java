package cz.honza.findme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

	boolean handleSMS(String from, String message)
	{
		String url = FindMeUrl.urlFromSms(message);
		if (url == null)
		{
			Util.toast("SMS is not our");
			return false;
		}
		else
		{
			Util.toast("our SMS" + url);
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
	    
	  /*  if (allOur)
	    	abortBroadcast(); - for sure */
	}

}
