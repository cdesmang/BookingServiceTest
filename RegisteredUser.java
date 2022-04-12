import java.util.ArrayList;
import java.util.UUID;

public class RegisteredUser extends User {

    /**
     * the number of seats to book will be one more than the length of this arraylist
     */
    private ArrayList<Friend> friends = new ArrayList<Friend>();
    private UUID userID;
    public String firstName;
    public String lastName;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String dateOfBirth;
    private String streetAddress;
    private String addressCity;
    private String addressState;
    private int addressZip;
    private Date dob;
    private String address;
    public ArrayList<Booking> bookings;
    public int seatsToBook;

    

    public RegisteredUser(String firstName, String lastName, String username, String password, String email, String address, Date dob){
        this.fullName = firstName + " " + lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.userType = "registered";
    }

    /**
     * @param userID a UUID used to identify the user as a registered user
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param username the username associated with the registered user's account
     * @param password the password associated with the registered user's account
     * @param email the user's email address
     * @param dateOfBirth the user's date of birth represented as a string
     * @param streetAddress the street address associated with the user's address
     * @param addressCity the city associated with the user's address
     * @param addressState the state associated with the user's address
     * @param addressZip the zip code associated with the user's address
     * @param bookings an array list of all of the user's bookings
     * @param friends an array list of all of the user's friends associated with their account
     * 
     * 
     * This constructor is used for reading and writing to/from the JSON file
     * 
     * 
     */
    public RegisteredUser(UUID userID, String firstName, String lastName, String username, String password, String email, String dateOfBirth, String streetAddress, String addressCity, String addressState, int addressZip, ArrayList<Booking> bookings, ArrayList<Friend> friends) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.streetAddress = streetAddress;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZip = addressZip;
        this.bookings = bookings;
        this.friends = friends;
    }

    public void addFriends (Friend friendusername) {
        friends.add(friendusername);
        seatsToBook++;
    }

    public void removeFriend(Friend friendusername) {
        friends.remove(friendusername);
        seatsToBook--;
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public UUID getUUID() {
        return this.userID;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getStreetAddress() {
        return this.streetAddress;
    }

    public String getAddressCity() {
        return this.addressCity;
    }

    public String getAddressState() {
        return this.addressState;
    }

    public int getAddressZip() {
        return this.addressZip;
    }

    public String getEmail() {
        return this.email;
    }

    public ArrayList<Booking> getBookings() {
        return this.bookings;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    
    /**
     * a booking has a :
     * full name
     * flight booking (can be null) - where to where, time and date, flight number, seat number
     * hotel booking (can be null) - hotel name, place, date, room number(s)
     * 
    */
}

