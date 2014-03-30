package cz.honza.findme;

import cz.honza.findme.history.HistoryDatabaseHelper;
import android.app.Application;

public class FindMeApplication extends Application {

	public static FindMeApplication sInstance;
	public static HistoryDatabaseHelper sDbHelper;
	
	@Override
	public void onCreate() {
		sInstance = this;
		super.onCreate();
		sDbHelper = new HistoryDatabaseHelper();
	}

}
