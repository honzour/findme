package cz.honza.findme;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.app.Activity;
import android.content.Context;

public class AskActivity extends Activity {

	private static class SendCallback implements Position.Callback
	{
		String mNumber;
		
		public SendCallback(String number)
		{
			mNumber = number;
		}
		
		@Override
		public void onTimeout() {
			Util.toast(R.string.gps_timeout);
		}
		
		@Override
		public void onMyPositionFound(Location location) {
			String url = FindMeUrl.createAskUrl(true, location);
			readyToSendSms(url, mNumber);
		}
		
		@Override
		public void onError(int errorCode) {
			Util.toast(R.string.gps_disabled);

		}
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask);
        
        LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
        	findViewById(R.id.ask_attach).setEnabled(false);
        }
        
        final View askSms = findViewById(R.id.ask_sms);
        askSms.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final EditText number = (EditText) findViewById(R.id.ask_number);
				final CheckBox attach = (CheckBox) findViewById(R.id.ask_attach);
				
				String numberValue = number.getText().toString();
				
				if (attach.isChecked())
				{
					Position.findMyPosition(60, new SendCallback(numberValue));
				}
				else
				{
					String url = FindMeUrl.createAskUrl(true, null);
					readyToSendSms(url, number.getText().toString());
				}
				finish();
			}
		});
    }
    
    private static void readyToSendSms(String url, String number)
    {
    	Util.toast("Send SMS to " + number + " " + url);
    }
}
