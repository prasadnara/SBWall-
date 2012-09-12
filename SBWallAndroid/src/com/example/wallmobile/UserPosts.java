package com.example.wallmobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UserPosts extends Activity{

	private String posts = "";
	private int numberOfPosts = -1; // First title returned is NA and is not counted

  // Called when the activity is first created. 
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.userpost);
      Bundle extras = getIntent().getExtras();
      Long location = extras.getLong("id");
      
      TextView result = (TextView)findViewById(R.id.result);
    
      try {
    	  
    	  URL rssUrl = new URL("http://sb2312nat.cs.sunysb.edu/SBWall/mobile/rss.php?rss=messages&location="+location+"&from=0");
		  SAXParserFactory mySAXParserFactory = SAXParserFactory.newInstance();
		  SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
		  XMLReader myXMLReader = mySAXParser.getXMLReader();
		  RSSHandler myRSSHandler = new RSSHandler();
		  myXMLReader.setContentHandler(myRSSHandler);
		  InputSource myInputSource = new InputSource(rssUrl.openStream());
		  myXMLReader.parse(myInputSource);
   
		  if (numberOfPosts==0)
		  	posts = "\n  There is no post to display on this wall !";
		
		  result.setText(posts);
		  
		 } catch (MalformedURLException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		  result.setText(" We are unable to connect to the network!\n\n Please check the network connection.");
		 } catch (ParserConfigurationException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		  result.setText(" We are unable to connect to the network!\n\n Please check the network connection.");
		 } catch (SAXException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		  result.setText(" We are unable to connect to the network!\n\n Please check the network connection.");
		 } catch (IOException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		  result.setText(" We are unable to connect to the network!\n\n Please check the network connection.");
		 }  
  }

  private class RSSHandler extends DefaultHandler
  {
   final int stateUnknown = 0;
   final int stateTitle = 1;
   int state = stateUnknown;
  
   String strTitle = "";
   String strElement = "";
  
 @Override
 public void startDocument() throws SAXException {
  // TODO Auto-generated method stub
  strTitle = "";
 }

 @Override
 public void endDocument() throws SAXException {
  // TODO Auto-generated method stub
  strTitle += "";
  posts = strTitle;
 }

 @Override
 public void startElement(String uri, String localName, String qName,
		   Attributes attributes) throws SAXException {
		  // TODO Auto-generated method stub
//		System.out.print(localName+" : "); 

		if (localName.equalsIgnoreCase("title"))
		  {
		   numberOfPosts++;

		   if (numberOfPosts!=0){
			   strElement = "";
			   state = stateTitle;
		   }
		  }
		   else if (localName.equalsIgnoreCase("description"))
		   {
			   if (numberOfPosts!=0){
				   strElement = "";
				   state = stateTitle;
			   }
			  }
		   else if (localName.equalsIgnoreCase("author"))
		   {
			   if (numberOfPosts!=0){
				   strElement = "Posted by: ";
				   state = stateTitle;
			   }
			  }
		   else if (localName.equalsIgnoreCase("toe"))
		   {
			   if (numberOfPosts!=0){
				   strElement = "Posted at: ";
				   state = stateTitle;
			   }
			  }
		   else
		  {
		   state = stateUnknown;
		  }
		 
	}

		 @Override
	public void endElement(String uri, String localName, String qName)
		   throws SAXException {
		  // TODO Auto-generated method stub
		  if (localName.equalsIgnoreCase("title"))
		  {
		   strTitle += strElement + "\n";
		  }
		  else if (localName.equalsIgnoreCase("description"))
		  {
			   strTitle += "\n" + strElement + "\n";
		  }
		  else if (localName.equalsIgnoreCase("author"))
		  {
			   strTitle += "\n"+strElement;
		  }
		  else if (localName.equalsIgnoreCase("toe"))
		  {
			   strTitle += "\n"+ strElement;
			   strTitle += "\n_______________________________________________\n";
		  }	
		  state = stateUnknown;
		 }

		 @Override
	public void characters(char[] ch, int start, int length)
		   throws SAXException {
		  // TODO Auto-generated method stub
		  String strCharacters = new String(ch, start, length);
		  System.out.println(strCharacters); 

		  if (state == stateTitle)
		  {
		   strElement += strCharacters;
		  }
		 }
  	}
}