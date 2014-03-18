package cz.honza.findme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ConfirmSMSActivity extends Activity {
	public static final String EXTRA_TO = "EXTRA_TO"; 
	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	public static final String EXTRA_AUTOREPLY = "EXTRA_AUTOREPLY";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_sms);
		
		View dontSend = findViewById(R.id.do_not_send);
		dontSend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		Bundle extras = getIntent().getExtras(); 
		
		if (extras == null)
			return;
		
		final String to = extras.getString(EXTRA_TO);
		final String message = extras.getString(EXTRA_MESSAGE);
		final boolean autoreply = extras.getBoolean(EXTRA_AUTOREPLY);
		
		View progress = findViewById(R.id.progress);
		if (!autoreply)
			progress.setVisibility(View.GONE);
		
		View send = findViewById(R.id.send);
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Util.sendSMS(to, message, true);
				finish();
			}
		});
	}
	
	
}
