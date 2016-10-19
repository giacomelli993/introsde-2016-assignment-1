

import java.io.File;
import java.text.ParseException;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import peoplereader.generated.*;

public class PeopleProfileReaderJson {
	public static People people = new People();
	public static List<People.Person>  list;
	public static ObjectMapper mapper = new ObjectMapper();
	//this method Prints in a readable way the object People.person
	public static String toString(People.Person person) throws ParseException{
    	
    	return ("ID :\t\t"+person.getId()+"\n"
    			+"Name :\t\t"+person.getFirstname()+"\n"
    			+"Last name :\t\t"+person.getLastname()+"\n"
    			+"BirthDay :\t\t"+person.getBirthdate()+"\n"
    			+"Last Update :\t"+person.getHealthprofile().getLastupdate()+"\n"
    			+"Weight :\t\t"+person.getHealthprofile().getWeight()+"\n"
    			+"Height :\t\t"+person.getHealthprofile().getHeight()+"\n"
    			+"BMI :\t\t"+person.getHealthprofile().getBmi()+"\n"
    			
    	);
    }
	public static void main(String[] args) throws Exception {
		// Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		// configure as necessary
		mapper.registerModule(module);
 		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
	   

	    people = (People) mapper.readValue(new File("people.json"), People.class);;
	    list = people.getPerson();
    
    
	    //Printing all the People
	    for (People.Person person : list) {
	    	System.out.println(toString(person));
	    }
}
}