package cz.honza.findme;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;

public class Position {
	
	public interface Callback {
		public void onMyPositionFound(Location location);
		public void onTimeout();
		public void onError(int errorCode);
	}
	
	private static class PositionLocationListener implements LocationListener
	{
		private Handler mTimeoutHandler;
		LocationManager mManager;
		Location mLast;
		Callback mCallback;
		
		public PositionLocationListener(int timeoutS, Callback callback)
		{
			mCallback = callback;
			mTimeoutHandler = new Handler();
			mTimeoutHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					if (mLast == null)
						mCallback.onTimeout();
					else
						mCallback.onMyPositionFound(mLast);
					mManager.removeUpdates(PositionLocationListener.this);
				}
			}, timeoutS * 1000);
			mLast = null;
			mManager = (LocationManager)FindMeApplication.sInstance.getSystemService(Context.LOCATION_SERVICE);
			mManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		}
		
		@Override
		public void onLocationChanged(Location location) {
			if (mLast == null)
			{
				mLast = location;
				return;
			}
			if (location.getAccuracy() >= mLast.getAccuracy())
			{
				mCallback.onMyPositionFound(mLast);
				mTimeoutHandler.removeCallbacksAndMessages(null);
				mManager.removeUpdates(this);
				return;
			}
			
			mLast = location;
		}

		@Override
		public void onProviderDisabled(String provider) {
			mCallback.onError(0);
			mTimeoutHandler.removeCallbacksAndMessages(null);
			mManager.removeUpdates(this);
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status,
				Bundle extras) {
		}

	}
	
	public static void findMyPosition(int timeoutS, Callback callback)
	{
		new PositionLocationListener(timeoutS, callback);
	}
}
