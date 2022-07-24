package services;

import models.Location;
import models.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataServiceTest {
    private DataService dataService;

    @BeforeAll
    public void setup(){
        URL fileUrl = getClass().getClassLoader().getResource("testcontacts.txt");
        dataService = new DataService(fileUrl);
    }

    @Test
    void searchForPeopleTest() {
        List<Person> expected = List.of(
                Person.convert("\"1\", \"Mila\""),
                Person.convert("\"2\", \"Milan\"")
        );

        List<Person> result = dataService.searchForPeople("ILa");

        assertEquals(expected, result);
    }

    @Test
    void searchForLocationsTest() {
        List<Location> expected = List.of(
                Location.convert("\"1\",\"Markt\",\"in_door\""),
                Location.convert("\"2\",\"Supermarkt\",\"in_door\"")
        );

        List<Location> result = dataService.searchForLocations("MaRk");

        assertEquals(expected, result);
    }

    @Test
    void searchForContactsTest() {
        List<Person> expected = List.of(
                Person.convert("\"3\", \"Johnny\"")
        );

        List<Person> result = dataService.searchForContacts(1);

        assertEquals(expected, result);
    }
}