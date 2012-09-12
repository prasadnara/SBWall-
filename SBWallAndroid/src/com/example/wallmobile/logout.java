package com.example.wallmobile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import com.facebook.android.*;
import com.facebook.*;
import android.app.Activity;
import android.os.Bundle;


public class logout extends MyGreatActivity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.main_notused);
		try {
			facebook.logout(getBaseContext());
			facebook.logout(getParent());
			facebook.logout(getApplication());
			facebook.logout(getApplicationContext());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
   
	       
}