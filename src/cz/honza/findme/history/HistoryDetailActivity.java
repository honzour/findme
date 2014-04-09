package cz.honza.findme.history;

import cz.honza.findme.FindMeApplication;
import cz.honza.findme.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
		final TextView type = (TextView)findViewById(R.id.history_detail_type);
		
		number.setText(item.mNumber);
		message.setText(item.mSms);
		direction.setText(item.mOutgoing ? R.string.outgoing : R.string.incoming);
		time.setText(item.getTimeString());
		type.setText(HistoryItem.getTypeResource(item.getType()));
		
		final Button button1 = (Button)findViewById(R.id.history_detail_button1);
		final Button button2 = (Button)findViewById(R.id.history_detail_button2);

		if (item.mOutgoing)
		{
			button1.setText(R.string.send_again);
			if (item.getType() == HistoryItem.TYPE_REPLY)
			{
				button2.setVisibility(View.VISIBLE);
				button2.setText(R.string.show_position);
			}
		}
		else // incoming
		{
		
			switch (item.getType())
			{
			case HistoryItem.TYPE_ASK:
				button1.setText(R.string.handle_again);
				break;
			case HistoryItem.TYPE_REPLY:
				button1.setText(R.string.show_again);
				break;
			default:
				button1.setVisibility(View.GONE);
				break;
			}
		}
	}
}
