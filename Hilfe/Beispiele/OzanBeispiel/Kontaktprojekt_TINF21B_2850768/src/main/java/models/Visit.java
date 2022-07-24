package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Visit{
    private Person person;
    private Location location;
    private LocalDateTime start;
    private LocalDateTime end;

    public Visit(Person person, Location location, LocalDateTime start, LocalDateTime end) {
        this.person = person;
        this.location = location;
        this.start = start;
        this.end = end;
    }

    public Person getPerson() {
        return person;
    }

    public Location getLocation() {
        return location;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * checks if value is the same based on properties
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        //normal equals is obj this object
        if(this == obj){
            return true;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        Visit visit = (Visit) obj;
        //Checks if all the Data is the same
        return Objects.equals(person, visit.person) && Objects.equals(location, visit.location) && Objects.equals(start, visit.start) && Objects.equals(end, visit.end);
    }
}
