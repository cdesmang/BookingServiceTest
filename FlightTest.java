import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Testing of Flight.java
 * @author Max Strickland
 */
public class FlightTest
{
    private Flights flights = Flights.getInstance();
    private ArrayList<Flight> flightList = flights.getFlights();
    private ArrayList<Seat> seatList = new ArrayList<Seat>();

    @BeforeClass
    public static void oneTimeSetup()
    {
        return;
    }

    @AfterClass
    public static void oneTimeTearDown()
    {
        return;
    }

    @BeforeEach
    public void setup()
    {
        if (flightList != null) {
            flightList.clear();
       
            seatList.add(new Seat(1, "A", false));
            seatList.add(new Seat(1, "B", false));
            seatList.add(new Seat(1, "C", true));
            seatList.add(new Seat(2, "A", true));
            seatList.add(new Seat(2, "B", false));
            seatList.add(new Seat(2, "C", true));
            seatList.add(new Seat(3, "A", false));
            seatList.add(new Seat(3, "B", false));
            seatList.add(new Seat(3, "C", true));
            seatList.add(new Seat(4, "A", true));
            seatList.add(new Seat(4, "B", false));
            seatList.add(new Seat(4, "C", true));
    
            flightList.add(new Flight(UUID.fromString("eb45fea7-6625-4253-91ff-d559a610667b"), 9486, "DB", "Houston", "TX", "Seattle", "WA", new Date(10, 31, 2022), new Date(10, 31, 2022), "4 hours and 20 minutes", "11:15 AM PDT", "5:35 PM CDT", seatList));
            
            seatList.clear();
    
            seatList.add(new Seat(1, "A", false));
            seatList.add(new Seat(1, "B", true));
            seatList.add(new Seat(1, "C", true));
            seatList.add(new Seat(1, "D", false));
            seatList.add(new Seat(1, "E", true));
            seatList.add(new Seat(1, "F", false));
            seatList.add(new Seat(2, "A", false));
            seatList.add(new Seat(2, "B", true));
            seatList.add(new Seat(2, "C", true));
            seatList.add(new Seat(2, "D", true));
            seatList.add(new Seat(2, "E", true));
            seatList.add(new Seat(2, "F", false));
            seatList.add(new Seat(3, "A", true));
            seatList.add(new Seat(3, "B", false));
            seatList.add(new Seat(3, "C", false));
            seatList.add(new Seat(3, "D", true));
            seatList.add(new Seat(3, "E", false));
            seatList.add(new Seat(3, "F", true));
            seatList.add(new Seat(4, "A", false));
            seatList.add(new Seat(4, "B", false));
            seatList.add(new Seat(4, "C", true));
            seatList.add(new Seat(4, "D", false));
            seatList.add(new Seat(4, "E", true));
            seatList.add(new Seat(4, "F", true));
    
            flightList.add(new Flight(UUID.fromString("7ee6155a-3a75-497c-98e3-26c810817baa"), 5530, "DB", "Boston", "MA", "Miami", "FL", new Date(7, 13, 2023), new Date(7, 13, 2023), "3 hours and 15 minutes", "2:45 PM EST", "6:00 PM EST", seatList));
        }
        return;
    }

    @AfterEach
    public void tearDown()
    {
        return;
    }

    @Test
    public void toStringTest()
    {
        assertEquals("\n Airline: DB"+
        "\n Flight Num : 9486"+
        "\n Departure Location: Houston, TX"+
        "\n Arrival Location: Seattle, WA"+
        "\n Flight Duration: 3 hours and 15 minutes"+
        "\n Departure Date and Time: 7/13/2023 at 2:45 PM EST"+
        "\n Arrival Date and Time: 7/13/2023 at 6:00 PM EST"+
        "\n Connecting Flight Result Number(s):  "+
        "\n --------------------------------------------------------------------------------------------------------", flightList.get(1).toString());
    }

    @Test
    public void seatsToArrayTest()
    {
        Seat seatA1 = new Seat(1, "A", false);
        Seat seatB1 = new Seat(1, "B", false);
        Seat seatC1 = new Seat(1, "C", true);
        Seat seatA2 = new Seat(2, "A", true);
        Seat seatB2 = new Seat(2, "B", false);
        Seat seatC2 = new Seat(2, "C", true);
        Seat seatA3 = new Seat(3, "A", false);
        Seat seatB3 = new Seat(3, "B", false);
        Seat seatC3 = new Seat(3, "C", true);
        Seat seatA4 = new Seat(4, "A", true);
        Seat seatB4 = new Seat(4, "B", false);
        Seat seatC4 = new Seat(4, "C", true);
        
        Seat[][] SeatArray = {{seatA1, seatA2, seatA3, seatA4},{seatB1, seatB2, seatB3, seatB4},{seatC1, seatC2, seatC3, seatC4}};
        assertEquals(SeatArray, flightList.get(0).toSeatArray());
    }
}