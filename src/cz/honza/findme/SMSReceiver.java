package cz.honza.findme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context content, Intent intent) {
		Bundle extras = intent.getExtras();

	    Object[] pdus = (Object[])extras.get("pdus");
	    for (Object pdu: pdus)
	    {
	      SmsMessage msg = SmsMessage.createFromPdu((byte[])pdu);

	      String origin = msg.getOriginatingAddress();
	      String body = msg.getMessageBody();

	      Log.e("Prisla SMS", origin + " " + body);
	      
	      /*
	      // Parse the SMS body
	      if (isMySpecialSMS)
	      {
	        // Stop it being passed to the main Messaging inbox
	        abortBroadcast();
	      }
	      */
	    }
	}

}
