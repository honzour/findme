package cz.honza.findme.history;

import java.text.SimpleDateFormat;
import java.util.Date;

import cz.honza.findme.FindMeApplication;
import cz.honza.findme.FindMeUrl;
import cz.honza.findme.R;
import android.database.Cursor;
import android.net.Uri;

public class HistoryItem {
	
	public static final int TYPE_UNKNOWN = 0;
	public static final int TYPE_ASK = 1;
	public static final int TYPE_REPLY = 2;
	
	public long mId;
	public String mNumber;
	public String mSms;
	public long mTime;
	public boolean mOutgoing;
	
	public HistoryItem(long id, String number, String sms, long time,
			boolean outgoing) {
		
		mId = id;
		mNumber = number;
		mSms = sms;
		mTime = time;
		mOutgoing = outgoing;
	}
	
	public static HistoryItem fromCursor(Cursor cursor)
	{
		if (cursor == null)
			return null;
		final long id = cursor.getLong(HistoryDatabaseHelper.DB_TABLE_HISTORY_COLUMN_ID_INDEX);
		final String number = cursor.getString(HistoryDatabaseHelper.DB_TABLE_HISTORY_COLUMN_NUMBER_INDEX);
		final String sms = cursor.getString(HistoryDatabaseHelper.DB_TABLE_HISTORY_COLUMN_SMS_INDEX);
		final long time = cursor.getLong(HistoryDatabaseHelper.DB_TABLE_HISTORY_COLUMN_TIME_INDEX);
		final int outgoing = cursor.getInt(HistoryDatabaseHelper.DB_TABLE_HISTORY_COLUMN_OUTGOING_INDEX);

		return new HistoryItem(id, number, sms, time, outgoing != 0);
	}
	
	/* get type of the record
	 *  
	 */
	public int getType()
	{
		try
		{
			final Uri uri = FindMeUrl.uriFromSms(mSms);
			final String host = uri.getHost();
			if (host.equals(FindMeUrl.ACTION_ASK))
			{
				return TYPE_ASK;
			}
		
			if (host.equals(FindMeUrl.ACTION_REPLY))
			{
				return TYPE_REPLY;
			}
		}
		catch (Exception e)
		{
			// ignore
		}
		return TYPE_UNKNOWN;
	}
	
	public static int getTypeResource(int type)
	{
		switch (type)
		{
		case TYPE_ASK:
			return R.string.query;
		case TYPE_REPLY:
			return R.string.reply;
		default:
			return R.string.unknown_type;
		}
	}
	
	public String getTimeString()
	{
		final Date date = new Date(mTime * 1000);
		final String dateText = new SimpleDateFormat(FindMeApplication.sInstance.getResources().getString(R.string.time_format)).format(date);
		return dateText;
	}
}
