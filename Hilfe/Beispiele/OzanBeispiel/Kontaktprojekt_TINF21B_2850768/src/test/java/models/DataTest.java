package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    @Test
    void nameDoesContainTest() {
        Data data = new Data(0, "Milan");
        boolean expected = true;

        boolean result = data.nameContains("ila");

        assertEquals(expected, result);
    }

    @Test
    void nameDoesContainCaseSensitiveTest() {
        Data data = new Data(0, "Milan");
        boolean expected = true;

        boolean result = data.nameContains("IlA");

        assertEquals(expected, result);
    }

    @Test
    void nameDoesNotContainTest() {
        Data data = new Data(0, "Milan");
        boolean expected = false;

        boolean result = data.nameContains("mia");

        assertEquals(expected, result);
    }

    @Test
    void isEqualTest(){
        boolean expected = true;
        Data data = new Data(1, "Milan");

        //equal only checks if at a base minimum the ids are equal
        boolean resultSameData =  data.equals(new Data(1, "garbage"));

        assertEquals(expected,resultSameData);

    }

    @Test
    void isNotEqualTest(){
        boolean expected = false;
        Data data = new Data(1, "Milan");

        //equal only checks if at a base minimum the ids are equal
        boolean result =  data.equals(new Data(2, "Milan"));

        assertEquals(expected,result);

    }
}