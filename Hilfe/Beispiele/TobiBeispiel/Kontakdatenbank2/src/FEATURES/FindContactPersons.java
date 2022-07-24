package FEATURES;

import DATA.Location;
import DATA.Person;
import DATA.Visit;
import INTERFACES.FindArgs;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class FindContactPersons implements FindArgs {
    // VARIABLES
    // --------------------------------------------------------------------------------
    private Integer returnArg;

    // GETTER/SETTER
    // --------------------------------------------------------------------------------
    public Integer getReturnArg() {
        return returnArg;
    }

    // METHODS
    // --------------------------------------------------------------------------------
    @Override
    public void FindArgs(String[] _args) {
        // INPUT STRING LOOKS LIKE THIS: --kontaktpersonen=1
        for(int i = 0; i<_args.length; i++)
        {
            if (_args[i].startsWith("--kontaktpersonen=")) {
                String[] stringsContactPersonSearch = _args[i].split("=");
                returnArg = Integer.parseInt(stringsContactPersonSearch[1]);
            }
            else if(returnArg == null)
            {
                // cant find a right param
                returnArg = null;
            }
            else
            {
                // smth different ;)
            }
        }
    }

    // FIND CONTACT PERSONS
    // Input: id, visitList, locationHashMap, personHashMap
    public HashMap<Integer, Person> FindContactPersons(Integer _id, List<Visit> _visitList, HashMap<Integer, Location> _locationHashMap, HashMap<Integer, Person> _personHashMap)
    {
        HashMap<Integer, Person> personHashMap = new HashMap<>();

        if(_id != null) {
            // go through list and find person with this id
            for (int j = 0; j < _visitList.size(); j++) {
                if (_id == _visitList.get(j).getPerson_id()) {
                    LocalDateTime startTime = LocalDateTime.parse(_visitList.get(j).getStart_date());
                    LocalDateTime endTime = LocalDateTime.parse(_visitList.get(j).getEnd_date());
                    // go again through list and find person with same location id, in_door, and not the same id as the search id
                    for (int k = 0; k < _visitList.size(); k++) {
                        if (_visitList.get(j).getLocation_id() == _visitList.get(k).getLocation_id() &&
                                _locationHashMap.get(_visitList.get(k).getLocation_id()).getInDoor().equals("in_door") &&
                                    _id != _visitList.get(k).getPerson_id())
                        {
                            LocalDateTime varStartTime = LocalDateTime.parse(_visitList.get(k).getStart_date());
                            LocalDateTime varEndTime = LocalDateTime.parse(_visitList.get(k).getEnd_date());

                            if (startTime.isBefore(varEndTime) && endTime.isAfter(varStartTime)) {
                                personHashMap.put(_visitList.get(k).getPerson_id(), _personHashMap.get(_visitList.get(k).getPerson_id()));

                            }
                        }
                    }
                }
            }
        }
        return personHashMap;
    }

    public void DisplayContactPersons(HashMap<Integer, Person> _contactPersonsList)
    {
        String output = null;

        LinkedHashMap<Integer, Person> mapSortedBasedOnName = _contactPersonsList.entrySet().stream().sorted(Comparator.comparing(e -> e.getValue().getName())).
               collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2)->e1, LinkedHashMap::new));

        output = mapSortedBasedOnName.values().stream().map(Person::getName).collect(Collectors.joining(", "));
        System.out.println(output);
    }
}
