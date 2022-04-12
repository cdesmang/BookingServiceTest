/**
 * Everything having to do with flights
 * @author Christina Desmangles
 */
import java.util.ArrayList;
import java.util.UUID;
public class Flights {

    private static Flights flights = null;
    private static ArrayList<Flight> allFlights = new ArrayList<>();
   // private static DataLoader DL = new DataLoader();

    /**
     * initializes the arraylist of all flights fromthe data loader
     */
    private Flights(){
        allFlights = DataLoader.loadFlights();
    }

    /**
     * Flights is a singleton; this method allows the only instance of the class to be accessed
     * @return the instance of Flight class
     */
    public static Flights getInstance(){
        if (flights == null) {
            flights = new Flights();
        }
        return flights;

    }

    /**
     * Searches the larger array list for flights that fit the requirements from the user
     * @param dCity- departure city
     * @param dState- departure state
     * @param aCity- arrival city
     * @param aState- arrival state
     * @param dDate- departrue date
     * @param aDate- arrival date
     * @return- an arraylist holding all flights that fir the parameters
     */
    public ArrayList<Flight> searchFlight(String dCity, String dState, String aCity, String aState, Date dDate, Date aDate){
        int i = 0;
        ArrayList<Flight> temp = new ArrayList<Flight>();
        ArrayList<Flight> tempD = new ArrayList<Flight>();
        ArrayList<Flight> tempA = new ArrayList<Flight>();
        while(i < allFlights.size()){
            if (checkDL(dCity,dState,i) && checkAL(aCity, aState, i) && checkDD(dDate, i) && checkAD(aDate, i)){
                temp.add(allFlights.get(i));
                temp.get(temp.size()-1).setConnection(00);
                i++;
            } else if (checkDL(dCity,dState,i) &&checkDD(dDate, i)){
                tempD.add(allFlights.get(i));
                i++;
            } else if (checkAL(aCity, aState, i)&&checkAD(aDate, i)){
                tempA.add(allFlights.get(i));
                i++;
            }else{
                i++;
            }
        }
        int j = 0;
        ArrayList<Flight> connection = checkConnection(tempD, tempA);
        while(j<connection.size()){
            temp.add(connection.get(i));
            j++;
        }
        return temp;
    }
    public Flight[] searchOneFlight(String airline, int flightNum, Flight[] flights){
        Flight[] temp = new Flight[1];
        for (int i = 0; i< flights.length; i++){
            if(flights[i].getAirline().equalsIgnoreCase(airline) && flights[i].getFlightNum()==flightNum){
               temp[0]=flights[i];
            }
        }
        return temp;
    }

    /**
     * Checks to make sure flights with only partially correct info have a connection
     * @param x- flights with matching departures
     * @param y- flights with matching arrival
     * @return - flights that fit OG parameters but need a connection
     * 
     * 
     * I added a connection index to the flight object ( but this may need to be an arraylist because what if there are multiple possible connections)
     */
    private ArrayList<Flight>checkConnection(ArrayList<Flight> x, ArrayList<Flight> y){
        ArrayList<Flight> temp = new ArrayList<Flight>();
        for (int i = 0; i < x.size(); i++){
            for (int j = 0; j< y.size(); j++){
                if(x.get(i).getDestinationCity().equalsIgnoreCase(y.get(j).getDepartCity())&&x.get(i).getDestinationState().equalsIgnoreCase(y.get(j).getDepartState())){
                    temp.add(x.get(i));
                    temp.get(temp.size()-1).setConnection(temp.size()-1);
                    temp.add(y.get(j));
                    temp.get(temp.size()-1).setConnection(temp.size()-2);
                }
            }
        }
        return temp;
    }

    /**
     * Checks if there is a flight with the specificed city and state as the departure location
     * @param city - departure city
     * @param state - departure state
     * @param i- index in arraylist from dataloader
     * @return true if the flight exists
     */
    private boolean checkDL(String city, String state,int i){
        try{
           return allFlights.get(i).getDepartCity().equalsIgnoreCase(city) && allFlights.get(i).getDepartState().equalsIgnoreCase(state);
           
        } catch(Exception e){
            return false;
        }
        
    }

    /**
     * Checks if there is a flight with the specificed city and state as the arrival location
     * @param city - arrival city
     * @param state - arrival state
     * @param i- index in arraylist from dataloader
     * @return true if the flight exists
     */
    private boolean checkAL(String city, String state,int i){
        return allFlights.get(i).getDestinationCity().equalsIgnoreCase(city) && allFlights.get(i).getDestinationState().equalsIgnoreCase(state);
    }

    /**
     * Checks if there is a flight with the specified departure date
     * @param date- departure date
     * @param i- index in arraylist from dataloader
     * @return - true if the flight exists
     */
    private boolean checkDD(Date date, int i){
        return allFlights.get(i).getDepartDate().dateMatch(date);
    }

    /**
     * Checks if there is a flight with the specified arrival date
     * @param date- arrival date
     * @param i- index in arraylist from dataloader
     * @return - true if the flight exists
     */
    private boolean checkAD(Date date, int i){
        return allFlights.get(i).getArrivalDate().dateMatch(date);
    }
    
    public ArrayList<Flight> getFlights() { 
        return allFlights;
    }

    public void addFlight(UUID flightID, int flightNum, String airline, String destinationCity, String destinationState, String departureCity, String departureState, Date departureDate, Date arrivalDate, String flightDuration, String departureTime, String arrivalTime, ArrayList<Seat> seats) {
        allFlights.add(new Flight(flightID, flightNum, airline, destinationCity, destinationState, departureCity, departureState, departureDate, arrivalDate, flightDuration, departureTime, arrivalTime, seats));
    }

    public void logout() {
        DataWriter.saveFlights();
    }
    
}