package Java8Homework;

import org.junit.Test;
import java.text.ParseException;
import static org.junit.Assert.*;

public class MainTest {


    @Test
    public void testFileInput() throws ParseException {
        //given
        String inputLine = "Mirela,Ionescu,25.06.1989";

        //when
        Person result = Main.getPeopleFromFile(inputLine);
        //then
        assert result != null;
        assertEquals("Mirela", result.getFirstName());
        assertEquals("Ionescu", result.getLastName());
    }

    @Test
    public void testMonthExtractionAsInt() throws ParseException {
        //given
        String inputLine = "Oana,Filcescu,11.03.1999";
        //when
        Person result = Main.getPeopleFromFile(inputLine);
        //then
        assert result != null;
        assertEquals(3, result.getMonthOfBirth());

    }

    @Test
    public void testWhitespaceInput() throws ParseException {
        //given
        String inputLine = "Oana , Filcescu,11.03.1999 ";
        //when
        Person result = Main.getPeopleFromFile(inputLine);
        //then
        assert result != null;
        assertEquals("Oana", result.getFirstName());
        assertEquals("Filcescu", result.getLastName());
        assertEquals(3, result.getMonthOfBirth());

    }
    @Test (expected = ParseException.class)
    public void testFailsWithParseException() throws ParseException {
        //given
        String inputLine = "Oana,Filcescu,11.0 3.19 99 ";
        //when
        Person result = Main.getPeopleFromFile(inputLine);
        //then

    }
}