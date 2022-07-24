package services;

import models.Person;
import models.Location;
import models.Visit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileServiceTest {

    private final FileService fileService = new FileService();

    @BeforeAll
    public void setup() {
        URL fileUrl = getClass().getClassLoader().getResource("testcontacts.txt");
        fileService.readFile(fileUrl);
    }

    @Test
    void getPeopleTest() {
        List<Person> expected = List.of(
                Person.convert("\"4\",\"ERs\""),
                Person.convert("\"3\",\"Johnny\""),
                Person.convert("\"1\",\"Mila\""),
                Person.convert("\"2\",\"Milan\""),
                Person.convert("\"5\",\"Test\"")
        );

        List<Person> result = fileService.getPeople();

        assertEquals(expected, result);
    }

    @Test
    void getLocationsTest() {
        List<Location> expected = List.of(
                Location.convert("\"3\",\"Mar\", \"out_door\""),
                Location.convert("\"1\",\"Markt\",\"in_door\""),
                Location.convert("\"2\",\"Supermarkt\",\"in_door\"")
        );

        List<Location> result = fileService.getLocations();

        assertEquals(expected, result);
    }

    /*
    @Test
    void getVisitsTest() {
        List<Visit> expected = List.of(
                Visit.convert("\"2021-05-15T15:00:00\",\"2021-05-15T16:00:00\",\"1\",\"1\""),
                Visit.convert("\"2021-05-15T15:00:00\",\"2021-05-15T16:00:00\",\"3\",\"1\""),
                Visit.convert("\"2021-05-15T15:00:00\",\"2021-05-15T16:00:00\",\"1\",\"3\""),
                Visit.convert("\"2021-05-15T15:00:00\",\"2021-05-15T16:00:00\",\"3\",\"3\"")
        );

        List<Visit> result = fileService.getVisits();

        assertEquals(expected, result);
    }
     */
}