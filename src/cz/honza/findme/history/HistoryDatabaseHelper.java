package cz.honza.findme.history;

import cz.honza.findme.FindMeApplication;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryDatabaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "History";
	private static final int DB_VERSION = 1;
	private static final String DB_TABLE_HISTORY = "History";
	private static final String DB_TABLE_HISTORY_COLUMN_ID = "id";
	private static final String DB_TABLE_HISTORY_COLUMN_NUMBER = "number";
	private static final String DB_TABLE_HISTORY_COLUMN_SMS = "sms";
	private static final String DB_TABLE_HISTORY_COLUMN_TIME = "time";
	private static final String DB_TABLE_HISTORY_COLUMN_OUTGOING = "outgoing";
	
	static final int DB_TABLE_HISTORY_COLUMN_ID_INDEX = 0;
	static final int DB_TABLE_HISTORY_COLUMN_NUMBER_INDEX = 1;
	static final int DB_TABLE_HISTORY_COLUMN_SMS_INDEX = 2;
	static final int DB_TABLE_HISTORY_COLUMN_TIME_INDEX = 3;
	static final int DB_TABLE_HISTORY_COLUMN_OUTGOING_INDEX = 4;
	
	public static String[] columns = {
		DB_TABLE_HISTORY_COLUMN_ID,
		DB_TABLE_HISTORY_COLUMN_NUMBER,
		DB_TABLE_HISTORY_COLUMN_SMS,
        DB_TABLE_HISTORY_COLUMN_TIME,
        DB_TABLE_HISTORY_COLUMN_OUTGOING};

	public HistoryDatabaseHelper() {
		super(FindMeApplication.sInstance, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_TABLE_HISTORY + " ("
                + DB_TABLE_HISTORY_COLUMN_ID + " INTEGER PRIMARY KEY,"
                + DB_TABLE_HISTORY_COLUMN_NUMBER + " TEXT, "
                + DB_TABLE_HISTORY_COLUMN_SMS + " TEXT, "
                + DB_TABLE_HISTORY_COLUMN_TIME + " INTEGER, "
                + DB_TABLE_HISTORY_COLUMN_OUTGOING + " INTEGER"
                + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// no upgrade may happen
	}
	
    public Cursor select() {
    	final SQLiteDatabase db = getReadableDatabase();
        final Cursor c = db.query(DB_TABLE_HISTORY, columns, null, null, null, null, DB_TABLE_HISTORY_COLUMN_ID);
        if (!c.moveToFirst())
        {
        	c.close();
        	return null;
        }
        return c;
    }
    
    public HistoryItem select(long id) {
    	final SQLiteDatabase db = getReadableDatabase();
        final String[] selectionArgs = {String.valueOf(id)};
        Cursor c = db.query(DB_TABLE_HISTORY, columns, DB_TABLE_HISTORY_COLUMN_ID + "= ?", selectionArgs,
                null, null, null);
        if (c == null)
        	return null;
        HistoryItem item = null;
        if (c.moveToFirst())
        {
        	item = HistoryItem.fromCursor(c);
        }
        c.close();
        return item;
    }
    
    public long insert(HistoryItem item) {
    	final SQLiteDatabase db = getWritableDatabase();

    	final ContentValues values = new ContentValues();
        values.put(DB_TABLE_HISTORY_COLUMN_NUMBER, item.mNumber);
        values.put(DB_TABLE_HISTORY_COLUMN_SMS, item.mSms);
        values.put(DB_TABLE_HISTORY_COLUMN_TIME, item.mTime);
        values.put(DB_TABLE_HISTORY_COLUMN_OUTGOING, (item.mOutgoing ? 1 : 0));
        

        long id = db.insert(DB_TABLE_HISTORY, null, values);
        db.close();
        return id;
    }
    
    public boolean delete(long id) {
    	final SQLiteDatabase db = getWritableDatabase();
    	final String[] selectionArgs = { String.valueOf(id) };

    	final int deletedCount = db.delete(DB_TABLE_HISTORY, DB_TABLE_HISTORY_COLUMN_ID + "= ?", selectionArgs);
        db.close();
        return deletedCount > 0;
    }
}
