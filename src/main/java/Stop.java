/**
 * Stop object class for the Pomona Transit System.
 * @author Joshua Jenkins
 * @author Jeremiah Garchia
 * @version 1.0
 */
public class Stop{
    private int stopNumber;
    private String stopAddress;

    /**
     * Constructor for Stop object
     * @param stopNumber - stop number
     * @param stopAddress - stop address
     */
    public Stop(int stopNumber, String stopAddress) {
        this.stopNumber = stopNumber;
        this.stopAddress = stopAddress;
    }

    /**
     * Get the stop number
     * @return stopNumber
     */
    public int getStopNumber() {
        return stopNumber;
    }

    /**
     * Set the stop number
     * @param stopNumber - the stop number
     */
    public void setStopNumber(int stopNumber) {
        this.stopNumber = stopNumber;
    }

    /**
     * Get the stop address
     * @return address of the stop
     */
    public String getStopAddress() {
        return stopAddress;
    }
    
    /**
     * Set the stop address
     * @param stopAddress - address of the stop
     */
    public void setStopAddress(String stopAddress) {
        this.stopAddress = stopAddress;
    }

    /**
     * toString method for Stop object
     * @return - stopNumber and stopAddress in string format
     */
    public String toString() {
        return 
        "\n [PK] Stop Number: " + stopNumber + 
        "\n Stop Address: " + stopAddress;
    }
}