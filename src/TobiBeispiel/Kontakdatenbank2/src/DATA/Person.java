package DATA;

public class Person {
    // VARIABLES
    private int id;
    private String name;

    // GETTER/SETTER
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public Person(int _id, String _name) {
        this.id = _id;
        this.name = _name;
    }

}
