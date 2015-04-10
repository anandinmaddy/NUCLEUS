package com.nucleus.activities;



import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {
	private CustomProgressBar mSpinBar;
	private Thread mWelcomeThread = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSpinBar = (CustomProgressBar) findViewById(R.id.my_spin_bar);

		mSpinBar.setBarColor(Color.WHITE);
		mSpinBar.execute();
		  mWelcomeThread = new Thread() {

	            @Override
	            public void run() {
	                try {
	                    super.run();
	                    sleep(2000) ; //Delay of 5 seconds
	                } catch (Exception e) {

	                } finally {

	                    Intent i = new Intent(MainActivity.this,
	                           HomeActivity .class);
	                    startActivity(i);
	                    finish();
	                }
	            }
	        };
	        mWelcomeThread.start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
