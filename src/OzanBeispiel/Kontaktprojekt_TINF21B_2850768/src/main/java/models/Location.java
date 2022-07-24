package models;

public class Location extends Data{
    private boolean inDoor;

    public Location(int id) {
        super(id);
    }

    public Location(int id, String name, boolean inDoor) {
        super(id, name);
        this.inDoor = inDoor;
    }

    public boolean isInDoor() {
        return inDoor;
    }

    /**
     * converts rawData into a new Location Object
     * @param rawData
     * @return a new Location
     */
    public static Location convert(String rawData) {
        String[] data = rawData.replace("\"","").split(",");
        return new Location(Integer.parseInt(data[0]), data[1], data[2].equals("in_door"));
    }

}
