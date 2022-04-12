import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Tommy Ecker
 */

class DataWriterTest {
    private Flights flights = Flights.getInstance();
    private ArrayList<Flight> flightList = flights.getFlights();

    private RegisteredUsers users = RegisteredUsers.getInstance();
    private ArrayList<RegisteredUser> usersList = users.getRegisteredUsers();

    private ArrayList<Seat> seatList = new ArrayList<Seat>();
    private ArrayList<Booking> bookingList = new ArrayList<Booking>();
    private ArrayList<Friend> friendList = new ArrayList<Friend>();

    @BeforeEach
    public void setup() {
        if (flightList != null) {
            Flights.getInstance().getFlights().clear();
            DataWriter.saveFlights();
        }

        if (usersList != null) {
            RegisteredUsers.getInstance().getRegisteredUsers().clear();
            DataWriter.saveUsers();
        }
    }

    @AfterEach
    public void tearDown() {
        if (flightList != null) {
            Flights.getInstance().getFlights().clear();
            DataWriter.saveFlights();
        }

        if (usersList != null) {
            RegisteredUsers.getInstance().getRegisteredUsers().clear();
            DataWriter.saveUsers();
        }
    }

    @Test
    void testWritingZeroFlights() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals(0, flightList.size());
        }
    }

    @Test
    void testWritingOneFlight() {
        if (flightList != null) {

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
            DataWriter.saveUsers();
            assertEquals(UUID.fromString("eb45fea7-6625-4253-91ff-d559a610667b"), DataLoader.loadFlights().get(0).getFlightID());
        }
    }

    @Test
    void testWritingTwoFlights() {
        if (flightList != null) {

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
            flightList.add(new Flight(UUID.fromString("55f677f7-eb91-485d-98a1-198c9bb66217"), 9486, "DB", "Houston", "TX", "Seattle", "WA", new Date(10, 31, 2022), new Date(10, 31, 2022), "4 hours and 20 minutes", "11:15 AM PDT", "5:35 PM CDT", seatList));
            DataWriter.saveUsers();
            assertEquals(UUID.fromString("55f677f7-eb91-485d-98a1-198c9bb66217"), DataLoader.loadFlights().get(1).getFlightID());
        }   
    }

    @Test
    void testWritingEmptyFlight() {
        if (flightList != null) {
            flightList.add(new Flight(UUID.fromString(""), 0, "", "", "", "", "", new Date(0, 0, 0), new Date(0, 0, 0), "", "", "", null));
            DataWriter.saveFlights();
            assertEquals(UUID.fromString(""), DataLoader.loadFlights().get(0).getFlightID());
        }
    }

    @Test
    void testWritingNullFlight() {
        if (flightList != null) {
            flightList.add(new Flight(null, 0, "", "", "", "", "", null, null, "", "", "", null));
             DataWriter.saveFlights();
            assertEquals(null, DataLoader.loadFlights().get(0).getFlightID());
        }
    }

    @Test
    void testWritingZeroUsers() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals(0, usersList.size());
        }
    }

    @Test
    void testWritingOneUser() {
        if (usersList != null) {
            bookingList.add(new Booking(UUID.fromString("1f5c4560-f6e4-4bae-8cc7-98592c1a9f70"), "2C"));
            friendList.add(new Friend(null, null, null));

            usersList.add(new RegisteredUser(UUID.fromString("470e996f-605e-4cd2-bd9a-d726fc27db00"), "Serena", "Williams", "swilliams33", "1L0veT3nn1s", "swilliams@gmail.com", "03/24/1974", "5593 Yellow Brick Road", "Columbia", "SC", 29208, bookingList, friendList)); 
            DataWriter.saveUsers();
            assertEquals(UUID.fromString("470e996f-605e-4cd2-bd9a-d726fc27db00") , DataLoader.getAllUsers().get(0).getUUID());      
        }
    }

    @Test
    void testWritingTwoUsers() {
        if (usersList != null) {
            bookingList.add(new Booking(UUID.fromString("1f5c4560-f6e4-4bae-8cc7-98592c1a9f70"), "2C"));
            friendList.add(new Friend(null, null, null));

            usersList.add(new RegisteredUser(UUID.fromString("470e996f-605e-4cd2-bd9a-d726fc27db00"), "Serena", "Williams", "swilliams33", "1L0veT3nn1s", "swilliams@gmail.com", "03/24/1974", "5593 Yellow Brick Road", "Columbia", "SC", 29208, bookingList, friendList)); 

            bookingList.add(new Booking(UUID.fromString("1f5c4560-f6e4-4bae-8cc7-98592c1a9f70"), "2C"));
            friendList.add(new Friend(null, null, null));

            usersList.add(new RegisteredUser(UUID.fromString("59de1f37-af25-46f0-ba06-53537bf4f454"), "Serena", "Williams", "swilliams33", "1L0veT3nn1s", "swilliams@gmail.com", "03/24/1974", "5593 Yellow Brick Road", "Columbia", "SC", 29208, bookingList, friendList)); 
            DataWriter.saveUsers();
            assertEquals(UUID.fromString("59de1f37-af25-46f0-ba06-53537bf4f454"), DataLoader.getAllUsers().get(1).getUUID());
        }
    }

    @Test
    void testWritingEmptyUser() {
        if (usersList != null) {
            usersList.add(new RegisteredUser(UUID.fromString(""), "", "", "", "", "", "", "", "", "", 0, null, null));
            DataWriter.saveUsers();
            assertEquals("", DataLoader.getAllUsers().get(0).getUUID());
        }
    }

    @Test
    void testWritingNullUser() {
        if (usersList != null) {
            usersList.add(new RegisteredUser(UUID.fromString(null), "", "", "", "", "", "", "", "", "", 0, null, null));
            DataWriter.saveUsers();
            assertEquals(null, DataLoader.getAllUsers().get(0).getUUID());
        }
    }   
}
