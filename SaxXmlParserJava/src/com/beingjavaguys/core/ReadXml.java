package com.beingjavaguys.core;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXml extends DefaultHandler{
	
public void getXml(){
		try {
			// obtain and configure a SAX based parser
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

			// obtain object for SAX parser
			SAXParser saxParser = saxParserFactory.newSAXParser();

			// default handler for SAX handler class
			// all three methods are written in handler's body
			DefaultHandler defaultHandler = new DefaultHandler(){
				
				String firstNameTag="close";
				String lastNameTag="close";
				String emailTag="close";
				String phoneTag="close";
				
				// this method is called every time the parser gets an open tag '<'
				// identifies which tag is being open at time by assigning an open flag
				public void startElement(String uri, String localName, String qName,
						Attributes attributes) throws SAXException {
			
					if (qName.equalsIgnoreCase("FIRSTNAME")) {
						firstNameTag = "open";
					}
					if (qName.equalsIgnoreCase("LASTNAME")) {
						lastNameTag = "open";
					}
					if (qName.equalsIgnoreCase("EMAIL")) {
						emailTag = "open";
					}
					if (qName.equalsIgnoreCase("PHONE")) {
						phoneTag = "open";
					}
				}

				// prints data stored in between '<' and '>' tags
				public void characters(char ch[], int start, int length)
						throws SAXException {
					
					if (firstNameTag.equals("open")) {
						System.out.println("First Name : " + new String(ch, start, length));
					}
					if (lastNameTag.equals("open")) {
						System.out.println("Last Name : " + new String(ch, start, length));
					}
					if (emailTag.equals("open")) {
						System.out.println("Email : " + new String(ch, start, length));
					}
					if (phoneTag.equals("open")) {
						System.out.println("Phone : " + new String(ch, start, length));
					}
				}

				// calls by the parser whenever '>' end tag is found in xml 
				// makes tags flag to 'close'
				public void endElement(String uri, String localName, String qName)
						throws SAXException {
					
					if (qName.equalsIgnoreCase("firstName")) {
						firstNameTag = "close";
					}
					if (qName.equalsIgnoreCase("lastName")) {
						lastNameTag = "close";
					}
					if (qName.equalsIgnoreCase("email")) {
						emailTag = "close";
					}
					if (qName.equalsIgnoreCase("phone")) {
						phoneTag = "close";
					}
				}
			};
			
			// parse the XML specified in the given path and uses supplied
			// handler to parse the document
			// this calls startElement(), endElement() and character() methods
			// accordingly
			saxParser.parse("xmlToRead/student.xml", defaultHandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
