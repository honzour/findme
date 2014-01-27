package cz.honza.findme;

import java.util.Locale;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class MainActivity extends Activity {
	
	LocationManager mlocManager;
	LocationListener mlocListener;
	Location mLocation = null;
	
	TextView mLongitude;
	TextView mLatitude;
	TextView mAltitude;
	TextView mAccuracy;
	
	protected void toast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final View findMeButton = findViewById(R.id.main_find_me);
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
			}

			@Override
			public void onProviderDisabled(String provider) {
				toast("onProviderDisabled");
			}

			@Override
			public void onProviderEnabled(String provider) {
				toast("onProviderEnabled");
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				toast("onStatusChanged");
			}
	    	
	    };

        
        findMeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			    mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
			}
		});
        
        openButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			    if (mLocation == null)
			    	return;
			    String uri = String.format(Locale.ENGLISH, "geo:%f,%f", mLocation.getLatitude(), mLocation.getLongitude());
			    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
			    MainActivity.this.startActivity(intent);
			}
		});
    }

	@Override
	protected void onDestroy() {
		mlocManager.removeUpdates(mlocListener);
		super.onDestroy();
	}
}
