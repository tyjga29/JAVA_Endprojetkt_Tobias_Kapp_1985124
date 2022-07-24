package TESTS;

import DATA.Location;
import DATA.Person;
import DATA.Visit;
import FEATURES.FindContactPersons;
import FEATURES.FindLocations;
import FEATURES.FindPersons;
import FEATURES.FindVisitorAndContacts;
import HELPER.LoadData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Tests {
    // TEST FEATURES
    // --------------------------------------------------------------------------------
    @Test
    public void TestsFindPersons()
    {
        HashMap<Integer, Person> personHashMap =  new HashMap<>();
        // DECLARE TEST DATA
        personHashMap.put(1, new Person(1, "Mia"));
        personHashMap.put(2, new Person(2, "Emilia"));

        // INITIATE TEST METHOD
        FindPersons myFindPerson = new FindPersons();
        HashMap<Integer, Person> tempPersonHashMap = myFindPerson.FindPersonsByName("ia", personHashMap);

        for (int key : tempPersonHashMap.keySet()) {
            Assertions.assertEquals(key, tempPersonHashMap.get(key).getId());
            Assertions.assertEquals(personHashMap.get(key).getName(), tempPersonHashMap.get(key).getName());
        }
    }

    @Test
    public void TestsFindLocations()
    {
        HashMap<Integer, Location> locationHashMap = new HashMap<>();
        // DECLARE TEST DATA
        locationHashMap.put(1, new Location(1, "Bäckerei", "in_door"));
        locationHashMap.put(2, new Location(2, "Supermarkt", "in_door"));
        locationHashMap.put(6, new Location(6, "Großmarkt", "in_door"));

        // INITIATE TEST METHOD
        FindLocations myFindLocations = new FindLocations();
        HashMap<Integer, Location> tempLocationHashMap = myFindLocations.FindLocationsByName("Markt", locationHashMap);

        for (int key : tempLocationHashMap.keySet()) {
            Assertions.assertEquals(tempLocationHashMap.get(key).getId(), tempLocationHashMap.get(key).getId());
            Assertions.assertEquals(tempLocationHashMap.get(key).getName(), tempLocationHashMap.get(key).getName());
            Assertions.assertEquals(tempLocationHashMap.get(key).getInDoor(), tempLocationHashMap.get(key).getInDoor());
        }
    }

    @Test
    public void TestsFindContactPersons()
    {
        HashMap<Integer, Person> personHashMap = new HashMap<>();
        HashMap<Integer, Location> locationHashMap = new HashMap<>();
        HashMap<Integer, Person> predefinedContactPersons = new HashMap<>();
        List<Visit> visitList = new ArrayList<>();
        // DECLARE TEST DATA
        personHashMap.put(1, new Person(1, "Mia"));
        personHashMap.put(2, new Person(2, "Emilia"));
        personHashMap.put(3, new Person(3, "Hannah"));
        predefinedContactPersons.put(2, new Person(2, "Emilia"));
        predefinedContactPersons.put(3, new Person(3, "Hannah"));
        locationHashMap.put(1, new Location(1, "Bäckerei", "in_door"));

        visitList.add(0, new Visit("2021-05-15T14:00:00","2021-05-15T16:00:00",1,1));
        visitList.add(1, new Visit("2021-05-15T14:00:00","2021-05-15T14:00:01",2,1));
        visitList.add(2, new Visit("2021-05-15T14:05:00","2021-05-15T14:15:00",3,1));

        // INITIATE TEST METHOD
        FindContactPersons myFindContactPersons = new FindContactPersons();
        HashMap<Integer, Person> tempContactPersons = myFindContactPersons.FindContactPersons(1,visitList, locationHashMap, personHashMap);

        for (int key : tempContactPersons.keySet()) {
            Assertions.assertEquals(predefinedContactPersons.get(key).getId(), tempContactPersons.get(key).getId());
            Assertions.assertEquals(predefinedContactPersons.get(key).getName(), tempContactPersons.get(key).getName());
        }
    }

    @Test
    public void TestsFindVisitorAndContacts()
    {
        HashMap<Integer, Person> personHashMap = new HashMap<>();
        HashMap<Integer, Location> locationHashMap = new HashMap<>();
        HashMap<Integer, Person> predefinedVaC = new HashMap<>();
        List<Visit> visitList = new ArrayList<>();
        // DECLARE TEST DATA
        personHashMap.put(1, new Person(1, "Mia"));
        personHashMap.put(133, new Person(133, "Adam"));
        personHashMap.put(23, new Person(23, "Amelie"));
        personHashMap.put(49, new Person(49, "Carla"));
        predefinedVaC.put(1, new Person(1, "Mia"));
        predefinedVaC.put(133, new Person(133, "Adam"));
        predefinedVaC.put(23, new Person(23, "Amelie"));
        predefinedVaC.put(49, new Person(49, "Carla"));
        locationHashMap.put(1, new Location(1, "Bäckerei", "in_door"));
        visitList.add(0, new Visit("2021-05-15T14:00:00","2021-05-15T16:00:00",1,1));
        visitList.add(1, new Visit("2021-05-15T14:00:00","2021-05-15T14:00:01",133,1));
        visitList.add(2, new Visit("2021-05-15T14:05:00","2021-05-15T14:15:00",23,1));
        visitList.add(3, new Visit("2021-05-15T14:05:00","2021-05-15T14:15:00",49,1));

        // INITIATE TEST METHOD
        FindVisitorAndContacts myFindVisitorAndContacts = new FindVisitorAndContacts();
        LinkedHashMap<Integer, Person> tempFindVaC = myFindVisitorAndContacts.FindVisitorAndContacts(1, LocalDateTime.parse("2021-05-15T14:16:00"),visitList, locationHashMap, personHashMap);

        for (int key : tempFindVaC.keySet()) {
            Assertions.assertEquals(predefinedVaC.get(key).getId(), tempFindVaC.get(key).getId());
            Assertions.assertEquals(predefinedVaC.get(key).getName(), tempFindVaC.get(key).getName());
        }

    }

    // Test LoadData
    // --------------------------------------------------------------------------------
    @Test
    public void TestsLoadData()
    {
        HashMap<Integer, Person> personHashMap = new HashMap<Integer, Person>();
        HashMap<Integer, Location> locationHashMap = new HashMap<Integer, Location>();
        List<Visit> visitList = new ArrayList<>();
        // DECLARE TEST DATA
        personHashMap.put(1, new Person(1, "Mia"));
        personHashMap.put(2, new Person(2, "Emilia"));
        locationHashMap.put(1, new Location(1, "Bäckerei", "in_door"));
        locationHashMap.put(2, new Location(2, "Supermarkt", "in_door"));
        visitList.add(0, new Visit("2021-05-15T15:00:00","2021-05-15T16:00:00",1,1));
        visitList.add(1, new Visit("2021-05-15T14:00:00","2021-05-15T15:00:01",2,1));

        // LOAD DATA
        LoadData myLoadData = new LoadData();
        myLoadData.readFile("E:/Dokumente/[11] Coding/Java/contacts2021.db.txt");

        HashMap<Integer, Person> tempPersonHashMap = myLoadData.getPersonHashMap();
        HashMap<Integer, Location> tempLocationHashMap = myLoadData.getLocationHashMap();
        List<Visit> tempVisitList = myLoadData.getVisitList();

        for (int key : personHashMap.keySet()) {
            Assertions.assertEquals(personHashMap.get(key).getName(), tempPersonHashMap.get(key).getName());
        }

        for (int key : locationHashMap.keySet()) {
            Assertions.assertEquals(locationHashMap.get(key).getId(), tempLocationHashMap.get(key).getId());
            Assertions.assertEquals(locationHashMap.get(key).getName(), tempLocationHashMap.get(key).getName());
            Assertions.assertEquals(locationHashMap.get(key).getInDoor(), tempLocationHashMap.get(key).getInDoor());
        }

        for(int i = 0; i < visitList.size(); i++) {
            Assertions.assertEquals(visitList.get(i).getStart_date(), tempVisitList.get(i).getStart_date());
            Assertions.assertEquals(visitList.get(i).getEnd_date(), tempVisitList.get(i).getEnd_date());
            Assertions.assertEquals(visitList.get(i).getPerson_id(), tempVisitList.get(i).getPerson_id());
            Assertions.assertEquals(visitList.get(i).getLocation_id(), tempVisitList.get(i).getLocation_id());
        }
    }
}
