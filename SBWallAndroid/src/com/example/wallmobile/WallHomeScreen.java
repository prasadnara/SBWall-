package com.example.wallmobile;

import com.example.wallmobile.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WallHomeScreen extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
		// buttons on the screen
		View feedback_button = this.findViewById(R.id.widget50);
		View locations_button = this.findViewById(R.id.widget49);
		View driving_directions_button = this.findViewById(R.id.widget51);
		View sunysb_map_button = this.findViewById(R.id.widget52);
		View postonwall_button = this.findViewById(R.id.widget53);
		View fblogout = this.findViewById(R.id.widget54);

		// listener for the locations button.
		locations_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), LocationsList.class);
				myIntent.putExtra("Location", "Chapin");
				startActivityForResult(myIntent, 0);
			}

		});
		
		//listener for feedback button
		feedback_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), Feedback.class);
				startActivityForResult(myIntent, 0);
			}

		});
		
		//listener for driving directions button
		driving_directions_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), Drivinglist.class);
				startActivityForResult(myIntent, 0);
			}

		});
		
		//listener for Stony Brook Map button
		sunysb_map_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				// open the activity showing SBU map
				Intent myIntent = new Intent(view.getContext(),Stonymap.class);
				startActivityForResult(myIntent, 0);
			}

		});

		//listener for wall post button
		postonwall_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), PostOnWall.class);
				startActivityForResult(myIntent, 0);
			}

		});
		
			fblogout.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view) {
			Intent myIntent = new Intent(view.getContext(), logout.class);
	//		myIntent.putExtra("Location", "Chapin");
			startActivityForResult(myIntent, 0);
		}

	});
	}
}

