/**
 * Creates a date
 * @author David Eta
 * 
 */

public class Date {
    
    private int month;
    private int day;
    private int year;


    public Date (int month,int day ,int year){
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * @return the date in mm/dd/yyyy format.
     */
    public String toString() {
        if (day < 10) {
            return month + "/0" + day + "/" + year;
        }
        return month + "/" + day + "/" + year;
    }
    
    public boolean dateMatch(Date x){
        return this.month == x.month && this.day == x.day && this.year == x.year;
    }
}
