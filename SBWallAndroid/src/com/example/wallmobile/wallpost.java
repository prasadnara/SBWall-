package com.example.wallmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TextView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class wallpost extends Activity {
    /** Called when the activity is first created. */
	
	public void postOnWall(View button) {
	
		final EditText sujectField = (EditText) findViewById(R.id.EditWallPostSubject);
		String postSubject = sujectField.getText().toString();
			
		final EditText postBodyField = (EditText) findViewById(R.id.EditWallPostBody);
		String postBody = postBodyField.getText().toString();
		
		final Spinner locationSpinner = (Spinner) findViewById(R.id.SpinnerLocations);
		String location = locationSpinner.getSelectedItem().toString();
		
		final Spinner expirationSpinner = (Spinner) findViewById(R.id.SpinnerPostExpires);
		String expiresIn = expirationSpinner.getSelectedItem().toString();
		
		final Spinner categorySpinner = (Spinner) findViewById(R.id.SpinnerCategories);
		String category = categorySpinner.getSelectedItem().toString();

// ------------------------- HTTP Post -----------------------------------------		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://sb2312nat.cs.sunysb.edu/SBWall/mobile/rss.php");
		HttpResponse response = null;
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("rss", "post"));
		pairs.add(new BasicNameValuePair("title", postSubject));
		pairs.add(new BasicNameValuePair("cat", category));
		pairs.add(new BasicNameValuePair("location", "1"));	// Get location information
		pairs.add(new BasicNameValuePair("message", postBody));
		pairs.add(new BasicNameValuePair("username", "SBWall"));	// Get current user
		pairs.add(new BasicNameValuePair("expire", expiresIn));
		pairs.add(new BasicNameValuePair("event", "yes"));
				
		try{
			post.setEntity(new UrlEncodedFormEntity(pairs,HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
// --------------------- TEST ------------------------------------------------
System.out.println("\n\n>> Post sent to server:\n");
HttpEntity resEntity = post.getEntity();
System.out.println(post.toString());
if (resEntity != null) {    
		System.out.println("\n>> Post:\n");
	try {
		System.out.println(EntityUtils.toString(resEntity));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}		
//End of --------------------- TEST ------------------------------------------------

// *************************** Uncomment the following comment block for the post to work **************

		try {
			response = client.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        startActivity(new Intent(this, UserPosts.class));

	}

	@Override
	public void onCreate(Bundle icicle) {
	super.onCreate(icicle);
	setContentView(R.layout.post_on_wall);
	
	}// onCreate
	
	protected String formatPostTitle(String location) {
		
		String strFeedbackSubjectFormat = getResources().getString(
				R.string.feedbackmessagesubject_format);

		String strFeedbackSubject = String.format(strFeedbackSubjectFormat, location);
		
		return strFeedbackSubject;

	}
	
	protected String formatPostBody(String location, String name,
			String email, String postBody) {
		
		String strFeedbackFormatMsg = getResources().getString(
				R.string.feedbackmessagebody_format);

		String strFeedbackMsg = String.format(strFeedbackFormatMsg,
				location, postBody, name, email);
		
		return strFeedbackMsg;

	}
	

	protected String getResponseString(boolean bRequiresResponse)
	{
		if(bRequiresResponse==true)
		{
			return getResources().getString(R.string.feedbackmessagebody_responseyes);
		} else {
			return getResources().getString(R.string.feedbackmessagebody_responseno);
		}
			
	}

	public void sendPostToWall(View button) {

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("sb2312nat.cs.sunysb.edu/rss.php");

		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("title", "Event Post Test"));
		pairs.add(new BasicNameValuePair("category", "Food"));
		pairs.add(new BasicNameValuePair("location", "Event Post Test"));
		pairs.add(new BasicNameValuePair("message", "This is a test event by Android"));
		pairs.add(new BasicNameValuePair("username", "SBWall"));
		pairs.add(new BasicNameValuePair("expire", "15 minutes"));
		try{
			post.setEntity(new UrlEncodedFormEntity(pairs));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
	}
}
