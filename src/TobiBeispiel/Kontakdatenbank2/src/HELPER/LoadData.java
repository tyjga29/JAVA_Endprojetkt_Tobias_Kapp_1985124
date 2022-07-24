package HELPER;

import DATA.Location;
import DATA.Person;
import DATA.Visit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoadData {
    // VARIABLES
    // --------------------------------------------------------------------------------
    HashMap<Integer, Person> personHashMap = new HashMap<Integer, Person>();
    HashMap<Integer, Location> locationHashMap = new HashMap<Integer, Location>();
    List<Visit> visitList = new ArrayList<>();

    // GETTER/SETTER
    // --------------------------------------------------------------------------------
    public HashMap<Integer, Person> getPersonHashMap() {
        return personHashMap;
    }
    public HashMap<Integer, Location> getLocationHashMap() {
        return locationHashMap;
    }
    public List<Visit> getVisitList() {
        return visitList;
    }

    // DATA TYPES
    // --------------------------------------------------------------------------------
    private enum Data {
        NONE,
        PERSON,
        LOCATION,
        VISIT
    }

    // METHODS
    // --------------------------------------------------------------------------------
    public void readFile(String _fileName)
    {
        try (BufferedReader bf =
                     new BufferedReader(
                             new FileReader(_fileName)))
        {
            String result = "";
            String line;

            Data obj = Data.NONE;

            while((line = bf.readLine()) != null)
            {
                if(line.equals("New_Entity: \"person_id\",\"person_name\""))
                {
                    obj = Data.PERSON;

                }
                else if(line.equals("New_Entity: \"location_id\",\"location_name\",\"in_door\""))
                {
                    obj = Data.LOCATION;
                }
                else if(line.equals("New_Entity: \"start_date\", \"end_date\", \"person_id\", \"location_id\""))
                {
                    obj = Data.VISIT;
                }
                else
                {
                    switch (obj)
                    {
                        case PERSON:
                            String[] stringsPerson = line.split(",");

                            // person_id, person_name
                            int person_id = Integer.parseInt(stringsPerson[0].replace("\"", ""));
                            String person_name = stringsPerson[1].replace("\"", "");
                            // put to hashMap (person)
                            if(personHashMap.get(person_id) == null)
                            {
                                personHashMap.put(person_id, new Person(person_id, person_name));
                            }
                            else
                            {
                                // ID already exits, do - nothing
                            }
                            break;

                        case LOCATION:
                            String[] stringsLocation = line.split(",");

                            // LocationId, locationName, inDoor
                            int location_id = Integer.parseInt(stringsLocation[0].replace("\"", ""));
                            String location_name = stringsLocation[1].replace("\"", "");
                            String in_door = stringsLocation[2].replace("\"", "");
                            // put to hashMap (location)
                            if(locationHashMap.get(location_id) == null)
                            {
                                locationHashMap.put(location_id, new Location(location_id, location_name, in_door));
                            }
                            else
                            {
                                // Location already exits, do - nothing
                            }
                            break;

                        case VISIT:
                            String[] stringsVisit = line.split(",");

                            // start_Date, end_date, person_id, location_id
                            String start_Date = stringsVisit[0].replace("\"", "");
                            String end_date = stringsVisit[1].replace("\"", "");
                            int visit_person_id = Integer.parseInt(stringsVisit[2].replace("\"", ""));
                            int visit_location_id = Integer.parseInt(stringsVisit[3].replace("\"", ""));
                            // Add to list (visit)
                            visitList.add(new Visit(start_Date, end_date, visit_person_id, visit_location_id));
                            break;

                        default:
                            break;

                    }
                }
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
