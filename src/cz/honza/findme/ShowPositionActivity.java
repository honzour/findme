package cz.honza.findme;

import android.app.Activity;
import android.os.Bundle;

public class ShowPositionActivity extends Activity {

	public static final String EXTRA_LON = "EXTRA_LON";
	public static final String EXTRA_LAT = "EXTRA_LAT";
	public static final String EXTRA_FROM = "EXTRA_FROM";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_position);
	}

}
