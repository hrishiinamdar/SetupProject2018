package com.codingcompetition.statefarm;

import com.codingcompetition.statefarm.model.PointOfInterest;
import com.codingcompetition.statefarm.utility.PointOfInterestParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreetMapDataInterpreter implements Interpreter {

 String myxmlfilepath = "";
    public StreetMapDataInterpreter(String s) {
    	this.myxmlfilepath = s;
    }
    
    

    @Override
    public List<PointOfInterest> interpret()  {
    	//Initialize a list of employees
        List<PointOfInterest> POIS = new ArrayList<PointOfInterest>();
        PointOfInterest singlePOI = null;
         
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        
        DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			System.out.println("Parser Configuration Error !!!!");
			e.printStackTrace();
		}
        
        Document document = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			document = builder.parse( new File(classLoader.getResource(myxmlfilepath.substring(1)).getFile()));
		} catch (SAXException | IOException e ) {
			// TODO Auto-generated catch block
			System.out.println("SAXE Exception oR IO Exception !!!!");
			e.printStackTrace();
		}
		catch(Exception e2) {
			System.out.println("Other Exceptions" );
			e2.printStackTrace();
		}
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("node");
    /*    for (int temp = 0; temp < nList.getLength(); temp++) */
        for (int temp = 0; temp <  nList.getLength(); temp++)
        {
           Node node = nList.item(temp);
           if (node.getNodeType() == Node.ELEMENT_NODE)
           {
              Element eElement = (Element) node;
              //Create new Employee Object
              singlePOI = new PointOfInterest();
              
		              ////Read Node details first 
		      //        System.out.println(eElement.getAttribute("lat"));
		      //        System.out.println(eElement.getAttribute("lon"));
		              singlePOI.setLatitude(eElement.getAttribute("lat"));
		              singlePOI.setLongitude(eElement.getAttribute("lon"));
		              //Read Node Details finished///
              
              //Read Sub Node - Tag details now//
              NodeList list2=  eElement.getElementsByTagName("tag");
              for (int t2 = 0; t2 < list2.getLength(); t2++)
              {
            	  Element eElement_sub = (Element) list2.item(t2);
           // 	  System.out.println(eElement_sub.getAttribute("k"));
           // 	  System.out.println(eElement_sub.getAttribute("v"));
            	  
            	  singlePOI.setK(eElement_sub.getAttribute("k"));
            	  singlePOI.setV(eElement_sub.getAttribute("v"));
              
              }
             
              
              
           /*   singlePOI.setId(Integer.parseInt(eElement.getAttribute("id")));
              singlePOI.setFirstName(eElement.getElementsByTagName("firstName").item(0).getTextContent());
              singlePOI.setLastName(eElement.getElementsByTagName("lastName").item(0).getTextContent());
              singlePOI.setLocation(eElement.getElementsByTagName("location").item(0).getTextContent());
            */   
              //Add Employee to list
              POIS.add(singlePOI);
           }
        }
        return POIS;
    }

    @Override
    public List<PointOfInterest> interpret(SearchCriteria criteria) {
    	if(criteria == null) {
    	List<PointOfInterest> POIS = new ArrayList<PointOfInterest>();
    	
    	return POIS;
    	}
    	return null;
    }

    @Override
    public List<PointOfInterest> interpret(Map<Integer, SearchCriteria> prioritizedCriteria) {
        return null;
    }

    @Override
    public List<PointOfInterest> findByCriterias(List<SearchCriteria> criterias) {
        return null;
    }
}
