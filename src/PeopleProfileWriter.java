

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


import peoplereader.generated.People;

public class PeopleProfileWriter {
	public static People people = new People();
	public static List<People.Person>  list;
	//Here there are random names and surnames for creating random people
	private static String[] givenNames = new String[] {
	        "Ada", "Albert", "Alexandra", "Alfredo", "Allen", "Andre", "Angelica",
	        "Anna", "Anthony", "Antonio", "Ashley", "Audrey", "Beatrice",
	        "Benjamin", "Billy", "Bobby", "Bradley", "Bryant", "Candace",
	        "Carole", "Carrie", "Claire", "Clifford", "Clint", "Clyde", "Cory",
	        "Dale", "Danielle", "Daryl", "Delia", "Devin", "Douglas", "Eddie",
	        "Ella", "Erica", "Erika", "Eva", "Frank", "Gayle", "George", "Georgia",
	        "Geraldine", "Gina", "Gwen", "Hector", "Homer", "Irene", "James",
	        "Jamie", "Jeremiah", "Joann", "Josefina", "Juan", "Karen", "Kenneth",
	        "Laurie", "Lee", "Leland", "Leroy", "Levi", "Lewis", "Lillian",
	        "Lillie", "Lorenzo", "Louise", "Lucas", "Lynn", "Marc", "Marcella",
	        "Marlon", "Marvin", "Micheal", "Miranda", "Miriam", "Misty", "Naomi",
	        "Natasha", "Nelson", "Oliver", "Pete", "Rafael", "Randall", "Raul",
	        "Rebecca", "Reginald", "Roger", "Ruby", "Rufus", "Sabrina", "Sean",
	        "Steven", "Stuart", "Terence", "Terry", "Van", "Velma", "Vincent",
	        "Wanda", "Willard", "Winifred"
	    };
	    private static String[] surnames = new String[] {
	        "Adkins", "Aguilar", "Anderson", "Armstrong", "Arnold", "Bailey",
	        "Banks", "Barrett", "Bates", "Bennett", "Bowers", "Bradley", "Brown",
	        "Bryant", "Buchanan", "Bush", "Butler", "Cain", "Carlson", "Carroll",
	        "Cummings", "Diaz", "Doyle", "Duncan", "Dunn", "Fernandez", "Foster",
	        "Fowler", "Fox", "Francis", "French", "Garrett", "Gill", "Glover",
	        "Goodwin", "Gordon", "Grant", "Griffin", "Gross", "Guerrero", "Hale",
	        "Harvey", "Holland", "Ingram", "Jacobs", "James", "Lamb", "Lowe",
	        "Lucas", "Mann", "Marshall", "Martin", "Martinez", "May", "Mcdaniel",
	        "Mendoza", "Meyer", "Moody", "Moreno", "Nelson", "Nichols", "Norton",
	        "Obrien", "Osborne", "Padilla", "Page", "Parks", "Parsons", "Payne",
	        "Pearson", "Powell", "Reese", "Reeves", "Reyes", "Reynolds",
	        "Richardson", "Rios", "Ross", "Russell", "Saunders", "Sharp", "Simon",
	        "Smith", "Steele", "Stephens", "Stokes", "Summers", "Thomas",
	        "Thompson", "Tyler", "Wagner", "Ward", "Washington", "Watkins",
	        "Watson", "Weber", "West", "Willis", "Young", "Zimmerman"
	    };
	    //With this method I read the existing XML, so I can append new users
	public static void retrieveXML() throws JAXBException, FileNotFoundException{
		JAXBContext jc = JAXBContext.newInstance(People.class);
	    
	    
	    Unmarshaller um = jc.createUnmarshaller();
	    people = (People) um.unmarshal(new FileReader("people.xml"));
	    list = people.getPerson();
	    
	}
	//calculates BMI, output is formatted as a String so I can put it directly in the constructor for a new person
	public static String calculateBMI(String h, String w){
		String bmi;
		float height=Float.parseFloat(h);
		float weight=Float.parseFloat(w);
		float bmiF = weight/(height*height);
		bmi = Float.toString(bmiF);
		
		
		return bmi;
		
	}
	//This method adds a new person, using fuction created with xjc
	public static void addPerson(String firstname, String lastname, XMLGregorianCalendar birthdate, String id, String weight, String height, XMLGregorianCalendar update){
		People.Person person = new People.Person();
		person.setFirstname(firstname);
		person.setBirthdate(birthdate);
		person.setLastname(lastname);
		person.setId(id);
		People.Person.Healthprofile hp = new People.Person.Healthprofile();
		hp.setBmi(calculateBMI(height,weight));
		hp.setHeight(height);
		hp.setWeight(weight);
		hp.setLastupdate(update);
		person.setHealthprofile(hp);
		list.add(person);
		
		
	}
	//This function creates a random date
	public static XMLGregorianCalendar createRandomDate() throws DatatypeConfigurationException{
		Random  rnd;
		Date    dt;
		long    ms;
		// Get a new random instance, seeded from the clock
		rnd = new Random();

		// Get an Epoch value roughly between 1940 and 2010
		// -946771200000L = January 1, 1940
		// Add up to 70 years to it (using modulus on the next long)
		ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));

		// Construct a date
		dt = new Date(ms);
		
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(dt);
		XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		return date;
	}
	
	static Random rand = new java.util.Random();
	//Choose a random element in an array of Strings (for creating random name/surnames)
	private static String randomChoice(String[] names) {
        
		return names[rand.nextInt(names.length)];
    }
	//Creates a random integer given a lower and upper bound)
	public static String randInt(int min, int max) {

	    // NOTE: This will (intentionally) not run as written so that folks
	    // copy-pasting have to think about how to initialize their
	    // Random instance.  Initialization of the Random instance is outside
	    // the main scope of the question, but some decent options are to have
	    // a field that is initialized once and then re-used as needed or to
	    // use ThreadLocalRandom (if using at least Java 1.7).
	   

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    String s;
	    s = Integer.toString(randomNum);
	    return s;
	}
	//Creates a Random height (between 1.0 and 2.0)
	public static String createHeight(){
		String h;
		double random = new Random().nextDouble();
		random += 1.0;
		h = Double.toString(random);
		return h;
	}
	
	public static void main(String[] args) throws Exception {
		retrieveXML();
		//Here I retrieve the last id of the Xml file
		String lastId = list.get(list.size()-1).getId();
		long sequence = Long.parseLong(lastId);
		//Here I check the input args
		if(args.length==1){
		int num = Integer.parseInt(args[0]);
		for(int i=0;i<num;i++){
			//I increase the id number, because the value has to be unique
		sequence ++;
		//I add a random person
		addPerson(randomChoice(givenNames),randomChoice(surnames),createRandomDate(),Long.toString(sequence),randInt(50,120),createHeight(),createRandomDate());
		}
		//Start marshalling
		JAXBContext jc = JAXBContext.newInstance(People.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(people,new File("people.xml")); // marshalling into a file
        m.marshal(people, System.out);
		}else{
			//wrong args
			System.out.println("input error");
		}
		
		
	}
}
