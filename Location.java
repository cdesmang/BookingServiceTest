public class Location {

    private String city;
    private String state;

    public Location(String city, String state)
    {
        this.city = this.state = "";
    }
    public String getCity() 
    {
        return city;
    }
    public String getState()
    {
        return state;
    }
    public void setCity(String city)
    {
        this.city = city;
    }
    public void setState(String state)
    {
        this.state = state;
    }
    
}
