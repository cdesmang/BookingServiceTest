import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing Flights.java
 * @author Christina Desmangles
 */

public class FlightsTest {
    @BeforeClass
    public static void oneTimeSetup(){}

    @AfterClass 
    public static void oneTimeTearDown(){}

    @BeforeEach
    public void setup(){}

    @Test
    public void testSearchFlightC1() // Case 1: Both aspects of departure and arrival locations, and dates are found
    {
        Flights flights = Flights.getInstance();
        Date dDate = new Date(5, 11, 2022);
        Date aDate = new Date (5,11,2022);
        ArrayList<Flight> actual = flights.searchFlight("charlotte", "NC", "Boston", "MA",dDate , aDate);
        assertNotNull(actual);
    }

    @Test 
    public void testSearchFlightC2() // Case 2: Either aspect of either location or either date is not found
    {
        Flights flights = Flights.getInstance();
        Date dDate = new Date(5, 11, 2022);
        Date aDate = new Date (5,11,2022);
        ArrayList<Flight> actual = flights.searchFlight("charlotte", "NC", "Boston", "MA",dDate , aDate);
        assertNull(actual);
    }














}