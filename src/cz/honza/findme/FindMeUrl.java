package cz.honza.findme;

import android.location.Location;

public class FindMeUrl {
	private static final String URL_START = "findme:";
	
	private static final String ACTION_ASK = "ask";
	private static final char CHAR_VERSION = 'v';
	private static final char CHAR_LONGITUDE = 'o';
	private static final char CHAR_LATITUDE = 'a';
	
	private static final int PROTOCOL_VERSION = 1;
	
	
	public static String urlFromSms(String sms)
	{
		return null;
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
