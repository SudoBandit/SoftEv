/*
 * @(#)XMLPayload.java	 0.1.0
 *
 * Copyright (c) 2019 Julian M. Bass
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */package XMLWriter;

import cobol.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLPayload {
	Document doc;
	Element rootElement;

	
	public XMLPayload() {
		try {
		DocumentBuilderFactory dbFactory =
		         DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = 
		            dbFactory.newDocumentBuilder();
		doc = dBuilder.newDocument();
		
		 // root element
        rootElement = doc.createElement("cobol");
        doc.appendChild(rootElement);
		
		 } catch (Exception e) {
	         e.printStackTrace();
	     }
		
	}
	
	
	public void addElements(Cobol c) {
		
		
		
		String redefinesDescription = c.getRedefinedName();
		if (redefinesDescription!= null) {
		this.addRedefinesDescription(redefinesDescription,c.getDefinedName(),c.getRecordDescriptionPicDesc(), c.getRecordDescriptionLength(),c.getRecordDescriptionSymbol());
		}		
		
		
		
		/*
		* add recordDescription element
		*/
		String recordDescription = c.getRecordDescriptionName();
		if (recordDescription != null) {
			
		this.addRecordDescriptionElement( recordDescription, 
		c.getRecordDescriptionLength(), c.getRecordDescriptionSymbol(), c.getRecordDescriptionPicDesc(), c.getRecordDescriptionType());
		//System.out.println("Got Section");
		// Add contents of procedure division
		} else {
		//System.out.println("Comment Line null");
		}
		
		/*
		* add commentLine element
		*/
		String commentLine = c.getCommentLine();
		if (commentLine != null) {
		this.addCommentLineElement( commentLine );
		//System.out.println("Got Section");
		// Add contents of procedure division
		} else {
		//System.out.println("Comment Line null");
		}
		/*
		* add ConstantName element
		*/
		String constantName = c.getConstantName();
		if (constantName != null) {
		this.addConstantValueElement( constantName,
		c.getConstantValue(), c.getLineNumber() );
		//System.out.println("Got Section");
		// Add contents of procedure division
		} else {
		//System.out.println("Comment Line null");
		}
		/*
		 *  add sectionName element
		 */		
		String sectionName = c.getSectionName();
		if (sectionName != null) {
			this.addSectionElement( sectionName );
			
			// Add contents of procedure division
		} else {
			// Section Name null
		}
		
		/*
		 *  add divisionName element
		 */		
		String divisionName = c.getDivisionName();
		if (divisionName != null) {
			this.addDivisionElement( divisionName );
			// Got Division
			// Add contents of procedure division
		} else {
			// Division Name null
		}
		
		/*
		 *  add ProgramID element
		 */		
		String programIDName = c.getProgram_ID();
		if (programIDName != null) {
			this.addProgram_IDElement( programIDName );
			// Got ProgramID
			// Add contents of procedure division
		} else {
			// ProgramID null
		}
		
		/*
		 *  add DateWritten element
		 */	
		// DayDateWritten
		int dayDateWritten = c.getDayDateWritten();
		if(dayDateWritten != 0) {
			this.addDayDateWrittenElement( dayDateWritten );
		}
		
		//MonthDateWritten
		String monthDateWritten = c.getMonthDateWritten();
		if (monthDateWritten != null) {
			this.addMonthDateWrittenElement( monthDateWritten );
			// Got month
			// Add contents of procedure division
		} else {
			// month null
		}

		// YearDateWritten
		int yearDateWritten = c.getYearDateWritten();
		if(yearDateWritten != 0) {
			this.addYearDateWrittenElement( yearDateWritten );
		}
		
		String remarks = c.getRemarks();
		if (remarks != null) {
			this.addRemarksElement(remarks);
		}

	}
	

 	private void addRedefinesDescription(String redefinedName, String definedName, String recordDescriptionPicDesc,
			int recordDescriptionLength, String recordDescriptionSymbol) {
 		
 		
 		Element recordRedefined=doc.createElement("Record");

 		
 			Element recordName=doc.createElement("Name");
 				recordName.appendChild(doc.createTextNode(redefinedName));
 				recordRedefined.appendChild(recordName);
 				
 			Element redefinedSubject = doc.createElement("Redefines");
 				redefinedSubject.appendChild(doc.createTextNode(definedName));
 				recordRedefined.appendChild(redefinedSubject);
 		
 		
 		if (null!=recordDescriptionPicDesc){
 				//System.out.println("this should say pic = ");

 			Element reservedSpace = doc.createElement("Size");
 				reservedSpace.appendChild(doc.createTextNode(Integer.toString(recordDescriptionLength)));
 				recordRedefined.appendChild(reservedSpace);
 			Element recordType = doc.createElement("Type");
 				recordType.appendChild(doc.createTextNode(recordDescriptionPicDesc));
 				recordRedefined.appendChild(recordType);
 			
 		}
		rootElement.appendChild(recordRedefined);		
	}


	private void addRecordDescriptionElement(String recordDescription, int recordDescriptionLength, String recordDescriptionSymbol, String recordDescriptionPicDesc, int type) {

		
		Element recordDescriptionElement = doc.createElement("Record");
		  
		Element recordLevel =doc.createElement("Level");
		  	recordLevel.appendChild(doc.createTextNode(String.valueOf(type)));
		  	recordDescriptionElement.appendChild(recordLevel);
		
		
	Element recordName=doc.createElement("Name");
		  	recordName.appendChild(doc.createTextNode(recordDescription));
		  	recordDescriptionElement.appendChild(recordName);
		  
  
	 Element recordValue=doc.createElement("Length");
	 		 recordValue.appendChild(doc.createTextNode(Integer.toString(recordDescriptionLength)));
		  	 recordDescriptionElement.appendChild(recordValue); 


		  
		  Element recordType=doc.createElement("Type");
		  	recordType.appendChild(doc.createTextNode(recordDescriptionPicDesc));
		  	recordDescriptionElement.appendChild(recordType); 
		  
		   	  		  
		  rootElement.appendChild(recordDescriptionElement);
		  
		 
// 		Element recordDescriptionElement = doc.createElement("Data_Type");
// 		
// 		Attr attrType4 = doc.createAttribute("Type");
//		attrType4.setValue(Integer.toString(type));
//		recordDescriptionElement.setAttributeNode(attrType4);
//		
// 		Attr attrType = doc.createAttribute("Name");
//		attrType.setValue(recordDescription);
//		recordDescriptionElement.setAttributeNode(attrType);
//		
//		Attr attrType1 = doc.createAttribute("Length");
//		attrType1.setValue(Integer.toString(recordDescriptionLength));
//		recordDescriptionElement.setAttributeNode(attrType1);
//		if(type == 1) {
//			//if(!recordDescriptionSymbol.equals(".")); 
//		Attr attrType2 = doc.createAttribute("Symbol");
//		attrType2.setValue(recordDescriptionSymbol);
//		recordDescriptionElement.setAttributeNode(attrType2);
//		
//		Attr attrType3 = doc.createAttribute("Pic_Description");
//		attrType3.setValue(recordDescriptionPicDesc);
//		recordDescriptionElement.setAttributeNode(attrType3);
//		}
//		rootElement.appendChild(recordDescriptionElement);
	}
	
	void addRemarksElement(String remarks) {
 			Element remarksName = doc.createElement("Remarks");
 			remarksName.appendChild(doc.createTextNode(remarks));
 			rootElement.appendChild(remarksName);	
	}


	void addProgram_IDElement(String stringElement) {
		//  Program ID element
		
		if(stringElement != null) {
			Element cobolname = doc.createElement("Program-ID");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}
 	
	void addCommentLineElement(String stringElement) {
		//  Comment Line element
		
		if(stringElement != null) {
			Element cobolname = doc.createElement("comment");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}
 	
 	
 	
 	void addSectionElement(String stringElement) {
		//  Section element
		
		if(stringElement != null) {
			Element cobolname = doc.createElement("section");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}
 	
 	void addDivisionElement(String stringElement) {
		//  Division element
		if(stringElement != null) {
			Element cobolname = doc.createElement("division");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}
	
	void addDayDateWrittenElement(int intElement) {
		//  DayDateWritten element
		
		if(intElement != 0) {
			Element cobolname = doc.createElement("day-date-written");
			String s = "" + intElement;
			cobolname.appendChild(doc.createTextNode(s));
			rootElement.appendChild(cobolname);
		}
	}
 	
	void addMonthDateWrittenElement(String stringElement) {
		//  MonthWritten element
		
		if(stringElement != null) {
			Element cobolname = doc.createElement("month-date-written");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}

	void addYearDateWrittenElement(int intElement) {
		//  YearDateWritten element
		
		if(intElement != 0) {
			Element cobolname = doc.createElement("year-date-written");
			String s = "" + intElement;
			cobolname.appendChild(doc.createTextNode(s));
			rootElement.appendChild(cobolname);
		}
	}
	
	public void writeFile(String filename) {
		try {
		// write the content into xml file
		// insert debug printf "WriteFile Filename: " + filename
        TransformerFactory transformerFactory =
        TransformerFactory.newInstance();
        Transformer transformer =
        transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        
        StreamResult result =
                new StreamResult(new File(filename));
        transformer.transform(source, result);
        
        // Output to console for testing
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
        
		 } catch (Exception e) {
	         e.printStackTrace();
	     }
	}
	void addConstantValueElement(String constantName,
			double constantValue, int lineNumber) {
			// Program ID element
			if(constantName != null) {
			Element cobolname = doc.createElement("Constant");
			
				Element constID = doc.createElement("Name");
				constID.appendChild(doc.createTextNode(constantName));
			
				Element cValue = doc.createElement("Value");
				cValue.appendChild(doc.createTextNode(Double.toString(constantValue)));
			
			cobolname.appendChild(constID);	
			cobolname.appendChild(cValue);
			rootElement.appendChild(cobolname);
			}
			}
}
