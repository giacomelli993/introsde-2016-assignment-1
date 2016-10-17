


/**
 * Based on code made by Muhammad Imran
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;


import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PeopleProfileSearcher {

    Document doc;
    XPath xpath;

    //Here I load the XML file, so I can use it later
    public String loadXML() throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        
        
       File f = new File("people.xml");
        doc = builder.parse(new FileInputStream(f));

        //creating xpath object
        getXPathObj();
        return "XML loaded";
        
    }

    public XPath getXPathObj() {

        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
        return xpath;
    }

    //method for getting weight given the id 
    public String getWeight(String id) throws XPathExpressionException {

        XPathExpression expr = xpath.compile("/people/person[@id='"+id+"']/healthprofile/weight");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
       
        return node.getTextContent();
    }
  //method for getting height given the id 
    public String getHeight(String id) throws XPathExpressionException {

        XPathExpression expr = xpath.compile("/people/person[@id='"+id+"']/healthprofile/height");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
       
        return node.getTextContent();
    }
    //This method prints the health profile given an input id
    private void printHealthProfile(String id) throws XPathExpressionException{
    	XPathExpression expr = xpath.compile("/people/person[@id='"+id+"']/healthprofile");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        Element element = (Element) node;
    	System.out.println(
    			"Last Update :\t"+element.getElementsByTagName("lastupdate").item(0).getTextContent()+"\n"
    			+"Weight :\t"+element.getElementsByTagName("weight").item(0).getTextContent()+"\n"
    			+"Height :\t"+element.getElementsByTagName("height").item(0).getTextContent()+"\n"
    			+"BMI :\t\t"+element.getElementsByTagName("bmi").item(0).getTextContent()+"\n"
    			
    	);
        
    }
    //this method prints all the elements of the XML
    private void printAll() throws XPathExpressionException, ParseException{
    	XPathExpression expr = xpath.compile("/people/person");
    	NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
    	for (int i = 0; i < nodeList.getLength(); i++) {
    	    System.out.println(this.toString(nodeList.item(i))); 
    	}
    }
    //This method prints the node, in a more readable way (Works only with person)
    private String toString(Node node) throws ParseException{
    	Element element = (Element) node;
    	return ("ID :\t\t"+element.getAttribute("id")+"\n"
    			+"Name :\t\t"+element.getElementsByTagName("firstname").item(0).getTextContent()+"\n"
    			+"Last name :\t"+element.getElementsByTagName("lastname").item(0).getTextContent()+"\n"
    			+"BirthDay :\t"+element.getElementsByTagName("birthdate").item(0).getTextContent()+"\n"
    			+"Last Update :\t"+element.getElementsByTagName("lastupdate").item(0).getTextContent()+"\n"
    			+"Weight :\t"+element.getElementsByTagName("weight").item(0).getTextContent()+"\n"
    			+"Height :\t"+element.getElementsByTagName("height").item(0).getTextContent()+"\n"
    			+"BMI :\t\t"+element.getElementsByTagName("bmi").item(0).getTextContent()+"\n"
    			
    	);
    }
    //This method prints person with the weight requested in input (could be lower, greater or equal)
    public void getWeightOperator(String operator) throws XPathExpressionException, ParseException {

    	XPathExpression expr = xpath.compile("/people/person[healthprofile/weight"+operator+"]");
    	NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
    	for (int i = 0; i < nodeList.getLength(); i++) {
    	    System.out.println(this.toString(nodeList.item(i))); 
    	}
       
        
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, ParseException{
    	PeopleProfileSearcher test = new PeopleProfileSearcher();
    	test.loadXML();
    
    
   
    	//System.out.println(test.toString(n) );
    	//System.out.println(test.getWeight("3"));
    	if(args.length==2){
    	String id = args[0];
    	String param = args[1];
    	System.out.println("Print all the People");
    	test.printAll();
    	System.out.println("Print health profile with id = "+id);
    	test.printHealthProfile(id);
    	System.out.println("Print all the People with weight "+param);
    	test.getWeightOperator(param);
    	}else{
    		System.out.println("Input error");
    	}
}
    
}
