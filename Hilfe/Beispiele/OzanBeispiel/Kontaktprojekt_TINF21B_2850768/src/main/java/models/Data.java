package models;

import java.util.Objects;

public class Data {
    private int id;
    private String name;

    public Data(int id) {
        this.id = id;
    }

    public Data(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * checks if the name contains the given query
     * @param query
     * @return boolean
     */
    public boolean nameContains(String query){
        return name.toLowerCase().contains(query.toLowerCase());
    }

    /**
     * checks if value is the same based on the id
     * @param obj
     * @return boolean
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
        Data data = (Data) obj;
        //is the ID for both Objects the same, is overwritten, because ids are unique so having the same id means it's the same person
        return Objects.equals(id, data.id);
    }
}
