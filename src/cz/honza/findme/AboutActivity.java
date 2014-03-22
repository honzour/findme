package cz.honza.findme;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends FindmeActivity {
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        final TextView program = (TextView) findViewById(R.id.about_program);
        String version;
        try {
        	version = getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName; 
        } catch (Exception e)
        {
        	version = "";
        }
        
        
        program.setText(getResources().getText(R.string.app_name) + " " + version);
        
    }
}
