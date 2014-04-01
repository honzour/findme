package cz.honza.findme.history;

import cz.honza.findme.FindMeApplication;
import cz.honza.findme.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HistoryDetailActivity extends Activity {
	public static final String EXTRA_ID = "EXTRA_ID";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_detail);
		Bundle extras = getIntent().getExtras();
		if (extras == null)
			return;
		long id = extras.getLong(EXTRA_ID, -1);
		HistoryItem item = FindMeApplication.sDbHelper.select(id);
		
		final TextView number = (TextView)findViewById(R.id.history_detail_number);
		final TextView message = (TextView)findViewById(R.id.history_detail_message);
		final TextView direction = (TextView)findViewById(R.id.history_detail_dirction);
		
		number.setText(item.mNumber);
		message.setText(item.mSms);
		direction.setText(item.mOutgoing ? R.string.outgoing : R.string.incoming);
		
	}

}
