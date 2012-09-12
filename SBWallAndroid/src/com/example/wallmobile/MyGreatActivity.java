package com.example.wallmobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.android.*;
import com.facebook.android.Facebook.*;
import android.app.Activity;
import android.os.Bundle;

public class MyGreatActivity extends Activity {
    /** Called when the activity is first created. */
	 Facebook facebook = new Facebook("176461082400612");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	            
        facebook.authorize(this, new DialogListener() {
            @Override
            public void onComplete(Bundle values) {
 			    Intent myIntent = new Intent(getApplicationContext(), WallHomeScreen.class);
 			    startActivityForResult(myIntent,0);
            }

            @Override
            public void onFacebookError(FacebookError error) {}

            @Override
            public void onError(DialogError e) {}

            @Override
            public void onCancel() {}
        });
        
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //add
        
        facebook.authorizeCallback(requestCode, resultCode, data);
	
    }
    
}