package cz.honza.findme;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.content.SharedPreferences;

public class MyPositionActivity extends FindmeActivity {
	
	protected LocationManager mlocManager;
	protected LocationListener mlocListener;
	protected Location mLocation = null;
	
	protected TextView mLongitude;
	protected TextView mLatitude;
	protected TextView mAltitude;
	protected TextView mAccuracy;
	
	protected static final String LAST_POSITION_PREFS = "LAST_POSITION_PREFS";
	protected static final String LAST_POSITION_LONG = "LAST_POSITION_LONG";
	protected static final String LAST_POSITION_LAT = "LAST_POSITION_LAT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_position);
        
        final View openButton = findViewById(R.id.main_open_last);
        
        mLongitude = (TextView) findViewById(R.id.main_longitude);
        mLatitude = (TextView) findViewById(R.id.main_latitude);
        mAltitude = (TextView) findViewById(R.id.main_altitude);
        mAccuracy = (TextView) findViewById(R.id.main_accuracy);
        
        mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	mlocListener = new LocationListener()
	    {
			@Override
			public void onLocationChanged(Location location) {
				mLongitude.setText(String.valueOf(location.getLongitude()));
				mLatitude.setText(String.valueOf(location.getLatitude()));
				mAltitude.setText(String.valueOf(location.getAltitude()));
				mAccuracy.setText(String.valueOf(location.getAccuracy()));
			
				mLocation = location;
				
				SharedPreferences prefs = getSharedPreferences(LAST_POSITION_PREFS, Context.MODE_PRIVATE);
				SharedPreferences.Editor e = prefs.edit();
				e.putString(LAST_POSITION_LONG, String.valueOf(location.getLongitude()));
				e.putString(LAST_POSITION_LAT, String.valueOf(location.getLatitude()));
				e.commit();
			}

			@Override
			public void onProviderDisabled(String provider) {
				Util.toast(R.string.gps_disabled);
				mlocManager.removeUpdates(mlocListener);
			}

			@Override
			public void onProviderEnabled(String provider) {
			}

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
			}
	    	
	    };

        
        
        openButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				double lon, lat;
			    if (mLocation == null)
			    {
			    	SharedPreferences prefs = getSharedPreferences(LAST_POSITION_PREFS, Context.MODE_PRIVATE);
			    	lat = Double.valueOf(prefs.getString(LAST_POSITION_LAT, "49.98480"));
			    	lon = Double.valueOf(prefs.getString(LAST_POSITION_LONG, "14.360617"));
			    	
			    }
			    else
			    {
			    	lon = mLocation.getLongitude();
			    	lat = mLocation.getLatitude();
			    }
			    
			    Util.openInMap(lon, lat, MyPositionActivity.this);
			}
		});
    }

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if (mlocListener != null && mlocManager != null)
			mlocManager.removeUpdates(mlocListener);
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
        if (!mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
        	Util.toast(R.string.gps_disabled);
        }
        else
        {
        	mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        }
	}
}
