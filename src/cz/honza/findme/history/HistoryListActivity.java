package cz.honza.findme.history;

import cz.honza.findme.FindMeApplication;
import cz.honza.findme.FindmeActivity;
import cz.honza.findme.R;
import android.content.Context;
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
        setContentView(R.layout.history);
        final ListView list = (ListView)findViewById(R.id.history_list);
        
        mAdapter = new CursorAdapter(this, FindMeApplication.sDbHelper.select(), true) {
        	
			@Override
			public View newView(Context context, Cursor cursor, ViewGroup parent) {
				LayoutInflater inflater = (LayoutInflater)context.getSystemService
					      (Context.LAYOUT_INFLATER_SERVICE);
				View v = inflater.inflate(R.layout.history_item, null);
				bindView(v, context, cursor);
				return v;
			}
			
			@Override
			public void bindView(View view, Context context, Cursor cursor) {
				final TextView number = (TextView)view.findViewById(R.id.history_item_number);
				final TextView message = (TextView)view.findViewById(R.id.history_item_message);
				final TextView direction = (TextView)view.findViewById(R.id.history_item_dirction);
				final HistoryItem hi = HistoryItem.fromCursor(cursor);
				number.setText(hi.mNumber);
				message.setText(hi.mSms);
				direction.setText(hi.mOutgoing ? R.string.outgoing : R.string.incoming);
			}
		};
		
		list.setAdapter(mAdapter);
    }
}
