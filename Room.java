public class Room {

    private int floor;
    private int roomNum;
    private int numBeds;
    private BedTypes bedtype;
    private boolean hasPullout;
    private boolean Available;

    public Room (int floor, int roomNum, boolean hasPullout) {
        this.floor = floor;
        this.roomNum = roomNum;
        this.hasPullout = hasPullout;
    }

    //getters
    public int getFloor (Room room) {
        return room.floor;
    }

    public int getRoomNum (Room room) {
        return room.roomNum;
    }

    public int getNumBeds (Room room) {
        return room.numBeds;
    }

    public boolean getHasPullout (Room room) {
        return hasPullout;
    }

    public BedTypes getBed (Room room){
        return room.bedtype;
    }

    public boolean getAvailibilty (Room room) {
        return room.Available;
    }
    
    //setters
    public void setFloor (int floor) {
        this.floor = floor;
    }

    public void setRoomNum (int num) {
        this.roomNum = num;
    }

    public void setNumBeds (int num) {
        this.numBeds = num;
    }

    public void setPullout (boolean pullout) {
        this.hasPullout = pullout;
    }

    public void setBedtype (BedTypes bed) {
        this.bedtype = bed;
    }

    public void setAvailibility (boolean avail) {
        this.Available = avail;
    }
}

