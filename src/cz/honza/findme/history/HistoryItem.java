package cz.honza.findme.history;

import android.database.Cursor;

public class HistoryItem {
	public String mNumber;
	public String mSms;
	public long mTime;
	public boolean mOutgoing;
	
	public HistoryItem(String number, String sms, long time,
			boolean outgoing) {
		
		mNumber = number;
		mSms = sms;
		mTime = time;
		mOutgoing = outgoing;
	}
	
	public static HistoryItem fromCursor(Cursor cursor)
	{
		if (cursor == null)
			return null;
		final String number = cursor.getString(HistoryDatabaseHelper.DB_TABLE_HISTORY_COLUMN_NUMBER_INDEX);
		final String sms = cursor.getString(HistoryDatabaseHelper.DB_TABLE_HISTORY_COLUMN_SMS_INDEX);
		final long time = cursor.getLong(HistoryDatabaseHelper.DB_TABLE_HISTORY_COLUMN_TIME_INDEX);
		final int outgoing = cursor.getInt(HistoryDatabaseHelper.DB_TABLE_HISTORY_COLUMN_OUTGOING_INDEX);

		return new HistoryItem(number, sms, time, outgoing != 0);
	}
}
