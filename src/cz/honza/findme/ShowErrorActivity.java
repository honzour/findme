package cz.honza.findme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowErrorActivity extends FindmeActivity {
	public static final String EXTRA_CAPTION = "EXTRA_CAPTION";
	public static final String EXTRA_TEXT = "EXTRA_TEXT";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.show_error);
		
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		
		if (extras == null)
			return;
		
		final String caption = extras.getString(EXTRA_CAPTION);
		if (caption != null) 
		{
			setTitle(caption);
			TextView captionView = (TextView)findViewById(R.id.caption);
			captionView.setText(caption);
		}
		
		final String text = extras.getString(EXTRA_TEXT);
		if (text != null) 
		{
			TextView textView = (TextView)findViewById(R.id.text);
			textView.setText(text);
		}
	}
}
