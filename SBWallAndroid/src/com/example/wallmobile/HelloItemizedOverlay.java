package com.example.wallmobile;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Point;
import android.content.Context;
import android.content.Intent;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.app.Dialog;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;


public class HelloItemizedOverlay extends ItemizedOverlay {

	// private static Drawable defaultMarker;
	/**
	 * @param arg0
	 */
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;
	private Drawable marker;
    private int LENGTH_LONG =2;
    private Bitmap imageToShow;
	public HelloItemizedOverlay(Drawable defaultMarker) {
		super(defaultMarker);

		marker = defaultMarker;

		// TODO Auto-generated constructor stub
	}

	public HelloItemizedOverlay(Drawable defaultMarker, Context context,Bitmap image) {
		super(defaultMarker);

		marker = defaultMarker;
		mContext = context;
		imageToShow =image;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);

	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	private static InputStream OpenHttpConnection(String urlString) throws
	IOException
	           {
	               InputStream in = null;
	               int response = -1;

	               URL url = new URL(urlString);
	               URLConnection conn = url.openConnection();

	               if (!(conn instanceof HttpURLConnection))
	                   throw new IOException("Not an HTTP connection");

	               try{
	                   HttpURLConnection httpConn = (HttpURLConnection) conn;
	                   httpConn.setAllowUserInteraction(false);
	                   httpConn.setInstanceFollowRedirects(true);
	                   httpConn.setRequestMethod("GET");
	                   httpConn.connect();

	                   response = httpConn.getResponseCode();
	                   if (response == HttpURLConnection.HTTP_OK) {
	                       in = httpConn.getInputStream();
	                   }
	               }
	               catch (Exception ex)
	               {
	                   throw new IOException("Error connecting");
	               }
	               return in;
	           }

	private Bitmap DownloadImage(String URL)
	   {
	       Bitmap bitmap = null;
	       InputStream in = null;
	       try {
	           in = OpenHttpConnection(URL);
	           bitmap = BitmapFactory.decodeStream(in);
	           in.close();
	       } catch (IOException e1) {
	           // TODO Auto-generated catch block
	           e1.printStackTrace();
	       }
	       return bitmap;
	   }

	@Override
	protected boolean onTap(int index) {
		OverlayItem item = mOverlays.get(index);
	/*	 AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		 dialog.setTitle(item.getTitle());
		 dialog.setMessage(item.getSnippet());
		 dialog.setCancelable(true);
		 dialog.show();*/
		Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.custom_dialog);
		dialog.setTitle("GRC EVENT @ CEWIT");
		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText(item.getSnippet());
	// <RatingBar android:id="@+id/small_ratingbar" style="?android:attr/ratingBarStyleSmall" android:layout_marginLeft="5dip" android:layout_width="wrap_content"></RatingBar>
	//	final RatingBar ratingbar = (RatingBar) dialog.findViewById(R.id.RatingBar01);
	//	ratingbar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
	//	public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
	//	Toast.makeText(mContext, "New Rating: " + rating, Toast.LENGTH_SHORT).show();
		    //}
//		}//);
		
		//add image to marker
        //DownloadImage( "http://map.gsfc.nasa.gov/media/060915/060915_CMB_Timeline75.jpg");
/*
 * 		Bitmap bitmap =
	    DownloadImage( "http://www.imagemagick.org/Magick++/thumbnail-anatomy-framed.jpg");
		ImageView image = (ImageView)dialog.findViewById(R.id.image);
        image.setImageBitmap(bitmap);*/
	/*	ImageView image = (ImageView) dialog.findViewById(R.id.image);
		image.setImageResource(R.drawable.sample);*/
		ImageView image = (ImageView)dialog.findViewById(R.id.image);
        image.setImageBitmap(imageToShow);
		dialog.setCancelable(true);
		dialog.show();
//		Toast.makeText(mContext, item.getTitle() + item.getSnippet(),Toast.LENGTH_LONG).show();
		return true;
	}

	
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow,long when) {
		super.draw(canvas, mapView, shadow);
		boundCenterBottom(marker);
/*    	 Paint paint=new Paint();
		// //---translate the GeoPoint to screen pixels---
		 Point screenPts = new Point();
		 mapView.getProjection().toPixels(p, screenPts);
		 paint.setStrokeWidth(1);
		 paint.setARGB(222, 255, 255, 222);
		 paint.setStyle(Paint.Style.STROKE);
		 
		// //---add the marker---
		 Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.redcircle);
		 canvas.drawBitmap(bmp, screenPts.x, screenPts.y-bmp.getHeight(),
		 paint);
		 canvas.drawText("I am here", screenPts.x, screenPts.y, paint); */
		return true; 
		      
	}
}
