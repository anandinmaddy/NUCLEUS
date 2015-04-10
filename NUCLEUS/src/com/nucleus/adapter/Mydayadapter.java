package com.nucleus.adapter;

import java.util.ArrayList;














import com.nucleus.activities.CommandActivity;
import com.nucleus.activities.MapAcivity;
import com.nucleus.activities.MydayActivity;
import com.nucleus.activities.PhoneCallActivity;
import com.nucleus.activities.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Mydayadapter extends BaseAdapter {

	ArrayList< MydayActivity> ar=new ArrayList<MydayActivity>();
	Context mcontext;
	String [] result;
	String [] result1;
    Context context;
	 int [] phone_call_array;
	 int [] map_view_array;
	 int [] pending_array;
	 int [] hold_array;
	 int [] pause_array;
	 int [] command_array;
      private static LayoutInflater inflater=null;
      
     
	
	public Mydayadapter(MydayActivity mydayActivity, String[] apt, String[] jobid,int[]  phone_call,int[] map_view,int[] pending,
			int[] hold,
	 int[] pause, int[]  command) {
		
		// TODO Auto-generated constructor stub
		  result=apt;
	        context=mydayActivity;
	        phone_call_array=phone_call;
	        map_view_array=map_view;
	        pending_array=pending;
	        hold_array=hold;
	        pause_array=pause;
	        command_array=command;
	        result1=jobid;
	         inflater = ( LayoutInflater )context.
	                 getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	

	


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return result.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(final int position, View convertview, ViewGroup parent) {
		
		
        View rowView;       
             rowView = inflater.inflate(R.layout.data_list, null);
             TextView time,job_id;
             time=(TextView) rowView.findViewById(R.id.time);
             job_id=(TextView) rowView.findViewById(R.id.jobid);
           
             
             ImageView phone_call,map_view,pending;
			final ImageView hold;
			final ImageView pause;
			final ImageView command;
             phone_call=(ImageView) rowView.findViewById(R.id.phon_call);    
             map_view=(ImageView) rowView.findViewById(R.id.map_view);
             pending=(ImageView) rowView.findViewById(R.id.pending);
             hold=(ImageView) rowView.findViewById(R.id.hold);
             pause=(ImageView) rowView.findViewById(R.id.pause);
             command=(ImageView) rowView.findViewById(R.id.command);   
         
             
             time.setText(result[position]);
             job_id.setText(result1[position]);
         
             
             phone_call.setImageResource(this.phone_call_array[position]);
             map_view.setImageResource(this.map_view_array[position]);
             pending.setImageResource(this.pending_array[position]);
             hold.setImageResource(this.hold_array[position]);
             pause.setImageResource(this.pause_array[position]);
             command.setImageResource(this.command_array[position]);
         
        
        
	        final AlertDialog.Builder alert=new AlertDialog.Builder(context);
	      
	         phone_call.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent phone_call_intent=new Intent(context,PhoneCallActivity.class);
				phone_call_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(phone_call_intent);
				
			}
		});
	        
	         map_view.setFocusableInTouchMode(false);
	         map_view.setFocusable(false);
	         map_view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					alert.setTitle("View On Map?");
					alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent  mapintent=new Intent(context,MapAcivity.class);
							mapintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.startActivity(mapintent);
						}
					});
					alert.setNegativeButton("Cancel", null);
					alert.show();
				}
			});
	      
	      pending.setFocusableInTouchMode(false);
	         pending.setFocusable(false);
         pending.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alert.setTitle("Are You Sure Want to Put Job Pending Status?");
				alert.setPositiveButton("OK", null );
				alert.setNegativeButton("CANCEL", null);
				alert.show();
			}
		});
         
         hold.setFocusableInTouchMode(false);
         hold.setFocusable(false);
         hold.setOnClickListener(new OnClickListener() {
			
        	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hold.setImageResource(R.drawable.success);
				alert.setTitle("Are You Hold The Job?");
				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				
				alert.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				alert.show();
				
			}
		});
         
          pause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pause.setImageResource(R.drawable.success);
			}
		});       
        
          command.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				command.setImageResource(R.drawable.pausebutton);
				Intent command_intent=new Intent(context,CommandActivity.class);
				command_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(command_intent);
			}
		});
          
        return rowView;		
		
		
		// TODO Auto-generated method stub
		
	}

}
