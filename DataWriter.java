import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.UUID;

/**
 * @author Tommy Ecker
 */

public class DataWriter extends DataConstants {

    public static void saveFlights() {
        Flights flights = Flights.getInstance();
        ArrayList<Flight> planes = flights.getFlights();
        JSONArray jsonPlanes = new JSONArray();

        // Creates all of the JSON files
        for (int i = 0; i < planes.size(); i++) {
            jsonPlanes.add(getFlightJSON(planes.get(i)));
        }

        // Writes to the JSON file
        try (FileWriter file = new FileWriter(FLIGHT_FILE_NAME)) {
            file.write(jsonPlanes.toJSONString());
            file.flush();            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveUsers() {
        RegisteredUsers users = RegisteredUsers.getInstance();
        ArrayList<RegisteredUser> accounts = users.getRegisteredUsers();
        JSONArray jsonAccounts = new JSONArray();

        // Creates all of the JSON files
        for (int i = 0; i < accounts.size(); i++) {
            jsonAccounts.add(getRegisteredUserJSON(accounts.get(i)));
        }

        // Writes to the JSON file
        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonAccounts.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getFlightJSON(Flight flight) {

        Flights flights = Flights.getInstance();

        ArrayList<Flight> planes = flights.getFlights();

        JSONObject flightDetails = new JSONObject();

        flightDetails.put(FLIGHT_ID, flight.getFlightID().toString());
        flightDetails.put(FLIGHT_NUM, flight.getFlightNum());
        flightDetails.put(AIRLINE, flight.getAirline());
        flightDetails.put(FLIGHT_DURATION, flight.getFlightDuration());
        flightDetails.put(DEPT_TIME, flight.getDepartTime());
        flightDetails.put(ARR_TIME, flight.getArrivalTime());

        JSONObject destinationObject = new JSONObject();
        destinationObject.put(DEST_CITY, flight.getDestinationCity());
        destinationObject.put(DEST_STATE, flight.getDestinationState());

        flightDetails.put(DESTINATION, destinationObject);

        JSONObject departureObject = new JSONObject();
        departureObject.put(DEPT_CITY, flight.getDepartCity());
        departureObject.put(DEPT_STATE, flight.getDepartState());

        flightDetails.put(DEPT_LOC, departureObject);

        JSONArray list = new JSONArray();

        for (int i = 0; i < flight.getSeats().size(); i++) {
            JSONObject seatObject = new JSONObject();
            int seatRow = flight.getSeats().get(i).getRow();
            String seatNum = flight.getSeats().get(i).getSeatNumber();
            Boolean available = flight.getSeats().get(i).getAvailable();

            seatObject.put(SEAT_ROW, seatRow);
            seatObject.put(SEAT_NUM, seatNum);
            seatObject.put(IS_AVAILABLE, available);

            list.add(seatObject);
        }

        flightDetails.put(SEATS, list);
       
        return flightDetails;
    }

    public static JSONObject getRegisteredUserJSON (RegisteredUser user) {

        RegisteredUsers users = RegisteredUsers.getInstance();

        ArrayList<RegisteredUser> accounts = users.getRegisteredUsers();

        JSONObject userDetails = new JSONObject();

        //userDetails.put(FLIGHT_ID, user.getID());
        userDetails.put(FIRST_NAME, user.getFirstName());
        userDetails.put(LAST_NAME, user.getLastName());
        userDetails.put(USERNAME, user.getUsername());
        userDetails.put(PASSWORD, user.getPassword());
        userDetails.put(EMAIL, user.getEmail());
        userDetails.put(DATE_OF_BIRTH, user.getDateOfBirth());

        JSONObject addressObject = new JSONObject();
        addressObject.put(STREET_ADDRESS, user.getStreetAddress());
        addressObject.put(ADDRESS_CITY, user.getAddressCity());
        addressObject.put(ADDRESS_STATE, user.getAddressState());
        addressObject.put(ADDRESS_ZIP, user.getAddressZip());

        userDetails.put(ADDRESS, addressObject);

        JSONArray bookingList = new JSONArray();

        for (int i = 0; i < user.getBookings().size(); i++) {
            JSONObject bookingObject = new JSONObject();
            UUID bookingFlightID =  user.getBookings().get(i).getFlightID();
            String bookingFlightSeat = user.getBookings().get(i).getSeat();

            bookingObject.put(USER_FLIGHT, bookingFlightID);
            bookingObject.put(USER_SEAT, bookingFlightSeat);

            bookingList.add(bookingObject);
        }
        userDetails.put(BOOKINGS, bookingList);


        JSONObject friendsObject = new JSONObject();
        JSONArray friendList = new JSONArray();

        for (int i = 0; i < user.getFriends().size(); i++) {
            JSONObject friendObject = new JSONObject();
            String friendFirstName = user.getFriends().get(i).getFirstName();
            String friendLastName = user.getFriends().get(i).getLastName();

            JSONArray friendBookings = new JSONArray();

            for (int j = 0; j < user.getFriends().get(j).getBookings().size(); j++) {
                JSONObject friendBookingObject = new JSONObject();
                UUID friendFlightID = user.getFriends().get(j).getBookings().get(j).getFlightID();
                String friendFlightSeat = user.getFriends().get(j).getBookings().get(j).getSeat();

                friendBookingObject.put(FRIENDS_FLIGHT_ID, friendFlightID);
                friendBookingObject.put(FRIENDS_FLIGHT_SEAT, friendFlightSeat);

                friendBookings.add(friendBookingObject);
            }
            friendObject.put(FRIENDS_FIRST_NAME, friendFirstName);
            friendObject.put(FRIENDS_LAST_NAME, friendLastName);
            friendObject.put(FRIENDS_BOOKINGS, friendBookings);

            friendList.add(friendObject);
        }
        userDetails.put(FRIENDS, friendList);

        return userDetails;
    }
}
