package com.nucleus.activities;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends Activity {
	
	
	Button myday,dashboard,myfinancial,geofence,employees;
	ImageButton current;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_latyout);
		myday=(Button) findViewById(R.id.myday);
		dashboard=(Button) findViewById(R.id.dashboard);
		
		myday.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent imyday=new Intent(getApplicationContext(),MydayActivity.class);
				startActivity(imyday);
			}
		});
		
		dashboard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent idashboard=new Intent(getApplicationContext(),MydayActivity.class);
				startActivity(idashboard);
			}
		});
	}

}
