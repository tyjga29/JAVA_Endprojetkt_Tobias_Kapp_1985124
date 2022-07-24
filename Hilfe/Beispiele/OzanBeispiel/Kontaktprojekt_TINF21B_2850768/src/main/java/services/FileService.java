package services;

import models.Location;
import models.Person;
import models.Visit;
import util.Type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileService {

    private final List<Person> people = new ArrayList<>();
    private final List<Location> locations = new ArrayList<>();
    private final List<Visit> visits = new ArrayList<>();

    /**
     * reads the file and adds its contents to the corresponding Lists
     * @param fileURL
     */
    public void readFile(URL fileURL){
        try{
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            fileURL.openStream()));
            String line;
            Type type = Type.PERSON;
            while ((line = in.readLine()) != null) {
                if(line.contains("New_Entity")){
                    if (line.contains("person_name")) {
                        type = Type.PERSON;
                    } else if (line.contains("location_name")) {
                        type = Type.LOCATION;
                    } else if (line.contains("start_date")) {
                        type = Type.VISIT;
                    }
                }else{
                    switch (type) {
                        case PERSON -> addToPersonList(line);
                        case LOCATION -> addToLocationList(line);
                        case VISIT -> addToVisitList(line);
                    }
                }
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToPersonList(String line) {
        Person toAddPerson = Person.convert(line);
        if(!people.contains(toAddPerson)){
            people.add(toAddPerson);
        }
    }

    private void addToLocationList(String line) {
        Location toAddLocation = Location.convert(line);
        if(!locations.contains(toAddLocation)){
            locations.add(toAddLocation);
        }
    }

    private void addToVisitList(String line) {
        Visit toAddVisit = convertVisit(line);
        if(!visits.contains(toAddVisit)){
            visits.add(toAddVisit);
        }
    }

    /**
     * Convert the rawData into a Visit with a Person, a Visit and the Timeframe
     * @param rawData data that has to be Converted
     * @return new Visit
     */
    protected Visit convertVisit(String rawData){
        String[] data = rawData.replace("\"","").split(",");
        int personId = Integer.parseInt(data[2]);
        int locationId = Integer.parseInt(data[3]);
        return new Visit(
                people.stream().filter(person -> person.getId() == personId).findFirst().orElse(new Person(personId)),
                locations.stream().filter(location -> location.getId() == locationId).findFirst().orElse(new Location(locationId)),
                LocalDateTime.parse(data[0]),
                LocalDateTime.parse(data[1]));
    }

    public List<Person> getPeople() {
        return people.stream().sorted(Comparator.comparing(Person::getName)).distinct().toList();
    }

    public List<Location> getLocations() {
        return locations.stream().sorted(Comparator.comparing(Location::getName)).distinct().toList();
    }

    public List<Visit> getVisits() {
        return visits.stream().distinct().toList();
    }
}
