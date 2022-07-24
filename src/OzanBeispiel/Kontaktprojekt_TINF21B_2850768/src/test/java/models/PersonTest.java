package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void convertTest() {
        Person expected = new Person(1,"Milan");

        Person result = Person.convert("\"1\",\"Milan\"");

        assertEquals(expected.getId(),result.getId());
        assertEquals(expected.getName(),result.getName());
    }

}