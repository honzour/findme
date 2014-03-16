package cz.honza.findme;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowPositionActivity extends FindmeActivity {

	public static final String EXTRA_LON = "EXTRA_LON";
	public static final String EXTRA_LAT = "EXTRA_LAT";
	public static final String EXTRA_TIME = "EXTRA_TIME";
	public static final String EXTRA_NUMBER = "EXTRA_NUMBER";
	public static final String EXTRA_CAPTION = "EXTRA_CAPTION";
	
	private double mLon;
	private double mLat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_position);
		Bundle extra = getIntent().getExtras();
		if (extra == null)
			return;
		String caption = extra.getString(EXTRA_CAPTION);
		mLon = extra.getDouble(EXTRA_LON, -1);
		mLat = extra.getDouble(EXTRA_LAT, -1);
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
		lonTv.setText(String.valueOf(mLon));
		latTv.setText(String.valueOf(mLat));
		
		View open = findViewById(R.id.open);
		open.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 String uri = String.format(Locale.ENGLISH, "geo:%f,%f", mLat, mLon);
				 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
				 startActivity(intent);
			}
		});
	}

}
