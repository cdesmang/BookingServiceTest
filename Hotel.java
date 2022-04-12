import java.util.ArrayList;

import java.util.*;
public class Hotel {
    private UUID hotelID;
    private String name;
    private String phoneNumber;
    private Address address;
    private ArrayList<Room> rooms;
    private ArrayList<Amenities> amenities;

    public Hotel(UUID hotelID, String name, String phoneNumber, Address address, ArrayList<Room> rooms, ArrayList<Amenities> amenities)
    {
        rooms = new ArrayList<Room>();
        amenities = new ArrayList<Amenities>();
    }
    public UUID getHotelID()
    {
        return hotelID;
    }
    public String getName()
    {
        return name;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public Address getAddress()
    {
        return address;
    }
    public ArrayList<Room> getRooms()
    {
        return rooms;
    }
    public ArrayList<Amenities> getAmenities()
    {
        return amenities;
    }
    public void setHotelID(UUID hotelID)
    {
        this.hotelID = hotelID;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public void setAddress(Address address)
    {
        this.address.streetAddress = address.streetAddress;
        this.address.city = address.city;
        this.address.state = address.state;
        this.address.zip = address.zip;
    }
    public void setRooms(ArrayList<Room> rooms)
    {
        this.rooms = rooms;
    }
    public void setAmenities(ArrayList<Amenities> amenities)
    {
        this.amenities = amenities;
    }

    //Method for adding amenities
    
    //Method for adding rooms


    public ArrayList<Room> checkRooms (ArrayList<Room> hotelRooms) {
        // wth is this supposed to do?? why does it return an arraylist
        ArrayList<Room> temp = new ArrayList<Room>();
        return temp;
    }
}
