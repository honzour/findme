package cz.honza.findme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
			Handling.handleUri(from, uri);
			return true;
		}
	}
	
	@Override
	public void onReceive(Context content, Intent intent) {
		
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
