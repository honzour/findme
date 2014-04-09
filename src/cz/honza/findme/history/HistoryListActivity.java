package cz.honza.findme.history;

import cz.honza.findme.FindMeApplication;
import cz.honza.findme.FindmeActivity;
import cz.honza.findme.R;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryListActivity extends FindmeActivity {

	CursorAdapter mAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_list);
        final ListView list = (ListView)findViewById(R.id.history_list);
        
        final Cursor c = FindMeApplication.sDbHelper.select();
        if (c == null || c.getCount() == 0)
        {
        	findViewById(R.id.history_no_record).setVisibility(View.VISIBLE);
        }
        mAdapter = new CursorAdapter(this, c) {
        	
			@Override
			public View newView(Context context, Cursor cursor, ViewGroup parent) {
				LayoutInflater inflater = (LayoutInflater)context.getSystemService
					      (Context.LAYOUT_INFLATER_SERVICE);
				View v = inflater.inflate(R.layout.history_item, null);
				return v;
			}
			
			@Override
			public void bindView(View view, Context context, Cursor cursor) {
				final TextView number = (TextView)view.findViewById(R.id.history_item_number);
				final TextView message = (TextView)view.findViewById(R.id.history_item_message);
				final TextView direction = (TextView)view.findViewById(R.id.history_item_dirction);
				final TextView time = (TextView)view.findViewById(R.id.history_item_time);
				final HistoryItem hi = HistoryItem.fromCursor(cursor);
				number.setText(hi.mNumber);
				message.setText(HistoryItem.getTypeResource(hi.getType()));
				direction.setText(hi.mOutgoing ? R.string.outgoing : R.string.incoming);
				time.setText(hi.getTimeString());
				view.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(HistoryListActivity.this, HistoryDetailActivity.class);
						intent.putExtra(HistoryDetailActivity.EXTRA_ID, hi.mId);
						startActivity(intent);
					}
				});
			}
		};
		
		list.setAdapter(mAdapter);
		
    }
}
