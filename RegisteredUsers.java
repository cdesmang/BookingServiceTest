import java.util.ArrayList;

import java.util.UUID;

public class RegisteredUsers {
    private static RegisteredUsers users = null;
    private static ArrayList<RegisteredUser> usersList = new ArrayList<>();
 
    /**
     * Creates an instance of a DataLoader which will be used to get all of the registered users in the system
     */
    private RegisteredUsers() {
        usersList = DataLoader.getAllUsers();
    }

    /**
     * @return Returns a new instance of registered users if the list of users is empty
     */
    public static RegisteredUsers getInstance() {
        if (users == null) {
            users = new RegisteredUsers();
        }

        return users;
    }

    /**
     * @return Returns all registered users
     */
    public ArrayList<RegisteredUser> getRegisteredUsers() {
        return usersList;
    }

    /**
     * @return Returns all of the friends associated with registered users' accounts
     */
    public ArrayList<RegisteredUser> getRegisteredUsersFriends() {
        return users.getRegisteredUsersFriends();
    }

    /**
     * 
     * Adds a new registered user to the collection of registered users
     * 
     * 
     * @param id the UUID associated with the registered user
     * @param firstName the registered user's first name
     * @param lastName the registered user's last name
     * @param username the username associated with the registered user's account
     * @param password the password associated with the registered user's account
     * @param email the email address associated with the registered user's account
     * @param dob the registered user's date of birth represented as a string
     * @param streetAddress the street address associated with the registered user's address
     * @param addressCity the city associated with the registered user's address
     * @param addressState the state associated with the registered user's address
     * @param addressZip the zip code associated with the registered user's address
     * @param bookings an array list of all of the registered user's bookings
     * @param friends an array list of all of the registered user's friends associated with their account
     */
    public void addUser(UUID id, String firstName, String lastName, String username, String password, String email, String dob, String streetAddress, String addressCity, String addressState, int addressZip, ArrayList<Booking> bookings, ArrayList<Friend> friends) {
        usersList.add(new RegisteredUser(id, firstName, lastName, username, password, email, dob, streetAddress, addressCity, addressState, addressZip, bookings, friends));
    }

    public void addUser(UUID id, String firstName, String lastName, String username, String password, String email, String dob, String streetAddress, String addressCity, String addressState, int addressZip) {
        usersList.add(new RegisteredUser(id, firstName, lastName, username, password, email, dob, streetAddress, addressCity, addressState, addressZip,null, null));
    }

    public void addUser(UUID id, String firstName, String lastName, String username, String password, String email, String dob, String streetAddress, String addressCity, String addressState, int addressZip, ArrayList<Booking> bookings) {
        usersList.add(new RegisteredUser(id, firstName, lastName, username, password, email, dob, streetAddress, addressCity, addressState, addressZip, bookings,null));
    }

    public void logout() {
        DataWriter.saveUsers();
    }
    
}

