package cz.honza.findme;

import android.os.Bundle;

public class MainActivity extends FindmeActivity {
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setStartActivityView(R.id.my_position, MyPositionActivity.class);
        setStartActivityView(R.id.ask_settings, AskActivity.class);
        setStartActivityView(R.id.reply_settings, ReplyActivity.class);
        setStartActivityView(R.id.settings, SettingsActivity.class);
    }
}
