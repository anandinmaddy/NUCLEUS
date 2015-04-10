package com.nucleus.activities;

import com.google.android.gms.location.SettingsApi;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost.Settings;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class MapAcivity extends Activity {
	
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	  static final LatLng KIEL = new LatLng(53.551, 9.993);
	GpsTracker gps;
	
	GoogleMap map;
	double latitude;
	double longtitude;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		
		gps=new GpsTracker();
		
             double lat=gps.getLatitude();
             double lng=gps.getLongtitude();
             

		System.out.println("latttttttttttt"+lat);
		System.out.println("longgggggggggg"+lng);
		
	map=((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	
	if(map!=null)
	{
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null); 
		Marker marker=map.addMarker(new MarkerOptions().position(HAMBURG).title("QC").snippet("good").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker)));
		
		
		
	}
		
		
	
		
		
		
	}

	
	public class GpsTracker extends Service implements LocationListener
	{
		 Context context;
		 
		 boolean isGpsEnabled=false;
		 boolean canGetLocation=false;
		 boolean isNetworkIsAvailable=false;
		 
		 double latitude;
		 double longtitude;
		 
		 Location location;
		 
		 private static final long MIN_DISTANCE_CHANGE_FOR_UP=10;
		 private static final long MIN_TIME_BETWEEN_UPDATES=1000*60*1;
		 
		protected LocationManager locationmanager;
		
		 public GpsTracker() {
			// TODO Auto-generated constructor stub
			this.context=context;
			getLocation();
		}
		 
		 public Location getLocation()
		 
		 {
			 
			 try
			 {
				 locationmanager=(LocationManager) context.getSystemService(LOCATION_SERVICE);
				 
				 isGpsEnabled=locationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER);
				 
				 isNetworkIsAvailable=locationmanager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
				 
				 
				 if(isGpsEnabled&&!isNetworkIsAvailable)
				 {
					 
				 }
				 else
				 {
					 this.canGetLocation=true;
				 
				 if(isNetworkIsAvailable)
				 {
					 locationmanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BETWEEN_UPDATES,MIN_DISTANCE_CHANGE_FOR_UP , this);
					 
					 
				 
				 if(locationmanager!=null)
				 {
					 location=locationmanager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
					 
					 if(location!=null)
					 {
						 latitude=location.getLatitude();
						 longtitude=location.getLongitude();
					 }
				 }
			 }
				 if(isGpsEnabled)
				 {
					 if(location!=null){
						 locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BETWEEN_UPDATES,MIN_DISTANCE_CHANGE_FOR_UP, this);
						 if(locationmanager!=null)
						 {
							 location=locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							 
							 if(location!=null)
							 {
								 latitude=location.getLatitude();
								 longtitude=location.getLongitude();
							 }
					 }
				 }
			 
				 
			 }
		 }
			 }
		 catch(Exception e)
		 {
		 }
	
				
			return location;
		 }
		
		 
		 public void stopUsingGps()
		 {
			 if(locationmanager!=null)
			 {
				 locationmanager.removeUpdates(GpsTracker.this);
			 }
		 }
		 
		 public double getLatitude() {
			
			 if(location!=null)
			 {
				 latitude=location.getLatitude();
			 }
			 return latitude;
		}
		 
		 public double getLongtitude() {
		if(location!=null)
			longtitude=location.getLongitude();
			 return longtitude;
		}
		 
		 public boolean canGetLocation()
		 {
			 return this.canGetLocation;
		 }
		 
		 public void showSettingsAlert()
		 {
			 AlertDialog.Builder ad=new AlertDialog.Builder(context);
			 ad.setTitle("Gps Settings");
			 ad.setMessage("DO you Want to go to settings Menu");
			 ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
					Intent i=new Intent();
					context.startActivity(i);
				}
			});
			 
			 ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int arg1) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
		 }

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public IBinder onBind(Intent intent) {
			// TODO Auto-generated method stub
			return null;
		} 
	}

//	@Override
//	public void onMyLocationChange(Location location) {
//		
//		// TODO Auto-generated method stub
//		gps=new GpsTracker();
//		if(gps.canGetLocation())
//		{
//		 latitude=gps.getLatitude();
//			
//	     longtitude=gps.getLongtitude();
//	     LatLng latlng=new LatLng(latitude, longtitude);
//			
//	     map.moveCamera(CameraUpdateFactory.newLatLng(latlng));
//	     map.animateCamera(CameraUpdateFactory.zoomTo(15));
//	     
//			
//		}
//		
//		Marker marker=map.addMarker(new MarkerOptions().position(new LatLng(latitude, longtitude)).title("current").snippet("population"));
//		
//	}
}
