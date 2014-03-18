package cz.honza.findme;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class SettingsActivity extends FindmeActivity {
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		CheckBox processSms = (CheckBox) findViewById(R.id.process_outgoing_sms);
		processSms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				SharedPreferences prefs = Util.getPreferences();
				SharedPreferences.Editor e = prefs.edit();
				e.putBoolean(Preferences.SETTINGS_PROCESS_SMS, isChecked);
				e.commit();
			}
			
		});
		
		setStartActivityView(R.id.fake_sms, FakeSMSActivity.class);
	}
	
	
}
