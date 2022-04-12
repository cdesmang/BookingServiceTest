import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Iterator;
import java.util.UUID;

/**
 * @author Tommy Ecker
 */

public class DataLoader extends DataConstants {
    
    /**
     * @return Returns an array list of all current flights in the system
     */
    public static ArrayList<Flight> loadFlights() {
        ArrayList<Flight> flights = new ArrayList<Flight>();

        try {
            FileReader reader = new FileReader(FLIGHT_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray flightsJSON = (JSONArray)parser.parse(reader);

            for(int i = 0; i < flightsJSON.size(); i++) {
                JSONObject flightJSON = (JSONObject)flightsJSON.get(i);
                UUID id = UUID.fromString((String)flightJSON.get(FLIGHT_ID));
                int flightNumber = ((Long)flightJSON.get(FLIGHT_NUM)).intValue();
                String airline = (String)flightJSON.get(AIRLINE);
               
                JSONObject jsonDestination = (JSONObject)flightJSON.get("destination");
                String destinationCity = (String)jsonDestination.get("City");
                String destinationState = (String)jsonDestination.get("State");
                
                JSONObject jsonDeparture = (JSONObject)flightJSON.get("departLocation");
                String departureCity = (String)jsonDeparture.get("City");
                String departureState = (String)jsonDeparture.get("State");

                JSONObject jsonDeptDate = (JSONObject)flightJSON.get("departureDate");
                int deptMonth = ((Long)jsonDeptDate.get("month")).intValue();
                int deptDay = ((Long)jsonDeptDate.get("day")).intValue();
                int deptYear = ((Long)jsonDeptDate.get("year")).intValue();

                Date deptDate = new Date(deptMonth, deptDay, deptYear);


                JSONObject jsonArrivalDate = (JSONObject)flightJSON.get("arrivalDate");
                int arrMonth = ((Long)jsonArrivalDate.get("month")).intValue();
                int arrDay = ((Long)jsonArrivalDate.get("day")).intValue();
                int arrYear = ((Long)jsonArrivalDate.get("year")).intValue();

                Date arrDate = new Date(arrMonth, arrDay, arrYear);
                
                String duration = (String)flightJSON.get(FLIGHT_DURATION);
                String deptTime = (String)flightJSON.get(DEPT_TIME);
                String arrTime = (String)flightJSON.get(ARR_TIME);

               ArrayList<Seat> seatsList = new ArrayList<Seat>();

                JSONArray jsonSeats = (JSONArray)flightJSON.get("seats");
                for (int j = 0; j < jsonSeats.size(); j++) {
                    JSONObject jsonSeat = (JSONObject) jsonSeats.get(j);
                    int jsonSeatRow = ((Long)jsonSeat.get("row")).intValue();
                    String jsonSeatNum = (String)jsonSeat.get("seatNum");
                    Boolean isAvailable = ((Boolean)jsonSeat.get("available")).booleanValue();

                    Seat newSeat = new Seat(jsonSeatRow, jsonSeatNum, isAvailable);
                    seatsList.add(newSeat);

                }


                flights.add(new Flight(id, flightNumber, airline, destinationCity, destinationState, departureCity, departureState, deptDate, arrDate, duration, deptTime, arrTime, seatsList));
            }


            return flights;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    
    /**
     * @return Returns an array list of all registered users that are currently in the system
     */
    public static ArrayList<RegisteredUser> getAllUsers() {
        ArrayList<RegisteredUser> users = new ArrayList<RegisteredUser>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray usersJSON = (JSONArray)parser.parse(reader);

            for (int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject)usersJSON.get(i);
                UUID userID = UUID.fromString((String)userJSON.get(USER_ID));
                String firstName = (String)userJSON.get(FIRST_NAME);
                String lastName = (String)userJSON.get(LAST_NAME);
                String username = (String)userJSON.get(USERNAME);
                String password = (String)userJSON.get(PASSWORD);
                String email = (String)userJSON.get(EMAIL);
                String dateOfBirth = (String)userJSON.get(DATE_OF_BIRTH);
            
                JSONObject jsonAddress = (JSONObject)userJSON.get(ADDRESS);
                String streetAddress = (String)jsonAddress.get(STREET_ADDRESS);
                String addressCity = (String)jsonAddress.get(ADDRESS_CITY);
                String addressState = (String)jsonAddress.get(ADDRESS_STATE);
                int addressZip = ((Long)jsonAddress.get(ADDRESS_ZIP)).intValue();

                ArrayList<Booking> bookings = new ArrayList<>();
                JSONArray bookingsList = (JSONArray)userJSON.get(BOOKINGS);
                
                for (int j = 0; j < bookingsList.size(); j++) {
                    JSONObject jsonBooking = (JSONObject) bookingsList.get(j);
                    UUID flightID = UUID.fromString((String)jsonBooking.get("flight_ID"));
                    String flightSeat = (String)jsonBooking.get("seat");

                    Booking booking = new Booking(flightID, flightSeat);
                    bookings.add(booking);
                
                }

                ArrayList<Friend> friendList = new ArrayList<Friend>();
                JSONArray jsonFriends = (JSONArray)userJSON.get("friends");
                 if (jsonFriends != null) {
                    for (int j = 0; j < jsonFriends.size(); j++) {
                        JSONObject jsonFriend = (JSONObject) jsonFriends.get(j);
                        String friendFirstName = (String)jsonFriend.get("first_name");
                        String friendLastName = (String)jsonFriend.get("last_name");

                        ArrayList<Booking> friendBookings = new ArrayList<Booking>();
                        JSONArray jsonFriendBookings = (JSONArray)jsonFriend.get("friend_bookings");
                        if (jsonFriendBookings != null) {
                            for (int k = 0; k < jsonFriendBookings.size(); k++) {
                                JSONObject friendBooking = (JSONObject) jsonFriendBookings.get(k);
                                UUID friendFlightID = UUID.fromString((String)friendBooking.get("flight_ID"));
                                String friendSeat = (String)friendBooking.get("seat");

                                Booking friendFlightBooking = new Booking(friendFlightID, friendSeat);

                                friendBookings.add(friendFlightBooking);
                            }
                        }
                        friendList.add(new Friend(friendFirstName, friendLastName,friendBookings));

                    }

                }
                users.add(new RegisteredUser(userID, firstName, lastName, username, password, email, dateOfBirth, streetAddress, addressCity, addressState, addressZip, bookings, friendList));

            }
            return users;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}