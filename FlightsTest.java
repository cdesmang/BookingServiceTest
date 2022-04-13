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
        if (flights != null){
        Date dDate = new Date(5, 11, 2022);
        Date aDate = new Date (5,11,2022);
        ArrayList<Flight> actual = flights.searchFlight("charlotte", "NC", "Boston", "MA",dDate , aDate);
        assertNotNull(actual);
        }
    }

    @Test 
    public void testSearchFlightC2() // Case 2: departure location is not found
    {
        Flights flights = Flights.getInstance();
        if (flights != null){
        Date dDate = new Date(5, 11, 2022);
        Date aDate = new Date (5,11,2022);
        ArrayList<Flight> actual = flights.searchFlight("greenville", "NC", "Boston", "MA",dDate , aDate);
        assertNull(actual);
        }
    }

    @Test
    public void testSearchFlightC3() // Case 3: arrival location is not found
    {
        Flights flights = Flights.getInstance();
        if (flights != null){
        Date dDate = new Date(5, 11, 2022);
        Date aDate = new Date (5,11,2022);
        ArrayList<Flight> actual = flights.searchFlight("charlotte", "NC", "chicago", "IL",dDate , aDate);
        assertNull(actual);
        }
    }

    @Test 
    public void testSearchFlightC4()// Case 4: departure date is not found
    {
        Flights flights = Flights.getInstance();
        if (flights != null){
        Date dDate = new Date(5,12, 2022);
        Date aDate = new Date (5,11,2022);
        ArrayList<Flight> actual = flights.searchFlight("greenville", "NC", "Boston", "MA",dDate , aDate);
        assertNull(actual);
        }
    }

    @Test 
    public void testSearchFlightC5()// Case 5: arrival date is unfound
    {
        Flights flights = Flights.getInstance();
        if (flights != null){
        Date dDate = new Date(5,11, 2022);
        Date aDate = new Date (5,12,2022);
        ArrayList<Flight> actual = flights.searchFlight("greenville", "NC", "Boston", "MA",dDate , aDate);
        assertNull(actual);
        }
    }

    /*@Test 
    public void testSearchOneFlightC1() // Case 1: flight with matching airline and number is found
    {
        Flight[] actual = new Flight[1];
        Flight[] temp = new Flight[2];

        Flights flights = Flights.getInstance();
        
    }*/

    @Test
    public void testGetFlights()
    {
        Flights flights = Flights.getInstance();
        if (flights != null){
        ArrayList<Flight> expected = DataLoader.loadFlights();
        ArrayList<Flight> actual = flights.getFlights();
        assertEquals(expected, actual);
        }
    }

    @Test 
    public void testCheckConnectionC1()// Case 1: there is no connection found
    {
        Flights flights = Flights.getInstance();
        if (flights != null){
        Date dDate = new Date(5,11, 2022);
        Date aDate = new Date (5,11,2022);
        ArrayList<Flight> x = flights.searchFlight("charlotte", "NC", "boston", "MA", dDate, aDate);
        Date dDate2 = new Date(5,15, 2022);
        Date aDate2 = new Date (5,16,2022);
        ArrayList<Flight> y = flights.searchFlight("chicago","IL","houston","TX",dDate2,aDate2);
        ArrayList<Flight> actual = flights.checkConnection(x, y);
        assertNull(actual);
        }
    }

    @Test
    public void testCheckConnectionC2() // Case 2: There is a connection found
    {
        Flights flights = Flights.getInstance();
        if (flights != null){
        Date Date1 = new Date(11,31, 2022);
        Date Date2 = new Date(3, 24, 2022);
        ArrayList<Flight> x = flights.searchFlight("charlotte","NC", "columbia","SC", Date2 ,Date2);
        ArrayList<Flight> y = flights.searchFlight("columbia", "SC", "charlotte", "NC", Date1, Date1);
        ArrayList<Flight> actual = flights.checkConnection(x, y);
        assertNotNull(actual);
        }
    }

    @Test 
    public void testCheckALC1()// Case 1: The arrival location matches the given flight
    {
        Flights flights = Flights.getInstance();
        if (flights != null){
        boolean actual = flights.checkAL("columbia", "SC", 1);
        assertEquals(true, actual);
        }
    }

    @Test 
    public void testCheckALC2() //Case 2: The arrival location does not match the given flight
    {
        Flights flights = Flights.getInstance();
        if (flights != null){
        boolean actual = flights.checkAL("houston","TX", 1);
        assertEquals(false, actual);
        }
    }

    @Test 
    public void testCheckDLC1()// Case 1: The departure location matches the given flight
    {
        Flights flights = Flights.getInstance();
        if (flights != null){
        boolean actual = flights.checkDL("charlotte", "NC", 1);
        assertEquals(true, actual);
        }
    }

    @Test 
    public void testCheckDLC2() //Case 2: The departure location does not match the given flight
    {
        Flights flights = Flights.getInstance();
        if (flights != null){
        boolean actual = flights.checkDL("houston","TX", 1);
        assertEquals(false, actual);
        }
    }

    @Test
    public void testCheckDDC1() // Case 1: The given date matches the departure date of the flight
    {
        Flights flights = Flights.getInstance();
        if (flights != null){
        Date date = new Date(3, 24, 2022);
        boolean actual = flights.checkDD(date, 1);
        assertEquals(true, actual);
        }
    }

    @Test
    public void testCheckDDC2() // Case 2: The given date not match the departure date of the flight
    {   Flights flights = Flights.getInstance();
        if (flights != null){
            Date date = new Date(3, 25, 2022);
            boolean actual = flights.checkDD(date, 1);
            assertEquals(false, actual);
        }
    }


   /* @Test 
    public void testAddFlight()
    {
        Seat seat1 = new Seat(1,"A",true);
        Seat seat2 = new Seat(1,"B",true);
        Seat seat3 = new Seat(1,"C", false);
        Seat seat4 = new Seat(1,"D",false);
        ArrayList<Seat> seats = new ArrayList<Seat>();
        seats.add(seat1);
        seats.add(seat2);
        seats.add(seat3);
        seats.add(seat4);
        Date date = new Date (2,1, 2023);
        UUID ID = 
        Flights.getInstance().addFlight(UUID, 1111, "Delta", "New Orleans","LA", "Atlanta", "GA",date , date,"3hrs", "5:00PM", "8:00PM", seats);
    }*/













}