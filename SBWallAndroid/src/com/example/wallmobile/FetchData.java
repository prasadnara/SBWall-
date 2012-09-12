package com.example.wallmobile;

import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FetchData {
	public static Vector<Vector<String>> createList() {
		Vector<Vector<String>> msgs = new Vector<Vector<String>>();
		
		try {
//			File file = new File("/home/varun/MyXMLFile.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("http://pcl.cewit.stonybrook.edu:8080/mobile/rss.php?rss=wiki&location=1");
			doc.getDocumentElement().normalize();
			System.out.println("Root element "
					+ doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("item");

			for (int s = 0; s < nodeLst.getLength(); s++) {
				
				Vector<String> msg = new Vector<String>();	

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					Element fstElmnt = (Element) fstNode;
					NodeList fstNmElmntLst = fstElmnt
							.getElementsByTagName("title");
					Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					NodeList fstNm = fstNmElmnt.getChildNodes();
					//System.out.println("Title : " + ((Node) fstNm.item(0)).getNodeValue());
					msg.add(((Node) fstNm.item(0)).getNodeValue());
					NodeList lstNmElmntLst = fstElmnt
							.getElementsByTagName("description");
					Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
					NodeList lstNm = lstNmElmnt.getChildNodes();
					//System.out.println("Description : " + ((Node) lstNm.item(0)).getNodeValue());
					if (lstNm.getLength() != 0) {
						msg.add(((Node)lstNm.item(0)).getNodeValue());
					}
				}
			msgs.add(msg);	

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return msgs;
	}
}
