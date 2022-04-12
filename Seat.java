public class Seat {
    private int row;
    private String seatNum;
    private Boolean available;

    public Seat() { }

    public Seat (int row, String seatNum, Boolean available) {
        this.row = row;
        this.seatNum = seatNum;
        this.available = available;
    }

    public int getRow(){
        return row;
    }

    public String getSeatNumber() {
        return seatNum;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setSeatNumber(String seatNum) {
        this.seatNum = seatNum;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}   