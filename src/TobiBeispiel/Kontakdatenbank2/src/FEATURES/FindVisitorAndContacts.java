package FEATURES;

import DATA.Location;
import DATA.Person;
import DATA.Visit;
import INTERFACES.FindArgs;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class FindVisitorAndContacts extends FindContactPersons implements FindArgs {
    // VARIABLES
    // --------------------------------------------------------------------------------
    private Integer returnArgLocationId;
    private LocalDateTime returnArgVisitorDate;

    // GETTER/SETTER
    // --------------------------------------------------------------------------------
    public Integer getReturnArgLocationId() {
        return returnArgLocationId;
    }

    public LocalDateTime getReturnArgVisitorDate() {
        return returnArgVisitorDate;
    }

    // METHODS
    // --------------------------------------------------------------------------------
    @Override
    public void FindArgs(String[] _args) {
        // INPUT STRING LOOKS LIKE THIS: --besucher=1,"2021-05-15T14:16:00"
        for (int i = 0; i < _args.length; i++) {
            if (_args[i].startsWith("--besucher=")) {
                String[] stringsContactPersonSearch = _args[i].split(",");
                returnArgLocationId = Integer.parseInt(stringsContactPersonSearch[0].split("=")[1]);
                String tempDate = stringsContactPersonSearch[1].replace("\"", "");
                returnArgVisitorDate = LocalDateTime .parse(tempDate);
            }
            else if(returnArgLocationId == null || returnArgVisitorDate == null)
            {
                returnArgLocationId = null;
                returnArgVisitorDate = null;
            }
            else
            {
                // smth different
            }
        }
    }

    // Input: locationId, visitorDate, visitList, LocationHashMap, personHashMap
    public LinkedHashMap<Integer, Person> FindVisitorAndContacts(Integer _LocationId, LocalDateTime _VisitorDate, List<Visit> _visitList, HashMap<Integer, Location> _locationHashMap, HashMap<Integer, Person> _personHashMap)
    {
        HashMap<Integer, Person> contactLocationsHashMap = new HashMap<>();
        LinkedHashMap<Integer, Person> mapSortedBasedOnName = new LinkedHashMap<>();

        if(_LocationId != null && _VisitorDate != null) {
            // go through list and find this location id
            for (int j = 0; j < _visitList.size(); j++) {
                if (_LocationId == _visitList.get(j).getLocation_id()) {
                    LocalDateTime inputDate = _VisitorDate;
                    LocalDateTime varStartTime = LocalDateTime.parse(_visitList.get(j).getStart_date());
                    LocalDateTime varEndTime = LocalDateTime.parse(_visitList.get(j).getEnd_date());
                    // if time is same or in between
                    if (inputDate.isBefore(varEndTime) && inputDate.isAfter(varStartTime)
                            || inputDate.isEqual(varStartTime)
                            || inputDate.isEqual(varEndTime)) {

                        String in_door = _locationHashMap.get(_LocationId).getInDoor();
                        if (in_door.equals("in_door")) {
                            // in door
                            contactLocationsHashMap.put(_visitList.get(j).getPerson_id(), _personHashMap.get(_visitList.get(j).getPerson_id()));
                            HashMap<Integer, Person> temp_listPerson = FindContactPersons(_visitList.get(j).getPerson_id(), _visitList, _locationHashMap, _personHashMap);
                            contactLocationsHashMap.putAll(temp_listPerson);
                        } else {
                            // out door
                            contactLocationsHashMap.put(_visitList.get(j).getPerson_id(), _personHashMap.get(_visitList.get(j).getPerson_id()));
                        }
                    }
                }
            }

            // Sort based on Name
            mapSortedBasedOnName = contactLocationsHashMap.entrySet().stream().sorted(Comparator.comparing(e -> e.getValue().getName())).
                    collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        }

        return mapSortedBasedOnName;
    }

    public static void DisplayContactLocations(HashMap<Integer, Person> _contactLocationsList)
    {
        String output;

        output = _contactLocationsList.values().stream().map(Person::getName).collect(Collectors.joining(", "));
        System.out.println(output);
    }
}
