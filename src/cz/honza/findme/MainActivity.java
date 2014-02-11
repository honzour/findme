package cz.honza.findme;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final View myPosition = findViewById(R.id.my_position);
        
        myPosition.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, MyPositionActivity.class));
			}
		});
        
        final View ask = findViewById(R.id.ask_settings);
        
        ask.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, AskActivity.class));
			}
		});
        
        final View reply = findViewById(R.id.reply_settings);
        
        reply.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, ReplyActivity.class));
			}
		});
        
    }
}
