import DATA.Location;
import DATA.Person;
import DATA.Visit;
import FEATURES.FindContactPersons;
import FEATURES.FindLocations;
import FEATURES.FindPersons;
import FEATURES.FindVisitorAndContacts;
import HELPER.LoadData;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LoadData loadData = new LoadData();
        FindPersons findPers = new FindPersons();
        FindLocations findLoc = new FindLocations();
        FindContactPersons findContPers = new FindContactPersons();
        FindVisitorAndContacts findVisitCont = new FindVisitorAndContacts();

        // DATA
        // --------------------------------------------------------------------------------
        HashMap<Integer, Person> personHashMap = loadData.getPersonHashMap();
        HashMap<Integer, Location> locationHashMap = loadData.getLocationHashMap();
        List<Visit> visitList = loadData.getVisitList();

        // METHODS
        // --------------------------------------------------------------------------------
        // LOAD DATA
        // Input: filepath + name
        loadData.readFile("E:/Dokumente/[11] Coding/Java/contacts2021.db.txt");

        // FIND PERSONS ----------
        findPers.FindArgs(args);
        String returnPersName = findPers.getReturnArg();
        // Input: name, personHashMap
        findPers.FindPersonsByName(returnPersName, personHashMap);

        // FIND LOCATIONS ----------
        findLoc.FindArgs(args);
        String returnlocName = findLoc.getReturnArg();
        // Input: location name, locationHashMap
        findLoc.FindLocationsByName(returnlocName, locationHashMap);

        // FIND CONTACT PERSONS ----------
        findContPers.FindArgs(args);
        Integer returnPersId = findContPers.getReturnArg();
        // Input: persId, visitList, locationHashMap, personHashMap
        findContPers.DisplayContactPersons(findContPers.FindContactPersons(returnPersId, visitList, locationHashMap, personHashMap));

        // FIND VISITOR AND CONTACTS ----------
        findVisitCont.FindArgs(args);
        Integer returnLocationId = findVisitCont.getReturnArgLocationId();
        LocalDateTime returnVisitorDate = findVisitCont.getReturnArgVisitorDate();
        // Input: locationId, visitorDate, visitList, LocationHashMap, personHashMap
        findVisitCont.DisplayContactLocations(
                findVisitCont.FindVisitorAndContacts(returnLocationId, returnVisitorDate, visitList, locationHashMap, personHashMap));

    }
}
