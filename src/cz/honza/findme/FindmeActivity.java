package cz.honza.findme;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class FindmeActivity extends Activity {
	protected boolean setStartActivityView(int resource, final Class<?> activity)
	{
		View view = findViewById(resource);
		if (view == null)
			return false;
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(FindmeActivity.this, activity));
			}
		});
		return true;
	}
}
