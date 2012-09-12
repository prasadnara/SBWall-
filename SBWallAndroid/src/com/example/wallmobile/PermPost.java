
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

public class PermPost extends Activity{

	String streamTitle = "";

  // Called when the activity is first created. 
  @Override
  public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
      setContentView(R.layout.userpost);
      Bundle extras = getIntent().getExtras();
      Long loaction = extras.getLong("id")+1;
      String url = "http://sb2312nat.cs.sunysb.edu/SBWall/mobile/rss.php?rss=wiki&location="+loaction;
      
      TextView result = (TextView)findViewById(R.id.result);
    
      try {

    	  URL rssUrl = new URL(url);
    	  
		  SAXParserFactory mySAXParserFactory = SAXParserFactory.newInstance();
		  SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
		  XMLReader myXMLReader = mySAXParser.getXMLReader();
		  RSSHandler myRSSHandler = new RSSHandler();
		  myXMLReader.setContentHandler(myRSSHandler);
		  InputSource myInputSource = new InputSource(rssUrl.openStream());
		  myXMLReader.parse(myInputSource);
		 
		  result.setText(streamTitle);
		 
		 } catch (MalformedURLException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		  result.setText(" We are unable to connect to the network!\n\n Please check the network connection.\n\n Error: MalformedURLException");
		 } catch (ParserConfigurationException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		  result.setText(" We are unable to connect to the network!\n\n Please check the network connection.\n\n Error: ParserConfigurationException");
		 } catch (SAXException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		  result.setText(" We are unable to connect to the network!\n\n Please check the network connection.\n\n Error: SAXException");
		 } catch (IOException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		  result.setText(" We are unable to connect to the network!\n\n Please check the network connection.\n\n Error: IOException");
		 }  
    
  }

  private class RSSHandler extends DefaultHandler
  {
   final int stateUnknown = 0;
   final int stateTitle = 1;
   int state = stateUnknown;
  
   int numberOfTitle = 0;
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
  streamTitle =  strTitle;
 }

 @Override
 public void startElement(String uri, String localName, String qName,
   Attributes attributes) throws SAXException {
  // TODO Auto-generated method stub
//System.out.print(localName+" : "); 

if (localName.equalsIgnoreCase("title"))
  {
   numberOfTitle++;

   if (numberOfTitle!=1){
	   strElement = "";
	   state = stateTitle;
   }
  }
   else if (localName.equalsIgnoreCase("description"))
   {

	   if (numberOfTitle!=1){
		   strElement = "";
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
	   strTitle += strElement + "\n";
  }
  state = stateUnknown;
 }

 @Override
 public void characters(char[] ch, int start, int length)
   throws SAXException {
  // TODO Auto-generated method stub
  String strCharacters = new String(ch, start, length);
  System.out.print(strCharacters); 

  if (state == stateTitle)
  {
   strElement += strCharacters+"\n";
  }
 }
  
  }
}


/*package com.example.wallmobile;

import com.example.wallmobile.R;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class PermPost extends Activity{
	WebView browser;
	Button mButton;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		Bundle extras = getIntent().getExtras();
		Long s = extras.getLong("id");

	    if(s == 0)
	    {	
		TextView textview = new TextView(this);
	    textview.setText("Stony Brook is an historic station for the Port Jefferson Branch of the Long Island Rail Road. It is located in Stony Brook on the southeast side of New York State Route 25A, across the street from the intersection of Route 25A with Cedar Street. On the opposite side of the tracks is the State University of New York at Stony Brook. There is also an at-grade pedestrian crossing between the station and a parking lot at the University.");
	    setContentView(textview);
	    }

	    if(s == 1)
	    {
			TextView textview = new TextView(this);
		    textview.setText("Sports Complex The sports complex is actually 2 separate buildings, the Pritchard Gym and the Newer West Wing.    Pritchard Gymnasium :The Pritchard Gym is one of the original buildings built on campus in the late 1960's when the campus moved to Stony Brook from its Oyster Bay location. The gymnasium is 20,000 square feet and has 3 regulation basketball courts that can be divided by a moveable wall into 2 separate rooms. West Wing: Opening in 1990, the West Wing was one of the biggest additions to the campus architect in many years.The arena is home to Men's and Women's Basketball, Women's Volleyball and numerous trade shows, ceremonies and concerts throughout the year.CONTACT INFORMATION : Department of Campus Recreation  Sports Complex, G-7 Stony Brook, NY 11794-2800 Phone: (631) 632-7168 Fax: (631) 632-2238 Email: campusrecreation@notes.cc.stonybrook.edu");
		    setContentView(textview);	    	
	    }

	    if(s == 2)
	    {
			TextView textview = new TextView(this);
		    textview.setText("The Student Health Service at the State University of Stony Brook is your on-campus source for meeting your primary health care needs. The staff of physicians, physician assistants, nurse practitioners,nurses, social workers, health educators, laboratory technologists, and technical and administrative staff are dedicated to their mission of providing students with quality medical care, and the services necessary to optimize preventative health and wellness.Fall and Spring Semesters : Medical Clinic :Monday through Friday 8 a.m to noon and 1-5 p.m., Tuesday until 7:30 p.m.Women's Clinic : Monday through Friday 8 a.m to noon and 1-3:30 p.m., Tuesday until 7:30 p.m.Summer Sessions and Intercessions : Walk-in Clinic : Monday through Friday 8 a.m to noon and 1-4 p.m.Women's Clinic : Monday through Friday 8 a.m to noon and 1-3:30 p.m.");
		    setContentView(textview);	    	
	    }
	    if(s == 3 )
	    {
			TextView textview = new TextView(this);
		    textview.setText("Just a short walk away from the Student Activities Center, via Zebra path, is the Stony Brook Union. Built in 1969, the Stony Brook Union is the place where students can take classes, attend meetings or unwind in one of the many lounges. The building is home to many student clubs and organizations including several campus newspapers, the University's yearbook, and the award winning radio station WUSB-FM. SBU also houses a university SINC Site, hair salon, Meal Plan Offices, Interfaith Center, Wo/Men's Center, Crafts Center, and dining facilities complete with a Kosher deli. Further, the Stony Brook Union is online with the campus AirNet Wireless Network.In addition to these services, the Stony Brook Union also provides the campus with access to a wide range of programming and meeting spaces. We welcome the campus community to enjoy our recently renovated Auditorium, Ballroom and UNITI Cultural Center as well as all our other meeting rooms and service locations.");
		    setContentView(textview);	    	
	    }
	    if(s == 4)
	    {
			TextView textview = new TextView(this);
		    textview.setText("The Staller Center offers a wealth of professional performances by popular artists from around the world, showcasing programs that include jazz, modern dance, ballet, opera, theatre, classical music and more. Through our First On Us program, new students can receive a free ticket to a performance of their choice.Contact Information : Mailing address: Staller Center For the Arts, room 2030A ,SUNY Stony Brook,Stony Brook, NY 11794 Administrative Office:(631) 632-7235 Fax: (631) 632-7354 Box Office:(631) 632-ARTS (631) 632-2787 Fax: (631) 632-7354");
		    setContentView(textview);	    	
	    }
	    if(s == 5)
	    {
			TextView textview = new TextView(this);
		    textview.setText("Frank Melville Jr. Memorial Library is the University's extensive library system, which houses two million bound volumes and four million publications in microformat. 1) Owns over 2.1 million items and circulates over 140,000 of them every year. 2) Is a member of the Association of Research Libraries 3) Represents at least 25 languages in its collections. 4) Owns over 130,000 maps, atlases, and aerial photographs. 5) Added nearly 11,000 books to its collection last year. 6) Lends about 16,000 books and articles to other libraries every year, and borrows almost 12,000. 7) Circulates a wider range of DVDs and videos 8) Has, with nearly 4 million rolls 9) Assists patrons in searching over 7 million patents 10) Spends over $2.5 million a year to lease online research databases and over 60,000 electronic journals.11) Has a Special Collections that houses over 25,000 rare books, 500 collections, 800 maps. Contact Information Stony Brook University Libraries Phone: 631.632.7100");
		    setContentView(textview);	    	
	    }
	    if(s == 6)
	    {
			TextView textview = new TextView(this);
		    textview.setText("The Charles B. Wang Center is one of the most beautiful and inventive buildings given to any university. Here you'll experience spaces of surprising traditional beauty juxtaposed with the latest in communications technology. Filled with light and air, graced by gardens inside and out, the Center offers spaces suitable for conferences, performances, exhibitions, and celebrations. Contact Information Asian/American Programs Dr. Sunita S. Mukhi Director of Asian/American Programs Stony Brook University Stony Brook, NY 11794-4040  Sunita.Mukhi@stonybrook.edu (631) 632-4400 Jennifer Iacona Coordinator for Asian/American Programs Charles B. Wang Center, Suite 302 Stony Brook University Stony Brook, NY 11794-4040 Jennifer.Iacona@stonybrook.edu (631) 632-1944 General Inquiries for Asian/American Programs WangCenter@stonybrook.edu (631) 632-4400 General Inquiries for Reservations of Wang Facilities Conferences and Special Events Office (631) 632-6320 wangreservations@stonybrook.edu");
		    setContentView(textview);	    	
	    }
	    if(s == 7)
	    {
			TextView textview = new TextView(this);
		    textview.setText("Stony Brook Sports Complex is a 5,226-seat multi-purpose arena on the campus of State University of New York at Stony Brook. The arena opened in 1990. It is home to the Stony Brook Seawolves basketball, swimming and diving, and volleyball teams. The arena has been the host of I-CON since 1982. The Sports Complex will undergo a twenty million dollar renovation during the 2008-2009 season. During the renovation, the Pritchard Gymnasium will be used to host the basketball and volleyball games. The 2009 I-CON convention will be the first to be held outside of the arena. During the 2006-2007 season the arena set a men's basketball attendance record with 4285 while hosting Villanova.");
		    setContentView(textview);	    	
	    }
	    if(s == 8)
	    {
			TextView textview = new TextView(this);
		    textview.setText("Stony Brook University Medical Center is, located in Stony Brook, New York. It is the largest academic medical center on Long Island. It comprises Stony Brook University School of Medicine and Stony Brook University Hospital, which is the only tertiary care hospital and Level 1 trauma center in Suffolk County. With 540 beds and 5,100 employees, the hospital is the largest in Suffolk County. Stony Brook University Medical Center includes the School of Medicine and serves as a regional hub for advanced patient care, education, research, and community service. There are many opportunities for Pre-Medical and Health Sciences students to volunteer and gain experience in the health professions. Contact Information Medical Center Switchboard Patient and Staff (631) 689-8333 General Information  HealthConnect®	(631) 444-400");
		    setContentView(textview);	    	
	    }
	    if(s == 9)
	    {
			TextView textview = new TextView(this);
		    textview.setText("The Student Activities Center or SAC, located on the academic mall, serves as the focal point of the University. Events at our venue include lectures, conferences, movies, concerts, fitness classes, and annual recognition ceremonies which bring together all members of the Stony Brook Community. The SAC is home to a variety of programming spaces, meeting rooms, lobbies, lounges, and services. CONTACT Information Office of Student Activities Clubs & Organizations, Fraternities & Sororities, Red Hot After Hours, Weekend Life Initiative Student Activities Center, Suite 218 ,Stony Brook, New York 11794-2800. Phone: (631) 632-9392. Fax:  (631) 632-6206. SAC Art Gallery ,Student Activities Center, Suite 218, Stony Brook, New York 11794-2800. Phone:  (631) 632-9392.");
		    setContentView(textview);	    	
	    }
	    if(s == 10)
	    {
			TextView textview = new TextView(this);
			textview.setText("Sports Complex The sports complex is actually 2 separate buildings, the Pritchard Gym and the Newer West Wing.    Pritchard Gymnasium :The Pritchard Gym is one of the original buildings built on campus in the late 1960's when the campus moved to Stony Brook from its Oyster Bay location. The gymnasium is 20,000 square feet and has 3 regulation basketball courts that can be divided by a moveable wall into 2 separate rooms. West Wing: Opening in 1990, the West Wing was one of the biggest additions to the campus architect in many years.The arena is home to Men's and Women's Basketball, Women's Volleyball and numerous trade shows, ceremonies and concerts throughout the year.CONTACT INFORMATION : Department of Campus Recreation  Sports Complex, G-7 Stony Brook, NY 11794-2800 Phone: (631) 632-7168 Fax: (631) 632-2238 Email: campusrecreation@notes.cc.stonybrook.edu");
		    setContentView(textview);	    	
	    }
	    if(s == 11)
	    {
			TextView textview = new TextView(this);
		    textview.setText("The Administration Building houses the Office of Undergraduate Admissions. A life-sized replica of Majungasaurus crenatissimus guards the lobby of our Administration Building.The Office of Administration is responsible for overseeing the planning and management of two major units of campus operations: Finance & Administration and Facilities & Services. Karol Kain Gray serves as the Vice President of Finance & Adminstration and Barbara Chernow as the Vice President of Facilities & Services. Contact Information: Office of Administration,221 Administration, Stony Brook University,Stony Brook, NY 11794-1002.Tel: (631) 632-6100.Fax: (631) 632-6111.");
		    setContentView(textview);	    	
	    }
	    if(s == 12)
	    {
			TextView textview = new TextView(this);
		    textview.setText("Situated in Student Activities center and provides specials every day. With 6 different stations -Charcoals Grill, Omelet Pan, Mulberry Street, World Soup, Native Spice & Wrap it Up - catering to a variety of cuisines you have a plethora of dishes to choose from.On the Grill: Fresh beef burger: $2.75 Double burger:	$3.98 Turkey burger:	$3.32 Veggie burger:	$3.32 Grilled cheese:	$2.55 NY steak sandwich: $6.22 Grilled chicken sandwich: $4.44 Philly cheese steak: $5.03 Hot dog: $1.73 Country chicken sandwich: $4.44 Toppings: $.77 each Sauteed Mushrooms Sauteed Peppers Grilled Onions Bacon Sides: $2.30 each French Fries Onion Rings Cheeses: $.51 each American Provolone Cheddar Swiss.");
		    setContentView(textview);	    	
	    }
	    if(s == 13)
	    {
			TextView textview = new TextView(this);
		    textview.setText("Career Center Mission: The Career Center at Stony Brook University exists to: EDUCATE students about the career development process and industry options, empowering them to make informed career decisions.PREPARE students for experiential learning (i.e. internships and community service),employment and further education.CONNECT hiring organizations with our diverse student talent. Career Center  Address : W0550 Frank Melville Jr. Memorial Library ,Stony Brook, NY 11794-3363 (631) 632-6810   Fax: (631) 632-9146.");
		    setContentView(textview);	    	
	    }
	    if(s == 14)
	    {
			TextView textview = new TextView(this);
		    textview.setText("The Chapin Apartments are comprised of 12 buildings conveniently located near the Health Sciences Center and University Hospital. The Chapin Apartments are for single Graduate students, as well as married couples, domestic partnerships, and families.Meal Plan: A meal plan is not required for residents of the Chapin Apartments. A kitchen or kitchenette is provided for your convenience. Mailroom: All packages can be picked up at Chapin Mailroom (444-6746).Parking: Parking is located on Stadium road, in several lots along Chapin Loop.Dining Facilities: There are no dining facilities provided for the Chapin Apartments. Each Apartment is equipped with a kitchen and dining area. For more information, visit their website. Computing Center: Located in the Chapin Commons Building. Fitness Center: Located in the Chapin Commons Building.Office Location: Chapin Commons opposit Building A. Call 631-632-6755.");
		    setContentView(textview);	    	
	    }
	    if(s == 15)
	    {
			TextView textview = new TextView(this);
		    textview.setText("Established in 1969, the Computer Science Department at Stony Brook University is consistently ranked among the top quarter of Computer Science research departments in North America. A Gourman report indicated Stony Brook's undergraduate program was ranked 15th nationwide and 2nd in New York State.The department is the largest unit in the College of Engineering and Applied Sciences and is among the largest on the campus.The department is active in many of the major research areas in computer science with specialization in Visual Computing, Computer Systems, Networking and Security, Databases, Logic Programming and Deductive Systems, Concurrency and Verification, Algorithms and Complexity, and Computer Science Education. Contact Info : The Department of Computer Science, Computer Science Building, Stony Brook University, Stony Brook, NY 11794-4400.631-632-8470 or 631-632-8471, Fax : 631-632-8334.");
		    setContentView(textview);	    	
	    }
	    if(s == 16)
	    {
			TextView textview = new TextView(this);
		    textview.setText("With the collaborative, interdisciplinary atmosphere of our departments, our outstanding faculty, and our dedication to providing students with a variety of opportunities for research, the Graduate School offers students a well-rounded education that fully enables them to excel in whatever career path they choose.At Stony Brook, diversity is a necessity for intellectual excellence. Since a third of the graduate enrollees are African-American, Latino, Native American, and international students, Stony Brook is a place where cultures converge for the mutual enrichment of all. Contact Information : The Graduate School, Suite 2401, Computer Science Building, Stony Brook University, Stony Brook, NY 11794-4433.E-Mail : Graduate.School@sunysb.edu.631-632-4723 (632-GRAD), Fax : 631-632-7243.");
		    setContentView(textview);	    	
	    }


	}			
}*/
