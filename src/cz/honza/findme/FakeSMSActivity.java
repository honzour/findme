package cz.honza.findme;

import android.os.Bundle;

public class FakeSMSActivity extends FindmeActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fake_sms);
    }
}
