package cz.honza.findme;

public class Preferences {
	// preferences name
	public static final String PRORAM_SETTINGS = "PRORAM_SETTINGS";
	
	// REPLY_SETTINGS_MODE
	public static final String REPLY_SETTINGS_MODE = "REPLY_SETTINGS_MODE";
	public static final int REPLY_SETTINGS_MODE_REPLY_ALL = 0;
	public static final int REPLY_SETTINGS_MODE_DO_NOT_REPLY = 1;
	public static final int REPLY_SETTINGS_MODE_REPLY_SECRET = 2;
	public static final int REPLY_SETTINGS_MODE_DEFAULT = REPLY_SETTINGS_MODE_REPLY_ALL;
		
	public static final String REPLY_SETTINGS_ASK_SMS = "REPLY_SETTINGS_ASK_SMS";
	public static final boolean REPLY_SETTINGS_ASK_SMS_DEFAULT = true;
		
	public static final String REPLY_SETTINGS_ASK_INTERNET = "REPLY_SETTINGS_ASK_INTERNET";
	
	public static final String REPLY_SETTINGS_ASK_AUTOREPLY = "REPLY_SETTINGS_ASK_AUTOREPLY";
	public static final boolean REPLY_SETTINGS_ASK_AUTOREPLY_DEFAULT = true;
	
	public static final String REPLY_SETTINGS_PROCESS_SMS = "REPLY_SETTINGS_PROCESS_SMS";
	public static final boolean REPLY_SETTINGS_PROCESS_SMS_DEFAULT = false;
}
