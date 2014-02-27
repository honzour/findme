package cz.honza.findme;

import android.location.Location;
import android.net.Uri;

public class FindMeUrl {
	private static final String URL_START = "findme://";
	
	private static final String ACTION_ASK = "ask";
	private static final String ACTION_REPLY = "reply";
	private static final char CHAR_VERSION = 'v';
	private static final char CHAR_LONGITUDE = 'o';
	private static final char CHAR_LATITUDE = 'a';
	
	private static final int PROTOCOL_VERSION = 1;
	
	public static Uri getValidUri(String url)
	{
		try
		{
			Uri u = Uri.parse(url);
			String host = u.getHost();
			
			int version = Integer.valueOf(u.getQueryParameter(String.valueOf(CHAR_VERSION)));
			
			if ((host.equals(ACTION_ASK) || host.equals(ACTION_REPLY)) && version == 1)
			{
				return u;
			}
			
		} catch(Exception e)
		{
			return null;
		}
		return null;
	}
	
	public static Uri uriFromSms(String sms)
	{
		if (!sms.contains(URL_START))
			return null;
		String[] urls = sms.split(URL_START);
		for (int i = 1; i < urls.length; i++)
		{
			String url = URL_START + urls[i].split(" ")[0];
			
			Uri u = getValidUri(url);
			if (u != null)
			{
				return u;
			}
		}
		return null;
		
	}
	
	public static void handleReply(String from, double lon, double lat)
	{
		// TODO
	}
	
	
	public static void handleUri(String from, Uri uri)
	{
		String host = uri.getHost();
		if (host.equals(ACTION_ASK))
		{
			String lon = uri.getQueryParameter(String.valueOf(CHAR_LONGITUDE));
			String lat = uri.getQueryParameter(String.valueOf(CHAR_LATITUDE));
			if (lon != null && lat != null)
			{
				try
				{
					double lond = Double.valueOf(lon);
					double latd = Double.valueOf(lat);
					handleReply(from, lond, latd);
				}
				catch (NumberFormatException e)
				{
					// ignore
				}
			}
			Position.findMyPosition(300, new Position.Callback() {
				
				@Override
				public void onTimeout() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onMyPositionFound(Location location) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onError(int errorCode) {
					// TODO Auto-generated method stub
					
				}
			});
			return;
		}
		//Util.toast("handleUri(" + from + ", " + uri + "");
	}
	
	private static String urlEncode(String param)
	{
		// TODO
		return param;
	}
	
	private static void addParam(StringBuffer sb, boolean first, char param, String value)
	{
		sb.append(first ? '?' : '&');
		sb.append(param);
		sb.append('=');
		sb.append(urlEncode(value));
	}
	
	public static String createAskUrl(boolean sms, Location myLocation)
	{
		StringBuffer sb = new StringBuffer(URL_START);
		sb.append(ACTION_ASK);
		addParam(sb, true, CHAR_VERSION, String.valueOf(PROTOCOL_VERSION));
		if (myLocation != null)
		{
			addParam(sb, true, CHAR_LONGITUDE, String.valueOf(myLocation.getLongitude()));
			addParam(sb, true, CHAR_LATITUDE, String.valueOf(myLocation.getLatitude()));
		}
		if (sms)
			sb.append(' ');
		return sb.toString();
	}
}
