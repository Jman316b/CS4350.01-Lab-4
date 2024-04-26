/**
 * Bus object class for Pomona Transit System
 * @author Joshua Jenkins
 * @author Jeremiah Garchia
 * @version 1.0
 */
public class Bus{
    private int busID;
    private String model;
    private int year;

    /**
     * Constructor for Bus object
     * @param busID - busID of the bus
     * @param model - model of the bus
     * @param year - year of the bus
     */
    public Bus(int busID, String model, int year) {
        this.busID = busID;
        this.model = model;
        this.year = year;
    }

    /**
     * Get the busID of the bus
     * @return busID
    */
    public int getBusID() {
        return busID;
    }

    /**
     * Set the busID of the bus
     * @param busID - busID of the bus
    */
    public void setBusID(int busID) {
        this.busID = busID;
    }

    /**
     * Get the model of the bus
     * @return model
    */
    public String getModel() {
        return model;
    }

    /**
     * Set the model of the bus
     * @param model - model of the bus
    */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Get the year of the bus
     * @return year
    */
    public int getYear() {
        return year;
    }

    /**
     * Set the year of the bus
     * @param year - year of the bus
    */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * toString for Bus object
     * @return busID, model, year in string format
    */
    public String toString() {
        return 
        "\n [PK] Bus ID: " + busID + 
        "\n Model: " + model + 
        "\n Year: " + year;
    }
}