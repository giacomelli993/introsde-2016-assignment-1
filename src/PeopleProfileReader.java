

import java.io.FileReader;
import java.text.ParseException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;



import peoplereader.generated.*;

public class PeopleProfileReader {
	//this method Prints in a readable way the object People.person
	public static String toString(People.Person person) throws ParseException{
    	
    	return ("ID :\t\t"+person.getId()+"\n"
    			+"Name :\t\t"+person.getFirstname()+"\n"
    			+"Last name :\t"+person.getLastname()+"\n"
    			+"BirthDay :\t"+person.getBirthdate()+"\n"
    			+"Last Update :\t"+person.getHealthprofile().getLastupdate()+"\n"
    			+"Weight :\t"+person.getHealthprofile().getWeight()+"\n"
    			+"Height :\t"+person.getHealthprofile().getHeight()+"\n"
    			+"BMI :\t\t"+person.getHealthprofile().getBmi()+"\n"
    			
    	);
    }
	public static void main(String[] args) throws Exception {
	JAXBContext jc = JAXBContext.newInstance(People.class);
    
    
    Unmarshaller um = jc.createUnmarshaller();
    People people = (People) um.unmarshal(new FileReader("people.xml"));
    List<People.Person> list = people.getPerson();
    
   
    for (People.Person person : list) {
      System.out.println(toString(person));
    }
}
}