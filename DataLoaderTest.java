import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.crypto.Data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Tommy Ecker
 */

class DataLoaderTest {
    private Flights flights = Flights.getInstance();
    private RegisteredUsers users = RegisteredUsers.getInstance();

    private ArrayList<Flight> flightList = flights.getFlights();
    private ArrayList<RegisteredUser> usersList = users.getRegisteredUsers();

    private ArrayList<Seat> seatList = new ArrayList<Seat>();
    private ArrayList<Booking> bookingList = new ArrayList<Booking>();
    private ArrayList<Friend> friendList = new ArrayList<Friend>();
    

    @BeforeEach
    public void setup() {

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
            DataWriter.saveFlights();
        }

        if (usersList != null) {
            usersList.clear();

            bookingList.add(new Booking(UUID.fromString("1f5c4560-f6e4-4bae-8cc7-98592c1a9f70"), "2C"));
            friendList.add(new Friend(null, null, null));

            usersList.add(new RegisteredUser(UUID.fromString("470e996f-605e-4cd2-bd9a-d726fc27db00"), "Serena", "Williams", "swilliams33", "1L0veT3nn1s", "swilliams@gmail.com", "03/24/1974", "5593 Yellow Brick Road", "Columbia", "SC", 29208, bookingList, friendList));

            bookingList.add(new Booking(UUID.fromString("89953bc8-5655-41a9-a62f-b360ad1aa695"), "4A"));
            friendList.add(new Friend("James", "McCoy", null));

            usersList.add(new RegisteredUser(UUID.fromString("d0e2c1cc-1598-41e9-97eb-4a27bed37710"), "John", "Hancock", "jhancock", "h@nc0ck98", "jhancock98@gmail.com", "05/13/1963", "1432 White Oak Drive", "Charlotte", "NC", 28270, bookingList, friendList));
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
    void testGetFlightsSize() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals(2, flightList.size());
        }
    }

    @Test
    void testGetFlightsSizeZero() {
        if (flightList != null) {
            Flights.getInstance().getFlights().clear();
            DataWriter.saveFlights();
            assertEquals(0, flightList.size());
        }
    }

    @Test
    void testGetFirstFlightFlightID() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals(UUID.fromString("eb45fea7-6625-4253-91ff-d559a610667b"), flightList.get(0).getFlightID());
        }
    }

    @Test
    void testGetFirstFlightNumber() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals(9468, flightList.get(0).getFlightNum());
        }
    }

    @Test
    void testGetFirstFlightAirline() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals("DB", flightList.get(0).getAirline());
        }
    }

    @Test
    void testGetFirstFlightDestinationCity() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals("Houston", flightList.get(0).getDestinationCity());
        }
    }

    @Test
    void testGetFirstFlightDestinationState() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals("TX", flightList.get(0).getDestinationState());
        }
    }

    @Test
    void testGetFirstFlightDepartureCity() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals("Seattle", flightList.get(0).getDepartCity());
        }
    }

    @Test
    void testGetFirstFlightDepartureState() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals("WA", flightList.get(0).getDepartState());
        }
    }

    @Test
    void testGetFirstFlightDepartureDate() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals(new Date(10, 31, 22), flightList.get(0).getDepartDate());
        }
    }

    @Test
    void testGetFirstFlightArrivalDate() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals(new Date(10, 31, 22), flightList.get(0).getArrivalDate());
        }
    }

    @Test
    void testGetFirstFlightFlightDuration() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals("4 hours and 20 minutes", flightList.get(0).getFlightDuration());
        }
    }

    @Test
    void testGetFirstFlightDepartureTime() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals("11:15 AM PDT", flightList.get(0).getDepartTime());
        }
    }

    @Test
    void testGetFirstFlightArrivalTime() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals("5:35 PM CDT", flightList.get(0).getArrivalTime());
        }
    }

    @Test
    void testGetFirstFlightFirstRow() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals(1, flightList.get(0).getSeats().get(0).getRow());
        }
    }

    @Test
    void testGetFirstFlightFirstSeat() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals("A", flightList.get(0).getSeats().get(0).getSeatNumber());
        }
    }

    @Test
    void testGetFirstFlightFirstSeatAvailability() {
        if (flightList != null) {
            flightList = DataLoader.loadFlights();
            assertEquals(false, flightList.get(0).getSeats().get(0).getAvailable());
        }
    }

    @Test
    void testGetUsersSize() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals(2, usersList.size());
        }
    }

    @Test
    void testGetUsersSizeZero() {
        if (usersList != null) {
            RegisteredUsers.getInstance().getRegisteredUsers().clear();
            DataWriter.saveUsers();
            assertEquals(0, usersList.size());
        }
    }

    @Test
    void testGetUserFirstUserUserID() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals(UUID.fromString("470e996f-605e-4cd2-bd9a-d726fc27db00"), usersList.get(0).getUUID());
        }
    }

    @Test
    void testGetUserFirstUserFirstName() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals("Serena", usersList.get(0).getFirstName());
        }
    }

    @Test
    void testGetUserFirstUserLastName() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals("Williams", usersList.get(0).getLastName());
        }
    }

    @Test
    void testGetUserFirstUserUsername() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals("swilliams33", usersList.get(0).getUsername());
        }
    }

    @Test
    void testGetUserFirstUserPassword() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals("1L0veT3nn1s", usersList.get(0).getPassword());
        }
    }

    @Test
    void testGetUserFirstUserEmail() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals("swilliams@gmail.com", usersList.get(0).getEmail());
        }
    }

    @Test
    void testGetUserFirstUserDOB() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals("03/24/1974", usersList.get(0).getDateOfBirth());
        }
    }

    @Test
    void testGetUserFirstUserStreetAddress() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals("5593 Yellow Brick Road", usersList.get(0).getStreetAddress());
        }
    }

    @Test
    void testGetUserFirstUserAddressCity() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals("Columbia", usersList.get(0).getAddressCity());
        }
    }

    @Test
    void testGetUserFirstUserAddressState() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals("SC", usersList.get(0).getAddressState());
        }
    }

    @Test
    void testGetUserFirstUserAddressZip() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals(29208, usersList.get(0).getAddressZip());
        }
    }

    @Test
    void testGetUserFirstUserBookingsFirstUUID() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals(UUID.fromString("1f5c4560-f6e4-4bae-8cc7-98592c1a9f70"), usersList.get(0).getBookings().get(0).getFlightID());
        }
    }

    @Test
    void testGetUserFirstUserBookingsFirstSeat() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals("2C", usersList.get(0).getBookings().get(0).getSeat());
        }
    }

    @Test
    void testGetUserFirstUserFriendsFirstFriendFirstName() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals(null, usersList.get(0).getFriends().get(0).getFirstName());
        }
    }

    @Test
    void testGetUserFirstUserFriendsFirstFriendLastName() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals(null, usersList.get(0).getFriends().get(0).getLastName());
        }
    }

    @Test
    void testGetUserFirstUserFriendsFirstFriendFirstBookingFlightID() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals(null, usersList.get(0).getFriends().get(0).getBookings().get(0).getFlightID());
        }
    }

    @Test
    void testGetUserFirstUserFriendsFirstFriendFirstBookingSeatNumber() {
        if (usersList != null) {
            usersList = DataLoader.getAllUsers();
            assertEquals(null, usersList.get(0).getFriends().get(0).getBookings().get(0).getSeat());
        }
    }
    
    

}