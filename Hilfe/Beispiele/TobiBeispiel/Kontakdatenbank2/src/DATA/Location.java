package DATA;

public class Location {
    private int id;
    private String name;
    private String inDoor;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getInDoor() {
        return inDoor;
    }

    public Location(int _id, String _name, String _inDoor) {
        this.id = _id;
        this.name = _name;
        this.inDoor = _inDoor;
    }
}
