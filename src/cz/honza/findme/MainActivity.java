package cz.honza.findme;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends Activity {
	
	LocationManager mlocManager;
	LocationListener mlocListener;
	
	protected void toast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final View findMeButton = findViewById(R.id.main_find_me);
        
        mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	    mlocListener = new LocationListener()
	    {
			@Override
			public void onLocationChanged(Location location) {
				toast("onLocationChanged");
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
    }

	@Override
	protected void onDestroy() {
		mlocManager.removeUpdates(mlocListener);
		super.onDestroy();
	}
}
