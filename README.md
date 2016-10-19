##Assignment 1 - Luca Giacomelli

There are 5 files. 
The PeopleProfileSearcher.java does all the tasks required in lab 3.
The PeopleProfileReader.java does the un-marshalling for XML.
The PeopleProfileReaderJson.java does the un-marshalling for Json.
The PeopleProfileWriter.java does the marshalling for XML.
The PeopleProfileWriterJson.java does the marshalling for Json.

##Further notes
Searcher and readers need the people.xml and people.json to be there. 
The writers check if a file (people.xml or people.json) exist and then they append new people to it. If a file does not exist, they create a new one and insert new people there.
For running the evaluation with ant simply write ant (execute.evaluation is the dafault task).
In build.xml it is possible to change parameters for the writers (only one parameter, that is the number of people you want to append) and for the searcher (First value is the id of the person whom health profile is printed (default = 5) and the second is the constraint regarding the weight (default >90)).


  
# introsde-2016-assignment-1
