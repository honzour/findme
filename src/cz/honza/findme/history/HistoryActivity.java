package cz.honza.findme.history;

import cz.honza.findme.FindMeApplication;
import cz.honza.findme.FindmeActivity;
import cz.honza.findme.R;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class HistoryActivity extends FindmeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        Cursor c = FindMeApplication.sDbHelper.select();
        if (c != null)
        {
        	try {
        	do {
        		HistoryItem item = HistoryItem.fromCursor(c);
        		Log.i("es em es", item.mNumber + " " + item.mSms);
        	} while (c.moveToNext());
        	c.close();
        	} catch (Exception e)
        	{
        		e.printStackTrace();
        	}
        }
    }
}
