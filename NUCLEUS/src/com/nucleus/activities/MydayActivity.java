package com.nucleus.activities;



import org.json.JSONObject;

import com.nucleus.adapter.Mydayadapter;
import com.nucleus.jsonrequest.JsonParser;
import com.nucleus.swipemenulistview.SwipeDetector;
import com.nucleus.swipemenulistview.SwipeInterface;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;


public class MydayActivity extends Activity implements SwipeInterface
{
	
	
	 static String url="http://test.quickcontractors.com/Handlers/API/APIHandler.ashx?action=auth&username=[username]&password=[password]";
	
	//SwipeMenuListView sListview;
	 
	ListView lv;
	    Context context;
	    ImageView map_view_acivity,home_activity,message_activity,complaints_activity,calender_activity;

	   
	    
	    
	    
	    public static int [] phone_call={R.drawable.phone,R.drawable.phone,R.drawable.phone,R.drawable.phone,R.drawable.phone,R.drawable.phone};
	    public static int [] map_view={R.drawable.mapmarker,R.drawable.mapmarker,R.drawable.mapmarker,R.drawable.mapmarker,R.drawable.mapmarker,R.drawable.mapmarker};
	    public static int [] pending={R.drawable.pendingicon,R.drawable.pendingicon,R.drawable.pendingicon,R.drawable.pendingicon,R.drawable.hold,R.drawable.hold};
	    public static int [] hold={R.drawable.hold,R.drawable.hold,R.drawable.hold,R.drawable.hold,R.drawable.hold,R.drawable.success};
	    public static int [] pause={R.drawable.pausebutton,R.drawable.pausebutton,R.drawable.pausebutton,R.drawable.pausebutton,R.drawable.pausebutton,R.drawable.pausebutton};
	    public static int [] command={R.drawable.command,R.drawable.command,R.drawable.command,R.drawable.command,R.drawable.command,R.drawable.command};
	    public static String [] apt={"8:00am","9:00am","10:00am","11:00am","12:00am","1:00pm"};
	    public static String [] jobid={"1234","4567","843904234","28323","84220","22020"};
	 
	    
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myday_layout);
		
		SwipeDetector swipe = new SwipeDetector(this);
		LinearLayout swipe_layout = (LinearLayout) findViewById(R.id.swipe_layout);
		swipe_layout.setOnTouchListener(swipe);
		
		lv=(ListView) findViewById(R.id.listview);
	//sListview=(SwipeMenuListView) findViewById(R.id.listView);	

     map_view_acivity=(ImageView) findViewById(R.id.map);
    home_activity=(ImageView) findViewById(R.id.home_button);
    message_activity=(ImageView) findViewById(R.id.msg_button);
    complaints_activity=(ImageView) findViewById(R.id.complaints_button);
    calender_activity=(ImageView) findViewById(R.id.calender_button);
    context=this;
   
     lv.setAdapter(new Mydayadapter(this, apt,jobid,phone_call,map_view,pending,hold,pause,command));
     new MydayData().execute();
// 	// step 1. create a MenuCreator
//	SwipeMenuCreator creator = new SwipeMenuCreator() {
//
//			@Override
//		public void create(SwipeMenu menu) {
//			// create "open" item
//				SwipeMenuItem openItem = new SwipeMenuItem(
//						getApplicationContext());
//				// set item background
//				openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
//						0xCE)));
//				// set item width
//				openItem.setWidth(dp2px(90));
//				// set item title
//				openItem.setTitle("Open");
//				// set item title fontsize
//				openItem.setTitleSize(18);
//				// set item title font color
//				openItem.setTitleColor(Color.WHITE);
//				// add to menu
//				menu.addMenuItem(openItem);
//
//				// create "delete" item
//				SwipeMenuItem deleteItem = new SwipeMenuItem(
//						getApplicationContext());
//				// set item background
//				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//						0x3F, 0x25)));
//				// set item width
//				deleteItem.setWidth(dp2px(90));
//				// set a icon
//				//deleteItem.setIcon(R.drawable.ic_delete);
//				// add to menu
//				menu.addMenuItem(deleteItem);
//			}
//		};
//		// set creator
//		sListview.setMenuCreator(creator);
//
//		// step 2. listener item click event
//		sListview.setOnMenuItemClickListener(new OnMenuItemClickListener() {
//			@Override
//			public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//				//ApplicationInfo item = mAppList.get(position);
//				switch (index) {
//				case 0:
//					// open
//				//	open(item);
//					break;
//				case 1:
////					// delete
////				<!--	delete(item);
////					mAppList.remove(position);
////					mAdapter.notifyDataSetChanged();
////					break;-->
//				}
//				return false;
//			}
//		});
//		
//		// set SwipeListener
//		sListview.setOnSwipeListener(new OnSwipeListener() {
//			
//			@Override
//			public void onSwipeStart(int position) {
//				// swipe start
//				
//			}
//			
//			@Override
//			public void onSwipeEnd(int position) {
//				// swipe end
//			}
//		});
//
//		// other setting
////		listView.setCloseInterpolator(new BounceInterpolator());
//		
//		// test item long click
//		sListview.setOnItemLongClickListener(new OnItemLongClickListener() {
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				Toast.makeText(getApplicationContext(), position + " long click", 0).show();
//				return false;
//			}
//		});
//		
//     
//     OnItemClickListener listener= new OnItemClickListener() {
//
//		@Override
//		public void onItemClick(AdapterViewCompat<?> arg0, View arg1, int arg2,
//				long arg3) {
//			// TODO Auto-generated method stub
//			
//		}
//	};
     
     map_view_acivity.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent map=new Intent(getApplicationContext(),MapAcivity.class);
			startActivity(map);
		}
	});
     home_activity.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent home_intent=new Intent(getApplicationContext(),HomeActivity.class);
			startActivity(home_intent);
			
		}
	});
     message_activity.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	});
     complaints_activity.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	});
     calender_activity.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	});
	}
	
	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}
	
	public class MydayData extends AsyncTask<String, String, JSONObject>
	{
		
	ProgressDialog pDialog;
	

	@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			 pDialog = new ProgressDialog(MydayActivity.this);
	            pDialog.setMessage("Loading ...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
		}
	@Override
	protected JSONObject doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		JsonParser jp=new JsonParser();
		jp.getJsonFromUrl(url);
		return null;
		
	}

		@Override
			protected void onPostExecute(JSONObject result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				pDialog.dismiss();
			}
	
	}

	@Override
	  public void bottom2top(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void left2right(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
        case R.id.swipe_layout:
            // do your stuff here
        break;
    }       
	}

	@Override
	public void right2left(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void top2bottom(View v) {
		// TODO Auto-generated method stub
		
	}

}
