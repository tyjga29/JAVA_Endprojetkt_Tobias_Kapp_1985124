package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void convertTest() {
        Location expected = new Location(3,"Zoo",false);

        Location result = Location.convert("\"3\",\"Zoo\",\"out_door\"");

        assertEquals(expected.getId(),result.getId());
        assertEquals(expected.getName(),result.getName());
        assertEquals(expected.isInDoor(),result.isInDoor());
    }
}