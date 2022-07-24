package DATA;

public class Visit {
    // VARIABLES
    private String start_date;
    private String end_date;
    private int person_id;
    private int location_id;

    // GETTER/SETTER
    public String getStart_date() {
        return start_date;
    }
    public String getEnd_date() {
        return end_date;
    }
    public int getPerson_id() {
        return person_id;
    }
    public int getLocation_id() {
        return location_id;
    }

    // OBJECT
    public Visit(String _startDate, String _endDate, int _personId, int _locationId) {
        this.start_date = _startDate;
        this.end_date = _endDate;
        this.person_id = _personId;
        this.location_id = _locationId;
    }

}
