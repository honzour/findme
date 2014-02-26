package cz.honza.findme;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class AskActivity extends Activity {

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
				
				if (attach.isChecked())
				{
					Position.findMyPosition(60, new Position.Callback() {
						
						@Override
						public void onTimeout() {
							Toast.makeText(FindMeApplication.sInstance, R.string.gps_timeout, Toast.LENGTH_LONG).show();
						}
						
						@Override
						public void onMyPositionFound(Location location) {
							String url = "findme:ask?v=1&n=" + number.getText() + "&p=" + location.toString();
							Toast.makeText(FindMeApplication.sInstance, url, Toast.LENGTH_LONG).show();
						}
						
						@Override
						public void onError(int errorCode) {
							Toast.makeText(FindMeApplication.sInstance, R.string.gps_disabled, Toast.LENGTH_LONG).show();	
						}
					});
				}
				else
				{
					String url = "findme:ask?v=1&n=" + number.getText();
				
					Toast.makeText(AskActivity.this, url, Toast.LENGTH_LONG).show();
				}
				finish();
			}
		});
    }
}
