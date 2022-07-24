package services;

import models.Person;
import models.Location;
import models.Visit;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DataService{
    private List<Person> people;
    private List<Location> locations;
    private List<Visit> visits;

    public DataService(URL fileUrl){
        FileService fileService = new FileService();
        fileService.readFile(fileUrl);
        this.people = fileService.getPeople();
        this.locations = fileService.getLocations();
        this.visits = fileService.getVisits();
    }
    /**
        Returns a list containing all the people whose name contains the searchQuery.
        @param searchQuery - a String used to compare to the names of a person
        @return list
     */
    public List<Person> searchForPeople(String searchQuery){
        return people.stream().filter(person -> person.nameContains(searchQuery)).toList();
    }

    /**
     * Returns a list containing all the locations whose name contains the searchQuery
     * @param searchQuery a String used to compare to the names of a locations
     * @return a new List
     */
    public List<Location> searchForLocations(String searchQuery){
        return locations.stream().filter(location -> location.nameContains(searchQuery)).toList();
    }

    /**
     * Returns a sorted and distinct list containing all the people whose have been in contact with the person with a specified personId
     * @param personId
     * @returna new List
     */
    public List<Person> searchForContacts(int personId){
        Person patientZero = people.get(people.indexOf(new Person(personId)));

        //gets the initial Visits of the patientZero named zeroVisits
        List<Visit> zeroVisits = visits.stream()
                .filter(
                        visit -> visit.getPerson().equals(patientZero) && visit.getLocation().isInDoor()
                )
                .toList();

        //the list that will contain all the contacts
        List<Person> contacts = new ArrayList<>();

        // add all the contacts to the contactsList
        zeroVisits.forEach(
                zeroVisit -> contacts
                        .addAll(
                                //filter out all the visits that match the visits of the patientZero
                                visits.stream()
                                        .filter(
                                                visit ->!visit.getPerson().equals(patientZero) &&
                                                        visit.getLocation().equals(zeroVisit.getLocation()) &&
                                                        (visit.getStart().isBefore(zeroVisit.getEnd())|| visit.getStart().isEqual(zeroVisit.getEnd())) &&
                                                        (visit.getEnd().isAfter(zeroVisit.getStart())|| visit.getEnd().isEqual(zeroVisit.getStart()))
                                        )
                                        //map them to the Person who is related to the visit
                                        .map(Visit::getPerson)
                                        //remove duplicated (only works because the equals function has been overwritten)
                                        .distinct()
                                        .toList()
                )
        );
        //sort the list by name and remove doubles
        return contacts.stream().distinct().sorted(Comparator.comparing(Person::getName)).toList();
    }

    /**
     * Returns a sorted and distinct list containing all the people that have visited a location at a specific time (if location is in-door the contacts are added too)
     * @param locationId
     * @param time
     * @return a new List
     */
    public List<Person> searchForVisitors(int locationId, LocalDateTime time){
        Location locationZero = locations.get(locations.indexOf(new Location(locationId)));
        //get Visitors of the location at the specified time
        List<Person> zeroVisitors = visits.stream()
                .filter(
                        visit -> visit.getLocation().equals(locationZero) &&
                                (
                                        visit.getStart().isBefore(time) ||
                                                visit.getStart().isEqual(time)
                                ) &&
                                (
                                        visit.getEnd().isAfter(time) ||
                                                visit.getEnd().isEqual(time)
                                )
                )
                .map(Visit::getPerson)
                .distinct()
                .sorted(Comparator.comparing(Person::getName))
                .toList();

        // add contact if the location is inDoor
        if(locationZero.isInDoor()){
            List<Person> contacts = new ArrayList<>();
            contacts.addAll(zeroVisitors);
            zeroVisitors.forEach(
                    person -> contacts.addAll(searchForContacts(person.getId()))
            );
            return contacts.stream().distinct().sorted(Comparator.comparing(Person::getName)).toList();
        }else{
            return zeroVisitors;
        }
    }
}
