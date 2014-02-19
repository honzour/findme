package cz.honza.findme;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

public class AskActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask);
        
        final View askSms = findViewById(R.id.ask_sms);
        askSms.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final EditText number = (EditText) findViewById(R.id.ask_number);
				final CheckBox attach = (CheckBox) findViewById(R.id.ask_attach);
				
				String url = "findme:ask?v=1&n=" + number.getText() + "&a=" + (attach.isChecked() ? '1' : '0') + " ";
				
				Toast.makeText(AskActivity.this, url, Toast.LENGTH_LONG).show();
				finish();
			}
		});
    }
}
