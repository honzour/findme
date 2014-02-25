package cz.honza.findme;

import android.app.Application;

public class FindMeApplication extends Application {

	public static FindMeApplication sInstance;
	
	@Override
	public void onCreate() {
		sInstance = this;
		super.onCreate();
	}

}
