/**
 * an object type Flight that has attributes:
 * departure and arrival time
 * aeparture and arrival location
 * number of tickets
 * type of flight (nonstop, round, etc.)
 */
import java.util.*;

public class Flight {

 private UUID flightID;
 private int flightNum;
 private String airline;
 private String destinationCity;
 private String destinationState;
 private String departureCity;
 private String departureState;
 private Date departureDate;
 private Date arrivalDate;
 private String flightDuration;
 private String departureTime;
 private String arrivalTime;
 private ArrayList<Seat> seats;
 private ArrayList<Integer> connectionIndex;

    /**
     * Constructs a new instance of a flight.
     */
    public Flight(UUID flightID, int flightNum, String airline, String destinationCity, String destinationState, String departureCity, String departureState, Date departureDate, Date arrivalDate, String flightDuration, String departureTime, String arrivalTime, ArrayList<Seat> seats) {
        this.flightID = flightID;
        this.flightNum = flightNum;
        this.airline = airline;
        this.destinationCity = destinationCity;
        this.destinationState = destinationState;
        this.departureCity = departureCity;
        this.departureState = departureState;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.flightDuration = flightDuration;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seats = seats;
    }

    /**
     * @return flight ID
     */
    public UUID getFlightID() {
        return flightID;
    }

    /**
     * @return flight number
     */
    public int getFlightNum() {
        return flightNum;
    }

    /**
     * @return name of airline
     */
    public String getAirline() {
        return airline;
    }

    /**
     * @return destination city
     */
    public String getDestinationCity()  {
        return destinationCity;
    }

    /**
     * @return destination state
     */
    public String getDestinationState()  {
        return destinationState;
    }

    /**
     * @return departure city
     */
    public String getDepartCity()  {
        return departureCity;
    }

    /**
     * @return departure state
     */
    public String getDepartState()  {
        return departureState;
    }

    /**
     * @return flight duration
     */
    public String getFlightDuration()  {
        return flightDuration;
    }

    /**
     * @return departure time
     */
    public String getDepartTime()  {
        return departureTime;
    }

    /**
     * @return arrival time
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @return flight ID
     */
    public Date getDepartDate() {
        return departureDate;
    }
    public Date getArrivalDate() {
        return arrivalDate;
    }
    public ArrayList<Seat> getSeats() {
        return seats;
    }
    public int getConnection(int i) {
        return this.connectionIndex.get(i);
    }
    public ArrayList<Integer> getConnectionString() {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i=0; i < connectionIndex.size(); i++){
            temp.add(connectionIndex.get(i)+1);
        }
        return temp;
    }

    public void setConnection(int i) {
        if (this.connectionIndex == null){
            this.connectionIndex = new ArrayList<Integer>();}
        this.connectionIndex.add(i);
    }
    public void setFlightID(UUID flightID) {
        this.flightID = flightID;
    }
    public void setFlightNum(int flightNum) {
        if (flightNum > 0)
            this.flightNum = flightNum;
    }
    public void setAirline(String airline) {
        this.airline = airline;
    }
    public void setDestinationCity(String city) {
        this.destinationCity = city;
    }
    public void setDestinationState(String state) {
        this.destinationState = state;
    }
    public void setDepartCity(String city) {
        this.departureCity = city;
    }
    public void setDepartState(String state) {
        this.departureState = state;
    }
    public void setDepartTime(String departTime) {
        this.departureTime = departTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public void setDepartDate(Date departDate) {
        this.departureDate = departDate;
    }
    public void setArrivalDate(Date arrivalDate)
    {
        this.arrivalDate = arrivalDate;
    }
    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }
    /**
     * Puts the flight information in a string
     */
    public String toString () {
        String connections=" ";
        if(this.connectionIndex != null){
            for(int i = 0; i< connectionIndex.size(); i++){
                connections += connectionIndex.get(i)+", ";
            }
        } else{
            connections ="NONE";
        }
        return  "\n Airline: "+this.airline+
        "\n Flight Num : "+ this.flightNum+
        "\n Departure Location: "+this.departureCity+", "+ this.departureState+
        "\n Arrival Location: "+this.destinationCity+ ", "+this.destinationState+
        "\n Flight Duration: "+ this.flightDuration+
        "\n Departure Date and Time: "+ this.departureDate.toString()+ " at "+this.departureTime+
        "\n Arrival Date and Time: "+ this.arrivalDate.toString()+ " at "+this.arrivalTime+
        "\n Connecting Flight Result Number(s): "+connections
        +"\n --------------------------------------------------------------------------------------------------------";
    }

    /**
     * Converts the array list of seats to a 2D array with correct rows
     * @return an array of seats
     */
    public Seat[][] toSeatArray() {
        Seat[][] temp;
        int sInRow = 0;
        int numRow =this.seats.get(seats.size()-1).getRow()-1; 
        for (int i = 0 ; i < seats.size(); i++){
            if (seats.get(i).getSeatNumber().equalsIgnoreCase("D")){
                sInRow = 4;
            } else if (seats.get(i).getSeatNumber().equalsIgnoreCase("F")){
                sInRow = 6;
            }
        }
            // this will end on the last one (so the variable will keep getting rewritten but the one it ends on is the true size)}
        temp = new Seat[numRow][sInRow];
        for (int i = 0 ; i< numRow; i++){
            for (int ii = 0; ii<sInRow; ii++){
                temp[i][ii]= seats.get(i);
            }
        }
        return temp;
    }
}
