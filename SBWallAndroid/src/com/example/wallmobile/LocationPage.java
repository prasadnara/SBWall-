package com.example.wallmobile;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;


import android.widget.TabHost;
import android.app.AlertDialog;

public class LocationPage extends TabActivity {
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {               	
        	
        	 super.onCreate(savedInstanceState);
        	    TabHost mTabHost = getTabHost();
        	    
        	  //tab 1
        	    Intent userpost_intent1 = new Intent(this,UserPosts.class);            	
        		Bundle extras1 = getIntent().getExtras();
        		Long s1 = extras1.getLong("id") + 1;
        	    userpost_intent1.putExtra("id", extras1.getLong("id"));
        	    
        	    mTabHost.addTab(mTabHost.newTabSpec("tab_test2")
        	    		.setIndicator("Wall")
        	    		.setContent(userpost_intent1));
        	    	//	.setContent(new Intent(this,UserPosts.class)));
        	    
        	  //tab 2
        	    Intent userpost_intent0 = new Intent(this,PermPost.class);
        		Bundle extras0 = getIntent().getExtras();
        		Long s0 = extras0.getLong("id") + 1; // Add 1 to the array index to get the location number
        	    userpost_intent0.putExtra("id", extras0.getLong("id"));
        	    
        	    AlertDialog.Builder adb=new AlertDialog.Builder(LocationPage.this);
				adb.setTitle("LVSelectedItemExample");
				adb.setMessage("Selected Item is = "+s0);
				adb.setPositiveButton("Ok", null);
//				adb.show();
				
        	    mTabHost.addTab(mTabHost.newTabSpec("tab_test1")
        	    		.setIndicator("Wiki")
        	    		.setContent(userpost_intent0));
        	    	//	.setContent(new Intent(this,PermPost.class)));
        	    
        	    //tab 3
        	    Intent userpost_intent2 = new Intent(this,PostOnWall.class);
        		Bundle extras2 = getIntent().getExtras();
        		Long s2 = extras2.getLong("id") + 1;
        	    userpost_intent2.putExtra("id", extras2.getLong("id"));
      	    
        	    mTabHost.addTab(mTabHost.newTabSpec("tab_test3")
        	    		.setIndicator("Post on wall")
        	    		.setContent(userpost_intent2));
        	      
        	    mTabHost.setCurrentTab(0);
}
}

