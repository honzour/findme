package cz.honza.findme;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.SharedPreferences;

public class ReplyActivity extends FindmeActivity {
	


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reply);
        SharedPreferences prefs = Util.getPreferences();
    
        // Spinner mode
        
        final TextView modeInfo = (TextView) findViewById(R.id.reply_mode_info);
        Spinner mode = (Spinner) findViewById(R.id.reply_mode);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.reply_mode, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                
        mode.setAdapter(adapter);

        
        mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int item, long arg3) {
				
				int res;
				
				if (item < 0 || item > 2)
					item = Preferences.REPLY_SETTINGS_MODE_DEFAULT;

				switch (item)
				{
				
				case Preferences.REPLY_SETTINGS_MODE_REPLY_SECRET:
					res = R.string.reply_mode_reply_secret;
					break;
				case Preferences.REPLY_SETTINGS_MODE_DO_NOT_REPLY:
					res = R.string.reply_mode_do_not_reply;
					break;
				case Preferences.REPLY_SETTINGS_MODE_REPLY_ALL:  // or default
				default:
					res = R.string.reply_mode_reply_all;
					break;
				}
				
				modeInfo.setText(res);
				SharedPreferences prefs = getSharedPreferences(Preferences.PRORAM_SETTINGS, MODE_PRIVATE);
				SharedPreferences.Editor e = prefs.edit();
				e.putInt(Preferences.REPLY_SETTINGS_MODE, item);
				e.commit();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
        	
		});
        final int modeValue = prefs.getInt(Preferences.REPLY_SETTINGS_MODE, Preferences.REPLY_SETTINGS_MODE_REPLY_ALL);
        mode.setSelection(modeValue);
        
        
        // Checkbox SMS
        setChekbox(prefs, Preferences.REPLY_SETTINGS_ASK_SMS, R.id.ask_sms, true);
        // Checkbox Internet
        setChekbox(prefs, Preferences.REPLY_SETTINGS_ASK_INTERNET, R.id.ask_internet, false);
        // Checkbox autoreply
        setChekbox(prefs, Preferences.REPLY_SETTINGS_ASK_AUTOREPLY, R.id.ask_autoreply, true);
    }
        
    
    private void setChekbox(SharedPreferences prefs, final String key, int resource, boolean defaultValue) {
    	final CheckBox cb = (CheckBox) findViewById(resource);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SharedPreferences prefs = Util.getPreferences();
				SharedPreferences.Editor e = prefs.edit();
				e.putBoolean(key, isChecked);
				e.commit();
			}
		});
        cb.setChecked(prefs.getBoolean(key, defaultValue));
    }
}
