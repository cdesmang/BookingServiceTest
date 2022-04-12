
/**
 * User Interface 
 * @author Christina Desmangles
 */

import java.util.Scanner;
import java.util.ArrayList;
public class UI {

    private static final Scanner key = new Scanner(System.in);
    private static FlightSystem flightSystem;

    /**
     * main method- interacts with user and runs code
     * @param args
     */
    public static void main(String[] args) {

        boolean run = true;
        while (run) {
            String login= Welcome();
            if (login.equalsIgnoreCase("guest")) {
                run = guestSearch();
            }
            else if (login.equalsIgnoreCase("log in")){
                System.out.println("Please enter your username");
                String name = key.nextLine().replace('\n', ' ').trim();
                System.out.println("Please enter your password");
                String pass = key.nextLine().replace('\n', ' ').trim();
                run = registeredSearch(name, pass);
            }
            else if (login.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                run = false;
            } else if (login.equalsIgnoreCase("sign up")){
                run = createUser(); 
            }else{
                System.out.println("Invalid input!");
                run = true;
            }
        }

        
    }





    /**
     * Prints out a welcome statment to the user that allows us to know how they will be searching.
     * Also gives the user the option to exit the program
     * @return the user response of what they wanna do
     */
    public static String Welcome(){ 
        System.out.println("Welcome to the Flight Booking System!"+
        "\nTo continue as a guest please enter \"guest\" ,to log in to an existing account please enter \"log in\", to create a new account please enter \"sign up\", or to exit please enter \"exit\"");
        String login = key.nextLine().toUpperCase();
        return login;
    }

    /**
     * runs program if user selects that they are a guess
     * will eventually force them to switch to registered user if they want to book
     */
    public static boolean guestSearch(){
        boolean error = true;
	    flightSystem= new FlightSystem();
	    Flight[]flights= getFlights();
        printArrFlights(flights);
	    System.out.println("\n"+"\nPlease enter the result number of the flight(s) you would like to book."+
	                                         "\nUse a comma to separate choice(s), or enter \"none\" if you would not like to book a flight.");
        String selection = key.nextLine().replace('\n', ' ');
        String[]y = selection.split(",");
        boolean run = true;
        while(run){
            System.out.println("Oops! You must be a registered user to book a flight.\n Please enter \"register\" to create and account or enter \"exit\" to leave the Flight Booking System!");
            String guestRegistry = key.nextLine().replaceAll("\n"," ").trim();
            if (guestRegistry.equalsIgnoreCase("register")){
                String[]x = InfoNoBDay();
                System.out.println("Please enter your Date of Birth");
                Date dob = getDate();
                flightSystem.setCurrentUser(x, dob);
                System.out.println("Welcome, "+flightSystem.getCurrentUser().getUsername()+" !");
                System.out.println("Please enter the number of tickets you would like to book:");
                int numFriends = key.nextInt();
                String flightSeats;
                ArrayList<String> friendsUsernames= new ArrayList<String>();
                if (numFriends > 1) {
                    for (int i =0; i<numFriends-1; i++){
                        friendsUsernames.add(addFriends());}
                    flightSeats = flightSystem.flightBooking(selection, flights,friendsUsernames);
                } else{
                 flightSeats = flightSystem.flightBooking(selection, flights,null);}

                 System.out.println(flightSeats+"\n In this chart, \"X\" represents a seat that is not available and \"_\" represents a seat that is available.");
                 ArrayList<String> picked = new ArrayList<String>();
                 for (int i=0 ;i< y.length; i++){
                     System.out.println("Please enter the Airline and Flight number followed by the seats that you would like to book."+"\nThe format should be \"Airline, Flight Number, 1A, 1B ,etc.\"");
                    String seatSelection = key.nextLine().replace('\n', ' ').trim();
                    picked.add(seatSelection);
                }
                System.out.println(flightSystem.seatingSelction(flights, picked,friendsUsernames));
                logout();
                boolean end= false;
                while (!end) {
                    System.out.println("Thank you for booking with Flight Booking Service! \nTo restart the program please enter \"again\" and to exit please enter \"exit\".");
                    String repeat =  key.nextLine().replaceAll("\n", " ").trim();
                    if (repeat.equalsIgnoreCase("again")){ 
                        error = true;
                        end = true;}
                    else if (repeat.equalsIgnoreCase("exit")){
                        error = false;
                        end = true;}
                    else end = false;
                }
            }else if (guestRegistry.equalsIgnoreCase("exit")){
                System.out.println("Goodbye!");
                run= false;
                error = false;
            }else {
               System.out.println("Invalid input!");
               run = true;
            }

        }
/******/if (error == false) System.out.println("Error in guestSearch()");
	    return error;

    }


    /**
     * runs program if user selects that they are registered or would like to register
     */
    private static boolean registeredSearch(String username, String password){
        boolean error = true;
        flightSystem = new FlightSystem(username, password);
        if(flightSystem.getCurrentUser() == null){
            int count = 0;
            while(count < 3){
                System.out.println("Invalid username or password!\n"+(count+1)+" / 3 failed attempts");
                count++;
                System.out.println("Please re-enter your username:");
                String x = key.nextLine().replace('\n',' ').trim();
                System.out.println("Please re-enter your password:");
                String y = key.nextLine().replace('\n', ' ').trim();
                flightSystem= new FlightSystem(x,y);
                if (flightSystem.getCurrentUser() != null) {
                    System.out.println("Welcome, "+flightSystem.getCurrentUser().getUsername()+" !");
                    count = 3;
                }else {
                    if (count == 2){
                        System.out.println("To create a new account enter \"Create\" to exit the program enter \"exit\".");
                        String choice = key.nextLine().replace('n', ' ').trim();
                        if (choice.equalsIgnoreCase("exit")){
                            System.out.println("Exiting system... Goodbye!");
                            error = false;
                            count = 3;
                        } else if (choice.equalsIgnoreCase("create")){
                            String[] info = InfoNoBDay();
                            System.out.println("Please enter your Date of Birth");
                            Date bday = getDate();
                            flightSystem = new FlightSystem(info[0], info[1], info[2], info[3], info[4], info[5],bday);
                            count++;
                        }
                    }
                    
                }
                count++;
            }
        }
        Flight[] flights = getFlights();
        if (flights != null) {
            printArrFlights(flights);
        }
        System.out.println("\n"+"\n Please enter the result number of the flight(s) you would like to book."+
                                        "\n Please use a comma to separate choice(s), or enter \"none\" if you would not like to book a flight.");
        String selection = key.nextLine().replace('\n', ' ');
        String[] x = selection.split(",");
        int intX = Integer.parseInt(selection);
        if (selection.equalsIgnoreCase("none")){
            logout();
            return false;
        }
        System.out.println("Please enter the number of tickets you would like to book:");
        int numFriends = key.nextInt();
        String flightSeats;
        ArrayList<String> friendsUsernames= new ArrayList<String>();
        if (numFriends > 1) {
            for (int i =0; i<numFriends-1; i++){
                friendsUsernames.add(addFriends());
            }
            flightSeats = flightSystem.flightBooking(selection, flights,friendsUsernames);
        } else{
            friendsUsernames = null;
            flightSeats = flightSystem.flightBooking(selection, flights,null);
        }
        System.out.println(flightSeats+"\n In this chart, \"X\" represents a seat that is not available and \"_\" represents a seat that is available.");
        ArrayList<String> picked = new ArrayList<String>();
        for (int i=0 ;i< intX; i++){
            System.out.println("Please enter the Airline and Flight number followed by the seats that you would like to book."+"\nThe format should be \"Airline, Flight Number, 1A, 1B ,etc.\"");
            //key.nextLine();
            String seatSelection = key.nextLine().replace('\n', ' ').trim();
            picked.add(seatSelection);
        }
        System.out.println(flightSystem.seatingSelction(flights, picked,friendsUsernames));
        boolean end= false;
                while (!end) {
                    System.out.println("Thank you for booking with Flight Booking Service! \nTo restart the program please enter \"again\" and to exit please enter \"exit\".");
                    String repeat =  key.nextLine().replaceAll("\n", " ").trim();
                    if (repeat.equalsIgnoreCase("again")){ 
                        error = true;
                        end = true;}
                    else if (repeat.equalsIgnoreCase("exit")){
                        error = false;
                        end = true;}
                    else end = false;
                }
        return error;
    }


    /**
     * runs program if user initally decides to create an account
     * @return - false if there is an error(to end program);
     */
    private static boolean createUser( ){
        boolean error = true;
        String [] info = InfoNoBDay();
        System.out.println("Please enter your Date of Birth.");
        Date bday= getDate();
        flightSystem = new FlightSystem(info[0], info[1], info[2], info[3], info[4], info[5],bday);
        System.out.println("Welcome, "+ flightSystem.getCurrentUser().getUsername()+" !");
        Flight[] flights = getFlights();
        printArrFlights(flights);
        System.out.println("\n"+"\nPlease enter the result number of the flight(s) you would like to book."+
                                        "\nPlease use a comma to separate choice(s), or enter \"none\" if you would not like to book a flight.");
        String selection = key.nextLine().replace('\n', ' ');
        String[]x = selection.split(",");
        System.out.println("Please enter the number of tickets you would like to book:");
        int numFriends = key.nextInt();
        String flightSeats;
        ArrayList<String> friendsUsernames= new ArrayList<String>();
        if (numFriends > 1) {
            for (int i =0; i<numFriends-1; i++){
                friendsUsernames.add(addFriends());
            }
            flightSeats = flightSystem.flightBooking(selection, flights,friendsUsernames);
        } else{
            flightSeats = flightSystem.flightBooking(selection, flights,null);
        }
        System.out.println(flightSeats+"\n In this chart, \"X\" represents a seat that is not available and \"_\" represents a seat that is available.");
        ArrayList<String> picked = new ArrayList<String>();
        for (int i=0 ;i< x.length; i++){
            System.out.println("Please enter the Airline and Flight number followed by the seats that you would like to book."+"\nThe format should be \"Airline, Flight Number, 1A, 1B ,etc.\"");
            String seatSelection = key.nextLine().replace('\n', ' ').trim();
            picked.add(seatSelection);
        }
        System.out.println(flightSystem.seatingSelction(flights, picked,friendsUsernames));
        boolean end= false;
        while (!end) {
            System.out.println("Thank you for booking with Flight Booking Service! \nTo restart the program please enter \"again\" and to exit please enter \"exit\".");
            String repeat =  key.nextLine().replaceAll("\n", " ").trim();
            if (repeat.equalsIgnoreCase("again")){ 
                error = true;
                end = true;}
            else if (repeat.equalsIgnoreCase("exit")){
                error = false;
                end = true;}
            else end = false;
        }
        return error;
    }

    private static boolean logout(){
        System.out.println("Goodbye, "+flightSystem.getCurrentUser().getUsername()+" !");
        return flightSystem.logout();
    }


    /**
     * Connects to flights class to find all flights that fit the given parameters
     * @return an string[] with all flights 
     */
    private static Flight[] getFlights(){

        System.out.println("Please enter your departure location in the format \"city, State\"");
        String dLString = key.nextLine().replaceAll("\n", " ").trim();
        String[] dLoc = parseLoc(dLString);
        System.out.println("Getting departure date information:");
        Date dDate= getDate();
        System.out.println("Please enter your arrival location in the format \"city, State\"");
        String aLString= key.nextLine().replaceAll("\n", " ").trim();
        String[] aLoc = parseLoc(aLString);
        System.out.println("Getting arrival date information:");
        Date aDate = getDate();
        Flight[]flights = flightSystem.getAllFlights(dLoc, aLoc, dDate, aDate);
        return flights;
    }
    
    /**
     * prints the search results
     * @param x- string arr
     */
    private static void printArrFlights(Object[]x) {
        if (x == null){
            System.out.println("No results found");
        }else{ 
            System.out.println("Search Results:");
            for (int i = 0; i<x.length;i++) {
                System.out.println("Result "+(i+1)+"\t"+x[i]);
            }
        }
    }
    /**
	 * Collects information from user about creatiing an account(except birthday)
	 * @return an array with all necessary information to create an new registered user except for the birthdate
	 */
	private static String[] InfoNoBDay(){
	    String[] temp = new String[6];
	    System.out.println("Please enter the first name that would appear on any legal documentation: ");
        key.nextLine();
	    temp[0] = key.nextLine().replace('\n',' ').trim();
	    System.out.println("Please enter the last name that would appear on any legal documentation: \n");
	    temp[1] = key.nextLine().replace('\n', ' ').trim();
	    System.out.println("Please enter a username: ");
	    temp[2] = key.nextLine().replace('\n',' ').trim();
	    System.out.println("Please enter a password: ");
	    temp[3] = key.nextLine().replace('\n',' ').trim();
	    System.out.println("Please enter an email: ");
	    temp[4] = key.nextLine().replace('\n',' ').trim();
	    System.out.println("Please enter the Street address ie. \"100 President St. City, State 11111\"");
	    temp[5]= key.nextLine().replace('\n',' ').trim();
	    return temp;
	    }

    /**
     * Converts a string entered by user to a location type
     * @param location User input of location(string)
     * @return Location type version of user input
     */
    private static String[] parseLoc(String location){
        String[] splitLoc= location.split(",");
        splitLoc[0] = splitLoc[0].trim();
        splitLoc[1] = splitLoc[1].trim();
        return splitLoc;
    }

    /**
     * Converts a string entered by the user to a date type
     * @return Date type of user input
     */
    private static Date getDate(){
        String[] tempS;
        int [] tempI = new int[3];
        while (tempI[0]==0|| tempI[1]==0){
            System.out.println("Enter a date: MM/DD/YYYY");
            String x = key.nextLine();
            tempS= x.split("/");
            for (int i = 0; i<3; i++){
                tempI[i] = Integer.parseInt(tempS[i]);
            }
            if(tempI[0] >= 13){ //invalid month
                System.out.println("Invalid Month");
                tempI[0] = 0;}
            if(tempI[1] >= 32) {//invalid day
                System.out.println("Invalid Day");
                tempI[1] = 0;}
        }
        tempS= new String[3];
        for (int i = 0; i< tempI.length; i++){
            tempS[i]= String.valueOf(tempI[i]);
        }
        Date created= new Date (tempI[0], tempI[1], tempI[2]);
        return created;
    }
    /**
     * Creates a new friend for the user and adds them to the arraylist
     * @return - new friends username
     */
    private static String addFriends(){
        System.out.println("Please enter the following information about those for whom you would like to book a ticket.");
        String[] newFriend = InfoNoBDay();
        Date fDate = getDate();
        flightSystem.addFriends(newFriend, fDate);
        return newFriend[2];
            //friends username
    }

    
}

