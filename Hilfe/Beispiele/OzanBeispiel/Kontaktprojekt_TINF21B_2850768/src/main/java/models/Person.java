package models;

public class Person extends Data {

    public Person(int id) {
        super(id);
    }

    public Person(int id, String name) {
        super(id, name);
    }

    /**
     * converts rawData into a new Person Object
     * @param rawData
     * @return a new Person
     */
    public static Person convert(String rawData) {
        String[] data = rawData.replace("\"","").split(",");
        return new Person(Integer.parseInt(data[0]), data[1]);
    }
}
