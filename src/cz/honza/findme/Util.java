package cz.honza.findme;

import android.widget.Toast;

public class Util {
	public static void toast(String s)
	{
		Toast.makeText(FindMeApplication.sInstance, s, Toast.LENGTH_LONG).show();
	}
	
	public static void toast(int res)
	{
		Toast.makeText(FindMeApplication.sInstance, res, Toast.LENGTH_LONG).show();
	}
}
