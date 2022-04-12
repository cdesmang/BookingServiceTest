/**
 * Allows interaction between UI and backend classes
 * @author Christina Desmangles
 */
import java.util.ArrayList;

public class FlightSystem {
    private static User currentUser;
    private static Flights flights;
    private static Users users= new Users();

    /**
     * Constructs a new instance of Flightsystem and intializes the current user based on user type (overloaded for different types)
     */
    public FlightSystem(){
        flights = Flights.getInstance();
        currentUser =new GuestUser();
    }
    /**
     * Overloaded constructor version for registered users
     * @param username- registered account username
     * @param password- registered account password
     */
    public FlightSystem (String username, String password){
        flights = Flights.getInstance();
        currentUser= users.searchUser(username, password);
    }
    /**
     * overloaded constructor version for new account creation
     * @param firstName- users first name
     * @param lastName users last name
     * @param username- chosen username
     * @param password - chosen password
     * @param email- users email address
     * @param dob- users email address
     * @param address- users street address
     */
    public FlightSystem(String firstName, String lastName, String username, String password, String email, String address, Date dob) {
         flights = Flights.getInstance();
         currentUser = new RegisteredUser(firstName, lastName, username, password, email, address, dob);
    }

    /**
     * prints the seats
     * @param flightSelection
     * @param flightResults
     * @param customers
     * @return
     */
    public String flightBooking(String flightSelection, Flight[] flightResults, ArrayList<String> customers){
        ArrayList<Flight> selected = new ArrayList<Flight>();
        String[] selectIndex  = flightSelection.split(",");
        for (int i = 0; i < selectIndex.length; i++){
            selected.add(flightResults[Integer.parseInt(selectIndex[i])-1]);
        }
        String printSeats = "";
        for (int i =0; i< selectIndex.length; i++){
            printSeats+= seatsToString(selected.get(i));
        }
        return printSeats;
    }

    /**
     * @param flight
     * @return string representation of the seat layout of a flight
     */
    private String seatsToString(Flight flight){
        String flightID = "Airline: "+flight.getAirline()+"\tFlight Number: "+String.valueOf(flight.getFlightNum())+"\n";
        char[][] seats = getSeats(flight);
        String print = flightID;
        if (seats[0].length == 5){
            print += "  A B  C D\n";
            for (int i  = 0; i < seats.length; i++){
                print += (i+1) +" ";
                for (int j = 0; j<seats[0].length; j++){
                    print += seats[i][j]+" ";
                }
                print += "\n";
            }
        } else {
            print+= "  A B C  D E F\n";
            for (int i  = 0; i < seats.length; i++){
                print += (i+1) +" ";
                for (int j = 0; j<seats[0].length; j++){
                    print += seats[i][j]+" ";
                }
                print+="\n";
            }
        }
        return print;
    }

    /**
     * Converts a seat array to an array of characters representing seat availibility
     * @param flight - the flight for which we need to present the seats
     * @return- a character array representing the availibility of seats on the given flight
     */
    private char[][] getSeats(Flight flight){
        Seat[][] sArr = flight.toSeatArray();
        char[][] charArr = new char[sArr.length][sArr[0].length+1];
        char avail =  '_';
        char full = 'X';
        char aisle =' ';
        for (int i = 0; i <sArr.length; i++){
            for (int j = 0; j<sArr[0].length; j++){
                if(sArr[0].length == 4 && j < 2){
                    // small plane on the left side
                    if(sArr[i][j].getAvailable()) charArr[i][j]= avail;
                    else charArr[i][j] = full;
                } else if (sArr[0].length == 4 && j >= 2){
                    // small plane on the right
                    charArr[i][2] = aisle; 
                    if (sArr[i][j].getAvailable()) charArr[i][j+1] = avail;
                    else charArr[i][j+1] = full;
                } else if(sArr[0].length == 6 && j < 3){
                    // larger plane on the left (and the aisle)
                    if(sArr[i][j].getAvailable()) charArr[i][j] = avail;
                    else charArr[i][j] = full;
                } else if (sArr[0].length == 6 && j >= 3){
                    // larger plane on the right (and the aisle)
                    charArr[i][3] = aisle;
                    if (sArr[i][j].getAvailable()) charArr[i][j+1] = avail;
                    else charArr[i][j+1] = full;
                }
            }
        }
        return charArr;
    }

    /**
     * Searches for all relevant flights using data given from user
     * @param dLoc departure location given by user
     * @param aLoc arrival location given by user
     * @param dDate departure date given by user
     * @param aDate arrival date given by user
     * @return  an arraylist showing all possible flights that fit requirements
     */
    public Flight[] getAllFlights (String[] dLoc,String[] aLoc, Date dDate, Date aDate) {
        String dCity = dLoc[0];
        String dState = dLoc[1];
        String aCity = aLoc[0];
        String aState = aLoc[1];
        ArrayList<Flight> temp = flights.searchFlight(dCity, dState, aCity, aState, dDate, aDate);
        Flight[] print;
        if(temp.size() == 0) { print = null;
            // if this is null there are no results.
            System.out.println("No results");
        }
        else print = toFlightArray(temp);
        return print;
    }

     /** Converts flight results to an array that allows user to see all important booking information as well as ability to select the flight(s) they want
     *  @param x- the flight results arraylist
     * @return - Flight results in a string array so that the user can enter which flight(s) they want
     */
    private Flight[] toFlightArray(ArrayList<Flight> x){
        Flight[] temp= new Flight[x.size()];
        for (int i = 0; i< x.size(); i++){
            temp[i] = x.get(i);
        }
        return temp;
    }
 
    public String seatingSelction(Flight[]flights, ArrayList<String> selection, ArrayList<String> friends){
        String [] temp;
        String print = "------------------------BOOKINGS--------------------------------\n";
        for(int i=0; i<selection.size();i++){

            temp = selection.get(i).replaceAll(", ", ",").split(",");
            String [] temp2 = new String[temp.length-2];
            if(flights[i].getAirline().equalsIgnoreCase(temp[0]) && flights[i].getFlightNum()==Integer.parseInt(temp[1])){
                print += flights[i].toString()+"\n"+currentUser.getFullName() +"\tSeat: ";
                for (int j = 0; j<temp2.length;j++){
                    temp2[j]=temp[j+2];
                    if (j == 0) {
                        print+=temp2[j];
                    }
                    if (j>0){
                        print += "\n"+friends.get(j-1)+"\tSeat: "+temp2[j];
                    }
                }      
            }
        }
        return print;
    }

    /**
     * Checks if the current user is a registered user (they would be logged in for this to occur)
     * @return true if the current user is registered
     */
   /* private boolean login(){
        return users.checkIfRegisterd(currentUser);
    }*/
    /**
     * updates user information and flight information in the database and ends program 
     */
    public boolean logout(){
        String username= currentUser.getUsername();
        String password =currentUser.getPassword();
        RegisteredUser edit = users.searchUser(username, password);
        users.editUser(edit);
        currentUser = null;
        flights.logout();
        return false;
        // false will end the program in the UI 
    }

    /**
     * returns the current user
     * @return- the current user
     */
    public User getCurrentUser (){
        return currentUser;
    }
    
    public void setCurrentUser(String[] x, Date dob){
        RegisteredUser newUser = new RegisteredUser(x[0],x[1], x[2], x[3], x[4], x[5], dob);
        currentUser = newUser;
        users.addUser(newUser);
    }

    /**
     * updates registered user information without ending program
     * @param username- account username
     * @param password- account password
     * @param edit- the current user information
     */
    public void editUser(String username,String password, RegisteredUser edit) {
        users.editUser(edit);
    }
      // Overloaded method allows you to add a user by creating a user or by entering an already existing user
    public void addUser(RegisteredUser user){
        users.addUser(user);
            // this version is for when they opt to create an account
    }
    public void addUser (String firstName, String lastName, String username, String password, String email, String address, Date dob){
        users.addUser(new RegisteredUser(firstName, lastName, username, password, email, address, dob));
            // this version is for when they are a guest and want to book
    }

    /**
     * removes users from the database if they decide to delete their accoutn
     * @param - username = account to be deleted username
     * @param - username = account to be deleted password
     */
    public boolean deleteUser(String username, String password) {
        boolean working= true;
        User deleteMe = users.searchUser(username, password);
        if (deleteMe == null) working = false;  
        users.deleteUser(deleteMe);
        return working;
    }

    public ArrayList<Friend> getAllFriends(){
        ArrayList<Friend> friends = currentUser.getFriends();
        return friends;
            // will return null if the user is a guest
    }

    public void addFriends (String[]friend, Date dob){
       Friend temp = new Friend(friend[0],friend[1], friend[2], friend[4], dob, friend[5]);
       users.addFriends(temp, currentUser);
    }
}

