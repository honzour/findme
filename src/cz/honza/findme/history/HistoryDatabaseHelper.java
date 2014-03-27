package cz.honza.findme.history;

import cz.honza.findme.FindMeApplication;
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
}
