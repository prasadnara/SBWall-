package com.example.wallmobile;

import com.example.wallmobile.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Loginselection extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginselection);
		// buttons on the screen
		View sbuid_button = this.findViewById(R.id.widget102);
		View fbid_button = this.findViewById(R.id.widget103);

		// listener for the locations button.
		sbuid_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), login.class);
				startActivityForResult(myIntent, 0);
			}

		});
		
		//listener for feedback button
		fbid_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), MyGreatActivity.class);
				startActivityForResult(myIntent, 0);
			}

		});
		}
}