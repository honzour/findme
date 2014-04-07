package cz.honza.findme.history;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		final TextView time = (TextView)findViewById(R.id.history_detail_time);
		
		number.setText(item.mNumber);
		message.setText(item.mSms);
		direction.setText(item.mOutgoing ? R.string.outgoing : R.string.incoming);

		Date date = new Date(item.mTime * 1000);
		String dateText = new SimpleDateFormat(getResources().getString(R.string.time_format)).format(date);
		time.setText(dateText);
		
	}

}
