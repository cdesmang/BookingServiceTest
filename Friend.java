import java.util.ArrayList;

public class Friend {
    
    private String email;
    private Date dob;
    private String address;
    private String username;
    private String firstName;
    private String lastName;
    private ArrayList<Booking> bookings = new ArrayList<>();
    
    
    public Friend(String firstName, String lastName, String username, String email, Date DOB, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.dob = DOB;
        this.address = address;
    }

    /**
     * @param firstName the first name of the friend
     * @param lastName the last name of the friend
     * @param bookings an array list of the bookings associated with the friend
     * 
     * 
     * This constructor is used for reading and writing to/from the JSON file
     * 
     * 
     */
    public Friend(String firstName, String lastName, ArrayList<Booking> bookings) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookings = bookings;
    }

    /**
     * @return Returns the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Returns the date of birth
     */
    public String getDOB() {
        return dob.toString();
    }

    /**
     * @return Returns the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return Returns the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return Returns the full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
    * @return Returns the first name
    */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return Returns the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return Returns the array of bookings
     */
    public ArrayList<Booking> getBookings() {
        return bookings;
    }
}

