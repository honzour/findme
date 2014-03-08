package cz.honza.findme;

import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;

public class Handling {
	
	protected static void handleAsk(final String from, Uri uri)
	{
		tryToHandleIncomingPosition(from, uri, null);
		
		SharedPreferences prefs = FindMeApplication.sInstance.getSharedPreferences(Preferences.PRORAM_SETTINGS, 
				Context.MODE_PRIVATE);
		
		int replyMode = prefs.getInt(Preferences.REPLY_SETTINGS_MODE, Preferences.REPLY_SETTINGS_MODE_DEFAULT);
		switch (replyMode)
		{
		case Preferences.REPLY_SETTINGS_MODE_DO_NOT_REPLY:
			Position.findMyPosition(Settings.gpsTimeout, new Position.Callback() {
				
				@Override
				public void onTimeout() {
					sendReply(from, null);					
				}
				
				@Override
				public void onMyPositionFound(Location location) {
					sendReply(from, location);
				}
				
				@Override
				public void onError(int errorCode) {
					sendReply(from, null);
				}
			});
			break;
		case Preferences.REPLY_SETTINGS_MODE_REPLY_SECRET:
			break;
		case Preferences.REPLY_SETTINGS_MODE_REPLY_ALL:
			break;
		}			
	}
	
	protected static void handleReply(final String from, Uri uri)
	{
		tryToHandleIncomingPosition(from, uri, new Runnable() {
			@Override
			public void run() {
				Util.toast(R.string.reply_without_location);
			}
		});
	}
	
	public static void handleUri(final String from, Uri uri)
	{
		String host = uri.getHost();
		if (host.equals(FindMeUrl.ACTION_ASK))
		{
			handleAsk(from, uri);
		}
		
		if (host.equals(FindMeUrl.ACTION_REPLY))
		{
			handleReply(from, uri);
			return;
		}
	}
	
	public static void handleIncomingPosition(String action, String from, double lon, double lat)
	{
		int actionResource = R.string.incoming_position;
		if (action.equals(FindMeUrl.ACTION_ASK))
			actionResource = R.string.incoming_position_ask;
		if (action.equals(FindMeUrl.ACTION_REPLY))
			actionResource = R.string.incoming_position_reply;
		Intent intent = new Intent(FindMeApplication.sInstance, ShowPositionActivity.class);
		intent.putExtra(ShowPositionActivity.EXTRA_CAPTION, FindMeApplication.sInstance.getResources().getString(actionResource));
		intent.putExtra(ShowPositionActivity.EXTRA_LON, lon);
		intent.putExtra(ShowPositionActivity.EXTRA_LAT, lat);
		intent.putExtra(ShowPositionActivity.EXTRA_NUMBER, from);
		intent.putExtra(ShowPositionActivity.EXTRA_TIME, Calendar.getInstance());
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		FindMeApplication.sInstance.startActivity(intent);
	}
	
	protected static void tryToHandleIncomingPosition(String from, Uri uri, Runnable onError)
	{
		String host = uri.getHost();
		String lon = uri.getQueryParameter(String.valueOf(FindMeUrl.CHAR_LONGITUDE));
		String lat = uri.getQueryParameter(String.valueOf(FindMeUrl.CHAR_LATITUDE));
		if (lon != null && lat != null)
		{
			try
			{
				double lond = Double.valueOf(lon);
				double latd = Double.valueOf(lat);
				handleIncomingPosition(host, from, lond, latd);
				return;
			}
			catch (NumberFormatException e)
			{
				// ignore
			}
		}
		if (onError != null)
		{
			// TODO
		}
	}
	
	protected static void sendReply(String to, Location location)
	{
		String message = FindMeUrl.createReplyUrl(true, location);
		Util.sendSMS(to, message);
	}
}
