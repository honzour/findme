package cz.honza.findme;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowPositionActivity extends Activity {

	public static final String EXTRA_LON = "EXTRA_LON";
	public static final String EXTRA_LAT = "EXTRA_LAT";
	public static final String EXTRA_TIME = "EXTRA_TIME";
	public static final String EXTRA_NUMBER = "EXTRA_NUMBER";
	public static final String EXTRA_CAPTION = "EXTRA_CAPTION";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_position);
		Bundle extra = getIntent().getExtras();
		if (extra == null)
			return;
		String caption = extra.getString(EXTRA_CAPTION);
		double lon = extra.getDouble(EXTRA_LON, -1);
		double lat = extra.getDouble(EXTRA_LAT, -1);
		String number = extra.getString(EXTRA_NUMBER);
		Calendar time = (Calendar)extra.getSerializable(EXTRA_TIME);
		
		TextView captionTv = (TextView)findViewById(R.id.caption);
		TextView lonTv = (TextView)findViewById(R.id.longitude);
		TextView latTv = (TextView)findViewById(R.id.latitude);
		TextView numberTv = (TextView)findViewById(R.id.number);
		TextView timeTv = (TextView)findViewById(R.id.time);
		
		captionTv.setText(caption);
		numberTv.setText(number);
		timeTv.setText(new SimpleDateFormat(getResources().getString(R.string.time_format)).format(time.getTime()));
		lonTv.setText(String.valueOf(lon));
		latTv.setText(String.valueOf(lat));
	}

}
