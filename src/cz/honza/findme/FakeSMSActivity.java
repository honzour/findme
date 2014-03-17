package cz.honza.findme;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FakeSMSActivity extends FindmeActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fake_sms);
        
        final TextView number = (TextView) findViewById(R.id.from_number);
        final TextView message = (TextView) findViewById(R.id.message);
        
        final View send = findViewById(R.id.receive_sms);
        
        send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Handling.handleSMS(number.getText().toString(), message.getText().toString());
			}
        	
        });
    }
}
