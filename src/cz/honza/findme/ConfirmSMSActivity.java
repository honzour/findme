package cz.honza.findme;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ConfirmSMSActivity extends Activity {
	public static final String EXTRA_TO = "EXTRA_TO"; 
	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	public static final String EXTRA_AUTOREPLY = "EXTRA_AUTOREPLY";
	
	public static final String STATE_PROGRESS = "STATE_PROGRESS";
	
	private Handler mHandler;
	private Runnable mAutoreplyStep = null;
	private int mAutoreplyTimer = 0;
	private static final int STEP_MS = 1000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_sms);
		
		mAutoreplyTimer = (savedInstanceState == null ? 0 : savedInstanceState.getInt(STATE_PROGRESS, 0));
		
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
		
		final TextView toView = (TextView)findViewById(R.id.number);
		final TextView messageView = (TextView)findViewById(R.id.message);
		toView.setText(to);
		messageView.setText(message);
		
		final boolean autoreply = extras.getBoolean(EXTRA_AUTOREPLY);
		
		final ProgressBar progress = (ProgressBar)findViewById(R.id.progress);

		if (autoreply)
		{
			mHandler = new Handler();
			mAutoreplyStep =  new Runnable() {
				@Override
				public void run() {
					if (mAutoreplyTimer <= Settings.autoreplyTimeout)
					{
						mHandler.postDelayed(mAutoreplyStep, STEP_MS);
						progress.setProgress(++mAutoreplyTimer);
					}
					else
					{
						Util.sendSMS(to, message, true);
						finish();
					}
				}
			};
			progress.setMax(Settings.autoreplyTimeout);
			progress.setProgress(mAutoreplyTimer);
			mHandler.postDelayed(mAutoreplyStep, STEP_MS);
		}
		else
		{
			progress.setVisibility(View.GONE);
		}
		
		View send = findViewById(R.id.send);
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Util.sendSMS(to, message, true);
				finish();
			}
		});
		
		
	}
	
	

	@Override
	protected void onDestroy() {
		if (mHandler != null)
			mHandler.removeCallbacksAndMessages(null);
		super.onDestroy();
	}



	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_PROGRESS, mAutoreplyTimer);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		
	}
	
	
	
}
