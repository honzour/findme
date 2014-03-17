package cz.honza.findme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

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
	      allOur = Handling.handleSMS(from, message) && allOur; 
	    }
	    
	    if (allOur)
	    	abortBroadcast();
	}

}
