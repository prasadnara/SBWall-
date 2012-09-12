package com.example.wallmobile;

import com.example.wallmobile.R;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;
import android.content.*;


public class login extends Activity {
	public static final String PREFS_NAME = "LoginPref";
	String paswd;
	String Login_id;
	String name;
	View Login_Button;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Login_Button = this.findViewById(R.id.BLogin);
         
//        setSilent(silent);

        
        Login_Button.setOnClickListener(new View.OnClickListener() {
public void onClick(View v) {
// TODO Auto-generated method stub
	
Context context = getApplicationContext();
CharSequence text = "Hello toast!";
String loginURL="http://pcl.cewit.stonybrook.edu:8080/mobile/rss.php?rss=elogin&username=";
//String loginURL="https://www.google.com/accounts/Login";


int duration = Toast.LENGTH_SHORT;
final EditText ETlogin = (EditText) findViewById(R.id.ETLogin);
Login_id = ETlogin.getText().toString();

final EditText ETpaswd = (EditText) findViewById(R.id.ETPassword);
paswd = ETpaswd.getText().toString();

loginURL += Login_id+"&password="+paswd;
	try {
//		File file = new File("/home/varun/MyXMLFile.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		System.out.println("#####loginURL : " + loginURL);
		Document doc = db.parse(loginURL);
		doc.getDocumentElement().normalize();
		System.out.println("Root element "
				+ doc);
	
		NodeList nodeLst = doc.getElementsByTagName("item");

		for (int s = 0; s < nodeLst.getLength(); s++) {
			Node fstNode = nodeLst.item(s);

			if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

				Element fstElmnt = (Element) fstNode;
				NodeList fstNmElmntLst = fstElmnt
						.getElementsByTagName("title");
				Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
				NodeList fstNm = fstNmElmnt.getChildNodes();
			//	System.out.println("#####Title : " + ((Node) fstNm.item(0)).getNodeValue());
				//msg.add(((Node) fstNm.item(0)).getNodeValue());
				NodeList lstNmElmntLst = fstElmnt
						.getElementsByTagName("message");
				Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
				NodeList lstNm = lstNmElmnt.getChildNodes();
				//System.out.println("####Message : " + ((Node) lstNm.item(0)).getNodeValue());
				String str=new String((((Node)lstNm.item(0)).getNodeValue()));
				
				if(str.equalsIgnoreCase("validuser"))
				{
					 SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
				     
				     SharedPreferences.Editor editor = settings.edit();
				     
				     //Reterive data from controls
				 	/*final EditText ETlogin = (EditText) findViewById(R.id.ETLogin);
					 Login_id = ETlogin.getText().toString();
					*/
					 /*final EditText ETpaswd = (EditText) findViewById(R.id.ETPassword);
					 paswd = ETpaswd.getText().toString();
					*/
				     editor.putBoolean("SessionLogin", true);
				     editor.putString("LoginId", Login_id);
				     editor.putString("LoginName", Login_id);
				     
				     editor.putString("LoginPswd", paswd);
				     
				  // Commit the edits!
				     editor.commit();
				     
				     Intent myIntent = new Intent(v.getContext(), WallHomeScreen.class);
				     //myIntent.putExtra("Location", "Chapin");
				     startActivityForResult(myIntent, 0);

				}
				else
				{
					Toast toast = Toast.makeText(context, "Invalid credential", duration);
					toast.show();
				}
				
			}

		}
} catch (Exception e) {
		e.printStackTrace();
	}

}
});
	}
}
