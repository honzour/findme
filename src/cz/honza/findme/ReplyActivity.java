package cz.honza.findme;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;

public class ReplyActivity extends Activity {
	
	public static final String REPLY_SETTINGS = "REPLY_SETTINGS";
	public static final String REPLY_SETTINGS_MODE = "REPLY_SETTINGS_MODE";
	public static final String REPLY_SETTINGS_ALLOW_SMS = "REPLY_SETTINGS_ALLOW_SMS";
	public static final String REPLY_SETTINGS_ALLOW_INTERNET = "REPLY_SETTINGS_ALLOW_INTERNET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reply);
        
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

				switch (item)
				{
				case 0:
					modeInfo.setText(R.string.reply_mode_reply_all);
					break;
				case 1:
					modeInfo.setText(R.string.reply_mode_do_not_reply);
					break;
				case 2:
					modeInfo.setText(R.string.reply_mode_reply_secret);
					break;
				default:
					modeInfo.setText("");
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
        	
		});

        mode.setSelection(1);
        
    }
}
