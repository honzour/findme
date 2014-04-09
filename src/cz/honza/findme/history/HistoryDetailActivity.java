package cz.honza.findme.history;

import cz.honza.findme.FindMeApplication;
import cz.honza.findme.Handling;
import cz.honza.findme.R;
import cz.honza.findme.Util;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HistoryDetailActivity extends Activity {
	public static final String EXTRA_ID = "EXTRA_ID";
	private HistoryItem mItem;
	
	private View.OnClickListener mHandleUri = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Handling.handleUri(mItem.mNumber, Uri.parse(mItem.mSms));			
		}
	};
	
	private View.OnClickListener mSendAgain = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Util.sendSMS(mItem.mNumber, mItem.mSms, false);			
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_detail);
		Bundle extras = getIntent().getExtras();
		if (extras == null)
			return;
		long id = extras.getLong(EXTRA_ID, -1);
		mItem = FindMeApplication.sDbHelper.select(id);
		
		final TextView number = (TextView)findViewById(R.id.history_detail_number);
		final TextView message = (TextView)findViewById(R.id.history_detail_message);
		final TextView direction = (TextView)findViewById(R.id.history_detail_dirction);
		final TextView time = (TextView)findViewById(R.id.history_detail_time);
		final TextView type = (TextView)findViewById(R.id.history_detail_type);
		
		number.setText(mItem.mNumber);
		message.setText(mItem.mSms);
		direction.setText(mItem.mOutgoing ? R.string.outgoing : R.string.incoming);
		time.setText(mItem.getTimeString());
		type.setText(HistoryItem.getTypeResource(mItem.getType()));
		
		final Button button1 = (Button)findViewById(R.id.history_detail_button1);
		final Button button2 = (Button)findViewById(R.id.history_detail_button2);

		if (mItem.mOutgoing)
		{
			button1.setText(R.string.send_again);
			button1.setOnClickListener(mSendAgain);
			if (mItem.getType() == HistoryItem.TYPE_REPLY)
			{
				button2.setVisibility(View.VISIBLE);
				button2.setText(R.string.show_position);
				button2.setOnClickListener(mHandleUri);
			}
		}
		else // incoming
		{
		
			switch (mItem.getType())
			{
			case HistoryItem.TYPE_ASK:
				button1.setText(R.string.handle_again);
				button1.setOnClickListener(mHandleUri);
				break;
			case HistoryItem.TYPE_REPLY:
				button1.setText(R.string.show_again);
				button1.setOnClickListener(mHandleUri);
				break;
			default:
				button1.setVisibility(View.GONE);
				break;
			}
		}
	}
}
